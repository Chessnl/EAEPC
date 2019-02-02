import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	
	
	Main (int casenumber) {
		Scanner sc;
		try {
			if (casenumber == 0) {
				sc = new Scanner(new File("input/IPO.txt"));
			} else if (casenumber == 1) {
				sc = new Scanner(new File("input/Laplace.txt"));
			} else if (casenumber == 2) {
				sc = new Scanner(new File("input/Paviljoen.txt"));
			} else if (casenumber == 3) {
				sc = new Scanner(new File("input/Traverse.txt"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Main(1);

	}

}
