package com.example.etudiant.quizapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsFragment extends Fragment{

    private int truth;
    private int rand;
    private int score;
    private int tour;
    private int[] questionsPrecedentes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MyDataBase db = new MyDataBase(getActivity());
        db.addQuestions();
        List<Question> questions = db.showAllQuestions();
        Random r = new Random();
        rand = r.nextInt(10);
        questionsPrecedentes=((QuestionsActivity) getActivity()).getQuestionsPrecedentes();
        if(!Arrays.asList(questionsPrecedentes).contains(-1)){
            //
        }
        View myFragmentView = inflater.inflate(R.layout.fragment_questions, container, false);
        final TextView textQuestion = (TextView) myFragmentView.findViewById(R.id.text_question);
        final TextView textScore = (TextView) myFragmentView.findViewById(R.id.score_question);
        while(questionsPrecedentes[rand]==1){
            rand = r.nextInt(10);

        }

        truth = questions.get(rand).getTruth();
        score = ((QuestionsActivity) getActivity()).getScore();


        final Button answerA = (Button) myFragmentView.findViewById(R.id.answer_a);
        final Button answerB = (Button) myFragmentView.findViewById(R.id.answer_b);
        final Button answerC = (Button) myFragmentView.findViewById(R.id.answer_c);
        final Button answerD = (Button) myFragmentView.findViewById(R.id.answer_d);
        textScore.setText("score: " + score);
        textQuestion.setText(questions.get(rand).getIntitule());
        answerA.setText(questions.get(rand).getAnswer_a());
        answerB.setText(questions.get(rand).getAnswer_b());
        answerC.setText(questions.get(rand).getAnswer_c());
        answerD.setText(questions.get(rand).getAnswer_d());


        questionsPrecedentes[rand]=1;

        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(truth){
                    case 1:
                        answerA.setTextColor(Color.GREEN);
                        ((QuestionsActivity) getActivity()).setScore(score + 1);

                        break;
                    case 2:
                        answerA.setTextColor(Color.RED);
                        answerB.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        answerA.setTextColor(Color.RED);
                        answerC.setTextColor(Color.GREEN);
                        break;
                    case 4:
                        answerA.setTextColor(Color.RED);
                        answerD.setTextColor(Color.GREEN);
                        break;
                }
                answerA.setClickable(false);
                answerB.setClickable(false);
                answerC.setClickable(false);
                answerD.setClickable(false);
                ((QuestionsActivity) getActivity()).setQuestionsPrecedentes(questionsPrecedentes);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 2 seconds
                        QuestionsFragment fragment = new QuestionsFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_questions, fragment);
                        fragmentTransaction.commit();
                    }
                }, 2000);
            }
        });

        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(truth){
                    case 1:
                        answerA.setTextColor(Color.GREEN);
                        answerB.setTextColor(Color.RED);
                        break;
                    case 2:
                        answerB.setTextColor(Color.GREEN);
                        ((QuestionsActivity) getActivity()).setScore(score + 1);
                        break;
                    case 3:
                        answerB.setTextColor(Color.RED);
                        answerC.setTextColor(Color.GREEN);
                        break;
                    case 4:
                        answerB.setTextColor(Color.RED);
                        answerD.setTextColor(Color.GREEN);
                        break;
                }
                answerA.setClickable(false);
                answerB.setClickable(false);
                answerC.setClickable(false);
                answerD.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 2 seconds
                        QuestionsFragment fragment = new QuestionsFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_questions, fragment);
                        fragmentTransaction.commit();
                    }
                }, 2000);
            }
        });

        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(truth){
                    case 1:
                        answerA.setTextColor(Color.GREEN);
                        answerC.setTextColor(Color.RED);
                        break;
                    case 2:
                        answerB.setTextColor(Color.GREEN);
                        answerC.setTextColor(Color.RED);
                        break;
                    case 3:
                        answerC.setTextColor(Color.GREEN);
                        ((QuestionsActivity) getActivity()).setScore(score + 1);
                        break;
                    case 4:
                        answerC.setTextColor(Color.RED);
                        answerD.setTextColor(Color.GREEN);
                        break;
                }
                answerA.setClickable(false);
                answerB.setClickable(false);
                answerC.setClickable(false);
                answerD.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 2 seconds
                        QuestionsFragment fragment = new QuestionsFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_questions, fragment);
                        fragmentTransaction.commit();
                    }
                }, 2000);
            }
        });

        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(truth){
                    case 1:
                        answerA.setTextColor(Color.GREEN);
                        answerD.setTextColor(Color.RED);
                        break;
                    case 2:
                        answerB.setTextColor(Color.GREEN);
                        answerD.setTextColor(Color.RED);
                        break;
                    case 3:
                        answerC.setTextColor(Color.GREEN);
                        answerD.setTextColor(Color.RED);
                        break;
                    case 4:
                        answerD.setTextColor(Color.GREEN);
                        ((QuestionsActivity) getActivity()).setScore(score + 1);
                        break;
                }
                answerA.setClickable(false);
                answerB.setClickable(false);
                answerC.setClickable(false);
                answerD.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 2 seconds
                        QuestionsFragment fragment = new QuestionsFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_questions, fragment);
                        fragmentTransaction.commit();
                    }
                }, 2000);
            }
        });
        return myFragmentView;
    }
}
