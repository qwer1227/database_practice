package bookDaoExample;

public class BookDaoApp5 {
    public static void main(String[] args) throws Exception {
        //책 번호로 삭제하기
        BookDao dao = new BookDao();
        int bookNo = 1000;
        dao.deleteBookByNo(bookNo);
    }
}
