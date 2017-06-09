package pomocne;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteOrder;

import rs.aleph.android.jelovnik.R;

public class Slike {

    public int trenutni_bayt = 0;


    // convert from bitmap to byte array
    public static byte[] getBytes(ImageView slika) {
        if (slika.getDrawable().equals(null)) {
           return null;
        }else{

            slika.setDrawingCacheEnabled(true);
            slika.buildDrawingCache();
            Bitmap bm = slika.getDrawingCache();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            return byteArray;
        }


    }

    // convert from byte array to bitmap
    public static Bitmap byteAry2BMP(byte[] image) {
        Bitmap slika=null;
        try {
            slika= BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        catch (Exception  e){
            Log.e("BTMError",e.toString());
        }
        finally {
            return slika;
        }
    }

    // Prikaz slike u ImageView cntroli
    public void BMPImagefromByte(ImageView img, byte[] stavka) {
        BitmapFactory bmf = new BitmapFactory();
        ByteArrayInputStream byAis = new ByteArrayInputStream(stavka);
        int re = byAis.read();

        Bitmap bmpSlika = BitmapFactory.decodeStream(byAis);// (stavka, 0,
        // ukuvel);
        img.setImageBitmap(bmpSlika);

    }

		
    //Uzimanje slike sa SD kartice
    public void pictureFromByte(byte[] slika) {

        File sdcardPath = Environment.getExternalStorageDirectory();

        try {
            FileOutputStream fiOs = new FileOutputStream(
                    "/mnt/sdcard/slike/slikaIzbyteNiza.jpg");
            fiOs.write(slika);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.e("Greska: ", e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("Greska: ", e.toString());
        }

    }


    /**
     * Uzimanje slike iz fajlča tj postavkom fajl path
     * @param _putanja
     * @param _imeFajlaSlike
     * @return  Bitmap
     */
    public static Bitmap getSlikaIzFajla(String _putanja, String _imeFajlaSlike){
        File slikaFajl = new File(_putanja+_imeFajlaSlike);

        if(slikaFajl.exists()){

            Bitmap bitmapSlika = BitmapFactory.decodeFile(_putanja+_imeFajlaSlike);
            return bitmapSlika;

        }
         return null;
    }
}