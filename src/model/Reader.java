package model;

/*
 * 读者实体类
 */
public class Reader {
	private int reader_id;// 读者ID
	private String reader_name;// 读者名
	private String reader_phone;// 读者手机号
	private String reader_password;// 读者密码

	public Reader() {
		super();
	}

	public Reader(int reader_id, String reader_name, String reader_phone, String reader_password) {
		super();
		this.reader_id = reader_id;
		this.reader_name = reader_name;
		this.reader_phone = reader_phone;
		this.reader_password = reader_password;
	}

	public Reader(String reader_name, String reader_password) {
		super();
		this.reader_name = reader_name;
		this.reader_password = reader_password;
	}

	public int getReader_id() {
		return reader_id;
	}

	public void setReader_id(int reader_id) {
		this.reader_id = reader_id;
	}

	public String getReader_name() {
		return reader_name;
	}

	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}

	public String getReader_phone() {
		return reader_phone;
	}

	public void setReader_phone(String reader_phone) {
		this.reader_phone = reader_phone;
	}

	public String getReader_password() {
		return reader_password;
	}

	public void setReader_password(String reader_password) {
		this.reader_password = reader_password;
	}

}