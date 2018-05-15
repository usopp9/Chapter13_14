package kr.or.dgit.it.chapter13_14;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {
    private TextView tv;
    private AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler_message);
        setTitle(getIntent().getStringExtra("title"));

        tv = findViewById(R.id.main_textView);


    }
    public void btnStartClick(View view) {
        new MyAsyncTask().execute();
    }

    public void btnPauseClick(View view) {

    }

    class MyAsyncTask extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AsyncTaskActivity.this,"onPreExecute()",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... voids) {
            for(int i =0;i<5;i++){
                publishProgress(i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }


            return "Finish!!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
         //   Toast.makeText(AsyncTaskActivity.this,"onPostExecute() ->"+s,Toast.LENGTH_SHORT).show();
            tv.setText(s);
        }
    }
}
