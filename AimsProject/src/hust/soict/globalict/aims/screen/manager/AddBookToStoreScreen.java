package hust.soict.globalict.aims.screen.manager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfCost = new JTextField();
        JTextField tfAuthors = new JTextField();

        center.add(new JLabel("Title:"));
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        center.add(tfCategory);

        center.add(new JLabel("Cost:"));
        center.add(tfCost);

        center.add(new JLabel("Authors (comma separated):"));
        center.add(tfAuthors);

        JButton addBtn = new JButton("Add to Store");
        addBtn.addActionListener(e -> {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                float cost = Float.parseFloat(tfCost.getText().trim());

                Book book = new Book(title, category, cost);

                String authorsText = tfAuthors.getText().trim();
                if (!authorsText.isEmpty()) {
                    for (String a : authorsText.split(",")) {
                        book.addAuthor(a.trim());
                    }
                }

                store.addMedia(book);

                JOptionPane.showMessageDialog(this,
                        "Book added: " + title,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear form for the next entry
                tfTitle.setText("");
                tfCategory.setText("");
                tfCost.setText("");
                tfAuthors.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid cost. Please enter a number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(new JLabel());
        center.add(addBtn);

        return center;
    }
}