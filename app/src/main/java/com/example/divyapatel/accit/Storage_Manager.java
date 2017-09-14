package com.example.divyapatel.accit;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by divyapatel on 13/09/17.
 */

public class Storage_Manager {
    Context context;
    public Storage_Manager(Context context) {
        this.context = context;
    }
    public boolean generalstoreQr(Bitmap bitmap,String name){
        File file= Environment.getExternalStorageDirectory();
        File file1=new File(file+"/generalimages/");
        file1.mkdirs();
        File file2=new File(file1,name+".png");
        OutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(file2);
            if(bitmap!=null){
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean volunteerqr(Bitmap bitmap,String name){
        File file= Environment.getExternalStorageDirectory();
        File file1=new File(file+"/volimages/");
        file1.mkdirs();
        File file2=new File(file1,name+".png");
        OutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(file2);
            if(bitmap!=null){
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
