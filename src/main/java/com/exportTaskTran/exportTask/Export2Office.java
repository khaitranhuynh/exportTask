package com.miniproject.fisrtexportproject;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Export2Office {
    //workbook ta làm việc ~ excel file
    private Workbook wb;
    private String conurl;
    public Export2Office(Workbook wb, String conurl) {
        this.wb=wb;
        this.conurl=conurl;
    }
    /**
     * Export 1 result set ra file excel
     * @param rs là result set cần xuất
     * @param sheetName là tên của sheet trong workbook
     * @throws Exception
     */
    public void Export(ResultSet rs, String sheetName)throws Exception{
        int nos=wb.getNumberOfSheets();
        for (int i = 0; i < nos; i++) {
            String sn=wb.getSheetName(i);
            if(sn.equalsIgnoreCase(sheetName)){
                sheetName+="$1";
            }
        }
        //tạo worksheet
        Sheet sheet = wb.createSheet(sheetName);
        //Lấy metadata làm column header
        ResultSetMetaData rsmd=rs.getMetaData();
        //tạo dòng tiêu đề
        Row row = sheet.createRow((short)0);
        int colNum=rsmd.getColumnCount();//số fields có trong bảng
        //duyệt qua các fields lấy làm column header
        for (int i = 0; i < colNum; i++) {
            String col=rsmd.getColumnName(i+1);
            Cell cell=row.createCell(i);
            cell.setCellValue(col);
        }
        //điền giá trị vào các hàng
        while(rs.next()){
            //tạo 1 hàng mới
            row=sheet.createRow(rs.getRow());
            for (int i = 0; i < colNum; i++) {
                String val=rs.getString(i+1);
                //tạo 1 ô mới
                Cell cell=row.createCell(i);
                cell.setCellValue(val);
            }
        }

    }
    /**
     * Lấy tất cả các bảng của cơ sở dữ liệu sau đó export ra thành các sheet trong 1 file excel
     * @param excelFilePath là đường dẫn đến file excel kết xuất
     * @throws Exception
    */
    public void ExportAllDBFiles(String excelFilePath)throws Exception{
        ConnectDB condb=new ConnectDB(conurl);
        Connection con=condb.getConnection();

        DatabaseMetaData dbm=con.getMetaData();
        String []types={"TABLE"};
        ResultSet rsm= dbm.getTables(null, null, null, types);
        while(rsm.next()){
            String tableName=rsm.getString("TABLE_NAME");
            ResultSet rs=condb.getTable(tableName);
            Export(rs,tableName);
        }
        //Ghi dữ liệu xuống file
        FileOutputStream fos=new FileOutputStream(excelFilePath,true);
        wb.write(fos);
        fos.close();
        condb.CloseConnection();
    }
    /**
     * Export 1 table
     * @param tableName là bảng cần export
     * @param excelFilePath đường dẫn đến file excel cần tạo
     * @throws Exception
     */
    public void ExportTable(String tableName,String excelFilePath)throws Exception{
        ConnectDB condb=new ConnectDB(conurl);
        ResultSet rs=condb.getTable(tableName);
        Export(rs,tableName);
        //Ghi dữ liệu xuống file
        String fileName=excelFilePath;
        FileOutputStream fos=new FileOutputStream(fileName,true);
        wb.write(fos);
        fos.close();
        condb.CloseConnection();
    }
}