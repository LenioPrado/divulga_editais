package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import beans.divulga.editais.ifsuldeminas.edu.br.UsersCategory;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/userCategory")
public class UserCategoryService extends BaseService<UsersCategory> {

	public UserCategoryService() {
		super(UsersCategory.class);
	}

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
	public Response subscribe(Category category) {
		UsersCategory userCategory = new UsersCategory();
		userCategory.setCategory(category);
		userCategory.setUser(UserUtils.getUserInSession(getSession()));

		return super.edit(userCategory);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribeByUserId/{categoryId}/{userId}")
	public Response subscribeByUserId(@PathParam("categoryId") int categoryId, @PathParam("userId") int userId) {
		return super.edit(new UsersCategory(categoryId, userId));
	}

	protected String getEditSuccessMessage() {
		return "Inscrição Realizada com Sucesso!";
	}	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribe")
	public Response deleteSubscribed(Category category) {
		User user = UserUtils.getUserInSession(getSession());
		String query = getDeleteQuery(category.getCategoryId(), user.getUserId());
		return super.delete(query);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribeByUserId/{categoryId}/{userId}")
	public Response deleteSubscribedByUserId(@PathParam("categoryId") int categoryId, @PathParam("userId") int userId) {
		String query = getDeleteQuery(categoryId, userId);
		return super.delete(query);
	}
	
	private String getDeleteQuery(int categoryId, int userId) {
		String query = "DELETE FROM UsersCategory uc WHERE uc.user.userId = " + userId + 
				" AND uc.category.categoryId = " + categoryId;
		return query;
	}

	protected String getDeleteSuccessMessage() {
		return "Inscrição Removida com Sucesso!";
	}	
}