package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.LoginClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class ActivityIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Button cadastrar = findViewById(R.id.cadastrar);
        Button entrar = findViewById(R.id.entrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityRegister.class);
                startActivity(i);
                finish();
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(i);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        Session s = new Session(this);

        if(bundle != null && bundle.size() > 0 && bundle.containsKey("notice")){
            int noticeId = Integer.parseInt(bundle.get("notice").toString());
            bundle.remove("notice");
            s.setNoticeId(noticeId);
        }

        if(s.isLoggedIn()){
            String userName = s.getUserName();
            String email = s.getUserEmail();
            String password = s.getUserPassword();
            Log.d("##################", userName);
            Log.d("##################", email);
            Log.d("##################", password);
            new LoginClickListener().login(email, password, this);
        }
    }
}
