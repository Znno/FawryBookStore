import java.time.LocalDate;

public class Showcasebook extends Book{
    Showcasebook( LocalDate date, String isbn, String title){
        super.setPrice(-1);
        super.setPublish_Year(date);
        super.setIsbn(isbn);
        super.setTitle(title);
    }
}
