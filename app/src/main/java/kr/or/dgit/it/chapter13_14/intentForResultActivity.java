package kr.or.dgit.it.chapter13_14;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

import kr.or.dgit.it.chapter13_14.db.DBHelper;

public class intentForResultActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 10;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> datas;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_for_result);
        setTitle(getIntent().getStringExtra("title"));

        //원본
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select location from tb_data where category='0'",null);
        datas = new ArrayList<>();
        while(cursor.moveToNext()){
            datas.add(cursor.getString(0));
        }
        db.close();

        //adapter
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        //listview
        listView = findViewById(R.id.addrList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                category = tv.getText().toString();

                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("category", category);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            String location = category+ ":"+ data.getStringExtra("location");
            Toast.makeText(this,location,Toast.LENGTH_SHORT).show();
        }
    }
}
