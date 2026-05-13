package hust.soict.globalict.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

public class StoreManagerScreen extends JFrame {
    private Store store;

    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ---------- NORTH ----------
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            dispose();
            new StoreManagerScreen(store);
        });
        menu.add(viewStore);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store);
        });
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store);
        });
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // ---------- CENTER ----------
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        int limit = Math.min(9, mediaInStore.size());
        for (int i = 0; i < limit; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    // ---------- main ----------
    public static void main(String[] args) {
        // Sample store data so we have something to display
        Store store = new Store();
        store.addMedia(new DigitalVideoDisc(
                "Harry Potter and the Philosopher's Stone (2001)",
                "Fantasy", "Chris Columbus", 152, 3.0f));
        store.addMedia(new DigitalVideoDisc(
                "Harry Potter and the Chamber of Secrets (2002)",
                "Fantasy", "Chris Columbus", 161, 3.5f));
        store.addMedia(new DigitalVideoDisc(
                "Harry Potter and the Prisoner of Azkaban (2004)",
                "Fantasy", "Alfonso Cuaron", 142, 5.0f));
        store.addMedia(new DigitalVideoDisc(
                "Harry Potter and the Goblet of Fire (2005)",
                "Fantasy", "Mike Newell", 157, 4.5f));
        store.addMedia(new CompactDisc(
                "Fetch the Bolt Cutters", "Music", "Fiona Apple", 10.39f));
        store.addMedia(new CompactDisc(
                "Future Nostalgia", "Music", "Dua Lipa", 9.6f));
        store.addMedia(new Book("The Hunger Games", "Novel", 5.5f));
        store.addMedia(new Book("Catching Fire", "Novel", 4.9f));
        store.addMedia(new Book("Mockingjay", "Novel", 5.1f));

        new StoreManagerScreen(store);
    }
}