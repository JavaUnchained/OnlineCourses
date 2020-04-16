import cv2
import numpy as np
class LDSPGenerator():
    
    PATTERN = set([( 2, 0 ),( 1, 1 ),( 0, 2 ),( -1, 1 ),( -2, 0 ),( -1, -1 ),( 0, -2 ),( 1, -1 )])

    def __init__(self):
        self.last = set()
        self.visited = set()
        self.origin = None

    def setOrigin(self, point):
        if point == self.origin:
            raise ValueError("Can't set the same point as new origin")
        if self.last and point not in self.last:
            raise ValueError("Next origin doesn't belong to last pattern")
        self.origin = point

    def generate(self):
        current = [ (self.origin[0] + x, self.origin[1] + y) for x, y in self.PATTERN ]
        yield self.origin
        for p in current:
            if p not in self.visited:
                yield p

        last = set(current) | set([self.origin])
        self.visited = self.visited | last
        self.last = last

    def image_area_filter(picture, x):
        if x[0] < 0 or x[1] < 0:
            return False
        if len(picture) <= x[1] or len(picture[0]) <= x[0]:
            return False
        return True

class DiamondSearch():
    def __init__(self, ref, shifted, n, p):
        self.ref = ref
        self.shifted = shifted
        self.n = n
        self.p = p
        self.x = 0
        self.y = 0
    def image_area_filter2(self, curr, ref):
        return image_area_filter(self.shifted, curr) and image_area_filter(self.ref, ref)

    def search_area_filter(self, x):
        if not image_area_filter(self.shifted, x):
            return False
        if abs(self.first[0] - x[0]) > self.p:
            return False
        if abs(self.first[1] - x[1]) > self.p:
            return False
        return True

    def blockSAD(self, ref_center):
        _sum = 0
        cnt = 0
        for (x, y) in [ (x, y) for x in range(self.n) for y in range(self.n) ]:
            curr = [self.x + x, self.y + y]
            ref = [ref_center[0] + x - self.n//2, ref_center[1] + y - self.n//2]
            if not self.image_area_filter2(curr, ref):
                continue
            _sum += abs(self.shifted[curr[1]][curr[0]] - self.ref[ref[1]][ref[0]])
            cnt += 1
        _sum /= cnt
        return _sum

    def motionVector(self,isInterpolated=False):
        self.first = ( self.x + self.n//2, self.y + self.n//2)
        ldsp = LDSPGenerator()
        origin = self.first
        while True:
            ldsp.setOrigin(origin)
            filtered_pattern = filter(lambda x: self.search_area_filter(x), ldsp.generate())
            mblock_cmp = lambda x: self.blockSAD(x)
            try:
                next_origin = min(filtered_pattern, key=mblock_cmp)
                if next_origin[0] == origin[0] and next_origin[1] == origin[1]:
                    sdsp = [(origin[0] + x, origin[1] + y) for x, y in [[0, 0], [1,0], [0, 1],[-1, 0], [0, -1]]]
                    filtered_pattern = filter(lambda x: self.search_area_filter(x), sdsp)
                    last = min(filtered_pattern, key=mblock_cmp)
                    vec = [ -last[0]+self.first[0], last[1]-self.first[1] ]
                    return [ vec[1], vec[0] ]
                origin = next_origin
            except ValueError as e:
                print(e)
                print("filtered_pattern", filtered_pattern)
                print("mblock_cmp",mblock_cmp)
                sdsp = [(origin[0] + x, origin[1] + y) for x, y in [[0, 0], [1,0], [0, 1], [-1, 0], [0, -1]]]
                filtered_pattern = filter(lambda x: self.search_area_filter(x), sdsp)
                last = min(filtered_pattern, key=mblock_cmp)
                vec = [ -last[0]+self.first[0], last[1]-self.first[1] ]
                return [ vec[1], vec[0] ]
 
    def __position___(self,i,j):
        y=self.y+i
        x=self.x+j
        return y,x
    def __makroBlock__(self,i,j,isCurrent=True):
        y, x=self.__position___(i,j)
        if x>=len(self.shifted[0]) or x<0:
            raise IndexError('x out of list')
        if y>=len(self.shifted) or y<0:
            raise IndexError('y out of list')
        if isCurrent:
            return list(reversed(self.shifted))[y][x]
        else:
            return list(reversed(self.ref))[y][x]
    
    def motionEstimation(self):
        num_of_macroblocs_in_y=len(self.shifted)//self.n
        num_of_macroblocs_in_x=len(self.shifted[0])//self.n
        result=[]
        for y in range(num_of_macroblocs_in_y):
            row=[]
            for x in range(num_of_macroblocs_in_x):
                self.x=x*self.n
                self.y=y*self.n
                row.append(self.motionVector())
            result.append(row)
        return result