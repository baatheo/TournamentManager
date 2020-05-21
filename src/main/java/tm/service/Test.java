package tm.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    public static void main(String[] args){
        String url="jdbc:postgresql://192.168.99.100:5432/";
        String user="postgres";
        String password = "123456";
        try{
            Connection con =
                    DriverManager.getConnection(url,user,password);
            System.out.println("success!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
