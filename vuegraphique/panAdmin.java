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
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;


public class panAdmin implements Initializable {

    @FXML
    private Label L1;

    @FXML
    private Label L2;

    @FXML
    private Label L3;

    @FXML
    private Label L4;

    @FXML
    private Label L5;

    @FXML
    private Label label_enr;

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
    private ComboBox<String> com_fen;

    @FXML
    private ComboBox<String> com_occ;

    @FXML
    void combo_bits(ActionEvent event) {
        //code pour les bits
    }

    @FXML
    void combo_mode(ActionEvent event) {
        //code pour les modes
    }

    @FXML
    void combo_fen(ActionEvent event) {
        //code pour les fenêtres
    }

    @FXML
    void combo_occ(ActionEvent event) {

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
        // Récupération des valeurs des paramètres
        String param1 = com_mode.getValue();
        String param2 = com_bits.getValue();
        int param3 = (int) slider_seuil.getValue();
        int param4 = (int) slider_fen.getValue();
        String param5 = com_fen.getValue();
        String param6 = com_occ.getValue();
        // Enregistrement des paramètres dans un fichier de configuration
        try {

            Properties config = new Properties();
            config.setProperty("parametre1", param1);
            config.setProperty("parametre2", param2);
            config.setProperty("parametre3", Integer.toString(param3));
            config.setProperty("parametre4", Integer.toString(param4));
            FileOutputStream fos = new FileOutputStream("config.properties");
            config.store(fos, "Configuration des paramètres de l'application");
            fos.close();
            // Affichage d'un message de confirmation
            label_enr.setText("Les paramètres ont été enregistrées");
        } catch (IOException ex) {
            // Gestion de l'exception en cas d'erreur lors de l'enregistrement des paramètres
            label_enr.setText("Une erreur s'est produite lors de l'enregistrement des paramètres");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list_m = FXCollections.observableArrayList("ouvert", "fermé");
        ObservableList<String> list_b = FXCollections.observableArrayList("2", "3");
        ObservableList<String> list_f = FXCollections.observableArrayList("1024", "2048", "4096", "8192");
        ObservableList<String> list_o = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7");

        com_mode.setItems(list_m);
        com_bits.setItems(list_b);
        com_fen.setItems(list_f);
        com_occ.setItems(list_o);

        com_mode.setValue(com_mode.getItems().get(0));
        com_bits.setValue(com_bits.getItems().get(0));
        com_fen.setValue(com_fen.getItems().get(0));
        com_occ.setValue(com_occ.getItems().get(0));


        //com_mode.getSelectionModel().select();


        double minf = slider_fen.getMin();
        double maxf = slider_fen.getMax();
        double mins = slider_seuil.getMin();
        double maxs = slider_seuil.getMax();
        Random random = new Random();
        double randomValuef = minf + (maxf - minf) * random.nextDouble();
        double randomValues = mins + (maxs - mins) * random.nextDouble();
        slider_fen.setValue(randomValuef);
        label_fen.setText(String.format("%.0f%%", randomValuef));
        slider_seuil.setValue(randomValues);
        label_seuil.setText(String.format("%.0f%%", randomValues));


    }
}
