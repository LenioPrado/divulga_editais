package services.divulga.editais.ifsuldeminas.edu.br;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.Token;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.PasswordUtils;
import utils.divulga.editais.ifsuldeminas.edu.br.ProjectLogger;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@WebService
@Path("/user")
public class UserService extends BaseService<User> {
	
	public UserService() {
		super(User.class);
	}
	
	@Override
	protected User beforeCreate(User user) {
		String password = user.getPassword();
		try {
			String cryptoPassword = PasswordUtils.encriptyPassword(password);
			user.setPassword(cryptoPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/roles")
    public Response getLoggedUserRoles() {
		User user = UserUtils.getUserInSession(getSession());
		if(user!=null) {
			String json = "";
			try {
				json = getJsonFormattedObject(user.getUsersRoles());
				return Response.ok().entity(json).build();
			} catch (IOException e) {
				e.printStackTrace();
				return Response.serverError().entity(e.getMessage()).build();	
			}
		} else {
			Response.serverError().entity("Usuário não logado!").build();
		}
		return Response.ok().build();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/usersRoles")
    public Response getUsersRoles() {
		User user = UserUtils.getUserInSession(getSession());
		if(user!=null) {
			String json = "";
			try {
				json = getJsonFormattedObject(user.getUsersRoles());
				return Response.ok().entity(json).build();
			} catch (IOException e) {
				e.printStackTrace();
				return Response.serverError().entity(e.getMessage()).build();	
			}
		} else {
			Response.serverError().entity("Usuário não logado!").build();
		}
		return Response.ok().build();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/token")
    public Response token(final Token data){		
		ProjectLogger.log.info("###Token###");
		System.out.println(String.format("Email: %s - Token: %s", data.email, data.token));	    
	    
		User user = get("SELECT t FROM User t WHERE t.email = '" + data.email + "'");
		
		if (user != null) {
			user.setToken(data.token);
			super.edit(user);			
		}
		return Response.ok().entity(user).build();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public Response logout() {
		UserUtils.removeUserFromSession(getSession());
		return Response.ok().build();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login/{userEmail}/{userPassword}")
    public Response login(@PathParam("userEmail") String userEmail, @PathParam("userPassword") String userPassword) {
		ProjectLogger.log.info("login ");
		User user = get("SELECT t FROM User t WHERE t.email = '" + userEmail + "'");
		
		if (user != null) {
			boolean passwordIsValid = false;
			
			try {
				passwordIsValid = PasswordUtils.passwordMatchTest(userPassword, user.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return Response.serverError().entity(e.getMessage()).build();
			}
			
			if(passwordIsValid) {				
				ProjectLogger.log.info("Usuário Logado!!!!!");
				UserUtils.saveUserInSession(getSession(), user);
				String json = "";
				try {
					json = getJsonFormattedObject(user);
				} catch (IOException e) {
					e.printStackTrace();
					return Response.serverError().entity(e.getMessage()).build();	
				}
				return Response.ok().entity(json).build();
			} else {
				// Senha inválida
				ProjectLogger.log.info("invalid password ");
				UserUtils.removeUserFromSession(getSession());
				return Response.serverError().entity("Os dados de acesso informados estão incorretos!").build();
			}
		} else {
			// Conta inexistente
			ProjectLogger.log.info("account not found ");
			UserUtils.removeUserFromSession(getSession());
			return Response.serverError().entity("Não há uma conta associada ao e-mail informado!").build();
		}
    }
    
    public List<User> listUsersByCategories(List<Category> categories) {

    	String categoriesIds = "";
    	for (int i = 0; i < categories.size(); i++) {
    		categoriesIds += categories.get(i).getCategoryId();
    		if(i < categories.size() - 1) {
    			categoriesIds += ",";
    		}
		}
    	
		String query = "SELECT DISTINCT uc.user FROM UsersCategory uc WHERE uc.category.categoryId IN ( " +
				categoriesIds + " ) ";
    	
    	
		return super.listFiltering(query);
    }
}
