package animal;

public abstract class Animal {

    private static int count = 0;
    private String name;

    public Animal(final String value) {
        if (value == null || value.equals("")) {

            this.name = "default";
        } else {


            this.name = value;
        }
        count++;
    }

    public static int getCount() {
        return count;
    }


    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);
}
