package com.example.popupanimation;

import static java.util.concurrent.TimeUnit.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.Backgroud));
        }//status bar or the time bar at the top
        myDialog = new Dialog(this);

        textView=findViewById(R.id.text_timer);
        long duration = SECONDS.toMillis(05);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {

                String sDuration =String.format(Locale.ENGLISH,"%02d : %02d",
                        MILLISECONDS.toMinutes(l),
                        MILLISECONDS.toSeconds(l) -
                        MINUTES.toSeconds(MILLISECONDS.toMinutes(l)));
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {

                textView.setVisibility(View.GONE);
                showPopup(textView);

            }
        }.start();
    }

    public void showPopup(View view) {
        TextView close;
        TextView part;
        TextView name;
        float o = 0;
        myDialog.setContentView(R.layout.custompopup);
        close=myDialog.findViewById(R.id.popupclose);
        part=myDialog.findViewById(R.id.part);
        name=myDialog.findViewById(R.id.myname);
        part.setTranslationX(-800);
        part.setAlpha(o);
        part.animate().translationX(0).alpha(1).setDuration(2000).setStartDelay(200).start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}