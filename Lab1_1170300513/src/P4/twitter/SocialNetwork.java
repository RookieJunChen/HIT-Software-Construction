/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) //要求返回一个“追随者—他追随的人组成的集合”的键值对集合，判断追随者是通过他@了别人来确定的

    {
    	Map<String, Set<String>> socialnetwork = new TreeMap<>();
    	Filter filter = new Filter();//需要用到前面做题时用到的两个类。
    	Extract extract = new Extract();
    	int i , j ,  k ,n = tweets.size();
    	List<String> username = new ArrayList<>();
    	for(i = 0 ; i < n ; i++)//先从tweets中的username取出参与tweets的“追随者”。
    	{
    		if(!extract.isthesame(username, tweets.get(i).getAuthor()))
    		{
    			username.add(tweets.get(i).getAuthor());
    		}
    	}
    	for(i = 0 ; i < username.size() ; i++)
    	{	
    		List<Tweet> thisguy =  filter.writtenBy(tweets, username.get(i));//取出每个“追随者”所写的tweets（循环一个一个人来）。
    		Set<String> hisidol = extract.getMentionedUsers(thisguy);//再取出这些tweets里@的人名构成集合（“追随者”追随的人）。
    		socialnetwork.put(username.get(i), hisidol);//添加该键值对。
    	}
			

    
    	return socialnetwork;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {//根据前面的键值对集合计算影响力排行榜（被追随次数越多影响力越大）
    	List<String> influence = new ArrayList<>();
    	List<people> rank = new ArrayList<>();
    	List<String> temp = new ArrayList<>();
       	Filter filter = new Filter();
    	Extract extract = new Extract();
    	int i , j;
    	for(String key : followsGraph.keySet() )
    	{
    		for(String idol : followsGraph.get(key))
    		{
    			if(!extract.isthesame(temp, idol))
    			{
    				temp.add(idol); //取出map里所有的Set，将其中人名添加入排行榜中（注意人名是否重复——isthesame函数检查），并且没出现一次热度增加。
    				people p = new people();
    				p.name = idol;
    				p.hot++;
    				rank.add(p);
    			}
    			else //如果人名已在排行榜中，则相应地增加其影响力。
    			{
    				int m = getindex(temp, idol);
    				people change = new people();
    				change = rank.get(m);
    				change.hot++;
    				rank.set(m, change);
    						
    			}
    		}
    	}
    	for(i = 0 ; i < rank.size() ; i++) //对排行榜按影响力大小进行排序。
    	{
    		for(j = i ; j < rank.size() ; j++)
    		{
    			people up = new people();
    			if(rank.get(j).hot > rank.get(i).hot)
    			{
    				up = rank.get(j);
    				rank.set(j, rank.get(i));
    				rank.set(i, up);
    			}
    		}
    	}
    	for(i = 0 ; i < rank.size() ; i++)
    	{
    		influence.add(rank.get(i).name);
    	}
    	return influence;
    }
    public static List<String> gethashset(List<Tweet> tweets) //辅助函数，取出tweets里所含的所有“#”标签并将其构成列表。（原理与取@类似）
    {
        List<String> mentionedusers = new ArrayList<>();
        int n = tweets.size();
        Extract extract = new Extract();
        List<String> temp = new ArrayList<>();
        int i , j;
        for(i = 0 ; i < n ; i++)
        {
        	String[] strarray = tweets.get(i).getText().split("#");
        	
        	for(j = 1 ; j < strarray.length ; j++)
        	{
        		String str1 = strarray[j].split(" ")[0];
        		if( !(extract.isthesame(temp, str1)))
        		{
        			
        			temp.add(str1);
        			mentionedusers.add("#"+str1);
        		}
        	}	
        }
        return mentionedusers;
    }
    public static boolean  isthesame(Set<String> mentionedusers , String m) //辅助函数，确定某个名字是否在一个关于名字的集合里。
    {
		int j , flag = 0;
		for(String k : mentionedusers)
		{
			
			if(m.length() == k.length())
			{
				for(j = 0 ; j < m.length() ; j++)
				{
					if(!( (m.charAt(j) == k.charAt(j)) || (m.charAt(j) == k.charAt(j)-32) || (m.charAt(j) == k.charAt(j)+32) ))
						break;
					else if(j == m.length() - 1)
						flag = 1;
				}
			}
		}
		if(flag == 1)
			return true;
		else
			return false;
    }

    public static int getindex(List<String> mentionedusers , String m) //辅助函数，找到列表中对应名字的下标。
    {
    	int n = mentionedusers.size();
    	int i , j , flag = 0;
    	for(i = 0 ; i < n ; i++)
    	{
    		String k = mentionedusers.get(i);
    		if(m.length() == k.length())
    		{
    			for(j = 0 ; j < m.length() ; j++)
    			{
    				if(!( (m.charAt(j) == k.charAt(j)) || (m.charAt(j) == k.charAt(j)-32) || (m.charAt(j) == k.charAt(j)+32) ))
    					break;//利用字符字母中大小写的关系来实现大小写不敏感的相同字符串判断。
    				else if(j == m.length() - 1)
    					flag = 1;
    			}
    		}
    		if(flag == 1)
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    public static Map<String, Set<String>> myguessFollowsGraph(List<Tweet> tweets)
    {
    	Map<String, Set<String>> socialnetwork = new TreeMap<>();
    	Filter filter = new Filter();//需要用到前面做题时用到的两个类。
    	Extract extract = new Extract();
    	int i , j ,  k ,n = tweets.size();
    	List<String> username = new ArrayList<>();
    	for(i = 0 ; i < n ; i++)//先从tweets中的username取出参与tweets的“追随者”。
    	{
    		if(!extract.isthesame(username, tweets.get(i).getAuthor()))
    		{
    			username.add(tweets.get(i).getAuthor());
    		}
    	}
    	for(i = 0 ; i < username.size() ; i++)
    	{	
    		List<Tweet> thisguy =  filter.writtenBy(tweets, username.get(i));//取出每个“追随者”所写的tweets（循环一个一个人来）。
    		Set<String> hisidol = extract.getMentionedUsers(thisguy);//再取出这些tweets里@的人名构成集合（“追随者”追随的人）。
    		socialnetwork.put(username.get(i), hisidol);//添加该键值对。
    	}
			
    	List<String> hashset = gethashset(tweets);//取出所有标签。
    	for(i = 0 ; i < hashset.size() ; i++)//循环针对每个标签。
    	{
    		
    		List<String> hashsetcase = new ArrayList<>();
    		hashsetcase.add(0, hashset.get(i));
    		List<Tweet> interests = filter.containing(tweets, hashsetcase);//取出所有tweets中包含该标签的tweets。
    		for(j = 0 ; j < interests.size() ; j++)//关注该标签的某个人应算作追随关注该标签的其它所有人。
    		{
    			Set<String> lovers = socialnetwork.get(interests.get(j).getAuthor());
    			for(k = 0 ; k < interests.size() ; k++)
    			{
    				if((k != j) && !(isthesame(lovers, interests.get(k).getAuthor())))
    				{
    					lovers.add(interests.get(k).getAuthor());
    				}
    				
    			}
    			socialnetwork.put(interests.get(j).getAuthor(), lovers);
    		}
    		
    	}
    
    	return socialnetwork;
    }
}

class people //构造辅助类people，记录相应人名以及其影响力hot。
{
	public String name;
	public  int  hot = 0;
}