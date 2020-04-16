import cv2 as cv
from matplotlib import pyplot as plt
import numpy as np
from copy import deepcopy as dc

def distributionFunc(hist):
    pixels = hist.transpose()[0]
    allPix = sum(hist)
    dist = [0] * 256
    for i in range(256):
        dist[i] = pixels[i] / allPix
        if i > 0:
            dist[i] += dist[i-1]
    return dist


# image - исходное image2- функцией open CV image3 - сами
image = cv.imread("source/nedo2.jpg",0)
image2 = cv.equalizeHist(image)
image3 = dc(image)

# считаем 1-ю и 2-ю диаграмму диаграму
hist = cv.calcHist([image],[0],None,[256],[0,256])
hist2 = cv.calcHist([image2],[0],None,[256],[0,256])

row = len(image)
cols = len(image[0])
allPix = row * cols
pixels = hist.transpose()[0]
dist = distributionFunc(hist)
# попиксельно меняем интенсивность
for i in range(row):
    for j in range(cols):
        image3[i][j] = dist[image3[i][j]] * 256

#   3-ю гистограмму
hist3 = cv.calcHist([image3],[0],None,[256],[0,256])

# рисуем распределение
plt.plot(distributionFunc(hist), color = 'r')
plt.plot(distributionFunc(hist2), color = 'g')
plt.plot(distributionFunc(hist3), color = 'b')
plt.show()

# рисуем результат эквализации
plt.plot(hist, color = 'r')
plt.plot(hist2, color = 'g')
plt.plot(hist3, color = 'b')
plt.show()

#сохраним результат2Ubc
#cv.imwrite ( '5equalFunc.png' , image2)
#cv.imwrite ( '5equalOwn.png' , image3)
# Выводим 3 изображения
image3 = np.hstack((image, image2, image3))
cv.imshow("Equalization", image3)
cv.waitKey(0)
cv.destroyAllWindows()
