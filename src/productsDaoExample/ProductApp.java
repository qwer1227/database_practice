package productsDaoExample;

import java.util.List;

public class ProductApp {
    public static void main(String[] args) throws Exception {
        ProductDao dao = new ProductDao();
        Product product = new Product();

//         1
//        product.setNo(1);
//        product.setName("갤럭시북");
//        product.setCompany("삼성");
//        product.setPrice(2000000);
//        product.setStock(5);
//
//        dao.insertProduct(product);

        // 2
//        List<Product> products = dao.getALlProducts();
//        for (Product product1 : products) {
//            System.out.println("product_no : "+product1.getNo());
//            System.out.println("product_name : "+product1.getName());
//            System.out.println("product_price : "+product1.getPrice());
//            System.out.println("product_company : "+product1.getCompany());
//            System.out.println("product_stock : "+product1.getStock());
//            System.out.println("product_soldout : "+product1.getSoldOut());
//            System.out.println("product_created_date : "+product1.getCreatedDate());
//            System.out.println("product_updated_date : "+product1.getUpdatedDate());
//            System.out.println();
//        }

        // 3
//        int productNo = 1;
//        Product product2 = new Product();
//        product2 = dao.getProductByNo(productNo);
//
//        System.out.println("product_no : " + product2.getNo());
//        System.out.println("product_name : " + product2.getName());
//        System.out.println("product_price : " + product2.getPrice());

        // 4
//        int productNo = 1;
//        dao.deleteProductByNo(productNo);

        // 5
//        dao.deleteAllProducts();

        // 6
        String keyword = "맥북";
        List<Product> products = dao.getProductsByKeyword(keyword);
        for (Product product1 : products) {
            System.out.println(product1.getNo());
            System.out.println(product1.getName());
            System.out.println(product1.getPrice());

            System.out.println("------------------");
        }
    }
}
