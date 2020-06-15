package com.qa.data;

//Pojo: Plain old java object 
public class Users {
	
	String name;
	String job;
	String id;
	String createdAt;
	
	// if you have 10 attributes in your post request payload, create 10 attributes 
	
	public Users() {
		
	}
	
	
   public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

public Users(String name, String job) {
	   
	   this.name=name;
	   this.job=job;	
	}

 //getters and setters Methods:
   
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getJob() {
	return job;
}

public void setJob(String job) {
	this.job = job;
}
   
   
  
   
}
