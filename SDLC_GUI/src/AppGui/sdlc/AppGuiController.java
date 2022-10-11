package sdlc;

import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.text.Text;

public class AppGuiController
{
	@FXML
	private Text actiontarget;
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event)
	{
	actiontarget.setText("Pressed");
	}
}
