package flippingcoins.javafx.controller;

import flippingcoins.model.FlippingCoinsState;
import flippingcoins.model.ResultState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label player1NameLabel;

    @FXML
    private Label player2NameLabel;

    @FXML
    private ImageView player1Icon;

    @FXML
    private ImageView player2Icon;

    @FXML
    private Label endGameLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button switchButton;

    private static int player1Steps = 0;

    private static int player2Steps = 0;

    private boolean player1Turn = true;

    FlippingCoinsState gameState = new FlippingCoinsState();

    ResultState resultState = new ResultState();

    private Image headImage = new Image(getClass().getResource("/images/head.png").toExternalForm());

    private Image tailImage = new Image(getClass().getResource("/images/tail.png").toExternalForm());

    private Image playerIcon = new Image(getClass().getResource("/images/player-icon.png").toExternalForm());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1NameLabel.setText(resultState.getPlayer1Name());
        player2NameLabel.setText(resultState.getPlayer2Name());
        player1Icon.setImage(playerIcon);
    }

    public void handleClickOnCoin(MouseEvent mouseEvent){
        var column= GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();

        if(!gameState.isGameOver(gameState.coinState)) {
            if(column == 9 && gameState.coinState.get(column) == 1){
                endGameLabel.setText("Last coin cannot change from tail to head!");
            }
            else if (gameState.coinState.get(column) == 0) {
                gameState.coinState.set(column, 1);
                modifyStepsOfPlayer();
                im.setImage(tailImage);
            }
            else {
                gameState.coinState.set(column, 0);
                modifyStepsOfPlayer();
                im.setImage(headImage);
            }
        }
        else {
            if (player1Turn) {
                endGameLabel.setText(player1NameLabel.getText() + " won the game!");
            }
            else {
                endGameLabel.setText(player2NameLabel.getText() + " won the game!");
            }
            restartButton.setVisible(true);
            switchButton.setDisable(true);
        }
    }

    private void modifyStepsOfPlayer() {
        if (player1Turn) {
            player1Steps++;
        } else {
            player2Steps++;
        }
    }

    public void switchAction(ActionEvent actionEvent) throws IOException {
        if((player1Turn && player1Steps == 0) || (!player1Turn && player2Steps == 0)){
            endGameLabel.setText("0 flips, please, flip coin!");;
        }
        else {
            if (player1Turn) {
                player1Turn = false;
                player1Icon.setImage(null);
                player2Icon.setImage(playerIcon);
            } else {
                player1Turn = true;
                player2Icon.setImage(null);
                player1Icon.setImage(playerIcon);
            }
        }
    }
}
