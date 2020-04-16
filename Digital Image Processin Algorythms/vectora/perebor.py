import cv2
import numpy as np

BLOCK_WIDTH = 16
BLOCK_HEIGHT = 16
WINDOW_WIDTH = 2
WINDOW_HEIGHT = 2

def sad(mat1, mat2):
    return np.sum(cv2.absdiff(mat1, mat2))

def match_blocks(ref, shifted, x, y):
    h,w = ref.shape
    left = max(x - BLOCK_WIDTH * WINDOW_WIDTH, 0)
    top = max(y - BLOCK_HEIGHT * WINDOW_HEIGHT, 0)
    width = BLOCK_WIDTH * 2 * WINDOW_WIDTH
    height = BLOCK_HEIGHT * 2 * WINDOW_HEIGHT
    right = min(left + width, w - BLOCK_WIDTH)
    bottom = min(top + height, h - BLOCK_HEIGHT)
    ref_block = ref[y:y+BLOCK_HEIGHT, x:x+BLOCK_WIDTH]
    best_dist = 1.e30
    best_x = best_y = -1
    for y2 in range(top, bottom):
        for x2 in range(left, right):
            dist = sad(ref_block, shifted[y2:y2+BLOCK_HEIGHT, x2:x2+BLOCK_WIDTH])
            if dist < best_dist:
                best_dist = dist
                best_x = x2
                best_y = y2
    if best_x == x and best_y == y:
        return [-100000, -100000, best_dist]
    return [best_x - x, best_y - y, best_dist]

def match(ref, shifted):
    h,w = ref.shape
    motion_vectors = []
    for y in range(0, h - BLOCK_HEIGHT, BLOCK_HEIGHT):
        print(y, '/', h - BLOCK_HEIGHT)
        for x in range(0, w - BLOCK_WIDTH, BLOCK_WIDTH):
            motion_vector = match_blocks(ref, shifted, x, y)
            if motion_vector[0] != -100000:
                motion_vectors.append([x, y, motion_vector[0], motion_vector[1]])
    return motion_vectors

def rsz(img, percents):
    if percents >= 1:
        return img
    return cv2.resize(img, (0, 0), fx=percents, fy=percents)

if __name__ == '__main__':
    percents = 1
    ref_path = 'src/11.png'
    shifted_path = 'src/2222.png'
    res_path = 'src/2222.png'
    ref = cv2.imread(ref_path, cv2.IMREAD_GRAYSCALE)
    shifted = cv2.imread(shifted_path, cv2.IMREAD_GRAYSCALE)
    ref = rsz(ref, percents)
    shifted = rsz(shifted, percents)
    motion_vectors = match(ref, shifted)
    ref = cv2.imread(ref_path)
    ref = rsz(ref, percents)
    for vec in motion_vectors:
        cv2.arrowedLine(ref, (vec[0] + BLOCK_WIDTH//2, vec[1] + BLOCK_HEIGHT//2),
        (vec[0] + vec[2] + BLOCK_WIDTH//2, vec[1] + vec[3] + BLOCK_HEIGHT//2), (255, 0,0))
    cv2.imwrite(res_path, ref)
    cv2.imshow('Image', ref)
    cv2.waitKey(0)
    cv2.destroyAllWindows()
