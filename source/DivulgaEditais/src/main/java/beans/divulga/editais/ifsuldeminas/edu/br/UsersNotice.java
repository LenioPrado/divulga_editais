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
 * The persistent class for the users_notices database table.
 * 
 */
@Entity
@Table(name="users_notices")
@NamedQuery(name="UsersNotice.findAll", query="SELECT u FROM UsersNotice u")
public class UsersNotice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_notice_id")
	private int userNoticeId;

	//bi-directional many-to-one association to Notice
	@ManyToOne
	@JoinColumn(name="notice_id")
	@JsonIgnore
	private Notice notice;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;

	public UsersNotice() {
	}
	
	public UsersNotice(int noticeId, int userId) {
		notice = new Notice(noticeId);
		user = new User(userId);
	}

	public int getUserNoticeId() {
		return this.userNoticeId;
	}

	public void setUserNoticeId(int userNoticeId) {
		this.userNoticeId = userNoticeId;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}