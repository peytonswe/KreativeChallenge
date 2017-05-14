package com.example.ksusha.kreativechallenge.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksusha.kreativechallenge.ApiService;
import com.example.ksusha.kreativechallenge.AppClass;
import com.example.ksusha.kreativechallenge.ChallengesTable;
import com.example.ksusha.kreativechallenge.DBHelper;
import com.example.ksusha.kreativechallenge.R;
import com.example.ksusha.kreativechallenge.entities.Challenge;

import java.text.SimpleDateFormat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = InfoChallengeActivity.class.getSimpleName();

    private TextView ratingInfo, nameInfo, descriptionInfo, dateInfo;
    //private DBHelper dbHelper;
    private Button btnSubmit, btnComplete;
    private Spinner spinnerRate;
    private Challenge challenge;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_challenge);

        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tManager.getDeviceId();

        ratingInfo = (TextView) findViewById(R.id.rating_text);
        nameInfo = (TextView) findViewById(R.id.name_text);
        descriptionInfo = (TextView) findViewById(R.id.description_text);
        dateInfo = (TextView) findViewById(R.id.date_text);

        //rate challenge
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        btnComplete = (Button) findViewById(R.id.btn_complete);
        btnComplete.setOnClickListener(this);

        //dbHelper = ChallengeActivity.getDbHelper();
        //SQLiteDatabase db = AppClass.getDbHelper().getWritableDatabase();

        Intent intent = getIntent();
        challenge = intent.getParcelableExtra(Challenge.class.getSimpleName());

        ratingInfo.setText(String.valueOf(challenge.getRating()));
        nameInfo.setText(challenge.getName());
        descriptionInfo.setText(challenge.getDescription());
        dateInfo.setText(formatDate(challenge.getDate()).toString());

        /*String selection = "_ID = ?";
        String[] selectionArgs = new String[] {challengeId};
        Cursor c = db.query("challenges", null, selection, selectionArgs, null, null, null);


        if (c.moveToFirst()) {
            do {
                ratingInfo.setText(String.valueOf(c.getDouble(c.getColumnIndex(ChallengesTable.COLUMN_CHALLENGE_RATING))));
                nameInfo.setText(c.getString(c.getColumnIndex(ChallengesTable.COLUMN_CHALLENGE_NAME)));
                descriptionInfo.setText(c.getString(c.getColumnIndex(ChallengesTable.COLUMN_CHALLENGE_DESCRIPTION)));
                dateInfo.setText(String.valueOf(c.getLong(c.getColumnIndex(ChallengesTable.COLUMN_CHALLENGE_DATE))));
            } while (c.moveToNext());
        } else {
            Log.d(LOG_TAG, "0 rows");
        }
        c.close();
        dbHelper.close();*/
    }

    private String formatDate(long dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    @Override
    public void onClick(View v) {

        spinnerRate = (Spinner) findViewById(R.id.spinner_rate);
        String selected = spinnerRate.getSelectedItem().toString();

        switch (v.getId()) {

            case R.id.btn_submit:
                ApiService.instance.getChallengeRating(challenge.getID(), selected, uuid).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_complete:
                ApiService.instance.getChallengeComplete(challenge.getID(), uuid).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(InfoChallengeActivity.this, ChallengeActivity.class);
                startActivity(intent);
                finish();
        }

    }
}
