package main.java;

import java.util.List;

public class Move {
	private List<Coordinate> path;


	//default constructor and setter for jackson
	public Move() {
	}

	public void setPath(List<Coordinate> path) {
		this.path = path;
	}

	public Move(List<Coordinate> path) {
		this.path = path;
	}

	private double getDistanceBetweenCoordinates(Coordinate a, Coordinate b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}

	public Coordinate getCoordinateByDistance(double distance) {
		double length = 0;
		double currentLenght = 0;

		Coordinate a = null;
		Coordinate b = null;
		for (int i = 0; i < path.size() - 1; i++) {
			a = path.get(i);
			b = path.get(i + 1);
			currentLenght = getDistanceBetweenCoordinates(a, b);
			if (length + currentLenght >= distance) {
				break;
			}
			length += currentLenght;
		}

		if (a == null || b == null)
			return null;

		double driven = (distance - length) / currentLenght;
		return new Coordinate(a.getX() + (b.getX() - a.getX()) * driven, a.getY() + (b.getY() - a.getY()) * driven);
	}

	public double getPathSize() {
		double size = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			size += getDistanceBetweenCoordinates(path.get(i), path.get(i + 1));
		}
		return size;
	}

	@Override
	public String toString() {
		return "Move{" +
				"path=" + path +
				'}';
	}
}
