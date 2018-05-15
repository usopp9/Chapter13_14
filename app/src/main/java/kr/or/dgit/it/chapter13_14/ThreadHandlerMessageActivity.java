package kr.or.dgit.it.chapter13_14;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThreadHandlerMessageActivity extends AppCompatActivity {
    private TextView tv;
    private Boolean loopFlag;
    private Boolean isRun;
    private MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler_message);
        setTitle(getIntent().getStringExtra("title"));

        tv = findViewById(R.id.main_textView);
        loopFlag= true;

        thread = new MyThread();

    }


    public void btnStartClick(View view) {

        isRun = true;
        thread.start();
    }

    public void btnPauseClick(View view) {
        isRun = false;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                tv.setText(String.valueOf(msg.arg1));
            }else if(msg.what==2){
                tv.setText((String)(msg.obj));
            }
        }
    };

    class  MyThread extends Thread{
        @Override
        public void run() {
           int count = 10;
           Message msg;
           while (loopFlag){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               if(isRun){
                   count--;
                   if(count==0){
                       msg = Message.obtain(handler,2,"Finish!!");
                       loopFlag=false;
                   }else{
                       msg = Message.obtain(handler,1,count,0);
                   }
                   handler.sendMessage(msg);
               }
           }
        }
    }

}
