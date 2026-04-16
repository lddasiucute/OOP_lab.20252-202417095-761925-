package hust.soict.globalict.aims.store;

import java.util.ArrayList;
import hust.soict.globalict.aims.media.Media;

public class Store {
    private ArrayList<Media> items = new ArrayList<>();

    public void addMedia(Media m) {
        items.add(m);
    }

    public void removeMedia(Media m) {
        items.remove(m);
    }

    public ArrayList<Media> getItemsInStore() {
        return items;
    }

    public void print() {
        for (Media m : items) System.out.println(m);
    }
}