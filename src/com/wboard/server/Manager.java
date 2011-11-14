package com.wboard.server;

import java.util.ArrayList;

/**
 * Manages all logged in users
 * @author SKCCADMIN
 *
 * @param <T>
 */
public abstract class Manager<T> {
	protected ArrayList<T> list;
	public Manager(){
		list = new ArrayList<T>();
	}
	public T get(int i){
		return list.get(i);
	}
	public boolean delete(int i){
		try{
			list.remove(i);
		}catch(IndexOutOfBoundsException e){
			return false;
		}
		return true;
	}
	abstract public int add(T t);
}
