package gui;

import domain.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    private Service service;

    private int score;

    public Controller(Service service) {
        this.service = service;
    }

    public void initialize(){populateQuestions();}

    @FXML
    private ListView<Question> questionList;

    @FXML
    private TextField searchqField;

    @FXML
    private TextField searchsField;

    @FXML
    private TextField answerField;

    @FXML
    private Label scoreLabel;

    void populateQuestions(){
        List<Question> quest = new ArrayList<>();
        service.getAll().forEach(quest::add);
        ObservableList<Question> orderList = FXCollections.observableArrayList(quest);
        questionList.setItems(orderList);
    }

    @FXML
    void searchItem(KeyEvent event) {
        String searchText = searchqField.getText();
        int searchScore = 0;
        if(!searchsField.getText().isEmpty())
            searchScore = Integer.parseInt(searchsField.getText());
        //System.out.println(searchScore);
        if (searchText.isEmpty())
            populateQuestions();
        else{
                List<Question> quest = service.getItem(searchText, searchScore);
                ObservableList<Question> found = FXCollections.observableArrayList(quest);
                questionList.setItems(found);
        }
    }

    @FXML
    void answerQuestion(MouseEvent event) {
        Question q = questionList.getSelectionModel().getSelectedItem();
        service.updateUserAnswer(q.getId(), answerField.getText());
        if(Objects.equals(q.getCorrAnswer(), answerField.getText())){
            score += q.getScore();
            scoreLabel.setText(String.format(Integer.toString(score)));
        }

    }
    @FXML
    void getHint(MouseEvent event) {
        Question q = questionList.getSelectionModel().getSelectedItem();
        Dialog<String> dialog = new Dialog<>();
        dialog.setContentText(q.getCorrAnswer().substring(0,2));
        dialog.show();
        Stage stage = new Stage();
    }
}
