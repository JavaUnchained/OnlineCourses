import cv2 as cv
import numpy as np 
from copy import deepcopy
from scipy.signal import convolve2d

# загружаем и редактируем размер изображения
image = cv.imread("./source/1.PNG", 0)
height, width = image.shape

# нужно немного увеличить изображение, чтобы пройтись ядром свертки по всем крайним пикселям
image = cv.resize(image, (int(image.shape[1]*(300 / height)), int(image.shape[0]*(300 / height))))

# маски для свертки
s_x, s_y = [[-1, -2, -1], [0, 0, 0], [1, 2, 1]], [[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]]
sobel = [s_x, s_y]

r_x, r_y = [[-1, 0], [0, 1]], [[0, -1], [1, 0]]
roberts = [r_x, r_y]

p_x, p_y = [[-1, -1, -1], [0, 0, 0], [1, 1, 1]], [[-1, 0, 1], [-1, 0, 1], [-1, 0, 1]]
previtt = [p_x, p_y]

def find_edges(img, masks):
    image_x, image_y = convolve2d(img, np.flip(masks[0]), mode='same'),convolve2d(img, np.flip(masks[1]), mode='same') #свертки
    img_res = (image_x**2 + image_y**2)**0.5 # находим гипотинузу
    img_res = np.clip(img_res, 0, 255) # ограничиваем интервал  значений ( [ < 0 примет 0, [ > 255 будет 255)
    img_res = np.uint8(img_res) # целое число без знаков 0 - 255
    return img_res

images = np.vstack((np.hstack((image, find_edges(image, sobel))),
              np.hstack((find_edges(image, roberts), find_edges(image, previtt)))))
#cv.imwrite ( 'lines3.png' , images)
cv.imshow('Iab2', images)
cv.waitKey(0)
cv.destroyAllWindows()