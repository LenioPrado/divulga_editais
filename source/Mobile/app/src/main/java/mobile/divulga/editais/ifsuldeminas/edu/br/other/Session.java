package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityIndex;

public class Session {

    SharedPreferences pref;

    Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidUserPreferences";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "0";
    public static final String KEY_NAME = "";
    public static final String KEY_EMAIL = "";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_NOTICE = "notice";

    public Session(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(int userId, String name, String email, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID, userId);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void saveToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public void setNoticeId(int noticeId) {
        editor.putInt(KEY_NOTICE, noticeId);
        editor.commit();
    }

    public void removeNoticeId() {
        editor.remove(KEY_NOTICE);
        editor.commit();
    }

    public int getUserId() {
        return pref.getInt(KEY_ID, 0);
    }

    public String getUserName() {
        return pref.getString(KEY_NAME, "");
    }

    public String getUserEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public String getUserPassword() {
        return pref.getString(KEY_PASSWORD, "");
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "");
    }

    public int getNoticeId() {
        return pref.getInt(KEY_NOTICE, 0);
    }

    public boolean hasNoticeId() {
        return pref.contains(KEY_NOTICE);
    }

    private HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        redirectToIndex();
    }

    private void redirectToIndex(){
        Intent i = new Intent(context, ActivityIndex.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
