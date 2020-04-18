import java.util.Arrays;
import java.util.function.Predicate;

public class Determinant {
    public static void main(String[] args) throws Exception {
        int[][] matrix2x2 = {{2,3}, {1,1}};
        int[][] matrix3x3 = {{1, 2, 1}, {3, -1, -1}, {-2, 2, 3}};
        int[][] notMatrix = {{2,3,3}, {1,1}};
        System.out.println(deter2x2( matrix2x2));
        System.out.println(deter3x3(matrix3x3));

//        System.out.println(is2x2Matrix(matrix2x2));
//        System.out.println(is2x2Matrix(matrix3x3));
//        System.out.println(is2x2Matrix(notMatrix));
//        System.out.println(deter2x2( notMatrix2x2));
    }

    //******************* Нахождение детерминантов матриц**************************//
    public static int deter2x2(int[][] matrix) throws Exception {
        if(isMatrix(matrix)){
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }else throw new Exception("Is not 2x2 matrix");
    }

    public static int deter3x3(int[][] matrix) throws Exception {
        if(isMatrix(matrix)){
            return  matrix[0][0] * matrix[1][1] * matrix[2][2] +
                    matrix[0][1] * matrix[1][2] * matrix[2][0] +
                    matrix[1][0] * matrix[2][1] * matrix[0][2] -
                    matrix[2][0] * matrix[1][1] * matrix[0][2] -
                    matrix[1][0] * matrix[0][1] * matrix[2][2] -
                    matrix[0][0] * matrix[1][2] * matrix[2][1] ;
        } else throw new Exception("Is not 3x3 matrix");
    }

    private static int deteNxN(int [][] matrix) throws Exception{
        if(isMatrix(matrix)){
            int d = 0;
            for (int i = 0; i < matrix.length; i++) {
                int[][] subMatrix = new int[matrix.length-1][matrix.length-1];
                for (int j = i + 1; j < matrix.length; j++) {
//                    subMatrix[][]
                }
//                d = matrix[0][i] * deteNxN();
            }
            return 1;
        } else throw new Exception("Is not a matrix");
    }

    public static boolean isMatrix(int [][] matrix){
        Predicate<int[][]> isCorrect = a -> {
            for (int i = 0; i < a.length; i++){
                if(a.length != a[i].length)
                    return false;
            } return true;
        };
        return isCorrect.test(matrix);
    }

    public static boolean is2x2Matrix(int [][] matrix){
        Predicate<int[][]> isCorrect = a -> a.length == 2 && a[0].length == 2 && a[1].length == 2;
        return isCorrect.test(matrix);
    }

}
