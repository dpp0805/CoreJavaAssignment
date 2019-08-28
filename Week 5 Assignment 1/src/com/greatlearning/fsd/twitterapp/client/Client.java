package com.greatlearning.fsd.twitterapp.client;

import java.util.Scanner;
import java.util.Set;

import com.greatlearning.fsd.twitterapp.model.Tweet;
import com.greatlearning.fsd.twitterapp.model.User;
import com.greatlearning.fsd.twitterapp.service.InMemoryUserServiceImpl;
import com.greatlearning.fsd.twitterapp.service.UserService;
import com.greatlearning.fsd.twitterapp.exception.InvalidUserException;

public class Client {
	private static Scanner sc = new Scanner(System.in);	;
	private static UserService userService = new InMemoryUserServiceImpl();
	
	public static void main(String[] args) throws InvalidUserException  {
		Client twitter = new Client();
		User user = null;	
		
		/*
		 Sample Account (for testing):
		 	User user1 = userService.createUser( "preetom93", "Preetom", "Bhowmik", "preetom@gmail.com", "Pass1");
		 	User name: preetom93
		 	Password: Pass1
		 */
		
		boolean close = false;
		while(close == false) {
			System.out.println("***************** Welcome to Twitter ********************");
			System.out.println("Press 1 to populate mock data");
			System.out.println("Press 2 to go to Twitter");
			System.out.println("Press 0 to close Twitter");
			int option = sc.nextInt();
			switch(option) {
				case 1:
					twitter.populateData();
					break;
				case 2:
					user = twitter.twitterPage();
					twitter.userPage(user);
					break;
				case 0:
					System.out.println("***************** Thank You for Visiting Twitter ********************");
					close = true;
					break;
				default:
					System.out.println("Invalid Option !!!");
			}	
		}
		
		sc.close();
	}
	
	private User twitterPage() {
		User user = null;
		
		boolean exit = false;
		while(exit == false) {
			System.out.println("================= Twitter Login Page ==========================");
			System.out.println("Press 1 => Sign Up");
			System.out.println("Press 2 => Log In");
			System.out.println("Press 3 => Update user");
			System.out.println("Press 0 => Exit");
			System.out.println("====================================================");
       		
			int option = sc.nextInt();
			switch(option) {
				case 1:
					user = createUserAccount();			
					System.out.println("User successfully created");
					System.out.println(user);
					break;
				case 2:
					user = login();
					exit = user == null ? false : true;
					break;
				case 3:
					user = updateUser();
					System.out.println(user);
					break;
				case 0:
					user = null;
					exit = true;
					break;
				default:
					System.out.println("Invalid Option !!!");
			}
		}
		
		return user;
	}

	private void userPage(User user) {
		while(user != null) {
			System.out.println("===================== User Account ==================");
			System.out.println("1 => Post a Tweet");
			System.out.println("2 => View external tweet feed");
			System.out.println("3 => View my Tweets");
			System.out.println("4 => Like a Tweet");
			System.out.println("5 => Comment on a tweet");
			System.out.println("6 => Show my followings");
			System.out.println("7 => Show my followerss");
			System.out.println("0 => Logout");
			System.out.println("====================================================");
			
			sc = new Scanner(System.in);		
			int option = sc.nextInt();
			switch(option) {
				case 1: 
					postTweet(user); 
					break;
				case 2: 
					viewExternalTweets(user);
					break;
				case 3: 
					viewMyTweets(user);
					break;
				case 4: 
					likeTweet(user);
					break;
				case 5: 
					commentOnTweet(user);
					break;
				case 6: 
					showFollowing(user);
					break;
				case 7:
					showFollowers(user);
					break;
				case 0:
					user = null;;
					System.out.println("================= Logged out !!!! =================== ");
					break;
				default:
					System.out.println("Invalid Option !!!");
			}	
		}
	}
	
