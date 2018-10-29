package closestPairPoints;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

/*
 * Archie Paredes
 * Created October 8, 2018 Updated October 16, 2018
 * Algorithms
 * Closest Pair Distance(good way) and Points (bad way)
 */
	
public class closestPair extends mergeSortModified{
	public static int globalSize; // size for auxillary arrays
	public static points a, b; // stores the points in these global vars
	
	/*
	 * Returns distance of the two points
	 * 
	 * @return distance of the two points
	 */
	public static float dist(points p1, points p2) { // calculates the distance of two points
		float distance = (float) Math.sqrt( Math.pow((p1.x() - p2.x()),2) + Math.pow((p1.y() - p2.y()),2) );
		return distance;
	}
	
	/*
	 * Returns true if v is less than w
	 *
	 * @return true if v is less than w
	 */
	public static boolean less(float v, float w) {
		Float a = new Float(v);
		Float b = new Float(w);
		return(a.compareTo(b) < 0);	// if a less than b, return true
	}

	/*
	 * Returns the distance of the closest pair and sets global variables a and b
	 *
	 * @return a float number of the distance of closes pairs
	 */
	public static float rec_cl_pair(points[] points, int lo, int hi) {
		float delta, deltaL, deltaR;
		mergeSortModified sortYCoord = new mergeSortModified();
		if(hi - lo < 3) {
			sortYCoord.sortY(points, lo, hi); // sorts with respect to y coordinates
			delta = dist(points[lo], points[lo + 1]);
			if(hi - lo == 1) {
				return delta;
			}
			if(dist(points[lo + 1], points[lo + 2]) < delta) {
				delta = dist(points[lo + 1], points[lo + 2]);
			}
			if(dist(points[lo], points[lo + 2]) < delta) {
				delta = dist(points[lo], points[lo+2]);
			}
			return delta;
		}

		int mid = (lo + hi) / 2;
		float midX = points[mid].x();
		deltaL = rec_cl_pair(points, lo, mid); // left split
		deltaR = rec_cl_pair(points, mid + 1, hi); // right split
		delta = Math.min(deltaL, deltaR);

		points[] vertStrip = new points[globalSize];
		int t = 0;

		// strip

		for(int q = lo; q < hi; q++) {
			if(points[q].x() > midX - delta && points[q].x() < midX + delta) {
				t++;
				vertStrip[t] = new points(points[q].x(), points[q].y());
			}
		}

		for(int u = 1; u <= t-1; u++) {
			for(int s = u + 1; s <= Math.min(t, u + 7); s++) {
				delta = Math.min(delta, dist(vertStrip[u], vertStrip[s]));

			}
		}

		// dumb way of finding the points: program runs much faster without this brute force way of finding the points.
		for(int o = 0; o < globalSize; o++) {
			for(int r = o+1; r < globalSize; r++) {
				if(dist(points[o],points[r]) == delta) {
					b = points[o];
					a = points[r];
					break; // this helps out the speed by just a little bit, but still the worst way
				}
			}
		}

		return delta;
	}

	public static float closest_pair(points[] points){
		int n = points.length;
		mergeSortModified sortXCoord = new mergeSortModified();
		sortXCoord.sortX(points, 0, n-1); // sort by xCoord
		points[] v = new points[points.length];
		return rec_cl_pair(points, 0, n-1);
	}

	public static void main(String[] args) throws FileNotFoundException {
		// file retrieval
		System.out.println("The size of array is pulled from the file name\n" +
				"Please enter file name: ");
		@SuppressWarnings("resource")
		Scanner fileInput = new Scanner(System.in);
		String fileName = fileInput.next();
		@SuppressWarnings("resource")
		Scanner read = new Scanner(new File(fileName));

		String getSize = fileName.replaceAll("\\D+", ""); // since name of file starts with the int size, this will get that integer
		int size = Integer.parseInt(getSize); // change string to int
		int i = 0;
		globalSize = size;
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

		System.out.println("Min. distance: "+ closest_pair(point) +" p(" + a.x() + ", " + a.y()+ ") <---> q(" + b.x() + ", " + b.y() + ")");
	}

}
