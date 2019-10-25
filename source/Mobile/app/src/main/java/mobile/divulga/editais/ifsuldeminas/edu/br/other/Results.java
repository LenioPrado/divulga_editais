package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;

public interface Results {

    public void resultadoFiltro(List<Notice> resultado, Context context);

}
