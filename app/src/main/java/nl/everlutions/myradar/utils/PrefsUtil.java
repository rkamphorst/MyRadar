package nl.everlutions.myradar.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PrefsUtil implements Constants {

    private static final String TAG = PrefsUtil.class.getSimpleName();
    private static final String CANT_READ_PREF = "Context is null, cannot read preference: ";
    private static final String CANT_WRITE_PREF = "Context is null, cannot write preference: ";

    @SuppressLint("SdCardPath")
    public static void clearAllPreferences(Context ctx, String packageName) {
        ctx.getSharedPreferences(PREF_SETTINGS,
                Context.MODE_PRIVATE).edit().clear().commit();

        File sharedPreferences = new File("/portals/portals/" + packageName + "/shared_prefs/");
        try {
            FileUtils.deleteDirectory(sharedPreferences);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean readBool(Context ctx, String key, boolean defValue) {

        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            return settings.getBoolean(key, defValue);
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }

        return defValue;
    }

    static public void saveBool(Context ctx, String key, boolean value) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, value);

            editor.commit();
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static int readInt(Context ctx, String key, int defValue) {

        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            return settings.getInt(key, defValue);
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }

        return defValue;
    }

    static public void saveInt(Context ctx, String key, int value) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(key, value);
            editor.commit();
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static float readFloat(Context ctx, String key, float defValue) {

        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            return settings.getFloat(key, defValue);
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }

        return defValue;
    }

    static public void saveFloat(Context ctx, String key, float value) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putFloat(key, value);
            editor.commit();
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static long readLong(Context ctx, String key, long defValue) {

        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            return settings.getLong(key, defValue);
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }

        return defValue;
    }

    static public void saveLong(Context ctx, String key, long value) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong(key, value);
            editor.commit();
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static String readString(Context ctx, String key, String defValue) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            return settings.getString(key, defValue);
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }

        return defValue;
    }

    static public void saveString(Context ctx, String key, String value) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(key, value);
            editor.commit();
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static Object readObject(Context ctx, String key, Class<?> objectClass) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            String jsonObject = settings.getString(key, null);
            if (jsonObject != null) {
                ObjectMapper objectMapper = Utils.getObjectMapper();
                try {
                    return objectMapper.readValue(jsonObject, objectClass);
                } catch (IOException e) {
                    Log.e(TAG, "readObject failed " + key + ": " + e.getMessage());
                }
            }
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }
        return null;
    }

    public static void saveObject(Context ctx, String key, Object object) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            ObjectMapper objectMapper = Utils.getObjectMapper();
            try {
                String value = objectMapper.writeValueAsString(object);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(key, value);
                editor.commit();
            } catch (IOException e) {
                Log.e(TAG, "saveObject failed: " + e.getMessage());
            }
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    public static ArrayList<?> readObjectList(Context ctx, String key, TypeReference<?> objectClass) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            String jsonObject = settings.getString(key, null);
            if (jsonObject != null) {
                ObjectMapper objectMapper = Utils.getObjectMapper();
                try {
                    return objectMapper.readValue(jsonObject, objectClass);
                } catch (IOException e) {
                    Log.e(TAG, "readObjectList failed " + key + ": " + e.getMessage());
                }
            }
        } else {
            Log.e(TAG, CANT_READ_PREF + key);
        }
        return null;
    }

    public static void saveObjectList(Context ctx, String key, ArrayList<?> objectList) {
        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(PREF_SETTINGS,
                    Context.MODE_PRIVATE);
            ObjectMapper objectMapper = Utils.getObjectMapper();
            try {
                String value = objectMapper.writeValueAsString(objectList);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(key, value);
                editor.commit();
            } catch (IOException e) {
                Log.e(TAG, "saveObjectList failed: " + e.getMessage());
            }
        } else {
            Log.e(TAG, CANT_WRITE_PREF + key);
        }
    }

    static public void clearPreferences(Context ctx, String preferencesName) {
        Log.w(TAG, "clearPreferences(" + preferencesName + ")");

        if (ctx != null) {
            SharedPreferences settings = ctx.getSharedPreferences(preferencesName, 0);
            SharedPreferences.Editor editor = settings.edit();

            // Clear all fields
            editor.clear();
            // Commit the edits!
            editor.commit();
        } else {
            Log.e(TAG, "Content is null, cannot clear preferences");
        }
    }
}
