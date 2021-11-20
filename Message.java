import java.util.ArrayList;

/**
 * This class is used for storing information to be sent and received as a message. 
 * @author Cordell Bonnieux
 * @since September 2021
 */
public class Message {
	
	/*
	 * counter is used to keep track of the length of every message created by the class's constructors
	 * the index of the counter implicitly keeps track of the number of Message objects instantiated.
	 */
	private static ArrayList<Integer> counter = new ArrayList<Integer>();

	/*
	 * Status enumerator is used to keep track of a Message object's current status.
	 */
	public enum Status { READ, UNREAD, STARRED };
	
	/*
	 * Class data members
	 */
	private String text;
	private String sender;
	private String recipient;
	private Status status;
	//stores an instance's index for the class's counter
	private int counterIndex; 
	
	/**
	 * Default constructor
	 * @param t String - main body of message
	 * @param s String - sender's name
	 * @param r String - recipient's name
	 * @param x Status - current status of message
	 */
	public Message(String t, String s, String r, Status x) {
		if (x.equals(null)) {
			throw new NullPointerException("Status must not be null");
			
		} else if (t.equals(null) || s.equals(null) || r.equals(null)) {
			throw new NullPointerException("String parameters cannot be null");
		}
		this.text = t;
		this.sender = s;
		this.recipient = r;
		this.status = x;
		counter.add(t.length());
		this.counterIndex = counter.size() - 1;		
	}
	
	/**
	 * Short constructor, the Status object for instances 
	 * created by this constructor are set to UNREAD.
	 * @param t String - main body of message
	 * @param s String - sender's name
	 * @param r String - recipient's name
	 */
	public Message(String t, String s, String r) {
		this(t, s, r, Status.UNREAD);
	}
	
	/**
	 * Get Text
	 * @return t String - main body of text
	 */
	public String getText() {
		String t = this.text;
		return t;
	}
	
	/**
	 * Get Sender
	 * @return sender String - sender's name
	 */
	public String getSender() {
		String sender = this.sender;
		return sender;
	}
	
	/**
	 * Get Recipient
	 * @return r String - recipient's name
	 */
	public String getRecipient() {
		String r = this.recipient;
		return r;
	}
	
	/**
	 * Get Status
	 * @return s Status - Status enumerator, representing the instance's current status
	 */
	public Status getStatus() {
		Status s = this.status;
		return s;
	}
	
	/**
	 * Get Counter Index
	 * @return int n - the instance's index in the class message counter
	 */
	public int getCounterIndex() {
		int n = this.counterIndex;
		return n;
	}
	
	/**
	 * Set Status
	 * @param s Status - a Status enum to assign to the current instance
	 */
	public void setStatus(Status s) {
		if (s.equals(null)) {
			throw new NullPointerException("Status must not be null");
		}
		this.status = s;
		// maybe add a switch statement here to handle enum?
	}

	/**
	 * Get Total Number Of Messages
	 * Static class method
	 * @return int n - the total number of Message objects created
	 */
	public static int getTotalNumberOfMessages() {
		int n = counter.size();
		return n;
	}
	
	/**
	 * Get Number Of Characters
	 * Static class method
	 * @param int index - the index of the message
	 * @return int n - the number of characters in the Message object at position index
	 */
	public static int getNumberOfChars(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("index must not be negative");
		} else if (index > Message.counter.size() - 1) {
			throw new IllegalArgumentException("parameter index greater than maximum index");
		}
		int n = counter.get(index);
		return n;
	}
	
	/**
	 * Get Total Number Of Characters
	 * Static class method
	 * @return int n - the total number of characters in all Message instances
	 */
	public static int getTotalNumberOfChars() {
		int n = 0;
		for (int i = 0; i < counter.size(); i++) {
			n += counter.get(i);
		}
		return n;
	}
	
	/**
	 * Reset Counter
	 * Static class method
	 */
	public static void resetCounter() {
		counter = new ArrayList<Integer>();
	}
	
	/**
	 * To String
	 * @return String out - a description including all the caller instance's data member info
	 */
	public String toString() {
		String out = "*** Message Info ***\n";
		out += "Sender: " + this.sender + "\n";
		out += "Recipient: " + this.recipient + "\n";
		out += "Status: " + this.status + "\n";
		out += "Message Text: \n" + this.text + "\n";
		out += "Total Number of Characters: " + Message.getNumberOfChars(this.getCounterIndex()) + "\n";
		out += "Message #" + (this.getCounterIndex() + 1) + ", Counter Index: " + this.getCounterIndex() + "\n";
		return out;
	}
	
}
