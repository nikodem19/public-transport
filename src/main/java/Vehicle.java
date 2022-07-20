package main.java;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Drawable, UpdateTime {
	private Coordinate position;
	private double speed;
	private Move path;

	@JsonIgnore
	private List<Shape> gui;
	private double distance = 0;

	public Vehicle(Coordinate position, double speed, Move path) {
		this.position = position;
		this.speed = speed;
		this.path = path;
		gui = new ArrayList<>();
		gui.add(new Circle(position.getX(), position.getY(), 8, Color.BLUE));
	}

	private void moveGui(Coordinate coordinate) {
		for (Shape shape : gui) {
			shape.setTranslateX((coordinate.getX() - position.getX()) + shape.getTranslateX());
			shape.setTranslateY((coordinate.getY() - position.getY()) + shape.getTranslateY()); // check stream
		}
	}

	@Override
	public List<Shape> getGui() {
		return gui;
	}

	@Override
	public void update(LocalTime time) {
		distance += speed;
		if (distance > path.getPathSize())
			return;
		Coordinate coords = path.getCoordinateByDistance(distance);
		moveGui(coords);
		position = coords;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"position=" + position +
				", speed=" + speed +
				", path=" + path +
				'}';
	}
}
