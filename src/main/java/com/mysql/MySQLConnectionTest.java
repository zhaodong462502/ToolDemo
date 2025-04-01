package com.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionTest {

    // MySQL 数据库的连接信息
    private static final String URL = "jdbc:mysql://192.168.124.174:13306/canal?useUnicode=true"; // 替换为你的数据库名称
    private static final String USER = "canal"; // 替换为你的数据库用户名
    private static final String PASSWORD = "000000"; // 替换为你的数据库密码

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 尝试建立数据库连接
            System.out.println("正在连接到数据库...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 检查连接是否成功
            if (connection != null && !connection.isClosed()) {
                System.out.println("数据库连接成功！");
            } else {
                System.out.println("数据库连接失败！");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到 MySQL JDBC 驱动！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败，请检查连接信息！");
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("数据库连接已关闭。");
                } catch (SQLException e) {
                    System.out.println("关闭数据库连接时出错！");
                    e.printStackTrace();
                }
            }
        }
    }
}