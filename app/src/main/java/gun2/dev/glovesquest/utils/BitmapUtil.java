package gun2.dev.glovesquest.utils;

import android.graphics.Bitmap;

public class BitmapUtil {

    static public Bitmap resizeBitmapBaseHeight(Bitmap original, int height) {

        double scale = original.getHeight() / height;
        int resizeWidth = 100;
        if (scale == 0) {
            resizeWidth = original.getHeight();
        } else {
            resizeWidth = (int) (original.getWidth() / scale);
        }
        Bitmap result = Bitmap.createScaledBitmap(original, resizeWidth, height, false);
        if (result != original) {
            original.recycle();
        }
        return result;
    }

    static public Bitmap resizeBitmapBaseWidth(Bitmap original, int width) {

        double scale = (double)original.getWidth() / (double)width;
        int resizeHeight = 100;
        if (scale == 0) {
            resizeHeight = original.getHeight();
        } else {
            resizeHeight = (int) (original.getHeight() / scale);
        }

        Bitmap result = Bitmap.createScaledBitmap(original, width, resizeHeight, false);
        if (result != original) {
            original.recycle();
        }
        return result;
    }


    static public Bitmap calcBitmapSizeBaseWidth(Bitmap original, int width, double baseWidth) {

        double widthScale = (original.getWidth() / baseWidth);

        double reWidth = width * widthScale;

        double scale = (double)original.getWidth() / reWidth;
        int resizeHeight = 100;
        if (scale == 0) {
            resizeHeight = original.getHeight();
        } else {
            resizeHeight = (int) (original.getHeight() / scale);
        }


        Bitmap result = Bitmap.createScaledBitmap(original, (int)reWidth, resizeHeight, false);
        if (result != original) {
            original.recycle();
        }
        return result;
    }
}
