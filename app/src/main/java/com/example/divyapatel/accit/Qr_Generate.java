package com.example.divyapatel.accit;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class Qr_Generate extends AppCompatActivity {

    Qr_Generator_class qr_generator_class;
    EditText editText;
    Storage_Manager storage;

    Dialog dia;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__generate);
        editText=(EditText)findViewById(R.id.text_et);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dia.dismiss();
                Toast.makeText(Qr_Generate.this, "okk done", Toast.LENGTH_SHORT).show();
            }
        };
        dia=new Dialog(this);
        dia.setCancelable(false);
        dia.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        dia.setContentView(R.layout.dialog_progress);
        storage=new Storage_Manager(this);
        qr_generator_class=new Qr_Generator_class(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Qr_Generate.this);
                dialog.setContentView(R.layout.my_qr_dialog);
                final EditText username_et=dialog.findViewById(R.id.username_et);
                final EditText lastname_et=dialog.findViewById(R.id.lastname_et);
                final EditText department_et=dialog.findViewById(R.id.department_et);
                final EditText year_et=dialog.findViewById(R.id.year_et);
                final EditText email_et=dialog.findViewById(R.id.email_et);
                final EditText number_et=dialog.findViewById(R.id.number_et);
                final EditText nickname_et=dialog.findViewById(R.id.nickname_et);
                Button submit=dialog.findViewById(R.id.submit_btn);

                submit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String firstname=username_et.getText().toString();
                        String lastname=lastname_et.getText().toString();
                        String department=department_et.getText().toString();
                        String year=year_et.getText().toString();
                        String email=email_et.getText().toString();
                        String number=number_et.getText().toString();
                        String nickname=nickname_et.getText().toString();

                        boolean flag=verify(firstname,lastname,department,year,email,number);
                        if(flag){

                            boolean a=storage.volunteerqr(qr_generator_class.volbitmap(firstname,lastname,department,year,email,number),firstname+" "+lastname+" "+nickname);

                            if(a){
                                Toast.makeText(Qr_Generate.this, "Done", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(Qr_Generate.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                dialog.setTitle("Qr Info");
                dialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    public void submittext(View view){

        /*Thread thread=new Thread(new MyThread());
        dia.show();
        thread.start();*/
        String s=editText.getText().toString();
        if(!s.isEmpty()){
            if(storage.generalstoreQr(qr_generator_class.createBitmap(s),s)){
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean verify(String firstname,String lastname,String department,String year,String email,String number){
        if(firstname.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }else if(lastname.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }else if(department.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }else if(year.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }else if(email.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }else if(number.isEmpty()){
            Toast.makeText(this, "Plz Fill Full Form", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private class MyThread implements Runnable{
        @Override
        public void run() {
            String s[]=getResources().getStringArray(R.array.studentinfo);
            for(String st:s){
                storage.generalstoreQr(qr_generator_class.createBitmap(st),st);
            }
            Message m=Message.obtain();
            handler.sendMessage(m);
        }
    }
}
