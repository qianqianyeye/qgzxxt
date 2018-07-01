package qianye.pda.presentation.controller.dao.infer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;


public interface IUserDao  {
	
	@Select("select * from users where username=#{username} and password=#{password} and tenantid=#{tenantid} and statue='1'")
	List<Users> getUsersByCondition(@Param("username")String username,@Param("password")String password,@Param("tenantid")String tenantid);

	@Insert("insert  into users(username,password,phone,sex,tenantid,statue,age) values (#{users.username},#{users.password},#{users.phone},#{users.sex},#{users.tenantid},#{users.statue},#{users.age})")
	int regist(@Param("users")Users users);
	
	@Select("select * from users where username=#{username} and userid <> #{userid}")
	List<Users>getUsersByUsername(@Param("username")String username,@Param("userid")String userid);
	
	@Select("select * from users where username=#{username}")
	List<Users>getUsersByUsername2(@Param("username")String username);
	
	@Select("SELECT * from users where statue='0' limit ${page},${rows}")
	List<Users> getUsersByCondition2(Map<String, Object>map);
	
	@Select("SELECT * from users where statue='0' and username like #{username} limit ${page},${rows}")
	List<Users>getUsersByCondition3(Map<String, Object>map);
	
	@Update("UPDATE users SET statue=#{statue} where userid=#{userid}")
	int updateUser(@Param("statue")String statue,@Param("userid")String userid);
	
	@Delete("delete from users where userid=#{userid} ")
	int deleteUser(@Param("userid")String userid);
	
	@Select("select * from users where userid=#{userid}")
	Users getUsers(Map<String, Object>map);
	
	@Update("update users set username=#{user.username},phone=#{user.phone},sex=#{user.sex},age = #{user.age},password = #{user.password} where userid=#{user.userid}")
	int updateUsers(@Param("user")Users user);
	
	@Update("update users set username=#{user.username},phone=#{user.phone},sex=#{user.sex},age = #{user.age},password =#{user.password},tenantid=#{user.tenantid},statue=#{user.statue} where userid=#{user.userid}")
	int adminupdateUsers(@Param("user")Users user);
	
	@Select("select * from users where 1=1 limit ${page},${rows}")
	List<Users> admingetUsers(Map<String, Object>map);
	
	@Select("select * from users where username like #{username}  limit ${page},${rows}")
	List<Users>adminSearch(Map<String, Object>map);
	
	@Update("update comment set username = #{username} where username=#{username}")
	int updatecommentUser(@Param("username")String username);
	
	@Update("call updateAllUserName(#{oldusername},#{newusername})")
	int updateAllUsername(@Param("newusername")String newusername,@Param("oldusername")String oldusername);
	
	@Select("select count(*) from users where sex='0'")
	int boyCount();
	
	@Select("select count(*) from users where sex='1'")
	int girlCount();
	
	@Select("SELECT * FROM(select count(1) counts, t.workid from applywork t group by t.workid order by count(1) desc )t1 LIMIT 0,10")
	List<ApplyWork>getTop10();
	
	@Select("SELECT * FROM(select sum(money)money, t.userid from sign t WHERE confirmstatue='1'group by t.userid )t1 ORDER by t1.money  LIMIT 0,10")
	List<Sign>getTopMoney();
	
}
