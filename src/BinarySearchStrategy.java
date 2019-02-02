import javax.jnlp.IntegrationService;
import java.util.*;

public class BinarySearchStrategy {
    ArrayList<Set<Integer>> result;


    public class EdgeComperator implements Comparator<ExamEdge>{
            public int compare(ExamEdge a, ExamEdge b) {
                if ( a.weight > b.weight ) return -1;
                else if ( a.weight == b.weight ) return 0;
                else return 1;
            }
    }

    ArrayList<Set<Integer>> run(Exam[] graph, int number_of_days) {
        for (int i = 0; i < number_of_days; i++) {
            result.add(new HashSet<>());
        }
        Set<Exam> exams = new HashSet<>();
        for (Exam e : graph) {
            exams.add(e);
        }

        recurse(exams, 0, number_of_days - 1);
        return result;
    }


    void recurse(Set<Exam> exams, int leftDayBoundary, int rightDayBoundary) {
        if (leftDayBoundary == rightDayBoundary) {
            Set<Integer> examID = new HashSet<>();
            for (Exam e : exams) {
                examID.add(e.id);
            }
            result.set(leftDayBoundary, examID);
        }

        Set<Exam> set1 = new HashSet<>();
        Set<Exam> set2 = new HashSet<>();

        ArrayList<ExamEdge> edges = new ArrayList<>();

        for (Exam e : exams) {
            for (ExamEdge edge : e.overlap) {
                edges.add(edge);
            }
        }

        while (edges.size() > 0) {
            Collections.sort(edges, new EdgeComperator());

            int examA = edges.get(0).examA;
            int examB = edges.get(0).examB;

            for (ExamEdge edge : edges) {
                if (edge.examA == examA || edge.examB == examA || edge.examA == examB || edge.examB == examB) {
                    edges.remove(edge);
                }
            }

            // Put exams in set
            for (Exam e : exams) {
                if (e.id == examA) {
                    set1.add(e);
                    exams.remove(e);
                } else if (e.id == examB){
                    set2.add(e);
                    exams.remove(e);
                }
            }

        }

        // Assign final exam when odd number
        if (exams.size() != 0) { // AT MOST 1!
            for (Exam e : exams) {
                set1.add(e);
            }
        }


        recurse(set1, leftDayBoundary, (leftDayBoundary + rightDayBoundary) / 2);
        recurse(set2, (leftDayBoundary + rightDayBoundary) / 2 + 1, rightDayBoundary);
    }

}
