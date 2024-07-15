package bookDaoExample;

public class BookDaoApp4 {
    //도서 정보 변경하기
    public static void main(String[] args) throws Exception {
        BookDao dao = new BookDao();
        int bookNo = 1000;
        Book book = dao.getBookByNo(bookNo);
        System.out.println("변경전 책 정보");
        System.out.println(book);

        //변경된 가격을 book 객체 반영
        book.setPrice(88848);
        System.out.println("변경 후 책 정보");
        System.out.println(book);
        dao.updateBook(book);

        //책 정보 수정하기
        //재고수량 = 0 , 도서상태 = 절판
        bookNo = 1000;
        Book book2 = dao.getBookByNo(bookNo);
        System.out.println("수정전 도서정보 : " + book2);
        
        book2.setStock(0);
        book2.setStatus("절판");
        dao.updateBook(book2);

        
    }
}
