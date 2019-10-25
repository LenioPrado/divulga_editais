package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityNoticeDetails;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class NoticeClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View v) {
        Object[] tags = (Object[])v.getTag();
        Notice notice = (Notice)tags[0];
        String currentTag = tags[1].toString();
        Activity host = (Activity) v.getContext();
        Intent i = new Intent(v.getContext(), ActivityNoticeDetails.class);
        i.putExtra("Notice", notice);
        i.putExtra(Utils.getCurrentTagKey(), currentTag);
        host.startActivity(i);
        host.finish();
    }
}
