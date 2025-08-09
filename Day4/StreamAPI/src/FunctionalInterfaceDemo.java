import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalInterfaceDemo {
    public static void main(String[] args){

        Consumer<String> printerr=(String s)-> System.out.println(s);
        printerr.accept("Heloo");

        Predicate<Integer> isEven=(n)-> n%2 ==0;
        System.out.println(isEven.test(4));
        System.out.println(7);

        Function<Integer,String> concatInt=(n)->"Number " +n;
        System.out.println(concatInt.apply(120));

        // they have methods defined

        Supplier<Double> getRandomGenerativeValue =()->Math.random();
        getRandomGenerativeValue = Math::random;

        System.out.println(getRandomGenerativeValue.get());

        List<String> names=List.of("Vignesh","Chris","Soham","Shiv");

        //stream
        names.stream()
                .filter(n->n.startsWith("R"))
                .map(n->n.toUpperCase())
                .sorted()
                .forEach(n->System.out.println(n));

        //
        List<String> newNames=names.stream()
                .filter(n->n.startsWith("R"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
        System.out.println(newNames);

        //COUNT OF VARIABLES IN LIST

        long numNames= names.stream()
                .filter(n->n.startsWith("R"))
                .map(String::toUpperCase)
                .sorted()
                .count();
        System.out.println("Total names starting with R are:" +numNames);


        













    }
}
