package databaseExample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDatabaseExample3 {
    public static void main(String[] args) throws Exception {
        String sql = """
             select product_no, product_name, product_company, product_price
             from sample_products
             order by product_no desc
""";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe","hta","zxcv1234");
        PreparedStatement ps = conn.prepareStatement(sql);

        /*
            ResultSet
                - SELECT구문 조회결과를 담고 있고, 조회결과를 처리하는 기능이 제공
         */
        ResultSet rs = ps.executeQuery();
        /*
            boolean next()
                - ResultSet 객체에 내장된 커서를 이동시키는 메소드다
                - next() 메소드를 실행하면 커서가 한 행 아래로 이동한다
                - 해당 위치에 데이터 행이 존재하면 true를 반환한다
                - 조회 결과를 모두 처리하기 위해서는 커서를 한 행씩 아래로 이동시키면서 커서가 위치한 행에서 데이터를 뽑는다
         */
        while (rs.next()) {
            int product_no = rs.getInt("product_no");
            String product_name = rs.getString("product_name");
            String product_company = rs.getString("product_company");
            double product_price = rs.getDouble("product_price");
            System.out.println(product_no);
            System.out.println(product_name);
            System.out.println(product_company);
            System.out.println(product_price);
        }
        rs.close();
        ps.close();
        conn.close();
    }
}
