package com.d4ti.frengkas.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.d4ti.frengkas.sharedPreference.PreferencesUtility.*;

public class SaveSharedPreference {
    static SharedPreferences getPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static void setIdUser(Context context, int id_user){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(ID_USER, id_user);
        editor.apply();
    }

    public static void setStatus(Context context, String status){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(STATUS, status);
        editor.apply();
    }

    public static void setEmailUser(Context context, String email){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(EMAIL_USER, email);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context){
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static int getIdUser(Context context){
        return getPreferences(context).getInt(ID_USER, 0);
    }

    public static String getEmail(Context context){
        return getPreferences(context).getString(EMAIL_USER, "");
    }

    public static String getStatus(Context context){
        return getPreferences(context).getString(STATUS, "");
    }
}
