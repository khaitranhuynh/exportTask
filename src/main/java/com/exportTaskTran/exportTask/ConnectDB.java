package com.miniproject.fisrtexportproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectDB {
    
    private Connection con;
    private String conurl;
   
    public ConnectDB(String myUrl) throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        this.conurl = myUrl;
        String url="***";
        con=DriverManager.getConnection(conurl);
        if (con != null) {
            System.out.println("Connected");
        }
    }
    /**
     * Lấy 1 bảng cơ sở dữ liệu
     * @param tableName là bảng cơ sở dữ liệu cần lấy
     * @return
     * @throws Exception
     */
    public ResultSet getTable(String tableName)throws Exception{
        String sql="select * from "+tableName;
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    /**
     * Lấy 1 resultset từ  câu truy vấn
     * @param mySql là câu truy vấn
     * @return
     * @throws Exception
     */
    public ResultSet getTableFromSQL(String mySql)throws Exception{
        PreparedStatement pstmt=con.prepareStatement(mySql);
        return pstmt.executeQuery();
    }
    /**
     * Lấy 1 connection
     * @return
     */
    public Connection getConnection(){
        return con;
    }
    /**
     * Đóng kết nối
     * @throws Exception
     */
    public void CloseConnection()throws Exception{
        if(con!=null)
            con.close();
    }
}