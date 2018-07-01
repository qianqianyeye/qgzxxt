package qianye.pda.presentation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qianye.core.domain.model.Users;
import qianye.pda.presentation.controller.dao.infer.IUserDao;

import com.google.code.kaptcha.Constants;

/**
 * 登录控制器
 * @author tiger
 *
 */
@Controller
public class loginController{
	
	@Autowired
	IUserDao userDao;
	
	@RequestMapping(value="/admin/tenantlogin",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, 
	           HttpServletResponse response,String tenantId,String name,String password){
		try {
			List<Users>list=userDao.getUsersByCondition(name, password,tenantId);
			if (list.size()>0) {
				return list.get(0).getUserid();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "false";
	}
	
	@RequestMapping("goto/home")
	public String gotohome(HttpServletRequest request, 
	           HttpServletResponse response,Model model){
		String tenantid=request.getParameter("tenantId");
		String userid=request.getParameter("userid");
		String username=request.getParameter("username");
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("userid", userid);
		model.addAttribute("username", username);
		return "home/index";
	}
	
	@RequestMapping("goto/regist")
	public String gotoregist(){
		return "home/regist";
	}
	@RequestMapping(value="admin/regist",method=RequestMethod.POST)
	@ResponseBody
	private String regist(HttpServletRequest request, 
	           HttpServletResponse response,Users users){
		List<Users>userList=userDao.getUsersByUsername2(users.getUsername());
		if (userList.size()<=0) {
			//users.setStatue("0");
			int i=userDao.regist(users);
			if (i>0) {
				return "true";
			}
		}
		return "false";
	}
	
	@RequestMapping("regist/zcsh")
	private String zcsh(){
		return "home/registlist";
	}
	
	@RequestMapping("regist/registlist")
	@ResponseBody
	public List<Users> registlist(HttpServletRequest request,
			HttpServletResponse response,String username){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		if (username!=null) {
			map.put("username", "%"+username+"%");
			List<Users>list = userDao.getUsersByCondition3(map);
			return list;
		}else {

			List<Users>list = userDao.getUsersByCondition2(map);
			return list;
		}
	}
	
	@RequestMapping("regist/passorrefuse")
	@ResponseBody
	public String passorrefuse(HttpServletRequest request,
			HttpServletResponse response,String statue,String userid){
		int i=0;
		if (statue.equals("1")) {
			i=userDao.updateUser(statue, userid);
		}else {
			i=userDao.deleteUser(userid);
		}
		if (i>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping("user/update")
	public String userupdate(HttpServletRequest request,
			HttpServletResponse response){
		return "home/updateusers";
	}
	
	@RequestMapping("users/getUsersData")
	@ResponseBody
	public Users getUsersData(HttpServletRequest request,
			HttpServletResponse response){
		String userid = request.getParameter("userid");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("userid", userid);
		Users users=userDao.getUsers(map);
		return users;
	}
	
	@RequestMapping("users/updateUserData")
	@ResponseBody
	public String updateUserData(Users users){
		List<Users> list = userDao.getUsersByUsername(users.getUsername(),users.getUserid());
		if (list.size()>0) {
			return "false";
		}else {
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("userid", users.getUserid());
			Users uu=userDao.getUsers(map);
			String oldusername = uu.getUsername();
			String newusername = users.getUsername();
			int j = userDao.updateAllUsername(newusername, oldusername);
			int i=userDao.updateUsers(users);
			if (i>0) {
				return "true";
			}
		}
		
		return "false";
	}
	
	@RequestMapping("users/adminupdateUserData")
	@ResponseBody
	public String adminupdateUserData(Users users){
		List<Users> list = userDao.getUsersByUsername(users.getUsername(),users.getUserid());
		if (list.size()>0) {
			return "false";
		}else {
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("userid", users.getUserid());
			Users uu=userDao.getUsers(map);
			String oldusername = uu.getUsername();
			String newusername = users.getUsername();
			int j = userDao.updateAllUsername(newusername, oldusername);
			int i=userDao.adminupdateUsers(users);
			if (i>0) {
				return "true";
			}
		}
		
		return "false";
	}
	
	@RequestMapping("user/manage")
	public String usermanage(){
		return "admin/usermanage";
	}
	
	@RequestMapping("user/userdata")
	@ResponseBody
	List<Users> userdata(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		String username = request.getParameter("username");
		List<Users>list = new ArrayList<Users>();
		if (username==null || username.equals("")) {
			list = userDao.admingetUsers(map);
		}else {
			map.put("username", "%"+username+"%");
			list = userDao.adminSearch(map);
		}
		
		return list;
	}
	
	@RequestMapping("user/deleteUser")
	@ResponseBody
	public String deleteUser(HttpServletRequest request,HttpServletResponse response){
		String userid = request.getParameter("userid");
		int i=userDao.deleteUser(userid);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	
}
