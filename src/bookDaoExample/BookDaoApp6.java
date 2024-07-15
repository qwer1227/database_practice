package bookDaoExample;

import java.util.List;

public class BookDaoApp6 {
    public static void main(String[] args) throws Exception {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getBooksByTitle("자바");
        for (Book book : books) {
            System.out.println(book.getNo());
            System.out.println(book.getTitle());
            System.out.println(book.getAuthor());
            System.out.println();
        }
    }
}
