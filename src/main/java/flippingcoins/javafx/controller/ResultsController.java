package flippingcoins.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import util.javafx.ControllerHelper;

import java.io.IOException;

public class ResultsController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    public void backAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/launch.fxml",stage);
    }
}
