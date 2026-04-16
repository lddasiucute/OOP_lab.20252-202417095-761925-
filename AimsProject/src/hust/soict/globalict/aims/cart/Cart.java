package hust.soict.globalict.aims.cart;

import java.util.ArrayList;
import hust.soict.globalict.aims.media.Media;

public class Cart {
    private ArrayList<Media> items = new ArrayList<>();

    public void addMedia(Media m) {
        if (!items.contains(m)) items.add(m);
    }

    public void removeMedia(Media m) {
        items.remove(m);
    }

    public ArrayList<Media> getItemsOrdered() {
        return items;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        
        int index = 1;
        for (Media m : items) {
            System.out.println(index + ". " + m.toString());
            index++;
        }
        
        System.out.println(String.format("Total cost: %.1f $", getTotalCost()));
        System.out.println("***************************************************");
    }

    public ArrayList<Media> searchById(int id) {
        ArrayList<Media> results = new ArrayList<>();
        for (Media m : items) {
            if (m.getId() == id) {
                results.add(m);
            }
        }
        return results;
    }

    public ArrayList<Media> searchByTitle(String title) {
        ArrayList<Media> results = new ArrayList<>();
        for (Media m : items) {
            if (m.isMatch(title)) {
                results.add(m);
            }
        }
        return results;
    }

    public float getTotalCost() {
        float total = 0;
        for (Media m : items) {
            total += m.getCost();
        }
        return total;
    }
}