package asiantech.dev.yalypro.Common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class Common {

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static int getHeightScreen(Context context) {
        Point size = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            windowManager.getDefaultDisplay().getSize(size);
            return size.y;
        } else {
            Display d = windowManager.getDefaultDisplay();
            return d.getHeight();
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static int getWidthScreen(Context context) {
        Point size = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            windowManager.getDefaultDisplay().getSize(size);
            return size.x;
        } else {
            Display d = windowManager.getDefaultDisplay();
            return d.getWidth();
        }
    }

    public static String saveBitmapToFile(Bitmap image) {
        // Store to tmp file
        String extr = Environment.getExternalStorageDirectory().toString();
        File mFolder = new File(extr + "/koebu");
        if (!mFolder.exists()) {
            mFolder.mkdir();
        }

        String s = "tmp.jpg";

        File f = new File(mFolder.getAbsolutePath(), s);

        String strMyImagePath = f.getAbsolutePath();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strMyImagePath;
    }
}
