import cv2 as cv
from matplotlib import pyplot as plt
import numpy as np

# функция растяжения
def liner_stretching(value):
    if value < a: 
            value = a
    elif value > b: 
            value = b
    #формула растяжения
    return (((value - a) * (d - c) / (b - a)) + c)


def calculation_area(some_hist):
    some_hist = some_hist.transpose()[0]
    nz = np.nonzero(some_hist)[0]
    all_ar = sum(some_hist)
    p = all_ar * 0.01 * percent
    right = nz[-1]
    left = nz[0]
    my_arr = sum(some_hist[left:right+1])
    while all_ar - my_arr < p and left + 1 < right :
        if some_hist[left] < some_hist[right] :
            left += 1
        else :
            right -= 1
        my_arr = sum(some_hist[left:right+1])
    return left, right


#загружаем изображение
image = cv.imread("/home/pc/Документы/vscocde/Python/Lab1/source/5.JPG")
image2 = image
image2 = cv.cvtColor(image2, cv.COLOR_BGR2HSV)
#рисуем начальную диаграмму 
hist = cv.calcHist([image],[0],None,[256],[0,256])
plt.plot(hist, color = 'r')
# задаем параметры для растяжения
percent = 5
a,b,c,d = 0,100,0,255

a, b = calculation_area(hist)

rows = len(image)
cols = len(image[0])
print("rows=",rows,", cols=", cols)

# применяем растяжение по пиксельно
for i in range(rows):
    for j in range(cols):
        h,s,v = image2[i][j]
        v = liner_stretching(v)
        image2[i][j] = [h,s,v]

# выводим гистограмму и второе изображение
image2 = cv.cvtColor(image2, cv.COLOR_HSV2BGR)
hist2 = cv.calcHist([image2],[0],None,[256],[0,256])
plt.plot(hist2, color = 'b')
plt.show()
cv.imwrite ( 'streatched.png' , image2)
image = np.hstack((image, image2))
cv.imshow("Stretchig image", image)
cv.waitKey(0)
cv.destroyAllWindows()



