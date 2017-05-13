package com.example.ksusha.kreativechallenge;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = AddChallengeActivity.class.getSimpleName();

    EditText etName, etDescription;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);

        etName = (EditText) findViewById(R.id.add_name);
        etDescription = (EditText) findViewById(R.id.add_description);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();

        DBHelper dbHelper = ChallengeActivity.getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put(ChallengesTable.COLUMN_CHALLENGE_NAME, name);
        cv.put(ChallengesTable.COLUMN_CHALLENGE_DESCRIPTION, description);

        long rowID = db.insert("challenges", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        db.close();

    }
}
