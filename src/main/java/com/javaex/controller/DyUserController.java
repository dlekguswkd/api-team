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
public class DyUserController {

	@Autowired		
	private DyUserService dyUserService;
	
	/* 회원가입 */
	@PostMapping("/api/users")
	public JsonResult userJoin(@RequestBody DyUserVo dyUserVo) {	
		System.out.println("DyUserController.userJoin()");
		
		int count = dyUserService.exeUserJoin(dyUserVo);
		
		if(count != 1) { 		//등록안됨
			return JsonResult.fail("회원등록에 실패했습니다.");
		}else { 				//등록됨
			return JsonResult.success(count);
		}
		
	}
	
	

	/* 이메일 중복체크 */
	@PostMapping("/api/users/email/{userEmail}")
	public JsonResult emailCheck(@PathVariable(value="userEmail") String userEmail) {
		System.out.println("DyUserController.emailCheck()");
		System.out.println(userEmail);
		
		boolean can = dyUserService.exeEmailCheck(userEmail);
		
		if(can == false) { 		//등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { 				//등록됨
			return JsonResult.success(can);
		}
		
	}
	
	/* 닉네임 중복체크 */
	@PostMapping("/api/users/name/{userName}")
	public JsonResult nameCheck(@PathVariable(value="userName") String userName) {
		System.out.println("DyUserController.nameCheck()");
		System.out.println(userName);
		
		boolean can = dyUserService.exeNameCheck(userName);
		
		if(can == false) { 		//등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { 				//등록됨
			return JsonResult.success(can);
		}
		
	}
	
	

	/* 로그인 */
	@PostMapping("/api/users/login")
	public JsonResult userLogin(@RequestBody DyUserVo dyUserVo, HttpServletResponse response) {
		System.out.println("DyUserController.userLogin()");
		System.out.println(dyUserVo);
		DyUserVo authUser = dyUserService.exeLogin(dyUserVo);	// userEmail, userPw만 온다
		
		if(authUser != null ) { 	//로그인됨 	-> id, password  admin이면 관리자로 로그인됨?
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getUserNum());
			return JsonResult.success(authUser);	// userNum, userName만 온다
			
		}else { 				//로그인 안됨
			return JsonResult.fail("이메일 또는 비밀번호가 잘못되었습니다.");
		}
		
	}
	
	
	/* 헤더에 유저 포인트 */
	@GetMapping("/api/user/points")
	public JsonResult UserPoints(@RequestParam(value = "userNum") int userNum) {
		System.out.println("DyUserController.UserPoints()");
	    int historyPoint = dyUserService.getUserPoints(userNum);

	    // 포인트 정보 반환
	    return JsonResult.success(historyPoint);
	}
	
	
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
