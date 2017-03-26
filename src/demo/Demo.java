package demo;

import db.*;

public class Demo {

    public static void main(String[] args) {
        System.out.println("demo.demo");

        Database db = new Database();
        Type t_person = db.newType("Person");

        System.out.println(t_person.getName());

        Instance peter = t_person.newInstance();

    }

}
