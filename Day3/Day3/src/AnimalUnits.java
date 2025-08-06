public class AnimalUnits {

    public void addanimal(Animal animal){ // super type reference variable

        System.out.println("Animal added" +animal.getName());
        animal.makesnoise();

        if(animal instanceof Cat) {
            //typecasting
            Cat cat = (Cat) animal;
            cat.jump();

        }else if(animal instanceof Dog){
            Dog dog=(Dog)animal;
            dog.play();

        }




    }
}
