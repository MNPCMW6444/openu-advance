package q1;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", 5, "Brown", new Owner("Alice", "123-456-7890"));
        Parrot parrot = new Parrot("Polly", 2, "Green");

        List<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(parrot);

        for (Animal animal : animals) {
            System.out.println(animal);
            animal.eat();
            animal.sleep();
            if (animal instanceof Bird) {
                ((Bird) animal).fly();
            } else if (animal instanceof Dog) {
                ((Dog) animal).sound();
            }
        }

        Dog clonedDog = dog.clone();
        System.out.println("\nOriginal Dog: " + dog);
        System.out.println("Cloned Dog: " + clonedDog);

        clonedDog.getOwner().setName("Bob");
        System.out.println("\nAfter modifying cloned dog's owner:");
        System.out.println("Original Dog: " + dog);
        System.out.println("Cloned Dog: " + clonedDog);
    }
}
