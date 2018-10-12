package com.morpheus.ultrachip.Herramientas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesLogin
{
    private static final String USER = "USER";
    private static final String PASS = "PASS";
    private Context context;
    private static PreferencesLogin preferences;

    private PreferencesLogin(Context context)
    {
        this.context = context;
    }

    public static PreferencesLogin getInstance(Context context)
    {
        if(preferences == null)
            preferences = new PreferencesLogin(context);

        return preferences;
    }

    private SharedPreferences getSettings()
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Obtiene el usuario
    public String getUser()
    {
        return getSettings().getString(USER, null);
    }

    //Obtiene la contraseña
    public String getPass()
    {
        return getSettings().getString(PASS, null);
    }

    public void setValues(String user, String pass)
    {
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(USER, user);
        editor.putString(PASS, pass);
        editor.apply();
    }
}
