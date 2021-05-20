package flippingcoins.javafx.controller;

import flippingcoins.model.ResultState;
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

    ResultState resultState = new ResultState();

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
            resultState.setPlayer1Name(player1NameTextField.getText());
            resultState.setPlayer2Name(player2NameTextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/game.fxml",stage);
        }
    }
}
