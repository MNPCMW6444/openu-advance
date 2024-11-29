package q1;


public class Dog extends Mammal {
    private Owner owner;

    public Dog(String name, int age, String color, Owner owner) {
        super(name, age, color);
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public void eat() {
        System.out.println(getName() + " eats dog food.");
    }

    @Override
    public void sound() {
        System.out.println(getName() + " says Woof!");
    }

    @Override
    public Dog clone() {
        Dog cloned = (Dog) super.clone();
        cloned.owner = this.owner.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return super.toString() + ", Owner=" + owner;
    }
}
