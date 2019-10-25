package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class WebService<T> {

    private Context context;
    private Class<T> classType;
    private Gson gson;
    private ProgressBar progressBar;

    public WebService(Class<T> classType, Context context){
        this.context = context;
        this.classType = classType;
        createGsonBuilder();
        createDialog(context);
    }

    private void createGsonBuilder(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        gson = gsonBuilder.create();
    }

    private void createDialog(Context context){

        ContentFrameLayout layout = ((Activity)context).findViewById(android.R.id.content);

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar,params);

        ((Activity)context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void querySingle(String endpoint, JSONObject jsonObject, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Query Single Endpoint: ", url);
        if(jsonObject != null){
            Log.i("Query Single Json: ", jsonObject.toString());
        }

        JsonObjectRequest request = null;

        if(jsonObject != null){
            request = new JsonObjectRequest(requestMethod.getValue(), url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    processOnResponse(response.toString(), callback);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    processOnErrorResponse(error, callback);
                }
            });
        } else {
            request = new JsonObjectRequest(requestMethod.getValue(), url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    processOnResponse(response.toString(), callback);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    processOnErrorResponse(error, callback);
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
                @Override
                protected VolleyError parseNetworkError(VolleyError volleyError){
                    return processError(volleyError);
                }
            };
        }
        processRequestQueue(request);
    }

    public void queryList(String endpoint, JSONArray jsonArray, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Query List Endpoint: ", url);

        JsonArrayRequest request = new JsonArrayRequest(requestMethod.getValue(), url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                processOnResponse(response.toString(), callback);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processOnErrorResponse(error, callback);
            }
        }){
            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError){
                return processError(volleyError);
            }
        };

        processRequestQueue(request);
    }

    public void download(final String fileName, String endpoint, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Download Endpoint: ", url);

        InputStreamVolleyRequest request = new InputStreamVolleyRequest(requestMethod.getValue(), url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                processOnResponse(response, fileName, callback);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processOnErrorResponse(error, callback);
            }
        }, null)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError){
                return processError(volleyError);
            }
        };

        processRequestQueue(request);
    }

    private VolleyError processError(VolleyError volleyError){
        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }

        return volleyError;
    }

    private void processRequestQueue(Request<?> request){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        showDialog();
        requestQueue.add(request);
    }

    private void processOnResponse(byte[] response, String fileName, ResultCallback<T> callback){
        try{
            Utils.saveAppData(fileName, response, context);
            closeDialog();
            callback.onSuccess(null);
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }

    private void processOnResponse(String response, ResultCallback<T> callback){
        Log.i("#### PostLoaded: ", response);
        try {
            T entity = gson.fromJson(response, classType);
            Log.i("JSONObject to Object", entity.toString());
            closeDialog();
            callback.onSuccess(entity);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            callback.onError(e);
        }
    }

    private void processOnErrorResponse(VolleyError error, ResultCallback<T> callback){
        Log.e("#### PostError", error.toString());
        closeDialog();
        callback.onVolleyError(error);
    }

    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void closeDialog(){
        progressBar.setVisibility(View.GONE);
    }
}