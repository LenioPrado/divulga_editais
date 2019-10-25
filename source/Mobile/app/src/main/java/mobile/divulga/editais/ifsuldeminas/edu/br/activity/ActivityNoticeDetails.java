package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.DownloadNoticeClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.listeners.SubUnsubscribeNoticeClickListener;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Category;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.NoticesCategory;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class ActivityNoticeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_details);

        Bundle bundle = getIntent().getExtras();
        Notice notice = (Notice)bundle.get("Notice");
        String currentTag = bundle.get(Utils.getCurrentTagKey()).toString();

        showNoticeDetails(notice);

        Button open = findViewById(R.id.abrir);
        open.setTag(notice);
        open.setOnClickListener(new DownloadNoticeClickListener());

        Button subUnsubscribe = findViewById(R.id.subUnsubscribeNotice);
        if(Utils.isTagScreenAllNotices(currentTag)){
            subUnsubscribe.setText("Inscrever");
            subUnsubscribe.setTag(Utils.getSubscribeAction());
        } else {
            subUnsubscribe.setText("Desinscrever");
            subUnsubscribe.setTag(Utils.getUnsubscribeAction());
        }

        subUnsubscribe.setOnClickListener(new SubUnsubscribeNoticeClickListener(notice));
    }

    protected void showNoticeDetails(Notice notice) {

        TextView modality = findViewById(R.id.txtModalidade);
        TextView numero = findViewById(R.id.txtNumero);
        TextView objeto = findViewById(R.id.txtObjeto);
        TextView data = findViewById(R.id.txtData);
        TextView dataPubli = findViewById(R.id.txtDataPubli);
        TextView dataFecha = findViewById(R.id.txtDataFecha);
        TextView status = findViewById(R.id.txtStatus);
        TextView tipo = findViewById(R.id.txtTipo);

        String modalityText = String.format("%s - %s",
                notice.getModality().getAcronyms(), notice.getModality().getDescription());

        modality.setText(modalityText);
        numero.setText(notice.getNumber());
        objeto.setText(notice.getObject());
        data.setText(Utils.createDateFormatddMMyyyy(notice.getTradingDate()));
        dataFecha.setText(Utils.createDateFormatddMMyyyy(notice.getClosingDate()));
        dataPubli.setText(Utils.createDateFormatddMMyyyy(notice.getPublishingDate()));
        status.setText(notice.getStatus());

        String categoryText = "";
        for (int i=0;i<notice.getNoticesCategories().size();i++) {
            NoticesCategory noticeCategory = notice.getNoticesCategories().get(i);
            Category category = noticeCategory.getCategory();
            categoryText += category.getDescription();
            if(i<notice.getNoticesCategories().size()-1){
                categoryText += "-";
            }
        }

        tipo.setText(categoryText);
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
