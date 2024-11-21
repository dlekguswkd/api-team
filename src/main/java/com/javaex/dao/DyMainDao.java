package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.DyUserVo;

@Repository 
public class DyMainDao {
	
	@Autowired
	private SqlSession sqlSession;	

	/* 회원가입 */
//	public int insertUser(DyUserVo dyUserVo) {
//		System.out.println("DyUserDao.insertUser()");
//		
//		int count = sqlSession.insert("dyuser.insert", dyUserVo);
//		
//		return count;
//		
//	}
	

//    /* 헤더에 프로필사진 */
//    public DyUserVo getUserProfile(int userNum) {
//        System.out.println("DyUserDao.getUserProfile()");
//        return sqlSession.selectOne("dyuser.getUserData", userNum); 
//    }
//	
    
    
}
