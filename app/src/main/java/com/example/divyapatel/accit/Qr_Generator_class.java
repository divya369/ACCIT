package com.example.divyapatel.accit;
import android.content.Context;
import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
/**
 * Created by divyapatel on 13/09/17.
 */
public class Qr_Generator_class {
    Context context;

    public Qr_Generator_class(Context context) {
        this.context = context;
    }
    public Bitmap createBitmap(String s){
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(s, BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Bitmap volbitmap(String firstname,String lastname,String department,String year,String email,String number)
    {
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(firstname+"\n"+lastname+"\n"+department+"\n"+year+"\n"+email+"\n"+number+"\n", BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String encrypt(String key,String value){
        return null;
    }
}
