package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private int roleId;

    private String name;

    private String observation;

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