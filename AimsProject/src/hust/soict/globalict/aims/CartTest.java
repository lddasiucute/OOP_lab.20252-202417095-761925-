package hust.soict.globalict.aims;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        
        // Create sample DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Walt Disney", 90, 20f);
        dvd1.setId(1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avengers", "Action", "Joss Whedon", 142, 25f);
        dvd2.setId(2);
        
        // Create sample Books
        Book book1 = new Book("Java Programming", "Education", 35.5f);
        book1.setId(3);
        Book book2 = new Book("Clean Code", "Technology", 45.0f);
        book2.setId(4);
        
        CompactDisc cd1 = new CompactDisc("Abbey Road", "Music", "The Beatles", 55.0f);
        cd1.setId(5);
        cd1.addTrack(new Track("Come Together", 259));
        cd1.addTrack(new Track("Something", 183));
        cd1.addTrack(new Track("Maxwell Silver Hammer", 207));
        
        cart.addMedia(dvd1);
        cart.addMedia(dvd2);
        cart.addMedia(book1);
        cart.addMedia(book2);
        cart.addMedia(cd1);
        
        System.out.println("========== Test print() method ==========");
        cart.print();
        
        System.out.println("\n========== Test searchById() method ==========");
        var results = cart.searchById(2);
        if (results.isEmpty()) System.out.println("No match found!");
        else results.forEach(System.out::println);
        
        results = cart.searchById(100);
        if (results.isEmpty()) System.out.println("No match found!");
        else results.forEach(System.out::println);
        
        System.out.println("\n========== Test searchByTitle() method ==========");
        results = cart.searchByTitle("Avengers");
        if (results.isEmpty()) System.out.println("No match found!");
        else results.forEach(System.out::println);
        
        results = cart.searchByTitle("NonExistent");
        if (results.isEmpty()) System.out.println("No match found!");
        else results.forEach(System.out::println);
        
        System.out.println("\n========== Test getTotalCost() method ==========");
        System.out.println("Total cost: " + cart.getTotalCost());
        
        System.out.println("\n========== Test removeMedia() method ==========");
        cart.removeMedia(dvd1);
        cart.print();
        
        System.out.println("\n========== Test play() for Playable media ==========");
        dvd2.play();
        cd1.play();
    }
}