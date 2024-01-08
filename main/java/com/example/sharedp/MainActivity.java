package com.example.sharedp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2,ed3;
    Button savesp, clearsp, getsp;

    public static final String MyPREF="MyPref";
    public static final String name ="nameKey";
    public static final String phone="phoneKey";
    public static final String email="emailKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);

        savesp = findViewById(R.id.button);
        getsp = findViewById(R.id.getsp);
        clearsp = findViewById(R.id.clearsp);

        sharedPreferences=getSharedPreferences(MyPREF, Context.MODE_PRIVATE);

        savesp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = ed1.getText().toString();
                String p = ed2.getText().toString();
                String e = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(name,n);
                editor.putString(phone,p);
                editor.putString(email,e);
                editor.commit();
                Toast.makeText(MainActivity.this,"Data Saved Into SharedPreferences",Toast.LENGTH_LONG).show();

            }
        });

        clearsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(MainActivity.this,"SharedPreferences deleted!",Toast.LENGTH_LONG).show();
            }
        });

        getsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getAll().isEmpty()){
                    Toast.makeText(MainActivity.this,"SharedPreferences is empty!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this,"SharedPreferences not empty!",Toast.LENGTH_LONG).show();
                    String name = sharedPreferences.getString("nameKey", "");
                    String email = sharedPreferences.getString("emailKey", "");
                    String phone = sharedPreferences.getString("phoneKey", "");

                    ed1.setText(name.toString());
                    ed2.setText(phone.toString());
                    ed3.setText(email.toString());
                }

            }
        });
    }
}