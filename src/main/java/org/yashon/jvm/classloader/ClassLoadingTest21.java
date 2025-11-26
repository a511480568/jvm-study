package org.yashon.jvm.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClassLoadingTest21 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc://mysql:lcoalhost:3306/xxx", "username", "password");
    }
}
