package qianye.pda.presentation.controller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import qianye.core.domain.enums.DataSourceEnvironment;
import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;
import qianye.core.domain.model.Work;
import qianye.pda.infrastructure.repository.base.NoMapperXmlDao;
import qianye.pda.presentation.controller.dao.infer.ISiganDao;
import qianye.pda.presentation.controller.dao.infer.IWorkDao;

@Repository
public class SignDao extends NoMapperXmlDao implements ISiganDao{

	@Override
	public List<ApplyWork> getMyApplyWorks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getMyApplyWorks(map);
	}

	@Override
	public Work getMyWork(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getMyWork(map);
	}

	@Override
	public List<Sign> getSigns(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getSigns(map);
	}

	@Override
	public int addSign(Sign sign) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name(),true).addSign(sign);
	}

	@Override
	public int updatesign(Sign sign) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name(),true).updatesign(sign);
	}

	@Override
	public int updatesign2(Sign sign) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name(),true).updatesign2(sign);
	}

	@Override
	public int updateSignStatue(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name(),true).updateSignStatue(map);
	}

	@Override
	public List<Sign> getMySigns(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getMySigns(map);
	}

	@Override
	public Work getWorkById(String id) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getWorkById(id);
	}

	@Override
	public List<Sign> getgsqr(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getgsqr(map);
	}

	@Override
	public Users getUsersById(String userid) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getUsersById(userid);
	}

	@Override
	public int updateConfirmMoney(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name(),true).updateConfirmMoney(map);
	}

	@Override
	public List<Sign> getAllSign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(ISiganDao.class,DataSourceEnvironment.development.name()).getAllSign(map);
	}

}
