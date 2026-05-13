package hust.soict.globalict.aims.screen.manager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add Compact Disc");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfArtist = new JTextField();
        JTextField tfCost = new JTextField();

        center.add(new JLabel("Title:"));
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        center.add(tfCategory);

        center.add(new JLabel("Artist:"));
        center.add(tfArtist);

        center.add(new JLabel("Cost:"));
        center.add(tfCost);

        JButton addBtn = new JButton("Add to Store");
        addBtn.addActionListener(e -> {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String artist = tfArtist.getText().trim();
                float cost = Float.parseFloat(tfCost.getText().trim());

                CompactDisc cd = new CompactDisc(title, category, artist, cost);
                store.addMedia(cd);

                JOptionPane.showMessageDialog(this,
                        "CD added: " + title,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear form for the next entry
                tfTitle.setText("");
                tfCategory.setText("");
                tfArtist.setText("");
                tfCost.setText("");
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