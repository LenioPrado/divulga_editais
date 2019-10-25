package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the modalities database table.
 * 
 */
@Entity
@Table(name="modalities")
@NamedQuery(name="Modality.findAll", query="SELECT m FROM Modality m")
public class Modality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="modality_id")
	private int modalityId;

	private String acronyms;

	private String description;

	//bi-directional many-to-one association to Notice
	@OneToMany(mappedBy="modality")
	@JsonIgnore
	private List<Notice> notices;

	public Modality() {
	}

	public int getModalityId() {
		return this.modalityId;
	}

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
	}

	public String getAcronyms() {
		return this.acronyms;
	}

	public void setAcronyms(String acronyms) {
		this.acronyms = acronyms;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public Notice addNotice(Notice notice) {
		getNotices().add(notice);
		notice.setModality(this);

		return notice;
	}

	public Notice removeNotice(Notice notice) {
		getNotices().remove(notice);
		notice.setModality(null);

		return notice;
	}

}