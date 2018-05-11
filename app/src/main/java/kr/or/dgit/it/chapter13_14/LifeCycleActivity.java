package kr.or.dgit.it.chapter13_14;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LifeCycleActivity extends AppCompatActivity {
    private static final String TAG="LifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate: ");
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("title"));

        Button btn = new Button(this);
        btn.setText("SubActivity Start");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);

            }
        });
        setContentView(btn);
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"onStart: ");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d(TAG,"onResume: ");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG,"onSaveInstanceState(Bundle): ");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d(TAG,"onSaveInstanceState(Bundle, PersistableBundle)");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG,"onRestoreInstanceState(Bundle): ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d(TAG,"onRestoreInstanceState(Bundle, PersistableBundle): ");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"onPause: ");
        super.onPause();
    }



    @Override
    protected void onStop() {
        Log.d(TAG,"onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy: ");
        super.onDestroy();
    }
}
