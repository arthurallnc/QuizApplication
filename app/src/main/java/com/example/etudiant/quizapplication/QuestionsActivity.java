package com.example.etudiant.quizapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        MyDataBase db = new MyDataBase(this);
        //db.addQuestions();
        List<Question> questions = db.showAllQuestions();

        QuestionsFragment fragment = (QuestionsFragment) getFragmentManager().findFragmentById(R.id.fragment_questions);
        fragment.setQuestions(questions);
    }
}
