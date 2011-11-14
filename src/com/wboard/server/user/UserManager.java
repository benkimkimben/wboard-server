package com.wboard.server.user;

import com.wboard.server.Manager;
import com.wboard.exception.FindException;
import com.wboard.user.User;

public class UserManager extends Manager<User> {
	private int cid;
	public UserManager(){
		super();
		cid = 0;
	}
	public User getById(int id) throws FindException{
		for(User u: list){
			if(u.getId()==id){
				return u;
			}
		}
		throw new FindException("User not found. " + id);
	}
	public int add(User u){
		u.setId(cid);
		cid++;
		return u.getId();
	}
}
