package com.mona.shamsolebad.asynctaskexample;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MyBackgroundTask  extends AsyncTask<Void,Integer,String> {
    private WeakReference<TextView> mTextView;
    private int seconds;

    public MyBackgroundTask(TextView textView, int seconds) {
        mTextView = new WeakReference<>(textView);
        this.seconds = seconds;
    }

    @Override
    protected String doInBackground(Void... voids) { // varargs
        // tasks you want to do in the background
        for(int i = 0; i < this.seconds * 10; i++) {
            try {
                Thread.sleep( 100);
                // this method can be called from doInBackground() to publish
                // updates on the MainThread while the background work is still running.
                // each call to this method will trigger the execution of onProgressUpdate()
                // on the mainThread.

                publishProgress(((i + 1) * 100) / (seconds * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "I am back from a " + this.seconds + " secs nap!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // gets called during the background work process (by publicProgress();)
        mTextView.get().setText("Completed: " + values[0] + "%");

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // returns the value back to the main thread
        // we want to display returned text
        mTextView.get().setText(s);
    }
    //Async task<p1,p2,p3>
    //p1: the data type of parameters sent to the task upon executing the doInBackground() method
    //p2:progress:the data type of the progress units published using onProgressUpdated
    //p3:data type of the results delivered by the onPostExecute() method
}
