package com.mycom.happyhouse.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.happyhouse.dao.HouseDao;
import com.mycom.happyhouse.dto.HouseDealDto;
import com.mycom.happyhouse.dto.HouseDealExpandDto;
import com.mycom.happyhouse.dto.HouseDto;
import com.mycom.happyhouse.dto.HouseParamDto;
import com.mycom.happyhouse.dto.HouseResultDto;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public HouseResultDto houseList(HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDto> list = dao.houseList(houseParamDto);
			int count = dao.houseListTotalCount();
			houseResultDto.setList(list);
			houseResultDto.setCount(count);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}
	
	@Override
	public HouseResultDto houseListSearchDong(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDto> list = dao.houseListSearchDong(houseParamDto);
			int count = dao.houseListSearchDongTotalCount(houseParamDto);
			
			houseResultDto.setList(list); // 리스트 담기
			houseResultDto.setCount(count);
			
			houseResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseListSearchWord(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDto> list = dao.houseListSearchWord(houseParamDto);
			int count = dao.houseListSearchWordTotalCount(houseParamDto);
			
			houseResultDto.setList(list); // 리스트 담기
			houseResultDto.setCount(count);
			
			houseResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseDealDetail(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDealDto> dealList = dao.houseDealDetail(houseParamDto);
			
			houseResultDto.setDealList(dealList); // 리스트 담기
			
			houseResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseListRand(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		List<HouseDealExpandDto> houseDealExpandList = new ArrayList<>();

		try {
			// 랜덤하게 가져오기
			List<HouseDto> list = dao.houseListRand(houseParamDto);
			
			for (HouseDto houseDto : list) {
				
				houseParamDto.setNo(houseDto.getNo()); // 랜덤하게 가져온 아파트 번호를 파라미터에 대입
				
				List<HouseDealDto> dealList = dao.houseDealRecentList(houseParamDto);
				
				if(dealList.size() == 0) dealList = dao.houseDealDetail(houseParamDto); // 거래내역 1개도 있음

				HouseDealExpandDto tmp = new HouseDealExpandDto();
				
				tmp.setNo(houseDto.getNo());
				tmp.setCity(houseDto.getCity());
				tmp.setGugun(houseDto.getGugun());
				tmp.setDong(houseDto.getDong());
				tmp.setAptName(houseDto.getAptName());
				tmp.setBuildYear(houseDto.getBuildYear());
				tmp.setJibun(houseDto.getJibun());
				tmp.setLat(houseDto.getLat());
				tmp.setLng(houseDto.getLng());
				tmp.setImg(houseDto.getImg());
				tmp.setStatus(houseDto.getStatus());
				tmp.setRating(houseDto.getRating());
				
				if(dealList.size() == 0) {
					tmp.setRecentDealAmount("거래내역없음");
					tmp.setDealDiffPercent("0");
					tmp.setDiffDays("-1");
				}
				else if(dealList.size() == 1) {
					HouseDealDto recent = dealList.get(0);
					tmp.setRecentDealAmount( recent.getDealAmount() );
					tmp.setDealDiffPercent("0");
					tmp.setDiffDays( getDiffDays(recent.getDealYear(), recent.getDealMonth(), recent.getDealDay()) );
				}
				else if(dealList.size() == 2) {
					HouseDealDto prev = dealList.get(0);
					HouseDealDto recent = dealList.get(1);
					tmp.setRecentDealAmount( recent.getDealAmount() );
					
					tmp.setDealDiffPercent( getDiffDealPercent(recent.getDealAmount(), prev.getDealAmount()) );
					
					tmp.setDiffDays( getDiffDays(recent.getDealYear(), recent.getDealMonth(), recent.getDealDay()) );
				}
				
				houseDealExpandList.add(tmp);
				
				System.out.println("----------------------");
				System.out.println( dealList.size() );
				System.out.println("------------------------");
			}
			
			houseResultDto.setHouseDealExpandList(houseDealExpandList);
			houseResultDto.setCount(houseParamDto.getLimit());
			houseResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println(houseDealExpandList.get(0));
		System.out.println(houseDealExpandList.get(1));
		System.out.println(houseDealExpandList.get(2));
		System.out.println(houseDealExpandList.get(3));

		return houseResultDto;
	}

	@Override
	public HouseResultDto houseListSearchStatus(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDto> list = dao.houseListSearchStatus(houseParamDto);
			int count = dao.houseListSearchStatusTotalCount(houseParamDto);
			
			houseResultDto.setList(list); // 리스트 담기
			houseResultDto.setCount(count);
			
			houseResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseDealRecentList(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDealDto> dealList = dao.houseDealRecentList(houseParamDto);
			
			if(dealList == null) dealList = dao.houseDealDetail(houseParamDto);
			
			houseResultDto.setDealList(dealList); // 리스트 담기
			
			houseResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}
	
	private static String getDiffDealPercent(String recentDeal, String prevDeal) {
		
		double diff = Double.parseDouble( recentDeal.trim().replace(",", "") ) 
					/ Double.parseDouble( prevDeal.trim().replace(",", "") );
		
		diff = Math.round(diff * 1000 - 1000) / 10.0;
		
		return String.valueOf(diff);
	}
	
	// 두 날짜간의 차이 일수를 구하는 메서드
	private static String getDiffDays(String year, String month, String day) {
		Calendar getToday = Calendar.getInstance();
		getToday.setTime(new Date()); //금일 날짜
		
		String dateStr = year.trim() + "-" + month.trim() + "-" + day.trim();
		
		Date date = null;
		long diffSec = 0;
		long diffDays = 0;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			Calendar cmpDate = Calendar.getInstance();
			cmpDate.setTime(date); //특정 일자
			
			diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
			diffDays = diffSec / (24*60*60); //일자수 차이
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(diffSec + "초 차이");
//		System.out.println(diffDays + "일 차이");
		
		return String.valueOf(diffDays);
	}

}
