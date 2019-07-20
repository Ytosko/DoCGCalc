package com.ytosko.cgcalc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Course_Code;
    private EditText Credit;
    private  EditText gpa;
    private Button add;
    private Button get;
    private Button reset;
    private TextView Display;
    private TextView GpaDis;
    private TextView CrDis;
    private TextView slDis;
    double cgpa;
    String Course;
    String c_Credit;
    String c_gpa;
    double sum = 0;
    double mul_sum = 0;
    int i = 1;
    int j = 1;
    Vibrator xyz;
    String s="Summary : \n";

    public MainActivity(){

    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ActionBar a = getSupportActionBar();
        a.hide();



        Course_Code = (EditText)findViewById(R.id.ccode);
        Credit = (EditText)findViewById(R.id.credit);
        gpa = (EditText)findViewById(R.id.gpa);
        add = (Button)findViewById( R.id.add);
        get = (Button)findViewById(R.id.get);
        Display = (TextView)findViewById(R.id.displaytView);
        GpaDis = (TextView)findViewById(R.id.GpaView);
        CrDis = (TextView)findViewById(R.id.creditView);
        reset = (Button)findViewById( R.id.reset );
        slDis = (TextView)findViewById(R.id.sln);
        xyz = (Vibrator)this.getSystemService( Context.VIBRATOR_SERVICE);
        Course_Code.addTextChangedListener( x );
        Credit.addTextChangedListener( x );
        gpa.addTextChangedListener( x );
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                int x = v.getId();
                if (x == R.id.add) {

                    if(xyz!=null && xyz.hasVibrator())
                        xyz.vibrate(60);
                    Course = Course_Code.getText().toString().trim();
                    c_Credit = Credit.getText().toString().trim();
                    c_gpa = gpa.getText().toString().trim();
                    if( c_Credit.trim().equals("") && c_gpa.trim().equals("")){
                        Toast.makeText( getApplicationContext(), "Please Input Valid Course Credit & GPA",1000 ).show();
                    }
                    else {
                        String a = "\n" + Course;
                        String b = "\n" + c_Credit;
                        String c = "\n" + c_gpa;
                        String d = "\n" + i++;
                        s += System.getProperty("line.separator") + j + ".Course : " + Course + "       Credit : " + c_Credit + "       GPA : " + c_gpa;
                        j++;
                        Double ab = Double.parseDouble( c_Credit );
                        Double bc = Double.parseDouble( c_gpa );
                        if(a.length() > 10)
                        {
                            Toast.makeText( getApplicationContext(), "Course Code can not be greater than 7 letter!",1000 ).show();
                            Toast.makeText( getApplicationContext(), "if it is greater use a sort form or keep it blank!",1000 ).show();
                        }
                        else if(bc >= 2.00 && bc <= 4.00 || bc == 0 )
                        {

                            mul_sum += ab * bc;
                            sum += ab;
                            cgpa = mul_sum / sum;
                            Display.append( a );
                            GpaDis.append( c );
                            CrDis.append( b );
                            slDis.append( d );
                            Course_Code.setText( "" );
                            Credit.setText( "" );
                            gpa.setText( "" );
                        }
                        else {
                            Toast.makeText( getApplicationContext(), "GPA is Invalid!",1000 ).show();

                        }
                    }
                }

            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(xyz!=null && xyz.hasVibrator())
                    xyz.vibrate(60);
                openActivity2();
                sum = 0;
                mul_sum = 0;
                Display.setText( "" );
                GpaDis.setText( "" );
                CrDis.setText( "" );
                slDis.setText( "" );
            }
        });
        reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                xyz.vibrate(100);
                mul_sum = 0;
                sum = 0;
                cgpa = 0;
                Display.setText( "" );
                GpaDis.setText( "" );
                CrDis.setText( "" );
                slDis.setText( "" );
            }
        } );
    }



    public void openActivity2() {
        Intent i = new Intent(this, com.ytosko.cgcalc.Display.class);
        String cg_c = Double.toString(cgpa);
        String cg_s = s;
        i.putExtra("Key" ,cg_c);
        i.putExtra("Key1" ,cg_s);
        startActivity(i);
    }
    private TextWatcher x = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String q = Credit.getText().toString().trim();
            String v = gpa.getText().toString().trim();
            add.setEnabled( !q.isEmpty() && !v.isEmpty() );

        }

        @Override
        public void afterTextChanged( Editable s) {

        }
    };
}
