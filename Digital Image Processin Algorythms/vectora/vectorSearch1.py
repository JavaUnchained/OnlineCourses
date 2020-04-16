import diamondSearch
import cv2
import numpy as np

def loadImage(path):
    return cv2.imread(path, cv2.IMREAD_GRAYSCALE).tolist()

refp = 'img1.png'
shift = 'img2.png'

ref = loadImage(refp)
shifted = loadImage(shift)
p,n = 4,8

ds = diamondSearch.DiamondSearch(ref, shifted, n, p)

print(np.asarray(ref).shape)
motion_vectors = np.asarray(ds.motionEstimation())

ref_pict = np.asarray(ref).astype(np.uint8)

ref_pict = cv2.imread(refp)

col_ind = 0
for column in motion_vectors:
    cell_ind = 0
    for vec in column:
        if vec[0] != 0 or vec[1] != 0:
            cv2.arrowedLine(ref_pict, (cell_indm col_ind),
            (cell_ind + vec[1] * 3, col_ind + vec[0] * 3), (255,0,0))
        cell_ind += n
    col_ind += n
cv2.imwrite('diamond_result.png', ref_pict)
cv2.waitKey(0)
cv2.destroyAllWindows()