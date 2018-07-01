package qianye.pda.presentation.controller.dao.infer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import qianye.core.domain.model.ApplyWork;
import qianye.core.domain.model.Comment;
import qianye.core.domain.model.Notice;
import qianye.core.domain.model.Work;

public interface IWorkDao {

	@Select("select * from work where 1=1 limit ${page},${rows}")
	List<Work> getWorks(@Param("page")String page,@Param("rows")String rows);
	
	@Insert("insert into work (workname,username,content,userid,statue,money) values(#{work.workname},#{work.username},#{work.content},#{work.userid},#{work.statue},#{work.money})")
	int addWork(@Param("work")Work work);
	
	@Update("call updateAllWorkName(#{newworkname},#{workid})")
	int updateAllWorkName(@Param("newworkname")String newworkname,@Param("workid")String workid);
	
	/*@Update("UPDATE work SET workname=#{work.workname},content=#{work.content},statue=#{work.statue} where userid=#{work.userid} and id=#{work.id}")*/
	@Update("UPDATE work SET workname=#{work.workname},content=#{work.content},statue=#{work.statue} where id=#{work.id}")
	int updateWork(@Param("work")Work work);
	
	
	@Select("select * from work where workname like #{workname} limit ${page},${rows}")
	List<Work> workSearch(Map<String, Object>map);
/*	@Delete("delete  from work where id=#{id} and userid=#{userid}")
*/	@Delete("delete  from work where id=#{id} ")
	int delete(Map<String, Object>map);
	
	@Delete("call deleteAllWork(#{workid})")
	int deleteAllWork(@Param("workid")String workid);
	
	@Select("select * from comment where workid=#{workid}")
	List<Comment> getComments(@Param("workid")String workid);
	
	@Insert("insert into comment (content,workid,username) values (#{comment.content},#{comment.workid},#{comment.username})")
	int addComment(@Param("comment")Comment comment);
	
	@Select("select * from applywork where suserid=#{applyWork.suserid} and workid=#{applyWork.workid}")
	List<ApplyWork> getApplyWorks(@Param("applyWork")ApplyWork applyWork);
	
	@Insert("insert into applywork (fuserid,suserid,fusername,susername,workid,workname,statue) values(#{applyWork.fuserid},#{applyWork.suserid},#{applyWork.fusername},#{applyWork.susername},#{applyWork.workid},#{applyWork.workname},#{applyWork.statue})")
	int addApplyWork(@Param("applyWork")ApplyWork applyWork);
	
	@Select("select * from applywork where suserid=#{suserid} limit ${page},${rows}")
	List<ApplyWork>getMyWork(Map<String, Object>map);
	
	@Select("select * from applywork where suserid=#{suserid} and workname like #{workname}")
	List<ApplyWork>searchMyWork(Map<String, Object>map);
	
	@Select("select * from work where id=#{workid}")
	Work getWorkById(@Param("workid")String workid);
	
	@Select("select * from work where userid=#{userid} limit ${page},${rows}")
	List<Work>getWorkByUserId(Map<String, Object>map);
	
	@Select("select * from work where userid=#{userid} and workname like #{workname} limit ${page},${rows}")
	List<Work>searchConfirm(Map<String, Object>map);
	
	@Select("select * from applywork where workid=#{workid}")
	List<ApplyWork> getApplyWorkByWorkId(@Param("workid")String workid);
	
	@Update("Update ApplyWork SET statue = #{statue} where workid=#{workid} and suserid=#{suserid}")
	int updateApplyWork(Map<String, Object>map);
	
	@Select("select * from ApplyWork where fuserid=#{fuserid}  limit ${page},${rows}")
	public List<ApplyWork> getApplyWorksByFuser(Map<String, Object>map);
	
	@Insert("insert into notice (workname,content,time,suserid,fuserid,workid,susername,fusername,statue) values(#{notice.workname}," +
			"#{notice.content},#{notice.time},#{notice.suserid},#{notice.fuserid},#{notice.workid},#{notice.susername},#{notice.fusername},#{notice.statue})")
	int addNotice(@Param("notice")Notice notice);
	
	@Select("select * from notice where suserid = #{suserid} order by id desc  limit ${page},${rows} ")
	List<Notice>myNotices(Map<String, Object>map);
	
	@Select("select * from notice where suserid = #{suserid} and workname like #{workname} order by id desc  limit ${page},${rows} ")
	List<Notice>myNoticesSearch(Map<String, Object>map);
	
	@Select("select * from ApplyWork where fuserid=#{fuserid} and workname like #{workname} limit ${page},${rows}")
	List<Notice>workSearchNotice(Map<String, Object>map);
}
