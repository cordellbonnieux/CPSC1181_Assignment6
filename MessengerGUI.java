import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
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
	private final double WIDTH = 600;
	private final double HEIGHT = 450;
	private BorderPane root;
	private Scene scene;
	private VBox mainContainer;
	private TabPane tabContainer;
	private Text topText;
	/*
	 * tab one
	 */
	private Tab tabOne;
	private HBox tabOneContainer;
	private Text enterUserName;
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
	private Button loadAllMessages;
	private Button loadUnreadMessages;
	/*
	 * tab three
	 */
	private Tab tabThree;
	private VBox tabThreeContainer;
	private HBox tabThreeTop;
	private HBox tabThreeBottom;
	private Text recipientFieldLabel;
	private TextField recipientField;
	private TextArea messageArea;
	private Text messageType = new Text("Message Type");
	private ToggleGroup smileOrWritten = new ToggleGroup();
	private RadioButton smile;
	private RadioButton written;
	private Button send;
	
	
	public void start(Stage stage) {
		
		buildUI(stage);
		buildTabOne();
		buildTabTwo();
		buildTabThree();
		
		// attach events
		
	}
	
	public void buildUI(Stage stage) {
		root = new BorderPane();
		scene = new Scene(root);
		topText = new Text("Select A User");
		tabContainer = new TabPane();
		mainContainer = new VBox(topText, tabContainer);
		root.setCenter(mainContainer);
		root.setPrefSize(WIDTH, HEIGHT);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("MessengerGUI");
		stage.show();
	}
	
	public void buildTabOne() {
		tabOne = new Tab("Choose User");
		tabOne.setClosable(false);
		enterUserName = new Text("Enter Username:");
		enterUserNameField = new TextField();
		selectUserName = new Button("Select");
		tabOneContainer = new HBox(enterUserName, enterUserNameField, selectUserName);
		tabOne.setContent(tabOneContainer);
		tabContainer.getTabs().add(tabOne);
	}
	
	public void buildTabTwo() {
		tabTwo = new Tab("Read Messages");
		tabTwo.setClosable(false);
		messageDisplay = new TextArea("No Messages Displayed"); //make this uneditable
		nextMessage = new Button("Next");
		tabTwoTop = new HBox(messageDisplay, nextMessage);
		loadAllMessages = new Button("Load All Messages");
		loadUnreadMessages = new Button("Load Unread Messages");
		tabTwoBottom = new HBox(loadAllMessages, loadUnreadMessages);
		tabTwoContainer = new VBox(tabTwoTop, tabTwoBottom);
		tabTwo.setContent(tabTwoContainer);
		tabContainer.getTabs().add(tabTwo);
	}
	
	public void buildTabThree() {
		tabThree = new Tab("Send Message");
		tabThree.setClosable(false);
		recipientFieldLabel = new Text("To:");
		recipientField = new TextField();
		tabThreeTop = new HBox(recipientFieldLabel, recipientField);
		messageArea = new TextArea();
		messageType = new Text("Message Type");
		smileOrWritten = new ToggleGroup();
		smile = new RadioButton("Smile");
		written = new RadioButton("Written");
		send = new Button("Send");
		smile.setToggleGroup(smileOrWritten);
		written.setToggleGroup(smileOrWritten);
		tabThreeBottom = new HBox(messageType, smile, written, send);
		tabThreeContainer = new VBox(tabThreeTop, messageArea, tabThreeBottom);
		tabThree.setContent(tabThreeContainer);
		tabContainer.getTabs().add(tabThree);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
