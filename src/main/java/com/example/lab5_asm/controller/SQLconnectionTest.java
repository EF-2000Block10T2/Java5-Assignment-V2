package com.example.lab5_asm.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLconnectionTest {
    public static void main(String[] args) {
        try {
            // Thông tin kết nối
            String user = "sa2";  // Thay đổi nếu cần
            String pass = "Leopard2a7v"; // Thay đổi nếu cần
            String url = "jdbc:sqlserver://localhost:1433;databaseName=FlightShop;encrypt=false";

            // Tải driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Kết nối
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công!");

            // Câu lệnh SQL
            String sql = "SELECT * FROM OrderDetails"; // Đổi từ Account → Users
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Duyệt kết quả
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Order ID: " + rs.getString("OrderID"));
                System.out.println("Route ID: " + rs.getString("ProductID"));
                System.out.println("Ticket: " + rs.getString("Name"));
                System.out.println("Total: " + rs.getString("Price"));
                System.out.println("Quantity: " + rs.getString("Quantity"));
                System.out.println("-----------------------");
            }


            // Đóng kết nối
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
