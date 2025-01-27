import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
  Dorm and Meal Plan Calculator
*/

public class Main extends Application
{
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      // Build the Dorm ComboBox.
      ComboBox<String> dormComboBox = new ComboBox<>();
      dormComboBox.getItems().addAll("Allen Hall", "Pike Hall", "Farthing Hall", "University Suites");

      // Label to prompt the user to select a dorm
      Label dormLabel = new Label("Select a Dorm:");

      // Build the Meal Plan ComboBox.
      ComboBox<String> mealComboBox = new ComboBox<>();
      mealComboBox.getItems().addAll("7 meals per week", "14 meals per week", "Unlimited meals");

      // Label to prompt the user to select a meal plan
      Label mealLabel = new Label("Select a Meal Plan:");

      // Create the output label for total cost.
      Label costOutputLabel = new Label("Total Cost: $0.00");

      // Create the Calculate button.
      Button calcButton = new Button("Calculate Total");

      // Register event handler for the button
      calcButton.setOnAction(event ->
      {
         double dormCharges = 0.0;
         double mealCharges = 0.0;
         double totalCharges = 0.0;

         if (dormComboBox.getValue() != null)
         {
            String dorm = dormComboBox.getValue();
            if (dorm.equals("Allen Hall"))
               dormCharges = 1800.0;
            else if (dorm.equals("Pike Hall"))
               dormCharges = 2200.0;
            else if (dorm.equals("Farthing Hall"))
               dormCharges = 2800.0;
            else if (dorm.equals("University Suites"))
               dormCharges = 3000.0;
            else
               dormCharges = 0.0;
         }

         if (mealComboBox.getValue() != null)
         {

            //code for mealComboBox
            String mealPlan = mealComboBox.getValue();
            if (mealPlan.equals("7 meals per week"))
               mealCharges = 600.0;
            else if (mealPlan.equals("14 meals per week"))
               mealCharges = 1100.0;
            else if (mealPlan.equals("Unlimited meals"))
               mealCharges = 1800.0;
            else
               mealCharges = 0.0;               
         }

         // Get the total charges
         totalCharges = dormCharges + mealCharges;

         // Display the charges.
         costOutputLabel.setText(String.format("Total Charges: %,.2f", totalCharges));
      });

      // Create the HBox for the dorm slection.
      HBox dormHBox = new HBox(10, dormLabel, dormComboBox);
      dormHBox.setAlignment(Pos.CENTER);

      //Create HBox for meal plan selection
      HBox mealHBox = new HBox(10, mealLabel, mealComboBox);
      mealHBox.setAlignment(Pos.CENTER);

      //Create HBox for cost output 
      HBox costHBox = new HBox(10, costOutputLabel);
      costHBox.setAlignment(Pos.CENTER);
      
      // Put everything into a VBox
      VBox mainVBox = new VBox(10, dormHBox, mealHBox, calcButton, costHBox);
      mainVBox.setAlignment(Pos.CENTER);
      mainVBox.setPadding(new Insets(10));

      // Add the main VBox to a scene.
      Scene scene = new Scene(mainVBox, 400, 200);

      // Set the scene to the stage aand display it.
      primaryStage.setScene(scene);
      primaryStage.setTitle("Dorm and Meal Plan Calculator");
      primaryStage.show();
   }
}