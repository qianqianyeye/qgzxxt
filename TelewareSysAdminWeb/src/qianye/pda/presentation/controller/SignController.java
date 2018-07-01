package qianye.pda.presentation.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.sun.org.apache.xml.internal.serializer.ElemDesc;

import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Sign;
import qianye.core.domain.model.Users;
import qianye.core.domain.model.Work;
import qianye.pda.presentation.controller.dao.infer.ISiganDao;

@Controller
public class SignController {
	@Autowired
	ISiganDao siganDao;
	
	@RequestMapping("kaoqin/sign")
	public String signhome(){
		return "sign/siginhome";
	}
	
	@RequestMapping("sign/getSignWork")
	@ResponseBody
	public List<Work> getSignWork(HttpServletRequest request,HttpServletResponse response){
		String userid = request.getParameter("userid");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("userid", userid);
		List<ApplyWork>myApplyWorks=siganDao.getMyApplyWorks(map);
		List<Work>myWorks=new ArrayList<Work>();
		if (myApplyWorks.size()>0) {
			for (int i = 0; i < myApplyWorks.size(); i++) {
				String id = myApplyWorks.get(i).getWorkid();
				map.put("id", id);
				Work work = siganDao.getMyWork(map);
				myWorks.add(work);
			}
		}
		return myWorks;
	}

	@RequestMapping("goto/sign")
	public String gotosign(HttpServletRequest request,HttpServletResponse response,Model model){
		String workid=request.getParameter("workid");
		model.addAttribute("workid", workid);
		return "sign/signorback";
	}
	
	@RequestMapping("sign/initSign")
	@ResponseBody
	public Sign initSign(HttpServletRequest request,HttpServletResponse response){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		String workid=request.getParameter("workid");
		String userid=request.getParameter("userid");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("workid", workid);
		map.put("userid", userid);
		map.put("time", date);
		List<Sign>list=siganDao.getSigns(map);
		Sign sign=new Sign();
		if (list.size()>0) {
			sign=list.get(0);
		}else {
			sign.setNextstatue("签到");
		}
		return sign;
	}
	
	@RequestMapping("sign/signorback")
	@ResponseBody
	public String signorback(HttpServletRequest request,HttpServletResponse response){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String workid=request.getParameter("workid");
		String userid=request.getParameter("userid");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("workid", workid);
		map.put("userid", userid);
		map.put("time", date);
		map.put("id", workid);
		Work work = siganDao.getMyWork(map);
		
		if (work!=null) {
			List<Sign>list=siganDao.getSigns(map);
			if (list.size()<=0) {
				String singintime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				Sign sign= new Sign();
				sign.setNextstatue("签退");
				sign.setMoney("0");
				sign.setTime(date);
				sign.setStatue("0");//未确认
				sign.setTimelength("0");
				sign.setUserid(userid);
				sign.setSingintime(singintime);
				sign.setWorkid(workid);
				sign.setFuserid(work.getUserid());
				int i=siganDao.addSign(sign);
				if (i>0) {
					return "true";
				}else {
					return "false";
				}
			}else {
				try {
					Sign sign=list.get(0);
					String newtime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					String oldtime=sign.getSingintime();

						if (sign.getNextstatue().equals("签到")) {
							sign.setNextstatue(newtime);
							sign.setNextstatue("签退");
							int i=siganDao.updatesign2(sign);
							if(i>0){
								return "true";
							}
						}else {
							
							SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
							long olddate;  
							long newdate;
							int hours;
							int minutes;
							olddate = simpleFormat.parse(oldtime).getTime();
							newdate = simpleFormat.parse(newtime).getTime(); 
							hours = (int) ((newdate - olddate)/(1000 * 60 * 60));  
							minutes = (int) ((newdate- olddate)/(1000 * 60)); 
							
							//long number = newdate.getTime()-olddate.getTime();
							DecimalFormat df = new DecimalFormat("#.0");
							double paymoney = Double.valueOf(work.getMoney());
							double money = 000000.00;

							if (!sign.getTimelength().equals("0")) {
								String temp=sign.getTimelength();
								String [] arr=temp.split(":");
								int temphour=Integer.valueOf(arr[0])+hours;
								int tempminutes=Integer.valueOf(arr[1])+minutes;
								money=money+paymoney*temphour;
								//System.out.println("sssssssssss           "+(paymoney/60)*tempminutes);
								money=money+(paymoney/60)*tempminutes;
								sign.setTimelength(temphour+":"+tempminutes);
							}else{
								money=money+paymoney*hours;
								money=money+(paymoney/60)*minutes;
								sign.setTimelength(hours+":"+minutes);
							}
							
							df.format(money);
							sign.setMoney(String.valueOf(money));
						
							sign.setNextstatue("");
							sign.setNextstatue("签到");
							sign.setSingintime(newtime);
							int i=siganDao.updatesign(sign);
							if(i>0){
								return "true";
							}
						}

				} catch (ParseException e) {
					e.printStackTrace();
				}  
				
			}
		}
		
		return "false";
	}
	
	@RequestMapping("sign/applytime")
	@ResponseBody
	public String applytime(HttpServletRequest request,HttpServletResponse response){
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String workid=request.getParameter("workid");
		String userid=request.getParameter("userid");
		String time=request.getParameter("time");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("workid", workid);
		map.put("userid", userid);
		if (time==null || time.equals("")) {
			map.put("time", date);
		}else {
			map.put("time", time);
		}
		
		List<Sign>list=siganDao.getSigns(map);
		if (list.size()<=0) {
			return "false";
		}else if (list.get(0).getStatue().equals("1") || list.get(0).getNextstatue().equals("签退")) {
			return "false";
		}
		map.put("id", list.get(0).getId());
		map.put("statue", "1");
		int i=siganDao.updateSignStatue(map);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping("user/mymoney")
	public String gotomymoney(){
		return "sign/mymoney";
	}
	
	@RequestMapping("sign/moneydata")
	@ResponseBody
	public List<Sign> moneydata(HttpServletRequest request,HttpServletResponse response){
		String userid = request.getParameter("userid");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("userid", userid);
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		//String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		map.put("page", page);
		map.put("rows", rows);
		List<Sign>myList=siganDao.getMySigns(map);
		
		for (int i = 0; i < myList.size(); i++) {
			Work work=siganDao.getWorkById(myList.get(i).getWorkid());
			myList.get(i).setWorkname(work.getWorkname());
		}
		return myList;
	}
	
	@RequestMapping("money/confirm")
	public String gotomoneyconfirm(){
		return "sign/moneyconfirm";
	}
	
	@RequestMapping("sign/gsqr")
	@ResponseBody
	public List<Sign> gsqr(HttpServletRequest request,HttpServletResponse response){
		String userid = request.getParameter("userid");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("fuserid", userid);
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		//String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		map.put("page", page);
		map.put("rows", rows);
		List<Sign>signs=siganDao.getgsqr(map);
		for (int i = 0; i < signs.size(); i++) {
			Work work=siganDao.getWorkById(signs.get(i).getWorkid());
			signs.get(i).setWorkname(work.getWorkname());
			Users users=siganDao.getUsersById(signs.get(i).getUserid());
			signs.get(i).setUsername(users.getUsername());
		}
		return signs;
	}
	
	@RequestMapping("sign/confirm")
	@ResponseBody
	public String confirm(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		String confirmstatue = "0";
		if (type.equals("pass")) {
			confirmstatue="1";
		}else {
			confirmstatue="2";
		}
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("confirmstatue", confirmstatue);
		int i=siganDao.updateConfirmMoney(map);
		if (i>0) {
			return "true";
		}
		return "false";
	}
}
