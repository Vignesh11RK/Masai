import javax.xml.namespace.QName;

public class Address {
    private String city;
    private String state;
    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    //constructor

    public Address(String city, String state, int pin) {
        System.out.println("This is the parameterized constructor");
        this.city = city;
        this.state = state;
        this.pin = pin;
    }


    //toString() is being used
    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pin=" + pin +
                '}';
    }
}
