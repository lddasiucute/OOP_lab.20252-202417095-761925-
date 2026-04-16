package hust.soict.globalict.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    public Book(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public void addAuthor(String a) {
        if (!authors.contains(a)) authors.add(a);
    }

    public String toString() {
        return "Book - " + title + " - " + cost;
    }
}