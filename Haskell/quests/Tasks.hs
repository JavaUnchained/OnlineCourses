module Tasks where
{--
-- Вспомогательные функции

-- (b -> a -> b) -> b -> t a -> b
foldl :: (b -> a -> b) -> b -> [a] -> b
foldl f x []     = x
foldl f x (y:ys) = foldl f (f x y) ys


-- (a -> b -> b) -> b -> t a -> b
foldr :: (a -> b -> b) -> b -> [a] -> b
foldr f x []     = x
foldr f x (y:ys) = f y (foldr f x ys)


-- (b -> Maybe (a, b)) -> b -> [a]
unfoldr :: (b -> Maybe (a, b)) -> b -> [a]
unfoldr f = maybe [] (\ (a, b) -> a : unfoldr f b) . f
--}

-- splitAt n xs -- splitAt 3 [1..5] = ([1,2,3],[4,5])
-- new_element : xs -- 5 : [1..3] = [5,1,2,3]
-- map my_function xs -- map (+1) [1..3] = [2,3.4]
-- drop n xs -- drop 2 [1..5] = [3,4,5]
-- take n xs -- take 2 [1..5] = [1,2]
-- lst !! n -- [0..10] !! 5 = 5 ( по индексу)
-- filter foo xs -- filter (\x -> x>3) [1..5] = [4,5]
-- elem el lst -- elem 58 [1..100] = True
-- ls1 ++ ls2 -- [1..2] ++ [3,4] = [1,2,3,4]
-- length lst -- length [0..10] = 11
-- reverse lst -- reverse [1..3] = [3,2,1]

-- является ли данное число простым?
isPrime :: Integer -> Bool
isPrime x = if x <= 1 then False else isPrime' x 2
    where
      isPrime' x d | x == d    = True
                   | otherwise = if x `mod` d == 0 then False else isPrime' x (d + 1)


-- наибольший общий делитель двух чисел
gcd' :: Integer -> Integer -> Integer
gcd' 0 0 = error "both zero"
gcd' x 0 = x
gcd' x y = if x < 0 || y < 0 then error "negative" else gcd' y (x `rem` y) 

--1

x = [(+2),(+2),(+2)]
y = [(+3), (+3), (+3)]

composing ms   []     []   = foldr (\x s -> x s) 0 ms
composing ms (x:xs) (y:ys) = composing (x:y:ms) xs ys 
composing' = composing []

--2 Даны два списка чисел. Верните список чисел, 
-- присутствующих в обоих исходных списках.

mergeLists []     _    = []
mergeLists (x:xs) lst2 = if (isContain x lst2) then [x] ++ (mergeLists xs lst2) else mergeLists xs lst2

isContain x     [] = False
isContain x (y:ys) | x == y     = True
                   | otherwise  = isContain x ys

--3 Найти сумму всех натуральных чисел от 1 до n
sumTo n | 1 > n     = foldr (+) 0 [n..1]
        | otherwise = foldr (+) 0 [1..n]

--4 Удалить каждый n-ый элемент из списка 
eachnElem lst n = eachnElem' lst n n

