package flippingcoins.javafx;

import javax.inject.Inject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FlippingCoinsApplication extends Application {


    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/launch.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Flipping Coins");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
