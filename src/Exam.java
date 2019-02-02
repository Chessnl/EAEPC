public class Exam {
    int id;
    ExamEdge[] overlap;
    int students;

    public Exam(int id, int noExams) {
        this.id = id;
        this.overlap = new ExamEdge[noExams];
    }
}
