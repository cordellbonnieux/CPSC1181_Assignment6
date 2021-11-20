import java.util.ArrayList;

/**
 * This class creates a Messenger object which can be used to store Message objects and associated usernames(Strings).
 * Used to store conversations between different users.
 * @author Cordell Bonnieux
 * @since October 2021
 */
public class Messenger {
	
	private ArrayList<String> usernames;
	private ArrayList<Message> messages;
	
	/**
	 * Messenger Constructor
	 * Creates a new Messenger object
	 */
	public Messenger() {
		this.usernames = new ArrayList<String>();
		this.messages = new ArrayList<Message>();
	}
	
	/**
	 * Add User
	 * Appends a valid user to a Messenger instance
	 * @param username
	 */
	public void addUser(String username) {
		if (username.equals(null)) {
			throw new NullPointerException();
		}
		this.usernames.add(username);
	}
	
	/**
	 * Add Users
	 * Appends valid users to a Messenger instance
	 * @param username
	 */
	public void addUsers(ArrayList<String> usernames) {
		if (usernames.equals(null)) {
			throw new NullPointerException();
		}
		this.usernames.addAll(usernames);
	}
	
	/**
	 * Send Message
	 * Creates a new message instances and adds it to a Messenger instance
	 * @param sender String - sender's name
	 * @param receiver String - receiver's name
	 * @param text String - Main body of text
	 */
	public void sendMessage(String sender, String receiver, String text) {
		if (sender.equals(null) || receiver.equals(null) || text.equals(null)){
			throw new NullPointerException();
			
		} else if (!usernames.contains(sender)) {
			throw new IllegalArgumentException("Sender not found in usernames");
			
		} else if (!usernames.contains(receiver)) {
			throw new IllegalArgumentException("Receiver not found in usernames");
		}

		Message message = new Message(text, sender, receiver);
		messages.add(message);
	}

	/**
	 * Send a Smile Message
	 * Creates a new smile message instance and adds it to a Messenger instance
	 * @param sender String - sender's name
	 * @param receiver String - receiver's name
	 */
	public void sendSmile(String sender, String receiver) {
		if (sender.equals(null) || receiver.equals(null)){
			throw new NullPointerException();
			
		} else if (!usernames.contains(sender)) {
			throw new IllegalArgumentException("Sender not found in usernames");
			
		} else if (!usernames.contains(receiver)) {
			throw new IllegalArgumentException("Receiver not found in usernames");
		}

		SmileMessage message = new SmileMessage(sender, receiver);
		messages.add(message);	
	}
		
	/**
	 * Get Received Messages
	 * Returns an ArrayList which includes all the message from user, which have a state of status
	 * If status is UNREAD, it is then set to READ
	 * @param user String - a valid username
	 * @param status Status - status type of messages to be return
	 * @return ArrayList<Message> - list of all requested messages
	 */
	public ArrayList<Message> getReceivedMessages(String user, Message.Status status) {
		if (status.equals(null)) {
			throw new NullPointerException();
			
		} else if (user.equals(null)) {
			throw new NullPointerException();
			
		} else if (!usernames.contains(user)) {
			throw new IllegalArgumentException("User not found in usernames");
		}
		
		ArrayList<Message> list = new ArrayList<Message>();
		
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getStatus().equals(status) && messages.get(i).getRecipient().equals(user)) {		
				if (status.equals(Message.Status.UNREAD)) {
					messages.get(i).setStatus(Message.Status.READ);	
				}
				list.add(messages.get(i));
			}
		}
		
		return list;
	}
	
	/**
	 * Get Received Messages
	 * Returns all the messages from user in an ArrayList, if messages are UNREAD
	 * they will be changed to READ
	 * @param user String - a valid username
	 * @return ArrayList<Message> - list of all messages associated with user
	 */
	public ArrayList<Message> getReceivedMessages(String user) {
		if (user.equals(null)) {
			throw new NullPointerException();
			
		} else if (!usernames.contains(user)) {
			throw new IllegalArgumentException("User not found in usernames");
		}
		
		ArrayList<Message> list = new ArrayList<Message>();
		
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getRecipient().equals(user)) {
				if (messages.get(i).getStatus().equals(Message.Status.UNREAD)) {
					messages.get(i).setStatus(Message.Status.READ);
				}
				list.add(messages.get(i));	
			}
		}
		return list;
	}
	
	/**
	 * Get Users
	 * Returns all the users in the messager  
	 * @return ArrayList<String> - a list of all usernames in the messenger object.
	 */
	public ArrayList<String> getUsers() {
		return new ArrayList<String>(this.usernames);
	}
	
	/**
	 * Get Number Of Messages
	 * Returns the total number of messages exchanged
	 * @return int the number of messages sent
	 */
	public int getNumberOfMessages() {
		return this.messages.size();
	}

}
