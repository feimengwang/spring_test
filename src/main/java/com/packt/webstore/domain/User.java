package com.packt.webstore.domain;

public class User {
	private String name;
	private String age;
	private String sex;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
