package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
	@FXML
	private Pane map;

	@FXML
	private Slider zoomSlider, speedSlider;

	@FXML
	private void zoomMinusClick(ActionEvent event) {
		event.consume();
		if (zoomSlider.getValue() == zoomSlider.getMin())
			return;

		zoomSlider.decrement();
		map.setScaleX(zoomSlider.getValue() / 100);
		map.setScaleY(zoomSlider.getValue() / 100);
		map.layout();
	}

	@FXML
	private void zoomPlusClick(ActionEvent event) {
		event.consume();
		if (zoomSlider.getValue() == zoomSlider.getMax())
			return;

		zoomSlider.increment();
		map.setScaleX(zoomSlider.getValue() / 100);
		map.setScaleY(zoomSlider.getValue() / 100);
		map.layout();
	}

	@FXML
	private void speedMinusClick(ActionEvent event) {
		event.consume();
		if (speedSlider.getValue() == speedSlider.getMin())
			return;

		speedSlider.decrement();
		timer.cancel();
		timing((float) (speedSlider.getValue()) / 10);
	}

	@FXML
	private void speedPlusClick(ActionEvent event) {
		event.consume();
		if (speedSlider.getValue() == speedSlider.getMax())
			return;

		speedSlider.increment();
		timer.cancel();
		timing((float) (speedSlider.getValue()) / 10);
	}

	@FXML
	private void speedUpdate(MouseEvent event) {
		event.consume();
		timer.cancel();
		timing((float) (speedSlider.getValue()) / 10);
	}

	@FXML
	private void zoomUpdate(MouseEvent event) {
		event.consume();
		map.setScaleX(zoomSlider.getValue() / 100);
		map.setScaleY(zoomSlider.getValue() / 100);
		map.layout();
	}

	@FXML
	private void onScroll(ScrollEvent event) {
		event.consume();
		double zoom = 1;
		if (event.getDeltaY() == 40) {
			zoom = 1.1;
			if (zoomSlider.getValue() == zoomSlider.getMax())
				return;
			zoomSlider.increment();
		} else if (event.getDeltaY() == -40) {
			zoom = 0.9;
			if (zoomSlider.getValue() == zoomSlider.getMin())
				return;
			zoomSlider.decrement();
		} else {
		    return;
        }
		map.setScaleX(zoomSlider.getValue() / 100);
		map.setScaleY(zoomSlider.getValue() / 100);
		map.layout();
	}

	private List<Drawable> elements = new ArrayList<>();
	private List<UpdateTime> updates = new ArrayList<>();

	private Timer timer;
	private LocalTime time = LocalTime.now();

	public void setElements(List<Drawable> elements) {
		this.elements = elements;
		for (Drawable drawable : elements) {
			map.getChildren().addAll(drawable.getGui());
			if (drawable instanceof UpdateTime) {
				updates.add((UpdateTime) drawable);
			}
		}

	}

	public void timing(double scale) {
		timer = new Timer(false);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				time.plusSeconds(1);
				for (UpdateTime update : updates) {
					update.update(time);
				}
			}
		}, 0, (long) (1000 / scale));

	}
}
