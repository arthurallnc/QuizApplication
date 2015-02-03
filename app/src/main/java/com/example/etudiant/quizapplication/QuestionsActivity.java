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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_questions, new QuestionsFragment());
        fragmentTransaction.commit();
        setContentView(R.layout.activity_questions);
    }

    public int getScore(){
        return  score;
    }

    public void setScore (int score){
        this.score = score;
    }
}
