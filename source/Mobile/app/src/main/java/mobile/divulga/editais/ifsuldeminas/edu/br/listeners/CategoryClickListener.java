package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityCategoryDetails;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityNoticeDetails;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class CategoryClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View v) {
        Object[] tags = (Object[])v.getTag();
        Category category = (Category)tags[0];
        String currentTag = tags[1].toString();
        Activity host = (Activity) v.getContext();
        Intent i = new Intent(v.getContext(), ActivityCategoryDetails.class);
        i.putExtra("Category", category);
        i.putExtra(Utils.getCurrentTagKey(), currentTag);
        host.startActivity(i);
        host.finish();
    }
}
