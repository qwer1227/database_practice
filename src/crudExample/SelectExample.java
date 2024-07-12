package crudExample;

import utils.ConnectionUtils;

import java.sql.*;

public class SelectExample {
    public static void main(String[] args) throws Exception {
        /*
            책정보 조회하기

            번호, 제목, 저자, 출판사, 가격, 상태, 재고수량을 조회하기

            SELECT BOOK_NO, BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE, BOOK_STATUS, BOOK_STOCK
            FROM SAMPLE_BOOKS
            ORDER BY BOOK_NO DESC
         */

        // 1. SQL작성
        String sql = """
                    SELECT BOOK_NO, BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE, BOOK_STATUS, BOOK_STOCK
                                FROM SAMPLE_BOOKS
                                ORDER BY BOOK_NO DESC
                """;

        // 2. Connection
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = ConnectionUtils.getConnection();

        // 3. PreparedStatement 작성
        PreparedStatement ps = conn.prepareStatement(sql);

        // 4. ResultSet
        ResultSet rs = ps.executeQuery();

        // 5. while문을 이용해 한 행씩 모든 열을 출력
        while (rs.next()) {
            int bookNo = rs.getInt("BOOK_NO");
            String bookTitle = rs.getString("BOOK_TITLE");
            String bookAuthor = rs.getString("BOOK_AUTHOR");
            String bookPrice = rs.getString("BOOK_PRICE");
            String bookStatus = rs.getString("BOOK_STATUS");
            int bookStock = rs.getInt("BOOK_STOCK");
            System.out.println("bookNo: " + bookNo);
            System.out.println("bookTitle: " + bookTitle);
            System.out.println("bookAuthor: " + bookAuthor);
            System.out.println("bookPrice: " + bookPrice);
            System.out.println("bookStatus: " + bookStatus);
            System.out.println("bookStock: " + bookStock);
            System.out.println("------------------------");
        }

        // 6. 사용했던 자원 반납
        rs.close();
        ps.close();
        conn.close();


    }
}
