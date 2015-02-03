package com.example.etudiant.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.level_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuestionsActivity.class);
                intent.putExtra("LEVEL_KEY", spinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
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
                intent = new Intent(HomeActivity.this, GraphActivity.class);
                return true;
            case R.id.action_graph_medium:
                intent = new Intent(HomeActivity.this, GraphActivity.class);
                return true;
            case R.id.action_graph_hard:
                intent = new Intent(HomeActivity.this, GraphActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
