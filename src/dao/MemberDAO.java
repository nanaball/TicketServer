package dao;

import vo.MemberVO;

public interface MemberDAO {
	
	
	

	//boolean 사용? 
	boolean join(MemberVO memberVO);
	
	/*아이디 비밀번호로 로그인
	 * SELECT * FROM member WHERE id = ? AND pass = ?
	 */
	MemberVO login(String id, String pass);

	

}
