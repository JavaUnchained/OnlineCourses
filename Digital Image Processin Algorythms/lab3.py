import cv2 as cv
import numpy as np
from math import exp

def gfunc(x,y,sigma):
 return (exp(-(x**2 + y**2)/(2*(sigma**2))))/(2*3.14*(sigma**2))

def gaussFilter(size, sigma):
    out = np.zeros(size)
        for i in range(size[0]):
            for j in range(size[1]):
                out[i,j] = gfunc(i-size[0]//2,j-size[1]//2, sigma )
    return out/np.sum(out)

def nlmfunc(i, j, fw, fh, nw, nh ,image,sigma1, sigma2, nlmWFilter):
 imgmain = image[i - fh//2:i+1 + fh//2, j - fw//2:j+1 + fw//2, :]

 nlmFilter = 0
 for p in range(-(nh//2), 1+(nh//2)):
    for q in range(-(nw//2), 1+(nw//2)):
        imgneighbour = image[i + p - fh//2: i+1+p + fh//2, j+q - fw//2:j+1+q + fw//2, :]
        nlmIFilter = ((imgmain - imgneighbour)**2 )/(2*(sigma1**2))
        nlmFilter += np.exp(-1*nlmIFilter)

 nlmFilter = nlmFilter/np.sum(nlmFilter,axis=(0,1))
 nlmFilter = nlmFilter*nlmWFilter
 nlmFilter = nlmFilter/np.sum(nlmFilter,axis=(0,1))

 return np.sum(np.multiply(imgmain, nlmFilter),axis=(0,1))

def nlmFilterConv(image, kernel_size, sigma_i, sigma_s):
    size = image.shape

    nw = kernel_size
    nh = kernel_size
    nlmWFilter1 = 2*3.14*sigma_s*sigma_s*gaussFilter((kernel_size, kernel_size), sigma_s)
    if len(image.shape) < 3 or image.shape[2] == 1:
        nlmWFilter = np.resize(nlmWFilter1,(*nlmWFilter1.shape,1))
    else:
        nlmWFilter = np.stack([nlmWFilter1, nlmWFilter1, nlmWFilter1], axis=2)
    out = np.zeros((size[0]-2*kernel_size +1-nw//2,size[1]-2*kernel_size +1-nh//2, size[2]))

    for i in range(nh//2, size[0]-2*kernel_size +1-nh//2):
        for j in range(nw//2, size[1]-2*kernel_size +1- nw//2):
            out[i,j,:] = nlmfunc(i+kernel_size-1, j+kernel_size-1, kernel_size, kernel_size,nw, nh, image, sigma_i, sigma_s, nlmWFilter)
    if size[2] == 1:
        out[:,0:nw//2,:] = out[:,nw//2,:,np.newaxis]
        return np.resize(out, (out.shape[0], out.shape[1]))
    else:
        out[0:nh//2,:,:] = out[nh//2,:,:]
        return out

def SAD(origin_image, result_image):
 height = result_image.shape[0]
 width = result_image.shape[1]
 AreaImage = height * width
 res = 0

 for i in range(height):
    for j in range(width):
        diff = origin_image[i][j] - result_image[i][j]
        res += abs(diff)

 res /= AreaImage
 return round(res[0])


Sigma = float(input("Sigma = "))
img = cv.imread("./noise4.PNG", 0)
if len(img.shape) < 3:
 img = img[:, :, np.newaxis]
noise = np.random.normal(0, 12, img.shape)
img_changed = (img + noise)
Kernel_size = int(4 * Sigma + 1)
img_new = nlmFilterConv(img_changed, Kernel_size, 20, Sigma)
metric = SAD(img, img_new)
print(metric)
img_changed /= np.max(img_changed)
img_new /= np.max(img_new)
cv.imshow('Normal image', img)
cv.imshow("Noise image", img_changed)
cv.imshow("NLM filter", img_new)