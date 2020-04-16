public class SortingBT {
//    Функция сортирующая массив элементов A:
//    Sort(A,p,r)
//    if p < r
//    then           q := round_half_down((p+r)/2)
//                   Sort(A,p,q)
//                   Sort(A,q+1,r)
//                   Merge(A,p,q,r)
//
//Пример массива:
//  A = (5,2,4,6,1,3,2,6)
//Примера запуска:
// Sort(A,1,length[A])

    public void sort(int[] a, int p, int r){
        if(p < r){
            int q = Math.round((r -p)/2);
            sort(a, p, q);
            sort(a, q, r);
            merge(a, p, q, r);
        }
    }

    public void merge(int[] a, int p, int q, int r) {

    }

}

//    private static void merge(int[] array, int low, int middle, int high) {
//        int[] capacity = new int[high - low];
//
//        int currentIndex = 0;
//
//        for (int i = low, k = middle; i < middle || k < high; ) {
//            if (i == middle) {
//                while (k < high)
//                    capacity[currentIndex++] = array[k++];
//                break;
//            }
//
//            if (k == high) {
//                while (i < middle)
//                    capacity[currentIndex++] = array[i++];
//                break;
//            }
//
//            if (array[i] < array[k])
//                capacity[currentIndex++] = array[i++];
//            else
//                capacity[currentIndex++] = array[k++];
//        }
//
//        for (int i = low, c = 0; i < high && c < capacity.length; i++, c++)
//            array[i] = capacity[c];
//    }
