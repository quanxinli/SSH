package com.lqx.Service;

import java.util.List;

import com.lqx.bean.Admin;

public interface AdminService extends BaseService<Admin> {

	List<Admin> getAllUserOrderById();

	
	
}
