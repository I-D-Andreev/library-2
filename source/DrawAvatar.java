import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A drawing class that allows the user to make their own avatar.
 * Uses straight line and free drawing.
 *
 * @author Christina Meggs, Steven Lewkowicz
 */

public class DrawAvatar extends Application {

    private String filePath;

    /**
     * The set window width for the window that appears.
     */
    private static final int WINDOW_WIDTH = 600;

    /**
     * The set window height for the window that appears.
     */
    private static final int WINDOW_HEIGHT = 500;

    /**
     * The set canvas height for the area the user can draw in.
     */
    private static final int CANVAS_WIDTH = 400;

    /**
     * The set canvas weight for the area the user can draw in.
     */
    private static final int CANVAS_HEIGHT = 400;

    /**
     * Counter used to track what layer next canvas should be made on.
     */
    private int counter = 0;

    /**
     * Used to detect users initial click.
     */
    private Pair<Double, Double> initialTouch;

    /**
     * Current canvas to use for drawing a line.
     */
    private Canvas layer;

    /**
     * Current canvas to use to trace a line.
     */
    private Canvas trace;

    /**
     * The controller class from which DrawAvatar was called.
     */
    private Controller controller;

    private TextField textFieldToSet;

    /**
     * Constructs drawing interface.
     *
     * @param textField The text field in which the path will be saved.
     */
    public DrawAvatar(TextField textField) {
        this.textFieldToSet = textField;
    }


    /**
     * Set the file path to a new one.
     *
     * @param filePath The new file path.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the file path.
     *
     * @return The file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Starts the whole drawing system.
     *
     * @param primaryStage The window the application will appear in.
     */
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        FlowPane grid = new FlowPane();
        root.getChildren().add(grid);

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setPadding(new Insets(0, 0, 0, 10));

        Slider slider = new Slider();
        slider.setPadding(new Insets(10, 0, 0, 10));
        Label label = new Label("1.0");

        slider.setMax(100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(e -> {
            double value = slider.getValue();
            String str = String.format("%.0f", value);
            label.setText(str);
            gc.setLineWidth(value);
        });


        RadioButton lineButton = new RadioButton("Line");
        lineButton.setPadding(new Insets(0, 0, 0, 10));

        RadioButton traceButton = new RadioButton("Trace");
        traceButton.setPadding(new Insets(0, 0, 0, 10));


        ToggleGroup toggleGroup = new ToggleGroup();

        lineButton.setToggleGroup(toggleGroup);
        traceButton.setToggleGroup(toggleGroup);

        borderRect(gc);
        toggleGroup.selectToggle(lineButton);
        makeLine(canvas, root, colorPicker, slider);
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == lineButton) {
                makeLine(canvas, root, colorPicker, slider);
            } else if (newVal == traceButton) {
                makeTrace(canvas, root, colorPicker, slider);
            }
            borderRect(gc);
        });

        Button clearButton = new Button();

        clearButton.setText("Clear");
        clearButton.setOnAction(e -> {
            clear(root, grid, canvas);
        });

        Button saveButton = new Button();
        saveButton.setText("Save");


        saveButton.setOnAction(e -> {
            captureAndSaveDisplay(grid, root, primaryStage);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
//
//            // here we make image from vbox and add it to scene, can be repeated :)
//            WritableImage snapshot = grid.snapshot(new SnapshotParameters(), null);
//
//            grid.getChildren().add(new ImageView(snapshot));
//            System.out.println(grid.getChildren().size());
//
        });


        grid.getChildren().addAll(colorPicker, lineButton, traceButton, slider, saveButton, clearButton);

        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Clears the whole canvas for the user to start fresh.
     *
     * @param root   The Stackpane which contains all nodes on screen.
     * @param flow   The FlowPane which the all the buttons etc appear on.
     * @param canvas The canvas we want to remain after it is cleared.
     */
    private void clear(StackPane root, FlowPane flow, Canvas canvas) {
        counter = 0;
        layer = new Canvas();
        trace = new Canvas();

        ArrayList<Node> list = new ArrayList<>();

        list.addAll(root.getChildren());
        list.remove(flow);
        list.remove(canvas);

        root.getChildren().removeAll(list);
    }

    /**
     * Add a border to the canvas.
     *
     * @param graphicsContext The current GraphicContents the canvas is working on.
     */
    public void borderRect(GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(8);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                CANVAS_WIDTH,    //width of the rectangle
                CANVAS_HEIGHT);  //height of the rectangle
    }

    /**
     * Allows the user to trace a line (free draw).
     *
     * @param canvas      The current canvas the user is drawing on.
     * @param stackPanel  The StackPane the contains all the nodes, used to add new canvas to.
     * @param colorPicker The ColorPicker to allow the line to change colour.
     * @param slider      The Slider to allow the line to change width.
     */
    private void makeTrace(Canvas canvas, StackPane stackPanel, ColorPicker colorPicker, Slider slider) {
        canvas.setOnMousePressed(e -> {
            Canvas newTrace = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
            trace = newTrace;
            stackPanel.getChildren().add(counter, newTrace);
            counter++;
            GraphicsContext gc = trace.getGraphicsContext2D();
            gc.beginPath();
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
            borderRect(gc);
        });
        canvas.setOnMouseDragged(e -> {
            GraphicsContext gc = trace.getGraphicsContext2D();
            gc.setStroke(colorPicker.getValue());
            gc.setLineWidth(slider.getValue());
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
            borderRect(gc);
        });
        canvas.toFront();
    }

    /**
     * Allows the user to draw a line.
     *
     * @param canvas      The current canvas the user is drawing on.
     * @param stackPanel  The StackPane the contains all the nodes, used to add new canvas to.
     * @param colorPicker The ColorPicker to allow the line to change colour.
     * @param slider      The Slider to allow the line to change width.
     */
    private void makeLine(Canvas canvas, StackPane stackPanel, ColorPicker colorPicker, Slider slider) {
        canvas.setOnMousePressed(e -> {
            Canvas newLayer = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
            layer = newLayer;
            stackPanel.getChildren().add(counter, newLayer);
            counter++;
            initialTouch = new Pair<>(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            GraphicsContext context = layer.getGraphicsContext2D();
            context.clearRect(0, 0, layer.getWidth(), layer.getHeight());
            context.setStroke(colorPicker.getValue());
            context.setLineWidth(slider.getValue());
            context.strokeLine(initialTouch.getKey(), initialTouch.getValue(), e.getX(), e.getY());
        });

    }

    /**
     * Used to save the avatars (canvas's) that user has just drawn.
     *
     * @param flowPanel  The FlowPane that contains all buttons etc at the top.
     * @param stackPanel The StackPane the contains all of the nodes.
     * @param stage      The Stage the class is working in.
     */
    public void captureAndSaveDisplay(FlowPane flowPanel, StackPane stackPanel, Stage stage) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                //Pad the capture area
                ArrayList<Node> list = new ArrayList<>();
                list.addAll(stackPanel.getChildren());
                list.remove(flowPanel);

                Group group = new Group();
                group.getChildren().addAll(list);


                WritableImage writableImage = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
                group.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);

                group.getChildren().removeAll(stackPanel.getChildren());

                stackPanel.getChildren().addAll(list);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            setFilePath(file.getAbsolutePath());
            textFieldToSet.setText(filePath);
        }


    }
}