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

    public void removeAuthor(String a) {
        if (authors.contains(a)) {
            authors.remove(a);
        }
    }

    @Override
    public String toString() {
        String authorList = String.join(", ", authors);
        return "Book - " + title + " - " + category + " - Authors: [" + authorList + "]: " + cost + " $";
    }

    
    public String getCategory() {
        return category;
    }
}