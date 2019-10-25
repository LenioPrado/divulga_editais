package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the users_roles database table.
 * 
 */
@Embeddable
public class UsersRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_role_id")
	private int userRoleId;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="role_id", insertable=false, updatable=false)
	private int roleId;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private java.util.Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private java.util.Date endDate;

	public UsersRolePK() {
	}
	public int getUserRoleId() {
		return this.userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsersRolePK)) {
			return false;
		}
		UsersRolePK castOther = (UsersRolePK)other;
		return 
			(this.userRoleId == castOther.userRoleId)
			&& (this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId)
			&& this.startDate.equals(castOther.startDate)
			&& this.endDate.equals(castOther.endDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userRoleId;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.startDate.hashCode();
		hash = hash * prime + this.endDate.hashCode();
		
		return hash;
	}
}