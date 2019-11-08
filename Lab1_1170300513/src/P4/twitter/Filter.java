/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {//寻找所有tweets中某个作者写的tweets列表。
       List<Tweet> message = new ArrayList<>();
       int i , j , n = tweets.size();
       for(i = 0 ; i < n ; i++)
       {
    	   String cmp = tweets.get(i).getAuthor();
    	   if(username.length() == cmp.length())
    	   {
    		   for(j = 0 ; j < cmp.length() ; j++) //单个字符单个字符进行比较。
    		   {
    			   if(!((username.charAt(j) == cmp.charAt(j)) ||  //针对大小写不敏感而添加的比较条件。
    					   (username.charAt(j) == cmp.charAt(j)+32) || 
    					   (username.charAt(j) == cmp.charAt(j)-32)))
    				   break;
    			   else if(j == cmp.length() - 1)
    			   {
    				   message.add(tweets.get(i));
    			   }
    		   }
    	   }
       }       
       return message;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {//判断tweet的时间是否在指定时间段内，如果是添加到应返回的列表中。
    	List<Tweet> message = new ArrayList<>();
    	int i , n = tweets.size();
    	for(i = 0 ; i < n ; i++)
    	{
    		long time = tweets.get(i).getTimestamp().getEpochSecond();
    		if((time <= timespan.getEnd().getEpochSecond()) && 
    				(time >= timespan.getStart().getEpochSecond()))//判断其是否在该时间段内。
    		{
    			message.add(tweets.get(i));
    		}
    	}
    	return message;
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {//在所有tweets中寻找text里含words集合里的单词的tweets集合。
    	List<Tweet> message = new ArrayList<>();
    	int i , j , r , l , n = tweets.size() , flag = 0;
    	for(i = 0 ; i < n ; i++)
    	{
    		String[] txt = tweets.get(i).getText().split(" ");//首先要将text切割成单个单词的字符串集合。
    		int size = txt.length;
    		for(j = 0 ; j < size ; j++) //四重循环以进行单个字母单个字母的比较。
    		{
    			String k = txt[j];
    			for(r = 0 ; r < words.size(); r++)
    			{
    				String m = words.get(r);
    				if(m.length() == k.length())
    				{
    					for(l = 0 ; l < m.length() ; l++)
    					{
    						if(!( (m.charAt(l) == k.charAt(l)) || //针对大小写不敏感而添加的比较条件。
    								(m.charAt(l) == k.charAt(l)-32) || 
    								(m.charAt(l) == k.charAt(l)+32) ))
    							break;
    						else if(l == m.length() - 1)
    							flag = 1;
    					}
    				}
    			}
    		}
    		if(flag == 1)
    			message.add(tweets.get(i));
    		flag = 0;
    	}
    	
    	
    	return message;
    }

}
