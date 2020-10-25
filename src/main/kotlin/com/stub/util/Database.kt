package com.stub.util

import java.sql.Connection
import java.sql.DriverManager

class Database {
    var conn: Connection

    init{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobapp", "root","")
    }
}