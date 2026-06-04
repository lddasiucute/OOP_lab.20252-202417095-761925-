package hust.soict.globalict.aims.screen.customer;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Track;
import hust.soict.globalict.aims.screen.customer.controller.ViewStoreController;
import hust.soict.globalict.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aims extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/globalict/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        fxmlLoader.setController(new ViewStoreController(store, cart));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        // -------- Sample data --------
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
            "Harry Potter and the Philosopher's Stone (2001)",
            "Fantasy", "Chris Columbus", 152, 3.0f);
        dvd1.setId(1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
            "Harry Potter and the Chamber of Secrets (2002)",
            "Fantasy", "Chris Columbus", 161, 3.5f);
        dvd2.setId(2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
            "Harry Potter and the Prisoner of Azkaban (2004)",
            "Fantasy", "Alfonso Cuaron", 142, 5.0f);
        dvd3.setId(3);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(
            "Harry Potter and the Goblet of Fire (2005)",
            "Fantasy", "Mike Newell", 157, 4.5f);
        dvd4.setId(4);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc(
            "Harry Potter and the Order of the Phoenix (2007)",
            "Fantasy", "David Yates", 138, 6.5f);
        dvd5.setId(5);
        DigitalVideoDisc dvd6 = new DigitalVideoDisc(
            "Harry Potter and the Half-Blood Prince (2009)",
            "Fantasy", "David Yates", 153, 5.8f);
        dvd6.setId(6);
        DigitalVideoDisc dvd7 = new DigitalVideoDisc(
            "Harry Potter and the Deathly Hallows - Part 1 (2010)",
            "Fantasy", "David Yates", 146, 6.3f);
        dvd7.setId(7);
        DigitalVideoDisc dvd8 = new DigitalVideoDisc(
            "Harry Potter and the Deathly Hallows - Part 2 (2011)",
            "Fantasy", "David Yates", 130, 7.0f);
        dvd8.setId(8);

        CompactDisc cd1 = new CompactDisc("Future Nostalgia", "Music", "Dua Lipa", 9.6f);
        cd1.setId(9);
        cd1.addTrack(new Track("Don't Start Now", 183));
        cd1.addTrack(new Track("Physical", 194));

        Book book1 = new Book("Green Eggs and Ham", "Children", 3.3f);
        book1.setId(10);
        book1.addAuthor("Dr. Seuss");

        // Add to store
        Media[] all = { dvd1, dvd2, dvd3, dvd4, dvd5, dvd6, dvd7, dvd8, cd1, book1 };
        for (Media m : all) {
            store.addMedia(m);
        }

        launch(args);
    }
}