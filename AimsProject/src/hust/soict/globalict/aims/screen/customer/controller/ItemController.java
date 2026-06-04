package hust.soict.globalict.aims.screen.customer.controller;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.exception.PlayerException;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    private Media media;
    private Cart cart;

    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            // Push Add-to-Cart to the right so the layout still looks balanced
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    @FXML
void btnAddToCartClicked(ActionEvent event) {
    try {
        cart.addMedia(media);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add to cart");
        alert.setHeaderText(null);
        alert.setContentText("Added: " + media.getTitle());
        alert.showAndWait();
    } catch (LimitExceededException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Cart full");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
@FXML
void btnPlayClicked(ActionEvent event) {
    if (media instanceof Playable) {
        try {
            ((Playable) media).play();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Playing");
            alert.setHeaderText(null);
            alert.setContentText("Now playing: " + media.getTitle());
            alert.showAndWait();
        } catch (PlayerException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot play");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
}