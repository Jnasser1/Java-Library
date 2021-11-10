package jb3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Provide a directory name - string input, returns files in directories and subdirectories recursively.
// The integer layers is used to control indentation for easier view in console.

public class DirectoryList {
	public static void DirectorySearch(File[] arr, int layer) throws IOException {

		for (File f : arr) {
			// Spacing if you're in a subdirectory - i.e. layer >0
			for (int j = 0; j < layer; j++) {
				System.out.print("\t");
			}

			if (f.isFile()) {
				System.out.println(f.getName());
			} else if (f.isDirectory()) {
				System.out.println("[" + f.getName() + "]"); // Folder names have brackets [].

				// Recursively going through sub-directories.
				DirectorySearch(f.listFiles(), layer + 1);
			}
		}

	}

	public static void main(String args[]) throws IOException {

		// Change to directory you want to search.
		String dirpath = "C:\\Users\\jihad\\Desktop\\MCresults";

		// Creating File object.

		File dir = new File(dirpath);

		if (dir.exists() && dir.isDirectory()) {

			// array for files and subdirectories.
			File arr[] = dir.listFiles();

			System.out.println("Search under main directory " + "[" + dir + "].");
			DirectorySearch(arr, 0);
		}
	}
}
