public class Person {
    private String name;
    private int age;


    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    //address creation

   Address addr=new Address("Mumbai", "Maharashtra", 400055);


    public void displayinfo(){
        System.out.println("This is name :" +name);
        System.out.println("This is age :" +age);
        System.out.println("This is address :" +addr.toString());
    }


}

