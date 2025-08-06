import java.sql.SQLOutput;

public class Employee {

    String id;
    String name;
    String department;
    private TrainingModule TM;

    public String getId() {
        return id;
    }

    public Employee(TrainingModule TM) {
        this.TM = TM;
    }

    public TrainingModule getTM() {
        return TM;
    }

    public void setTM(TrainingModule TM) {
        this.TM = TM;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee(String id, String name, String department, TrainingModule TM) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.TM = TM;
    }

    void showDetails(){
        System.out.println("Employee id is :" +id);
        System.out.println("Employee name is :" +name);
        System.out.println("Employee Department is :" +department);

    }

    public void calculateBonus(int base){
        System.out.println("This is base "+base);
    }

    public void calculateBonus(int base,int rating){
        System.out.println("This is base with the required rating "  +rating);
    }


    public static boolean validate(String id){
        // checking id length
        if(id.length() !=5){
            System.out.println("This is invalid empcode ");
            return false;
        }

        // checking char are digits or no
        for(char c:id.toCharArray()){
            if(!Character.isDigit(c)){
                System.out.println("This is invalid empcode ");
                return false;
            }
        }
        System.out.println("This is valid emp code ");
        return true;

    }


}
