package cn.com.cmdd.dao;



import java.util.List;

import cn.com.cmdd.domain.Lottery;
import cn.com.cmdd.domain.MemberCard;

public interface LotteryDao {
	
	void addLottery(Lottery lottery);

	Lottery getLotteryById(Long id);
	
	//public abstract Lottery getLotteryByAccount(String account);*/
	
	//public abstract List<Lottery> getLotteryList();
	
	List<Lottery> getLotteryListByShop_id(int shop_id);
	
	void updateLottery(Lottery lottery);
	
	//public abstract void updateLotteryPassword(int id, String newPwd);
	
	//public abstract void updateLotteryStatus(int status);
	
	//public abstract void updateUserLoginTime(Integer id, Date loginTime);
	
	//同步数据
	List<Lottery> selectByIsUpload();
	int updateIsUpload(Long id);
}
