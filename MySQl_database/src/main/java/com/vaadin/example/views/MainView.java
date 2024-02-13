package com.vaadin.example.views;

import com.vaadin.flow.data.renderer.LitRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.example.ApplicationServiceInitListener;
import com.vaadin.example.data.entity.Movie;
import com.vaadin.example.data.service.MovieService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Key;

/**
 * A simple Vaadin View class that shows all Movies in a database.
 * <p>
 * See {@link MovieService} for details on the database, and
 * {@link ApplicationServiceInitListener} for adding more demo data.
 */
@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {
    private TextField name;
    private Button sayHello;

    public MainView(@Autowired MovieService movieService) {

        // Create and add header text
        add(new H3("Accessing my local mySQL database"));
        name = new TextField("Your name");

        // create Grid component
        final Grid<Movie> movies = new Grid<>(Movie.class);

        // fetch all movies from our Service
        movies.setItems(movieService.getMovies());

        // Use these auto-generated columns
        movies.setColumns("title", "releaseYear");

        // Add 'Director' column
        movies.addColumn(movie -> movie.getDirector().getName()).setHeader("Creator");

        // Add link to iMDB column; the TemplateRenderer allows us to use a HTML link.
        movies.addColumn(
                LitRenderer.<Movie>of("<a href='${item.wikilink}' target='_blank'>Click to wiki</a>").withProperty("wikilink", Movie::getImbdLink))
                .setHeader("Wiki");

        // set one column to specific width
        movies.getColumnByKey("releaseYear").setWidth("55px");

        // Add Grid to view
        add(movies);
        Button sayHello = new Button("Say kello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);
        add(name, sayHello);
    }

}
