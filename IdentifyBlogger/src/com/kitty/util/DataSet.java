package com.kitty.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class DataSet {
	/**
	 * @author Kitty Wang
	 * @brief read the dataset to build blog info.
	 * @param path is the file path.
	 * @return 0 is success and 1 is fail.
	 */
	public static List<Blog> scanAllBlog(String path){
		try {
			File file = new File(path);
			if(!file.exists() || !file.isFile()) {
				System.out.println("指定文件不存在或不是一个文件！");
				return null;
			}
			List<Blog> blogList = new LinkedList<Blog>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tmpStr = null;
			while((tmpStr = br.readLine()) != null) {
				String[] arr = tmpStr.split(" :&: ");
//				if(arr.length < 10) {
//					System.out.println(tmpStr);
//				}
				if (arr.length == 10) {
					Blog blog = new Blog();
					blog.setTitle(arr[0]);
					blog.setTimeStamp(arr[1]);
					blog.setBlogger(arr[2]);
					
					String[] tags = arr[3].split(",");
					List<String> tmpList = new LinkedList<String>();
					int n = tags.length;
					for (int i=0; i<n; i++) {
						if(tags[i].length() > 0) {
							tmpList.add(tags[i]);
						}
					}
					blog.setTags(tmpList);
					blog.setBlogPostContent(arr[4]);
					blog.setOutlinks(Integer.parseInt(arr[5]));
					blog.setBlogPostPermalink(arr[6]);
					if(isNum(arr[7]))
						blog.setInlinks(Integer.parseInt(arr[7]));
					else
						blog.setInlinks(0);
					blog.setCommentsURL(arr[8]);
					blog.setComments(Integer.parseInt(arr[9]));
					blogList.add(blog);
				}
			}
			System.out.println("Toally "+blogList.size()+" posts.");
			return blogList;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+))$");
	}
	
	public static List<Blogger> classifyBlog2Blogger(List<Blog> blogList) {
		List<Blogger> bloggerList = new LinkedList<Blogger>();
		int n = blogList.size();
		for(int i = 0; i < n; i++) {
			Blog blog = blogList.get(i);
			String name = blog.getBlogger();
			int m = bloggerList.size();
			if (m > 0) {
				boolean isAdded = false;
				for (int j = 0; j < m; j++) {
					Blogger existedBlogger = bloggerList.get(j);
					String existedName = existedBlogger.getName();
					if (existedName.equals(name)) {
						existedBlogger.addBlog(blog);
						isAdded = true;
						break;
					}
				}
				if (!isAdded) {
					Blogger blogger = new Blogger();
					blogger.setName(name);
					blogger.addBlog(blog);
					bloggerList.add(blogger);
				}
			}
			else {
				Blogger blogger = new Blogger();
				blogger.setName(name);
				blogger.addBlog(blog);
				bloggerList.add(blogger);
			}
		}
		System.out.println("Toally "+bloggerList.size()+" bloggers.");
		return bloggerList;
	}

}
