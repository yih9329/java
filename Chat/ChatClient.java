package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam03_ChatClient extends Application {
	private TextArea textarea;
	private BufferedReader br;
	private PrintWriter pr;
	private Socket socket;
	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);

		textarea = new TextArea();
		textarea.setEditable(false);
		root.setCenter(textarea);

		FlowPane bottom = new FlowPane();
		bottom.setPadding(new Insets(20, 20, 20, 20));
		bottom.setHgap(10); 

		Button startBtn = new Button("서버 접속");
		startBtn.setPrefSize(100, 30);
		startBtn.setOnAction(event -> {
			try {
				socket = new Socket("10.16.138.248", 5555);
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pr = new PrintWriter(socket.getOutputStream());
				
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						while(true) {
							try {
								String msg = br.readLine();
								printMsg(msg);
							} catch (Exception e) {
								break;
							}
						}
					}
				};
				executorService.execute(runnable);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		TextField idField = new TextField();
		idField.setPrefSize(100, 30);
				TextField msgField = new TextField();
		msgField.setPrefSize(200, 30);
		msgField.setOnAction(event -> {
			String id = idField.getText();
			String msg = id + ">" + msgField.getText();
			pr.println(msg);
			pr.flush();
			msgField.clear();
		}); 

		bottom.getChildren().add(startBtn);
		bottom.getChildren().add(idField);
		bottom.getChildren().add(msgField);
		root.setBottom(bottom);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		asdfasfdafdsafdasf
				asdfsafasdfdas
				asdfsdafsfsdaf
						asdfadsfsdafs
		launch();
	}

}
