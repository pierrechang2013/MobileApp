package canada.montreal.pierre.android2_exam2.mysql;


import android.util.Log;


import com.mysql.jdbc.JDBC4PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import canada.montreal.pierre.android2_exam2.Client;


public class DbUtil {

    private final static String diver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://192.168.1.8:3306/bdclients?serverTimezone = GMT&characterEncoding=UTF-8";
    //    private final static String url = "jdbc:mysql://127.0.0.1:3306/forexam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
//    private final static String url = "jdbc:mysql://localhost:3306/forexam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private final static String user = "root";//compte
    private final static String password = "123456";//mot de passe

    /*
     * 连接数据库
     * */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(diver);
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
            Log.d("getConn", "连接成功");
        } catch (ClassNotFoundException e) {
            Log.d("找不到驱动", e.getMessage(), e);
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("异常，异常。。。。。", e.getMessage(), e);
            e.printStackTrace();
        }
        return conn;
    }

    public static void addClient(Connection con, Client client) {
        String str = "insert into client(nom,adresse,age,sexe) value(?,?,?,?)";


        PreparedStatement ps;
        //ResultSet rs;

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1, client.getLastName());
            ps.setObject(2, client.getFirstName());
            ps.setObject(3, client.getAge());
            ps.setObject(4, client.getSex());
            String rsq = ((JDBC4PreparedStatement) ps).asSql();

            Log.d("", rsq);//打印sql语句
            ps.execute();
//            con.commit();
            ps.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


    }



    public static void deleteClient(Connection con, long id) {
        String str = "delete from client where code = " + id;
        Statement stmt;


        try {
            stmt = con.createStatement();

            stmt.execute(str);

            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


    }

    public static List<Client> getClientByAge(Connection con, int age) {
        String str = "select * from client where age >= " + age;
        Statement stmt;
        List<Client> list = new ArrayList<Client>();

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(str);



            while (rs.next()) {

                Client client = new Client(rs.getString("nom"), rs.getString("adresse"), rs.getInt("Age"), rs.getString("sexe"));
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                client.setId(rs.getInt("code"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));
                list.add(client);
            }

             Log.d("************list.size ",String.valueOf(list.size()));

            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


        return list;

    }

    public static List<Client> getClientBySex(Connection con, String sex) {
        String str = "select * from client where sexe = '" + sex+"'";
        Statement stmt;
        List<Client> list = new ArrayList<Client>();

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(str);



            while (rs.next()) {

                Client client = new Client(rs.getString("nom"), rs.getString("adresse"), rs.getInt("Age"), rs.getString("sexe"));
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                client.setId(rs.getInt("code"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));
                list.add(client);
            }

            Log.d("************list.size ",String.valueOf(list.size()));

            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


        return list;

    }


    public static List<Client> getClientList(Connection con) {
        String str = "select * from  client ";
        Statement stmt;
        ResultSet rs;
        List<Client> list = new ArrayList<Client>();
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(str);
            while (rs.next()) {
                Client client = new Client(rs.getString("nom"), rs.getString("adresse"), rs.getInt("Age"), rs.getString("sexe"));
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                client.setId(rs.getInt("code"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));
                list.add(client);

            }


            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


        return list;

    }

    public static void close(PreparedStatement ps, Connection conn) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, Statement state, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

