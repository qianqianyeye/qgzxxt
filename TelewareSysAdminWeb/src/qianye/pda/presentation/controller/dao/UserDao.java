package qianye.pda.presentation.controller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import qianye.core.domain.enums.DataSourceEnvironment;
import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;
import qianye.pda.infrastructure.repository.base.NoMapperXmlDao;
import qianye.pda.presentation.controller.dao.infer.IUserDao;

@Repository
public class UserDao extends NoMapperXmlDao implements IUserDao{

	@Override
	public List<Users> getUsersByCondition(String username, String password,String tenantid) {
		// TODO Auto-generated method stub
		return  getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsersByCondition(username, password,tenantid);
	}

	@Override
	public int regist(Users users) {
		// TODO Auto-generated method stub
		return  getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).regist(users);
	}

	@Override
	public List<Users> getUsersByUsername(String username,String userid) {
		// TODO Auto-generated method stub
		return  getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsersByUsername(username,userid);
	}

	@Override
	public List<Users> getUsersByCondition2(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsersByCondition2(map);
	}

	@Override
	public List<Users> getUsersByCondition3(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsersByCondition3(map);
	}

	@Override
	public int updateUser(String statue, String userid) {
		// TODO Auto-generated method stub
		return  getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).updateUser(statue, userid);
	}

	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).deleteUser(userid);
	}

	@Override
	public Users getUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsers(map);
	}

	@Override
	public int updateUsers(Users user) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).updateUsers(user);
	}

	@Override
	public int updatecommentUser(String username) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).updatecommentUser(username);
	}

	@Override
	public int updateAllUsername(String newusername, String oldusername) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).updateAllUsername(newusername, oldusername);
	}

	@Override
	public List<Users> admingetUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).admingetUsers(map);
	}

	@Override
	public List<Users> adminSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).adminSearch(map);
	}

	@Override
	public int adminupdateUsers(Users user) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name(),true).adminupdateUsers(user);
	}

	@Override
	public List<Users> getUsersByUsername2(String username) {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).getUsersByUsername2(username);
	}

	@Override
	public int boyCount() {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).boyCount();
	}

	@Override
	public int girlCount() {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).girlCount();
	}

	@Override
	public List<ApplyWork> getTop10() {
		// TODO Auto-generated method stub
		return  getDao(IUserDao.class,DataSourceEnvironment.development.name()).getTop10();
	}

	@Override
	public List<Sign> getTopMoney() {
		// TODO Auto-generated method stub
		return getDao(IUserDao.class,DataSourceEnvironment.development.name()).getTopMoney();
	}


}
