import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


         //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner sc=new Scanner(System.in);
        String name=sc.nextLine();
        String id =sc.nextLine();
        String dept=sc.next();

        TrainingModule tm = new TrainingModule("Java",9);
        Employee emp=new Employee(id,name,dept,tm);
        emp.showDetails();

        emp.calculateBonus(56);
        emp.calculateBonus(56,9);

        Employee.validate(id);


        String s = "Hello World this is Vignesh in HDFC LIFE";
        StringBuilder sb = new StringBuilder(s);
        String reversedString = sb.reverse().toString();
        System.out.println(reversedString);


//        String:
//        An immutable sequence of characters, meaning its value cannot be changed after creation, making it suitable for constant or rarely modified textual data.
//
//        StringBuilder:
//        A mutable and non-thread-safe sequence of characters, providing efficient string manipulation for single-threaded environments.
//
//        StringBuffer:
//        A mutable and thread-safe sequence of characters, ensuring synchronized string operations for multi-threaded environments at the cost of some performance compared to StringBuilder.




        // one -d array
        int[] n=new int[5];
        System.out.println("Scores");
        for(int i=0;i<n.length;i++){
            n[i] = sc.nextInt();
        }

        int avg =0;
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for(int i=0;i<n.length;i++){
            if(min >=n[i]){
                min=n[i];
            }

            if(max<=n[i]){
                max=n[i];
            }
            avg+=n[i];
        }
        avg=avg/n.length;

        System.out.println("Average: "+avg+ " | Max "+max+ "|Min : "+min);


        //2d array

        int[][] multiplicationTable = new int[3][3];


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                multiplicationTable[i][j] = (i + 1) * (j + 1);
            }
        }

        // Print the array
        System.out.println("Multiplication Table:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(multiplicationTable[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing a row
        }





        // Command line arg simulation
        System.out.println("Entert 3 numbers to sum ");
        int a = sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();

        System.out.println("Sum"+(a+b+c));
        sc.close();





    }
}