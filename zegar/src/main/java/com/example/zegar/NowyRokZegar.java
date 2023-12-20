package com.example.zegar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.time.LocalDateTime;


public class NowyRokZegar extends Application {

    private Label daysLabel;
    private Label hoursLabel;
    private Label minutesLabel;
    private Label secondsLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zegar do Nowego Roku");

        daysLabel = new Label();
        hoursLabel = new Label();
        minutesLabel = new Label();
        secondsLabel = new Label();

        updateTimer();

        VBox vBox = new VBox(
                new Label("Czas do Nowego Roku:"),
                daysLabel,
                hoursLabel,
                minutesLabel,
                secondsLabel
        );

        vBox.setSpacing(10);
        vBox.setStyle("-fx-alignment: center; -fx-font-size: 20;");

        Scene scene = new Scene(vBox, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

        startCountdown();
    }

    private void startCountdown() {
        Duration duration = Duration.seconds(1);
        Timeline timeline = new Timeline(new KeyFrame(duration, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateTimer();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateTimer() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newYear = LocalDateTime.of(now.getYear() + 1, 1, 1, 0, 0, 0);
        java.time.Duration duration = java.time.Duration.between(now, newYear);

        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        daysLabel.setText("Dni: " + days);
        hoursLabel.setText("Godziny: " + String.format("%02d", hours));
        minutesLabel.setText("Minuty: " + String.format("%02d",minutes));
        secondsLabel.setText("Sekundy: " + String.format("%02d",seconds));

        if (duration.isZero() || duration.isNegative()) {
            startCountdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
