package utils.divulga.editais.ifsuldeminas.edu.br;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Sender;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.User;

public class NotificationSender extends Sender {
	
	private static final String serverKey = "AAAAPEJ1OOA:APA91bFFDgEgenfzAgFbB4mIO0RnC8jBDzxIRWJB3jquhlGB4S6wnuvbIgoCC83-zQF-_j0EkVNd7KwKh_fIgezAxevRB02t9-JYRMm3EY1FwUeVCrc4p8TPx6VCCPTuKmVi-QmxTeCB";
	
	public NotificationSender() {
        super(serverKey);
    }
	
	@Override
    protected HttpURLConnection getConnection(String url) throws IOException {
        String fcmUrl = "https://fcm.googleapis.com/fcm/send";
        return (HttpURLConnection) new URL(fcmUrl).openConnection();
    }

	public MulticastResult sendMessage(List<User> users, Notice notice) throws IOException {

		Notification notification = new Notification.Builder("myicon")
			     .title("Divulga Editais")
			     .body("Um novo edital foi publicado!")
			     .build();
		Message message = new Message.Builder()
				.collapseKey("message")
				.timeToLive(3)
	            .delayWhileIdle(true)
	            .notification(notification)
	            .addData("notice", String.valueOf(notice.getNoticeId()))
	            .build();		
		
		List<String> tokens = new ArrayList<>();
		for (User user : users) {
			String token = user.getToken();
			if(token != null && !token.isEmpty() && !tokens.contains(token)) {
				tokens.add(token);
				System.out.println("###### Enviar mensagem para o token ######: " + token);
			}
		}
		
		System.out.println("###### Total de mensanges a enviar ######: " + tokens.size());
		if(tokens.size() > 0) {
			return send(message, tokens, 1);
		}
		
		return null;	   
	}
}