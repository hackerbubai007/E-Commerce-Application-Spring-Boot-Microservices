package com.e_com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(unique = true)
	private String roleName; // USER, ADMIN

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleEntity(Long roleId, String roleName) {

		this.roleId = roleId;
		this.roleName = roleName;
	}

	public RoleEntity() {
		// TODO Auto-generated constructor stub
	}

}
