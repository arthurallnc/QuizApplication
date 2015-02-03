package com.example.etudiant.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by etudiant on 03/02/2015.
 */
public class GraphActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_graph);
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
                return true;
            case R.id.action_graph_medium:
                intent = new Intent(GraphActivity.this, GraphActivity.class);
                return true;
            case R.id.action_graph_hard:
                intent = new Intent(GraphActivity.this, GraphActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
