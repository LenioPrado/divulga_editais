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
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int roleId;

	private String name;

	private String observation;

	//bi-directional many-to-one association to UsersRole
	@OneToMany(mappedBy="role")
	@JsonIgnore
	private List<UsersRole> usersRoles;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<UsersRole> getUsersRoles() {
		return this.usersRoles;
	}

	public void setUsersRoles(List<UsersRole> usersRoles) {
		this.usersRoles = usersRoles;
	}

	public UsersRole addUsersRole(UsersRole usersRole) {
		getUsersRoles().add(usersRole);
		usersRole.setRole(this);

		return usersRole;
	}

	public UsersRole removeUsersRole(UsersRole usersRole) {
		getUsersRoles().remove(usersRole);
		usersRole.setRole(null);

		return usersRole;
	}

}