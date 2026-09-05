package potato.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import potato.Potato;

/**
 * Controller for the main GUI layout.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Potato potato;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image potatoImage = new Image(this.getClass().getResourceAsStream("/images/DaPotato.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPotato(Potato p) {
        potato = p;
        String welcome = potato.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getPotatoDialog(welcome, potatoImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = potato.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPotatoDialog(response, potatoImage)
        );
        userInput.clear();
    }
}