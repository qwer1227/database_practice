package bookDaoExample;

import java.util.List;

public class BookDaoApp2 {
    public static void main(String[] args) throws Exception {

        // 모든 도서정보 조회해서 출력하기
        BookDao bookDao = new BookDao();

        List<Book> books = bookDao.findAllBooks();

        if (books.isEmpty()) {
            System.out.println("도서 정보가 존재하지 않습니다");
        } else {
            for (Book book : books) {
                System.out.println(book.getNo() + ", " + book.getTitle() + ", " + book.getAuthor());
            }
        }
    }
}
