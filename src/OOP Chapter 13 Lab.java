import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.collections.ObservableList;

/**
   Smartphone Packages
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
      // Data plan radio buttons
      RadioButton gig8RadioButton = new RadioButton("8 Gigabytes per month");
      RadioButton gig16RadioButton = new RadioButton("16 Gigabytes per month");
      RadioButton gig20RadioButton = new RadioButton("20 Gigabytes per month");
      gig8RadioButton.setSelected(true);
      ToggleGroup dataGroup = new ToggleGroup();
      gig8RadioButton.setToggleGroup(dataGroup);
      gig16RadioButton.setToggleGroup(dataGroup);
      gig20RadioButton.setToggleGroup(dataGroup);
      Label dataPrompt = new Label("Select a Data Plan");
      Label dataDescriptor = new Label("Data Plan Cost:");
      Label dataCostLabel = new Label("$45 per month");
      HBox dataCostHBox = new HBox(10, dataDescriptor, dataCostLabel);  
      VBox dataVBox = new VBox(10, dataPrompt, gig8RadioButton, gig16RadioButton,
                                   gig20RadioButton, dataCostHBox);

      // Register event handlers for the data plan radio buttons
      gig8RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$45 per month");
      });

      gig16RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$65 per month");
      });

      gig20RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$99 per month");
      });

      // Phone radio buttons
      RadioButton model100Button = new RadioButton("Model 100");
      RadioButton model110Button = new RadioButton("Model 110");
      RadioButton model200Button = new RadioButton("Model 200");
      model100Button.setSelected(true);
      TogglesGroup phoneGroup = new ToggleGroup();
      model100Button.setToggleGroup(phoneGroup);
      model110Button.setToggleGroup(phoneGroup);
      model200Button.setToggleGroup(phoneGroup);
      Label phonePrompt = new Label("Select a Phone Model");
      Label phoneDescriptor = new Label("Phone Cost;");
      Label phoneCostLabel = new Label ("$299.95");
      HBox phoneCostHBox = new HBox(10, phoneDescriptor, phoneCostLabel);
      VBox phoneVBox = new VBox(10, phonePrompt, model100Button, model110Button, model200Button, phoneCostHBox);
      
      // Register event handlers for the phone radio buttons
      model100Button.setOnAction(event -> {
         phoneCostLabel.setText("$299.95");
      });
      model110Button.setOnAction(event -> {
         phoneCostLabel.setText("$399.95");
      });
      model200Button.setOnAction(event -> {
         phoneCostLabel.setText("$499.95");
      });

      // Options
      CheckBox insuranceCheckBox = new CheckBox("Phone Replacement Insurance ($5.00 per month)");
      CheckBox hotspotCheckBox = new CheckBox("WiFi Hotspot Capabilty ($10.00 per month)");
      Label optionsDescriptor = new Label("Options Cost:");
      Label optionsCostLabel = new Label("$0");
      HBox optionsCostHBox = new HBox(10, optionsDescriptor, optionsCostLabel);
      VBox optionsVBox = new VBox(10, insuranceCheckBox, hotspotCheckBox, optionsCostHBox);

      // Register event handlers for the check boxes
      insuranceCheckBox.setOnAction(event -> {
         updateOptionsCost(optionsCostLabel, insuranceCheckBox, hotspotCheckBox);
      });

      hotspotCheckBox.setOnAction(event -> {
         updateOptionsCost(optionsCostLabel, insuranceCheckBox, hotspotCheckBox);
      });

      //Total Cost Label 
      Label totalDescriptor = new Label("Total Cost:");
      Label totalCostLabel = new Label("$0.00");
      HBox totalCostHBox = new HBox(10, totalDescriptor, totalCostLabel);

      //Calculate Button
      Button calculateButton = new Button("Calculate Total Cost");
      calculateButton.setOnAction(event -> {
         double dataCost = getDatacost(dataCostLabel.getText());
         double phoneCost = getPhoneCost(phoneCostLabel.getText());
         double optionsCost = getOptionsCost(optionsCostLabel.getText());
         double totalCost = (phoneCost * 1.06) + dataCost + optionsCost;
         totalCostLabel.setText(String.format("$%.2f", totalCost));
      });
      
      // Put everything into a VBox
      VBox mainVBox = new VBox(10, dataVBox, phoneVBox, optionsVBox, totalCostHBox, calculateButton);
      mainVBox.setAlignment(Pos.CENTER);
      mainVBox.setPadding(new Insets(10));

      // Add the main VBox to a scene.
      Scene scene = new Scene(mainVBox);

      // Set the scene to the stage aand display it.
      primaryStage.setTitle("Cell Phone Package Calculator");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   private void updateOptionsCost(Label optionsCostLabel, CheckBox insuranceCheckBox, CheckBox hotspotCheckBox) {
      double optionsCost = 0.0;
      if (insuranceCheckBox.isSelected())
         optionsCost += 5.0;
      if (hotspotCheckBox.isSelected())
         optionsCost += 10.0;
      optionsCostLabel.setText(String.format("$%.2f", optionsCost));
   }

   private double getDataCost(String dataCostText) {
      return Double.parseDouble(dataCostText.replace("$","").replace(" per month", ""));
   }
   private double getPhoneCost(String phoneCostText) {
      return Double.parseDouble(phoneCostText.replace("$", ""));
   }
   private double getOptionsCost(String optionsCostText) {
      return Double.parseDouble(optionsCostText.replace("$", ""));
   }
}