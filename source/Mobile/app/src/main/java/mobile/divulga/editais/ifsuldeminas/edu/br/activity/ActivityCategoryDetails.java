package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.SubUnsubscribeCategoryClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class ActivityCategoryDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_details);

        Bundle bundle = getIntent().getExtras();
        Category category = (Category)bundle.get("Category");
        String currentTag = bundle.get(Utils.getCurrentTagKey()).toString();

        showCategoryDetails(category);

        Button subUnsubscribe = findViewById(R.id.subUnsubscribeCategory);
        if(Utils.isTagScreenAllCategories(currentTag)){
            subUnsubscribe.setText("Inscrever");
            subUnsubscribe.setTag(Utils.getSubscribeAction());
        } else {
            subUnsubscribe.setText("Desinscrever");
            subUnsubscribe.setTag(Utils.getUnsubscribeAction());
        }

        subUnsubscribe.setOnClickListener(new SubUnsubscribeCategoryClickListener(category));
    }

    protected void showCategoryDetails(Category category) {
        TextView descricao = findViewById(R.id.txtDescricao);
        descricao.setText(category.getDescription());
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityHome.class);
        startActivity(i);
        this.finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
