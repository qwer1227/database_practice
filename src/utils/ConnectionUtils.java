package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:Xe";
    private static final String USERNAME = "hta";
    private static final String PASSWORD = "zxcv1234";

    /*
        static 초기화 블록
        - ConnectionUtils 클래스가 메모리에 로딩될떄 실행된다
     */

    static{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 데이터베이스와 연결된 CONNECTION객체를 반환한다.
     * @return 데이터베이스와 연결된 CONNECTION 객체
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
