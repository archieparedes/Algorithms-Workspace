//Archie Paredes
//CSC355 Algorithms
//October 2, 2018
//
//Insertion sort, merge sort, and quick sort all in one.
package sortingAlgs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays; // unused unless uncommented
import java.util.Scanner;


public class insertionMergeQuickSort {
    private static boolean less(int v, int w) {
		Integer a = new Integer(v);
		Integer b = new Integer(w);
		return(a.compareTo(b) < 0);	
	}
	// - INSERTION SORT -
    public static void insertionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                if(less(arr[j], arr[j-1])){
                    exch(arr, j, j-1); // exchange arr[j] value with arr[j-1] value
                }
            }
        }
    }

    private static void exch(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    
    // - MERGE SORT -
	public static void sortRecursion(int[] arr, int lowIndex, int highIndex) {
		int mid;
		if(lowIndex == highIndex) return; // base case
		else {
			mid = (lowIndex + highIndex) / 2; // mid index
			sortRecursion(arr, lowIndex, mid); // left split
			sortRecursion(arr, mid + 1, highIndex); // right split
			merge(arr, lowIndex, mid, highIndex);
		}
	}
	
	public static void merge(int[] arr, int lowIndex, int mid, int highIndex) {
		int[] aux = new int[arr.length]; // auxillary array
		for(int k = lowIndex; k <= highIndex; k++) { // copies arr values to aux
			aux[k] = arr[k];
		}
		
		int i = lowIndex, j = mid + 1;
		for (int k = lowIndex; k <= highIndex; k++) {
			if      (i > mid)              arr[k] = aux[j++];
			else if (j > highIndex)        arr[k] = aux[i++];
			else if (less(aux[j], aux[i])) arr[k] = aux[j++];
			else                           arr[k] = aux[i++];
		}
	}
	
	// - QUICK SORT -
	public static int partition(int arr[], int i, int j) {
		int pivot = arr[i], // uses the first index as pivot
					h = i, 
					temp;
		
		for(int k = i + 1; k <= j; k++) {
			if(arr[k] < pivot) {
				h = h + 1;
				temp = arr[h];  
				arr[h] = arr[k];
				arr[k] = temp;
			}
		}
		
		temp = arr[i];
		arr[i] = arr[h];
		arr[h] = temp;
		return h;
	}
	
	public static void quicksort(int arr[], int i, int j) {
		int p;
		if(i < j) {
			p = partition(arr, i, j);
			quicksort(arr, i, p - 1);  //p-1
			quicksort(arr, p + 1, i);  //p+1
		}	
	}
	
	
	// - IS SORTED -
	private static boolean isSorted(int[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(int[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Which sorting algorithm would you like to sort?\n"
				+ "Enter 1 for Insertion Sort\n"
				+ "Enter 2 for Merge Sort\n"
				+ "Enter 3 for Quick Sort");
		@SuppressWarnings("resource")
		Scanner choice = new Scanner(System.in);
		int chosenAlg = choice.nextInt();
		while(chosenAlg > 3 || chosenAlg < 1){
            System.out.print("Invalid choice! Choose again:");
            chosenAlg = choice.nextInt();
        }
		
		// file retrieval
		System.out.println("Please enter file name: ");
		@SuppressWarnings("resource")
		Scanner fileInput = new Scanner(System.in);
		String fileName = fileInput.next();
		@SuppressWarnings("resource")
		Scanner read = new Scanner(new File(fileName));
		
		String getSize = fileName.replaceAll("\\D+", ""); // since name of file starts with the int size, this will get that integer
		int size = Integer.parseInt(getSize); // change string to int
		int[] arr = new int[size];
		int i = 0;
		
		while(read.hasNextInt()) {
			arr[i] = read.nextInt();
			i++;
		}
		
		if(!isSorted(arr)){ //checks if arr is sorted
			if(chosenAlg == 1) insertionSort(arr);
			else if(chosenAlg == 2) sortRecursion(arr, 0, size - 1);
			else quicksort(arr, 0, size - 1);
		}
		
		//System.out.println("Sorted list: " + Arrays.toString(arr)); // uncomment to see sorted array
		System.out.println("Success, the array has been sorted!");
	}

}
