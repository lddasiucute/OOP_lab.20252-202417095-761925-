package hust.soict.globalict.aims.test.screen.customer.store;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.exception.LimitExceededException;
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

public class TestViewStoreScreen extends Application {

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

    public static void main(String[] args) throws LimitExceededException {
        store = new Store();
        cart = new Cart();

        // Sample data for testing
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
            "The Lion King", "Animation", "Roger Allers", 89, 19.95f);
        dvd1.setId(1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
            "Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        dvd2.setId(2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
            "Aladdin", "Animation", "Ron Clements", 90, 18.99f);
        dvd3.setId(3);

        CompactDisc cd1 = new CompactDisc("Abbey Road", "Music", "The Beatles", 22.50f);
        cd1.setId(4);
        cd1.addTrack(new Track("Come Together", 259));
        cd1.addTrack(new Track("Something", 183));

        Book book1 = new Book("Effective Java", "Education", 45.0f);
        book1.setId(5);
        book1.addAuthor("Joshua Bloch");

        Media[] all = { dvd1, dvd2, dvd3, cd1, book1 };
        for (Media m : all) {
            store.addMedia(m);
        }

        launch(args);
    }
}