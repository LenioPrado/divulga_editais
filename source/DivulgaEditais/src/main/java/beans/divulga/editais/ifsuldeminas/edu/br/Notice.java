package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the notices database table.
 * 
 */
@Entity
@Table(name="notices")
@NamedQuery(name="Notice.findAll", query="SELECT n FROM Notice n")
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notice_id")
	private int noticeId;

	@Temporal(TemporalType.DATE)
	@Column(name="closing_date")
	private Date closingDate;

	@Column(name="file_name")
	private String fileName;

	private String number;

	private String object;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="trading_date")
	private Date tradingDate;

	//bi-directional many-to-one association to CompanyType
	@ManyToOne
	@JoinColumn(name="company_type_id")	
	private CompanyType companyType;

	//bi-directional many-to-one association to Modality
	@ManyToOne
	@JoinColumn(name="modality_id")
	private Modality modality;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="inserted_by")
	private User user;

	//bi-directional many-to-one association to NoticesCategory
	@OneToMany(mappedBy="notice", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<NoticesCategory> noticesCategories;

	//bi-directional many-to-one association to UsersNotice
	@OneToMany(mappedBy="notice")
	private List<UsersNotice> usersNotices;

	public Notice() {
	}
	
	public Notice(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Date getClosingDate() {
		return this.closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTradingDate() {
		return this.tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}

	public CompanyType getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Modality getModality() {
		return this.modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<NoticesCategory> getNoticesCategories() {
		return this.noticesCategories;
	}

	public void setNoticesCategories(List<NoticesCategory> noticesCategories) {
		this.noticesCategories = noticesCategories;
	}

	public NoticesCategory addNoticesCategory(NoticesCategory noticesCategory) {
		getNoticesCategories().add(noticesCategory);
		noticesCategory.setNotice(this);

		return noticesCategory;
	}

	public NoticesCategory removeNoticesCategory(NoticesCategory noticesCategory) {
		getNoticesCategories().remove(noticesCategory);
		noticesCategory.setNotice(null);

		return noticesCategory;
	}

	public List<UsersNotice> getUsersNotices() {
		return this.usersNotices;
	}

	public void setUsersNotices(List<UsersNotice> usersNotices) {
		this.usersNotices = usersNotices;
	}

	public UsersNotice addUsersNotice(UsersNotice usersNotice) {
		getUsersNotices().add(usersNotice);
		usersNotice.setNotice(this);

		return usersNotice;
	}

	public UsersNotice removeUsersNotice(UsersNotice usersNotice) {
		getUsersNotices().remove(usersNotice);
		usersNotice.setNotice(null);

		return usersNotice;
	}
}