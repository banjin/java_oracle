//java连接Oracle数据库

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement; 
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;

public class OracleClient{
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       //1.加载驱动
        Connection conn=null;  //连接对象
        Statement stmt=null;  //语句对象
        ResultSet rs=null;   //数据集对象
        int recordNum=0;
        //thin：小型驱动，驱动方式 localhost 本机ip地址 127.0.0.1 SID：数据库的SID/SERVICENAME：数据库的SID
        String url="jdbc:oracle:thin:@localhost:1521:orac";
        String username="sys";   //Oracle数据库用户名
        String password="pass";   //Oracle数据库密码
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            //2.获得数据库连接
            conn=DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false); //关闭自动提交
            //判断数据库连接是否成功
            if(conn!=null)
            {
                System.out.println("Oracle数据库间接成功");
            }else{
                System.out.println("Oracle数据库连接失败");
            }
            
            //3.创建语句对象
            // stmt=conn.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into STUDENT (LSH, CLRQ, SFZMHM, YWLX, YWYY, YWGW, CZGW, JBR, GLBM, BZ, YWBLBM, IP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            Long startTime = System.currentTimeMillis(); //获取当前时间
            PreparedStatement pst = conn.prepareStatement(sql.toString());
            for (int i = 0; i < 500; i++) {
                recordNum++;
                pst.setString(1, "java");
                String time_str = "2019-01-10 12:00:00";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    pst.setTimestamp(2, new Timestamp(sdf.parse(time_str).getTime()));
                   } catch (ParseException e) {
                    e.printStackTrace();
                   }
                pst.setString(3,"a");
                pst.setString(4,"b");
                pst.setString(5,"b");
                pst.setString(6,"c");
                pst.setString(7,"d");
                pst.setString(8,"e");
                pst.setString(9,"f");
                pst.setString(10,"h");
                pst.setString(11,"i");
                pst.setString(12,"j");
                // 把一个SQL命令加入命令列表
                pst.addBatch();
               }
               // 执行批量更新
               pst.executeBatch();
               // 语句执行完毕，提交本事务
               conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("用时：" + (endTime - startTime));
            pst.close();
            conn.close();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
  }
