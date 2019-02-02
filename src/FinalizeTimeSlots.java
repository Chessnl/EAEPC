import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.Set;

public class FinalizeTimeSlots {

    // takes an input array of length D containing a set of the exams
    //  that are supposed to be executed on day i

    static int[] solution;

    public static int[] finalizeSolution(ArrayList<Set<Integer>> assignments, int timeSlots, Exam[] exams, int maxPerSlot) {
        solution = new int[exams.length];

        for (int i = 0; i < assignments.size(); i++) {
            assignDaySimple(assignments.get(i), i, timeSlots, exams);
        }


        return solution;
    }




    private static void assignDay(Set<Integer> assignments, int day, int timeSlots, Exam[] exams) {
        ArrayList<HuffmanNode> nodes = new ArrayList<>();
        while(nodes.size() > timeSlots) {
            //find min
            int minI, minJ;
            minI = 0;
            minJ = 1;
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = i + 1; j < nodes.size(); j++) {

                }
            }

            //merge min
            ArrayList<Integer> ids = nodes.get(minJ).ids;
            nodes.remove(minJ);
            nodes.get(minI).ids.addAll(ids);
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (Integer j : nodes.get(i).ids) {
                solution[j] = day * timeSlots + i;
            }
        }



    }

    private static void assignDaySimple(Set<Integer> assignments, int day, int timeSlots, Exam[] exams) {

        int counter = 0;
        for (Integer i : assignments)  {
            solution[i] = day * timeSlots + (counter % timeSlots);
            counter++;
        }
        System.out.println(counter);
    }




}
