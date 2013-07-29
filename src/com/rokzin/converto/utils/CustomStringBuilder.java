package com.rokzin.converto.utils;

public class CustomStringBuilder {
	
	private StringBuilder sb = new StringBuilder();
	int size =0 ;
	
	public CustomStringBuilder() {
	
	}
	
	public void append(String value){
		sb.append(value);
		size++;
	}
	
	public void append(int value){
		sb.append(value);
		size++;
	}
	
	public void append(double value){
		sb.append(value);
		size++;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString(){
		return sb.toString();
	}
	
	

}
