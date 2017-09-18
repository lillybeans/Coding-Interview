package SystemDesign;

import java.util.*;

public class ChatServer {
	 class UserManager{
		 private static UserManager instance;
		 private HashMap<Integer, User> onlineUsers;
		 
		 public void addUser(User fromUser, String toAccountName){}
		 public void approveAddRequest(AddRequest req){}
		 public void rejectAddRequest(AddRequest req){}
		 public void userSignedOn(String accountName){}
		 public void userSignedOff(String accountName){}
	 }
	 
	 class User{ 
		 private int id; //call using getters like getID()
		 private String accountName; //call using getters like getAccountName()
		 private UserStatus status=null; //call using getStatus()
		 private HashMap<Integer, User> contacts;
		 private HashMap<Integer, PrivateChat> privateChats;
		 private ArrayList<GroupChat> groupChats;
		 private HashMap<Integer, AddRequest> receivedAddRequests;
		 private HashMap<Integer, AddRequest> sentAddRequests;
		 
		 public User(){}
		 public void setStatus(){}
		 public boolean addContact(User user){}
		 public boolean sendMessageToUser(User to, String content){}
		 public boolean sendMessageToGroupChat(int id, String cnt){}
		 public void addConversation(PrivateChat conversation){}
		 public void addConversation(GroupChat conversation){}
		 public void receivedAddRequest(AddRequest req){}
		 public void sentAddRequest(AddRequest req){}
		 public void removeAddRequest(AddRequest req){}
		 
	 }
	
	 abstract class Conversation{
		 protected ArrayList<User> participants;
		 protected int id; //getter
		 protected ArrayList<Message> messages; //getter
		 
		 public boolean addMessage(Message m){}
	 }
	 
	 class PrivateChat extends Conversation{}
	 class GroupChat extends Conversation{}
	 
	 class Message{
		 private String content; //getContent()
		 private Date date; //getDate()
	 }

	 /***Following classes have little functionality. Main purpose: group data other classes will use ***/
	 
	 class AddRequest{
		 private User from;
		 private User to;
		 private Date date;
		 RequestStatus status;
	 }
	 
	 class UserStatus{
		 private UserStatusType type;
	 }
	 
	 public enum UserStatusType{
		 Offline, Away, Idle, Available, Busy
	 }
	 
	 public enum RequestStatus{
		 Unread, Read, Accepted, Rejected
	 }

}
