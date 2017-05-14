package com.example.ksusha.kreativechallenge.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ksusha.kreativechallenge.ApiService;
import com.example.ksusha.kreativechallenge.AppClass;
import com.example.ksusha.kreativechallenge.DBHelper;
import com.example.ksusha.kreativechallenge.R;
import com.example.ksusha.kreativechallenge.UsersTable;
import com.example.ksusha.kreativechallenge.entities.Challenge;
import com.example.ksusha.kreativechallenge.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = LoginActivity.class.getSimpleName();

    private EditText enterName, enterSurname;
    private Button btnSubmit;
    private ContentValues cv;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tManager.getDeviceId();

        if (isExist()) {
            Intent intent = new Intent(LoginActivity.this, ChallengeActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        enterName = (EditText) findViewById(R.id.enter_name);
        enterSurname = (EditText) findViewById(R.id.enter_surname);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String userName = enterName.getText().toString();
        String userSurname = enterSurname.getText().toString();


        ApiService.instance.register(userName, userSurname, uuid).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    User user = response.body();
                    createUser(user);
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createUser(User user) {
        cv = new ContentValues();

        //dbHelper = ChallengeActivity.getDbHelper();
        SQLiteDatabase db = AppClass.getDbHelper().getWritableDatabase();

        cv.put(UsersTable.COLUMN_USER_NAME, user.getUserName());
        cv.put(UsersTable.COLUMN_USER_SURNAME, user.getUserSurname());

        long rowID = db.insert("challenges", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        db.close();

        Intent intent = new Intent(LoginActivity.this, ChallengeActivity.class);
        startActivity(intent);
    }

    public boolean isExist() {
        SQLiteDatabase db = AppClass.getDbHelper().getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME, null);
        boolean exist = (c.getCount() > 0);
        c.close();
        db.close();
        return exist;
    }
}
