// TomasuloSimulator.java (Main Entry Point)
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class TomasuloSimulator extends Application {

    private Simulator simulator;

    @Override
    public void start(Stage primaryStage) {
        simulator = new Simulator();
        
        // Create GUI elements
        TableView<ReservationStation> rsTable = createReservationStationsTable();
        TableView<RegisterFile> registerFileTable = createRegisterFileTable();

        Button startButton = new Button("Start Simulation");
        startButton.setOnAction(e -> simulator.startSimulation());

        VBox root = new VBox();
        root.getChildren().addAll(rsTable, registerFileTable, startButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Tomasulo Algorithm Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<ReservationStation> createReservationStationsTable() {
        TableView<ReservationStation> table = new TableView<>();

        TableColumn<ReservationStation, String> opCol = new TableColumn<>("Operation");
        TableColumn<ReservationStation, String> statusCol = new TableColumn<>("Status");

        opCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().op));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status));

        table.getColumns().addAll(opCol, statusCol);
        return table;
    }

    private TableView<RegisterFile> createRegisterFileTable() {
        TableView<RegisterFile> table = new TableView<>();

        // Create columns and add data for registers (details omitted for brevity)
        return table;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
