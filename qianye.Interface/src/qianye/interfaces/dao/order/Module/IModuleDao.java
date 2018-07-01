package qianye.interfaces.dao.order.Module;

import java.util.List;
import java.util.Map;

import qianye.core.domain.model.Module;
import qianye.interfaces.dao.order.base.IRepository;

public interface IModuleDao extends IRepository{
	List<Module> findPdaModuleByKey(Map<String, Object> map);
}
