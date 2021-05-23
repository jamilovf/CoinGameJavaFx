package flippingcoins.javafx.controller;

import flippingcoins.model.Coin;
import flippingcoins.model.FlippingCoinsState;
import flippingcoins.model.PlayerState;
import flippingcoins.model.ResultState;
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
import javafx.stage.Stage;
import org.tinylog.Logger;
import util.javafx.ControllerHelper;
import util.json.JsonWriter;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private FXMLLoader fxmlLoader = new FXMLLoader();

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

    FlippingCoinsState gameState = new FlippingCoinsState();

    PlayerState playerState = new PlayerState();

    ResultState resultState = new ResultState();

    JsonWriter jsonWriter = new JsonWriter();

    private Image headImage = new Image(getClass().getResource("/images/head.png").toExternalForm());

    private Image tailImage = new Image(getClass().getResource("/images/tail.png").toExternalForm());

    private Image playerIcon = new Image(getClass().getResource("/images/player-icon.png").toExternalForm());

    public void initializeState(String playerName1, String playerName2){
        resultState.setPlayer1Name(playerName1);
        resultState.setPlayer2Name(playerName2);
        player1NameLabel.setText(resultState.getPlayer1Name());
        player2NameLabel.setText(resultState.getPlayer2Name());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultState.setTime(Instant.now());
        gameState.initializeCoins();
        player1Icon.setImage(playerIcon);
    }

    public void handleClickOnCoin(MouseEvent mouseEvent){
        endGameLabel.setText("");
        var column= GridPane.getColumnIndex((Node) mouseEvent.getSource());
        ImageView im = (ImageView) mouseEvent.getTarget();

        Logger.debug("Coin {} is pressed", column);

        if(!isGameFinished()) {
            if(column == 9 && gameState.coinState.get(column) == Coin.TAIL){
                endGameLabel.setText("Last coin cannot change from tail to head!");
                Logger.warn("Invalid move");
            }
            else {
                Coin coin = gameState.modifyCoinState(column);
                move(im, coin);
                Logger.debug(gameState);
            }
        }
    }

    private void move(ImageView im, Coin coin){
        if(coin == Coin.TAIL){
            modifyStepsOfPlayer();
            im.setImage(tailImage);
            Logger.debug("Coin is flipped to TAIL");
        }
        else {
            modifyStepsOfPlayer();
            im.setImage(headImage);
            Logger.debug("Coin is flipped to HEAD");
        }
        if(isGameFinished()){
            endGame();
        }
    }

    private boolean isGameFinished() {
        Logger.debug("Checking game finished or not...");
        return gameState.isGameOver(gameState.coinState);
    }

    private void endGame() {
        if (playerState.isPlayer1Turn()) {
            endGameLabel.setText(player1NameLabel.getText() + " won the game!");
            resultState.setWinner(player1NameLabel.getText());
        }
        else {
            endGameLabel.setText(player2NameLabel.getText() + " won the game!");
            resultState.setWinner(player2NameLabel.getText());
        }
        restartButton.setVisible(true);
        switchButton.setDisable(true);

        Logger.warn("Game is ended!!!");

        jsonWriter.writer(resultState);

    }

    private void modifyStepsOfPlayer() {
        Logger.debug("Modifying steps of player...");
        int steps = playerState.modifySteps(resultState);
        checkSteps(steps);
    }

    private void checkSteps(int steps){
        if(isGameFinished()){
            return;
        }
        if(steps == 3){
            switchPlayer();
        }
    }

    public void switchAction(ActionEvent actionEvent) throws IOException {
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        switchPlayer();
    }

    private void switchPlayer() {
        if((playerState.isPlayer1Turn() && playerState.getPlayer1Steps() == 0)
                || (!playerState.isPlayer1Turn() && playerState.getPlayer2Steps() == 0)){
            endGameLabel.setText("0 flips, please, flip coin!");
            Logger.warn("Invalid switch");
        }
        else {
            if (playerState.switchPlayer()) {
                player2Icon.setImage(null);
                player1Icon.setImage(playerIcon);
                Logger.debug("Switched to {} ",player1NameLabel.getText());
            }
            else {
                player1Icon.setImage(null);
                player2Icon.setImage(playerIcon);
                Logger.debug("Switched to {} ",player2NameLabel.getText());
            }
        }
    }

    public void restartAction(ActionEvent actionEvent) throws IOException {
        Logger.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        Logger.info("Restarting game");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/launch.fxml",stage);
    }


}
