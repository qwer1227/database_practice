package productsDaoExample;

import java.util.Date;

public class Product {
    private int no;
    private String name;
    private String company;
    private int price;
    private int stock;
    private String soldOut;
    private Date createdDate;
    private Date updatedDate;

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", soldOut='" + soldOut + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(String soldOut) {
        this.soldOut = soldOut;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
