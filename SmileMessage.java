/**
 * Smile Message
 * A class which contains a text-image of a smiling doggy
 * @author Cordell Bonnieux
 *
 */
public class SmileMessage extends Message{
	private static String text = "(⌐ ͡■ ͜ʖ ͡■)";
	
	/**
	 * Class constructor
	 * @param sender String
	 * @param recipient String
	 */
	public SmileMessage(String sender, String recipient) {
		super(text, sender, recipient);
	}
}
