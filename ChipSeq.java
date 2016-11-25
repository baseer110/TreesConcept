package memoryManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChipSeq {

	public void WriteInFile(String fileName, List<String> list) {
		File file = new File(fileName);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			PrintWriter pWriter = new PrintWriter(fileWriter);
			for (String itr : list) {
				pWriter.println(itr);
			}
			pWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		/** Q.5 Starts Here */
		System.out.println("Start Dividing a List into 4 sublists --> ");
		/** startTime Time */
		long startTime = System.currentTimeMillis();
		double totalCount = 10000000;

		In in;
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			/** Reading File */
			in = new In("RNA-seq-reads-1M.dat");
			/** If file is not empty */
			while (!in.isEmpty()) {
				String s = in.readLine();
				arrayList.add(s);
			}
		} catch (Exception e) {
			/** Printing Exception */
			StdOut.printf("Exception : ", e);
		}

		/** Creating four lists */
		List<String> listA = arrayList.subList(0, 249999);
		List<String> listB = arrayList.subList(250000, 499999);
		List<String> listC = arrayList.subList(500000, 749999);
		List<String> listD = arrayList.subList(750000, 1000000);

		/** End time */
		long endTime = System.currentTimeMillis();
		/** Average Time */
		double avg = (endTime - startTime) / totalCount;
		System.out.println("Average Time taken to read a file : " + avg);

		/** Creating instance of Class */
		ChipSeq oClass = new ChipSeq();

		/** Saving each sublist in separate file */

		/** Start Time for creating files for each list */
		long startTimeForFile = System.currentTimeMillis();

		/** Creating File A.dat */
		oClass.WriteInFile("A.dat", listA);

		/** Creating File B.dat */
		oClass.WriteInFile("B.dat", listB);

		/** Creating File C.dat */
		oClass.WriteInFile("C.dat", listC);

		/** Creating File D.dat */
		oClass.WriteInFile("D.dat", listD);

		long endTimeForFile = System.currentTimeMillis();
		avg = (endTimeForFile - startTimeForFile) / totalCount;
		System.out.println("Average Time taken for creating separate file : " + avg);

		/** Sorting Lists */
		System.out.println("Now sorting lists --> ");
		Collections.sort(listA);
		Collections.sort(listB);
		Collections.sort(listC);
		Collections.sort(listD);

		/** Creating Separate file in order to save sorted lists */
		long sTime = System.currentTimeMillis();
		/** Creating File AS.dat */
		oClass.WriteInFile("AS.dat", listA);

		/** Creating File BS.dat */
		oClass.WriteInFile("BS.dat", listB);

		/** Creating File CS.dat */
		oClass.WriteInFile("CS.dat", listC);

		/** Creating File DS.dat */
		oClass.WriteInFile("DS.dat", listD);

		long eTime = System.currentTimeMillis();
		avg = (eTime - sTime) / totalCount;
		System.out.println("Average time taken to save sorted lists in a file : " + avg);

		System.out.println("Start merging Sorted Files -->");
		long startTimeToMerge = System.currentTimeMillis();
		/** Merging Sorted Lists */
		String[] fnames = { "AS.dat", "BS.dat", "CS.dat", "DS.dat" };
		int N = fnames.length;
		In[] streams = new In[N];
		for (int i = 0; i < N; i++)
			streams[i] = new In(fnames[i]);
		Multiway multi = new Multiway();
		List<String> mergedList = multi.merge(streams);

		long endTimetoMerge = System.currentTimeMillis();
		avg = (endTimetoMerge - startTimeToMerge) / totalCount;
		System.out.println("Average time taken to merge the file : " + avg);

		/** Storing merged list into RNA-seq-reads-1M-sorted.dat file. */
		long startTimetoSave = System.currentTimeMillis();

		/** Saving merged list in a file */
		oClass.WriteInFile("RNA-seq-reads-1M-sorted.dat", mergedList);

		long endTimetoSave = System.currentTimeMillis();

		avg = (endTimetoSave - startTimetoSave) / totalCount;
		System.out.println("Averge time taken to save merged list in a file : " + avg);

		avg = (endTimetoSave - startTime) / totalCount;
		System.out.println("Total time taken in order to complete this task is as follows : " + avg);

		/** Q.5 Ends Here */

		/** Q.6 Starts Here */
		 System.out.println("");
		 System.out.println("---- Q.6 Starts Here ----");
		 BTree<String, String> bTree = new BTree<String, String>();
		 /** Start time */
		 long sT1 = System.currentTimeMillis();
		 // In ini;
		
		 /** Initializing an array */
		 ArrayList<String> arrlist = new ArrayList<String>();
		
		 /** Reading data from an array */
		 try {
		 in = new In("RNA-seq-reads-1M-sorted.dat");
		 while (!in.isEmpty()) {
		 String s = in.readLine();
		 arrlist.add(s);
		 }
		 } catch (Exception e) {
		 System.out.println(e);
		 }
		
		 /** End Time */
		 long eT1 = System.currentTimeMillis();
		 /** Average Time */
		 double avg1 = (eT1 - sT1) / totalCount;
		 System.out.println("Average time taken to read a file : " + avg1);
		
		 long sT2 = System.currentTimeMillis();
		 for (String i : arrlist) {
		 bTree.put(i, "0");
		 }
		
		 /** Saving a list in a file */
		 File file_BT = new File("B-tree.dat");
		 FileOutputStream fis;
		 try {
		 fis = new FileOutputStream(file_BT);
		 PrintStream out = new PrintStream(fis);
		
		 } catch (Exception e) {
		 System.out.println(e);
		 }
		
		 long eT2 = System.currentTimeMillis();
		 double avg2 = (eT2 - sT2) / totalCount;
		 System.out.println("Average time taken to save the list in the file : " + avg2);
		
		 // StdOut.println(bTree);
		 StdOut.println("size: " + bTree.size());
		 StdOut.println("height: " + bTree.height());

		/** Q.6 Ends Here */

	}

}
