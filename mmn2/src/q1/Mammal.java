package q1;

public abstract class Mammal extends Animal {
    public Mammal(String name, int age, String color) {
        super(name, age, color);
    }

    public abstract void sound();
}
