package com.miniproject.fisrtexportproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.miniproject")
@SpringBootApplication
public class FisrtexportprojectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FisrtexportprojectApplication.class, args);
        //  String query = "Select product.productid, product_language.html from tgdd_news.Product_Language join tgdd_news.product on product.productid = Product_Language.productid where Product_Language.htmldescription is not null and rownum < 4";
        //        String url="jdbc:oracle:thin:tgdd_news/44662288@10.1.12.138:1521:pdbbeta";
        //        String fname = "E:\\dba\\java\\exceltest\\trantest6.xlsx";
        //        Export2Office_Driver exportFile = new Export2Office_Driver(query,url,fname);
        //        exportFile.run();
        //        System.out.println("Line1");so luong column ko duoc qua lon

    }

}
