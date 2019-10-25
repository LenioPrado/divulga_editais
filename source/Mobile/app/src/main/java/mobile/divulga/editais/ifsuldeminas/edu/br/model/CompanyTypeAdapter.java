package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

public class CompanyTypeAdapter extends ArrayAdapter<CompanyType> {

    LayoutInflater flater;

    private static class ViewHolder {
        private TextView itemView;
    }

    public CompanyTypeAdapter(Activity context, List<CompanyType> items) {
        super(context, R.layout.company_type_list, R.id.description, items);
        flater = context.getLayoutInflater();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        CompanyType companyType = getItem(position);

        if (convertView == null) {
            convertView = flater.inflate(R.layout.company_type_list, parent, false);
        }

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(companyType.getDescription());

        return convertView;
    }
}
