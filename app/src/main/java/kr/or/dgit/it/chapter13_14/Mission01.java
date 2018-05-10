package kr.or.dgit.it.chapter13_14;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.or.dgit.it.chapter13_14.database.DBHelper;
import kr.or.dgit.it.chapter13_14.dto.Mission01Adapter;
import kr.or.dgit.it.chapter13_14.dto.Mission01VO;

public class Mission01 extends AppCompatActivity {
    boolean callPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            callPermission=true;
        }

        if(!callPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }

        ListView listView=(ListView)findViewById(R.id.listDB);
        ArrayList<Mission01VO> datas=new ArrayList<>();

        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select name, photo, date, phone from tb_calllog", null);
        while (cursor.moveToNext()){
            Mission01VO vo=new Mission01VO();
            vo.name=cursor.getString(0);
            vo.photo=cursor.getString(1);
            vo.date=cursor.getString(2);
            vo.phone=cursor.getString(3);
            datas.add(vo);
        }
        db.close();

        Mission01Adapter adapter=new Mission01Adapter(this, R.layout.custom_item, datas);
        listView.setAdapter(adapter);
    }
}
