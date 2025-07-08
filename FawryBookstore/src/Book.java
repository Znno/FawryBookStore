import java.time.LocalDate;

public class Book {
    String title="Default";
    String ISBN="Default";
    LocalDate publish_Year=LocalDate.now();
    double price=0;
    void setPrice(double Price){
        this.price=Price;
    }
    double getPrice(){
        return this.price;
    }
    void setTitle(String Title){
        this.title=Title;
    }
    String getTitle(){
        return this.title;
    }
    void setIsbn(String ISBN){
        this.ISBN=ISBN;
    }
    String getIsbn(){
        return this.ISBN;
    }
    void setPublish_Year(LocalDate Publish_Year){
        this.publish_Year=Publish_Year;
    }
    LocalDate getPublish_Year(){
        return this.publish_Year;
    }



}
