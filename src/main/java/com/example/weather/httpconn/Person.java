package com.example.weather.httpconn;

import java.util.List;

public class Person {

	private String name;
	private int age;
	private String url;
	private List<SchoolInfo> schList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<SchoolInfo> getSchList() {
		return schList;
	}
	public void setSchList(List<SchoolInfo> schList) {
		this.schList = schList;
	}
	
}
