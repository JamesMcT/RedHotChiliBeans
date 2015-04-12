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
 * This class is for the entity called User and implements Serializable.
 * 
 * <p>
 * A table 'Principals' is created with the column 'principal_id' as a primary
 * key joining it to the table 'Roles'
 * 
 * <p>
 * There is one NamedQuery: User.allUser
 * 
 * @author Cristiana
 */

@NamedQuery(name = "User.allUser", query = "from User")
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

	/**
	 * The empty contructor creating the columns
	 * 
	 * @Column(name="group_role", table="Roles") private String group
	 */
	public User() {
	}

	/**
	 * The constructor for User
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 */
	public User(String userId, String password, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	/**
	 * This overrides the hashcode() method which uses 31 as a base
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/**
	 * This overrides the equals method comparing userId
	 * 
	 */
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

	/**
	 * Gets the Key as a String
	 * 
	 * @return userId
	 */
	public String getKey() {
		return userId;
	}

	/**
	 * Overrides the toString() method. It overrides to return the userId,
	 * password and role.
	 * 
	 */
	public String toString() {
		return "UserId : " + userId + " Password : " + password
				+ " User Role : " + role;
	}

	/**
	 * Gets the userId as a String
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the userId as a String
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the password as a String
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password as a String
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role as a String
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role as a String
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
