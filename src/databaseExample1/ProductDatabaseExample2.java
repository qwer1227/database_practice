package databaseExample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ProductDatabaseExample2 {
    public static void main(String[] args) throws Exception{

        String sql = """
            insert into sample_products (product_no, product_name, product_company, product_price)
             values(?,?,?,?)
""";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe","hta","zxcv1234");

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,100);          //product_no > number(4,0)
        ps.setString(2,"맥북 프로");   //product_name > varchar(255)
        ps.setString(3,"애플");      //product_company > varchar(255)
        ps.setInt(4,2000000);      //product_price > number(7,0)

        int rowCount = ps.executeUpdate();
        System.out.println(rowCount + " rows inserted");

        ps.close();
        conn.close();
    }
}
