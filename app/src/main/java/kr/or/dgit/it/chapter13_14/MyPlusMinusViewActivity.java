package kr.or.dgit.it.chapter13_14;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

class MyCircleView extends View{
    int color;
    StringBuffer sb = new StringBuffer();
    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(attrs!=null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyCircleView);
            color = a.getColor(R.styleable.MyCircleView_customColor,Color.RED);
        }
        for(int i =0;i<attrs.getAttributeCount();i++){
            sb.append(attrs.getAttributeName(i) + "=" + attrs.getAttributeValue(i));
        }
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width=0;
        if(widthMode==MeasureSpec.AT_MOST){
            width=700;
        }else if(widthMode==MeasureSpec.EXACTLY){
            width= widthSize;
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height=0;
        if(heightMode==MeasureSpec.AT_MOST){
            height=250;
        }else  if(heightMode== MeasureSpec.EXACTLY){
            height=heightSize;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.alpha(Color.CYAN));
        RectF rect = new RectF(15,15,160,160);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawArc(rect,0,360,false,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(35);
        canvas.drawText(sb.toString(),15,180,paint);
    }
}
public class MyPlusMinusViewActivity extends AppCompatActivity implements MyPlusMinusView.OnMyChangeListener{
    View barView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_my_plus_minus_view);
        setTitle(getIntent().getStringExtra("title"));

        MyPlusMinusView plusMinusView = findViewById(R.id.customView);
        barView = findViewById(R.id.barView);

        plusMinusView.setOnMyChangeListener(this);
       /* MyCircleView view = new MyCircleView(this);
        setContentView(view);*/
    }

    @Override
    public void onChange(int value) {
        if(value<0){
            barView.setBackgroundColor(Color.RED);
        }else if(value<30){
            barView.setBackgroundColor(Color.YELLOW);
        }else if(value<60){
            barView.setBackgroundColor(Color.BLUE);
        }else{
            barView.setBackgroundColor(Color.GREEN);
        }
    }
}
