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
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by etudiant on 02/02/2015.
 */
public class QuestionsActivity extends ActionBarActivity {

    private int score = 0;
    private final int NB_QUESTIONS = 20;
    private int[] questionsPrecedentes = new int[NB_QUESTIONS];
    private String level;
    private TextView timerText;
    private Timer timer;

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
        timerText = (TextView) findViewById(R.id.timer_question);

        if(timer != null){
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(timerTask(), 1000, 1000);

    }

    private TimerTask timerTask(){
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                int currentTime = Integer.parseInt(timerText.getText().toString());
                if(currentTime > 0) {
                    final String finalTime = String.valueOf(currentTime - 1);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            timerText.setText(finalTime);
                        }
                    });
                }
                else{
                    timer.cancel();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_questions, new QuestionsFragment());
                    fragmentTransaction.commit();
                }
            }
        };
        return myTimerTask;
    }

    @Override
    protected void onStop(){
        super.onStop();
        timer.cancel();
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
            case R.id.action_graph_easy:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Débutant");
                startActivity(intent);
                return true;
            case R.id.action_graph_medium:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Intermédiaire");
                startActivity(intent);
                return true;
            case R.id.action_graph_hard:
                intent = new Intent(QuestionsActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Expert");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initializeQuestionsPrecedentes(int[] questionsPrecedentes){
        for(int i = 0;  i < questionsPrecedentes.length ; i++){
            questionsPrecedentes[i] = -1;
        }
    }

    public int getTime(){
        return Integer.parseInt(timerText.getText().toString());
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
