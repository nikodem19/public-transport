package main.java;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private int number;
	private List<Street> lineStreets;

	public Line(int number) {
		this.number = number;
		lineStreets = new ArrayList<>();
	}

	public void addStreet(Street street) {
		lineStreets.add(street);
	}
}
