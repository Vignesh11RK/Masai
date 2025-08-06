//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//         Animal animal=new Animal("Animal name");
//         animal.makesnoise();
//
//         Cat cat=new Cat("Billa");
//         cat.makesnoise();
//
//         Animal an=new Cat("billi");
//         an.makesnoise();
//
//         Cat c=(Cat)an;
//         c.jump();


        Dog d=new Dog("Mike");
        Cat c=new Cat("Persian");



        AnimalUnits util=new AnimalUnits();
        util.addanimal(d);
        util.addanimal(c);


        //Abstract class means when u dont know the functioning currently but might know it or need it later








         //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        }
    }
