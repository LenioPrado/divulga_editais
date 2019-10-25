package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Path;

import beans.divulga.editais.ifsuldeminas.edu.br.CompanyType;

@Path("/companyType")
public class CompanyTypeService extends BaseService<CompanyType> {

	public CompanyTypeService() {
		super(CompanyType.class);
	}
	
	protected String getCreateErrorMessage(Exception e) {
		if (e.getMessage().toLowerCase().contains("duplicate")) {
			return "O valor informado para o campo Acronimo já existe!";
		}
		return e.getMessage();
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("notices")) {
			return "Não é possível excluir pois existem editais cadastrados com este tipo de companhia!";
		}
		return exception.getMessage();
	}	
}