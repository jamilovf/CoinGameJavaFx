package flippingcoins.javafx.controller;

import util.javafx.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LaunchController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TextField player1NameTextField;

    @FXML
    private TextField player2NameTextField;

    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        errorLabel.setText("");

        if(player1NameTextField.getText().isEmpty() && player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Players, please, enter your names!!!");
        }
        else if(player1NameTextField.getText().isEmpty()){
            errorLabel.setText("Player1, please, enter your name!!!");
        }
        else if(player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Player2, please, enter your name!!!");
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/game.fxml",stage);
        }
    }
}
