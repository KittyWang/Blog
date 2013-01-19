package com.kitty.util;

import java.util.ArrayList;
import java.util.List;

public class Identify {

	/**
	 * @author Kitty Wang
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "E:\\\\workspace\\\\MyEclipse9\\\\IdentifyBlogger\\\\Dataset\\\\TUAWData.txt";
		List<Blog> blogList = DataSet.scanAllBlog(path);
		if(blogList == null) {
			System.out.println("浏览日志失败！");
			return ;
		}
		List<Blogger> bloggerList = DataSet.classifyBlog2Blogger(blogList);
		if(bloggerList == null) {
			System.out.println("日志与作者关联失败！");
			return ;
		}
		
		int n = bloggerList.size();
		for (int i = 0; i < n; i++) {
			Blogger blogger = bloggerList.get(i);
			Blogger.getBloggeriIndex(blogger);
		}
		
		List<Blogger> kmost = new ArrayList<Blogger>();
		int k = 5;
		for(int i = 0; i < k; i++) {
			Blogger blogger = bloggerList.get(i);
			kmost.add(blogger);
		}
		//sort the kmost blogger list
		Blogger.sortBloggers(kmost);
		
		for(int i = k; i < n; i++) {
			Blogger blogger = bloggerList.get(i);
			float score = blogger.getiIndex();
			if(score > kmost.get(k-1).getiIndex()) {
				kmost.set(k-1, blogger);
				Blogger.sortBloggers(kmost);
			}
		}//end for
		System.out.println("The "+k+" most influences:");
		for(int i = 0; i < kmost.size(); i++) {
			System.out.println((i+1)+": "+kmost.get(i).getName());
		}
	}
	
}
