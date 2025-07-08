import java.time.LocalDate;

public class Paperbook extends Book implements Shippable{
    String address="Default";
    int quantity=1;
    @Override
    public void setShippingAddress(String Address) {
        address=Address;
    }

    @Override
    public String getShippingAddress() {
        return address;
    }
    Paperbook (int quantity, double price, LocalDate date,String isbn,String title){
        super.setPrice(price);
        super.setPublish_Year(date);
        super.setIsbn(isbn);
        super.setTitle(title);
        this.quantity=quantity;
    }
}
