package ne.subgrupo_catorce.proyecto_final;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private final SharedPreferences prefs;
    private final String TOKEN_KEY = "token";
    private final String EMAIL_KEY = "email";
    private final String NAME_KEY = "first_names";
    private final String LASTNAME_KEY = "last_names";


    public Preferences(Context context) {
        prefs = context.getSharedPreferences("preferred_choices", Activity.MODE_PRIVATE);
    }

    private void saveString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String getString(String key) {
        return prefs.getString(key, "");
    }

    public void saveToken(String token) {
        saveString(TOKEN_KEY, token);
    }

    public String getToken() {
        return getString(TOKEN_KEY);
    }

    public void saveEmail(String email) {
        saveString(EMAIL_KEY, email);
    }

    public String getEmail() {
        return getString(EMAIL_KEY);
    }

    public void saveNames(String firstNames) {
        saveString(NAME_KEY, firstNames);
    }

    public String getNames() {
        return getString(NAME_KEY);
    }

    public void saveLastNames(String lastNames) {
        saveString(LASTNAME_KEY, lastNames);
    }

    public String getLastNames() {
        return getString(LASTNAME_KEY);
    }
}