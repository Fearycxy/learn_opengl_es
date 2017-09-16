package com.feary.util;

import android.util.Log;

/**
 * Created by feary on 2017/9/16.
 */

public class Logger {

    private static final boolean ON = true;

    public static void w(String tag, String s) {
        if (ON) {
            Log.w(tag, s);
        }
    }

    public static void e(String tag, String s) {
        if (ON) {
            Log.e(tag, s);
        }
    }

    public static void v(String tag, String s) {
        if (ON) {
            Log.v(tag, s);
        }
    }

    public static void d(String tag, String s) {
        if (ON) {
            Log.d(tag, s);
        }
    }

    public static void i(String tag, String s) {
        if (ON) {
            Log.i(tag, s);
        }
    }
}
