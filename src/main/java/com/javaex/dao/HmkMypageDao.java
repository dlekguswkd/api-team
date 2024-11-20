// src/main/java/com/javaex/dao/UserDao.java

package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.HmkChallengeVo;
import com.javaex.vo.HmkChartDataVo;
import com.javaex.vo.HmkGiftVo;
import com.javaex.vo.HmkPointHistoryVo;
import com.javaex.vo.HmkPointSummaryVo;
import com.javaex.vo.HmkUserVo;

@Repository
public class HmkMypageDao {
	private static final Logger logger = LoggerFactory.getLogger(HmkMypageDao.class);

	@Autowired
	private SqlSession sqlSession;

	// 사용자 정보 조회
	public HmkUserVo getUserInfo(int userNum) {
		return sqlSession.selectOne("Mypage.getUserInfo", userNum);
	}

	// 사용자의 챌린지 통계 조회
	public HmkUserVo getUserChallengeStats(int userNum) {
		return sqlSession.selectOne("Mypage.getUserChallengeStats", userNum);
	}

	// 사용자의 현재 진행중인 챌린지 리스트
	// MypageDao.java
	public List<HmkChallengeVo> getOngoingChallenges(int userNum) {
		return sqlSession.selectList("Mypage.getOngoingChallenges", userNum);
	}

	public List<HmkChallengeVo> getUpcomingChallenges(int userNum) {
		return sqlSession.selectList("Mypage.getUpcomingChallenges", userNum);
	}

	public List<HmkChallengeVo> getCompletedChallenges(int userNum) {
		return sqlSession.selectList("Mypage.getCompletedChallenges", userNum);
	}

	// 프로필 이미지 업데이트
	public int updateProfileImage(HmkUserVo userVo) {
		return sqlSession.update("Mypage.updateProfileImage", userVo);
	}

	// 닉네임 중복 체크
	public int checkNickname(String nickname) {
		return sqlSession.selectOne("Mypage.checkNickname", nickname);
	}

	// 닉네임 업데이트
	public int updateNickname(HmkUserVo userVo) {
		return sqlSession.update("Mypage.updateNickname", userVo);
	}

	// 주소 업데이트
	public int updateAddress(HmkUserVo userVo) {
		System.out.println("다오가 받은 지역 정보: " + userVo.getRegion());
		System.out.println("다오가 받은 유저 정보: " + userVo.getUserNum());
		return sqlSession.update("Mypage.updateAddress", userVo);
	}

	// 자동완성: 입력된 텍스트(query)로 시작하는 지역명 리스트 반환
	public List<String> findRegionsByName(String query) {
		return sqlSession.selectList("Mypage.findRegionsByName", query + "%");
	}

	// 비밀번호 업데이트
	public int updatePassword(HmkUserVo userVo) {
		return sqlSession.update("Mypage.updatePassword", userVo);
	}

	/**
	 * 사용자의 차트 데이터 조회 - 미션 수행률 (일반/챌린지/전체) - 미션 달성률 (일반/챌린지/전체)
	 */
	public List<HmkChartDataVo> getCharts(int userNum) {
		logger.debug("차트 데이터 조회 - userNum: {}", userNum);
		List<HmkChartDataVo> result = sqlSession.selectList("Mypage.getCharts", userNum);
		logger.debug("조회된 차트 데이터 수: {}", result.size());
		return result;
	}

	// 보관함 기프티콘 리스트 조회
	public List<HmkGiftVo> getUserGiftCards(int userNum) {
		return sqlSession.selectList("Mypage.getUserGiftCards", userNum);
	}

	// ** 포인트 요약 정보 조회**
	public HmkPointSummaryVo getPointSummary(int userNum) {
		return sqlSession.selectOne("Mypage.getPointSummary", userNum);
	}

	// ** 포인트 상세 내역 조회**
	public List<HmkPointHistoryVo> getPointHistory(int userNum, Map<String, Object> params) {
		return sqlSession.selectList("Mypage.getPointHistory", params);
	}
}
