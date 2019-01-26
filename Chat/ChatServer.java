package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam03_ChatServer extends Application {
	private TextArea textarea;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private ServerSocket serverSocket;
	private SharedObject sharedObject = new SharedObject();
	
	class SharedObject { 
		
		List<ClientRunnable> list = new ArrayList<ClientRunnable>();
		
		public void broadcast(String msg) {
			for(ClientRunnable client : list) {
				client.pw.println(msg);
				client.pw.flush();
			}
		}
	}
	
	class ClientRunnable implements Runnable {

		Socket socket;
		PrintWriter pw;
		BufferedReader br;
		SharedObject sharedObject;
		
		public ClientRunnable(Socket socket, SharedObject sharedObject) {
			this.socket = socket;
			this.sharedObject = sharedObject;
			try {
				this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.pw = new PrintWriter(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// 클라이언트가 보내준 데이터를 받아서 다시 돌려준다.
			while(true) {
				try {
					String msg = br.readLine();
					sharedObject.broadcast(msg);
					// 클라이언트의 입력을 받아서 공용객체의 method를 통해 모든클라이언트에게 데이터 전달.
				} catch (IOException e) {
					printMsg("클라이언트 연결 종료");
					break;
				}
				
			}
		}

	}

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
		bottom.setHgap(10); // 버튼간 간격

		Button startBtn = new Button("서버 시작");
		startBtn.setPrefSize(100, 30);
		startBtn.setOnAction(event -> {
			printMsg("[서버 시작]");
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						serverSocket = new ServerSocket(5555);
						while (true) {
							printMsg("클라이언트 접속 대기중");
							Socket socket = serverSocket.accept();
							ClientRunnable runnable = new ClientRunnable(socket, sharedObject);
							synchronized (this) {
								sharedObject.list.add(runnable);
							}
							executorService.execute(runnable);
							printMsg("클라이언트 접속 - 현재 클라이언트 수는 : " + sharedObject.list.size());
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};
			executorService.execute(runnable);

		});

		Button stopBtn = new Button("서버 중지");
		stopBtn.setPrefSize(100, 30);
		stopBtn.setOnAction(event -> {

		});

		bottom.getChildren().add(startBtn); 
		bottom.getChildren().add(stopBtn);

		root.setBottom(bottom);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}
