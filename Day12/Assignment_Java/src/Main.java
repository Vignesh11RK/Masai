import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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


        // task 1

        List<Employee> sal = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .collect(Collectors.toList());
        System.out.println(sal);

        //task 2

        List<String> uppercaseNames = employees.stream()
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println(uppercaseNames);

        //task 3

        Map<String, List<Employee>> groupedByDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
//                 .collect(Collectors.groupingBy(Employee::getDepartment));   this is method reference for shorthand notation in java 8

        System.out.println(groupedByDept);

        //task 4
        List<Employee> sortedSalary = employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .collect(Collectors.toList());
        System.out.println(sortedSalary);

        //task 5
        double avg = employees.stream()
                .mapToDouble(e -> e.getSalary())
                .average()
                .orElse(0.0);
        System.out.println(avg);

        //task 6
        Optional<Employee> firstEmp = employees.stream().findFirst();
        System.out.println("First employee (Optional) → " + firstEmp.map(Employee::getName).orElse("No employee"));


// task 7

        BonusCalculator bonusCalc = salary -> salary * 1.10;


        System.out.println("Bonus salaries printed → ");
        employees.forEach(e -> {
            double newSalary = bonusCalc.calculate(e.getSalary());
            System.out.println(e.getName() + ": " + newSalary);
        });

    }
}