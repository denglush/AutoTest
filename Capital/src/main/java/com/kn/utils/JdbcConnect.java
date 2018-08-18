package com.kn.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;

public class JdbcConnect {
    public static void main(String[] args) {

        String user = "qsq ";  //数据库用户名
        String password = "Pswd4BS!";  //数据库密码
        try {
// 1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
// 2、创建连接
        Connection conn = null;
        try{
            go();
            conn = DriverManager.getConnection("jdbc:mysql://10.1.1.16:3306/hftq", user, password);
            getData(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void go() {
        String url = "url"; //远程MySQL服务器
        String sshurl = "182.254.211.45 "; //SSH服务器
        String sshuser = "denglulu "; //SSH连接用户名
        String sshpassword = "denglulu542"; //SSH连接密码
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(sshuser, sshurl, 22);
            session.setPassword(sshpassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息

            int assinged_port = session.setPortForwardingL(3306, url, 3306);//端口映射 转发  数据库服务器地址url

            System.out.println("localhost:" + assinged_port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getData(Connection conn) throws SQLException {

// 获取所有表名
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM accout limit 1";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString(1));



// 获取列名
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        for (int i = 0; i < metaData.getColumnCount(); i++) {
//// resultSet数据下标从1开始
//            String columnName = metaData.getColumnName(i + 1);
//            int type = metaData.getColumnType(i + 1);
//            if (Types.INTEGER == type) {
//// int
//            } else if (Types.VARCHAR == type) {
//// String
//            }
//            System.out.print(columnName + "\t");
//        }
//        System.out.println();
// 获取数据
//        while (resultSet.next()) {
//            for (int i = 0; i < metaData.getColumnCount(); i++) {
//// resultSet数据下标从1开始
//                System.out.print(resultSet.getString(i + 1) + "\t");
//            }
//            System.out.println();
//
//
//        }
        st.close();
        conn.close();
    }



}
