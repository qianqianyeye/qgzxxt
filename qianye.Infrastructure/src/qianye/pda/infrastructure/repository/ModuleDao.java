package qianye.pda.infrastructure.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import qianye.core.domain.enums.DataSourceEnvironment;
import qianye.core.domain.model.Module;
import qianye.interfaces.dao.order.Module.IModuleDao;
import qianye.pda.infrastructure.repository.base.MapperFactory;

@Repository
public class ModuleDao implements IModuleDao{

	@Override
	public List<Module> findPdaModuleByKey(Map<String, Object> map) {
		IModuleDao moduleDao = null;
		List<Module> modules=null;
        try {
        	
        	moduleDao = MapperFactory.createMapper(IModuleDao.class, DataSourceEnvironment.development);
        	modules = moduleDao.findPdaModuleByKey(map);
        }catch(Exception e){
        	e.printStackTrace();
        }
        return modules;
	}

	@Override
	public int deleteEntities(String[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countEntities(Map<String, Object> parms) {
		// TODO Auto-generated method stub
		return 0;
	}

}
