package kr.or.dgit.it.chapter13_14.database;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.or.dgit.it.chapter13_14.R;

public class Mission01Holder {
    public ImageView personImageView;
    public TextView nameView;
    public TextView dateView;
    public ImageView dialerImageView;

    public Mission01Holder(View root) {
        personImageView=(ImageView)root.findViewById(R.id.main_item_person);
        nameView=(TextView)root.findViewById(R.id.main_item_name);
        dateView=(TextView)root.findViewById(R.id.main_item_date);
        dialerImageView=(ImageView)root.findViewById(R.id.main_item_dialer);
    }
}