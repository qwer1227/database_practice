package databaseExample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDatabaseExample1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. OracleDriver를 메모리에 로딩시킨다.
        /*
            - OracleDriver내의 정적 초기화 블록의 수행문이 실행된다
            - JVM의 드라이버 레지스트리에 url과 OracleDriver를 등록시킨다
         */
        Class.forName("oracle.jdbc.OracleDriver");

        // 2. url, username, password를 지정해서 데이터베이스와 연결된 Connection객체를 획득한다
        /*
            - url, username, password 정보를 설정한다
            - url에는 jdbc 드라이버URL과 접속정보가 포함되어 있다
            - DriverManager 클래스의 getConnection() 메소드는 드라이버 레지스트리에서 url에서 지정한
              Driver정보를 가져온다 해당 Driver가 제공하는 라이브러리를 이용해서 데이터베이스와
              연결된 Connection 구현객체를 생성한다
         */
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hta";
        String password = "zxcv1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
        // 3. SQL구문을 데이터베이스로 전송하고 실행하는 Statement객체를 획득한다
        /*
            - Connection 객체의 createStatement() 메소드를 실행해서
              Statement 인터페이스를 구현한 구현객체를 획득한다
         */
        Statement stmt = connection.createStatement();

        //4. SQL을 전송하고, 실행시킨다
        int rowCount = stmt.executeUpdate("delete from sample_products");
        System.out.println(rowCount);

        //5. 데이터베이스 엑세스 작업에 사용했던 자원을 반납한다
        stmt.close();
        connection.close();

    }
}
