import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class VideoPlayer extends Application {

    private final int OFFSET = 9;
    private String title;
    private final String TRAILER = "trailer";

    public VideoPlayer(String title) {
        this.title = title;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setWidth(825);
        stage.setHeight(650);
        Scene scene = new Scene(new Group());
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);

        webEngine.getLoadWorker().stateProperty()
                .addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

                        if (newState == Worker.State.SUCCEEDED) {
                            stage.setTitle(webEngine.getLocation());
                        }

                    }
                });

        webEngine.load("https://www.youtube.com/embed/" + getUrl());
        scene.setRoot(scrollPane);
        stage.setScene(scene);
        stage.show();
    }

    public String getUrl() throws Exception {

        String youtube = "https://www.youtube.com/results?search_query=";
        String URL = youtube;
        String[] titles = title.split(" ");

        for (String word : titles) {
            URL+= word + "+";
        }

        URL += TRAILER;
        java.net.URL oracle = new URL(URL);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        String trailerUrl = "fail";
        Boolean first = false;

        while(!first) {
            inputLine = in.readLine();
            String line = inputLine;
            if (line.contains("href=\"/watch")) {
                trailerUrl = line;
                first = true;
            }
        }

        in.close();
        int startingIndex = trailerUrl.indexOf("href=\"/watch");
        trailerUrl = trailerUrl.substring(startingIndex);
        startingIndex = trailerUrl.indexOf("/watch?v=");
        startingIndex += OFFSET;
        trailerUrl = trailerUrl.substring(startingIndex);
        int endIndex = trailerUrl.indexOf("\" ");
        trailerUrl = trailerUrl.substring(0, endIndex);

        return trailerUrl;

    }

}