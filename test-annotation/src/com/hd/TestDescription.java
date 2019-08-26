 package com.hd;

 /**
  * 注解使用：
  * @<注解名>(<成员名1>=<成员值1>, <成员名2>=<成员值2>, ...)
  * @author H__D
  * @date 2019-07-09 22:49:32
  *
  */
@Description(desc="I am class annotation", author="hd")
public class TestDescription {

	@Description(desc="I am method annotation", author="hd")
	public String test(){
		
		return "red";
	}
	
	public static void main(String[] args) {
		
	}
	
}
