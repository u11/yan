package com.example.model;

//�û�ϵͳ��Ϣ������
public class User {

	private String uid;// �û����
	private String uname;// �û�����
	private String utel;// �û��绰
	private String upwd;// �û�����
	private String unote;// �û�˵��
	private String utoken; // �û���Կ
	private String ubirth;//
	private String umail;

	public String ulname;
	public String ulx;
	public String uadd;
	public String udw;

	public String getUMail() {
		return umail;
	}

	public void setUMail(String umail) {
		this.umail = umail;
	}

	public String getUBirth() {
		return ubirth;
	}

	public void setUBirth(String ubirth) {
		this.ubirth = ubirth;
	}

	public String getUId() {
		return uid;
	}

	public void setUId(String uid) {
		this.uid = uid;
	}

	public String getUName() {
		return uname;
	}

	public void setUName(String uname) {
		this.uname = uname;
	}

	public String getUTel() {
		return utel;
	}

	public void setUTel(String utel) {
		this.utel = utel;
	}

	public String getUPwd() {
		return upwd;
	}

	public void setUPwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUNote() {
		return unote;
	}

	public void setUNote(String unote) {
		this.unote = unote;
	}

	public String getUToken() {
		return utoken;
	}

	public void setUToken(String utoken) {
		this.utoken = utoken;
	}
}
