import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class FinalizeTimeSlots {

    // takes an input array of length D containing a set of the exams
    //  that are supposed to be executed on day i

    static int[] solution;
    static Random random;

    public static int[] finalizeSolution(ArrayList<Set<Integer>> assignments, int timeSlots, Exam[] exams, int maxPerSlot) {
        solution = new int[exams.length];

        random = new Random();


        for (int i = 0; i < assignments.size(); i++) {
            assignDayRandom(assignments.get(i), i, timeSlots, maxPerSlot, exams);
            //System.out.println(maxPerSlot + " " + assignments.get(i).size());
        }


        return solution;
    }

    private static void assignDaySimple(Set<Integer> assignments, int day, int timeSlots) {

        int counter = 0;
        for (Integer i : assignments)  {
            solution[i] = day * timeSlots + (counter % timeSlots);
            counter++;
        }
    }




    private static void assignDayRandom(Set<Integer> assignments, int day, int timeSlots, int maxPerSlot, Exam[] graph) {
        int maxScore = -10;
        int[][] bestAssigment = new int[0][0];
        for (int i = 0; i < 1000; i++) {
            int[][] assigment = getRandomAssignment(assignments, timeSlots, maxPerSlot);
            int score = analyzeDay(assigment, graph);
            System.out.println("score: "+ score);
            if (score > maxScore) {
                bestAssigment = assigment;
            } else {
                System.out.println(score);
            }
        }


        for (int i = 0; i < bestAssigment.length; i++) {
            solution[bestAssigment[i][0]] = day*timeSlots + bestAssigment[i][1];
            System.out.println(bestAssigment[i][0] + " " + (day*timeSlots + bestAssigment[i][1]));
        }

    }

    private static int[][] getRandomAssignment(Set<Integer> assignments, int timeSlots, int maxPerSlot) {
        int[] slots = new int[timeSlots];
        int[][] assignment = new int[assignments.size()][2];
        int counter = 0;
        for (Integer i : assignments)  {
            int slot = getRandomSlot(slots, maxPerSlot);
            assignment[counter][0] = i;
            assignment[counter][1] = (slot);
            slots[slot]++;
            counter++;
        }

        /* System.out.println("-------------");
        for (int i = 0; i < assignment.length; i++) {
            System.out.println(assignment[i][0] + " " + assignment[i][1]);
        } */


        return assignment;
    }

    private static int getRandomSlot(int[] slots, int maxPerSlot) {
        int slot = random.nextInt(slots.length);

        while (slots[slot] == maxPerSlot) {
            slot = random.nextInt(slots.length);
        }

        return slot;
    }

    private static int analyzeDay(int[][] config, Exam[] graph) {
        int result = 0;
        for (int i = 0; i < config.length; i++) {
            for (int j = i + 1; j < config.length; j++) {
                if (config[i][1] == config[j][1]) {
                    for (Exam e : graph) {
                        for (ExamEdge edge : e.overlap) {
                            if (edge.examA == config[i][0] && edge.examB == config[j][0]) {
                                result += edge.weight;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


}
