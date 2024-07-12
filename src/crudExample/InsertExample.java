package crudExample;

import utils.ConnectionUtils;

import java.sql.*;

public class InsertExample {
    public static void main(String[] args) throws Exception{
        /*
            신규 도서 등록하기

            번호    제목      저자    출판사    가격    할인율
            100 자바의 정석   남궁성  도우출판사 30000    0.15
            102 이것이 자바다  신용권  한빛미디어 28000   0.15
         */

        int no = 102;
        String title = "이것이 자바다";
        String author = "신용권";
        String publisher = "한빛미디어";
        int price = 28000;
        double discountRate = 0.15;

        // 1. INSERT SQL 정의
        String sql = """
                insert into SAMPLE_BOOKS(BOOK_NO,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_PRICE,BOOK_DISCOUNT_RATE)
                values(?,?,?,?,?,?)
                """;
        // 2. Connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = ConnectionUtils.getConnection();

        // 3. PreparedStatement 획득
        PreparedStatement ps = conn.prepareStatement(sql);
        // 4. ?에 값 바인딩
        ps.setInt(1,no);
        ps.setString(2,title);
        ps.setString(3,author);
        ps.setString(4,publisher);
        ps.setInt(5,price);
        ps.setDouble(6,discountRate);
        // 5. SQL을 데이터베이스로 전송하고 실행시키기
        int query = ps.executeUpdate();
        System.out.println(query + "행 추가 됨");
        // 6. 사용했던 자원 반납하기
        ps.close();
        conn.close();
    }
}
