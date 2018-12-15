package gun2.dev.glovesquest.utils;

import android.util.Log;

/**
 * Logging Class
 */
public class LogManager {
    private String TAG = "unknown";
    private static final boolean DEBUG = true;


    public LogManager(String classname){
        TAG = classname;
    }

    public void msg(String msg){
        if(DEBUG) Log.i(TAG, msg);
    }
}
