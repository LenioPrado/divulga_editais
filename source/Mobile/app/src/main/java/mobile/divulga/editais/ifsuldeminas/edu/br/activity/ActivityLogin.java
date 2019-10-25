package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.LoginClickListener;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button entrar = findViewById(R.id.entrar);
        entrar.setOnClickListener(new LoginClickListener());
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityIndex.class);
        startActivity(i);
        finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
