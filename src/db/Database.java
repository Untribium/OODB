package db;

import java.sql.*;

public class Database {

    private Connection connection;

    public Database() {

        try {
            // from http://halyph.com/blog/2015/01/22/how-to-use-embedded-h2-with-h2-console.html
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "sa");

            // CREATE TABLE Types
            Statement c_types = connection.createStatement();
            c_types.executeUpdate(
            "CREATE TABLE Types(" +
                    "ID int AUTO_INCREMENT, " +
                    "name varchar(255) NOT NULL UNIQUE, " +
                    "PRIMARY KEY (ID)" +
                ")"
            );

            // CREATE TABLE Instances
            Statement c_instances = connection.createStatement();
            c_instances.executeUpdate(
            "CREATE TABLE Instances(" +
                    "ID int, " +
                    "TID int, " +
                    "PRIMARY KEY (ID), " +
                    "FOREIGN KEY (TID) REFERENCES Types(ID)" +
                ")"
            );

            // CREATE TABLE Fields
            Statement c_fields = connection.createStatement();
            c_fields.executeUpdate(
            "CREATE TABLE Fields(" +
                    "TID int, " +
                    "FID int, " +
                    "name varchar(255), " +
                    "PRIMARY KEY (TID, FID)" +
                ")"
            );

            // CREATE TABLE Data
            Statement c_data = connection.createStatement();
            c_data.executeUpdate(
            "CREATE TABLE Data(" +
                    "IID int, " +
                    "FID int, " +
                    "content varchar(255), " +
                    "PRIMARY KEY (IID, FID)" +
                ")"
            );


        } catch (Exception e) {
            System.out.println("Uh-oh...");
        }
    }

    public Type newType(String name) {

        Type t = new Type(name);

        try {
            int id = (int) (Math.random()*1000);
            Statement i_type = connection.createStatement();
            i_type.executeUpdate("INSERT INTO Types (name) VALUES ('"+name+"')", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = i_type.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getInt(1));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return t;
    }

    // for testing only
    public void printTypes() {
        try {

            Statement q_types = connection.createStatement();
            ResultSet rs = q_types.executeQuery("SELECT * FROM Types");

            System.out.println("Types:");

            if(!rs.isBeforeFirst()) {
                System.out.println("> none");
            } else {
                while (rs.next()) {
                    System.out.println("> " + rs.getString("name"));
                }
            }

        } catch (Exception e) {
            System.out.println("Uh-oh...");
        }
    }
}
