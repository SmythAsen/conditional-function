package com.asen.example;

import com.asen.util.IfFunction;

import java.util.HashMap;

/**
 * @Decription: the IfFunction test
 * @Author: Asen
 * @Date: Create in 12:39 2018/5/1 0001
 * @Email: SmythAsen@gmail.com
 **/
public class Test {
	
	public static void main(String[] args) {
		
		IfFunction<String> ifFunction = new IfFunction<>(new HashMap<>(5));
		
		ifFunction.add("hello", () -> System.out.println("你好"))
				.add("helloWorld", () -> System.out.println("你好，世界"))
				.doIfWithDefault("hello", () -> System.out.println("没有找到对应的key!"));
	}
}
