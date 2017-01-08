package com.lqx.bean;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity
@Table(name="table_menu")

public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="col_menu_id")
	private Integer id;
	@Column(name="col_menu_name")
	private String menuName;
	@Column(name="col_menu_url")
	private String menuUrl;
	@Column(name="col_ishide")
	private boolean isHide=true;
	
	@OrderBy("col_index")
	@OneToOne(targetEntity=Menu.class,cascade={CascadeType.ALL})
	@JoinColumn(name="col_parent_id")
	private Menu parent;
	@Column(name="col_index")
	private Double index;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public boolean isHide() {
		return isHide;
	}
	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public Double getIndex() {
		return index;
	}
	public void setIndex(Double index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuUrl="
				+ menuUrl + ", isHide=" + isHide + ", parent=" + parent
				+ ", index=" + index + "]";
	}
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	

}
