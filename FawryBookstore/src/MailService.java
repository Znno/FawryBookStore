import java.util.ArrayList;

public class MailService {
    ArrayList<Emailable>items;
    MailService(){
        items=new ArrayList<>();
    }
    void addItem(Emailable item){
        items.add(item);
    }
}

