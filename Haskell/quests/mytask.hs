--eachContain u t = eachContain' (ignoredCase u) (ignoredCase t) 0

alphabet = [('A','a'),('B','b'),('C','c'),('D','d'),('E','e'),('F','f')]
ignoredCase u = ignoredCase' u alphabet
ignoredCase' [] _  = []
ignoredCase' _  [] = error"not a Char"
ignoredCase' x@(h:t) al@(a:as) | (h == fst a) || (h == snd a) = (snd a) ++ (ignoredCase' t alphabet)
                               | otherwise                    = ignoredCase' x as