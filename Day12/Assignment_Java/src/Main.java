import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Java Assignmnent");



        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 60000),
                new Employee(2, "Bob", "Finance", 45000),
                new Employee(3, "Charlie", "IT", 70000),
                new Employee(4, "David", "HR", 40000),
                new Employee(5, "Eva", "Finance", 80000)
        );


        List<Employee> sal =employees.stream()
                .filter(e->e.getSalary() > 50000)
                .collect(Collectors.toList());
        System.out.println(sal);














    }
}