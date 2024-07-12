package crudExample;

import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateExample {
    public static void main(String[] args) throws Exception{
        /*
            도서정보 변경하기

            100번 도서의 도서가격, 할인율, 재고수량을 변경하기

            UPDATE SAMPLE_BOOKS

            SET
                BOOK_PRICE = ?,
                BOOK_DISCOUNT_RATE = ?,
                BOOK_STOCK = ?
            WHERE BOOK_NO = ?
         */

        // 1. UPDATE 쿼리 작성하기
        String sql = """
                    UPDATE SAMPLE_BOOKS
                    SET BOOK_PRICE = ?, BOOK_DISCOUNT_RATE = ?, BOOK_STOCK = ?
                    WHERE BOOK_NO = ?
                """;

        // 2. Connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = ConnectionUtils.getConnection();

        // 3. PreparedStatement
        PreparedStatement ps = conn.prepareStatement(sql);

        // 4. UPDATE QUERY 작성
        ps.setInt(1,40000);
        ps.setDouble(2,0.25);
        ps.setDouble(3,20);
        ps.setInt(4,100);

        // 5. 변경된 쿼리 실행
        int countQuery = ps.executeUpdate();
        System.out.println(countQuery);

        // 6. 사용했던 자원 반납
        ps.close();
        conn.close();
    }
}
