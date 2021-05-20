package util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import flippingcoins.javafx.controller.GameController;
import flippingcoins.model.ResultState;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {

    public void writer(ResultState resultState){
        File file = new File(GameController.class.getClassLoader().getResource("data.json").getFile());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try{
            List<ResultState> resultStatesList = new ArrayList<>();
            if(file.length() != 0){
                resultStatesList = mapper.readValue(file, new TypeReference<List<ResultState>>() {
                });
            }
            resultStatesList.add(resultState);
            writer.writeValue(file,resultStatesList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
