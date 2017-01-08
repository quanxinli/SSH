/*package com.lqx.utils;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.foxlink.mes.bean.Log;
import com.foxlink.mes.service.LogService;

@Component

public class logManager{
	@Resource
	private LogService logService;
	public void log(JoinPoint point){
		
		
		System.out.println(".....before");
		Object [] para = point.getArgs();
		for (int i = 0; i < para.length; i++) {
			System.out.println(para[i]);
		}
	}
	
	public Object afterlog(ProceedingJoinPoint  point) throws Throwable{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String username=null;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		Object [] para = point.getArgs();
		StringBuilder sb = new StringBuilder("(");
		
		for (int i = 0; i < para.length; i++) {
			if(para[i] instanceof Object ){
				System.out.println(para[i].toString());
			}
			sb.append(para[i]+",");
		}
		String IP = WebUtil.getIP(request);
		
		sb.append(")");
		//System.out.println("------------22222222222--------------------");
		Log log = new Log();
		log.setAdmin(username);
		log.setIP(IP);
		log.setContent(IP+"--"+username+"調用了"+point.getTarget().getClass().getSimpleName()+"類調用了"+point.getSignature().getName()+sb.toString());
		log.setCreateDate(new Date());
		logService.save(log);
		
		return point.proceed();
		
	}

}
*/