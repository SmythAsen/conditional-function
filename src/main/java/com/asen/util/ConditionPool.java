package com.asen.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 一个可以替换大量if-else 和 switch的条件工具类
 *
 * @author Asen
 * @date 2018/4/29 16:36
 **/
public class ConditionPool<K, R> {

	/**
	 * 默认方法，当找不到key时会调用次方法，类似switch中的default条件
	 */
	private Function<R> defaultFunction;

	/**
	 * 存放条件的执行函数
	 */
	private Map<K, Function<R>> pool;

	/**
	 * 初始化条件池
	 *
	 * @param pool 存放条件的map
	 */
	public ConditionPool(Map<K, Function<R>> pool) {
		this.pool = pool;
	}

	public ConditionPool() {
		this.pool = new HashMap<>();
	}

	/**
	 * 添加一个条件和触发它时需要执行的方法
	 *
	 * @param condition 触发条件
	 * @param function  条件方法
	 * @return ConditionPool
	 */
	public ConditionPool<K, R> add(K condition, Function<R> function) {
		this.pool.put(condition, function);
		return this;
	}

	/**
	 * 添加多个条件执行同一个方法
	 * 当多个条件的执行逻辑相同时可以使用此方法
	 *
	 * @param conditions 触发条件
	 * @param function   条件方法
	 * @return ConditionPool
	 */
	public ConditionPool<K, R> add(List<K> conditions, Function<R> function) {
		conditions.forEach(k -> this.pool.put(k, function));
		return this;
	}

	/**
	 * 添加一个默认方法，在找不到匹配条件时，会调用此函数执行
	 * 类似switch里的default条件
	 *
	 * @param defaultFunction 条件方法
	 * @return ConditionPool
	 */
	public ConditionPool<K, R> addDefault(Function<R> defaultFunction) {
		this.defaultFunction = defaultFunction;
		return this;
	}

	/**
	 * 执行一个condition，如果pool中存在对应的condition，将会调用它的函数执行
	 * 如过不存在，将执行addDefault方法添加的默认function
	 *
	 * @param condition 需要验证的条件
	 * @return 条件执行结果
	 */
	public R doIf(K condition) {
		if (this.pool.containsKey(condition)) {
			return pool.get(condition).invoke();
		}
		if (Objects.nonNull(this.defaultFunction)) {
			return defaultFunction.invoke();
		}
		return null;
	}

	/**
	 * 执行一个condition，如果pool中存在对应的condition，将会调用它的函数执行
	 * 如果不存在，将执行指定的默认方法
	 *
	 * @param condition       需要验证的条件
	 * @param defaultFunction 当条件不存在时需要执行的方法
	 */
	public R doIfWithDefault(K condition, Function<R> defaultFunction) {
		if (this.pool.containsKey(condition)) {
			return pool.get(condition).invoke();
		} else {
			return defaultFunction.invoke();
		}
	}
}
