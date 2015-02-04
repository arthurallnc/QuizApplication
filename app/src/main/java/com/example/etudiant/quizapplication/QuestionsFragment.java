package com.example.etudiant.quizapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsFragment extends Fragment{

    private int truth;
    private int rand;
    private int score;
    private int[] questionsPrecedentes;
    private List<Question> questions;
    private boolean roomLeft = false;
    private final int NB_QUESTIONS = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MyDataBase db = new MyDataBase(getActivity());
        switch (((QuestionsActivity) getActivity()).getLevel()){
            case("Débutant"):
                questions = db.showEasyQuestions();
                break;
            case("Intermédiaire"):
                questions = db.showMediumQuestions();
                break;
            case("Expert"):
                questions = db.showHardQuestions();
                break;
        }

        Random r = new Random();
        rand = r.nextInt(NB_QUESTIONS);
        questionsPrecedentes = ((QuestionsActivity) getActivity()).getQuestionsPrecedentes();
        for(int i = 0; i < questionsPrecedentes.length; i++){
            if(questionsPrecedentes[i] == -1) roomLeft = true;
        }
        if(!roomLeft || ((QuestionsActivity) getActivity()).getTime() == 0){
            View myFragmentView = inflater.inflate(R.layout.fragment_graph, container, false);
            final TextView textScoreGraph = (TextView) myFragmentView.findViewById(R.id.score_graph);
            score = ((QuestionsActivity) getActivity()).getScore();
            textScoreGraph.setText("score final: " + score);
            GraphView graph = (GraphView) myFragmentView.findViewById(R.id.graph);
            List<Score> scores = null;
            switch (((QuestionsActivity) getActivity()).getLevel()){
                case("Débutant"):
                    db.addEasyScore(new Score(score));
                    scores = db.showEasyScores();
                    break;
                case("Intermédiaire"):
                    db.addMediumScore(new Score(score));
                    scores = db.showMediumScores();
                    break;
                case("Expert"):
                    db.addHardScore(new Score(score));
                    scores = db.showHardScores();
                    break;
            }

            DataPoint[] dataPoint = new DataPoint[scores.size()];
            for (int i = 0; i < scores.size(); i++){
                dataPoint[i] = new DataPoint(scores.get(i).getId(), scores.get(i).getScore());
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);
            graph.addSeries(series);
            series.setDrawDataPoints(true);
            series.setDataPointsRadius(10);
            series.setTitle("Scores");
            graph.setTitle("Graphique d'évolution au niveau " + ((QuestionsActivity) getActivity()).getLevel());
            if (scores.size() <= 1){
                Toast.makeText(getActivity().getApplicationContext(), "Graphique non disponible\n Au moins 2 tentatives nécessaires pour afficher les scores", Toast.LENGTH_SHORT).show();
            }
            return myFragmentView;
        }
        else {
            View myFragmentView = inflater.inflate(R.layout.fragment_questions, container, false);
            final TextView textScore = (TextView) myFragmentView.findViewById(R.id.score_question);
            score = ((QuestionsActivity) getActivity()).getScore();
            textScore.setText("score: " + score);

            while (questionsPrecedentes[rand] == 1){
                rand = r.nextInt(NB_QUESTIONS);
            }

            truth = questions.get(rand).getTruth();
            final TextView textQuestion = (TextView) myFragmentView.findViewById(R.id.text_question);
            final Button answerA = (Button) myFragmentView.findViewById(R.id.answer_a);
            final Button answerB = (Button) myFragmentView.findViewById(R.id.answer_b);
            final Button answerC = (Button) myFragmentView.findViewById(R.id.answer_c);
            final Button answerD = (Button) myFragmentView.findViewById(R.id.answer_d);
            textQuestion.setText(questions.get(rand).getIntitule());
            answerA.setText(questions.get(rand).getAnswer_a());
            answerB.setText(questions.get(rand).getAnswer_b());
            answerC.setText(questions.get(rand).getAnswer_c());
            answerD.setText(questions.get(rand).getAnswer_d());

            questionsPrecedentes[rand] = 1;
            ((QuestionsActivity) getActivity()).setQuestionsPrecedentes(questionsPrecedentes);

            answerA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (truth) {
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
                    if (((QuestionsActivity) getActivity()).getTime() > 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 1 second
                                QuestionsFragment fragment = new QuestionsFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_questions, fragment);
                                fragmentTransaction.commit();
                            }
                        }, 1000);
                    }
                }
            });

            answerB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (truth) {
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
                    if (((QuestionsActivity) getActivity()).getTime() > 1) {
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
                        }, 1000);
                    }
                }
            });

            answerC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (truth) {
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
                    if (((QuestionsActivity) getActivity()).getTime() > 1) {
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
                        }, 1000);
                    }
                }
            });

            answerD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (truth) {
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
                    if (((QuestionsActivity) getActivity()).getTime() > 1) {
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
                        }, 1000);
                    }
                }
            });
            return myFragmentView;
        }
    }
}
