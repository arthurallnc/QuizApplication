package com.example.etudiant.quizapplication;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsFragment extends Fragment{

    private int truth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MyDataBase db = new MyDataBase(getActivity());
        //db.addQuestions();
        List<Question> questions = db.showAllQuestions();
        truth = questions.get(0).getTruth();
        View myFragmentView = inflater.inflate(R.layout.fragment_questions, container, false);
        final TextView textQuestion = (TextView) myFragmentView.findViewById(R.id.text_question);
        final Button answerA = (Button) myFragmentView.findViewById(R.id.answer_a);
        final Button answerB = (Button) myFragmentView.findViewById(R.id.answer_b);
        final Button answerC = (Button) myFragmentView.findViewById(R.id.answer_c);
        final Button answerD = (Button) myFragmentView.findViewById(R.id.answer_d);
        textQuestion.setText(questions.get(0).getIntitule());
        answerA.setText(questions.get(0).getAnswer_a());
        answerB.setText(questions.get(0).getAnswer_b());
        answerC.setText(questions.get(0).getAnswer_c());
        answerD.setText(questions.get(0).getAnswer_d());

        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(truth == 1){
                    answerA.setBackgroundColor(Color.GREEN);
                }
                else answerA.setBackgroundColor(Color.RED);
            }
        });

        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(truth == 2){
                    answerB.setBackgroundColor(Color.GREEN);
                }
                else answerB.setBackgroundColor(Color.RED);
            }
        });

        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(truth == 3){
                    answerC.setBackgroundColor(Color.GREEN);
                }
                else answerC.setBackgroundColor(Color.RED);
            }
        });

        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(truth == 4){
                    answerD.setBackgroundColor(Color.GREEN);
                }
                else answerD.setBackgroundColor(Color.RED);
            }
        });
        return myFragmentView;
    }
}
