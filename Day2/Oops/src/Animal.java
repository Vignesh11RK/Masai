public class Animal {
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//NON PARA
    public Animal(){
        System.out.println("This is non parametrized");
    }

    //parameterized constructor
   public Animal(String name){
       this.name=name;
   }


   //methods

    public void eat() {
        System.out.println("The" +this.name+ "is eating ");
    }

    public void sleep() {
        System.out.println("The " +this.name+ "is sleeps ");
    }

    public void makenoise() {
        System.out.println("The " +this.name+ "is makes noise ");
    }

    public void roams() {
        System.out.println("The " +this.name+ "is roams ");
    }









}
