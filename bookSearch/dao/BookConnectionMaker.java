package bookSearch.dao;

import java.sql.Connection;

public interface BookConnectionMaker {
		public abstract  Connection getConnection();
}
