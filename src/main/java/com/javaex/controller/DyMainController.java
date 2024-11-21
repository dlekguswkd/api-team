package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DyUserService;
import com.javaex.uti.JsonResult;
import com.javaex.uti.JwtUtil;
import com.javaex.vo.DyUserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController	
public class DyMainController {

	@Autowired		
	private DyUserService dyUserService;
	
	/* 회원가입 */
//	@PostMapping("/api/users")
//	public JsonResult userJoin(@RequestBody DyUserVo dyUserVo) {	
//		System.out.println("DyUserController.userJoin()");
//		
//		int count = dyUserService.exeUserJoin(dyUserVo);
//		
//		if(count != 1) { 		//등록안됨
//			return JsonResult.fail("회원등록에 실패했습니다.");
//		}else { 				//등록됨
//			return JsonResult.success(count);
//		}
//		
//	}
	
	
	
	/* 헤더에 프로필사진 */
//    @GetMapping("/api/user/{userNum}")
//    public JsonResult UserProfile(@PathVariable int userNum, HttpServletRequest request) {
//        System.out.println("DyUserController.UserProfile()");
//
//        // 사용자의 프로필 정보 조회
//        DyUserVo userProfile = dyUserService.getUserProfile(userNum);   
//
//        if (userProfile != null) {
//            return JsonResult.success(userProfile);
//        } else {
//            return JsonResult.fail("사용자 정보를 찾을 수 없습니다.");
//        }
//    }
}
