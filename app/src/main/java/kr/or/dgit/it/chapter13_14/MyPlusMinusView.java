package kr.or.dgit.it.chapter13_14;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyPlusMinusView extends View {

    private Bitmap plusBitmap;
    private Bitmap minusBitmap;
    private Rect plusRectDst;
    private Rect minusRectDst;
    private int textColor;
    private int value;
    public static interface  OnMyChangeListener{
        void onChange(int value);
    }
    private ArrayList<OnMyChangeListener> listeners;
    private Context context;
    public void setOnMyChangeListener(OnMyChangeListener listener){
        listeners.add(listener);
    }
    public MyPlusMinusView(Context context) {
        super(context);
        this.context = context;
        init(null);


    }

    private void init(AttributeSet attrs) {
        //이미지 로딩
        plusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plus);
        minusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.minus);

        //터치포인트의 포함여부를 알기위한 면적
        plusRectDst = new Rect(10,10,210,210);
        minusRectDst = new Rect(400,10,600,210);

        if(attrs !=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyView);
           textColor = typedArray.getColor(R.styleable.MyView_customTextColor, Color.RED);
        }
        listeners = new ArrayList<>();
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.alpha(Color.CYAN));

        Rect plusRectSource = new Rect(0,0,plusBitmap.getWidth(),plusBitmap.getHeight());
        Rect minusRectSource = new Rect(0,0,minusBitmap.getWidth(),minusBitmap.getHeight());

        Paint paint = new Paint();
        canvas.drawBitmap(plusBitmap,plusRectSource,plusRectDst,null);

        paint.setTextSize(80);
        paint.setColor(textColor);
        canvas.drawText(String.valueOf(value),260,150,paint);

        canvas.drawBitmap(minusBitmap,minusRectSource,minusRectDst,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y  = (int)event.getY();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(plusRectDst.contains(x,y)){
                value++;
            }
            if(minusRectDst.contains(x,y)){
                value--;
            }
            invalidate();
            for(OnMyChangeListener listener:listeners){
                listener.onChange(value);
            }
            return true;
        }
        return super.onTouchEvent(event);
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
}
