package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;

public class UsersRolePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userRoleId;

    private int userId;

    private int roleId;

    private java.util.Date startDate;

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