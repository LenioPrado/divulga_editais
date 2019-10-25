package services;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.android.gcm.server.MulticastResult;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.NoticesCategory;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import services.divulga.editais.ifsuldeminas.edu.br.NoticeService;
import services.divulga.editais.ifsuldeminas.edu.br.UserService;
import utils.divulga.editais.ifsuldeminas.edu.br.HttpClientHelper;
import utils.divulga.editais.ifsuldeminas.edu.br.NotificationSender;

public class UserServiceTests {
	
	static HttpClientHelper clientHelper;
	static UserService userService;
	static NoticeService noticeService;
	
	@Before
	public void before() throws Exception {

	}
	
	@After
	public void closeEntityManager() throws Exception {

	}
	
	@BeforeClass
	public static void beforeClass() {
		clientHelper = new HttpClientHelper();
		userService = new UserService();		
		noticeService = new NoticeService();
	}
	
	@Test
	public void sendNotification() {		

		List<User> users = userService.listAll();
		List<Notice> notices = noticeService.listAll();
		Notice notice = notices.get(0);
		
		try {
			String noticeInfo = String.format("###NoticeId Id: %d -- Objeto: %s -- Número: %s", 
					notice.getNoticeId(), notice.getObject(), notice.getNumber());
			System.out.println(noticeInfo);
			for (NoticesCategory noticeCategory : notice.getNoticesCategories()) {
				Category category = noticeCategory.getCategory();
				String categoriesInfo = String.format("###Category Id: %d -- Name: %s", category.getCategoryId(), category.getDescription());
				System.out.println(categoriesInfo);
			}			
			MulticastResult result = new NotificationSender().sendMessage(users, notice);			
            System.out.println("Result: " + result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
