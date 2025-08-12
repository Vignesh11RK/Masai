interface Coffee {
    String getDescription();
    double cost();
} //   interface creation


//implementing the coffee interface
class SimpleCoffee implements Coffee{
    public String getDescription() { return "Simple Coffee"; }
    public double cost() { return 30; }
}


// creation of new abstract class named CoffeeDecorator implementing Coffee interface
abstract class  CoffeeDecorator implements Coffee{
    protected final Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    public String getDescription() { return decoratedCoffee.getDescription(); }
    public double cost() { return decoratedCoffee.cost(); }
}


class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee); }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
    public double cost() { return decoratedCoffee.cost() + 10; }
}



class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
    public double cost() { return decoratedCoffee.cost() + 5; }
}




