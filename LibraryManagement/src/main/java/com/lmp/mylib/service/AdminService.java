package com.lmp.mylib.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
import com.lmp.mylib.Seat;
import com.lmp.mylib.dao.AdminDAO;

@Service
@Transactional
public class AdminService implements IAdminService{

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public boolean isAdmin(String adminId, String adminPw) {
		return adminDAO.getAdminId(adminId, adminPw);
	}

	@Override
	public int registerMember(MemberWeb member, int seatNum) {
		int res = 0;
		try {
			res = adminDAO.insertMember(member);
			if(res == 1) 
				res = adminDAO.registerSeat(member, seatNum);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 
		return res;
	}
	
	@Override
	public int moveSeat(int curSeatNum, int newSeatNum) {
		int res = 0;
		try {
			res = adminDAO.moveSeat(curSeatNum, newSeatNum);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int deleteMember(int seatNum) {
		int res = 0;
		try {
			res = adminDAO.deleteMember(seatNum);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Seat> showSeatInfo() {
		return adminDAO.getSeatInfo();
	}

	@Override
	public MemberDB getMemInfo(int seatNum) {
		return adminDAO.getMemInfo(seatNum);
	}

	@Override
	public int modifyMemInfo(MemberWeb member, String curMemPw) {
		int res = 0;
		try {
			res = adminDAO.updateMemInfo(member, curMemPw);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Ride> showRideInfo() {
		return adminDAO.getRideInfo();
	}
	
	@Override
	public List<RTime> showRTimeInfo() {
		return adminDAO.getRTimeInfo();
	}

	@Override
	public int deleteRTime() {
		int res = 0;
		try {
			res = adminDAO.deleteRTime();	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int setRTime(String[] rtime, int maxNum) {
		int res = 0;
		try {
			res = adminDAO.setRTime(rtime, maxNum);	
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	@Scheduled(cron="0/50 * * * * ?")
	public void deleteRTimeAuto() {
		List<Ride> rideInfo = adminDAO.getRideInfo();
		List<RTime> rtimeInfo = adminDAO.getRTimeInfo();
		LocalDate date = LocalDate.now();
		Path fp = Paths.get("C:\\Users\\user\\Desktop\\운행정보\\" + date + ".txt");		
		try {
			Files.deleteIfExists(fp);
			fp = Files.createFile(fp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if(rtimeInfo.size() == 0)
				Files.write(fp, "운행 휴무일입니다.".getBytes(), StandardOpenOption.APPEND);
			
			else {
				for(int i=0; i<rtimeInfo.size(); i++) {
					String time = rtimeInfo.get(i).getPosTime();
					Files.write(fp, (new String(time) + '\t').getBytes(), StandardOpenOption.APPEND);
					for(int j=0; j<rideInfo.size(); j++) {
						Ride r = rideInfo.get(j);
						if(time.equals(r.getPosTime())) {
							String name = r.getMemName();
							Files.write(fp, (new String(name)+' ').getBytes(), StandardOpenOption.APPEND);
						}
					}
					
					Files.write(fp, (System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		adminDAO.deleteRTime();
		System.out.println("운행 정보 초기화");
	}
	
}
