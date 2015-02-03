package com.example.etudiant.quizapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsActivity extends ActionBarActivity {

    private int score = 0;
    private int[] questionsPrecedentes = new int[10];
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        if(null != intent){
            level = intent.getStringExtra("LEVEL_KEY");
        }
        initializeQuestionsPrecedentes(questionsPrecedentes);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_questions, new QuestionsFragment());
        fragmentTransaction.commit();
        setContentView(R.layout.activity_questions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_graph_easy:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                return true;
            case R.id.action_graph_medium:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                return true;
            case R.id.action_graph_hard:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initializeQuestionsPrecedentes(int[] questionsPrecedentes){
        int i = 0;
        for(i = 0;  i < questionsPrecedentes.length ; i++){
            questionsPrecedentes[i] = -1;
        }
    }

    public String getLevel(){
        return level;
    }

    public int getScore(){
        return  score;
    }

    public void setScore (int score){
        this.score = score;
    }

    public int[] getQuestionsPrecedentes(){
        return  questionsPrecedentes;
    }

    public void setQuestionsPrecedentes (int[] questionsPrecedentes){
        this.questionsPrecedentes = questionsPrecedentes;
    }
}
