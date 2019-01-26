package bookSearch;

import java.util.List;

import bookSearch.dto.BookDTO;
import bookSearch.service.BookService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();		
		root.setPrefSize(700, 500);		
		
		TextArea textarea = new TextArea();
		textarea.setEditable(false);		
		root.setCenter(textarea);
		
		FlowPane bottom = new FlowPane();	
		bottom.setPrefSize(700, 50);
		
		TextField textfield = new TextField();
		textfield.setPrefSize(300, 50);
		Button searchBtn = new Button("검색");
		searchBtn.setPrefSize(100, 50);
		
		searchBtn.setOnAction(t -> {
			BookService service = new BookService();
			List<BookDTO> list = service.findByKeword(textfield.getText());	
			Platform.runLater(()->{
				for(BookDTO dto : list) {
					textarea.appendText(dto.getBtitle() + " : " + dto.getBisbn() + "\n");
				}
			});
		});				 
		
		Button deleteBtn = new Button("삭제");
		deleteBtn.setPrefSize(100, 50);
		
		deleteBtn.setOnAction(t-> {
			BookService service = new BookService();
			boolean result = service.deleteByIsbn(textfield.getText());
			Platform.runLater(()->{
				textarea.clear();
				if(result)
					textarea.appendText("삭제성공\n");
				else
					textarea.appendText("삭제실패\n");
			});
		});
		
		bottom.getChildren().add(textfield);
		bottom.getChildren().add(searchBtn);
		bottom.getChildren().add(deleteBtn);
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);	
		primaryStage.setScene(scene);  
		primaryStage.setTitle("도서검색 프로그램"); 
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}

