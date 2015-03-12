package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

/**
 *User mapping tables
 * @author Cristiana 
 */

@NamedQuery(name="User.allUser", query="from User")

@Entity
@Table(name = "Principals")
@SecondaryTable(name = "Roles", pkJoinColumns = @PrimaryKeyJoinColumn(name = "principal_id", referencedColumnName = "principal_id"))
public class User implements Serializable {

    private static final long serialVersionUID = 3522038156117770929L;
  
    @Id
    @Column(name = "principal_id")
    private String userId;
    @Column(name = "password")
    private String password;
    
    @Column(name = "user_role", table = "Roles")
    private String role;

    /*
     * @Column(name="group_role", table="Roles") private String group;
     */
    public User() {}
    
    public User(String userId, String password, String role) {
        super();
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
    
    public String getKey(){
        return userId;
    }

    public String toString() {
        return "UserId : " + userId + " Password : " + password
                + " User Role : " + role;
    }

   

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
