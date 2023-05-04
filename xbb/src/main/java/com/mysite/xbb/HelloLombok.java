package com.mysite.xbb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
	
	private String hello;
	private int lombok;
	
	public static void main(String[] args) {
		HelloLombok hlombok = new HelloLombok();
		hlombok.setHello("헬로");
		hlombok.setLombok(10);
		
		System.out.println(hlombok.getHello());
		System.out.println(hlombok.getLombok());
	}

}
