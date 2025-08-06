public class Cat extends Animal{
    // this are considered as constructor

    public Cat(){
        super();
    }

    // this are considered as constructor
    public Cat(String name){
        super(name);
    }


    public void jump(){
        System.out.println("Is jumping");
    }
    public void makesnoise(){
        System.out.println("Is makesnoise");
    }
}



