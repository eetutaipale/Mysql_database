package com.vaadin.example.views;

import com.vaadin.flow.data.renderer.LitRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.example.ApplicationServiceInitListener;
import com.vaadin.example.Database_change;
import com.vaadin.example.data.entity.Movie;
import com.vaadin.example.data.service.MovieService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;

/**
 * A simple Vaadin View class that shows all Movies in a database.
 * <p>
 * See {@link MovieService} for details on the database, and
 * {@link ApplicationServiceInitListener} for adding more demo data.
 */
@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {
    private TextField Title, creator, wiki;
    private IntegerField release;
    private Button sayHello;
    private Button testi;
    public MainView(@Autowired MovieService movieService) {

        // Create and add header text
        add(new H3("Accessing my local mySQL database"));
        Title = new TextField("Title");
        release = new IntegerField("Release year");
        creator = new TextField("creator");
        wiki = new TextField("wiki");
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
        movies.addComponentColumn(person -> {
            Button button = new Button("Delete");
            button.addClickListener(event -> {
                // Create and open a pop-up dialog
                Dialog dialog = new Dialog();
                dialog.add(person.getTitle() + " deleted");
                dialog.open();
                Database_change.deletedata(person.getId());
                movies.setItems(movieService.getMovies());
            });
            return button;
        }).setHeader("Actions");
        // Add Grid to view
        add(movies);
        movies.getDataProvider().refreshAll();
        add(new H4("Add a game"));
        
        Button sayHello = new Button("Add to database");
        sayHello.addClickListener(e -> {
            Notification.show("added " + Title.getValue());
            Database_change.insertData(Title.getValue(), release.getValue(), creator.getValue(), wiki.getValue());
            movies.setItems(movieService.getMovies());
        });
        sayHello.addClickShortcut(Key.ENTER);
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(Title, release, creator, wiki);
        add(layout);
        add(sayHello);
        
        
        
    }

}
