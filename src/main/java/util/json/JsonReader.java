package util.json;

import flippingcoins.javafx.controller.ResultsController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * Class provides reader with the help of Jackson
 */
public class JsonReader {

    /**
     * Reading data from IDE
     * @param controller
     * @return data.json file
     */
    public static FileInputStream readFromJar(ResultsController controller){
        try {
            String path = controller.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            path = path.substring(0, path.lastIndexOf("/") + 1);
            path = URLDecoder.decode(path, "UTF-8");
            FileInputStream file = new FileInputStream(path+"/classes/data.json");
            return file;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reading data from Jar
     * @param controller
     * @return data.json file
     */
    public static InputStream readFromIde(ResultsController controller){
        return controller.getClass().getResourceAsStream("/data.json");
    }
}
