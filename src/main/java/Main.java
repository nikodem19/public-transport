package main.java;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import com.sun.java.accessibility.util.AccessibilityListenerList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout.fxml"));
		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Public transport");
		primaryStage.setScene(scene);
		primaryStage.show();

		Controller controller = loader.getController();
		List<Drawable> elements = new ArrayList<>();

		try {
			// create object mapper instance
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new ParanamerModule());
			// append JSON arrays to list of elements
			elements.addAll(Arrays.asList(mapper.readValue(Paths.get("data/vehicles.json").toFile(), Vehicle[].class)));
			elements.addAll(Arrays.asList(mapper.readValue(Paths.get("data/streets.json").toFile(), Street[].class)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		controller.setElements(elements);
		controller.timing(1);
	}
}
