module Test where
-- 9,13,2,5,3,4,6,24
-- > 8 -- 18

--1. Поместить все последовательные повторяющиеся элементы списка в подсписки
pack lst = unfoldr fun lst

fun [] = Nothing
fun (h : t) = Just (makeSublist h t [h], remove h t [])

makeSublist _ [] lst = lst
makeSublist x (h : t) lst | h == x = makeSublist x t (x : lst)
                          | h /= x = lst

remove h (h' : t') lst | h == h' =  remove h t' lst
                       | h /= h' =  remove h t' (h' : lst)
remove _ [] lst = lst

-- 7.Создать список вида Plus x, Minus x, Equal входной последовательности
data MyData = Plus Integer | Minus Integer | Equal

myDataFun lst = myDataFun2 lst []
myDataFun (h:[])  = l
myDataFun2 (h:t) lst = myDataFun3 h (take 1 t) : lst
myDataFun3 x y
    | a > 0 = Plus a
    | a < 0 = Minus a
    | a  == 0 = Equal
    where a = x - y


-- 8.Создать список вида Multiple a x, Single a входной последовательности
data MyData2 = Multiple Char Integer | Single Char deriving(Show)

myData2Fun (h:t) = fff h t 1

fff x (h:t) acc
    | h == x = fff x t (acc + 1)
    | h /= x && acc > 1 = Multiple x acc : fff h t 1
    | h /= x && acc == 1 = Single x : fff h t 1

fff x [] acc
    | acc>0 = Multiple x acc : []
    | acc == 0 = Single x : []



--10. Найти на бесконечном списке два числа дающих в сумме заданное число
myFuncNumFuck y (h:t) = myFuncNumFuck' y t [h]

myFuncNumFuck' y (h : t) r =  if (hasSum y h r) then takeSum y h r
                           else myFuncNumFuck' y t (h : r)

myFuncNumFuck' _ [] _ = error ":("

hasSum y x [] = False
hasSum y x (h:t) | x + h == y = True
                 | otherwise = hasSum y x t

takeSum y x (h:t) | x + h == y = (x, h)
                  | otherwise = takeSum y x t


-- 11.Дан список и два числа; m, n. Необходимо заменить все элементы списка, кратные индексам m на n.
changeEls lst m n = changeEls' (reverse lst) m n 0 []                            

changeEls' lst m n i  res | i == length lst =  res
                          | mod (i+1) m == 0  = changeEls' lst m n (i + 1)  res ++  [n]
                          | otherwise     = changeEls' lst m n (i + 1)  res ++  [lst!!i]


-- 12.Дано число. Заменить в нем все нули на 5.
convertFive a = convertFive' (show a) (length (show a)) 0 []

convertFive' lst len i newlst = if i < len then
    if (lst !! i) /= '0'
    then convertFive' lst len i ('5' : newlst)
    else convertFive' lst len i ((lst !! i) : newlst)
    else read newlst

--14. Дан список чисел. Вывести самую частовстречающуюся цифру (если таких несколько - сумму).
-- -- 1 вариант
mostFreq z = map fst $ filter (\ (a,n) -> n == max) pc
         where pc = mostFreq' z
               max = maximum $ map snd pc

mostFreq' []     = []
mostFreq' (x:xs) = (x,n) : (mostFreq' xxs)
            where n   = (length xs) - (length xxs) + 1
                  xxs = filter (/= x) xs

-- -- 2 вариант
-- import Data.List
-- import Data.Ord
-- mostFreq :: Integral a => [a] -> a
-- mostFreq = head . maximumBy (comparing length) . group . sort


-- 15.Дано число. Все цифры, кот не 0 заменить на m
convertN a n = convertN' (show a) n (length (show a)) 0 []

convertN' ls n len i newls = if i < len then
    if (ls !! i) /= '0'
    then convertN' ls n len i (n : newls)
    else convertN' ls n len i ((ls !! i) : newls)
    else read newls


-- 16.Дан список. Отсортировать по убыванию, начиная с n-ого элемента
mySort :: [Int] -> Int -> [Int]
mySort ls n = take n ls ++ (sortOn Down) (drop n ls)


-- 17.Являеться ли число сильным
task_ x y x1 y1 = let a = x*(y1-y)-y*(x1-x)
                      x = y1-y
                      y = x1-x in
                  show [y] ++ "y" ++ " + " ++ [x] ++ "x= " ++ [a]

isStrong n = getSum (digs n) == n

getFact ls = foldl (factorial) 0 lst

getSum ls = foldl (+) 0 getFact ls

factorial 0 = 1
factorial n = n*factorial (n-1)

digs 0 = []
digs x = digs (div x 10) ++ [mod x 10]


--18. Построить уравнение прямой, проходящей через 2 точки


--19. Удалить все элементы из первого (мб бесконечным) списка, содержащиеся во втором
removeAll (h:t) lst = removeAll' (h:t) lst []

removeAll' [] _ res = res
removeAll' (h:t) lst res | contains h lst = removeAll' t lst res
                         | otherwise = removeAll' t lst (h:res)

contains x [] = False
contains x (h:t) | h == x = True
                 | otherwise contains x t

--20. Два списка, написать функцию разницы
data MyData3 = Same | Different Int Int deriving(Show)

diff (h:t)(h1:t1) = if (h == h1) then Same : diff t t1 else Different h h1 : diff t t1
diff [] = []


--21. Вернуть длину наибольшей последовательности
longestSpan lst x = longestSpan' lst x 0

longestSpan' [] _ s = s
longestSpan' (h:t) x s | x == h = if (l>s) then longestSpan' (drop l t) x l else longestSpan' (drop l t) x s where l = countX t 1 x

countX [] i x = i
countX (h:t) i x | x == h = countX (i+1) x
                 | otherwise i



--22. Являеться ли число палиндромом?
isPalindrome x = isPalidrome' show x

isPalindrome' x | length x == 0 = True
                | length x == 1 = True
                | mod (length x) 2 ==0 = isEqualTwo (take (length x/2) x) (drop (length x/2) x)
                | otherwise = isEqualTwo (take (length x/2) x) (drop (length x/2 + 1) x)

isEqualTwo [] [] = True
isEqualTwo (h:t) (h1:t1) | h == h1 = isEqualTwo t t1
                         | otherwise = False

-- 23.функция f гарантированно возрастает по обоим аргументам, написать f::int->int->int и x и возвращает произвольные
-- натуральные числа a и b, такие что f a b = x
invertF f x | x>=0 = invertF' f x (0,0)
            | otherwise = error "err"

invertF' f x (y1,y2) | f (fst res) (snd res) == x = res
                     | f (y1+1) 0 > x = error "err1"
                     | otherwise invertF' f x (y1+1, 0)
                     where res invertF' f x (y1 0)

invertF'' f x (y1 y2) | f y1 y2 == x = (y1 y2)
                      | f y1 y2 < x = invertF'' f x (y1 y2+1)
                      | otherwise = (y1 y2)




