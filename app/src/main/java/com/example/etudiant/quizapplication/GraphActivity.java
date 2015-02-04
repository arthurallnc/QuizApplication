package com.example.etudiant.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

/**
 * Created by etudiant on 03/02/2015.
 */
public class GraphActivity extends ActionBarActivity {

    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent intent = getIntent();
        if(null != intent){
            level = intent.getStringExtra("LEVEL_KEY");
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        MyDataBase db = new MyDataBase(this);
        List<Score> scores = null;
        TextView textGraph = (TextView) findViewById(R.id.text_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph_activity);
        switch (level){
            case("Débutant"):
                textGraph.setText("Evolution à partir du niveau Débutant");
                textGraph.setTextSize(20);
                scores = db.showEasyScores();
                break;
            case("Intermédiaire"):
                textGraph.setText("Evolution à partir du niveau Intermédiaire");
                textGraph.setTextSize(18);
                scores = db.showMediumScores();
                break;
            case("Expert"):
                textGraph.setText("Evolution à partir du niveau Expert");
                textGraph.setTextSize(20);
                scores = db.showHardScores();
                break;
        }
        DataPoint[] dataPoint = new DataPoint[scores.size()];
        for (int i = 0; i < scores.size(); i++){
            dataPoint[i] = new DataPoint(scores.get(i).getId(), scores.get(i).getScore());
        }
        if (scores.size() <= 1){
            Toast.makeText(getApplicationContext(), "Graphique non disponible pour le moment", Toast.LENGTH_LONG).show();
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setTitle("Scores");
        graph.setTitle("Graphique d'évolution");
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
                intent = new Intent(GraphActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Débutant");
                startActivity(intent);
                return true;
            case R.id.action_graph_medium:
                intent = new Intent(GraphActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Intermédiaire");
                startActivity(intent);
                return true;
            case R.id.action_graph_hard:
                intent = new Intent(GraphActivity.this, GraphActivity.class);
                intent.putExtra("LEVEL_KEY", "Expert");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
