import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AllMethods {

    public void count(){
        List<String> words = Arrays.asList("Apple","Ball","Ant","Bat");
        long count=words.stream()
                .filter(w->w.startsWith("A"))
                .count();
        System.out.println(count);
    }


    public void Dev(){
        List<Integer> numbers= Arrays.asList(1,2,3,4,5,6);
        List<Integer> evenNumbers= numbers.stream()
                .filter(n->n%2==0)
                .collect(Collectors.toList());
        System.out.println(evenNumbers);


        List<Employee> employees = Arrays.asList(
                new Employee("Bob", "Tester", 50000.0),
                new Employee("Alice", "Developer", 70000.0),
                new Employee("Charlie", "Manager", 90000.0)
        );


        List<String> developers=employees.stream()
                .filter(e->e.getRole().equals("Developer"))
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("Developers: " + developers);


    }


    public void Dup(){
        List<Integer> list = Arrays.asList(1,2,2,3,3,3,4);
        List<Integer> unique=list.stream().distinct().collect(Collectors.toList());
        System.out.println(unique);

    }


    public void sortemp(){
        List<Employee> employees = Arrays.asList(
                new Employee("Bob", "Tester", 50000.0),
                new Employee("Alice", "Developer", 70000.0),
                new Employee("Charlie", "Manager", 90000.0)
        );

        List<Employee> sorted=employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println(sorted);
    }





}
