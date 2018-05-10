package kr.or.dgit.it.chapter13_14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntentFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);
    }

    public void btnClick(View view) {
        Intent intent = new Intent();
        intent.setAction("kr.or.dgit.it.datapersistenceapplication.ACTION_BAR");
        startActivity(intent);
    }
}
