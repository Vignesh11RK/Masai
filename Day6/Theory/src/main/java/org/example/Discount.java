package org.example;

public interface Discount {
    double getDiscount();
}


 class ChristmasDiscount implements Discount{
     public double getDiscount() { return 0.2; }
}

class NewYearDiscount implements Discount {
    public double getDiscount() { return 0.3; }
}



