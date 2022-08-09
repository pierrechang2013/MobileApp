package canada.montreal.pierre.android2_labo3.mysql;


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

import canada.montreal.pierre.android2_labo3.Gym;
import canada.montreal.pierre.android2_labo3.GymExercice;
import canada.montreal.pierre.android2_labo3.Video;


public class DbUtil {

    private final static String diver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://192.168.1.8:3306/forexam1?serverTimezone = GMT&characterEncoding=UTF-8";
//    private final static String url = "jdbc:mysql://127.0.0.1:3306/forexam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
//    private final static String url = "jdbc:mysql://localhost:3306/forexam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private final static String user = "root";//用户名
    private final static String password = "123456";//密码

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

    public static void addGymExercice(Connection con, GymExercice ge) {
        String str = "insert into gymExercice(gymId,title,img,disc) value(?,?,?,?)";



        PreparedStatement ps ;
        //ResultSet rs;

        try {
            ps = con.prepareStatement(str);
            ps.setObject(1,ge.getGymId());
            ps.setObject(2,ge.getTitle());
            ps.setObject(3,ge.getImg());
            ps.setObject(4,ge.getDisc());
            String rsq = ((JDBC4PreparedStatement)ps).asSql();

            Log.d("",rsq);//打印sql语句
            ps.execute();
//            con.commit();
            ps.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }




    }

    public static void updateGymExercice(Connection con, GymExercice ge) {
        String str = "update gymExercice set title = ? ,img = ?  ,disc = ?  where id =  ?";



        PreparedStatement ps ;
        ResultSet rs;

        try {
            ps = con.prepareStatement(str);

            ps.setObject(1,ge.getTitle());
            ps.setObject(2,ge.getImg());
            ps.setObject(3,ge.getDisc());
            ps.setObject(4,ge.getId());
            Log.d("",str);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }




    }

    public static void deleteGymExercice(Connection con, long id) {
        String str = "delete from gymExercice where id = " + id;
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

    public static Video getVideo(Connection con, long geId) {
        String str = "select * from  video  where geID = " + geId;
        Statement stmt;
        ResultSet rs;

        Video video = new Video();
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(str);


            while (rs.next()) {
                video = new Video(geId, rs.getString("videoAdress"), "", rs.getString("questionAnswer"));
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                video.setId(rs.getInt("id"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));

                break;
            }


            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


        return video;

    }

    public static List<GymExercice> getGeList(Connection con, long gymId) {
        String str = "select * from  gymExercice  where gymId = " + gymId + " order by id desc";
        Statement stmt;
        ResultSet rs;
        List<GymExercice> list = new ArrayList<GymExercice>();
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(str);
            while (rs.next()) {
                GymExercice ge = new GymExercice(gymId, rs.getString("img"), rs.getString("title"), rs.getString("disc"), "");
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                ge.setId(rs.getInt("id"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));
                list.add(ge);

            }


            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {


            e.printStackTrace();
        }


        return list;

    }

    public static List<Gym> getGymList(Connection con) {
        String str = "select * from  gym ";
        Statement stmt;
        ResultSet rs;
        List<Gym> list = new ArrayList<Gym>();
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(str);
            while (rs.next()) {
                Gym gym = new Gym(rs.getString("img"), rs.getString("title"));
                // 获取数据 两种方式 根据列的索引获取 根据列名获取
                gym.setId(rs.getInt("id"));
                //Log.d("***************",rs.getString("title"));
                //Log.d("***************",rs.getString("img"));
                list.add(gym);

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

