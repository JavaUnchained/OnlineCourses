from scipy.ndimage import correlate1d
import cv2
import math
from scipy.spatial import distance
import os.path
import numpy as np
import matplotlib.pyplot as plt


def filtering(image,halo,sigma):
    height = image.shape[0]
    width = image.shape[1]
    #out_image=np.empty_like(image)

    #pre-calculatespatial_gaussian
    spatial_gaussian=[]
    for i in range(- halo,halo + 1):
        for j in range(- halo,halo + 1):
            spatial_gaussian.append(np.exp(-0.5*(i**2+j**2)/(max(1,halo/2)**2)))
    padded=np.pad(image,halo,mode="edge")

    out_image = np.zeros(image.shape)
    weight = np.zeros(image.shape)

    idx=0
    for row in range(-halo,1+halo):
        for col in range(-halo,1+halo):
            value=np.exp(-0.5*(padded[halo+row:height+halo+row,halo+col:width+halo+col]-image)**2)*spatial_gaussian[idx]
            out_image += value*padded[halo+row:height+halo+row,halo+col:width+halo+col]
            weight += value
            idx += 1

    out_image/=weight
    img_lib
    returnout_image


def distance(x,y,i,j):
    return np.sqrt((x-i)**2+(y-j)**2)

def gaussian(x,sigma):
    return(1.0/(2*math.pi*(sigma**2)))*math.exp(-(x**2)/(2*sigma**2))

def apply_bilateral_filter(source,filtered_image,x,y,diameter,sigma_i,sigma_s):
    hl=diameter/2
    i_filtered=0
    Wp=0
    i=0
    while i < diameter:
        j=0
        while j < diameter:
            neighbour_x = x-(hl-i)
            neighbour_y = y-(hl-j)
            if neighbour_x >= len(source):
                neighbour_x -= len(source)
            if neighbour_y >= len(source[0]):
                neighbour_y -= len(source[0])
            gi=gaussian(source[neighbour_x][neighbour_y]-source[x][y],sigma_i)
            gs=gaussian(distance(neighbour_x,neighbour_y,x,y),sigma_s)
            w=gi*gs
            i_filtered+=source[neighbour_x][neighbour_y]*w
            Wp+=w
            j+=1
        i+=1
    i_filtered=i_filtered/Wp
    filtered_image[x][y]=int(round(i_filtered))
    img_lib=cv2.fastNlMeansDenoising

def bilateral_filter_own(source,filter_diameter,sigma_i,sigma_s):
    filtered_image=np.zeros(source.shape)

    i=0
    while i < len(source):
        j=0
        while j < len(source[0]):
            apply_bilateral_filter(source,filtered_image,i,j,filter_diameter,sigma_i,sigma_s)
            j+=1
        i+=1
    return filtered_image

#def noisy(noise_typ,image,sigma_input):
#   print(image.shape)
#   row,col=image.shape
#   mean=0
#   sigma=sigma_input
#   gauss=np.random.normal(mean,sigma,(row,col))
#   gauss=gauss.reshape(row,col)
#   noisy=image+gauss
#   return noisy
img_bil=cv2.bilateralFilter

for folder in ['output/gauss','output/bilateral','output/nlmeans']:
    if not os.path.exists(folder):
        os.makedirs(folder)

#Добавить шум в изображение img с сигмой sigma и мю=mu
def noisy(img,sigma,mu=0):
    gauss=np.random.normal(mu,sigma,img.shape)
    return np.clip(img+gauss,0,255).astype(np.uint8)


#Отобразить изображение"a"
def get_mse(img):
    global source_img
    return np.sum(np.square(img-source_img))/img.size

def to_str(val):
    return'{0:.1f}'.format(val)


#Добавить наизображение img текст title
def add_title(img,title):
    cv2.putText(img,title,(10,int(30)),cv2.FONT_HERSHEY_COMPLEX_SMALL,1,(255,0,0),1,cv2.LINE_AA)

#Создать мерное 1-ядро Гаусса с нормализацией
def kern(s,r,normalize=True):
    o=np.exp(-0.5/(s*s)*np.arange(-r,r+1)**2)
    if normalize:
        return o/o.sum()
    return o

def half_kern(s,r):
    return np.exp(-0.5*np.arange(0,r+1)/(s*s))


#Создать мерное 2-ядро  Гаусса с сигмой=s и радиусом=r.
#Если normalize==True,то возвращаетнормализированное ядро
def kern2d(s,r,normalize=True):
    res=[[i*i+j*j for j in range(-r,r+1)]for i in range(-r,r+1)]
    res=np.asarray(res)
    res=np.exp(-0.5/(s*s)*res)
    if normalize:
        return res/res.sum()
    return res

#Применить фильтр Гаусса к изображению noisy_img
def gauss_filter(noisy_img,sigma):
    tr=4.0
    radius=int(float(sigma)*tr+0.5)
    weights=kern(sigma,radius)
    res=noisy_img
    for i in range(2):
        res=correlate1d(res,weights,i,mode='reflect')
    return res



