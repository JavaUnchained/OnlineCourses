import java.util.function.Predicate;

public class Matrix {
    public static void main(String[] args) throws Exception {
        int[][] matrix2x2 = {{2,3}, {1,1}};
        int[][] notMatrix2x2 = {{2,3,3}, {1,1}};
        System.out.println(deter2x2( matrix2x2));
//        System.out.println(deter2x2( notMatrix2x2));
    }

    //******************* Нахождение детерминантов матриц**************************//
    private static int deter2x2(int[][] matrix) throws Exception {
        Predicate<int[][]> is2x2 = a -> a.length == a[0].length && a.length == a[1].length;
        if(is2x2.test(matrix)){
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }else throw new Exception("Is not 2x2 matrix");
    }
}
