package flippingcoins.javafx.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import flippingcoins.model.PlayerHighScoreResult;
import flippingcoins.model.ResultState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.javafx.ControllerHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ResultsController {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<ResultState, String> name;
    @FXML
    private TableColumn<ResultState, Double> score;

    ResultState resultState = new ResultState();

    @FXML
    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        File data = new File(GameController.class.getClassLoader().getResource("data.json").getFile());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        List<ResultState> resultStateList = new ArrayList<>();
        ObservableList<PlayerHighScoreResult> playerResults = FXCollections.observableArrayList();

        try {
            if(data.length()!=0){
                resultStateList = mapper.readValue(data, new TypeReference<List<ResultState>>() {
                });
              Map<String,Long> map = resultStateList.stream()
                        .collect(Collectors.groupingBy(ResultState::getWinner,Collectors.counting()));

              map.entrySet().forEach(entry -> {
                  PlayerHighScoreResult playerResult = new PlayerHighScoreResult();
                  playerResult.setName(entry.getKey());
                  playerResult.setScore(entry.getValue().intValue());
                  playerResults.add(playerResult);
                });

            }

            tableView.setItems(playerResults);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void backAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerHelper.loadAndShowFXML(fxmlLoader,"/fxml/launch.fxml",stage);
    }
}