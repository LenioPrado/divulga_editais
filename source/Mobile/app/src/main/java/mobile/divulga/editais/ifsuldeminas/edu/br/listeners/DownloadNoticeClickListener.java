package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.io.File;

import mobile.divulga.editais.ifsuldeminas.edu.br.BuildConfig;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.Notice;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.RequestMethods;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class DownloadNoticeClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Notice notice = (Notice)v.getTag();
        String endpoint = "file/download/" + notice.getNoticeId() + "/" + notice.getFileName();

        downloadFile(v.getContext(), notice.getFileName(), endpoint);
    }

    private void downloadFile(final Context context, final String fileName, String endpoint){
        new WebService<Byte[]>(Byte[].class, context).download(fileName, endpoint, RequestMethods.GET, new ResultCallback<Byte[]>() {
            @Override
            public void onSuccess(Byte[] data){
                Toast.makeText(context, "Download do arquivo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                openFile(context, fileName);
            }

            @Override
            public void onError(Exception e) {
                String error = String.format("Erro desconhecido: %s", e.getMessage());
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolleyError(VolleyError e) {
                String error = "Ocorreu um erro ao realizar a operação!";
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openFile(Context context, String fileName){
        File file = new File(context.getFilesDir(), fileName);
        Log.i("Path:", file.getAbsolutePath());

        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(uri,"application/pdf");
        target.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent intent = Intent.createChooser(target, "Abrir Arquivo");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Instale um leitor de PDF para poder abrir o arquivo!", Toast.LENGTH_LONG).show();
        }
    }
}