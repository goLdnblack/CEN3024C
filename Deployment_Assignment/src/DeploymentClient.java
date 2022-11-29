// Jesus Sanchez
// CEN 3024
// Prof Walauskis
// 11.28.2022
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DeploymentClient extends Application
{
	DataOutputStream sendData = null;
	DataInputStream receiveData = null;

	public static void main(String[] args) throws Exception
	{
		//AppClient();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		InetAddress host = InetAddress.getLocalHost();

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setStyle("-fx-border-color: blue");
		pane.setLeft(new Label("Enter number: "));

		TextField tf = new TextField();
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		pane.setCenter(tf);

		BorderPane mainPane = new BorderPane();
		TextArea ta = new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(pane);

		Scene scene = new Scene(mainPane, 450, 200);
		primaryStage.setTitle("Client Application");
		primaryStage.setScene(scene);
		primaryStage.show();

		tf.setOnAction(e ->
		{
			try 
			{
				//double r = Double.parseDouble(tf.getText().trim());
				int num = Integer.parseInt(tf.getText().trim());

				sendData.writeInt(num);
				sendData.flush();

				String isPrime = receiveData.readUTF();

				ta.appendText(isPrime);
			}
			catch (IOException err)
			{
				System.err.println(err);
			}
		});

		try
		{
			Socket socket = new Socket(host.getHostName(), 9567);
			receiveData = new DataInputStream(socket.getInputStream());

			sendData = new DataOutputStream(socket.getOutputStream());

		}
		catch (IOException e)
		{
			ta.appendText(e.toString() + '\n');
		}
	}	
}
