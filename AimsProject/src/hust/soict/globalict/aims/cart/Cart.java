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
        System.out.println("CART:");
        for (Media m : items) System.out.println(m);
    }
}