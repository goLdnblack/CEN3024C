package sdlc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.util.*;
import java.io.IOException;

public class AppGui extends Application
{
	private static Scene wordScene;
	private Label topWords = new Label();
	private int x = 300;
	private int y = 450;
	
	public Scene setTextPage(Stage stage, GridPane grid, Scene scene)
	{
		Group group = new Group();
		
		Scene tscene = new Scene(group, x, y);

		GridPane newGrid = new GridPane();
		newGrid.setPadding(new Insets(30, 30, 30, 30));
		newGrid.setVgap(5);
		newGrid.setHgap(5);
		
		Label w = new Label();
		GridPane.setConstraints(w, 0, 0);
		GridPane.setColumnSpan(w, 1);
		w.setText("Top 20 Words and their frequency");
		w.setFont(new Font("Arial", 12));
		newGrid.getChildren().add(w);
		
		topWords.setFont(new Font("Arial", 12));
		GridPane.setConstraints(topWords, 0, 1);
		GridPane.setColumnSpan(topWords, 2);
		newGrid.getChildren().add(topWords);
		
		Button mainPage = new Button("Return to previous page");
		mainPage.setFont(new Font("Arial", 12));
		GridPane.setConstraints(mainPage, 0, 10);
		GridPane.setColumnSpan(mainPage, 1);
		newGrid.getChildren().add(mainPage);
			
		((Group)tscene.getRoot()).getChildren().add(newGrid);
		
		mainPage.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e) 
			{
				stage.setScene(scene);
			}
		});
		
		stage.setScene(tscene);
			
		stage.show();
		
		return tscene;
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		GridPane grid = new GridPane();
		final Label label = new Label();
		final TextField web = new TextField();
		Button clear = new Button("Clear");
		Button submit = new Button("Display 20 most frequent words");
		final Label webl = new Label();
		
		label.setFont(new Font("Arial", 12));
		webl.setFont(new Font("Arial", 12));
		clear.setFont(new Font("Arial", 12));
		submit.setFont(new Font("Arial", 12));
		
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		web.setPromptText("Enter text website to analyze");
		GridPane.setHalignment(web, HPos.CENTER);
		web.setPrefColumnCount(10);
		web.getText();
		GridPane.setConstraints(web, 3, 1);
		grid.getChildren().add(web);
		
		GridPane.setConstraints(submit, 3, 2);
		grid.getChildren().add(submit);

		GridPane.setConstraints(clear, 3, 3);
		GridPane.setHalignment(clear, HPos.CENTER);
		grid.getChildren().add(clear);
		
		GridPane.setConstraints(label, 2, 6);
		GridPane.setColumnSpan(label, 3);
		grid.getChildren().add(label);

		webl.setText("Enter website url:");
		GridPane.setConstraints(webl, 3, 0);
		GridPane.setColumnSpan(webl, 1);
		grid.getChildren().add(webl);
		
		StringBuilder sb = new StringBuilder();
		
		Scene scene = new Scene(grid, x, y);
		
		submit.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				if ((web.getText() != null && !web.getText().isEmpty())) 
				{
					if (wordScene == null)
					{
						
						try
						{
							int counter = 0;
							
							Map<String, Integer> sortedWordFreq = TextAnalyzer.analyzeText(web.getText());
						
							for (Map.Entry<String, Integer> set : sortedWordFreq.entrySet())
							{
								sb.append(Integer.toString(++counter));
								sb.append(". ");
								sb.append(set.getKey());
								sb.append(" ");
								sb.append(Integer.toString(set.getValue()));
								sb.append("\n");
								
								// Only for top 20 words
								if (counter == 20)
									break;
							}
						}
						catch (IOException error)
						{
							
						}
						
						topWords.setText(sb.toString());
						
						// Reset StringBuilder
						sb.setLength(0);
						
						wordScene = setTextPage(stage, grid, scene);
					}
					else
					{
						stage.setScene(wordScene);
					}
					
				} else {
					label.setText("You have not entered a website.");
				}
			}
		});
			 

		clear.setOnAction(new EventHandler<ActionEvent>() {

		@Override
			public void handle(ActionEvent e) {
				web.clear();
				label.setText(null);
			}
		});

		StackPane root = new StackPane();

		stage.setTitle("Text Analyzer");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
