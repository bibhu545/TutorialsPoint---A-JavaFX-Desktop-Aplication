package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Scrollable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Welcome 
{
    @FXML
    private JFXButton start;
    Stage topic_primaryStage = null;
    Stage topic_content_primaryStage = null;
    static TreeMap<String, String> temp_hm = new TreeMap<String,String>();
//    ArrayList<String> temp_url = new ArrayList<>();
    
    @FXML
    void openContents(ActionEvent event) throws IOException 
    {
		((Node)event.getSource()).getScene().getWindow().hide(); 
		topic_primaryStage = new Stage();
		BorderPane bp = new BorderPane();
        
        Label topLabel = new Label("Here are the contents");
        topLabel.setId("topLabel");
        HBox top = new HBox(topLabel);
        top.setAlignment(Pos.CENTER);
        bp.setTop(top);
        
        Label bottomLabel = new Label("Choose any one subject you like to learn.");
        bottomLabel.setId("bottomLabel");
        HBox bottom = new HBox(bottomLabel);
        bottom.setAlignment(Pos.CENTER);
        bp.setBottom(bottom);

        VBox centerLeft = new VBox(10);
        centerLeft.setId("centerLeft");
        centerLeft.setAlignment(Pos.CENTER);
        
        int i = 0;
        for(i=0;i<8;i++)
        {
        	JFXButton left_items = new JFXButton(ScrapContents.h4.get(i));
        	left_items.setId("left_items");
        	left_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(left_items.getText());
					int temp=0;
					Welcome.temp_hm=ScrapContents.al.get(index);
					openChapters(event,left_items.getText());
				}
        	});
        	centerLeft.getChildren().add(left_items);
        }
        VBox centerMiddle = new VBox(10);
        centerMiddle.setId("centerMiddle");
        centerMiddle.setAlignment(Pos.CENTER);
        for(;i<17;i++)
        {
        	JFXButton center_items = new JFXButton(ScrapContents.h4.get(i));
        	center_items.setId("center_items");
        	center_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(center_items.getText());
					int temp=0;
					Welcome.temp_hm=ScrapContents.al.get(index);
					
					openChapters(event,center_items.getText());
				}
        		
        	});
        	centerMiddle.getChildren().add(center_items);
        }
        VBox centerRight = new VBox(10);
        centerRight.setId("centerRight");
        centerRight.setAlignment(Pos.CENTER);
        for(;i<25;i++)
        {
        	JFXButton right_items = new JFXButton(ScrapContents.h4.get(i));
        	right_items.setId("right_items");
        	right_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(right_items.getText());
					int temp=0;
					Welcome.temp_hm=ScrapContents.al.get(index);
					openChapters(event,right_items.getText()); 
				}
        		
        	});
        	centerRight.getChildren().add(right_items);
        }
        HBox center = new HBox(30,centerLeft,centerMiddle,centerRight);
        center.setAlignment(Pos.CENTER);
        center.setId("centerBody");
        bp.setCenter(center);
        BorderPane.setMargin(center, new Insets(30,50,10,50)); 
        
        Scene scene = new Scene(bp,800,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        topic_primaryStage.setScene(scene);
        topic_primaryStage.setTitle("All Contents");
        topic_primaryStage.setScene(scene);
//        topic_primaryStage.setMaximized(true);
        topic_primaryStage.show();
    }	
    
    @FXML
	private void openChapters(Event event, String text) 
    {
		((Node)event.getSource()).getScene().getWindow().hide(); 
		topic_content_primaryStage = new Stage();
		
		
		BorderPane pane = new BorderPane();
        
		JFXButton back = new JFXButton("Back");
		back.setId("back_button");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) 
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				topic_primaryStage.show();
			}
		});
		
		JFXButton exit = new JFXButton("Exit");
		exit.setId("exit_button");
		
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) 
			{
				System.exit(0);	
			}
		});
		
		
		
        Label topLabel = new Label(text);
        topLabel.setId("index_topLabel");
        HBox top = new HBox(70,back,topLabel,exit);
        top.setAlignment(Pos.CENTER);
        pane.setTop(top);
        
        
        
        Label bottomLabel = new Label("Choose one topic and get started");
        bottomLabel.setId("bottomLabel");
        HBox bottom = new HBox(bottomLabel);
        bottom.setAlignment(Pos.CENTER);
        pane.setBottom(bottom);
        
        
        
        
        VBox center_left = new VBox(10);
        center_left.setId("content_center_left");
        center_left.setAlignment(Pos.CENTER);
        int i = 0;
        ArrayList<String> temp_content = new ArrayList<>();
        ArrayList<String> temp_url = new ArrayList<>();
        for(Map.Entry me:temp_hm.entrySet())
        {
        	temp_content.add((String) me.getValue());
        	temp_url.add("https://www.tutorialspoint.com"+(String)me.getKey());
        }
        
