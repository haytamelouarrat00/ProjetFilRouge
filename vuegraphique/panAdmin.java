package ProjetFilRouge.vuegraphique;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class panAdmin implements Initializable {

    @FXML
    private Button buttonRetour1;

    @FXML
    private Button buttonEnregistrer;

    @FXML
    private ToggleButton button_ferme;

    @FXML
    private ToggleButton button_ouvert;

    @FXML
    private Label label_fen;

    @FXML
    private Label label_seuil;

    @FXML
    private Slider slider_fen;

    @FXML
    private Slider slider_seuil;

    @FXML
    private ComboBox<String> com_bits;

    @FXML
    private ComboBox<String> com_mode;

    @FXML
    private ComboBox<?> com_fen;

    @FXML
    void combo_bits(ActionEvent event) {
        //code pour les
    }

    @FXML
    void combo_mode(ActionEvent event) {
        //code pour les modes
    }
    @FXML
    void retour1(ActionEvent event) {
        /*Stage stage = (Stage) PanSIdentifier.getScene().getWindow();
        Node previousPage = PanSIdentifier.getPreviousPage();

        if (previousPage != null) {
            stage.setScene(previousPage.getScene());
        }*/
        Scene scene = ((Node) event.getSource()).getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    @FXML
    void porc_fen(MouseEvent event) {
        slider_fen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (newValue.doubleValue() / slider_fen.getMax()) * 100;
            label_fen.setText(String.format("%.0f%%", percentage));
        });
    }

    @FXML
    void pourc_seuil(MouseEvent event) {
        slider_seuil.valueProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = (newValue.doubleValue() / slider_seuil.getMax()) * 100;
            label_seuil.setText(String.format("%.0f%%", percentage));
        });
    }

    @FXML
    void mode_f(ActionEvent event) {
        button_ferme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (button_ferme.isSelected()){

                } else {

                }
            }
        });
    }

    @FXML
    void mode_o(ActionEvent event) {
        button_ouvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (button_ouvert.isSelected()){

                } else {

                }
            }
        });
    }

    @FXML
    void enregistrerModif(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list_m = FXCollections.observableArrayList("ouvert", "fermé");
        ObservableList<String> list_b = FXCollections.observableArrayList("2", "3");

        com_mode.setItems(list_m);
        com_bits.setItems(list_b);

        //com_mode.getSelectionModel().select();


    }
}
