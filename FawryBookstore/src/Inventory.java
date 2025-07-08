import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory {
    ArrayList<Book> books=new ArrayList<>();
    ArrayList<Book> outdatedBooks=new ArrayList<>();
    int OUTDATED_DIFFERENCE=10;
    public void addBook(Book book){
        books.add(book);
    }
    double buyBook(String isbn,int quantity){
       filterOutdatedBooks();
       if(quantity<=0)
           return -2;
       for(int i=0;i<books.size();i++){
            if(books.get(i).getIsbn().equals(isbn)){
                if(books.get(i) instanceof Paperbook) {
                    Paperbook pb = (Paperbook) books.get(i);
                    if(pb.quantity>=quantity) {
                        pb.quantity -= quantity;
                        Paperbook tobeShipped=new Paperbook(quantity,pb.getPrice(),pb.getPublish_Year(),pb.getIsbn(),pb.getTitle());
                        tobeShipped.setShippingAddress(pb.getShippingAddress());
                        ShippingService shippingService=new ShippingService();
                        shippingService.addItem(tobeShipped);
                        if(pb.quantity==0)
                            removeBook(isbn);
                        return quantity*pb.price;

                    }
                    else{
                        return -2;
                    }
                }
                else if(books.get(i) instanceof Ebook){
                    Ebook eb = (Ebook) books.get(i);

                        MailService mailService=new MailService();
                    mailService.addItem(eb);
                    return eb.getPrice();

                }
                else if(books.get(i) instanceof Showcasebook){
                    return -1;
                }
            }
       }

       return -2;
    }
    public ArrayList<Book> getBooks(){
        return books;
    }
    public boolean removeBook(String isbn){
        for(int i=0;i<books.size();i++){
            if(books.get(i).getIsbn().equals(isbn)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean reduceQuantity(String  isbn,int num){

            for(int i=0;i<books.size();i++){
                if(books.get(i).getIsbn().equals(isbn)){
                        if(books.get(i) instanceof Paperbook) {
                            Paperbook pb = (Paperbook) books.get(i);
                            if(pb.quantity>=num) {
                                pb.quantity -= num;
                                if(pb.quantity==0)
                                    removeBook(isbn);
                                return true;
                            }
                            else return false;
                        }
                        else
                            return true;


                }
            }
        return false;
    }
    void filterOutdatedBooks()
    {
        for(int i=0;i<books.size();i++)
        {
            LocalDate d1=books.get(i).getPublish_Year();
            LocalDate d2=LocalDate.now();
            if(d1.plusYears(OUTDATED_DIFFERENCE).isBefore(d2))
            {
                outdatedBooks.add(books.get(i));
            }
        }
        for(int i=0;i<outdatedBooks.size();i++){
                books.remove(outdatedBooks.get(i));

        }
    }
    public ArrayList<Book> getOutdatedBooks(){
        return outdatedBooks;
    }



}
