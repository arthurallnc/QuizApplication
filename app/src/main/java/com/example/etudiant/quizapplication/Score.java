package com.example.etudiant.quizapplication;

/**
 * Created by etudiant on 03/02/2015.
 */
public class Score {

    private int id;
    private int score;

    public Score(){}

    public Score(int score){
        this.score = score;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }
}
