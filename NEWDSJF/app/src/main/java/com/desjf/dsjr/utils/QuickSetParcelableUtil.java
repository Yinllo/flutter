package com.desjf.dsjr.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;

/**
 * @Author GL
 * @Description 快速配置 Parcelable 接口的工具类 (实现接口的类要有无参构造方法)
 * @Date 2012-4-19
 */
public class QuickSetParcelableUtil {

	public static void write(Parcel dest, Object o) {

		Field[] fields = o.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if(field.getType() == Parcelable.Creator.class){
					continue;
				}
				dest.writeValue(field.get(o));
				
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Object read(Parcel source, Class<?> cls){
		Object o;
		try {
			o = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if(field.getType() == Parcelable.Creator.class){
					continue;
				}
				field.set(o, source.readValue(cls.getClassLoader()));
			}
		} catch (IllegalAccessException e) {
			
			throw new RuntimeException(e);
			
		} catch (InstantiationException e) {
			
			throw new RuntimeException(e);
		}
		return o;
	}
	
	public static void write(Parcel dest, Object o, Class<?>... skipCls) {

		Field[] fields = o.getClass().getDeclaredFields();
		try {
			boolean flag = false;
			for (Field field : fields) {
				field.setAccessible(true);
				
				Class<?> type = field.getType();
				for(int i = 0; i < skipCls.length; i++){
					if(type == skipCls[i]){
						flag = true;
					}
				}
				
				if(flag){
					flag = false;
					continue;
				}
				
				dest.writeValue(field.get(o));
				
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Object read(Parcel source, Class<?> cls, Class<?>... skipCls){
		Object o;
		try {
			o = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			boolean flag = false;
			for (Field field : fields) {
				field.setAccessible(true);
				
				Class<?> type = field.getType();
				for(int i = 0; i < skipCls.length; i++){
					if(type == skipCls[i]){
						flag = true;
					}
				}
				
				if(flag){
					flag = false;
					continue;
				}

				field.set(o, source.readValue(cls.getClassLoader()));
			}
		} catch (IllegalAccessException e) {
			
			throw new RuntimeException(e);
			
		} catch (InstantiationException e) {
			
			throw new RuntimeException(e);
		}
		return o;
	}
}
