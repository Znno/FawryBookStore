import java.util.ArrayList;

public class ShippingService {
    ArrayList<Shippable>items;
    ShippingService(){
        items=new ArrayList<>();
    }
    void addItem(Shippable item){
        items.add(item);
    }
}
