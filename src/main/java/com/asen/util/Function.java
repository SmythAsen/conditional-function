package com.asen.util;

/**
 * @Decription: the function of IfFunction's key
 * @Author: Asen
 * @Date: Create in 17:35 2018/4/29 0029
 * @Email: SmythAsen@gmail.com
 **/
public interface Function<R> {

	/**
	 * 条件出发具体逻辑
	 *
	 * @return 处理结果
	 */
	R invoke();
}
