package workertableview;

import java.sql.Date;

public class WorkerBean {
	String wname;
	String mobile;
	String picpath;
	String address;
	String spl;
	Date dor;
	public WorkerBean() {}
	public WorkerBean(String wname, String mobile, String picpath, String address, String spl, Date dor) {
		super();
		this.wname = wname;
		this.mobile = mobile;
		this.picpath = picpath;
		this.address = address;
		this.spl = spl;
		this.dor = dor;
		
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}
	
}
