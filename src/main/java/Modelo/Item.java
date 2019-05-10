package Modelo;

public class Item {

    String id;
    String site_id;
    String title;
    String subtitle ;
    int seller_id;
    String category_id;
    double price;
    String currency_id;
    int available_quantity;
    String condition;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", site_id='" + site_id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", seller_id=" + seller_id +
                ", category_id='" + category_id + '\'' +
                ", price=" + price +
                ", currency_id='" + currency_id + '\'' +
                ", available_quantity=" + available_quantity +
                ", condition='" + condition + '\'' +
                '}';
    }
}
