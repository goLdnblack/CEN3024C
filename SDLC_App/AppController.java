//package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import java.util.*;
import java.io.IOException;

public class AppController
{
	
	private StringBuilder sb;

	private Stage stage;
	
	private Scene scene;
	
	private Parent root;

	@FXML
	private Label analyzeTitle;

	@FXML
	private Button analyzeText;
	
	@FXML
	private Label mainTitle;

	@FXML
	private TextField webInput;

	@FXML
	private Text topWords;

	@FXML
	private AnchorPane dynamicPane;
	
	@FXML
	private BarChart dataChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	private String website;

	public void calcFreq()
	{
		//ArrayList<XYChart> data = new ArrayList<>();
		XYChart.Series<String, Integer> mainSeries = new XYChart.Series();
		
		
		dataChart.getData().clear();
		
		website = webInput.getText();
		
		//StringBuilder sb = new StringBuilder();
		sb = new StringBuilder();
		int counter = 0;
		try
		{
			Map<String, Integer> sortedWordFreq = 
				TextAnalyzer.analyzeText(website);
				//TextAnalyzer.analyzeText(webInput.getText());

			for (Map.Entry<String, Integer> set : sortedWordFreq.entrySet())
			{
				sb.append(Integer.toString(++counter));
				sb.append(". ");
				sb.append(set.getKey());
				sb.append(" ");
				sb.append(Integer.toString(set.getValue()));
				sb.append("\n");
				
				
				// Add to chart
				mainSeries.getData().add(new XYChart.Data(set.getKey()
				+ " " + Integer.toString(set.getValue()), set.getValue()));
				
				// Count for 20 words
				if (counter == 20)
					break;
			}
			
			dataChart.getData().addAll(mainSeries);

		}
		catch (IOException e)
		{
			// e.printStackTrace();
			// Error message printed from
			// TextAnalyzer
		}
	}
	
	@FXML
	private void initialize() throws Exception
	{
		
	}
}
