package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class SubUnsubscribeCategoryClickListener implements View.OnClickListener {

    private final Category category;

    public SubUnsubscribeCategoryClickListener(Category category){
        this.category = category;
    }

    @Override
    public void onClick(final View v) {
        final String currentTag = v.getTag().toString();
        int userId = new Session(v.getContext()).getUserId();

        String endpoint = "userCategory/%sByUserId/"+ category.getCategoryId()+"/"+userId;

        if(Utils.isSubscribeAction(currentTag)){
            endpoint = String.format(endpoint, "subscribe");
        } else {
            endpoint = String.format(endpoint, "unsubscribe");
        }

        new WebService<Category>(Category.class, v.getContext()).querySingle(endpoint, null, RequestMethods.POST, new ResultCallback<Category>() {
            @Override
            public void onSuccess(Category category) {
                if (category != null) {
                    String message = "";

                    if(Utils.isSubscribeAction(currentTag)){
                        message = "Inscrição na categoria realizada com sucesso!";
                    } else {
                        message = "Inscrição na categoria removida com sucesso!";
                    }

                    Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
                    Activity activity = (Activity) v.getContext();
                    activity.onBackPressed();
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
