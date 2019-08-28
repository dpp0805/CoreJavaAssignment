package com.greatlearning.fsd.twitterapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class User implements Comparable<User> {
	private static long userIdCounter = 1000;
	
	private final long userId;
	private final String userHandle;
	private String firstName;
	private String lastName;
	private String password;
	private final String emailAddress;
	
	private final Set<User> followers = new HashSet<>();
	private final Set<User> followings = new HashSet<>();
	private final Date createdDate = new Date();
	private Date updatedDate = new Date();
	
	private String profilePic;
	private String coverImage;

	private final Set<Tweet> tweets = new HashSet<>();	
	
	public User(String userHandle,String firstName, String lastName, String emailAddress, String password) {
		this.userId = ++ userIdCounter;
		this.userHandle = userHandle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public User(String userHandle, String firstName, String lastName, String emailAddress, String password, 
			String profilePic, String coverImage) {		
		this.userId = ++ userIdCounter;
		this.userHandle = userHandle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.profilePic = profilePic;
		this.coverImage = coverImage;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserHandle() {
		return userHandle;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<User> getFollowers() {
		return followers;
	}
	public void addFollower(User follower) {
		this.followers.add(follower);
		follower.addFollowing(this);
	}
	public void removeFolllower(User follower) {
		this.followers.remove(follower);
		follower.removeFollowing(this);
	}
	
	public Set<User> getFollowings() {
		return followings;
	}
	public void addFollowing(User following) {
		this.followings.add(following);
	}
	public void removeFollowing(User following) {
		this.followings.remove(following);
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}
	public void addTweet(Tweet tweet) {
		this.tweets.add(tweet);
	}
	public void deleteTweet(Tweet tweet) {
		this.tweets.remove(tweet);
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((userHandle == null) ? 0 : userHandle.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (userHandle == null) {
			if (other.userHandle != null)
				return false;
		} else if (!userHandle.equals(other.userHandle))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nUser {userId=" + userId + ", userHandle=" + userHandle + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailAddress=" + emailAddress + "}";
	}

	@Override
	public int compareTo(User user) {
		return this.userHandle.compareTo(user.userHandle);
	}
	
}
