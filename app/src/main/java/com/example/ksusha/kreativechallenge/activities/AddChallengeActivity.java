package com.example.ksusha.kreativechallenge.activities;

import android.content.ContentValues;
import android.content.Context;
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
import com.example.ksusha.kreativechallenge.ChallengesTable;
import com.example.ksusha.kreativechallenge.DBHelper;
import com.example.ksusha.kreativechallenge.R;
import com.example.ksusha.kreativechallenge.entities.Challenge;
import com.example.ksusha.kreativechallenge.entities.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = AddChallengeActivity.class.getSimpleName();

    private EditText etName, etDescription;
    private Button btnAdd;
    private String uuid;
    //private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);

        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tManager.getDeviceId();

        etName = (EditText) findViewById(R.id.add_name);
        etDescription = (EditText) findViewById(R.id.add_description);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();


        ApiService.instance.addChallenge(name, description, 0, 0, uuid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

        /*dbHelper = ChallengeActivity.getDbHelper();
        SQLiteDatabase db = AppClass.getDbHelper().getWritableDatabase();

        cv.put(ChallengesTable.COLUMN_CHALLENGE_NAME, name);
        cv.put(ChallengesTable.COLUMN_CHALLENGE_DESCRIPTION, description);

        long rowID = db.insert("challenges", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        db.close();*/

    }
}
