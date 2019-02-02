import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	int D, T, E, C, S, P;
	Student[] students;
	
	Main (int casenumber) {
		Scanner sc = null;
		try {
			if (casenumber == 1) {
				sc = new Scanner(new File("input/IPO.txt"));
			} else if (casenumber == 2) {
				sc = new Scanner(new File("input/Laplace.txt"));
			} else if (casenumber == 3) {
				sc = new Scanner(new File("input/Paviljoen.txt"));
			} else if (casenumber == 4) {
				sc = new Scanner(new File("input/Traverse.txt"));
			} else if (casenumber == 0) {
				sc = new Scanner(new File("input/Connector.txt"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		D = sc.nextInt();
		T = sc.nextInt();
		E = sc.nextInt();
		C = sc.nextInt();
		S = sc.nextInt();
		P = sc.nextInt();
		
		students = new Student[S];
		
		for (int s = 0; s < S; s++) {
			students[s].i = sc.nextInt();
			for (int n = 0; n < students[s].i; n++) {
				students[s].exams[n] = sc.nextInt();
			}
		}
		
		// choose here the algorithm to run
		run();
		
		
	}
	
	void run() {
		
	}
	
	void analyzeOutput(int sol[]) {
		long score;
		int[] timeslots = new int[D * T];
		Arrays.fill(timeslots, 0);
		
		for (int s : sol) {
			timeslots[s]++;
			if (s >= D * T) {
				System.out.println("wrong output: assigned after DT - 1");
				return;
			}
		}
		
		for (int timeslot : timeslots) {
			if (timeslot > C) {
				System.out.println("wrong output: assigned more than C courses to a timeslot");
				return;
			}
		}
		
		for (Student stud : students) {
			
		}
	}
 

	public static void main(String[] args) {
		new Main(1);

	}

}
