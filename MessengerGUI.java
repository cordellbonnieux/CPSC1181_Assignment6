import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * MessengerGUI
 * This program allows the user to send and recieve messages from a graphical user interface
 * @author Cordell Bonnieux
 * @since November 2021
 *
 */
public class MessengerGUI extends Application {
	/*
	 * Logic data members
	 */
	private Messenger messenger = new Messenger();
	private String currentUser;
	private int pageCounter;
	private ArrayList<Message> currentMessages;
	private Font font = new Font("Monospaced", 12);
	/*
	 * main ui
	 */
	private final double WIDTH = 450;
	private final double HEIGHT = 350;
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
	private Text messageType;
	private ToggleGroup smileOrWritten;
	private RadioButton smile;
	private RadioButton written;
	private Button send;
	
	/**
	 * Start the program
	 * @param stage Stage
	 */
	public void start(Stage stage) {
		// build the GUI
		buildUI(stage);
		buildTabOne();
		buildTabTwo();
		buildTabThree();
		
		// attach event listeners
		tabOneEvents();
		tabTwoEvents();
		tabThreeEvents();
		
		// added sample users as shown in example video
		messenger.addUser("A");
		messenger.addUser("B");
	}
	
	/**
	 * Build UI
	 * Sets up boiler plate code and UI container
	 * @param stage
	 */
	public void buildUI(Stage stage) {
		root = new BorderPane();
		scene = new Scene(root);
		topText = new Text("Select A User");
		tabContainer = new TabPane();
		mainContainer = new VBox(topText, tabContainer);
		topText.setStyle("-fx-font-size:1.3em;");
		mainContainer.setAlignment(Pos.TOP_CENTER);
		root.setCenter(mainContainer);
		root.setPrefSize(WIDTH, HEIGHT);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("MessengerGUI");
		stage.show();
	}
	
	/**
	 * Build Tab One
	 * Sets up the UI elements for tab one
	 */
	public void buildTabOne() {
		tabOne = new Tab("Choose User");
		tabOne.setClosable(false);
		enterUserName = new Text("Enter Username: ");
		enterUserNameField = new TextField();
		enterUserNameField.setFont(font);
		enterUserNameField.setPadding(new Insets(7));
		selectUserName = new Button("Select");
		selectUserName.setPadding(new Insets(7));
		tabOneContainer = new HBox(5,enterUserName, enterUserNameField, selectUserName);
		tabOneContainer.setPadding(new Insets(5));
		tabOneContainer.setAlignment(Pos.CENTER);
		tabOne.setContent(tabOneContainer);
		tabContainer.getTabs().add(tabOne);
	}
	
	/**
	 * Build Tab Two
	 * Sets up the ui elements for tab two
	 */
	public void buildTabTwo() {
		tabTwo = new Tab("Read Messages");
		tabTwo.setClosable(false);
		messageDisplay = new TextArea("No Messages Displayed");
		messageDisplay.setEditable(false);
		messageDisplay.setPrefHeight(255);
		messageDisplay.setFont(font);
		HBox.setMargin(messageDisplay, new Insets(3));
		nextMessage = new Button("Next");
		nextMessage.setDisable(true);
		nextMessage.setPadding(new Insets(7));
		nextMessage.setPrefWidth(215);
		tabTwoTop = new HBox(messageDisplay, nextMessage);
		loadAllMessages = new Button("Load All Messages");
		loadAllMessages.setPadding(new Insets(7));
		loadUnreadMessages = new Button("Load Unread Messages");
		loadUnreadMessages.setPadding(new Insets(7));
		tabTwoBottom = new HBox(5,loadAllMessages, loadUnreadMessages);
		tabTwoContainer = new VBox(tabTwoTop, tabTwoBottom);
		tabTwoTop.setAlignment(Pos.CENTER);
		tabTwoBottom.setAlignment(Pos.CENTER);
		tabTwoContainer.setPadding(new Insets(5));
		tabTwo.setContent(tabTwoContainer);
		tabContainer.getTabs().add(tabTwo);
	}
	
	/**
	 * Build Tab Three
	 * Sets up the ui elements for tab three
	 */
	public void buildTabThree() {
		tabThree = new Tab("Send Message");
		tabThree.setClosable(false);
		recipientFieldLabel = new Text("To:");
		recipientField = new TextField();
		recipientField.setFont(font);
		recipientField.setPrefHeight(20);
		tabThreeTop = new HBox(3,recipientFieldLabel, recipientField);
		tabThreeTop.setAlignment(Pos.CENTER_LEFT);
		messageArea = new TextArea();
		messageArea.setFont(font);
		messageArea.setPrefHeight((HEIGHT/3)*2);
		messageType = new Text("Message Type");
		smileOrWritten = new ToggleGroup();
		smile = new RadioButton("Smile");
		written = new RadioButton("Written");
		send = new Button("Send");
		send.setPadding(new Insets(7));
		send.setDisable(true);
		smile.setToggleGroup(smileOrWritten);
		written.setToggleGroup(smileOrWritten);
		tabThreeBottom = new HBox(5,messageType, smile, written, send);
		tabThreeBottom.setAlignment(Pos.BASELINE_CENTER);
		HBox.setMargin(send, new Insets(5, 30, 0, 20));
		tabThreeContainer = new VBox(5,tabThreeTop, messageArea, tabThreeBottom);
		tabThreeContainer.setPadding(new Insets(3));
		tabThree.setContent(tabThreeContainer);
		tabContainer.getTabs().add(tabThree);
	}
	
