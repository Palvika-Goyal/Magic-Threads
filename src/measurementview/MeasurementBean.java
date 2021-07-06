package measurementview;

import java.sql.Date;

public class MeasurementBean {

	int oid;
	String custname;
	String custmobile;
	String dress;
	String spl;
	String measurement;
	Date doo;
	Date dor;
	int amount;
	String wish;
	int status;
	public MeasurementBean() {}
	public MeasurementBean(int oid, String custname, String custmobile, String dress, String spl, String measurement,
			Date doo, Date dor, int amount, String wish, int status) {
		super();
		this.oid = oid;
		this.custname = custname;
		this.custmobile = custmobile;
		this.dress = dress;
		this.spl = spl;
		this.measurement = measurement;
		this.doo = doo;
		this.dor = dor;
		this.amount = amount;
		this.wish = wish;
		this.status = status;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustmobile() {
		return custmobile;
	}
	public void setCustmobile(String custmobile) {
		this.custmobile = custmobile;
	}
	public String getDress() {
		return dress;
	}
	public void setDress(String dress) {
		this.dress = dress;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public Date getDoo() {
		return doo;
	}
	public void setDoo(Date doo) {
		this.doo = doo;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getWish() {
		return wish;
	}
	public void setWish(String wish) {
		this.wish = wish;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}

