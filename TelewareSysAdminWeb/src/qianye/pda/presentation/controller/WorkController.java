package qianye.pda.presentation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.w;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Comment;
import qianye.core.domain.model.Notice;
import qianye.core.domain.model.Work;
import qianye.pda.presentation.controller.dao.infer.IWorkDao;

@Controller
public class WorkController {
	
	@Autowired
	IWorkDao workDao;
	
	@RequestMapping("work/workmanage")
	public String workmanage(){
		return "work/workmanage";
	}
	
	@RequestMapping("work/workdata")
	@ResponseBody
	public List<Work> workdata(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		List<Work>list=workDao.getWorks(page, rows);
		for (int i = 0; i < list.size(); i++) {
			String workid=list.get(i).getId();
			List<Comment>comments=workDao.getComments(workid);
			if (comments!=null) {
				for (int j = 0; j < comments.size(); j++) {
					String temps="<br/>"+comments.get(j).getUsername()+":"+comments.get(j).getContent();
					if (list.get(i).getComment()==null) {
						list.get(i).setComment(temps);
					}else {
						list.get(i).setComment(list.get(i).getComment()+temps);
					}
					
				}
			}
			
		}
		return list;
	}
	
	@RequestMapping("work/addwork")
	@ResponseBody
	public String addWork(Work work){
		int i=workDao.addWork(work);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping("work/updatework")
	@ResponseBody
	public String updateWork(Work work){
		
		int j = workDao.updateAllWorkName(work.getWorkname(), work.getId());
		int i=workDao.updateWork(work);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping("work/search")
	@ResponseBody
	public List<Work> search(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("workname", "%"+workname+"%");
		List<Work>list=workDao.workSearch(map);
		for (int i = 0; i < list.size(); i++) {
			String workid=list.get(i).getId();
			List<Comment>comments=workDao.getComments(workid);
			if (comments!=null) {
				for (int j = 0; j < comments.size(); j++) {
					String temps="<br/>"+comments.get(j).getUsername()+":"+comments.get(j).getContent();
					if (list.get(i).getComment()==null) {
						list.get(i).setComment(temps);
					}else {
						list.get(i).setComment(list.get(i).getComment()+temps);
					}
					
				}
			}
			
		}
		return list;	
	}
	
	@RequestMapping("work/deleteWork")
	@ResponseBody
	public String deleteWork(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String userid=request.getParameter("userid");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("userid", userid);
		int j=workDao.deleteAllWork(id);
		int i=workDao.delete(map);
		if (i>0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping("work/apply")
	public String apply(HttpServletRequest request,HttpServletResponse response){
		return "work/workapply";
	}

	@RequestMapping("work/addcomment")
	@ResponseBody
	public String addcomment(Comment comment){
		int i=workDao.addComment(comment);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping("work/applywork")
	@ResponseBody
	public Map<String, Object> apply(ApplyWork applyWork){
		List<ApplyWork>list=workDao.getApplyWorks(applyWork);
		Map<String, Object>map =new HashMap<String, Object>();
		if (list.size()>0) {
			map.put("tishi", "已经申请过该岗位!");
		}else {
			applyWork.setStatue("0");//未通过
			int i=workDao.addApplyWork(applyWork);
			if (i>0) {
				map.put("tishi", "已申请,待审批!");
				
			}else{
				map.put("tishi", "申请失败!");
				
			}
		}
		return map;
	}
	
	@RequestMapping("user/mywork")
	public String mywork(){
		return "work/mywork";
	}
	
	@RequestMapping("work/myworkdata")
	@ResponseBody
	public List<Work> myworkdata(HttpServletRequest request,HttpServletResponse response,@Param("userid")String userid){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("suserid", userid);
		List<ApplyWork>list=new ArrayList<ApplyWork>();
		List<Work>resultWork=new ArrayList<Work>();
		if (workname!=null&&!workname.equals("")) {
			map.put("workname", "%"+workname+"%");
			list=workDao.searchMyWork(map);
		}else {
			list=workDao.getMyWork(map);
		}
		for (int i = 0; i < list.size(); i++) {
			String workid=list.get(i).getWorkid();
			Work work = workDao.getWorkById(workid);
			//System.out.println(list.get(i).getStatue());
			work.setPassstatue(list.get(i).getStatue());
			resultWork.add(work);
		}
		return resultWork;
	}
	
	@RequestMapping("work/confirm")
	public String workconfirm(){
		return "work/workconfirm";
	}
	
	@RequestMapping("work/confirmworkdata")
	@ResponseBody
	public List<Work> confirmworkdata(HttpServletRequest request,HttpServletResponse response,@Param("userid")String userid){
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		String workname = request.getParameter("workname");
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("userid", userid);
		List<Work>curWorkList=new ArrayList<Work>();
		List<Work>resultList = new ArrayList<Work>();
		if (workname!=null && !workname.equals("")) {
			map.put("workname", "%"+workname+"%");
			curWorkList=workDao.searchConfirm(map);
		}else {
			curWorkList = workDao.getWorkByUserId(map);
		}
		
		for (int i = 0; i < curWorkList.size(); i++) {
			String workid=curWorkList.get(i).getId();
			List<ApplyWork> applyWork=workDao.getApplyWorkByWorkId(workid);
			for (int j = 0; j < applyWork.size(); j++) {
				Work work = new Work();
				work=curWorkList.get(i);
				work.setSuserid(applyWork.get(j).getSuserid());
				work.setSusername(applyWork.get(j).getSusername());
				work.setPassstatue(applyWork.get(j).getStatue());
				work.setApplyid(applyWork.get(j).getId());
				resultList.add(work);
			}
			
			
		}
		return resultList;
	}
	
	@RequestMapping("work/passwork")
	@ResponseBody
	public Map<String, Object> passwork(HttpServletRequest request,HttpServletResponse response){
		String suserid=request.getParameter("suserid");
		String workid=request.getParameter("workid");
		String statue=request.getParameter("statue");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("suserid", suserid);
		map.put("workid", workid);
		map.put("statue", statue);
		int i=workDao.updateApplyWork(map);
		Map<String, Object>resultmap =new HashMap<String, Object>();
		if (i>0) {
			resultmap.put("tishi","操作成功!");
			
		}else {
			resultmap.put("tishi","操作失败!");
		}
		return resultmap;
	}
	
	@RequestMapping("notice/notice")
	public String notice(){
		return "work/notice";
	}
	
	@RequestMapping("work/notice")
	@ResponseBody
	public List<ApplyWork> worknotice(HttpServletRequest request,HttpServletResponse response){
		String fuserid = request.getParameter("fuserid");
		Map<String, Object>map=new HashMap<String, Object>();
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		//Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("fuserid", fuserid);
		List<ApplyWork>list=workDao.getApplyWorksByFuser(map);
		return list;
	}
	
	@RequestMapping("work/noticesearch")
	@ResponseBody
	public List<Notice> noticesearch(HttpServletRequest request,HttpServletResponse response){
		String fuserid = request.getParameter("userid");
		String workname = request.getParameter("workname");
		Map<String, Object>map=new HashMap<String, Object>();
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		//Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("fuserid", fuserid);
		map.put("workname", "%"+workname+"%");
		List<Notice>list=workDao.workSearchNotice(map);
		return list;
	}
	
	@RequestMapping("work/sendnotice")
	@ResponseBody
	public String sendnotice(HttpServletRequest request,HttpServletResponse response,Notice notice){
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		notice.setTime(date);
		int i=workDao.addNotice(notice);
		if (i>0) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping("notice/mynotice")
	public String mynotice(){
		return "work/mynotice";
	}
	
	@RequestMapping("notice/mynoticedata")
	@ResponseBody
	public List<Notice> mynoticedata(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object>map=new HashMap<String, Object>();
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows"); // 每页显示的记录数
		int pages=Integer.valueOf(page);
		int row=Integer.valueOf(rows);
		Integer temp=(pages-1)*row;
		page=temp.toString();
		//Map<String, Object>map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		String suserid=request.getParameter("suserid");
		map.put("suserid", suserid);
		String workname =request.getParameter("workname");
		map.put("workname", "%"+workname+"%");
		List<Notice>list = new ArrayList<Notice>();
		if (workname!=null && !workname.equals("")) {
			list=workDao.myNoticesSearch(map);
		}else {
			list=workDao.myNotices(map);
		}
		
		return list;
	}
	
	@RequestMapping("work/adminmanage")
	public String adminmanage(){
		return "admin/adminworkmanage";
	}
	
	
}
