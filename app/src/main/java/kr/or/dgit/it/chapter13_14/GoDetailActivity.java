package kr.or.dgit.it.chapter13_14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoDetailActivity extends AppCompatActivity implements View.OnClickListener {
    int count;
    TextView countVeiw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_detail);

        Button btn=(Button)findViewById(R.id.detail_btn);
        countVeiw=(TextView)findViewById(R.id.detail_count);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        count++;
        countVeiw.setText(String.valueOf(count));
    }
}
