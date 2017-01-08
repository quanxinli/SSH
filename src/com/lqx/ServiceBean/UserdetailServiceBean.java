package com.lqx.ServiceBean;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lqx.Service.AdminService;
import com.lqx.bean.Admin;
import com.lqx.utils.SqlText;
public class UserdetailServiceBean  implements UserDetailsService{
	@Resource
	private AdminService adminService;
	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		System.out.println("username.........."+name);
		Admin admin = (Admin) adminService.get(new SqlText("where o.username=?", name));
		/*java.util.List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		List<com.lqx.bean.Authority> setRole = admin.getAuth();
		for (int i = 0; i < setRole.size(); i++) {
			list.add(new SimpleGrantedAuthority("ROLE_"+setRole.get(i).getAuthorityName()));
		}
		System.out.println(admin.toString());
		admin.setAuth(list);*/
		// TODO Auto-generated method stub
		System.out.println(admin.getAuthorities());
		return admin;
	}

}
