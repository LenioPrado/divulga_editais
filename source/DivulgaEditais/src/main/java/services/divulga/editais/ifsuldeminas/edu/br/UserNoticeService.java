package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import beans.divulga.editais.ifsuldeminas.edu.br.UsersNotice;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/userNotice")
public class UserNoticeService extends BaseService<UsersNotice> {

	public UserNoticeService() {
		super(UsersNotice.class);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
	public Response subscribe(Notice notice) {
		User user = UserUtils.getUserInSession(getSession());
    	UsersNotice userNotice = new UsersNotice();
    	userNotice.setNotice(notice);
    	userNotice.setUser(user);
		return super.edit(userNotice);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribeByUserId/{noticeId}/{userId}")
	public Response subscribeByUserId(@PathParam("noticeId") int noticeId, @PathParam("userId") int userId) {
		return super.edit(new UsersNotice(noticeId, userId));
	}

	protected String getEditSuccessMessage() {
		return "Inscrição Realizada com Sucesso!";
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribe")
	public Response deleteSubscribed(Notice notice) {
		User user = UserUtils.getUserInSession(getSession());
		String query = getDeleteQuery(notice.getNoticeId(), user.getUserId());
		return super.delete(query);
	}

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribeByUserId/{noticeId}/{userId}")
	public Response deleteSubscribedByUserId(@PathParam("noticeId") int noticeId, @PathParam("userId") int userId) {		
		String query = getDeleteQuery(noticeId, userId);
		return super.delete(query);
	}
	
	private String getDeleteQuery(int noticeId, int userId) {
		String query = "DELETE FROM UsersNotice un WHERE un.user.userId = " + userId + 
				" AND un.notice.noticeId = " + noticeId;
		return query;
	}
	
	protected String getDeleteSuccessMessage() {
		return "Inscrição Removida com Sucesso!";
	}	
}
