package flippingcoins.javafx;

import util.javafx.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class FlippingCoinsApplication extends Application {


    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Flipping Coins");
        stage.setResizable(false);
        ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/launch.fxml",stage);
    }
}
