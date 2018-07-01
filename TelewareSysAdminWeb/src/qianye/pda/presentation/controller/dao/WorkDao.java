package qianye.pda.presentation.controller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import qianye.core.domain.enums.DataSourceEnvironment;
import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Comment;
import qianye.core.domain.model.Notice;
import qianye.core.domain.model.Work;
import qianye.pda.infrastructure.repository.base.NoMapperXmlDao;
import qianye.pda.presentation.controller.dao.infer.IUserDao;
import qianye.pda.presentation.controller.dao.infer.IWorkDao;

@Repository
public class WorkDao extends NoMapperXmlDao implements IWorkDao{

	@Override
	public List<Work> getWorks(String page, String rows) {
		// TODO Auto-generated method stub
		return  getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getWorks(page, rows);
	}

	@Override
	public int addWork(Work work) {
		// TODO Auto-generated method stub
		return  getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).addWork(work);
	}

	@Override
	public int updateWork(Work work) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).updateWork(work);
	}

	@Override
	public List<Work> workSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).workSearch(map);
	}

	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).delete(map);
	}

	@Override
	public List<Comment> getComments(String workid) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getComments(workid);
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).addComment(comment);
	}

	@Override
	public List<ApplyWork> getApplyWorks(ApplyWork applyWork) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getApplyWorks(applyWork);
	}

	@Override
	public int addApplyWork(ApplyWork applyWork) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).addApplyWork(applyWork);
	}

	@Override
	public List<ApplyWork> getMyWork(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getMyWork(map);
	}

	@Override
	public List<ApplyWork> searchMyWork(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).searchMyWork(map);
	}

	@Override
	public Work getWorkById(String workid) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getWorkById(workid);
	}

	@Override
	public List<Work> getWorkByUserId(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getWorkByUserId(map);
	}

	@Override
	public List<ApplyWork> getApplyWorkByWorkId(String workid) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getApplyWorkByWorkId(workid);
	}

	@Override
	public List<Work> searchConfirm(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).searchConfirm(map);
	}

	@Override
	public int updateApplyWork(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).updateApplyWork(map);
	}

	@Override
	public List<ApplyWork> getApplyWorksByFuser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).getApplyWorksByFuser(map);
	}

	@Override
	public int addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).addNotice(notice);
	}

	@Override
	public List<Notice> myNotices(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).myNotices(map);
	}

	@Override
	public List<Notice> workSearchNotice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).workSearchNotice(map);
	}

	@Override
	public List<Notice> myNoticesSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name()).myNoticesSearch(map);
	}

	@Override
	public int updateAllWorkName(String newworkname, String workid) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).updateAllWorkName(newworkname, workid);
	}

	@Override
	public int deleteAllWork(String workid) {
		// TODO Auto-generated method stub
		return getDao(IWorkDao.class,DataSourceEnvironment.development.name(),true).deleteAllWork(workid);
	}

	
}
