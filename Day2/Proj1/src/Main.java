//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Person p = new Person("Vignesh",21);
        p.displayinfo();


        Employee emp = new Employee(134,"VP",56,p.getName(), p.getAge());
        emp.displayinfo();
        emp.applyforleave();

        Student std = new Student(9551,21,"Vignesh","A");

        std.displayinfo();



    }

}