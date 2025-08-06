public class Employee extends Person{
    int empcode;
    String designation;

    public Employee(int empcode, String designation, float salary,String name , int age) {
        super(name,age);
        this.empcode = empcode;
        this.designation = designation;
        this.salary = salary;
    }

    float salary;


    public int getEmpcode() {
        return empcode;
    }

    public void setEmpcode(int empcode) {
        this.empcode = empcode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }


    public void applyforleave(){
        System.out.println("This person has applied for a leave");
    }

    public void displayinfo(){
        System.out.println("This is empcode :" +empcode);
        System.out.println("This is designation :" +designation);
        System.out.println("This is salary :" +salary);
        System.out.println("This is address :" +addr.toString());
    }



}
