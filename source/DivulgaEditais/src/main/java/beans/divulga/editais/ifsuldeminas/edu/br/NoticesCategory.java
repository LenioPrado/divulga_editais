package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the notices_categories database table.
 * 
 */
@Entity
@Table(name="notices_categories")
@NamedQuery(name="NoticesCategory.findAll", query="SELECT n FROM NoticesCategory n")
public class NoticesCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notice_category_id")
	private int noticeCategoryId;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")	
	private Category category;

	//bi-directional many-to-one association to Notice
	@ManyToOne
	@JoinColumn(name="notice_id")
	@JsonIgnore
	private Notice notice;

	public NoticesCategory() {
	}
	
	public NoticesCategory(Category category) {
		this.category = category;
	}

	public int getNoticeCategoryId() {
		return this.noticeCategoryId;
	}

	public void setNoticeCategoryId(int noticeCategoryId) {
		this.noticeCategoryId = noticeCategoryId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}