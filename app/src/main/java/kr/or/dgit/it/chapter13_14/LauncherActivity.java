package kr.or.dgit.it.chapter13_14;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        setTitle(getIntent().getStringExtra("title"));
    }

    public void btnToggle(View view) {
        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume.....");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if(isInMultiWindowMode()){
                showToast("inResume....isInmultiWindowMode...yes....");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause........");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        showToast("onMultiWindowModeChanged"+isInMultiWindowMode);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            showToast("portrait......");
        }else{
            showToast("landscape.......");
        }
    }
}
