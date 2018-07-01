package qianye.pda.appliction.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qianye.core.domain.model.Module;
import qianye.interfaces.application.Module.IModuleService;
import qianye.interfaces.dao.order.Module.IModuleDao;

@Service
public class ModuleService implements IModuleService{

	@Autowired
	IModuleDao moduleDao;
	
	public IModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public List<Module> getModules(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return moduleDao.findPdaModuleByKey(map);
	}

}
