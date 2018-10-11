package closestPairPoints;
/*
 * Archie Paredes
 * Created March 14, 2018 Updated October 9, 2018
 * Merge Sort 
 * Modified to sort with respect to x-coordinate or y-coordinate
 */


public class mergeSortModified{
	static class points {
		private int xCoord = 0;
		private int yCoord = 0;

		public points(int xCoord, int yCoord) {
			this.xCoord = xCoord;
			this.yCoord = yCoord;
		}

		public int x() {
			return xCoord;
		}
		
		public int y() {
			return yCoord;
		}
		public String toString() {
			return(xCoord + " " + yCoord);
		}

	}

	public void sortX(points[] point, int lowIndex, int highIndex) { // sort with respect to x-coordinate
		int mid;
		if(lowIndex == highIndex) return; // base case
		else {
			mid = (lowIndex + highIndex) / 2; // mid index
			sortX(point, lowIndex, mid); // left split
			sortX(point, mid + 1, highIndex); // right split
			mergeX(point, lowIndex, mid, highIndex);
		}
		
	}
	
	public void sortY(points[] arr, int lowIndex, int highIndex) { // sort with respect to y-coordinate
		int mid;
		if(lowIndex == highIndex) return; // base case
		else {
			mid = (lowIndex + highIndex) / 2; // mid index
			sortY(arr, lowIndex, mid); // left split
			sortY(arr, mid + 1, highIndex); // right split
			mergeY(arr, lowIndex, mid, highIndex);
		}
	}
	
	public void mergeX(points[] point, int lowIndex, int mid, int highIndex) {
		points[] aux = new points[point.length]; // auxillary array
		for(int k = lowIndex; k <= highIndex; k++) { // copies point values to aux
			aux[k] = new points(point[k].x(), point[k].y()); 
		}
		
		int i = lowIndex, j = mid + 1;
		for (int k = lowIndex; k <= highIndex; k++) {
			if      (i > mid)              point[k] = aux[j++];
			else if (j > highIndex)        point[k] = aux[i++];
			else if (less(aux[j].x(), aux[i].x())) point[k] = aux[j++];
			else                           point[k] = aux[i++];
		}
	}
	
	public void mergeY(points[] point, int lowIndex, int mid, int highIndex) {
		points[] aux = new points[point.length]; // auxillary array
		for(int k = lowIndex; k <= highIndex; k++) { // copies point values to aux
			aux[k] = new points(point[k].x(), point[k].y()); 
		}
		
		int i = lowIndex, j = mid + 1;
		for (int k = lowIndex; k <= highIndex; k++) {
			if      (i > mid)              point[k] = aux[j++];
			else if (j > highIndex)        point[k] = aux[i++];
			else if (less(aux[j].y(), aux[i].y())) point[k] = aux[j++];
			else                           point[k] = aux[i++];
		}
	}
	
	public static boolean less(int v, int w) {
		Integer a = new Integer(v);
		Integer b = new Integer(w);
		return(a.compareTo(b) < 0);	// if a less than b
	}
	
}
