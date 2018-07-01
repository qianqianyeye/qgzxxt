package qianye.pda.presentation.controller.dao.infer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;
import qianye.core.domain.model.Work;

public interface ISiganDao {

	@Select("select * from applywork where suserid=#{userid} and statue='1'")
	List<ApplyWork>getMyApplyWorks(Map<String, Object>map);
	
	@Select("select * from work where id=#{id} and statue<>'3'")
	Work getMyWork(Map<String, Object>map);
	
	@Select("select * from Sign where userid=#{userid} and time = #{time} and workid=#{workid}")
	List<Sign>getSigns(Map<String, Object>map);
	
	@Insert("insert into Sign(workid,singintime,timelength,userid,money,statue,time,nextstatue,fuserid) values (#{sign.workid},#{sign.singintime},#{sign.timelength},#{sign.userid},#{sign.money},#{sign.statue},#{sign.time},#{sign.nextstatue},#{sign.fuserid})")
	int addSign(@Param("sign")Sign sign);
	
	@Update("update sign set timelength = #{sign.timelength},singintime=#{sign.singintime},money=#{sign.money},nextstatue=#{sign.nextstatue} where id=#{sign.id}")
	int updatesign(@Param("sign")Sign sign);
	
	@Update("update sign set singintime=#{sign.singintime},nextstatue=#{sign.nextstatue} where id=#{sign.id}")
	int updatesign2(@Param("sign")Sign sign);
	
	@Update("update sign set statue = #{statue} where id=#{id}")
	int updateSignStatue(Map<String, Object>map);
	
	@Select("select * from sign where userid=#{userid} limit ${page},${rows}")
	List<Sign>getMySigns(Map<String, Object>map);
	
	@Select("select * from sign where 1=1 limit  ${page},${rows}")
	List<Sign>getAllSign(Map<String, Object>map);
	
	@Select("select * from work where id=#{id}")
	Work getWorkById(@Param("id")String id);
	
	@Select("select * from sign where fuserid=#{fuserid} and statue<>0 limit ${page},${rows}")
	List<Sign>getgsqr(Map<String, Object>map);
	
	@Select("select * from users where userid=#{userid}")
	Users getUsersById(@Param("userid")String userid);
	
	@Update("update sign set confirmstatue=#{confirmstatue} where id=#{id}")
	int updateConfirmMoney(Map<String, Object>map);
	
}
