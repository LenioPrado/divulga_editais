package services.divulga.editais.ifsuldeminas.edu.br;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.NoticesCategory;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.NotificationSender;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/notice")
public class NoticeService extends BaseService<Notice> {

	public NoticeService() {
		super(Notice.class);
	}
	
	public NoticeService(HttpServletRequest request) {
		super(Notice.class, request);
	}

	protected Notice beforeCreate(Notice notice) {
	    for (NoticesCategory noticeCategory : notice.getNoticesCategories()) {
	    	noticeCategory.setNotice(notice);
		}
    	notice.setPublishingDate(new Date());
    	User user = UserUtils.getUserInSession(getSession());
    	notice.setUser(user);
    	
		return notice;
	}
	
	protected Notice afterCreate(Notice notice) {
		List<Category> categories = new ArrayList<>();
		List<NoticesCategory> noticesCategories = notice.getNoticesCategories();
		if(noticesCategories != null) {			
			for (NoticesCategory noticesCategory : noticesCategories) {
				categories.add(noticesCategory.getCategory());
				int categoryId = noticesCategory.getCategory().getCategoryId();
				System.out.println("Enviar mensagem referente a categoria: " + categoryId);
			}
		}
		
		try {
			List<User> users = new UserService().listUsersByCategories(categories);
			new NotificationSender().sendMessage(users, notice);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return notice;
	}
	
	protected Notice beforeEdit(Notice notice) {
	    for (NoticesCategory noticeCategory : notice.getNoticesCategories()) {
	    	noticeCategory.setNotice(notice);
		}
    	
		return notice;
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("users_notices")) {
			return "Não é possível excluir o edital pois existem usuários inscritos!";
		}
		return exception.getMessage();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listNotSubscribed/{userId}")
    public List<Notice> listNoticesNotSubscribedByUserId(@PathParam("userId") int userId) {
		return listNotices("NOT IN", userId);
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listNotSubscribed")
    public List<Notice> listNoticesNotSubscribed() {
		User user = UserUtils.getUserInSession(getSession());
		return listNotices("NOT IN", user.getUserId());
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listSubscribed/{userId}")
    public List<Notice> listNoticesRegisteredByUserId(@PathParam("userId") int userId) {
		return listNotices("IN", userId);
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listSubscribed")
    public List<Notice> listNoticesRegisteredByUser() {
		User user = UserUtils.getUserInSession(getSession());
		return listNotices("IN", user.getUserId());
    }

    private List<Notice> listNotices(String queryPart, int userId) {		
		String query = "select t from Notice t WHERE t.noticeId " + queryPart +
				" (select un.notice.noticeId from UsersNotice un where un.user.userId = "+ userId +")"; 
		return listFiltering(query);
    }	
}