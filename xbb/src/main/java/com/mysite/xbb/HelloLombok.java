package com.mysite.xbb;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok hlombok = new HelloLombok("헬로", 10);
//		hlombok.setHello("헬로");
//		hlombok.setLombok(10);
		
		System.out.println(hlombok.getHello());
		System.out.println(hlombok.getLombok());
	}

}
