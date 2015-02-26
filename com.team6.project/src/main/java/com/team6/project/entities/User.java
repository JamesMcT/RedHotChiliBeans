package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * @author Cristiana
 * User mapping tables
 */
@Entity @Table(name = "Principals")
@SecondaryTable(name="Roles",pkJoinColumns = @PrimaryKeyJoinColumn(name="principal_id", referencedColumnName="principal_id"))
public class User implements Serializable{
    
    @Id
    @Column(name="principal_id")
    private String userId;
    @Column(name="password")
    private String password;
    @Column(name="user_role", table="Roles")
    private String role;
    @Column(name="group_role", table="Roles")
    private String group;
    
    public String toString(){
        return "UserId : "+userId+" Password : "+password+" User Role : "+role;
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
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    
    
    
}
