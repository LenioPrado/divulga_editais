package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Path;

import beans.divulga.editais.ifsuldeminas.edu.br.Modality;

@Path("/modality")
public class ModalityService extends BaseService<Modality> {

	public ModalityService() {
		super(Modality.class);
	}
	
	protected String getCreateErrorMessage(Exception e) {
		if (e.getMessage().toLowerCase().contains("duplicate")) {
			return "O valor informado para o campo Acr�nimo j� existe!";
		}
		return e.getMessage();
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("notices")) {
			return "N�o � poss�vel excluir pois existem editais cadastrados com esta modalidade!";
		}
		return exception.getMessage();
	}
}