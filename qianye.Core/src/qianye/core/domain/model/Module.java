package qianye.core.domain.model;

import java.util.List;

public class Module {
	
	private String menuid; //模块编号
	private String icon; //模块图标
	private String menuname; //模块名称
	private String url; //链接地址
	private String tenantid;
	private String pmenuid; //父类编号， 0表示父类，其他表示子类
	private List<Module> child;
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPmenuid() {
		return pmenuid;
	}
	public void setPmenuid(String pmenuid) {
		this.pmenuid = pmenuid;
	}

	public String getTenantid() {
		return tenantid;
	}
	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}
	public List<Module> getChild() {
		return child;
	}
	public void setChild(List<Module> child) {
		this.child = child;
	}
	
}
