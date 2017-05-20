package com.arthur.common.strategy;

/**
 * Created by wangtao on 17/4/24.
 */
public class ClassloadTree {


		public static void main(String[] args) {
			ClassLoader loader = ClassloadTree.class.getClassLoader();
			while (loader != null) {
				System.out.println(loader.toString());
				loader = loader.getParent();
			}
		}

}
