package hust.soict.globalict.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.exception.PlayerException;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    private Store store;
    private Cart cart;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        // 1. Setup how each column reads from Media object
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        // 2. Bind cart data to TableView
        if (cart.getItemsOrdered() != null) {
            tblMedia.setItems(cart.getItemsOrdered());
        }

        // 3. Initially hide Play/Remove buttons
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // 4. Show/hide buttons based on row selected
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
            }
        );

        // 5. Update total cost label whenever the cart changes (add/remove)
        updateCostLabel();
        cart.getItemsOrdered().addListener((javafx.collections.ListChangeListener<Media>) change -> updateCostLabel());

        // 6. Filter functionality
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
    }

    void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    void updateCostLabel() {
        costLabel.setText(String.format("%.2f $", cart.getTotalCost()));
    }

    void showFilteredMedia(String filterText) {
        FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        if (filterText == null || filterText.isEmpty()) {
            filteredList.setPredicate(p -> true);
        } else if (radioBtnFilterId.isSelected()) {
            filteredList.setPredicate(media -> {
                try {
                    int id = Integer.parseInt(filterText);
                    return media.getId() == id;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
        } else if (radioBtnFilterTitle.isSelected()) {
            filteredList.setPredicate(media -> media.isMatch(filterText));
        }
        tblMedia.setItems(filteredList);
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            updateButtonBar(null);
        }
    }

    @FXML
void btnPlayPressed(ActionEvent event) {
    Media media = tblMedia.getSelectionModel().getSelectedItem();
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

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Empty cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order placed");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Order placed! Total: %.2f $", cart.getTotalCost()));
        alert.showAndWait();

        // Clear the cart after the order
        cart.getItemsOrdered().clear();
        updateCostLabel();
        updateButtonBar(null);
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/globalict/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}