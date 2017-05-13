package com.example.ksusha.kreativechallenge;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class InfoChallengeActivity extends AppCompatActivity {

    final String LOG_TAG = InfoChallengeActivity.class.getSimpleName();

    TextView ratingInfo, nameInfo, descriptionInfo, dateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        ratingInfo = (TextView) findViewById(R.id.rating_text);
        nameInfo = (TextView) findViewById(R.id.name_text);
        descriptionInfo = (TextView) findViewById(R.id.description_text);
        dateInfo = (TextView) findViewById(R.id.date_text);


        DBHelper dbHelper = ChallengeActivity.getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        String challengeId = intent.getStringExtra(ChallengeActivity.CHALLENGE_ID);

        String selection = "_ID = ?";
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
        dbHelper.close();
    }
}
