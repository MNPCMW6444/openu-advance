package q1;

public class Parrot extends Bird {
    public Parrot(String name, int age, String color) {
        super(name, age, color);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " eats seeds.");
    }
}
