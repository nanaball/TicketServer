package dao;

import vo.PayVO;

public interface PayDAO {
	
	PayVO pay(String name, String price, String seat, String musical, String date);

	boolean addPay(PayVO payVO);
	
}