def gauss_all(source_img,sigma):
    result=gauss_filter(noisy_img,sigma)
    return result


def extend_border(img,radius):
    left_side=img[:,:radius]
    right_side=img[:,img.shape[1]-radius:]
    left_flipped=np.flip(left_side,1)
    right_flipped=np.flip(right_side,1)
    h_extended=np.hstack((left_flipped,img,right_flipped))

    top_side=h_extended[:radius,:]
    bottom_side=h_extended[h_extended.shape[0]-radius:,:]
    top_flipped=np.flip(top_side,0)
    bottom_flipped=np.flip(bottom_side,0)
    return np.vstack((top_flipped,h_extended,bottom_flipped))


def shrink_border(img,radius):
    h,w=img.shape
    return img[radius:h-radius,radius:w-radius]


def to_int(img):
    return np.clip(img,0,255).astype(np.uint8)


#Применить билатеральный фильтр к изображению noisy_img
def bilateral_filter(noisy_img,sigma_s,sigma_r):
    tr=4.0
    radius=int(float(sigma_s)*tr+0.5)
    print("space sigma for bilateral filter = "+to_str(sigma_s))
    print("use␣automatically␣defined␣radius="+str(radius))
    print("␣␣␣␣range␣sigma␣for␣bilateral␣filter␣=␣"+str(sigma_r))
    g_s=kern2d(sigma_s,radius,normalize=False)
    f_=half_kern(sigma_r,255)
    img=extend_border(noisy_img,radius)
    h,w=img.shape
    res=np.zeros((h,w))

    def apply_gaus(matr,sigma_s):
        return np.exp(-0.5*np.square(matr)/sigma_s**2)

    for i in range(radius,h-radius):
        for j in range(radius,w-radius):
            #f=apply_gaus(Omega-img[i,j],sigma_r)
            Omega=img[i-radius:i+radius+1,j-radius:j+radius+1]
            f=np.take(f_,np.abs(Omega-img[i,j]))
            tmp=g_s*f
            Wp=np.sum(tmp)
            res[i,j]=np.sum(tmp*Omega)/Wp
    
    return shrink_border(to_int(res),radius)


def bilateral_all():
    globalsource_img
    filter_name='bilateral'
    print(filter_name+'filter')
    report_file_path='output/'+filter_name+'_report.txt'

    report=open(report_file_path,'w')
    report.write('%15s%15s%15s%15s%15s\n'%('noise_sigma','sigma_s','sigma_r','noise_mse','result_mse'))
    for noise_sigma_ind in range(len(noise_sigmas)):
        noise_sigma=noise_sigmas[noise_sigma_ind]
        print('␣␣sigma␣for␣noise␣=␣'+str(noise_sigma)+'...')
        noisy_img=noisy_imgs[noise_sigma_ind]
        noise_mse=get_mse(noisy_img)
        print('␣␣mse␣for␣the␣noise␣=␣'+to_str(noise_mse))
        mse_list=[]
        sigma_first=0.1
        sigma_last=2.0
        sigma_step=0.1
        for sigma in np.arange(sigma_first,sigma_last,sigma_step):
            for sigma_r in range(1,10):
                result=bilateral_filter(noisy_img,sigma,sigma_r)
                file_path='output/'+filter_name+'/noise'+str(noise_sigma)+'_spacesigma'+to_str(sigma)+'_rangesigma='+str(sigma_r)+'.png'
                cv2.imwrite(file_path,result)
                mse=get_mse(result)
                mse_list.append({'mse':mse,'sigma':sigma,'sigma_r':sigma_r})
                print('␣␣␣␣mse␣=',to_str(mse))
                print('␣␣␣␣saved␣to␣'+ file_path)
                report.write('%15s%15s%15s%15s%15s\n'%(str(noise_sigma),to_str(sigma),str(sigma_r),to_str(noise_mse),to_str(mse)))
        #mse_min=min(mse_list)
        #mse_min_ind=mse_list.index(mse_min)
        #print('␣␣minimum␣mse␣=␣'+to_str(mse_min)+'␣for␣sigma␣=␣'+to_str(mse_min_ind*sigma_step+sigma_first))
        print()
        print('report␣saved␣to␣'+report_file_path)
        report.close()

#Применить к изображению noisy_img фильтр Non-localmeans
def nlmeans_filter_wikipedia_variant(noisy_img):
    sigma_r=40
    radius=0
    print('nlmeans_filter␣for␣radius␣=',radius,'...')
    res=extend_border(noisy_img.copy(),radius)
    h,w=res.shape
    img=res.copy()
    B=np.zeros((h,w))
    r2p1=radius*2+1
    for i in range(radius,h-radius):
        for j in range(radius,w-radius):
            v_i=img[i-radius:i+radius+1,j-radius:j+radius+1]
            B[i,j]=np.sum(v_i)/(r2p1*r2p1)
            #print('B1␣=',B)
    B=B.astype(np.int)
    #print('B2␣=',B)
    #input()
    B=B[radius:h-radius,radius:w-radius]
    h_,w_=h-2*radius,w-2*radius
    #gaus=kern2d(sigma_r,256,normalize=False)
