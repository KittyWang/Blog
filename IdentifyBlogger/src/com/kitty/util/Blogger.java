package com.kitty.util;

import java.util.LinkedList;
import java.util.List;

public class Blogger {
	private String name;
	private List<Blog> blogs = new LinkedList<Blog>();
	private float iIndex;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setiIndex(float iIndex) {
		this.iIndex = iIndex;
	}
	public float getiIndex() {
		return iIndex;
	}
	public void addBlog(Blog blog){
		this.blogs.add(blog);
	}
	
	public static float getBloggeriIndex(Blogger blogger) {
		int n = blogger.getBlogs().size();
		float max = 0;
		for (int i=0; i<n; i++) {
			Blog blog = blogger.getBlogs().get(i);
			float influenceScore = blog.getBlogPostScore(blog);
			if (max < influenceScore) {
				max = influenceScore;
			}
		}
		blogger.setiIndex(max);
		return max;
	}
	
	public static void sortBloggers(List<Blogger> kmost) {
		bubbleSort(kmost);
		
	}
	
	private static void bubbleSort(List<Blogger> kmost) {
		boolean exchange;
		int n = kmost.size();
		for (int i = 0; i < n-1; i++) {
			exchange = false;
			for (int j = 0; j < (n-i-1); j++) {
				Blogger a = kmost.get(j);
				Blogger b = kmost.get(j+1);
				if (a.iIndex < b.iIndex) {
					Blogger t = a;
					kmost.set(j, b);
					kmost.set(j+1, t);
					exchange = true;
				}
			}
			if (!exchange)
				break;
		}//end for
	}//end bubbleSort function
}
