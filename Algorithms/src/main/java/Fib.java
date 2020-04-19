import javafx.collections.MapChangeListener;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.nashorn.internal.objects.NativeDebug.map;

public class Fib {
    public static void fib1() {
        Scanner in = new Scanner(System.in);
        //System.out.println("Введите количество итераций алгоритма: ");
        int Much = in.nextInt(); // число итераций
        if(Much == 0) {
            System.out.println(0);
        }else if(Much == 1){
            //System.out.print(0 + "\n" + 1 + "\n");
            System.out.print(1);
        }else if(Much >= 2){
            int goodMath = Much+1;
            long[] FibArr = new long[goodMath];
            FibArr[0] = 0;
            FibArr[1] = 1;
            for(int i = 2; i < FibArr.length; i++){
                FibArr[i] = FibArr[i-1] + FibArr[i-2];
                //System.out.println(FibArr[i]);
            }
            System.out.println(FibArr[goodMath-1]);
        }
    }

//    По данным nn отрезкам необходимо найти множество точек минимального размера, для которого каждый
//    из отрезков содержит хотя бы одну из точек.
//    В первой строке дано число 1 \le n \le 1001≤n≤100 отрезков. Каждая из последующих nn строк содержит
//    по два числа 0 \le l \le r \le 10^90≤l≤r≤1
//    , задающих начало и конец отрезка. Выведите оптимальное число mm точек и сами mm точек.
//    Если таких множеств точек несколько, выведите любое из них.

//    public static void minimalPoints(){
//        Scanner in = new Scanner(System.in);
//
//        TreeSet<Map<Long,Long>> set = new TreeSet<>(Comparator.comparing());
//        for (int i = 1; i <= in.nextInt() ; i++) {
//            set.add(Collections.singletonMap(in.nextLong(), in.nextLong()));
//        }
//
//        set.first().
//
//    }
}