#def apply_gaus(a,b):
#return gaus[int(a),int(b)]
#vec=np.vectorize(apply_gaus)

    for i in range(radius,h-radius):
        print(i,'/',h)
        for j in range(radius,w-radius):
            f_=np.exp(-0.5*(np.square(B-B[i-radius,j-radius]))/sigma_r**2)
            Wp=np.sum(f_)
            v=img[radius:h-radius,radius:w-radius]
            vf=(v*f_)
            res[i,j]=np.sum(vf)/Wp
    res=np.clip(res,0,255).astype(np.uint8)
#res=cv2.cvtColor(res,cv2.COLOR_GRAY2BGR)
#add_title(res,'nlmeans')
    return shrink_border(to_int(res),radius)


#Применитькизображениюnoisy_imgфильтрNon-localmeans
def nlmeans_filter(noisy_img,sigma_r):
    radius=0
    #window_size=radius*2+1
    #img=extend_border(noisy_img,radius)
    #h,w=img.shape
    #res=img.copy()
    #foriinrange(radius,h-radius):
    #forjinrange(radius,w-radius):
    #print(i,j)
    #v_p=img[i-radius:i+radius+1,j-radius:j+radius+1]
    #w_p=0
    #res[i,j]=0
    #forkinrange(radius,h-radius):
    #forlinrange(radius,w-radius):
    #v_q=img[k-radius:k+radius+1,l-radius:l+radius+1]
    #val=np.exp(-0.5*np.square(np.sum(np.square(v_p-v_q)))/sigma_r*sigma_r)
    #w_p+=val
    #res[i,j]+=val*img[k,l]
    #res[i,j]/=w_p
    new_img=img_lib(noisy_img,30.0,sigma_r,21)
    return shrink_border(new_img,radius)

def get_mse(source_img,img):
    return np.sum(np.square(img-source_img))/img.size

#Считываем исходную картинку в перменную source_img
source_img=cv2.imread('noise0.png',cv2.IMREAD_GRAYSCALE)
cv2.imwrite("noise0gray.png",source_img)
noisy_imgs=[]
noise_sigmas=[15]

gauss_error=[]
bilateral_error=[]
nlmeans_error=[]
sig_filter=np.arange(0.1,7,0.5)
param_filter_bilateral=np.arange(1,50,10)

for noise_sigma in noise_sigmas:
    noisy_img=noisy(source_img,noise_sigma)
    noise_file_path='bird_sigma_'+str(noise_sigma)+'.png'
    cv2.imwrite(noise_file_path,noisy_img)
    for sigma_filter in sig_filter:
        print("------for␣noise␣sigma␣"+str(noise_sigma)+"␣and␣"+str(sigma_filter)+"--------------")
        gauss_img=gauss_all(noisy_img,sigma_filter)
        gauss_mse=get_mse(source_img,gauss_img)
        gauss_error.append(gauss_mse)
        print("gauss␣:␣"+str(gauss_mse))

        cv2.imwrite("gauss_filter/gauss_bird_sigma_"+str(noise_sigma)+"_"+str(sigma_filter)+".png",gauss_img)
        y=61
        bilateral_img = filtering(noisy_img,int(sigma_filter),y)
        bilateral_mse=get_mse(source_img,bilateral_img)
        bilateral_error.append(bilateral_mse)
        print("bilateral␣:␣"+str(bilateral_mse))
        cv2.imwrite("bilateral_filter/bilateral_bird_sigman_"+str(noise_sigma)+"sigmaf_"+str(sigma_filter)+"2param_"+str()+".png",bilateral_img)

        nlmeans=nlmeans_filter(noisy_img,sigma_filter*3)
        nlmeans_mse=get_mse(source_img,nlmeans)
        nlmeans_error.append(nlmeans_mse)
        print("nlmeans␣:␣"+str(nlmeans_mse))
        cv2.imwrite("nlmeans_filter/nlmeans_bird_sigma_"+str(noise_sigma)+"_"+str(sigma_filter*3)+".png",nlmeans)
        print(",→------------------------------------------------------------------------------------------")

print(gauss_error)
print(bilateral_error)
print(nlmeans_error)

new_bilateral_error=[]
for x in bilateral_error:
    new_bilateral_error.append(x*50/145)

print(new_bilateral_error)

plt.plot(sig_filter,gauss_error)
plt.legend(('gauss'),loc='upper␣left')
plt.show()
plt.plot(sig_filter,new_bilateral_error)
plt.legend(('bilateral'),loc='upper␣left')
plt.show()
plt.plot(sig_filter,nlmeans_error)
plt.legend(('nlmeans'),loc='upper␣left')
plt.show()