package q1;

public abstract class Animal implements Cloneable {
    private String name;
    private int age;
    private String color;

    public Animal(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract void eat();

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + ", color='" + color + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return age == animal.age &&
                name.equals(animal.name) &&
                color.equals(animal.color);
    }

    @Override
    protected Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported.", e);
        }
    }
}
