package com.example.etudiant.quizapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsFragment extends Fragment{

    private List<Question> questions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //System.out.println("Intitule " + questions.get(0).getIntitule());
        View myFragmentView = inflater.inflate(R.layout.fragment_questions, container, false);
        TextView textQuestion = (TextView) myFragmentView.findViewById(R.id.text_question);
        //textQuestion.setText(questions.get(0).getIntitule());
        //Button answerA = (Button) getView().findViewById(R.id.answer_a);
        //answerA.setText(questions.get(0).getAnswer_a());
        //Button answerB = (Button) getView().findViewById(R.id.answer_b);
        //answerB.setText(questions.get(0).getAnswer_b());
        //Button answerC = (Button) getView().findViewById(R.id.answer_c);
        //answerC.setText(questions.get(0).getAnswer_c());
        //Button answerD = (Button) getView().findViewById(R.id.answer_d);
        //answerD.setText(questions.get(0).getAnswer_d());
        return myFragmentView;
    }

    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }
}
