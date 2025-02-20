public class GraphCreator {



    public static Exam[] createGraph(Student[] students, int noExams) {
        Exam[] graph = new Exam[noExams];
        for (int i = 0; i < noExams; i++) {
            graph[i] = new Exam(i, noExams);
        }

        for (int i = 0; i < noExams; i++) {
            for (int j = i; j < noExams; j++) {
                ExamEdge edge = new ExamEdge(i, j);
                graph[i].overlap[j] = edge;
                graph[j].overlap[i] = edge;
            }
        }


        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students[i].exams.length; j++) {
                graph[students[i].exams[j]].students++;
                for (int k = j + 1; k < students[i].exams.length; k++) {
                    graph[students[i].exams[j]].overlap[students[i].exams[k]].weight++;
                }
            }
        }

        return graph;
    }
}
