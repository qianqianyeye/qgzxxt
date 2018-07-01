package qianye.pda.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qianye.core.domain.model.Module;
import qianye.interfaces.application.Module.IModuleService;
import utils.GsonUtils;

@Controller
public class ModuleController {

	@Autowired
	IModuleService moduleService;
	
	@ResponseBody
	@RequestMapping(value="module/findModulejson",method=RequestMethod.POST)
	public String findModuleJson(HttpServletRequest request,HttpServletResponse response,@Param("tenantid")String tenantid){
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "";
		try {
			//String tenantId=request.getParameter("tenantid");
			map.put("pmenuid", "0");
			map.put("tenantId", tenantid);
			List<Module> moduleList = moduleService.getModules(map);
			for (Module pdaModuleModel : moduleList) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId", tenantid);
				params.put("pmenuid", pdaModuleModel.getMenuid());
				List<Module> sublist = moduleService.getModules(params);
				pdaModuleModel.setChild(sublist);
			}
			if(moduleList != null && moduleList.size() > 0){
//				result = GsonUtils.listToJson(moduleList);
				result = "{child"+":"+GsonUtils.listToJson(moduleList)+"}";
			}
//			System.out.println("获取的菜单内容数据："+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
