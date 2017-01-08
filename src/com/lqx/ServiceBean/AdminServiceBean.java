package com.lqx.ServiceBean;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lqx.Service.AdminService;
import com.lqx.bean.Admin;

@Service
public class AdminServiceBean extends BaseServiceBean<Admin> implements AdminService {
	@Override
	public List<Admin> getAllUserOrderById(){
		return getList("order by o.id");
	}
}
