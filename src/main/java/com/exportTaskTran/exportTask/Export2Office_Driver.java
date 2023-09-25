package com.miniproject.fisrtexportproject;

import java.io.FileOutputStream;
import java.sql.ResultSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export2Office_Driver {

    //public static void main(String[] args) throws Exception{
    private String mySql;
    private String myConn;
    private String myFileName;

    // contructor
    public Export2Office_Driver() {

    }
    public void setmySql(String mySql) {
        this.mySql = mySql;
    }

    public String getmySql() {
        return mySql;
    }

    public void setmyConn(String myConn) {
        this.myConn = myConn;
    }

    public String getmyConn() {
        return myConn;
    }

    public void setmyFileName(String myFileName) {
        this.myFileName = myFileName;
    }

    public String getmyFileName() {
        return myFileName;
    }

    public Export2Office_Driver(String mySql, String myConn, String fileName) {
        this.mySql = mySql;
        this.myConn = myConn;
        this.myFileName = fileName;
    }

    public void run() throws Exception {
        System.out.println("waiting for export...");
        long before=System.currentTimeMillis();

        //demo 01-export toàn bộ cơ sở dữ liệu thành 1 file excel với mỗi bảng là 1 sheet

        //demo 02- export 1 bảng ra file excel

        //demo 03-export kết quả của 1 câu seleect sql bất kỳ ra excel
        //Workbook wb3=new HSSFWorkbook(); -- file exel 2003
        myConn = "jdbc:oracle:thin:tgdd_news/44662288@10.1.12.138:1521:pdbbeta";
        Workbook wb3=new XSSFWorkbook();
        ConnectDB condb=new ConnectDB(myConn);
        Export2Office ep3=new Export2Office(wb3, myConn);
        //String sql="select * from tgdd_news.product where rownum < 3";
        ResultSet rs=condb.getTableFromSQL(mySql);
        ep3.Export(rs, "Join");
        FileOutputStream fos=new FileOutputStream(myFileName,true);
        wb3.write(fos);
        fos.close();
        condb.CloseConnection();

        //finish
        long after=System.currentTimeMillis();
        System.out.println("finished after "+(after-before)+" milliseconds");
    }
}
