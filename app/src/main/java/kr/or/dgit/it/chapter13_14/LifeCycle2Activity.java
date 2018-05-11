package kr.or.dgit.it.chapter13_14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LifeCycle2Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> datas;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle2);

        listView = findViewById(R.id.lcView);

        datas = new ArrayList<>();
        datas.add("onCreate......");
        adapter = new ArrayAdapter<>(this,android.R.layout.test_list_item,datas);
        listView.setAdapter(adapter);
    }

    public void goDetailBtnClick(View view) {
        Intent intent = new Intent(this,GoDetailActivity.class);
        startActivity(intent);
    }

    public void goDialogBtnClick(View view) {
        Intent intent = new Intent(this,GoDialogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        datas.add("onResume....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();

        datas.add("onPause....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        datas.add("onStart....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        datas.add("onStop....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        datas.add("onRestart....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        datas.add("onDestory....");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        datas.add("onSaveInstanceState....");
        adapter.notifyDataSetChanged();

        outState.putString("data1", "hello");
        outState.putInt("data2", 100);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        datas.add("onRestoreInstanceState....");
        adapter.notifyDataSetChanged();

        String data1=savedInstanceState.getString("data1");
        int data2=savedInstanceState.getInt("data2");

        Toast toast=Toast.makeText(this, data1+":"+data2, Toast.LENGTH_SHORT);
        toast.show();
    }
}
