package net.bohush.exercises.chapter25;

public class Exercise20 {

	public static void mergeSort(int[] list) {
		mergeSort(list, 0, list.length - 1);
	}

	public static void mergeSort(int[] list, int first, int last) {
		if (last - first > 0) {
			int middle = (last + first) / 2;
			mergeSort(list, first, middle);
			mergeSort(list, middle + 1, last);
			merge(list, first, middle, middle + 1, last);
		}
	}

	/** Merge two sorted lists */
	public static void merge(int[] list, int first1, int last1, int first2, int last2) {
		int current1 = first1;
		int current2 = first2;
		int current3 = 0;

		int[] temp = new int[(last1 - first1 + 1) + (last2 - first2 + 1)];
		
		
		while (current1 <= last1 && current2 <= last2) {
			if (list[current1] < list[current2])
				temp[current3++] = list[current1++];
			else
				temp[current3++] = list[current2++];
		}

		while (current1 <= last1)
			temp[current3++] = list[current1++];

		while (current2 <= last2)
			temp[current3++] = list[current2++];
		
		
		current3 = 0;
		for (int i = first1; i <= last1; i++, current3++) {
			list[i] = temp[current3];
		}
		for (int i = first2; i <= last2; i++, current3++) {
			list[i] = temp[current3];
		}
	}

	/** A test method */
	public static void main(String[] args) {
		int[] list = { 2, 3, 2, 5, 6, 1, -2, 3, 14, 12 };
		mergeSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
}
