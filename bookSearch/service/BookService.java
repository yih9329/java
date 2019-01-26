package bookSearch.service;

import java.util.List;

import bookSearch.dao.BookDAO;
import bookSearch.dao.MyBookDAO;
import bookSearch.dto.BookDTO;
public class BookService {
	
	public List<BookDTO> findByKeword(String keyword) {
	
		MyBookDAO connectionMaker = new MyBookDAO();
		BookDAO dao = new BookDAO(connectionMaker);
		return dao.select(keyword);
	}
	
	// Transaction 
	public boolean deleteByIsbn(String text) {
		
		MyBookDAO connectionMaker = new MyBookDAO();
		BookDAO dao = new BookDAO(connectionMaker);
		boolean result = false;
		
		int count = dao.delete(text);
		if(count==1)
			result = true;
		return result;
	}

}
