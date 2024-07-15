package bookDaoExample;

import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    Sample_Books 테이블에 대한 CRUD 작업이 구현된 클래스

    - 신규도서 추가 기능 ex) 추가기능은 반환타입이 void고 사용자의 입력한 내용을 객체로 담아서 매개변수로 받아야함
        + 반환타입 : void
        + 매개변수 : Book book
        + 메소드명 : insertBook
    - 전체 도서 조회 기능 ex) 전체조회라 모든 객체가 뿌려져야 해서 list타입
        + 반환타입 : List<Book>
        + 매개변수 : 없음
        + 메소드명 : findAllBooks
    - 특정 도서 조회 기능 ex) 사용자가 원하는 책의 번호에 따른 도서를 조회하니까 한객체만 조회할거라 list타입이 아님
        + 반환타입 : Book
        + 매개변수 : int bookNo
        + 메소드명 : getBookByNo
    - 도서정보 변경 기능 ex) 변경할거니까 변경할 book객체를 전달받아야함
        + 반환타입 : void
        + 매개변수 : Book book
        + 메소드명 : updateBook
    - 도서정보 삭제 기능
        + 반환타입 : void
        + 매개변수 : int bookNo
        + 메소드명 : deleteBookByNo
    - 도서정보 제목검색 기능
        + 반환타입 : List<Book>
        + 매개변수 : String title
        + 메소드명 : getBooksByTitle
    - 도서정보 저자검색 기능
        + 반환타입 : List<Book>
        + 매개변수 : String author
        + 메소드명 : getBooksByAuthor
    - 도서정보 출판사검색 기능
        + 반환타입 : List<Book>
        + 매개변수 : String publisher
        + 메소드명 : getBooksByPublisher
    - 도서정보 가격검색 기능
        + 반환타입 : List<Book>
        + 매개변수 : int minPrice, maxPrice
        + 메소드명 : getBooksByPrice
 */
public class BookDao {

    /***
     * 신규 도서정보를 전달받아서 테이블에 저장시킨다
     * @param book 신규 도서 정보
     */
    public void insertBook(Book book) throws SQLException {
        String sql = """
                    INSERT INTO SAMPLE_BOOKS
                    (book_no, book_title, book_author, book_publisher, book_price, book_discount_rate, book_stock)
                    values
                    (?,?,?,?,?,?,?)
                """;

        Connection conn = ConnectionUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,book.getNo());
        ps.setString(2,book.getTitle());
        ps.setString(3,book.getAuthor());
        ps.setString(4,book.getPublisher());
        ps.setDouble(5,book.getPrice());
        ps.setDouble(6,book.getDiscountRate());
        ps.setInt(7,book.getStock());
        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    public List<Book> findAllBooks() throws SQLException {
        String sql = """
                    select book_no, book_title, book_author, book_publisher, book_price, book_discount_rate, book_stock, book_status, book_created_date, book_updated_date
                    from sample_books
                    order by book_no desc
                """;

        List<Book> books = new ArrayList<>();

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery(); // 커서는 맨위 행부터 가리키고있음 그래서 .next 를 이용해서 커서를 내려줘서 값이 있으면 진행되게 해야함

        while(rs.next()) {
            int bookNo = rs.getInt("book_no");
            String bookTitle = rs.getString("book_title");
            String bookAuthor = rs.getString("book_author");
            String bookPublisher = rs.getString("book_publisher");
            int bookPrice = rs.getInt("book_price");
            double bookDiscountRate = rs.getDouble("book_discount_rate");
            int bookStock = rs.getInt("book_stock");
            String bookStatus = rs.getString("book_status");
            Date bookCreatedDate = rs.getDate("book_created_date");
            Date bookUpdatedDate = rs.getDate("book_updated_date");

            Book book = new Book();

            book.setNo(bookNo);
            book.setTitle(bookTitle);
            book.setAuthor(bookAuthor);
            book.setPublisher(bookPublisher);
            book.setPrice(bookPrice);
            book.setDiscountRate(bookDiscountRate);
            book.setStock(bookStock);
            book.setStatus(bookStatus);
            book.setCreatedDate(bookCreatedDate);
            book.setUpdatedDate(bookUpdatedDate);

            books.add(book);

        }
        rs.close();
        ps.close();
        conn.close();


        return books;
    }

    /***
     * 책번호를 전달받아서 해당 책정보를 반환한다
     * @param bookNo 조회할 책 번호
     * @return 책정보 , null이 반환될 수 있다
     * @throws SQLException
     */
    public Book getBookByNo(int bookNo) throws SQLException {
            String sql = """
                    select *
                    from sample_books
                    where book_no = ?
                    """;

            Book book = null;

            Connection conn = ConnectionUtils.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,bookNo);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                book = new Book();
                book.setNo(rs.getInt("book_no"));
                book.setTitle(rs.getString("book_title"));
                book.setAuthor(rs.getString("book_author"));
                book.setPublisher(rs.getString("book_publisher"));
                book.setPrice(rs.getInt("book_price"));
                book.setDiscountRate(rs.getDouble("book_discount_rate"));
                book.setStock(rs.getInt("book_stock"));
                book.setStatus(rs.getString("book_status"));
                book.setCreatedDate(rs.getDate("book_created_date"));
                book.setUpdatedDate(rs.getDate("book_updated_date"));
            }

            rs.close();
            ps.close();
            conn.close();

            return book;
    }

    /***
     * 변경된 도서정보를 전달받아서 해당 도서정보를 바꾼다.
     * @param book 변경된 도서정보
     * @throws SQLException
     */
    public void updateBook(Book book) throws SQLException {
        String sql = """
                    update sample_books
                    set book_title = ?
                    ,   book_author = ?
                    ,   book_publisher = ?
                    ,   book_price = ?
                    ,   book_discount_rate = ?
                    ,   book_stock = ?
                    ,   book_status = ?               
                    ,   book_updated_date = sysdate
                    where book_no = ?
                """;

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,book.getTitle());
        ps.setString(2,book.getAuthor());
        ps.setString(3,book.getPublisher());
        ps.setDouble(4,book.getPrice());
        ps.setDouble(5,book.getDiscountRate());
        ps.setInt(6,book.getStock());
        ps.setString(7,book.getStatus());
        ps.setInt(8,book.getNo());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void deleteBookByNo(int no) throws SQLException{

        String sql = """
                    delete from sample_books
                    where book_no = ?
                """;

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,no);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public List<Book> getBooksByTitle(String keyword) throws SQLException{
            String sql = """
                    Select *
                    from sample_books
                    where book_title like '%' || ? || '%'
                    """;

            List<Book> books = new ArrayList<>();

            Connection conn = ConnectionUtils.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,keyword);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Book book = new Book();
                extracted(book, rs);
                books.add(book);
            }
            rs.close();
            ps.close();
            conn.close();

            return books;
    }

    private void extracted(Book book, ResultSet rs) throws SQLException {
        book.setNo(rs.getInt("book_no"));
        book.setTitle(rs.getString("book_title"));
        book.setAuthor(rs.getString("book_author"));
        book.setPublisher(rs.getString("book_publisher"));
        book.setPrice(rs.getInt("book_price"));
        book.setDiscountRate(rs.getDouble("book_discount_rate"));
        book.setStock(rs.getInt("book_stock"));
        book.setStatus(rs.getString("book_status"));
        book.setCreatedDate(rs.getDate("book_created_date"));
        book.setUpdatedDate(rs.getDate("book_updated_date"));
    }
}
