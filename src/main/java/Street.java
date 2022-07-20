package main.java;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class Street implements Drawable {
	private Coordinate start;
	private Coordinate end;
	private String name;

	public Street(String name, Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
		this.name = name;
	}

	@Override
	@JsonIgnore
	public List<Shape> getGui() {
		return Arrays.asList(
				new Text(start.getX() + (Math.abs(start.getX() - end.getX()) / 2), start.getY() + Math.abs(start.getY() - end.getY()) / 2, name),
				new Line(start.getX(), start.getY(), end.getX(), end.getY())
		);
	}

	@Override
	public String toString() {
		return "Street{" +
				"start=" + start +
				", end=" + end +
				", name='" + name + '\'' +
				'}';
	}
}
