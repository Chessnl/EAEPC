import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	int D, T, E, C, S, P;
	Student[] students;
	int[] rewards;
	
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
			students[s] = new Student(sc.nextInt());
			for (int n = 0; n < students[s].i; n++) {
				students[s].exams[n] = sc.nextInt();
			}
		}
		
		rewards = new int[D];
		for (int r = 0; r < D; r++) {
			rewards[r] = sc.nextInt();
		}
		
		// choose here the algorithm to run
		run();
	}
	
	void run() {
	}
	
	void analyzeOutputFiles(File file) {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) {
			String[] splited = sc.nextLine().split(" ");
			int[] sol = new int[splited.length];
			for (int i = 0; i < splited.length; i++) {
				sol[i] = Integer.valueOf(splited[i]);
			}
			System.out.println(analyzeOutput(sol));
		}
	}
	
	long analyzeOutput(int sol[]) {
		long score = 0;
		int[] timeslots = new int[D * T];
		Arrays.fill(timeslots, 0);
		
		for (int s : sol) {
			timeslots[s]++;
			if (s >= D * T) {
				System.out.println("wrong output: assigned after DT - 1");
				return 0l;
			}
		}
		
		for (int timeslot : timeslots) {
			if (timeslot > C) {
				System.out.println("wrong output: assigned more than C courses to a timeslot");
				return 0l;
			}
		}
		
		for (Student stud : students) {
			HashMap<Integer, Integer> ass = new HashMap();
			for (int exam : stud.exams) {
				Integer c = ass.get(sol[exam]);
				if (c == null) {
					ass.put(sol[exam], 1);
				} else {
					ass.put(sol[exam], c.intValue() + 1);
				}
			}
			
			for (int c : ass.values()) {
				if (c > 1) {
					score -= (c-1) * P;
				}
			}
			
			int n = ass.keySet().size();
			Integer[] keyset = new Integer[n];
			ass.keySet().toArray(keyset);
			Arrays.sort(keyset);
				
			int cur = keyset[0];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < ass.get(keyset[i]); j++) {
					score += rewards[keyset[i] - cur];
					cur = keyset[i];
				}
			}
		}
		
		return score;
	}

	public static void main(String[] args) {
		new Main(0);

	}

}
