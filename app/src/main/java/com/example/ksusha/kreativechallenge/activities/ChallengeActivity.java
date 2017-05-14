package com.example.ksusha.kreativechallenge.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.ksusha.kreativechallenge.ApiService;
import com.example.ksusha.kreativechallenge.AppClass;
import com.example.ksusha.kreativechallenge.ChallengeAdapter;
import com.example.ksusha.kreativechallenge.DBHelper;
import com.example.ksusha.kreativechallenge.R;
import com.example.ksusha.kreativechallenge.entities.Challenge;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = ChallengeActivity.class.getSimpleName();

    private ChallengeAdapter mAdapter;
    private ListView challengeListView;
    //private static DBHelper dbHelper;
    private Button addChallenge;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tManager.getDeviceId();
        //SQLiteDatabase sqLiteDatabase = AppClass.getDbHelper().getWritableDatabase();

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
                intent.putExtra(Challenge.class.getSimpleName(), mAdapter.getItem(position));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiService.instance.getChallenges(uuid).enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if (response.isSuccessful()) {
                    List<Challenge> challenges = response.body();
                    mAdapter.clear();
                    mAdapter.addAll(challenges);
                } else {
                    Log.e(LOG_TAG, "Error: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Log.e(LOG_TAG, "Error when loading challenges");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ChallengeActivity.this, AddChallengeActivity.class);
        startActivity(intent);

    }

}
