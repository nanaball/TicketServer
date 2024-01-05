package vo;


public class MemberVO {

	private String mName;
	
	private String mId; // UNIQUE
	
	private String mPw;
	
	private String pNum;
	
	
	public MemberVO() {}
	
	
	// 회원 로그인 및 정보 검색용 생성자
	public MemberVO(String mId, String mPw) {
		this.mId = mId;
		this.mPw = mPw;
	}
	// 회원가입용 및 DB List
	public MemberVO( String mId, String mPw ,String mName, String pNum) {
		this.mName = mName;
		this.mId = mId;
		this.mPw = mPw;
		this.pNum = pNum;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getmId() {
		return mId;
	}


	public void setmId(String mId) {
		this.mId = mId;
	}


	public String getmPw() {
		return mPw;
	}


	public void setmPw(String mPw) {
		this.mPw = mPw;
	}


	public String getpNum() {
		return pNum;
	}


	public void setpNum(String pNum) {
		this.pNum = pNum;
	}


	@Override
	public String toString() {
		return "Member_DO [mName=" + mName + ", mId=" + mId + ", mPw=" + mPw + ",  pNum=" + pNum
				+ "]";
	}
	

	
	
	
}
