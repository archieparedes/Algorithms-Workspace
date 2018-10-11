package closestPairPoints;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
	
public class closestPair extends mergeSortModified{
	public static float dist(int[] p1, int[] p2) {
		float distance = (float) Math.sqrt(Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2));
		return distance;
	}
	
	public static float rec_cl_pair(points[] points, int i, int j) {
		float delta;
		mergeSortModified sort = new mergeSortModified();
		return 0;
	}
	
	public static float closest_pair(points[] points){
		int n = points.length;
		mergeSortModified xVals = new mergeSortModified();
		xVals.sortX(points, 0, n-1); // sort by xCoord
		return rec_cl_pair(points, 0, n-1);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// file retrieval
		System.out.println("Please enter file name: ");
		@SuppressWarnings("resource")
		Scanner fileInput = new Scanner(System.in);
		String fileName = fileInput.next();
		@SuppressWarnings("resource")
		Scanner read = new Scanner(new File(fileName));
		
		String getSize = fileName.replaceAll("\\D+", ""); // since name of file starts with the int size, this will get that integer
		int size = Integer.parseInt(getSize); // change string to int
		int i = 0;
		points[] point = new points[size];
		int[] intArr = new int[2];
		
		// coordinate array
		while(read.hasNext()) {
			String line = read.nextLine();
			String[] strArr = line.split(" ");
			for(int j = 0; j < intArr.length; j++) {
				intArr[j] = Integer.parseInt(strArr[j]);
			}
			point[i] = new points(intArr[0], intArr[1]);
			i++;
		}
		closest_pair(point);
		for(int k = 0; k < size; k++) {
			System.out.println(point[k].toString());
		}
		
	}

}