//        System.out.println(temp_content.get(2));
//        System.out.println(temp_url.get(2));
        
        int length = temp_content.size();
        for(;i<length/3;i++)
        {
            String button_text = temp_content.get(i);
            int url_index = i;
        	JFXButton left_items = new JFXButton(button_text);
        	left_items.setId("left_items");
        	left_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(left_items.getText());
					int temp=0;
//					System.out.println(url_index);
					try {
						openChapterContent(event,button_text,temp_url,url_index);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        	});
        	center_left.getChildren().add(left_items);
        }
        
        
        

        VBox center_middle = new VBox(10);
        center_middle.setId("content_center_middle");
        center_middle.setAlignment(Pos.CENTER);
        for(;i< 2*(length/3);i++)
        {
        	String button_text = temp_content.get(i);
        	int url_index = i;
        	JFXButton middle_items = new JFXButton(button_text);
        	middle_items.setId("right_items");
        	middle_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(middle_items.getText());
					int temp=0;
					try {
						openChapterContent(event,button_text,temp_url,url_index);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
        		
        	});
        	center_middle.getChildren().add(middle_items);
        }
        
        
        
        
        VBox center_right = new VBox(10);
        center_right.setId("content_center_right");
        center_right.setAlignment(Pos.CENTER);
        for(;i< length;i++)
        {
        	String button_text = temp_content.get(i);
        	int url_index = i;
        	JFXButton right_items = new JFXButton(button_text);
        	right_items.setId("right_items");
        	right_items.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) 
				{
					int index = ScrapContents.h4.indexOf(right_items.getText());
					int temp=0;
					try {
						openChapterContent(event,button_text,temp_url,url_index);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
        		
        	});
        	center_right.getChildren().add(right_items);
        }
        
        
        
        
        ScrollPane center_pane = new ScrollPane();
        center_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        HBox center = new HBox(60,center_left,center_middle,center_right);
        HBox.setMargin(center_left, new Insets(40,10,40,10));
        HBox.setMargin(center_middle, new Insets(40,10,40,10));
        HBox.setMargin(center_right, new Insets(40,10,40,10));
        center.setId("center_scroll");
        center.prefWidthProperty().bind(center_pane.widthProperty());
        center.prefHeightProperty().bind(center_pane.heightProperty());
        center.setAlignment(Pos.CENTER);
	
        center_pane.setContent(center);
		pane.setCenter(center_pane);
        
		Scene scene  = new Scene(pane,850,600);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		topic_content_primaryStage.setTitle("TutorialsPoint - Contents");
		topic_content_primaryStage.setScene(scene);
//		primaryStage.setMaximized(true);
		topic_content_primaryStage.show();
		
	}
	public void openChapterContent(Event event, String text, ArrayList<String> temp_url, int url_index) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		JFXButton back = new JFXButton("Back");
		back.setId("back_button");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) 
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				topic_content_primaryStage.show();
			}
		});
		
		JFXButton exit = new JFXButton("Exit");
		exit.setId("exit_button");
		
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) 
			{
				System.exit(0);	
			}
		});
		
		JFXButton reload = new JFXButton("Relaod");
		reload.setId("back_button");
		
		JFXComboBox all_contents = new JFXComboBox();
		 
        Label topLabel = new Label(text);
        topLabel.setId("index_topLabel");
        HBox top = new HBox(30,back,all_contents,topLabel,reload,exit);
        top.setAlignment(Pos.CENTER);
        pane.setTop(top);
        
//        Label l = new Label(Welcome.temp_url.get(url_index));
//        System.out.println(temp_url.get(url_index));
        HashMap<String, String> menu = ScrapContents.getEndContent(temp_url.get(url_index));
    	ArrayList<String> menu_urls = new ArrayList<String>();
    	ArrayList<String> menu_url_text = new ArrayList<String>();
    	for(Map.Entry<String,String> e:menu.entrySet())
    	{
    		menu_urls.add(e.getKey());
    		menu_url_text.add(e.getValue());
    	}
        all_contents.getSelectionModel().select(menu_url_text.get(1));
//        all_contents.setEditable(true);
		for(String items:menu_url_text)
		{
			all_contents.getItems().add(items);
		}
        
        WebView web = new WebView();
        WebEngine webengine = web.getEngine();
        webengine.loadContent("<link rel=\"stylesheet\" href=\"https://www.tutorialspoint.com/theme/css/style-min.css\">\r\n" + 
        		""+ScrapContents.content_html);
		reload.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) 
			{
				webengine.reload();
			}
		});
		
        all_contents.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) 
			{
				try 
				{
					ScrapContents.getEndContent("https://www.tutorialspoint.com"+menu_urls.get(menu_url_text.indexOf(all_contents.getValue())));
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
		        webengine.loadContent("<link rel=\"stylesheet\" href=\"https://www.tutorialspoint.com/theme/css/style-min.css\">\r\n" + 
		        		""+ScrapContents.content_html);
			}
		});
		
		VBox content_box = new VBox(10,web);
		content_box.setAlignment(Pos.CENTER);
		VBox.setMargin(web, new Insets(20,50,20,50));
        pane.setCenter(content_box);
		
		
		Scene scene  = new Scene(pane,850,600);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.setTitle("TutorialsPoint - Contents");
		primaryStage.setScene(scene);
//		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	
}
