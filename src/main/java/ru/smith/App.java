package ru.smith;

import java.sql.*;
import java.util.Scanner;

public class App {
    Connection con;
    Statement statement;

    public void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name:");
        String name = scanner.nextLine();
        System.out.print("Enter user phone:");
        String phone = scanner.nextLine();

        String query="INSERT INTO users ('name', 'phone') " +
                "VALUES ('" + name + "', '" + phone + "');";

        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void select(){
        try {
            String sql ="SELECT id, name, phone FROM users ORDER BY name;";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                System.out.println(id + "\t| " + name + "\t\t| " + phone);

            }
            rs.close();
            statement.close();
        }
        catch (Exception e){}
    }
    public static void main(String[] args) {
    App app = new App();
    app.open();
    //app.insert();
    app.select();
    app.close();
    }
}
