package com.lqx.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="talbe_authority")
public class Authority {
	@Id
	@GeneratedValue
	@Column(name="col_authority_id")
	private Integer id;
	@Column(name="col_authority_name")
	private String authorityName;
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="col_user_id")
	private Admin admin;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "Authority [id=" + id + ", authorityName=" + authorityName
				+ ", admin=" + admin + "]";
	}
	
	
}
