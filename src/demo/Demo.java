package demo;

import db.*;

public class Demo {

    public static void main(String[] args) {
        System.out.println("demo.demo");

        Database db = new Database();

        db.printTypes();

        Type t_person = db.newType("Person");
        Type t_course = db.newType("Course");

        System.out.println(t_person.getName());

        Instance peter = t_person.newInstance();

        System.out.println(peter.getClass().getName());

        db.printTypes();


    }

}
