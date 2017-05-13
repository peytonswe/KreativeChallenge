package com.example.ksusha.kreativechallenge;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = LoginActivity.class.getSimpleName();

    private EditText enterName, enterSurname;
    private Button btnSubmit;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enterName = (EditText) findViewById(R.id.enter_name);
        enterSurname = (EditText) findViewById(R.id.enter_surname);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        String userName = enterName.getText().toString();
        String userSurname = enterSurname.getText().toString();

        dbHelper = ChallengeActivity.getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put(UsersTable.COLUMN_USER_NAME, userName);
        cv.put(UsersTable.COLUMN_USER_SURNAME, userSurname);

        long rowID = db.insert("challenges", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        db.close();
    }
}
