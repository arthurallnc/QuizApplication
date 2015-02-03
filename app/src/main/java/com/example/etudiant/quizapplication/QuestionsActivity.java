package com.example.etudiant.quizapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsActivity extends FragmentActivity {

    private int score = 0;
    private int tour =0;
    private int[] questionsPrecedentes = new int[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeQuestionsPrecedentes(questionsPrecedentes);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_questions, new QuestionsFragment());
        fragmentTransaction.commit();
        setContentView(R.layout.activity_questions);

    }

    public void initializeQuestionsPrecedentes(int[] questionsPrecedentes){
        int i =0;
        for(i=0;  i<questionsPrecedentes.length ;i++){
            questionsPrecedentes[i]=-1;
        }
    }

    public int getScore(){
        return  score;
    }

    public void setScore (int score){
        this.score = score;
    }

    public int getTour(){
        return tour;
    }

    public void setTour (int tour){
        this.tour = tour;
    }

    public int[] getQuestionsPrecedentes(){
        return  questionsPrecedentes;
    }

    public void setQuestionsPrecedentes (int[] questionsPrecedentes){
        this.questionsPrecedentes = questionsPrecedentes;
    }
}
