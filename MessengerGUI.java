import java.util.*;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessengerGUI extends Application {
	/*
	 * 
	 */
	private BorderPane root;
	private TabPane container;
	private Text topMessage;
	/*
	 * tab one
	 */
	private Tab tabOne;
	private HBox tabOneContainer;
	private Text enterUserName
	private TextField enterUserNameField;
	private Button selectUserName;
	/*
	 * tab two
	 */
	private Tab tabTwo;
	private VBox tabTwoContainer;
	private HBox tabTwoTop;
	private HBox tabTwoBottom;
	private TextArea messageDisplay;
	private Button nextMessage;
	private Button loadAllMessages = new Button("Load All Messages");
	private Button loadUnreadMessages = new Button("Load Unread Messages");
	/*
	 * tab three
	 */
	private Tab tabThree;
	private VBox tabThreeContainer;
	private HBox tabThreeBottom;
	private TextField recipientField;
	private TextArea messageArea;
	private Text messageType = new Text("Message Type");
	private ToggleGroup smileOrWritten = new ToggleGroup();
	private RadioButton smile;
	private RadioButton written;
	
	
	public void start(Stage stage) {
		
		buildUI();
		buildTabOne();
		buildTabTwo();
		buildTabThree();
		
	}
	
	public static void buildUI() {
		
	}
	
	public static void buildTabOne() {
		
	}
	
	public static void buildTabTwo() {
		
	}
	
	public static void buildTabThree() {
		
	}

}
