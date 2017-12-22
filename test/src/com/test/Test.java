package com.test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;

public class Test {

	/**
	 * 题目：数组 1 2 2 3 4 5排列，有几种不同的排列方式，要求4个不在第三位，35不相邻
	 * 思路：
	 * 		1.列出 数组122345，的所有可能排列
	 * 		2.排除4在第三位的所有排列
	 * 		3.排除35相邻的所有排列
	 * 
	 * @Description TODO
	 * @author H__D
	 * @date 2016年9月20日
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] a = {1,2,2,3,4,5};
		StringBuffer sb = new StringBuffer("");
		getfor(sb);
		System.out.println("----");
		for (Object s : set) {
			System.out.println(s.toString());
		}
	}
	
	private static Set set = new HashSet();
	
	public static void getfor(StringBuffer sb)
	{
		String str = sb.toString();
		for (int i = 0; i<2; i++) {
			System.out.println(i+sb.toString());
			i = getDifNum(sb.toString(), i);
			if(i >1 ) break;
			if(!sb.toString().contains(i+""))
			{
				sb.append(i);
				if(sb.toString().length() == 2)
				{
					set.add(sb.toString());
					break;
					//getfor(new StringBuffer(""));
				}
				getfor(new StringBuffer(sb));
			}else
			{
				getfor(new StringBuffer(str));
			}
			
			
			
			
		}
	}
	
	
	public static int getDifNum(String str,int n)
	{
		
		if(str != null && str.contains(n+""))
		{
			n++;
			n = getDifNum(str, n);
		}
		return n;
	}
	
	
	

}
