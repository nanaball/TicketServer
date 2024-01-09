package dao;

import vo.PayVO;

public interface PayDAO {
	
	PayVO pay(String name, String date, String seat, String musical, Integer price);
	
	boolean addPay(PayVO payVO);

	
	
}
