package com.arthur.sms;

/**
 * Created by wangtao on 17/5/21.
 */
public class TestFunction {
	public static void  main(String[] args){

		for(int s = 0; s > -32; s --){
			Integer i = -1 << s;
			System.out.print("s = "+ s +";i = 0x" + Integer.toBinaryString(i) + ";i = " + i +";\n");
		}

	}
}
