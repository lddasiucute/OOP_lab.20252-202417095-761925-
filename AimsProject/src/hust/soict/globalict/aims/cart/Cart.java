package hust.soict.globalict.aims.cart;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public static final int MAX_NUMBERS_ORDERED = 20;

public void addMedia(Media m) throws LimitExceededException {
    if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
        throw new LimitExceededException(
            "ERROR: The number of media has reached its limit (" + MAX_NUMBERS_ORDERED + ")");
    }
    if (!itemsOrdered.contains(m)) {
        itemsOrdered.add(m);
    }
}

    public void removeMedia(Media m) {
        itemsOrdered.remove(m);
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        int index = 1;
        for (Media m : itemsOrdered) {
            System.out.println(index + ". " + m.toString());
            index++;
        }

        System.out.println(String.format("Total cost: %.1f $", getTotalCost()));
        System.out.println("***************************************************");
    }

    public ObservableList<Media> searchById(int id) {
        ObservableList<Media> results = FXCollections.observableArrayList();
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                results.add(m);
            }
        }
        return results;
    }

    public ObservableList<Media> searchByTitle(String title) {
        ObservableList<Media> results = FXCollections.observableArrayList();
        for (Media m : itemsOrdered) {
            if (m.isMatch(title)) {
                results.add(m);
            }
        }
        return results;
    }

    public float getTotalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }
}