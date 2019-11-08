package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindMedianSortedArraysTest {

	@Test
	public void testFindMedianSortedArrays() {
		int[] A1 = {1,3};
		int[] B1 = {2};
		int[] A2 = {1,2};
		int[] B2 = {3,4};
		int[] A3 = {1,1,1};
		int[] B3 = {5,6,7};
		int[] A4 = {1,1};
		int[] B4 = {1, 2, 3};
		int[] A5 = {1,2};
		int[] B5 = {3};
		FindMedianSortedArrays f = new FindMedianSortedArrays();
		assertEquals((int)(2.0 - f.findMedianSortedArrays(A1, B1)), 0);
		assertEquals((int)(2.5 - f.findMedianSortedArrays(A2, B2)), 0);
		assertEquals((int)(3.0 - f.findMedianSortedArrays(A3, B3)), 0);
		assertEquals((int)(1.0 - f.findMedianSortedArrays(A4, B4)), 0);
		System.out.println(f.findMedianSortedArrays(A1, B1));
		System.out.println(f.findMedianSortedArrays(A2, B2));
		System.out.println(f.findMedianSortedArrays(A3, B3));
		System.out.println(f.findMedianSortedArrays(A4, B4));
		System.out.println(f.findMedianSortedArrays(A5, B5));
	}

}
