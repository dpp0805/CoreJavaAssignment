package com.greatlearning.fsd.twitterapp.service;

import java.util.Set;

import com.greatlearning.fsd.twitterapp.exception.InvalidUserException;
import com.greatlearning.fsd.twitterapp.model.Tweet;
import com.greatlearning.fsd.twitterapp.model.User;

public interface UserService {
	User createUser(String userHandle,String firstName, String lastName, String emailAddress, String password);
	User updateUser(long userId, String password, String firstName, String lastName);
	void postTweet(long userId, Tweet tweet);
	void removeTweet(long userId, Tweet tweet);
	void likeTweet(long userId, long tweetId);
	void commentOnTweet(long userId, long tweetId, String comment);
	User authenticateUser(String username, String password) throws InvalidUserException;
	void follow(User user, User follower);
	void unfollow(User user, User follower);
	Set<User> suggestUsers(long userId);
	Set<Tweet> getAllTweetsByUser(long userId);
	Set<Tweet> getAllTweetsByFollowing(long userId);
	void showFollowings(long userId);
	void showFollowers(long userId);
}
