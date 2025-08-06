public class Dog extends Animal {

    // constructuor
    public Dog(){
    }

    public Dog(String name){
    super(name);
    }

    public void makenoise(){
        System.out.println("Bhow Bhow");
    }


    public void roams(){
        System.out.println(getName()+ "Roams on street");
    }

    public void eat(){
        System.out.println(getName()+" eats food");
    }

    public void play(){
        System.out.println(getName()+" is playing");
    }









}
