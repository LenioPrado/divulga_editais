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
 * The persistent class for the company_types database table.
 * 
 */
@Entity
@Table(name="company_types")
@NamedQuery(name="CompanyType.findAll", query="SELECT c FROM CompanyType c")
public class CompanyType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_type_id")
	private int companyTypeId;

	private String acronyms;

	private String description;

	//bi-directional many-to-one association to Notice
	@OneToMany(mappedBy="companyType")
	@JsonIgnore
	private List<Notice> notices;

	public CompanyType() {
	}

	public int getCompanyTypeId() {
		return this.companyTypeId;
	}

	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
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
		notice.setCompanyType(this);

		return notice;
	}

	public Notice removeNotice(Notice notice) {
		getNotices().remove(notice);
		notice.setCompanyType(null);

		return notice;
	}

}