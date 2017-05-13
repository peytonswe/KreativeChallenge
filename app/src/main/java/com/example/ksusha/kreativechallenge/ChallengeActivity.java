package com.example.ksusha.kreativechallenge;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    private ChallengeAdapter mAdapter;
    private ListView challengeListView;
    private List<Challenge> challengeList;
    private static DBHelper dbHelper;
    private Button addChallenge;

    public static String CHALLENGE_ID = "challenge_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        dbHelper = new DBHelper(this);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        challengeListView = (ListView) findViewById(R.id.list);

        //Add challenge button
        addChallenge = (Button) findViewById(R.id.add_challenge);
        addChallenge.setOnClickListener(this);

        mAdapter = new ChallengeAdapter(this, new ArrayList<Challenge>());

        challengeListView.setAdapter(mAdapter);


        challengeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ChallengeActivity.this, InfoChallengeActivity.class);
                intent.putExtra(CHALLENGE_ID, challengeList.get(position).getID());
                startActivity(intent);

            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ChallengeActivity.this, AddChallengeActivity.class);
        startActivity(intent);

    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }
}
