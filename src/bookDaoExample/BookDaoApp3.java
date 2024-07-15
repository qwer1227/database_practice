package bookDaoExample;

public class BookDaoApp3 {
    public static void main(String[] args) throws Exception {

        //책 번호로 책정보 조회하기
        BookDao bookDao = new BookDao();

        Book book1 = bookDao.getBookByNo(1000);
        if(book1 != null) {
            System.out.println(book1.getTitle());
        } else {
            System.out.println("책 정보가 없습니다");
        }
        

        Book book2 = bookDao.getBookByNo(2000);
        if(book2 != null) {
            System.out.println(book2.getTitle());
        } else{
            System.out.println("책 정보가 없습니다");
        }
        

        Book book3 = bookDao.getBookByNo(3000);
        if(book3 != null) {
            System.out.println(book3.getTitle());
        } else {
            System.out.println("책 정보가 없습니다");
        }
        


    }
}
