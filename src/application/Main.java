package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent root  = FXMLLoader.load(getClass().getResource("Main.fxml")); 
			Scene scene = new Scene(root,650,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
//			primaryStage.getIcons().add(new Image("H:/MindFire/TutorialsPoint/Img/logo.png"));
			primaryStage.setTitle("Tutorials Point");
			primaryStage.show();
			
			ScrapContents.getContents();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
