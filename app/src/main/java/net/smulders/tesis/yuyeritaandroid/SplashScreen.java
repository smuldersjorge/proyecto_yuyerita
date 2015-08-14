package net.smulders.tesis.yuyeritaandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstances){
        super .onCreate(savedInstances);
        setContentView(R.layout.splash);

        Toast toast1 = Toast.makeText(getApplicationContext(),"El MSPyBS advierte que el uso indiscriminado de hierbas medicinales puede tener efectos nocivos para la salud",Toast.LENGTH_LONG);
        toast1.show();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent("net.smulders.tesis.yuyeritaandroid.MAINACTIVITY");
                    startActivity(i);


                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause(){
        super .onPause();
        finish();
    }
}
