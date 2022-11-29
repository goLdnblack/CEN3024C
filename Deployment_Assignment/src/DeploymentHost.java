// Jesus Sanchez
// CEN 3024
// Prof Walauskis
// 11.28.2022
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DeploymentHost extends Application
{
    // port to listen
    private static int port = 9567;

    public static void main(String[] args) throws Exception
    {
        //AppServer();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        TextArea ta = new TextArea();

        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server application");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() ->
        {
            try
            {
                ServerSocket serverSocket = new ServerSocket(port);
                Platform.runLater(() ->
                    ta.appendText("Server running.\n"));
                    
                Socket socket = serverSocket.accept();

                DataInputStream clientInput = new DataInputStream(socket.getInputStream());
                DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());

                while (true)
                {
                    /* 
                    double r = clientInput.readDouble();

                    double area = r * r * Math.PI;

                    serverOutput.writeDouble(area);

                    Platform.runLater( () ->
                    {
                        ta.appendText("Radius from client: " + r + "\n");
                        ta.appendText("Area is " + String.format("%.2f", area) + "\n");
                    });
                    */

                    int num = clientInput.readInt();

                    boolean primeFlag = false;
                    int even = 2;

                    while (even <= num / 2)
                    {
                        if (num % even == 0)
                        {
                            primeFlag = true;
                            break;
                        }

                        ++even;
                    }

                    /* 
                    for (int x = 2; x <= num; ++x)
                    {
                        if (num % x == 0)
                        {
                            primeNum = true;
                            break;
                        }
                    }
                    */

                    if (!primeFlag)
                    {
                        serverOutput.writeUTF("The number " + num + " is a prime number.\n");
                    }
                    else
                    {
                        serverOutput.writeUTF("The number " + num + " is not a prime number.\n");
                    }

                    Platform.runLater( () ->
                    {
                        ta.appendText("Number from client " + num + "\n");
                    });

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();
    }
}
