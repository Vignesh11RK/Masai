public class Student extends Person{
    int roll_no;

    public Student(int roll_no, int age,String name,String grade) {
        super(name, age);
        this.roll_no = roll_no;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    String grade;

    public void displayinfo(){

        System.out.println("This is rollno :" +roll_no);
        System.out.println("This is grade :" +grade);

    }



}
