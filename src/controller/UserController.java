package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Session;
import model.dao.AccountDAO;
import model.dao.ReserveDAO;
import model.dao.UserDAO;
import model.dto.AccountDTO;
import model.dto.ReserveDTO;
import model.dto.UserDTO;

public class UserController {
	//아이디 중복 확인
	public boolean checkId(String userId) {
		UserDAO udao = new UserDAO();
		UserDTO user = udao.getUserByUserid(userId);

		return user == null;
		
	}
	//회원가입 // 유저와 계좌 동시 입력//
	public boolean join(UserDTO user,AccountDTO account) {
		UserDAO udao = new UserDAO();
		AccountDAO acdao = new AccountDAO();
		return udao.insertUser(user)&&acdao.insertAccount(account);
	}
	//로그인
	public boolean login(String userId, String userPw) {
		UserDAO udao = new UserDAO();
		UserDTO user = udao.getUserByUserid(userId);
		if(user != null) {
			//찾은 유저의 비밀번호도 비교
			if(user.getUserPw().equals(userPw)) {
				//로그인 성공시 세션에 세팅
				Session.setData("loginUser", user.getUserId());
				return true;
			}
		}
		return false;
	}
	//로그인 유저에게 유저객체,예약개수,계좌 잔액 정보 전달
	public HashMap<String, Object> getDetail(String loginUser) {
//		3개 이상의 데이터베이스에 접근해야 하므로 DAO가 3개 이상 필요.
		UserDAO udao = new UserDAO();
		ReserveDAO rdao = new ReserveDAO();
		AccountDAO acdao = new AccountDAO();
			
		
		UserDTO user = udao.getUserByUserid(loginUser);
		AccountDTO account = acdao.getAccountByUserid(loginUser);
		ArrayList<ReserveDTO> list = rdao.getReserveListByUserid(loginUser);
		int reserveCnt = list == null ? 0 : list.size();
		String balance = account == null ? "연결된 계좌가 없습니다." : account.getBalance()+"";
		
		
		//위에서 조회된 모든 정보들을 HashMap에 담아서 리턴
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("user", user);
		datas.put("reserveCnt", reserveCnt);
		datas.put("balance", balance);
		return datas;
		
	}
	public boolean leaveId(String loginUser) {
		UserDAO udao = new UserDAO();
		ReserveDAO rdao = new ReserveDAO();
		AccountDAO acdao = new AccountDAO();
		
		udao.deleteUser(loginUser);
		rdao.deleteReserveByUserId(loginUser);
		acdao.deleteAccountByUserId(loginUser);
		
		//탈퇴되었으므로 로그인 된 정보를 유지하는 세션도 초기화를 진행해야 한다.
		Session.setData("loginUser", null);
		if(udao.getUserByUserid(loginUser)==null){
			return true;
		}
		return false;
	}
	public boolean updateInfo(int choice,String newInfo) {
		UserDAO udao = new UserDAO();
		String[] infoList = { "userPw","phone","userAddr"};
		String userId = (String)Session.getData("loginUser");
		return udao.updateUserInfo(infoList[choice],newInfo,userId);
		
	}
	public boolean depositMoney(int money) {
		AccountDAO acdao = new AccountDAO();
		String userId = (String)Session.getData("loginUser");
		return acdao.updateBalance(money,userId);
		
	}

}


