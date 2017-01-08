package com.lqx.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="table_admin")
public class Admin implements Serializable,UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="col_user_id")
	private Integer id;
	@Column(name="col_user_name")
	private String username;
	@Column(name="col_user_password")
	private String password;
	
	private boolean enabled=true;
	@OneToMany(mappedBy="admin",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	
	private List<Authority> auth=new ArrayList<Authority>();
	@ManyToOne(targetEntity=Department.class,cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="col_department_id")
	private Department depart;
	@Column(name="col_chinese_name")
	private String chineseName;
	@Column(name="col_user_email")
	private String email;
	
	public List<Authority> getAuth() {
		return auth;
	}

	public void setAuth(List<Authority> auth) {
		this.auth = auth;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < auth.size(); i++) {
			list.add(new SimpleGrantedAuthority("ROLE_"+auth.get(i).getAuthorityName()));
		}
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", chineseName="
				+ chineseName + ", email=" + email + "]";
	}

	
	

}
