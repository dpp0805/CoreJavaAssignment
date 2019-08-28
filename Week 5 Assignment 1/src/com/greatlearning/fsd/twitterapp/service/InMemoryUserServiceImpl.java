package com.greatlearning.fsd.twitterapp.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.greatlearning.fsd.twitterapp.exception.InvalidUserException;
import com.greatlearning.fsd.twitterapp.model.Tweet;
import com.greatlearning.fsd.twitterapp.model.User;

public class InMemoryUserServiceImpl implements UserService {
	private static final Map<Long, User> users = new HashMap<>();
	
	@Override
	public User createUser(String userHandle,String firstName, String lastName, String emailAddress, String password) {
		User user = new User(userHandle, firstName, lastName,  emailAddress, password);
		users.put(user.getUserId(), user);
		
		return user;
	}

	@Override
	public User updateUser(long userId, String password, String firstName, String lastName) {
		if(!users.containsKey(userId)) {
			throw new IllegalArgumentException("Invalid user ID");
		}
		
		User user = users.get(userId);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		users.put(userId, user);
		
		return user;
	}

	@Override
	public void postTweet(long userId, Tweet tweet) {
		User existingUser = validateUserId(userId);
		existingUser.addTweet(tweet);
	}

	@Override
	public void removeTweet(long userId, Tweet tweet) {
		User existingUser = validateUserId(userId);
		existingUser.deleteTweet(tweet);
	}

	@Override
	public User authenticateUser(String username, String password) throws InvalidUserException {
		Set<Map.Entry<Long, User>> entries = users.entrySet();
		Iterator<Map.Entry<Long,User>> iterator = entries.iterator();
		while(iterator.hasNext()) {
			Map.Entry<Long, User> entry = iterator.next();
			User user = entry.getValue();
			if(user.getUserHandle().equals(username)) {
				if(!user.getPassword().equals(password)) {
					throw new InvalidUserException("Username / password does not match");
				}
				else {
					System.out.println("Successfully Logged In !!!");
					return user;
				}
			}
		}
		
		throw new InvalidUserException("User not found");
	}

	@Override
	public void follow(User user, User follower) {
		user.addFollower(follower);
	}

	@Override
	public void unfollow(User user, User follower) {
		user.removeFolllower(follower);
	}
	
	@Override
	public Set<User> suggestUsers(long userId){
		User existingUser = validateUserId(userId);
		Set<User> suggestedUsers = new HashSet<>();
		
		Set<Map.Entry<Long, User>> entries = users.entrySet();
		Iterator<Map.Entry<Long,User>> iterator = entries.iterator();
		while(iterator.hasNext()) {
			Map.Entry<Long, User> entry = iterator.next();			
			User user = entry.getValue();
			if(user.getUserId() == userId ||existingUser.getFollowings().contains(user)) {
				continue;
			}
			suggestedUsers.add(user);
		}
		
	return suggestedUsers;
	}
	
	@Override
	public Set<Tweet> getAllTweetsByUser(long userId) {
		User user = validateUserId(userId);
		
		return user.getTweets();
	}
	
	@Override
	public Set<Tweet> getAllTweetsByFollowing(long userId) {
		User user = validateUserId(userId);
		Set<Tweet> consolidateTweets = new HashSet<Tweet>();
		Set<User> following = user.getFollowings();
		Iterator<User> iterator = following.iterator();
		while(iterator.hasNext()) {
			User followingUser = iterator.next();
			consolidateTweets.addAll(followingUser.getTweets());
		}
		
		return consolidateTweets;
	}	
	
	@Override
	public void likeTweet(long userId, long tweetId) {
		User user = validateUserId(userId);
		Set<Tweet> tweets = user.getTweets();
		
		Set<User> followings = user.getFollowings();
		Iterator<User> userIterator = followings.iterator();
		while(userIterator.hasNext()) {
			tweets.addAll(userIterator.next().getTweets());
		}
			
		Iterator<Tweet> tweetIterator = tweets.iterator();
		while(tweetIterator.hasNext()) {
			Tweet currentTweet = tweetIterator.next();
			if(currentTweet.getId() == tweetId) {
				currentTweet.addLike();
			}	
		}
	}
	
	@Override
	public void commentOnTweet(long userId, long tweetId, String comment) {
		User user = validateUserId(userId);
		Set<Tweet> tweets = user.getTweets();
		
		Iterator<Tweet> iterator = tweets.iterator();
		while(iterator.hasNext()) {
			Tweet currentTweet = iterator.next();
			if(currentTweet.getId() == tweetId) {
				currentTweet.addComment(comment);
			}
		}
	}
	
	@Override
	public void showFollowings(long userId) {
		User user = validateUserId(userId);
		
		System.out.println(user.getFollowings());
	}

	@Override
	public void showFollowers(long userId) {
		User user = validateUserId(userId);
		System.out.println(user.getFollowers());
	}
	
	private User validateUserId(long userId) {
		if(!users.containsKey(userId)) {
			throw new IllegalArgumentException("Invalid User Id");
		}
		
		return users.get(userId);
	}
}
