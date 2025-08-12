public final class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String city;

    private Person(Builder b) {
        this.firstName = b.firstName;
        this.lastName  = b.lastName;
        this.age       = b.age;
        this.city      = b.city;
    }

    public static class Builder {
        private final String firstName; // required
        private final String lastName;  // required
        private int age = 0;            // optional default
        private String city = "Unknown";

        public Builder(String firstName, String lastName) {
            if(firstName == null || lastName == null) throw new IllegalArgumentException("Names required");
            this.firstName = firstName;
            this.lastName  = lastName;
        }

        public Builder age(int age) { this.age = age; return this; }
        public Builder city(String city) { this.city = city; return this; }

        public Person build() {
            // validation example:
            if (age < 0) throw new IllegalStateException("Age can't be negative");
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age + " yrs, " + city;
    }

    // demo
    public static void main(String[] args) {
        Person p = new Person.Builder("Asha", "Patel")
                .age(28)
                .city("Mumbai")
                .build();
        System.out.println(p);
    }
}
