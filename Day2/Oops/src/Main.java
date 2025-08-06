//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Animal ani = new Animal("tiger");
        ani.sleep();
        ani.makenoise();
        ani.roams();

        Animal Nonpara = new Animal();
        Nonpara.setName("Rabbit");
        Nonpara.eat();
        Nonpara.sleep();
        Nonpara.makenoise();
        Nonpara.roams();
    }


}