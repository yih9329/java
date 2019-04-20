package com.lmp.mylib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.mylib.RTime;
import com.lmp.mylib.dao.MemberDAO;

@Service
@Transactional
public class MemberService implements IMemberService {
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public boolean memberLogin(String memName, String memPw) {
		return memberDAO.getMemberId(memName, memPw);
	}
	
	@Override
	public int getSeatNum(String memName, String memPw) {
		return memberDAO.getSeatNum(memName, memPw);
	}

	@Override
	public List<RTime> showRideTime() {
		return memberDAO.getRTime();
	}

	@Override
	public List<Integer> showRideNum(List<RTime> rtime) {
		return memberDAO.getRideNum(rtime);
	}

	@Override
	public int applyRide(int seatNum, String rtime) {
		int res = 0;
		try {
			res = memberDAO.rideInsert(seatNum, rtime);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
		return res;
	}
	
	@Override
	public String showMyRideTime(int seatNum) {
		return memberDAO.getMyRideTime(seatNum);
	}

	@Override
	public int deleteRide(int seatNum) {
		int res = 0;
		try {
			res = memberDAO.rideDelete(seatNum);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

}
