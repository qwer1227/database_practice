package bookDaoExample;

import java.sql.SQLException;

public class BookDaoApp1 {
    public static void main(String[] args) throws SQLException {

        // 신규 도서 등록하기
        Book book = new Book();
        book.setNo(1000);
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setPublisher("한겨레 출판");
        book.setPrice(15000);
        book.setDiscountRate(0.15);
        book.setStock(20);

        BookDao bookDao = new BookDao();
        bookDao.insertBook(book);


    }
}
