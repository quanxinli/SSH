package com.lqx.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="table_department")
public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="col_department_id")
	private Integer id;
	@Column(name="col_department_name")
	private String departmentName;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="depart")
	
	private List<Admin > amdin =new ArrayList<Admin>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Admin> getAmdin() {
		return amdin;
	}
	public void setAmdin(List<Admin> amdin) {
		this.amdin = amdin;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName
				+ ", amdin=" + amdin + "]";
	}
	
}
