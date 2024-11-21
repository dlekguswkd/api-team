package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.DyUserDao;
import com.javaex.vo.DyUserVo;

@Service
public class DyMainService {
	
	@Autowired
	private DyUserDao dyUserDao;
	
	/* 회원가입 */
//	public int exeUserJoin(DyUserVo dyUserVo) {	 
//		System.out.println("DyUserService.exeUserJoin()");
//		
//		int count = dyUserDao.insertUser(dyUserVo);
//		
//		return count;
//		
//	}
	
    
	/* 헤더에 프로필사진 */
//    public DyUserVo getUserProfile(int userNum) {
//    	System.out.println("DyUserService.getUserProfile()");
//        return dyUserDao.getUserProfile(userNum);
//    }

}
