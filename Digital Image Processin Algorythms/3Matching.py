import numpy as np
import cv2 as cv
from matplotlib import pyplot as plt
from copy import deepcopy as dc

def find_near(my_arr, target):
    dif = my_arr - target
    mask = np.ma.less_equal(dif, -1)
    if np.all(mask):
        c = np.abs(dif).argmin()
        return c # вернёт минимальный индекс ближайшего обхект
    masked_diff = np.ma.masked_array(dif, mask)
    return masked_diff.argmin()

def hist_match(original, specified):
    oldshape = original.shape
    original = original.ravel()
    specified = specified.ravel()
    
    # получаем набор уникальных значений пикселей и соответствующих им индексы счетчиков
    s_val, bin_idx, s_count = np.unique(original, return_inverse=True,return_counts=True)
    t_val, t_count = np.unique(specified, return_counts=True)

    s_quant = np.cumsum(s_count).astype(np.float64)
    s_quant /= s_quant[-1]
    t_quant = np.cumsum(t_count).astype(np.float64)
    t_quant /= t_quant[-1]

    # округляем значения
    sour = np.around(s_quant*255)
    temp = np.around(t_quant*255)
    
    # маппинг округленных значений
    b=[]
    for data in sour[:]:
        b.append(find_near(temp,data))
    b= np.array(b,dtype='uint8')
    return b[bin_idx].reshape(oldshape)


def for_hist(image):
    hist = cv.calcHist([image],[0],None,[256],[0,256])
    pixs = hist.transpose()[0]
    s =[0]*256
    all = sum(pixs)
    s[0] = pixs[0]/all
    s[0] = s[0]
    for i in range(1,256): 
        s[i] = s[i-1] + pixs[i]/all
        s[i] = s[i]
    return s

# загружаем 2 изображения нужно и референс
original = cv.imread("source/6.JPG",0)
reference = cv.imread("source/7.JPG",0)
# приведение гистограмм
a = hist_match(original, reference)
target = np.array(a,dtype='uint8')

# выводим гистограммы
plt.plot(for_hist(original), color = 'r')
plt.plot(for_hist(reference), color = 'g')
plt.plot(for_hist(target), color = 'b')
plt.show()

hist1 = cv.calcHist([original],[0],None,[256],[0,256])
hist2 = cv.calcHist([reference],[0],None,[256],[0,256])
hist3 = cv.calcHist([target],[0],None,[256],[0,256])
plt.plot(hist1, color = 'r')
plt.plot(hist2, color = 'b')
plt.plot(hist3, color = 'g')
plt.show()

#сохранить изображение
cv.imwrite ( '6mathing.png' , target)
# выводим изображение
cv.imshow('result',target)
cv.imshow('original',original)
cv.imshow('specified',reference)
cv.waitKey(0)
cv.destroyAllWindows()