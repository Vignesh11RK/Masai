import java.util.Arrays;

public class Main {
        public static void main(String[] args) {
            int[] data = {5,2,9,1};
            SortContext ctx = new SortContext(new BubbleSort());
            ctx.sort(data);
            System.out.println(Arrays.toString(data));
            ctx.setStrategy(new QuickSort());
            int[] d2 = {7,3,8,4};
            ctx.sort(d2);
            System.out.println(Arrays.toString(d2));
        }
}
