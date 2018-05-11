package kr.or.dgit.it.chapter13_14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GoDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_dialog);
    }

    public void finishDialog(View v) {
      finish();
    }
}
