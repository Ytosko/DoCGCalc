package com.ytosko.cgcalc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Display extends AppCompatActivity {
    private Button again;
    Vibrator x;
    String ss;
    String RR;

    DecimalFormat d = new DecimalFormat("#,#.###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_display);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar a = getSupportActionBar();
        a.hide();
        final String result = getIntent().getStringExtra("Key");
        final String Show1 = getIntent().getStringExtra("Key1");
        ss = Show1;
        double op = Double.parseDouble(result);
        TextView fine = (TextView)findViewById(R.id.GPA_dis);
        Button print = (Button)findViewById(R.id.Print);
        TextView Show = (TextView)findViewById(R.id.show);
        Show.setText(Show1);
        RR = d.format(op);
        fine.setText("" + d.format(op));

        again = (Button)findViewById( R.id.ag );
        x = (Vibrator)this.getSystemService( Context.VIBRATOR_SERVICE);

        again.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                x.vibrate( 60 );
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        } );
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.vibrate(60);
                createpdf();
            }
        });
    }
    private void createpdf() {
        Intent j = new Intent(this, Print.class);
        j.putExtra("Key1" , ss);
        j.putExtra("Key2" , RR);
        startActivity(j);
    }
}
