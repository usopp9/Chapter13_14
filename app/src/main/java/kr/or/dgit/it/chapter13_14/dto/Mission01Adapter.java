package kr.or.dgit.it.chapter13_14.dto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.or.dgit.it.chapter13_14.R;
import kr.or.dgit.it.chapter13_14.database.Mission01Holder;

public class Mission01Adapter extends ArrayAdapter<Mission01VO>{
    Context context;
    int resId;
    ArrayList<Mission01VO> datas;

    public Mission01Adapter(Context context, int resId, ArrayList<Mission01VO> datas){
        super(context, resId);
        this.context=context;
        this.resId=resId;
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(resId, null);

            Mission01Holder wrapper=new Mission01Holder(convertView);
            convertView.setTag(wrapper);
        }

        Mission01Holder wrapper=(Mission01Holder)convertView.getTag();

        ImageView personImageView=wrapper.personImageView;
        TextView nameView=wrapper.nameView;
        TextView dateView=wrapper.dateView;
        ImageView diralerImageView=wrapper.dialerImageView;

        final Mission01VO vo=datas.get(position);

        nameView.setText(vo.name);
        dateView.setText(vo.date);

        if(vo.photo != null && vo.photo.equals("yes")){
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hong, null));
        }else {
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_person, null));
        }

        if(vo.phone != null && !vo.phone.equals("")){
            diralerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+vo.phone));
                    context.startActivity(intent);
                }
            });

        }
        return convertView;
    }
}