	/**
	 * Tab One Events
	 * Adds event listeners to tab one elements
	 */
	public void tabOneEvents() {
		selectUserName.setOnAction(e -> { 
			boolean found = false;
			for (int i = 0; i < messenger.getUsers().size(); i++) {
				if (enterUserNameField.getText().trim().length() > 0) {
					if (enterUserNameField.getText().trim().equals(messenger.getUsers().get(i))) {
						currentUser = enterUserNameField.getText().trim();
						topText.setText("Current user: " + currentUser);
						found = true;
						enterUserNameField.setText("");
					} else if (i == messenger.getUsers().size() - 1 && !found) {
						topText.setText("Incorrect Username");
					}
				}
			}
		});
	}
	
	/**
	 * Tab Two Events
	 * Adds event listeners to tab two elements
	 */
	public void tabTwoEvents() {
		loadAllMessages.setOnAction(e -> {
			if (currentUser != null) {
				try {
					currentMessages = messenger.getReceivedMessages(currentUser);
					if (currentMessages.size() > 0) {
						messageDisplay.setText(currentMessages.get(0).toString());
						pageCounter = 0;
						if (currentMessages.size() > 1) {
							nextMessage.setDisable(false);
						}
					} else {
						messageDisplay.setText("No Messages To Display");
					}
				} catch (NullPointerException err) {
					System.out.println(err.getMessage());
				} catch (IllegalArgumentException err) {
					System.out.println(err.getMessage());
				}
			}
		});
		loadUnreadMessages.setOnAction(e -> {
			if (currentUser != null) {
				try {
					currentMessages = messenger.getReceivedMessages(currentUser, Message.Status.UNREAD);
					if (currentMessages.size() > 0) {
						messageDisplay.setText(currentMessages.get(0).toString());
						pageCounter = 0;
						if (currentMessages.size() > 1) {
							nextMessage.setDisable(false);
						}
					} else {
						messageDisplay.setText("No Messages To Display");
					}
				} catch (NullPointerException err) {
					System.out.println(err.getMessage());
				} catch (IllegalArgumentException err) {
					System.out.println(err.getMessage());
				}
			}
		});
		nextMessage.setOnAction(e -> {
			if (currentUser != null && currentMessages != null) {
				if (pageCounter < currentMessages.size() - 1) {
					messageDisplay.setText(currentMessages.get(++pageCounter).toString());
					if (currentMessages.size() - 1 > pageCounter) {
						nextMessage.setDisable(false);
					} else {
						nextMessage.setDisable(true);
					}
				}
			}
		});
	}
	
	/**
	 * Tab Three Events
	 * Adds event listeners to tab three elements
	 */
	public void tabThreeEvents() {
		recipientField.setOnKeyReleased(e -> {
			if (currentUser != null ) {
				if (smile.isSelected() || written.isSelected()) {
					if (recipientField.getText().length() > 0){
						send.setDisable(false);
					} else {
						send.setDisable(true);
					}
				}
			}
		});
		send.setOnAction(e -> {
			if (currentUser != null) {
				try {
					if (messenger.getUsers().contains(recipientField.getText().trim())) {
						if (written.isSelected()) {
							messenger.sendMessage(currentUser, recipientField.getText().trim(), messageArea.getText());
							messageArea.setText("");
							recipientField.setText("");
						} else if (smile.isSelected()) {
							messenger.sendSmile(currentUser, recipientField.getText().trim());
							messageArea.setText("");
							recipientField.setText("");
						} else {
							System.out.println("something went wrong");
						}
					}
				} catch (NullPointerException err) {
					System.out.println(err.getMessage());
				} catch (IllegalArgumentException err) {
					System.out.println(err.getMessage());
				}
			}
		});
		smile.setOnAction(e -> {
			if (currentUser != null ) {
				messageArea.setEditable(false);
				messageArea.setText("");
				if (recipientField.getText().length() < 1) {
					send.setDisable(true);
				} else {
					send.setDisable(false);
				}
			}
		});
		written.setOnAction(e -> {
			if (currentUser != null) {
				messageArea.setEditable(true);
				if (recipientField.getText().length() < 1) {
					send.setDisable(true);
				} else {
					send.setDisable(false);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
