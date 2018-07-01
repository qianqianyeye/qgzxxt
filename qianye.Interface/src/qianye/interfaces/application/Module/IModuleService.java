package qianye.interfaces.application.Module;

import java.util.List;
import java.util.Map;

import qianye.core.domain.model.Module;

public interface IModuleService {

	 List<Module> getModules(Map<String, Object>map);
}
