package com.kitty.util;

import java.util.List;

public class Blog {
	private String 	title;				/*标题*/
	private String 	timeStamp;			/*文章发布时间*/
	private String 	blogger;			/*博主*/
	private List<String>	tags; 		/*文章标记*/
	private String	blogPostContent;	/*博客文章内容*/
	private int 	outlinks;			/*引用链接数*/
	private String	blogPostPermalink;	/*博客文章链接*/
	private int		inlinks;			/*被引用链接数*/
	private String	commentsURL;		/*评论地址*/
	private int		comments;			/*评论数*/
	
	/**
	 * @brief the following parameters' value in [0,1]. 
	 */
	public static float s_wcomm = 0.6f;
	public static float s_win = 0.9f;
	public static float s_wout = 0.2f;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}
	public String getBlogger() {
		return blogger;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setBlogPostContent(String blogPostContent) {
		this.blogPostContent = blogPostContent;
	}
	public String getBlogPostContent() {
		return blogPostContent;
	}
	public void setOutlinks(int outlinks) {
		this.outlinks = outlinks;
	}
	public int getOutlinks() {
		return outlinks;
	}
	public void setBlogPostPermalink(String blogPostPermalink) {
		this.blogPostPermalink = blogPostPermalink;
	}
	public String getBlogPostPermalink() {
		return blogPostPermalink;
	}
	public void setInlinks(int inlinks) {
		this.inlinks = inlinks;
	}
	public int getInlinks() {
		return inlinks;
	}
	public void setCommentsURL(String commentsURL) {
		this.commentsURL = commentsURL;
	}
	public String getCommentsURL() {
		return commentsURL;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getComments() {
		return comments;
	}
	
	public void resetDefaultParams() {
		setCustomParams(0.6f, 0.9f, 0.2f);
	}
	
	public void setCustomParams(float wcomm, float win, float wout) {
		s_wcomm = wcomm;
		s_win = win;
		s_wout = wout;
	}
	
	public float getBlogPostScore(Blog blog) {
		float inlinksInfluence = getLinksInfluence(blog.getInlinks(), blog, true);
		float outlinksInfluence = getLinksInfluence(blog.getOutlinks(), blog, false);
		float influenceFlow = s_win*inlinksInfluence-s_wout*outlinksInfluence;
		return lengthPenalize(blog.getBlogPostContent().length())*(s_wcomm*blog.getComments()+influenceFlow);	
	}
	
	/**
	 * @brief this weight is immaterial for identifying the influences
	 * at one blog site. So now default set it 1.
	 * @return the returned value in [0,1].
	 */
	private float lengthPenalize(int length) {
		return 1.0f;
	}
	private float getLinksInfluence(int linksNum, Blog blog, boolean isInlinks) {
		float sumLinksInfluence = 0.0f;
		for(int i=0; i<linksNum; i++) {
			//TODO: how to get the influence score of the inlink or outlink.
			float linkInfluence = 1.0f;
			sumLinksInfluence = sumLinksInfluence+linkInfluence;
		}
		return sumLinksInfluence;
	}
}
