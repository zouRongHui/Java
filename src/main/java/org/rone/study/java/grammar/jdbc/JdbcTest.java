package org.rone.study.java.grammar.jdbc;

import java.sql.*;

/**
 * 原生jdbc的使用示例
 */
public class JdbcTest {

    private static final String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/rone_test";
    private static final String mysqlUser = "root";
    private static final String mysqlPwd = "root";

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private static String sql;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPwd);
//        select();
//        batchInsert();
//        batchUpdate();
//        batchInsertAndUpdate();
        transaction();
    }

    /**
     * 事务
     */
    public static void transaction() throws SQLException {
        //设置默认提交为false，后面需要手动提交
        connection.setAutoCommit(false);

        sql = "delete from user where userName='NO.0'";
        statement = connection.prepareStatement(sql);
        statement.execute();
        statement.close();
        sql = "insert into user(`id`, `userName`, `pwd`) values('1', 'root', '123')";
        statement = connection.prepareStatement(sql);
        statement.execute();
        statement.close();

        try {
            //手动提交，一旦出错需要回滚
            connection.commit();
        } catch (SQLException e) {
            System.out.println("rollback...");
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            connection.close();
        }
    }

    /**
     * 批量混合操作
     */
    public static void batchInsertAndUpdate() throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch("update user set tip='normal' where userName='NO.2'");
        statement.addBatch("insert into user(`userName`,`pwd`,`tip`) values('t-ara','999','super vip')");
        statement.executeBatch();
        connection.close();
    }

    /**
     * 批量更新数据
     */
    public static void batchUpdate() throws SQLException {
        sql = "update user set tip=? where userName=?";
        statement = connection.prepareStatement(sql);
        for (int i = 0; i < 3; i++) {
            statement.setString(1, "VIP" + i);
            statement.setString(2, "NO." + i);
            statement.addBatch();
        }
        statement.executeBatch();
        connection.close();
    }

    /**
     * 批量插入数据
     */
    public static void batchInsert() throws SQLException {
        sql = "insert into user(`userName`,`pwd`) values(?,?)";
        statement = connection.prepareStatement(sql);
        for (int i = 0; i < 3; i++) {
            statement.setString(1, "NO." + i);
            statement.setString(2, "123456");
            statement.addBatch();
        }
        statement.executeBatch();
        connection.close();
    }

    /**
     * 查询数据
     */
    public static void select() throws Exception {
        sql = "select * from user";
        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String userName = resultSet.getString(2);
            String pwd = resultSet.getString(3);
            String tip = resultSet.getString(4);
            System.out.println("id:" + id + ",userName:" + userName + ",pwd:" + pwd + ",tip:" + tip);
        }
        connection.close();
    }
}
