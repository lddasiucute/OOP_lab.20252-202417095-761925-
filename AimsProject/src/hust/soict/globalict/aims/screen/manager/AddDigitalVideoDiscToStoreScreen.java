package hust.soict.globalict.aims.screen.manager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfDirector = new JTextField();
        JTextField tfLength = new JTextField();
        JTextField tfCost = new JTextField();

        center.add(new JLabel("Title:"));
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        center.add(tfCategory);

        center.add(new JLabel("Director:"));
        center.add(tfDirector);

        center.add(new JLabel("Length (minutes):"));
        center.add(tfLength);

        center.add(new JLabel("Cost:"));
        center.add(tfCost);

        JButton addBtn = new JButton("Add to Store");
        addBtn.addActionListener(e -> {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String director = tfDirector.getText().trim();
                int length = Integer.parseInt(tfLength.getText().trim());
                float cost = Float.parseFloat(tfCost.getText().trim());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);

                JOptionPane.showMessageDialog(this,
                        "DVD added: " + title,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear form for the next entry
                tfTitle.setText("");
                tfCategory.setText("");
                tfDirector.setText("");
                tfLength.setText("");
                tfCost.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid number in length or cost.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(new JLabel());
        center.add(addBtn);

        return center;
    }
}