package flippingcoins.javafx.controller;

import flippingcoins.model.FlippingCoinsState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private GridPane gameBoard;

    private Image headImage = new Image(getClass().getResource("/images/head.png").toExternalForm());

    private Image tailImage = new Image(getClass().getResource("/images/tail.png").toExternalForm());

    public void handleClickOnCoin(MouseEvent mouseEvent){
        System.out.println("mouse");
        var column= GridPane.getColumnIndex((Node) mouseEvent.getSource());
        System.out.println(column);
        ImageView im = (ImageView) mouseEvent.getTarget();

        if(FlippingCoinsState.coinState.get(column) == 0){
            FlippingCoinsState.coinState.set(column,1);
            im.setImage(tailImage);
        }
        else{
            FlippingCoinsState.coinState.set(column,0);
            im.setImage(headImage);
        }

    }

}
