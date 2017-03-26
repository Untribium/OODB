package db;

public class Type {
    private String name;

    Type(String name) {
        this.name = name;
    }

    public static Type newType(String name) {
        return new Type(name);
    }

    public String getName() {
        return name;
    }

    public Instance newInstance() {
        return new Instance();
    }

    /*public Set<Instance> allInstances() {
        return null;
    }

    public Set<Instance> where(String attribute, int value) {

    }

    public Set<Instance> where(String attribute, double value) {

    }

    public Set<Instance> where(String attribute, String value) {

    }

    public Set<Instance> where(String attribute, Instance value) {

    }*/
}
