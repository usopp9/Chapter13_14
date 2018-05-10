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

import java.util.ArrayList;

import kr.or.dgit.it.chapter13_14.db.DBHelper;

public class DetailActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        setTitle(category);

        //원본
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select location from tb_data where category=?",new String[]{category});
        datas= new ArrayList<>();
        while (cursor.moveToNext()){
            datas.add(cursor.getString(0));
        }
        db.close();
        //adapter
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,datas);

        //listview
        listView = findViewById(R.id.locList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                Intent intent = getIntent();
                intent.putExtra("location",tv.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
