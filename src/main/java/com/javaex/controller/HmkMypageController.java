// MypageController.java
package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.HmkMypageService;
import com.javaex.uti.JsonResult;
import com.javaex.uti.JwtUtil;
import com.javaex.vo.HmkChallengeVo;
import com.javaex.vo.HmkChartDataVo;
import com.javaex.vo.HmkGiftVo;
import com.javaex.vo.HmkPointHistoryVo;
import com.javaex.vo.HmkPointSummaryVo;
import com.javaex.vo.HmkUserVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class HmkMypageController {
	private static final Logger logger = LoggerFactory.getLogger(HmkMypageService.class);

	@Autowired
	private HmkMypageService mypageService;

	// 사용자 정보 조회
	@GetMapping("/{userNum}")
	public JsonResult getUserInfo(@PathVariable int userNum) {
		HmkUserVo userInfo = mypageService.getUserInfo(userNum);
		HmkUserVo statsVo = mypageService.getUserChallengeStats(userNum);
		// 챌린지 상세 정보 가져오기
		List<HmkChallengeVo> ongoingChallenges = mypageService.getOngoingChallenges(userNum);
		List<HmkChallengeVo> upcomingChallenges = mypageService.getUpcomingChallenges(userNum);
		List<HmkChallengeVo> completedChallenges = mypageService.getCompletedChallenges(userNum);
		// 응답 데이터 구조화
		Map<String, Object> responseData = new HashMap<>();
		// 사용자 기본 정보
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("nickname", userInfo.getNickname());
		userInfoMap.put("region", userInfo.getRegion());
		userInfoMap.put("profileImage", userInfo.getProfileImage());
		// ownedProfileImages가 null인 경우를 체크하여 기본값을 설정하는 코드
		userInfoMap.put("ownedProfileImages", userInfo.getOwnedProfileImages() != null ? userInfo.getOwnedProfileImages() : "[]");

		if (statsVo != null) {
			// 통계 정보 추가(count)
			userInfoMap.put("ongoingChallenges", statsVo.getOngoingChallenges());
			userInfoMap.put("upcomingChallenges", statsVo.getUpcomingChallenges());
			userInfoMap.put("completedChallenges", statsVo.getCompletedChallenges());
			userInfoMap.put("participationScore", statsVo.getParticipationScore());
		} else {
			// 기본값 설정
			userInfoMap.put("ongoingChallenges", 0);
			userInfoMap.put("upcomingChallenges", 0);
			userInfoMap.put("completedChallenges", 0);
			userInfoMap.put("participationScore", 0);
		}

		// 챌린지 상세 데이터 추가
		Map<String, Object> challengesMap = new HashMap<>();
		challengesMap.put("ongoing", ongoingChallenges);
		challengesMap.put("upcoming", upcomingChallenges);
		challengesMap.put("completed", completedChallenges);
		responseData.put("userInfo", userInfoMap);
		responseData.put("challenges", challengesMap);

		return JsonResult.success(responseData);
	}

	// 차트 데이터 조회
	@GetMapping("/{userNum}/charts")
	public JsonResult getCharts(@PathVariable int userNum) {
		List<HmkChartDataVo> chartData = mypageService.getCharts(userNum);
		Map<String, Object> responseData = new HashMap<>();
		Map<String, Object> performanceData = new HashMap<>();
		Map<String, Object> achievementData = new HashMap<>();
		// 데이터 분류
		for (HmkChartDataVo data : chartData) {
			if (data.getChartTitle().contains("수행률")) {
				performanceData.put(data.getChartTitle(), data);
			} else if (data.getChartTitle().contains("달성률")) {
				achievementData.put(data.getChartTitle(), data);
			}
		}
		responseData.put("performance", performanceData);
		responseData.put("achievement", achievementData);
		return JsonResult.success(responseData);
	}

	/*
	 * 포인트 요약 정보 조회 엔드포인트**
	 * 
	 * @param userNum 사용자 번호 (Path Variable)
	 * 
	 * @param request HTTP 요청 객체 (JWT 토큰 추출용)
	 * 
	 * @return 포인트 요약 정보
	 */
	@GetMapping("/{userNum}/pointSummary")
	public JsonResult getPointSummary(@PathVariable int userNum, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출 (보안 강화)
		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}

		HmkPointSummaryVo summary = mypageService.getPointSummary(userNum);
		System.out.println("이 회원의 포인트 요약: " + summary);
		return JsonResult.success(summary);
	}

	/*
	 * 추가: 포인트 상세 내역 조회 엔드포인트**
	 * 
	 * @param startDate 시작 날짜 (Query Param, optional)
	 * 
	 * @param endDate 종료 날짜 (Query Param, optional)
	 * 
	 * @param request HTTP 요청 객체 (JWT 토큰 추출용)
	 * 
	 * @return 포인트 상세 내역 리스트
	 */
	@GetMapping("/{userNum}/pointHistory")
	public JsonResult getPointHistory(@PathVariable int userNum, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출 (보안 강화)
		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}
		List<HmkPointHistoryVo> history = mypageService.getPointHistory(userNum, startDate, endDate);
		System.out.println("이 회원의 포인트 내역 기간: " + history);
		return JsonResult.success(history);
	}

	// 프로필 이미지 업데이트
	@PutMapping("/update-profile")
	public JsonResult updateProfileImage(@RequestBody HmkUserVo userVo, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출
		Integer userNum = JwtUtil.getNoFromHeader(request);
		if (userNum == null) {
			return JsonResult.fail("유효하지 않은 사용자 토큰입니다.");
		}
		userVo.setUserNum(userNum);

		try {
			boolean success = mypageService.updateProfileImage(userVo);
			if (success) {
				// 업데이트된 사용자 정보를 다시 조회하여 반환 (보안 고려)
				HmkUserVo updatedUser = mypageService.getUserInfo(userNum);
				return JsonResult.success(updatedUser);
			} else {
				return JsonResult.fail("프로필 이미지 업데이트에 실패했습니다.");
			}
		} catch (Exception e) {
			// 로깅 및 구체적인 에러 메시지 반환
			logger.error("프로필 이미지 업데이트 오류:", e);
			return JsonResult.fail("서버 오류로 인해 프로필 이미지 업데이트에 실패했습니다.");
		}
	}

	// 닉네임 변경
	@PutMapping("/{userNum}/updateNickname")
	public JsonResult updateNickname(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean success = mypageService.updateNickname(userVo);
		return success ? JsonResult.success(userVo) : JsonResult.fail("닉네임 업데이트 실패");
	}

	// 닉네임 중복 확인 API
	@GetMapping("/checkNickname")
	public JsonResult checkNickname(@RequestParam String nickname) {
		boolean isAvailable = mypageService.isNicknameAvailable(nickname);
		return JsonResult.success(isAvailable);
	}

	// 주소 자동완성 및 주소 업데이트를 통합한 메서드
	@GetMapping("/updateAddress")
	public JsonResult getRegionSuggestions(@RequestParam String query) {
		List<String> regionNames = (List<String>) mypageService.updateOrAutocompleteAddress(null, query);
		return JsonResult.success(regionNames);
	}

	@PutMapping("/{userNum}/updateAddress")
	public JsonResult updateAddress(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean success = (boolean) mypageService.updateOrAutocompleteAddress(userVo, null);
		return success ? JsonResult.success("주소가 업데이트되었습니다.") : JsonResult.fail("지역 업데이트 실패");
	}

	// 비밀번호 업데이트
	@PutMapping("/{userNum}/updatePassword")
	public JsonResult updatePassword(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean success = mypageService.updatePassword(userVo);

		if (success) {
			return JsonResult.success("비밀번호가 성공적으로 변경되었습니다.");
		} else {
			// 현재 비밀번호 불일치 또는 비밀번호 규칙 미충족
			return JsonResult.fail("비밀번호 변경에 실패했습니다. 현재 비밀번호가 일치하지 않거나, 새 비밀번호가 규칙에 맞지 않습니다.");
		}
	}

	// 회원 보관함 기프티콘 리스트 조회
	@GetMapping("/{userNum}/giftcards")
	public JsonResult getUserGiftCards(@PathVariable int userNum) {
		List<HmkGiftVo> giftCards = mypageService.getUserGiftCards(userNum);
		return JsonResult.success(giftCards);
	}
}