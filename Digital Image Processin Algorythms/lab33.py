import math
import os.path
import cv2 as cv
import numpy as np 
import matplotlib.pyplot as pyplot
from scipy.spatial import distance
from scipy.ndimage import correlate1d as crl

def filter (img, h, sigma):
    height, width = image.shape
    # out_img = np.empty_like(img)
    gaus_spat = []
    for i in range(-h, h+1):
        for j in range(-h, h+1):
            gaus_spat.append(np.exp(-0.5 * (i ** 2 + j ** 2) / (max(1,h/2)**2)))
    pad = np.pad(img, h, mode="edge")
    out_img = np.zeros(img.shape)
    weight  = np.zeros(img.shape)

    idx = 0
    for row in range(-h, h+1):
        for col in range(-h,h+1):
            val = np.exp(-0,5 * (pad[h + row:height + h +  row, h + col:width + h + col] - img) ** 2) * gaus_spat[idx]
            out_img += val * pad[h + row:height + h + row, h + col:width + h + col]
            weight += val
            idx +=1
    out_img /= weight
    img_lib
    return out_img

img_lib = cv.fastNlMeansDenoising


