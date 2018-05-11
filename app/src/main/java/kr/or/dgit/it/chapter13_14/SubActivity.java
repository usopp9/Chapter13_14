package kr.or.dgit.it.chapter13_14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    private static final String TAG="LifeCycleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"       Child - onCreate:");
        super.onCreate(savedInstanceState);

        Button btn = new Button(this);
        btn.setText("종료");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setContentView(btn);
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"       Child - onStart:");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"       Child - onResume:");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"       Child - onPause:");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"       Child - onStop:");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"       Child - onDestroy:");
        super.onDestroy();
    }
}
