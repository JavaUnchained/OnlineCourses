import diamondSearch
import cv2
import numpy as np

def loadImage(filepath):
    return cv2.imread(filepath, cv2.IMREAD_GRAYSCALE).tolist()

ref_path = 'src/11.png'
shifted_path = 'src/222.png'
ref = loadImage(ref_path)
shifted = loadImage(shifted_path)
p = 4
n = 8
ds = diamondSearch.DiamondSearch(ref, shifted, n, p)
print(np.asarray(ref).shape)
motion_vectors = np.asarray(ds.motionEstimation())
ref_pict = np.asarray(ref).astype(np.uint8)
ref_pict = cv2.imread(ref_path)
col_ind = 0
for column in motion_vectors:
    cell_ind = 0
    for vec in column:
        if vec[0] != 0 or vec[1] != 0:
            cv2.arrowedLine(ref_pict, (cell_ind, col_ind),
            (cell_ind + vec[1], col_ind + vec[0]), (255, 0, 0))
        cell_ind += n
    col_ind += n
cv2.imshow('Image', ref_pict)
cv2.waitKey(0)
cv2.destroyAllWindows()