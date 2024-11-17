package com.javaex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.DyPointstoreDao;
import com.javaex.vo.DyCheckinVo;
import com.javaex.vo.DyItemVo;
import com.javaex.vo.DyPointHistoryVo;

@Service
public class DyPointstoreService {
	
	@Autowired
	private DyPointstoreDao dyPointstoreDao;
	

	/* 포인트상점 폼  */
	public List<DyItemVo> exeItemList() {
		System.out.println("DyPointstoreService.exeItemList()");
		
		List<DyItemVo> itemList = dyPointstoreDao.getItemList();
		
		//System.out.println(itemList);
		
		return itemList;
	}
	
	
	/* 포인트상점 상품디테일 */
//	public DyItemVo exeItemRead(int itemNum) {
//		System.out.println("DyPointstoreService.exeItemRead()");
//		
//		DyItemVo dyItemVo = dyPointstoreDao.getItemRead(itemNum);
//		
//		return dyItemVo;
//	}
	

	/* 포인트상점 출석체크 */
    public boolean addCheckin(DyCheckinVo dyCheckinVo) {
    	System.out.println("DyPointstoreService.addCheckin()");
    	
        // 오늘 날짜 확인
        String todayDate = LocalDate.now().toString();
        dyCheckinVo.setCheckinDate(todayDate);
        
        // 중복 체크
        boolean alreadyCheckedIn = dyPointstoreDao.isAlreadyCheckedIn(dyCheckinVo);
        if (alreadyCheckedIn) {
            return false;  // 이미 출석체크 완료
        }

        // 출석 체크 추가
        dyPointstoreDao.insertCheckinAndPointHistory(dyCheckinVo);
        
        // 포인트 이력 추가
        DyPointHistoryVo dyPointHistoryVo = new DyPointHistoryVo();
        dyPointHistoryVo.setUserNum(dyCheckinVo.getUserNum());
        dyPointHistoryVo.setHistoryDate(dyCheckinVo.getCheckinDate());
        dyPointHistoryVo.setPointPurposeNum(7);  // 출석에 해당하는 pointPurposeNum (예: 7번)
        dyPointHistoryVo.setHistoryPoint(50);    // 출석 포인트 (50점)
        dyPointHistoryVo.setHistoryInfo("+");    // 포인트 정보 ('+'는 포인트 증가)
        dyPointstoreDao.insertPointHistory(dyPointHistoryVo);
        
        return true;
    }
	
	
	/* 아이템 교환 */
	public int exeItemExchange(DyPurchaseHistoryVo dyPurchaseHistoryVo) {
		

		// 4. 가져온 장바구니 목록을 이용하여 History 테이블에 인서트
		for (unionVo item : cartItems) {
			item.setReceiptNum(receiptNum); // 영수증 번호 설정
			dao.insertHistory(item); // History 테이블에 삽입
		}

		return deliveryInsertCount;
	}
	
	
}