	//Create Account
	private User createUserAccount() {
		System.out.println("============== User Registration ===============");
			
		System.out.println("Please enter your prefered user handle");
		String userHandle = sc.next();
			 
		System.out.println("Please enter your first name");
		String firstName = sc.next();
			 
		System.out.println("Please enter your last name");
		String lastName = sc.next();
			 
		System.out.println("Please enter your email");
		String emailAddress = sc.next();
			 
		System.out.println("Please enter password");
		String password = sc.next();
	 
		System.out.println("============== User Registration Ends ===============");
		User user = userService.createUser(userHandle, firstName, lastName, emailAddress, password);	
		return user;
	}
	//Login
	private User login() {
		System.out.println("================= Logged in !!!! =================== ");	
		User user = null;
		System.out.println("Please enter your user name");
		String username = sc.next();
		System.out.println("Please enter new password");
		String password = sc.next();

		try {
			user = userService.authenticateUser(username, password);
		}
		catch (InvalidUserException ex) {
			System.out.println(ex.getMessage());
		}
		
		return user;
	}
	//Update User Account
	private User updateUser() {
		System.out.println("============== Update User Details ===============");
		
		System.out.println("Please enter User Id");
		long userId = sc.nextLong();
		
		System.out.println("Please enter new password");
		String password = sc.next();
		 
		System.out.println("Please enter your first name");
		String firstName = sc.next();
		 
		System.out.println("Please enter your last name");
	
		String lastName = sc.next();
		System.out.println("============== Update User Detail Ends===============");

		User user = userService.updateUser(userId, password, firstName, lastName);
		return user;	
	}
	

	private void postTweet(User user) {
		System.out.println("========== Posting Tweet =========");
		System.out.println("Tweet:  ");
		Scanner sc2 = new Scanner(System.in);
		String tweetMsg = sc2.nextLine();
		Tweet tweet = new Tweet(user, tweetMsg);
		userService.postTweet(user.getUserId(), tweet);
	}

	private void viewExternalTweets(User user) {
		System.out.println("========== External tweets feed ==========");
		Set<Tweet> tweets = userService.getAllTweetsByFollowing(user.getUserId());
		System.out.println(tweets);
	}

	private void viewMyTweets(User user) {
		System.out.println("========== Tweets by you ==========");
		Set<Tweet> tweets = userService.getAllTweetsByUser(user.getUserId());
		System.out.println(tweets);
	}

	private void likeTweet(User user) {
		System.out.println("========= Like a tweet =========");
		System.out.println("Like tweet (Enter Tweet Id): ");
		long tweetId = sc.nextLong();
		userService.likeTweet(user.getUserId(), tweetId);
	}

	private void commentOnTweet(User user) {
		System.out.println("========= Commented on a tweet ========");
		System.out.println("Comment on tweet (Enter Tweet Id): ");
		long tweetId = sc.nextLong();
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Comment:  ");
		String comment = sc2.nextLine();
		userService.commentOnTweet(user.getUserId(), tweetId, comment);
	}
	
	private void showFollowing(User user) {
		System.out.println("================= You Follow ===============");
		userService.showFollowings(user.getUserId());
	}
	
	private void showFollowers(User user) {
		System.out.println("============= You are Followed by ===========");
		userService.showFollowers(user.getUserId());
	}
	
	private void populateData() {
		System.out.println("================== Populating Mock Data =====================");
		
        User user1 = userService.createUser( "preetom93", "Preetom", "Bhowmik", "preetom@gmail.com", "Pass1");
        User user2 = userService.createUser( "pant96", "Rishabh", "Pant", "rishabh@gmail.com", "Pass2");
        User user3 = userService.createUser( "kohli91", "Virat", "Kohli", "virat@outlook.com", "Pass3");
        User user4 = userService.createUser( "rohit90", "Rohit", "Sharma", "rohit@outlook.com", "Pass4");
        User user5 = userService.createUser( "dhoni88", "MS", "Dhoni", "dhoni@gmail.com", "Pass5");
        
        userService.follow(user2, user1);
        userService.follow(user3, user1);
        userService.follow(user4, user1);
        userService.follow(user5, user1);
        
        userService.follow(user3, user2);
        userService.follow(user4, user2);
        userService.follow(user5, user2);
        
        userService.follow(user4, user3);
        userService.follow(user5, user3);
        
        userService.follow(user2, user4);
        userService.follow(user2, user4);
        
        userService.follow(user3, user5);
        userService.follow(user4, user5);
        userService.follow(user5, user5);

        Tweet tweet1 = new Tweet(user1, "Excited to WC-2019 !!!");
        userService.postTweet(user1.getUserId(), tweet1);
        Tweet tweet2 = new Tweet(user2 , "My WC debut in england 2019. Thank you for your wishes !!!");
        userService.postTweet(user2.getUserId(), tweet2);
        Tweet tweet3 = new Tweet(user3, "We will try our best to win the WC-2019");
        userService.postTweet(user3.getUserId(), tweet3);
        Tweet tweet4 = new Tweet(user4, "I hope to get Man of The Tournament !!!");
        userService.postTweet(user4.getUserId(), tweet4);
        Tweet tweet5 = new Tweet(user5, "This is a very competetive world cup");
        userService.postTweet(user5.getUserId(), tweet5);
        
        System.out.println("================= Finished Populating Mock Data =====================");
    }
}
