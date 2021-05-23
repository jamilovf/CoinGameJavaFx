package util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import flippingcoins.javafx.controller.GameController;
import flippingcoins.model.ResultState;
import org.tinylog.Logger;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonWriter {

    public static void writer(ResultState resultState){
        String protocol = resultState.getClass().getResource("").getProtocol();
        if(Objects.equals(protocol, "file")){
            writeFromIDE(resultState);
        }
        else if(Objects.equals(protocol, "jar")) {
            writeFromJar(resultState);
        }
    }

    private static void writeFromIDE(ResultState resultState){
        InputStream data = GameController.class.getResourceAsStream("/data.json");
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            List<ResultState> resultStatesList = new ArrayList<>();
            if(data.available() != 0){
                resultStatesList = mapper.readValue(data, new TypeReference<List<ResultState>>() {
                });
            }
            resultStatesList.add(resultState);
            OutputStream out = new FileOutputStream(GameController.class.getResource("/data.json").getFile());
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(out,resultStatesList);
            Logger.info("Game results have been saved...");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeFromJar(ResultState resultState){
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            String path = resultState.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(path);
            path = path.substring(0, path.lastIndexOf("/") + 1);
            System.out.println(path);
            path = URLDecoder.decode(path, "UTF-8");
            FileInputStream data = new FileInputStream(path+"classes/data.json");

            BufferedReader in = new BufferedReader(new InputStreamReader(data));
            List<ResultState> resultStatesList = new ArrayList<>();

            if(data.available()!=0){
                resultStatesList = mapper.readValue(in, new TypeReference<List<ResultState>>() {
                });
            }
            resultStatesList.add(resultState);
            OutputStream out = new FileOutputStream(path+"classes/data.json");
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(out,resultStatesList);
            Logger.info("Game results have been saved...");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
