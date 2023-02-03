

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class F1 extends Application {
   public void start(Stage stage) {
    
    TextField time = new TextField("xxx");
      // Creating a Label
      Label label = new Label("xxx");
     
      // Creating a graphic (image)
      Image img = new Image("https://cdn-icons-png.flaticon.com/512/1742/1742553.png");
      ImageView view = new ImageView(img);
      view.setFitHeight(80);
      view.setPreserveRatio(true);
      label.setGraphic(view);
     
      // Setting font to the label
      Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
      label.setFont(font);
     
      // Filling color to the label
      label.setTextFill(Color.BROWN);
     
      // Setting the position
      label.setTranslateX(150);
      label.setTranslateY(25);
      Group root = new Group();
      root.getChildren().add(label);
     
      // Setting the stage
      Scene scene = new Scene(root, 595, 200, Color.BEIGE);
      stage.setTitle("Label Example");
      stage.setScene(scene);
      stage.show();
   }
   
   public static void main(String args[]){
      launch(args);
   }
}

