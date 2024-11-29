package q1;

public abstract class Bird extends Animal {
    public Bird(String name, int age, String color) {
        super(name, age, color);
    }

    public void fly() {
        System.out.println(getName() + " is flying.");
    }
}
