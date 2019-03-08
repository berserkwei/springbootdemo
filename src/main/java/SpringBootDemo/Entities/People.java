package SpringBootDemo.Entities;

import java.io.Serializable;

public class People implements Serializable {
	
	private static final long serialVersionUID = 1975594586972518334L;

	long id;
	String name;
	int age;
	String email;
	int sex; // 0: male; 1: female
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
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
	
	public People(){}
	
	public People(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public People(String name, int age, String email, int sex) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.sex = sex;
	}
}


