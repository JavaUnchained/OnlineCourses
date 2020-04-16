import cv2 as cv
import numpy as np
from copy import deepcopy

img1, img2 = cv.imread("./source/1.PNG"), cv.imread("./source/2.PNG")
h1, w1 = image.shape
h2, w2 = image.shape

block_size = 8
sad_neightborhood = 16

for i in range (0, w1-block_size, block_size):
    for j in range (0, h1-block_size, block_size):
        img1[i][j] = 
    