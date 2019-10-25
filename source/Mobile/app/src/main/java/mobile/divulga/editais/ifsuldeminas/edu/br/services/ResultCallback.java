package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import com.android.volley.VolleyError;

public interface ResultCallback<T> {
    void onSuccess(T result);
    void onError(Exception e);
    void onVolleyError(VolleyError e);
}
