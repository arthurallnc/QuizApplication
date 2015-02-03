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
        MyDataBase db = new MyDataBase(getActivity());
        //db.addQuestions();
        List<Question> questions = db.showAllQuestions();
        View myFragmentView = inflater.inflate(R.layout.fragment_questions, container, false);
        TextView textQuestion = (TextView) myFragmentView.findViewById(R.id.text_question);
        Button answerA = (Button) myFragmentView.findViewById(R.id.answer_a);
        Button answerB = (Button) myFragmentView.findViewById(R.id.answer_b);
        Button answerC = (Button) myFragmentView.findViewById(R.id.answer_c);
        Button answerD = (Button) myFragmentView.findViewById(R.id.answer_d);
        textQuestion.setText(questions.get(0).getIntitule());
        answerA.setText(questions.get(0).getAnswer_a());
        answerB.setText(questions.get(0).getAnswer_b());
        answerC.setText(questions.get(0).getAnswer_c());
        answerD.setText(questions.get(0).getAnswer_d());
        return myFragmentView;
    }
}
