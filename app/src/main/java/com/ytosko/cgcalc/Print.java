package com.ytosko.cgcalc;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Print extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;
    Vibrator o;
    String final1;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar a = getSupportActionBar();
        a.hide();
        final String result = getIntent().getStringExtra("Key2");
        final String Show = getIntent().getStringExtra("Key1");
        final Button x = (Button)findViewById(R.id.PN);
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText institute = (EditText)findViewById(R.id.inst);
        final EditText dept = (EditText)findViewById(R.id.dept);
        final EditText session = (EditText)findViewById(R.id.ss);
        TextView show1= (TextView)findViewById(R.id.show1);
        final EditText mail = (EditText)findViewById(R.id.mail);
        o = (Vibrator)this.getSystemService( Context.VIBRATOR_SERVICE);
        show1.setText(Show);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o.vibrate(60);
                s1 = name.getText().toString().trim();
                String s2 = institute.getText().toString().trim();
                String s3 = dept.getText().toString().trim();
                String s4 = session.getText().toString().trim();
                String s5 = mail.getText().toString().trim();
                final1 = "Name : " + s1 + "\n" + "Institute : " + s2 + "\n" + "Department : " + s3 + "\n" + "Session : " + s4 + "\n" + "E-mail : " + s5 + "\n\n\n" + Show + "\n\n\n" + "Your CGPA : " + result;
                if (s1.trim().equals("") || s2.trim().equals("") || s3.trim().equals("") || s4.trim().equals("") || s5.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields can not be empty", 1000).show();
                }
                else {
                   if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                       if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] p ={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(p , STORAGE_CODE);
                       }
                       else{
                       savePDF();
                       }
                   }
                   else{
                      savePDF();
                   }
                }
            }
        });

    }

    private void savePDF() {
        Document mDoc = new Document();
        String filename = new SimpleDateFormat("ddmmyyyy_hhmm",
                Locale.getDefault()).format(System.currentTimeMillis());
        File Filef = new File(Environment.getExternalStorageDirectory() + "/CGPA Calculator by Xavii");
        if(!Filef.exists()){
            if(Filef.mkdir());
        }
        String File = Environment.getExternalStorageDirectory() + "/CGPA Calculator by Xavii/" +  s1 + "_CG_"+ filename + ".pdf";

        try{
            PdfWriter.getInstance( mDoc ,new FileOutputStream(File));
            mDoc.open();
            mDoc.addAuthor("Xavii");
            mDoc.add(new Paragraph(final1));
            mDoc.close();
            Toast.makeText(this , s1 + "_CG_"+ filename + ".pdf" + " is created in " + File , 1000).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Storage access is required",1000).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_CODE);
        }
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case STORAGE_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    savePDF();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Permission denied" ,1000).show();
                }
            }
        }
    }
}