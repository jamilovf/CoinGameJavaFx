package flippingcoins.javafx.controller;

import flippingcoins.model.ResultState;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.tinylog.Logger;
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
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        errorLabel.setText("");

        if(player1NameTextField.getText().isEmpty() && player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Players, please, enter your names!!!");
            Logger.warn("Empty text fields!!!");
        }
        else if(player1NameTextField.getText().isEmpty()){
            errorLabel.setText("Player1, please, enter your name!!!");
            Logger.warn("Empty text field!!!");
        }
        else if(player2NameTextField.getText().isEmpty()){
            errorLabel.setText("Player2, please, enter your name!!!");
            Logger.warn("Empty text field!!!");
        }
        else{
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fxmlLoader.<GameController>getController()
                    .initializeState(player1NameTextField.getText(),player2NameTextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            Logger.info("1st player name is set to {} and 2nd player name is set to {}, " +
                    "loading game scene", player1NameTextField.getText(),player2NameTextField.getText());
        }
    }

    public void highScoresAction(ActionEvent actionEvent) throws IOException {
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/results.fxml",stage);
    }
}
