package com.mona.shamsolebad.asynctaskexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. create Async task object
        //2. implement abstract method from async task
        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editText);
    }

    public void startTask(View view) {
        int seconds=Integer.parseInt(editText.getText().toString());
        MyBackgroundTask task=new MyBackgroundTask(textView,seconds);
        task.execute();


        //Do not do this!
       // try{
        //Thread.sleep(5000);}
      //  catch (InterruptedException e){

      //  }
       // textView.setText("sleeping in main thread");
    }
}
