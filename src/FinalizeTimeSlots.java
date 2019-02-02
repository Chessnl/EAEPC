import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.Set;

public class FinalizeTimeSlots {

    // takes an input array of length D containing a set of the exams
    //  that are supposed to be executed on day i

    static int[] solution;

    public static int[] finalizeSolution(Set<Integer>[] assignments, int timeSlots, int noExams) {
        solution = new int[noExams];

        for (int i = 0; i < assignments.length; i++) {
            assignDay(assignments[i], i, timeSlots);
        }


        return solution;
    }




    private static void assignDay(Set<Integer> assignments, int day, int timeSlots) {
        //Exam[]
    }




}
