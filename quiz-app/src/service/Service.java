package service;

import domain.Question;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Question> getAll() {
        List<Question> sortedq = repo.getAll().stream().sorted((x,y)->{
            if(x.getScore() < y.getScore())
                return -1;
            if(x.getScore() > y.getScore())
                return 1;
            return 0;
        }).toList();
        return sortedq;
    }
    public List<Question> getItem(String text, int score) {
        List<Question> quest = new ArrayList<>();
        quest = (List<Question>) repo.getAll();
        return quest.stream().filter(x->{
            if(x.getText().contains(text) && x.getScore() > score)
                return true;
            return false;
        }).toList();
    }

    public void updateUserAnswer(int id, String newItem) {
        repo.updateUserAnswer(id, newItem);
    }
}
