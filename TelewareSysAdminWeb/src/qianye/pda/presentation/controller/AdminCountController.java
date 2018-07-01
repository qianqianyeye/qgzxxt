package qianye.pda.presentation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;
import qianye.core.domain.model.Work;
import qianye.pda.presentation.controller.dao.infer.ISiganDao;
import qianye.pda.presentation.controller.dao.infer.IUserDao;
import qianye.pda.presentation.controller.dao.infer.IWorkDao;

@Controller
public class AdminCountController {
	@Autowired
	IUserDao userDao;
	
	@Autowired
	IWorkDao workDao;
	
	@Autowired
	ISiganDao siganDao;
	
	@RequestMapping("tongji/user")
	public String tongjiUser(Model model){
		int boyCount=userDao.boyCount();
		int girlCount=userDao.girlCount();
		model.addAttribute("boy", boyCount);
		model.addAttribute("girl", girlCount);
		List<ApplyWork>list = userDao.getTop10();
		List<String>worknames=new ArrayList<String>();
		List<String>counts = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Work work = workDao.getWorkById(list.get(i).getWorkid());
			//list.get(i).setWorkname(work.getWorkname());
			worknames.add("'"+work.getWorkname()+"'");
			counts.add(list.get(i).getCounts());
		}
		model.addAttribute("worknames", worknames);
		model.addAttribute("counts", counts);
		List<Sign>signs=userDao.getTopMoney();
		List<String>usernames = new ArrayList<String>();
		List<String>moneys=new ArrayList<String>();
		for (int i = 0; i < signs.size(); i++) {
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("userid", signs.get(i).getUserid());
			Users users=userDao.getUsers(map);
			usernames.add("'"+users.getUsername()+"'");
			moneys.add(signs.get(i).getMoney());
		}
		model.addAttribute("usernames", usernames);
		model.addAttribute("moneys", moneys);
		return "admin/userscharts";
	}
	
	@RequestMapping("tongji/money")
	public String money(){
		return "admin/money";
	}
	
	@RequestMapping("tongji/moneydata")
	@ResponseBody
	public List<Sign>moneydata(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object>map=new HashMap<String, Object>();
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		//String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		map.put("page", page);
		map.put("rows", rows);
		List<Sign>myList=siganDao.getAllSign(map);
		for (int i = 0; i < myList.size(); i++) {
			Work work=siganDao.getWorkById(myList.get(i).getWorkid());
			myList.get(i).setWorkname(work.getWorkname());
		}
		return myList;
	}
	
}
