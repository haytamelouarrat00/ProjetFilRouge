package ProjetFilRouge.vuegraphique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.util.Objects;

public class PanSIdentifier {

    @FXML
    private PasswordField barreMDP;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRetour2;
    @FXML
    private Label label_erreur;

    public boolean checkPassword(String enteredPassword) {
        String password = "amal";
        return enteredPassword.equals(password);
    }
    @FXML
    void Login(ActionEvent event) throws Exception {
        String enteredPassword = barreMDP.getText();

        if (checkPassword(enteredPassword)) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pantadm.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            label_erreur.setText("Mot de passe incorrect");
        }


    }


    @FXML
    void retour2(ActionEvent event) {

    }

}
