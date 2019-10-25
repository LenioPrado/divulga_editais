package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityHome;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;
import mobile.divulga.editais.ifsuldeminas.edu.br.notification.InstanceTokenService;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class LoginClickListener implements View.OnClickListener{

    @Override
    public void onClick(final View v) {

        final Activity host = (Activity) v.getContext();

        EditText emailText = host.findViewById(R.id.txtEmail);
        EditText passwordText = host.findViewById(R.id.txtSenha);

        String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();

        if (email.trim().length() > 0 && password.trim().length() > 0) {
            login(email, password, v.getContext());
        } else {
            Toast.makeText(v.getContext(), "Digite um usuário e senha válidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(String email, final String password, final Context context){
        String endpoint = "user/login/" + email + "/" + password;

        new WebService<User>(User.class, context).querySingle(endpoint, null, RequestMethods.GET, new ResultCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    Session session = new Session(context);
                    session.createLoginSession(user.getUserId(), user.getSocialName(), user.getEmail(), password);
                    String token = session.getToken();
                    if(token != null && !token.isEmpty()){
                        sendRegistrationToServer(user.getEmail(), token, context);
                    } else {
                        redirectToHome(context);
                    }
                } else {
                    Toast.makeText(context, "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                String error = String.format("Erro ao realizar a operação: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendRegistrationToServer(String email, String token, final Context context) {
        String endpoint = "user/token";
        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
            json.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new WebService<User>(User.class, context).querySingle(endpoint, json, RequestMethods.POST, new ResultCallback<User>() {
            @Override
            public void onSuccess(User data) {
                redirectToHome(context);
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                e.printStackTrace();
                Toast.makeText(context, "Ocorreu um erro ao realizar a operação!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void redirectToHome(Context context){
        Intent i = new Intent(context, ActivityHome.class);
        Activity host = (Activity) context;
        host.startActivity(i);
        host.finish();
    }
}