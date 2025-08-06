public class Animal {

    String name ;


    // This is default class
    public Animal() {

    }
    // function
    public void eats(){
        System.out.println("Is eating");
    }

    public Animal(String name) {
        this.name = name;
    }

    public void makesnoise(){
        System.out.println("Is makesnoise");
    }


    // getter
    public String getName() {
        return name;
    }
    // setter
    public void setName(String name) {
        this.name = name;
    }
    //function
    public void sleep(){
        System.out.println("Is sleep");
    }

    //function
    public void roams(){
        System.out.println("Is roams");
    }



}
