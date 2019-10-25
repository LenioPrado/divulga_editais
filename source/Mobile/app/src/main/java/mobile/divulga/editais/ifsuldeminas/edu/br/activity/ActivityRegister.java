package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.CompanyType;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.CompanyTypeAdapter;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class ActivityRegister extends AppCompatActivity {

    EditText email, password, socialName, fantasyName, cnpj, cnae, zipCode, address, number, complement, neighbourhood, city, phonePrimary, phoneSecundary, resposibleName, responsibleCPF;
    Spinner companyType, state;

    String[] listaEstados = new String[] {
            "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(getApplicationContext(), R.layout.activity_register, null);

        if(v == null){
            Log.i("Rrro ao recuperar VIEW", "É nulo");
        }

        setContentView(v);

        getInputsFromView();
        setButtonListeners();
        getCompanyTypes(getApplicationContext());
        fillStateSelect();
    }

    private void getInputsFromView(){
        socialName = findViewById(R.id.socialName);
        fantasyName = findViewById(R.id.fantasyName);
        cnpj = findViewById(R.id.cnpj);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        companyType = findViewById(R.id.companyType);
        cnae = findViewById(R.id.cnae);
        zipCode = findViewById(R.id.zipCode);
        address = findViewById(R.id.address);
        number = findViewById(R.id.number);
        complement = findViewById(R.id.complement);
        neighbourhood = findViewById(R.id.neighbourhood);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        phonePrimary = findViewById(R.id.phonePrimary);
        phoneSecundary = findViewById(R.id.phoneSecundary);
        resposibleName = findViewById(R.id.resposibleName);
        responsibleCPF = findViewById(R.id.responsibleCPF);
    }

    private void setButtonListeners(){

        Button cadastrar = findViewById(R.id.cadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View v) {
            if (inputDataIsValid()) {
                JSONObject user = getUserData();
                final Activity host = (Activity) v.getContext();
                String endpoint = "user/create";

                new WebService<User>(User.class, v.getContext()).querySingle(endpoint, user, RequestMethods.POST, new ResultCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        if (user != null) {
                            Toast.makeText(v.getContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(v.getContext(), ActivityLogin.class);
                            host.startActivity(i);
                            host.finish();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        String error = String.format("Erro desconhecido: %s", e.getMessage());
                        Toast.makeText(v.getContext(), error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVolleyError(VolleyError e) {
                        Toast.makeText(v.getContext(), "Ocorreu um erro ao realizar a operação!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            }
        });
    }

    private void getCompanyTypes(final Context context){
        String endpoint = "companyType";
        new WebService<CompanyType[]>(CompanyType[].class, ActivityRegister.this).queryList(endpoint, null, RequestMethods.GET, new ResultCallback<CompanyType[]>() {
            @Override
            public void onSuccess(CompanyType[] companyTypesArray) {
                Log.d("######", "GET Company Types Results");
                List<CompanyType> companyTypes = Arrays.asList(companyTypesArray);;
                if (companyTypes == null){
                    Toast.makeText(context, "Erro ao recuperar lista de tipos de empresa!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("###### Company Types", companyTypes.toString());
                    fillViewSelects(companyTypes);
                }
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                Toast.makeText(context, "Ocorreu um erro ao realizar a operação!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillViewSelects(List<CompanyType> companyTypes){
        CompanyTypeAdapter adapter = new CompanyTypeAdapter(this, companyTypes);
        Spinner listView = (Spinner) findViewById(R.id.companyType);
        listView.setAdapter(adapter);
    }

    private void fillStateSelect(){

        List<String> list = new ArrayList<String>();
        for(String s : listaEstados)
        list.add(s);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(dataAdapter);
    }

    private JSONObject getUserData(){
        JSONObject user = new JSONObject();
        try {
            user.put("socialName", socialName.getText().toString());
            user.put("fantasyName", fantasyName.getText().toString());
            CompanyType data = (CompanyType)companyType.getSelectedItem();
            user.put("companyType", data.getAcronyms());
            user.put("type", "empresa");
            user.put("email", email.getText().toString());
            user.put("password", password.getText().toString());
            user.put("cnpj", cnpj.getText().toString());
            user.put("state", state.getSelectedItem().toString());
            user.put("cnae", cnae.getText().toString());
            user.put("zipCode", zipCode.getText().toString());
            user.put("address", address.getText().toString());
            user.put("number", number.getText().toString());
            user.put("complement", complement.getText().toString());
            user.put("neighborhood", neighbourhood.getText().toString());
            user.put("city", city.getText().toString());
            user.put("phonePrimary", phonePrimary.getText().toString());
            user.put("phoneSecondary", phoneSecundary.getText().toString());
            user.put("responsibleName", resposibleName.getText().toString());
            user.put("responsibleCpf", responsibleCPF.getText().toString());

            Log.d("######", user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    private boolean inputDataIsValid(){
        if(socialName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Nome Social", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fantasyName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Nome Fantasia", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((password.getText().toString().equalsIgnoreCase(""))||(password.getText().toString().length()<5)){
            Toast.makeText(this, "Digite uma Senha válida", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cnpj.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CNPJ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(companyType.getSelectedItem() == null){
            Toast.makeText(this, "Selecione um Tipo de Empresa", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cnae.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CNAE", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(zipCode.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um CEP", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(address.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Endereço", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(number.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Numero", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(neighbourhood.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Bairro", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(city.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite uma cidade", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phonePrimary.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite um Telefone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(state.getSelectedItem().toString().equalsIgnoreCase("")|| state.getSelectedItem().toString().equalsIgnoreCase("Selecione")){
            Toast.makeText(this, "Selecione um Estado", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(resposibleName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite o nome do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(responsibleCPF.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Digite o CPF do responsável", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityIndex.class);
        startActivity(i);
        this.finish();
        super.onBackPressed();
    }
}