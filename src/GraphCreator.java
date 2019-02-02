public class GraphCreator {



    public static Exam[] createGraph(Student[] students, int noExams) {
        Exam[] graph = new Exam[noExams];
        for (int i = 0; i < noExams; i++) {
            graph[i] = new Exam(i);
        }
        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students[i].exams.length; j++) {

            }
        }
        return graph;
    }
}
