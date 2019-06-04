import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {

    private Map<T, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    int getQuantity(T item) {
        Integer value = inventory.get(item);
        return value;

    }

    public void add(T item) {
        //  " adaugam un item" / increase

        int quantity = inventory.get(item);
        inventory.put(item, quantity + 1);


    }

    public boolean hasItem(T item) {
        // verificam daca este item pe stoc

        return getQuantity(item) > 0;
    }

    public void decrease(T item) {
        // "scoatem un item" / decrease
        if (hasItem(item)) {
            inventory.put(item, (inventory.get(item) - 1));
        }


    }

    public void clear() {
        //clear inventory
        inventory.clear();

    }

    public void put(T item, int quantity) {
        //put a specified quantity from a specified item

        inventory.put(item, quantity);
    }

    @Override
    public String toString() {
        return inventory + "\t";
    }
}
