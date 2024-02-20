package com.vaadin.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * This init listener is run once whenever the Vaadin context starts. As such,
 * it is a great place to create dummy data.
 * <p>
 * See the <code>application.properties</code> file for database connection
 * properties.
 */
@Service
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void serviceInit(ServiceInitEvent serviceInitEvent) {
		System.out.println("_________DB initiation has started____________");

		// Initializing tables in the database

		// First, remove if already exist
		initDBStructure();
		// insert data
		populateData();

		System.out.println("_________DB initiation has finished____________");
	}
	
	
	private void initDBStructure() {
		int length = table_exist.checkTableExistence("game");
		if(length == 0) {
			jdbcTemplate.execute("DROP TABLE IF EXISTS game;");
        	jdbcTemplate.execute("DROP TABLE IF EXISTS creator;");
			jdbcTemplate.execute("CREATE TABLE creator (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));");
        	jdbcTemplate.execute("CREATE TABLE game (id SERIAL PRIMARY KEY, title VARCHAR(255), gameId int, release_year INTEGER, wikilink VARCHAR(255), CONSTRAINT fk_game_creator FOREIGN KEY (gameId) REFERENCES creator(id));");
		}  
	}
	private void populateData() {
		int length = table_exist.checkTableExistence("game");
		if(length == 0) {
			jdbcTemplate.update("INSERT INTO creator (name) VALUES ('Shigeru Miyamoto');");
        	jdbcTemplate.update("INSERT INTO creator (name) VALUES ('Tim Cain');");

        	jdbcTemplate.update(
                "INSERT INTO game (title, gameId, release_year, wikilink) VALUES ('Legend of zelda', 1, 1986, 'https://en.wikipedia.org/wiki/The_Legend_of_Zelda')");

        	jdbcTemplate.update(
                "INSERT INTO game (title, gameId, release_year, wikilink) VALUES ('Fallout', 2, 1997, 'https://en.wikipedia.org/wiki/Fallout_(series)');");
        	jdbcTemplate.update(
                "INSERT INTO game (title, gameId, release_year, wikilink) VALUES ('Skyrim', 2, 2011, 'https://en.wikipedia.org/wiki/The_Elder_Scrolls_V:_Skyrim');");
		}
		

    }

}
