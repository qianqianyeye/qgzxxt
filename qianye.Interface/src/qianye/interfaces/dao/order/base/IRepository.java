package qianye.interfaces.dao.order.base;

import java.util.Map;


/**
 * 仓储层公共接口
 * 定义一个统一的Mapper标识接口
 * @author Administrator
 *
 */
public interface IRepository {
	/**
	 * 这里进行删除多个实体信息
	 * @param ids 这里传送的是主键信息 用逗号分开  ,如 1,2,3,...
	 * @return
	 */
	public int deleteEntities(String[] ids);
	/**
	 * 这里根据查询条件进行技术所查询的实体数据的列表总条数
	 * @param key
	 * @return
	 */
	public int countEntities(Map<String, Object> parms);
}