eachnElem' []     n e = []
eachnElem' (x:xs) n e | n == 1    = eachnElem' xs e e
                      | otherwise = [x] ++ (eachnElem' xs (n-1) e)


--5 Объединить повторяющиеся элементы в списке

antiRepiter [] = []
antiRepiter (x:xs) = if (isRepit x xs) then antiRepiter xs else [x] ++ (antiRepiter xs)

isRepit _  [] = False
isRepit x (h:t) | x == h    = True
                | otherwise = isRepit x t


-- [1,2,3,4,5,6,7] 3 -> 5,6,7,1,2,3,4
-- lentght [x] = 7, 7-3, 4 - > splitAt 4 -> snd x ++ fst x
-- 6.Функция, кот сдвигает все элементы по кругу
rotating lst match = snd x ++ fst x where x = splitAt (length lst  - match) lst


-- 7. Расчитайте расстояние между двумя точками на плоскости.
distance (x1,y1) (x2,y2) = sqrt $ (x1-x2)**2 + (y1-y2)**2
-- или 
distance' x1 x2 = sqrt $ (fst x1 - fst x2)**2 + (snd x1 - snd x2)**2

-- 8. Разбить список на 2. Размер первого списка задан параметром
listSpliter lst n = splitAt n lst

listSpliter' lst n = (take n lst, drop n lst)


-- 9. Постоить список всех простых чисел в диапазоне
primList from to | from >  to = error"from is mutch"
                 | from == to = if isPrime from then [from] else []
                 | otherwise  = if isPrime from then [from] ++ primList (from+1) to else primList (from+1) to

-- 10. Список от до - только из составных чисел
unprimList from to | from > to  = error"from more then to"
                   | from == to = if isPrime from then [] else [from]
                   | otherwise  = if isPrime from then unprimList (from+1) to else [from] ++ unprimList (from+1) to

-- 11. Список из всего натуральных чисел
natural []     = []
natural (x:xs) | x <= 0      = natural xs
               | isNatural x = [x] ++ natural xs 
               | otherwise   = natural xs 
                  where isNatural x = ceiling x == floor x

-- 12. Создать структуру из "true" "false" "unknown" и реализовать "и", "или" и "не равно"
data NewData = MyTrue | MyFalse | MyUnknown

(|||) l r = case (l,r) of
    (MyTrue, _) -> MyTrue
    (_, MyTrue) -> MyTrue
    (MyFalse, MyFalse) -> MyFalse
    otherwise -> MyUnknown

(&) l r = case (l,r) of
    (MyTrue, MyTrue) -> MyTrue
    (MyFalse, _) -> MyFalse
    (_, MyFalse) -> MyFalse
    otherwise -> MyUnknown

(//=) l r = case (l,r) of
    (MyTrue, MyTrue) -> MyFalse
    (MyFalse, MyFalse) -> MyFalse
    (MyTrue, MyFalse) -> MyTrue
    (MyFalse, MyTrue) -> MyTrue
    otherwise -> MyUnknown

-- 26. (?) realize show 4 value without deriving
data Term = IntConstant{ intValue :: Int }           -- числовая константа
            | Variable{ varName :: String }          -- переменная
            | BinaryTerm{ lhv :: Term, rhv :: Term } deriving (Show)

data DList a = DNil 
             | DCons { 
                left :: (DList a), 
                current :: a, 
                right :: (DList a) 
             }

instance (Show a) => Show (DList a) where
    show it = "[" ++ showBody it ++ "]"
              where showBody DNil = ""
                    showBody (DCons _ h DNil) = show h
                    showBody (DCons _ h t) = show h ++ ", " ++ showBody t

--24. проверка скобок
isCorrect [] [] = "Success"
isCorrect s  [] = show . fst . last $ s
isCorrect s (e@(i, c) : l)
  | c `elem` "([{" = isCorrect (e : s) l
  | c `elem` ")]}" = case s of
    []            -> show i
    ((_, t) : s') -> if Just t == lookup c [(')', '('), (']', '['), ('}', '{')]
                     then isCorrect s' l
                     else show i
  | otherwise      = isCorrect s l

main = isCorrect [] . zip [1 ..] <$> getLine >>= putStrLn

{-- 13. Вернуть длинну наибольшей последовательности
mostLonger lst = length $ mostLonger' lst 0

mostLonger' (x:xs) n| (x + 1) == head xs = mostLonger' xs (n+1)
                    | (x + 1) > head xs || (x + 1) < head xs = (n : len) (mostLonger' xs 0)
                    | otherwise = moreLength (head len) len
                      where len = [n]

moreLength _ [] = x
moreLength n (x:xs) | x >= head xs = moreLength x xs
                    | otherwise    = moreLength (head xs) (tail xs)
--}






{--
--  Поместить все последовательные повторяющиеся элементы списка в подсписки
-- [1,2,3,1,2,3,7,1,10,11]
-- if h+1 == h t -> [[h, ht]] f t else f t
-- x == x+1 - n+1 else if \= then split at 
serialNumber [] = []
serialNumber (x:xs) n@(h:t) | (head xs == [])  = []
                            | (x+1 == head xs) = ([[x]] ++) serialNumber xs 
                            | otherwise        = serialNumber xs n

                            --}



--
f:: Int
f = read ("1234" !! 0)
