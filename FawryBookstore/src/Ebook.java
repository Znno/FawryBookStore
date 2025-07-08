import java.time.LocalDate;

public class Ebook extends Book implements Emailable{
    String email="Default";
    String fileType="Default";
    @Override
    public void setEmail(String Email) {
        email=Email;
    }
    @Override
    public String getEmail() {
        return email;
    }
    Ebook (double price, LocalDate date, String isbn, String title,String email,String fileType){
        super.setPrice(price);
        super.setPublish_Year(date);
        super.setIsbn(isbn);
        super.setTitle(title);
        this.email=email;
        this.fileType=fileType;
    }
    String getFileType(){
        return this.fileType;
    }

}
