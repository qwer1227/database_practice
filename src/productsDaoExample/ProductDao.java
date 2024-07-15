package productsDaoExample;

import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    /***
     *  1. 신규 상품정보를 저장하는 기능 정의하기
     * 		상품번호, 상품이름, 제조회사, 가격, 재고수량정보를 Product객체로 전달받아서 저장한다.
     * @param product
     * @throws SQLException
     */
    public void insertProduct(Product product) throws SQLException {
        String sql = """
                    insert into sample_products(product_no, product_name, product_company, product_price, product_stock)
                    values(?,?,?,?,?)
                """;

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,product.getNo());
        ps.setString(2,product.getName());
        ps.setString(3, product.getCompany());
        ps.setDouble(4,product.getPrice());
        ps.setInt(5,product.getStock());

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    /***
     * 2. 저장된 모든 상품정보를 조회해서 반환하는 기능 정의하기
     * 		SAMPLE_PRODUCTS 테이블의 모든 정보를 조회해서 List<Product>로 반환한다.
     * @return
     * @throws SQLException
     */
    public List<Product> getALlProducts() throws SQLException {
        String sql = """
                    select *
                    from sample_products
                    order by product_no
                """;

        List<Product> products = new ArrayList<Product>();

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Product product = new Product();
            product.setNo(rs.getInt("product_no"));
            product.setName(rs.getString("product_name"));
            product.setCompany(rs.getString("product_company"));
            product.setPrice(rs.getInt("product_price"));
            product.setStock(rs.getInt("product_stock"));
            product.setSoldOut(rs.getString("product_soldout"));
            product.setCreatedDate(rs.getDate("product_created_date"));
            product.setUpdatedDate(rs.getDate("product_updated_date"));

            products.add(product);


        }
        rs.close();
        ps.close();
        conn.close();

        return products;
    }

    /***
     * 3. 상품번호를 전달받아서 해당 상품정보를 반환하는 기능 정의하기
     * 		상품번호를기본키 컬럼으로 생각하고 한 건만 조회해서 Product로 반환한다.
     * @param no
     * @return
     * @throws SQLException
     */
    public Product getProductByNo(int no) throws SQLException {
        String sql = """
                Select *
                from sample_products
                where product_no = ?
                """;

        Product product = new Product();

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,no);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            product.setNo(rs.getInt("product_no"));
            product.setName(rs.getString("product_name"));
            product.setCompany(rs.getString("product_company"));
            product.setPrice(rs.getInt("product_price"));
            product.setStock(rs.getInt("product_stock"));
            product.setSoldOut(rs.getString("product_soldout"));
            product.setCreatedDate(rs.getDate("product_created_date"));
            product.setUpdatedDate(rs.getDate("product_updated_date"));
        }
        rs.close();
        ps.close();
        conn.close();

        return product;
    }

    /***
     * 4. 상품번호를 전달받아서 해당 상품정보 삭제하는 기능 정의하기
     * @param no
     * @throws SQLException
     */
    public void deleteProductByNo(int no) throws SQLException {
        String sql = """
                    delete from sample_products
                    where product_no = ?
                """;

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,no);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    /***
     * 5. 모든 상품정보를 삭제하는 기능 정의하기
     * @throws SQLException
     */
    public void deleteAllProducts() throws SQLException {
        String sql = """
                    delete from sample_products
                """;

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    /***
     * 6. 키워드를 전달받아서 상품명에 해당 키워드가 포함되어 있는 상품정보를 조회해서 List<Product>로 반한한다.
     * @param keyword
     * @return
     * @throws SQLException
     */
    public List<Product> getProductsByKeyword(String keyword) throws SQLException {
        String sql = """
                    select *
                    from sample_products
                    where product_name like '%' || ? || '%'
                """;

        List<Product> products = new ArrayList<>();

        Connection conn = ConnectionUtils.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,keyword);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Product product = new Product();
            product.setNo(rs.getInt("product_no"));
            product.setName(rs.getString("product_name"));
            product.setCompany(rs.getString("product_company"));
            product.setPrice(rs.getInt("product_price"));
            product.setStock(rs.getInt("product_stock"));
            product.setSoldOut(rs.getString("product_soldout"));

            products.add(product);
        }
        rs.close();
        ps.close();
        conn.close();

        return  products;
    }
}
