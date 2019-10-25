package mobile.divulga.editais.ifsuldeminas.edu.br.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;

public class InstanceTokenService extends FirebaseInstanceIdService {

    public InstanceTokenService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        Log.i("######## onTokenRefresh", "onTokenRefresh");
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        new Session(this).saveToken(token);
    }
}