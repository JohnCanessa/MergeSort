import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 */
public class MergeSort {

    /**
     * Merge implementation (2 & 3).
     * 
     * @param <E>
     * @param arrayL
     * @param arrayR
     * @param start
     * @param mid
     * @param end
     * @param targetArray
     * @param comparator
     */
    private static <E> void merge(E[] arrayL, E[] arrayR, int start, int mid, int end, E[] targetArray, Comparator<E> comparator) {

        // **** ****
		int i = start;
		int j = mid;
        int k = start;
        
        // **** ****
		while (k < end) {

            // **** ****
			if (i == mid) {
				targetArray[k] = arrayR[j];
				j++;
			} else if (j == end) {
				targetArray[k] = arrayL[i];
						i++;
			} else if (comparator.compare(arrayL[i], arrayR[j]) > 0) {
				targetArray[k] = arrayR[j];
				j++;
			} else {
				targetArray[k] = arrayL[i];
				i++;
            }
            
            // **** ****
			k++;
		}
    }
    
    /**
     * Merge sort entry point (3).
     * 
     * @param <E>
     * @param array
     * @param start
     * @param mid
     * @param end
     * @param targetArray
     * @param comparator
     */
    private static <E> void merge(E[] array, int start, int mid, int end, E[] targetArray, Comparator<E> comparator) {
        merge(array, array, start, mid, end, targetArray, comparator);
    }

    /**
     * Entry call that implements the MergeSort algorithm (3).
     * 
     * @param <E>
     * @param sourceArray
     * @param start
     * @param end
     * @param tempArray
     * @param comparator
     */
	public static <E> void mergeSort(E[] sourceArray, int start, int end, E[] tempArray, Comparator<E> comparator) {

        // **** end base case ****
		if (start >= end - 1) {
			return;
        }
        
        // **** initialization ****
        int mid = (start + end) / 2;
        
        // **** recursive calls ****
		mergeSort(sourceArray, start, mid, tempArray, comparator);
		mergeSort(sourceArray, mid, end, tempArray, comparator);

        // **** merge both arrays ****
        merge(sourceArray, start, mid, end, tempArray, comparator);
        
        // **** copy temp to source array ****
		System.arraycopy(tempArray, start, sourceArray, start, end - start);
    }
    
    /**
     * Merge sort no copy entry point (2).
     * 
     * @param <E>
     * @param sourceArray
     * @param start
     * @param end
     * @param tempArray
     * @param comparator
     * @return
     */
    public static <E> E[] mergeSortNoCopy(E[] sourceArray, int start, int end, E[] tempArray, Comparator<E> comparator) {

        // **** ****
		if (start >= end - 1) {
	        return sourceArray;
        }
        
        // **** initialization ****
        int mid = (start + end) / 2;
        
        // **** recursive calls ****
		E[] sortedPart1 = mergeSortNoCopy(sourceArray, start, mid, tempArray, comparator);
		E[] sortedPart2 = mergeSortNoCopy(sourceArray, mid, end, tempArray, comparator);

        // **** ****
		if (sortedPart2 == sortedPart1) {
			if (sortedPart1 == sourceArray) {
					merge(sortedPart1, sortedPart2, start, mid, end, tempArray, comparator);
					return tempArray;
				} else {
					merge(sortedPart1, sortedPart2, start, mid, end, sourceArray, comparator);
					return sourceArray;
				}
		} else {            
			merge(sortedPart1, sortedPart2, start, mid, end, sortedPart2, comparator);
			return sortedPart2;
		}
    }
    
    /**
     * Merges two subarrays of arr[] (1).
     * First subarray is arr[l..m]
     * Second subarray is arr[m+1..r]
     * 
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    static void merge(Integer arr[], int l, int m, int r) {

        // **** determine the sizes of two subarrays to be merged ****
        int n1 = m - l + 1;
        int n2 = r - m;

        // ???? ????
        System.out.println("merge <<< n1: " + n1 + " n2: " + n2);
 
        // **** create temp arrays ****
        Integer L[] = new Integer[n1];
        Integer R[] = new Integer[n2];
 
        // **** copy data to the temp arrays ****
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // ???? ????
        System.out.println("merge <<< L: " + Arrays.toString(L));
        System.out.println("merge <<< R: " + Arrays.toString(R));
 
        // **** initial indexes of first and second subarrays ****
        int i = 0, j = 0;
 
        // **** initial index of merged subarry array ****
        int k = l;

        // **** copy L[] and R[] elements ****
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            }
            else {
                arr[k] = R[j++];
            }
            k++;
        }
 
        // **** copy remaining elements of L[] (if any) ****
        while (i < n1) {
            arr[k++] = L[i++];
        }
 
        // **** copy remaining elements of R[] (if any) ****
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
 
    /**
     * MergeSort entry point (1).
     * Recursive call.
     * 
     * @param arr
     * @param l
     * @param r
     */
    static void mergeSort(Integer arr[], int l, int r) {

        // **** ****
        if (l < r) {
            
            // **** find the middle point ****
            int m = (l + r) / 2;

            // ???? ????
            System.out.println("mergeSort <<< l: " + l + " m: " + m + " r: " + r);
 
            // **** sort first and second halves (recursive calls) ****
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
 
            // **** merge sorted halves ****
            merge(arr, l, m, r);
        }
    }

    /**
     * Test scaffolding.
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        // **** initial array to sort ****
        // Integer[] array = new Integer[]{10, 5, 2, 3, 78, 53, 3, 1, 1, 24, 1, 35,35, 2, 67, 4, 33, 30};
        // Integer array[] = new Integer[]{ 12, 11, 13, 5, 6, 7 };
        Integer[] array = new Integer[]{38, 27, 43, 3, 9, 82, 10};


        // **** display array to sort ****
        System.out.println("main <<<        array: " + Arrays.toString(array));

        // **** 1) merge sort the array ****
        mergeSort(array, 0, array.length - 1);

        // **** display sorted array ****
		System.out.println("main <<< sorted array: " + Arrays.toString(array) + "\n");


        // **** shuffle the array ****
        List<Integer> lst = Arrays.asList(array);
        Collections.shuffle(lst);
        array = lst.stream().toArray(Integer[]::new);

        // **** display array to sort ****
        System.out.println("main <<<        array: " + Arrays.toString(array));

        // **** auxiliary array ****
        Integer[] anotherArray = new Integer[array.length];

        // **** 2) merge sort no copy ****
        array = mergeSortNoCopy(array, 0, array.length, anotherArray, (a, b) -> a - b);
        
        // **** display sorted array ****
		System.out.println("main <<< sorted array: " + Arrays.toString(array) + "\n");
        

        // **** shuffle the array ****
        lst = Arrays.asList(array);
        Collections.shuffle(lst);
        array = lst.stream().toArray(Integer[]::new);

        // **** display array to sort ****
        System.out.println("main <<<        array: " + Arrays.toString(array));

        // **** 3) merge sort ****
        mergeSort(array, 0, array.length, anotherArray, (a, b) -> a - b);
        
        // **** display sorted array ****
		System.out.println("main <<< sorted array: " + Arrays.toString(array));
    }
}