package badicecream;

import java.util.ArrayList;
import java.util.logging.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class BadIceCream extends Application {

    UserAction action = UserAction.NONE;
    Timeline timeline = new Timeline();
    AnimationTimer animator, loadingBarAnimator;
    boolean runningPractice = false, running1 = false, running2 = false, running3 = false, running4 = false, running5 = false;
    int sceneWidth = 600, sceneHeight = 600;
    IceCream iceCream = new IceCream();
    Label positions = new Label();
    int level = 0, numFruit, delayLoadingBar = 0;
    Rectangle sideBar;
    Field field1, field2, field3, field4, field5, practice;

    /**
     * main class
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        displayMenu(primaryStage);

    }

    /**
     * loading screen
     *
     * @param primaryStage
     */
    public void displayLoading(Stage primaryStage) {
        sceneWidth = 600;
        sceneHeight = 600;
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: gainsboro");

        ArrayList<Rectangle> backIce = new ArrayList<>();
        ArrayList<Fruit> backFruit = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                backIce.add(new Rectangle(40 * i, 40 * j, 40, 40));
                backIce.get(backIce.size() - 1).setFill(Color.ALICEBLUE);
                backIce.get(backIce.size() - 1).setStroke(Color.LIGHTBLUE);

            }
        }

        // left column
        backFruit.add(new Fruit(20, 25, "cherry"));
        backFruit.add(new Fruit(25, 100, "banana"));
        backFruit.add(new Fruit(20, 185, "strawberry"));
        backFruit.add(new Fruit(20, 255, "watermelon"));
        backFruit.add(new Fruit(20, 345, "cherry"));
        backFruit.add(new Fruit(25, 420, "banana"));
        backFruit.add(new Fruit(20, 505, "strawberry"));
        backFruit.add(new Fruit(20, 575, "watermelon"));

        // right column
        backFruit.add(new Fruit(580, 15, "watermelon"));
        backFruit.add(new Fruit(580, 105, "strawberry"));
        backFruit.add(new Fruit(585, 180, "banana"));
        backFruit.add(new Fruit(580, 265, "cherry"));
        backFruit.add(new Fruit(580, 335, "watermelon"));
        backFruit.add(new Fruit(580, 425, "strawberry"));
        backFruit.add(new Fruit(585, 500, "banana"));
        backFruit.add(new Fruit(580, 585, "cherry"));

        for (int i = 0; i < backIce.size(); i++) {
            root.getChildren().add(backIce.get(i));
        }

        for (int i = 0; i < backFruit.size(); i++) {
            for (int j = 0; j < backFruit.get(i).getGraphic().getImage().size(); j++) {
                root.getChildren().add(backFruit.get(i).getGraphic().getImage().get(j));
            }
        }

        Rectangle box = new Rectangle(120, 240, 360, 120);
        box.setFill(Color.GAINSBORO);
        box.setStroke(Color.LIGHTBLUE);
        root.getChildren().addAll(box);

        Text loading = new Text(140, 290, "LOADING . . .");
        loading.setFont(Font.font(STYLESHEET_CASPIAN, 54));
        loading.setFill(Color.ALICEBLUE);
        Text outline = new Text(142, 290, "LOADING . . .");
        outline.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 52));
        outline.setFill(Color.LIGHTBLUE);

        Rectangle load = new Rectangle(160, 310, 280, 40);
        load.setFill(Color.TRANSPARENT);
        load.setStroke(Color.LIGHTBLUE);

        root.getChildren().addAll(outline, loading, load);

        ArrayList<Ice> bar = new ArrayList<>();
        bar.add(new Ice(160, 310));
        bar.add(new Ice(200, 310));
        bar.add(new Ice(240, 310));
        bar.add(new Ice(280, 310));
        bar.add(new Ice(320, 310));
        bar.add(new Ice(360, 310));
        bar.add(new Ice(400, 310));

        delayLoadingBar = 0;
        PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
        loadingBarAnimator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                if (delayLoadingBar < bar.size()) {
                    root.getChildren().add(bar.get(delayLoadingBar).getShape());
                    delayLoadingBar = delayLoadingBar + 1;

                    loadingBarAnimator.stop();
                    pause.setOnFinished(e -> loadingBarAnimator.start());
                    pause.play();
                } else {
                    loadingBarAnimator.stop();
                }
            }
        };
        loadingBarAnimator.start();
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setTitle("LOADING...");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * game over screen
     *
     * @param primaryStage
     */
    public void displayLose(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black");
        sceneWidth = 600;
        sceneHeight = 600;

        Pane deadIce = new Pane();

        // add ice cream man
        deadIce.getChildren().add(iceCream.getGraphic().getCir());
        deadIce.getChildren().add(iceCream.getGraphic().getTopOutline());
        deadIce.getChildren().add(iceCream.getGraphic().getCone());
        for (int i = 0; i < 7; i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                deadIce.getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            deadIce.getChildren().add(iceCream.getGraphic().getSwirl().get(i));

        }

        deadIce.getChildren().add(iceCream.getGraphic().getTopRight());

        //   root.getChildren().add(iceCream.getGraphic().getBottomMouth());
        //   root.getChildren().add(iceCream.getGraphic().getTopMouth());
        iceCream.getGraphic().getSwirl().get(1).setFill(Color.BLACK);

        iceCream.getGraphic().getCir().setCenterX(300);
        iceCream.getGraphic().getCir().setCenterY(300);
        iceCream.getGraphic().moveGraphic();

        // x eyes
        deadIce.getChildren().add(new Line(iceCream.getGraphic().getCir().getCenterX() - 8, iceCream.getGraphic().getCir().getCenterY() - 8, iceCream.getGraphic().getCir().getCenterX() - 3, iceCream.getGraphic().getCir().getCenterY() - 3));
        deadIce.getChildren().add(new Line(iceCream.getGraphic().getCir().getCenterX() - 8, iceCream.getGraphic().getCir().getCenterY() - 3, iceCream.getGraphic().getCir().getCenterX() - 3, iceCream.getGraphic().getCir().getCenterY() - 8));
        deadIce.getChildren().add(new Line(iceCream.getGraphic().getCir().getCenterX() + 8, iceCream.getGraphic().getCir().getCenterY() - 8, iceCream.getGraphic().getCir().getCenterX() + 3, iceCream.getGraphic().getCir().getCenterY() - 3));
        deadIce.getChildren().add(new Line(iceCream.getGraphic().getCir().getCenterX() + 8, iceCream.getGraphic().getCir().getCenterY() - 3, iceCream.getGraphic().getCir().getCenterX() + 3, iceCream.getGraphic().getCir().getCenterY() - 8));

        // failure
        Text failure = new Text(70, 330, "GAME   OVER.");
        failure.setFont(new Font("Chiller", 100));
        failure.setFill(Color.RED);
        Reflection reflection = new Reflection();

        root.getChildren().add(failure);

        failure.setEffect(reflection);

        deadIce.setEffect(new Reflection(70, 0.15, 0.25, 0));

        root.getChildren().add(deadIce);

        // replay button
        Button tryAgain = new Button("TRY AGAIN");
        tryAgain.setFont(new Font(30));
        tryAgain.setStyle("-fx-background-color: black; -fx-text-fill: red");
        tryAgain.setOnMouseEntered(e -> tryAgain.setStyle("-fx-background-color: red; -fx-text-fill: black"));
        tryAgain.setOnMouseExited(e -> tryAgain.setStyle("-fx-background-color: black; -fx-text-fill: red"));
        tryAgain.setOnMouseClicked(e -> displayMenu(primaryStage));
        HBox bottom = new HBox();
        bottom.getChildren().add(tryAgain);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        root.setBottom(bottom);

        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        primaryStage.setTitle("GAME OVER.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * congratulatory screen
     *
     * @param primaryStage
     */
    public void displayWin(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: aliceblue");
        sceneWidth = 600;
        sceneHeight = 600;

        ArrayList<Shape> graphic = new ArrayList<>();

        // cone
        graphic.add(new Polygon(
                new double[]{
                    sceneWidth / 2 - 100, sceneHeight / 2,
                    sceneWidth / 2 + 100, sceneHeight / 2,
                    sceneWidth / 2 + 50, sceneHeight / 2 + 200,
                    sceneWidth / 2 - 50, sceneHeight / 2 + 200

                }
        ));
        graphic.get(graphic.size() - 1).setFill(Color.GOLDENROD);
        graphic.get(graphic.size() - 1).setStroke(Color.DARKGOLDENROD);
        graphic.get(graphic.size() - 1).setStrokeWidth(3);
        root.getChildren().add(graphic.get(graphic.size() - 1));

        // ice cream
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 + 5, 160, 80));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 - 70, 120, 60));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 - 130, 80, 40));
        graphic.add(new Ellipse(sceneWidth / 2 + 10, sceneHeight / 2 - 180, 40, 40));
        graphic.add(new Ellipse(sceneWidth / 2 + 60, sceneHeight / 2 - 190, 40, 28.5));

        // eyes
        graphic.add(new Ellipse(sceneWidth / 2 - 40, sceneHeight / 2 - 80, 20, 35));
        graphic.add(new Ellipse(sceneWidth / 2 + 40, sceneHeight / 2 - 80, 20, 35));
        // pupils
        graphic.add(new Ellipse(sceneWidth / 2 - 40, sceneHeight / 2 - 70, 15, 25));
        graphic.add(new Ellipse(sceneWidth / 2 + 40, sceneHeight / 2 - 70, 15, 25));

        // mouth
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2, 50, 50));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 - 15, 60, 35));

        for (int i = 1; i < graphic.size(); i++) {

            graphic.get(i).setFill(Color.DEEPPINK);
            root.getChildren().add(graphic.get(i));
        }

        graphic.get(5).setFill(Color.ALICEBLUE);
        graphic.get(6).setFill(Color.WHITE);
        graphic.get(7).setFill(Color.WHITE);
        graphic.get(6).setStroke(Color.BLACK);
        graphic.get(7).setStroke(Color.BLACK);
        graphic.get(8).setFill(Color.BLACK);
        graphic.get(9).setFill(Color.BLACK);
        graphic.get(10).setFill(Color.RED);

        // mouth
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2, 16, 6));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 + 10, 20, 20));

        // CONGRATS
        Text winner = new Text(110, 580, "CONGRATULATIONS!");
        winner.setFont(new Font("AR CARTER", 60));
        winner.setStroke(Color.PINK);
        winner.setFill(Color.SKYBLUE);
        root.getChildren().add(winner);

        Text winner2 = new Text(110, 60, "CONGRATULATIONS!");
        winner2.setFont(new Font("AR CARTER", 60));
        winner2.setStroke(Color.PINK);
        winner2.setFill(Color.SKYBLUE);
        root.getChildren().add(winner2);

        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        AnimationTimer congrats = new AnimationTimer() {
            @Override
            public void handle(long now) {
                winner.setX(winner.getX() + 1);
                winner2.setX(winner2.getX() - 1);
                if (winner.getX() >= sceneWidth && winner2.getX() <= -300) {
                    winner.setX(-300);
                    winner2.setX(sceneWidth);
                }
            }
        };

        primaryStage.setTitle("CONGRATULATIONS!");
        primaryStage.setScene(scene);
        primaryStage.show();
        congrats.start();
    }

    /**
     * displays instructions
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayInstructions(Stage primaryStage) throws Exception {
        practice = new Field();
        sceneWidth = 600;
        sceneHeight = 450;
        practice.getRoot().setStyle("-fx-background-color: gainsboro");

        HBox bottom = new HBox(); // for formatting bottom part of scene

        GridPane dialog = new GridPane(); // where the words goes
        // formatting
        dialog.setHgap(10);
        dialog.setVgap(10);
        dialog.setPadding(new Insets(10, 10, 10, 10));
        dialog.setMinSize(sceneWidth - 120, 150);
        dialog.setMaxSize(sceneWidth - 120, 150);

        // first step 
        Text welcome = new Text("Welcome to BAD ICE CREAM!");
        dialog.add(welcome, 2, 0);
        Text intro = new Text("In this game you play the pink Ice Cream you see above."
                + "\nIsn't he cute?"
                + "\nYou can practice using his controls in this stage!");
        dialog.add(intro, 2, 1);

        //second step
        Text movingInstruct = new Text("To move, use the arrow keys on your keyboard."
                + "\n→ to go right."
                + "\n← to go left."
                + "\n↑ to go up."
                + "\n↓ to go down"
                + "\nI'm sure you're more than smart enough to figure that out.");

        // third step
        Text fruitStep = new Text("The objective of the game is to collect all the fruit in each level. "
                + "\nTo collect a fruit, you just need to cross it."
                + "\nWhen you collect all the fruit in each level, you move onto the next level, "
                + "\nuntil the final level."
                + "\nSeems easy enough, right?");

        // fourth step
        Text breakStep = new Text("Well, sometimes, the fruit are surrounded by ice."
                + "\nAnnoying, right?"
                + "\nWell, worry no more! Our little Ice Cream Man has the ability to break this ice."
                + "\nHe can break whole rows or columns of ice, using the following keys:"
                + "\nW - break ice above"
                + "\nA - break ice to the left"
                + "\nS - break ice below"
                + "\nD - break ice to the right");

        // fifth step
        Text makeStep = new Text("And Ice Cream Man has another superpower!"
                + "\nHe can make ice!"
                + "\nMake ice with the following keys:"
                + "\nSHIFT + W - make ice above"
                + "\nSHIFT + A - make ice to the left"
                + "\nSHIFT + S - make ice below"
                + "\nSHIFT + D - make ice to the right"
                + "\nCool, huh? (Get it?) But why should we make ice? Well...");

        // sixth step
        Text spoonIntro = new Text("Unfortunately, our Ice Cream Man has made a few enemies..."
                + "\nThis is a Spoonie. They are pretty simple-minded creatures."
                + "\nSpoonies only go in two directions: Either up and down or left and right."
                + "\nThey are quite easy to avoid, so I wouldn't worry much about them.");
        Enemy1 spoonie = new Enemy1(Color.TRANSPARENT, 0, 300, 225, 15, sceneWidth, sceneHeight, true);

        // seventh step
        Text bowlIntro = new Text("This angry little guy is a Bowler."
                + "\nThey go in circles."
                + "\nEverytime they collide with something, they change direction.");
        Enemy2 bowler = new Enemy2(1, Color.TRANSPARENT, 0, 300, 225, 15, sceneWidth, sceneHeight);

        // eighth step
        Text ghostIntro = new Text("Finally, this spooky guy is the vengeful ghost of an Ice Cream that was wronged."
                + "\nHe will chase you."
                + "\nBEWARE.");
        Enemy3 ghost = new Enemy3(1, Color.TRANSPARENT, 0, 300, 225, 15, sceneWidth, sceneHeight);

        // ninth step
        Text enemyStep = new Text("Avoid these enemies!"
                + "\nYou only have three lives to collect all the fruit on all five levels."
                + "\nGood luck!");

        // next button
        Button next = new Button("NEXT");
        next.setPrefSize(120, 150);
        next.setStyle("-fx-background-color: aquamarine; -fx-text-fill: black");
        next.setFont(new Font("AR CARTER", 20));
        next.setOnMouseEntered(e -> next.setStyle("-fx-background-color: teal; -fx-text-fill: white"));
        next.setOnMouseExited(e -> next.setStyle("-fx-background-color: aquamarine; -fx-text-fill: black"));

        // enables next step in instructions
        next.setOnAction(e -> {
            if (welcome.isVisible()) { // go to step 2
                dialog.getChildren().remove(welcome);
                dialog.getChildren().remove(intro);
                welcome.setVisible(false);
                intro.setVisible(false);
                dialog.add(movingInstruct, 2, 0);
            } else if (movingInstruct.isVisible()) { // go to step 3
                dialog.getChildren().remove(movingInstruct);
                movingInstruct.setVisible(false);
                dialog.add(fruitStep, 2, 0);
                practice.addFruits(new Fruit(30, 30, "watermelon"));
                practice.addFruits(new Fruit(210, 110, "strawberry"));
                practice.addFruits(new Fruit(410, 270, "banana"));
                practice.addFruits(new Fruit(150, 310, "cherry"));
                for (int i = 0; i < practice.getFruits().size(); i++) {
                    for (int j = 0; j < practice.getFruits().get(i).getGraphic().getImage().size(); j++) {
                        practice.getRoot().getChildren().add(practice.getFruits().get(i).getGraphic().getImage().get(j));
                    }
                }
            } else if (fruitStep.isVisible()) { // go to step 4
                dialog.getChildren().remove(fruitStep);
                fruitStep.setVisible(false);
                dialog.add(breakStep, 2, 0);

                //remove previous step's objects
                for (int i = 0; i < practice.getFruits().size(); i++) {
                    for (int j = 0; j < practice.getFruits().get(i).getGraphic().getImage().size(); j++) {
                        practice.getRoot().getChildren().remove(practice.getFruits().get(i).getGraphic().getImage().get(j));
                    }
                }
                while (practice.getFruits().size() > 0) {
                    practice.getFruits().remove(0);
                }

                // square of ice
                practice.addObstacle(new Ice(10, 10));
                practice.addObstacle(new Ice(50, 10));
                practice.addObstacle(new Ice(90, 10));
                practice.addObstacle(new Ice(90, 50));
                practice.addObstacle(new Ice(90, 90));
                practice.addObstacle(new Ice(50, 90));
                practice.addObstacle(new Ice(10, 90));
                practice.addObstacle(new Ice(10, 50));

                practice.addFruits(new Fruit(70, 70, "watermelon")); // fruit in the center

                //rows of ice
                practice.addObstacle(new Ice(10, 410));
                practice.addObstacle(new Ice(50, 410));
                practice.addObstacle(new Ice(90, 410));
                practice.addObstacle(new Ice(130, 410));
                practice.addObstacle(new Ice(170, 410));
                practice.addObstacle(new Ice(210, 410));
                practice.addObstacle(new Ice(250, 410));
                practice.addObstacle(new Ice(290, 410));
                practice.addObstacle(new Ice(330, 410));
                practice.addObstacle(new Ice(370, 410));
                practice.addObstacle(new Ice(410, 410));
                practice.addObstacle(new Ice(450, 410));
                practice.addObstacle(new Ice(490, 410));
                practice.addObstacle(new Ice(530, 410));

                // columns of ice
                practice.addObstacle(new Ice(570, 370));
                practice.addObstacle(new Ice(570, 330));
                practice.addObstacle(new Ice(570, 290));
                practice.addObstacle(new Ice(570, 250));
                practice.addObstacle(new Ice(570, 210));
                practice.addObstacle(new Ice(570, 170));
                practice.addObstacle(new Ice(570, 50));
                practice.addObstacle(new Ice(570, 10));

                // adding fruit and ice to borderpane
                for (int i = 0; i < practice.getList().size(); i++) {
                    practice.getRoot().getChildren().add(practice.getList().get(i).getShape());
                }

                for (int i = 0; i < practice.getFruits().size(); i++) {
                    for (int j = 0; j < practice.getFruits().get(i).getGraphic().getImage().size(); j++) {
                        practice.getRoot().getChildren().add(practice.getFruits().get(i).getGraphic().getImage().get(j));
                    }
                }

                // repositioning ice cream man
                iceCream.getGraphic().getCir().setCenterX(590);
                iceCream.getGraphic().getCir().setCenterY(430);
                iceCream.getGraphic().moveGraphic();

            } else if (breakStep.isVisible()) { // go to step 5
                dialog.getChildren().remove(breakStep);
                breakStep.setVisible(false);
                dialog.add(makeStep, 2, 0);
            } else if (makeStep.isVisible()) { // go to step 6
                dialog.getChildren().remove(makeStep);
                makeStep.setVisible(false);

                // emptying field
                emptyField(practice);
                practice.getRoot().setBottom(bottom);

                // remove ice cream man from practice field
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getCir());
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getCone());
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getTopOutline());
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getTopRight());
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getTopMouth());
                practice.getRoot().getChildren().remove(iceCream.getGraphic().getBottomMouth());
                for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
                    if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                        practice.getRoot().getChildren().remove(iceCream.getGraphic().getSwirlOutline().get(i));
                    }
                    practice.getRoot().getChildren().remove(iceCream.getGraphic().getSwirl().get(i));
                }

                // add spoonie to screen
                practice.addEnemies(spoonie);
                practice = addSpoon(practice, spoonie);
                dialog.add(spoonIntro, 2, 0);
            } else if (spoonIntro.isVisible()) { // go to step 7
                dialog.getChildren().remove(spoonIntro);
                spoonIntro.setVisible(false);
                spoonie.getGraphic().getBase().setCenterX(260); // reposition spoonie
                spoonie.getGraphic().moveGraphic();

                // add bowler to screen
                practice.addEnemies(bowler);
                practice = addBowl(practice, bowler);
                dialog.add(bowlIntro, 2, 0);
            } else if (bowlIntro.isVisible()) { // go to step 8
                dialog.getChildren().remove(bowlIntro);
                bowlIntro.setVisible(false);
                bowler.getGraphic().getBase().setCenterX(340); // reposition bowler
                bowler.getGraphic().moveGraphic();

                // add ghost to screen
                practice.addEnemies(ghost);
                practice = addGhost(practice, ghost);
                dialog.add(ghostIntro, 2, 0);
            } else if (ghostIntro.isVisible()) { // go to step 9
                dialog.getChildren().remove(ghostIntro);
                ghostIntro.setVisible(false);

                dialog.add(enemyStep, 2, 0);
            } else { // return to menu
                runningPractice = false;
                displayMenu(primaryStage);
            }
        });

        dialog.setStyle("-fx-background-color: white"); // background color of bottom part
        bottom.getChildren().addAll(dialog, next);
        practice.getRoot().setBottom(bottom);

        coordinateKeyInput(primaryStage, practice);
        primaryStage.show();
    }

    /**
     * emptying field recursion
     *
     * @param field
     * @return
     */
    public Field emptyField(Field field) {
        if (field.getRoot().getChildren().isEmpty() && field.getEnemies().isEmpty() && field.getFruits().isEmpty() && field.getList().isEmpty()) {
            return field;
        } else if (field.getEnemies().size() > 0) {
            field.getEnemies().remove(0);
            field.getList().remove(0);
            field.getRoot().getChildren().remove(0);
            return emptyField(field);
        } else if (field.getFruits().size() > 0) {
            field.getFruits().remove(0);
            field.getRoot().getChildren().remove(0);
            return emptyField(field);
        } else if (field.getList().size() > 0) {
            field.getList().remove(0);
            field.getRoot().getChildren().remove(0);
            return emptyField(field);
        } else {
            field.getRoot().getChildren().remove(0);
            return emptyField(field);
        }
    }

    /**
     * menu
     *
     * @param primaryStage
     */
    public void displayMenu(Stage primaryStage) {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        sceneWidth = 600;
        sceneHeight = 600;
        level = 0;
        iceCream.setLife(3);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: gainsboro");

        // background of ice
        ArrayList<Rectangle> backIce = new ArrayList<>();
        ArrayList<Fruit> backFruit = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                backIce.add(new Rectangle(40 * i, 40 * j, 40, 40));
                backIce.get(backIce.size() - 1).setFill(Color.ALICEBLUE);
                backIce.get(backIce.size() - 1).setStroke(Color.LIGHTBLUE);

            }
        }

        // left column
        backFruit.add(new Fruit(20, 25, "cherry"));
        backFruit.add(new Fruit(25, 100, "banana"));
        backFruit.add(new Fruit(20, 185, "strawberry"));
        backFruit.add(new Fruit(20, 255, "watermelon"));
        backFruit.add(new Fruit(20, 345, "cherry"));
        backFruit.add(new Fruit(25, 420, "banana"));
        backFruit.add(new Fruit(20, 505, "strawberry"));
        backFruit.add(new Fruit(20, 575, "watermelon"));

        // right column
        backFruit.add(new Fruit(580, 15, "watermelon"));
        backFruit.add(new Fruit(580, 105, "strawberry"));
        backFruit.add(new Fruit(585, 180, "banana"));
        backFruit.add(new Fruit(580, 265, "cherry"));
        backFruit.add(new Fruit(580, 335, "watermelon"));
        backFruit.add(new Fruit(580, 425, "strawberry"));
        backFruit.add(new Fruit(585, 500, "banana"));
        backFruit.add(new Fruit(580, 585, "cherry"));

        for (int i = 0; i < backIce.size(); i++) {
            root.getChildren().add(backIce.get(i));
        }

        for (int i = 0; i < backFruit.size(); i++) {
            for (int j = 0; j < backFruit.get(i).getGraphic().getImage().size(); j++) {
                root.getChildren().add(backFruit.get(i).getGraphic().getImage().get(j));
            }
        }

        ArrayList<Shape> graphic = new ArrayList<>();

        // cone
        graphic.add(new Polygon(
                new double[]{
                    sceneWidth / 2 - 100, sceneHeight / 2,
                    sceneWidth / 2 + 100, sceneHeight / 2,
                    sceneWidth / 2 + 50, sceneHeight / 2 + 200,
                    sceneWidth / 2 - 50, sceneHeight / 2 + 200

                }
        ));
        graphic.get(graphic.size() - 1).setFill(Color.GOLDENROD);
        graphic.get(graphic.size() - 1).setStroke(Color.DARKGOLDENROD);
        graphic.get(graphic.size() - 1).setStrokeWidth(3);
        root.getChildren().add(graphic.get(graphic.size() - 1));

        // ice cream
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 + 5, 160, 80));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 - 70, 120, 60));
        graphic.add(new Ellipse(sceneWidth / 2, sceneHeight / 2 - 130, 80, 40));
        graphic.add(new Ellipse(sceneWidth / 2 + 10, sceneHeight / 2 - 180, 40, 40));
        graphic.add(new Ellipse(sceneWidth / 2 + 60, sceneHeight / 2 - 190, 40, 28.5));

        for (int i = 1; i < graphic.size(); i++) {

            graphic.get(i).setFill(Color.DEEPPINK);
            root.getChildren().add(graphic.get(i));
        }

        //coverup
        graphic.get(5).setFill(Color.ALICEBLUE);
        Line coverV = new Line(360, 40, 360, 140);
        coverV.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(coverV);
        Line coverV2 = new Line(400, 80, 400, 120);
        coverV2.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(coverV2);
        Line coverH = new Line(322, 120, 400, 120);
        coverH.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(coverH);
        Line coverH2 = new Line(322, 80, 400, 80);
        coverH2.setStroke(Color.LIGHTBLUE);
        root.getChildren().add(coverH2);

        // title
        Text title1 = new Text(sceneWidth / 2 - 40, sceneHeight / 2 - 75, "BAD");
        title1.setFill(Color.LIGHTBLUE);
        title1.setFont(new Font("AR CARTER", 60));

        root.getChildren().add(title1);
        Text title2 = new Text(sceneWidth / 2 - 100, sceneHeight / 2, "ICE CREAM");
        title2.setFill(Color.LIGHTBLUE);
        title2.setFont(new Font("AR CARTER", 60));
        root.getChildren().add(title2);

        HBox layout = new HBox();
        layout.setSpacing(50);

        // buttons
        Button play = new Button("PLAY");
        play.setFont(new Font("AR CARTER", 20));
        play.setMinWidth(150);
        play.setStyle("-fx-background-color: thistle; -fx-text-fill: black");
        play.setOnMouseEntered(e -> play.setStyle("-fx-background-color: darkmagenta; -fx-text-fill: white"));
        play.setOnMouseExited(e -> play.setStyle("-fx-background-color: thistle; -fx-text-fill: black"));
        play.setOnMouseClicked((MouseEvent e) -> {
            try {
                level = 1;
                //running1 = true;
                displayLoading(primaryStage);
                pause.setOnFinished(event -> {
                    try {
                        displayLevel1(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pause.play();
            } catch (Exception ex) {
                Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button inst = new Button("INSTRUCTIONS");
        inst.setFont(new Font("AR CARTER", 20));
        inst.setMinWidth(150);
        inst.setStyle("-fx-background-color: thistle; -fx-text-fill: black");
        inst.setOnMouseEntered(e -> inst.setStyle("-fx-background-color: darkmagenta; -fx-text-fill: white"));
        inst.setOnMouseExited(e -> inst.setStyle("-fx-background-color: thistle; -fx-text-fill: black"));
        inst.setOnMouseClicked(e -> {
            try {
                runningPractice = true;
                displayInstructions(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        layout.getChildren().addAll(play, inst);

        root.setBottom(layout);

        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setPadding(new Insets(15, 12, 15, 12));

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setTitle("MAIN MENU");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * sidebar in each level
     *
     * @param field
     */
    public void displaySideBar(Field field) {

        sideBar = new Rectangle(sceneWidth + 3, 3, 194, sceneHeight - 6);
        sideBar.setFill(Color.ALICEBLUE);
        sideBar.setStroke(Color.LIGHTBLUE);
        sideBar.setStrokeWidth(4);
        field.getRoot().getChildren().add(sideBar);

        //sidebar graphics and text
        Text levelText = new Text(sceneWidth + 44, 60, "LEVEL " + level);
        levelText.setFont(Font.font(STYLESHEET_CASPIAN, 34));
        levelText.setFill(Color.DARKGRAY);
        field.getRoot().getChildren().add(levelText);

        ArrayList<Shape> heart1 = new ArrayList<>();
        heart1.add(new Ellipse(sceneWidth + 30, 100, 10, 10));
        heart1.add(new Ellipse(sceneWidth + 48, 100, 10, 10));
        heart1.add(new Ellipse(sceneWidth + 38, 104, 10, 4));
        heart1.add(new Polygon(
                new double[]{sceneWidth + 24, 108, sceneWidth + 54, 108, sceneWidth + 38, 120
                }));
        ArrayList<Shape> heart2 = new ArrayList<>();
        heart2.add(new Ellipse(sceneWidth + 90, 100, 10, 10));
        heart2.add(new Ellipse(sceneWidth + 108, 100, 10, 10));
        heart2.add(new Ellipse(sceneWidth + 98, 104, 10, 4));
        heart2.add(new Polygon(
                new double[]{sceneWidth + 84, 108, sceneWidth + 114, 108, sceneWidth + 98, 120
                }));
        ArrayList<Shape> heart3 = new ArrayList<>();
        heart3.add(new Ellipse(sceneWidth + 150, 100, 10, 10));
        heart3.add(new Ellipse(sceneWidth + 168, 100, 10, 10));
        heart3.add(new Ellipse(sceneWidth + 158, 104, 10, 4));
        heart3.add(new Polygon(
                new double[]{sceneWidth + 144, 108, sceneWidth + 174, 108, sceneWidth + 158, 120
                }));

        for (int i = 0; i < heart1.size(); i++) {
            heart1.get(i).setFill(Color.LIGHTCORAL);
            heart2.get(i).setFill(Color.LIGHTCORAL);
            heart3.get(i).setFill(Color.LIGHTCORAL);
            if (iceCream.getLife() == 2) {
                heart3.get(i).setFill(Color.LIGHTGREY);
            } else if (iceCream.getLife() == 1) {
                heart2.get(i).setFill(Color.LIGHTGREY);
                heart3.get(i).setFill(Color.LIGHTGREY);
            } else if (iceCream.getLife() == 0) {
                heart1.get(i).setFill(Color.LIGHTGREY);
                heart2.get(i).setFill(Color.LIGHTGREY);
                heart3.get(i).setFill(Color.LIGHTGREY);
            }

            field.getRoot().getChildren().addAll(heart1.get(i), heart2.get(i), heart3.get(i));
        }

        int ya = 0, yb = 0, yc = 0; // the intervals at which the text moves up and down
        switch (level) {
            case 2:
                ya = 10;
                yb = 20;
                yc = 30;
                break;
            case 3:
                ya = 10;
                yb = 40;
                yc = 70;
                break;
            case 4:
                ya = 10;
                yb = 40;
                yc = 70;
                break;
            case 5:
                ya = 10;
                yb = 40;
                yc = 70;
                break;
            default:
                break;
        }
        Text a = new Text(sceneWidth + 20, 160 + ya, "MOVE:\n"
                + "\n\n up     down    left     right");
        a.setFont(Font.font(STYLESHEET_CASPIAN, 14));
        a.setFill(Color.DARKGRAY);

        Text b = new Text(sceneWidth + 20, 250 + yb, "BREAK ICE:\n"
                + "\n\n up     down    left     right");
        b.setFont(Font.font(STYLESHEET_CASPIAN, 14));
        b.setFill(Color.DARKGRAY);

        Text c = new Text(sceneWidth + 20, 340 + yc, "MAKE ICE:\n "
                + "\n\n up     down    left     right");
        c.setFont(Font.font(STYLESHEET_CASPIAN, 14));
        c.setFill(Color.DARKGRAY);
        field.getRoot().getChildren().addAll(a, b, c);

        ArrayList<Rectangle> keys = new ArrayList<>();
        keys.add(new Rectangle(sceneWidth + 24, 174 + ya, 16, 18));
        keys.add(new Rectangle(sceneWidth + 68, 174 + ya, 16, 18));
        keys.add(new Rectangle(sceneWidth + 112, 174 + ya, 16, 18));
        keys.add(new Rectangle(sceneWidth + 154, 174 + ya, 16, 18));

        keys.add(new Rectangle(sceneWidth + 24, 264 + yb, 16, 18));
        keys.add(new Rectangle(sceneWidth + 68, 264 + yb, 16, 18));
        keys.add(new Rectangle(sceneWidth + 112, 264 + yb, 16, 18));
        keys.add(new Rectangle(sceneWidth + 154, 264 + yb, 16, 18));

        keys.add(new Rectangle(sceneWidth + 20, 348 + yc, 25, 14));
        keys.add(new Rectangle(sceneWidth + 64, 348 + yc, 25, 14));
        keys.add(new Rectangle(sceneWidth + 108, 348 + yc, 25, 14));
        keys.add(new Rectangle(sceneWidth + 150, 348 + yc, 25, 14));

        keys.add(new Rectangle(sceneWidth + 30, 368 + yc, 14, 12));
        keys.add(new Rectangle(sceneWidth + 74, 368 + yc, 14, 12));
        keys.add(new Rectangle(sceneWidth + 118, 368 + yc, 14, 12));
        keys.add(new Rectangle(sceneWidth + 160, 368 + yc, 14, 12));

        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).setFill(Color.TRANSPARENT);
            keys.get(i).setStroke(Color.DARKGRAY);
            field.getRoot().getChildren().add(keys.get(i));
        }

        ArrayList<Shape> keyTexta = new ArrayList<>();
        keyTexta.add(new Polygon(
                new double[]{sceneWidth + 28, 186 + ya, sceneWidth + 32, 180 + ya, sceneWidth + 36, 186 + ya
                }));
        keyTexta.add(new Polygon(
                new double[]{sceneWidth + 72, 180 + ya, sceneWidth + 76, 186 + ya, sceneWidth + 80, 180 + ya
                }));
        keyTexta.add(new Polygon(
                new double[]{sceneWidth + 123, 179 + ya, sceneWidth + 123, 187 + ya, sceneWidth + 117, 183 + ya
                }));
        keyTexta.add(new Polygon(
                new double[]{sceneWidth + 158, 179 + ya, sceneWidth + 158, 187 + ya, sceneWidth + 164, 183 + ya
                }));
        for (int i = 0; i < keyTexta.size(); i++) {
            keyTexta.get(i).setFill(Color.DARKGRAY);
            field.getRoot().getChildren().addAll(keyTexta.get(i));
        }

        Text keyTextb = new Text(sceneWidth + 27, 277 + yb, "W             S              A             D");
        keyTextb.setFont(Font.font(STYLESHEET_CASPIAN, 10));
        keyTextb.setFill(Color.DARKGRAY);
        Text keyTextc = new Text(sceneWidth + 21, 358 + yc, "SHIFT         SHIFT        SHIFT        SHIFT");
        keyTextc.setFont(Font.font(STYLESHEET_CASPIAN, 9));
        keyTextc.setFill(Color.DARKGRAY);
        Text keyTextd = new Text(sceneWidth + 20, 378 + yc, "+   W          +  S           +  A          +  D");
        keyTextd.setFont(Font.font(STYLESHEET_CASPIAN, 9));
        keyTextd.setFill(Color.DARKGRAY);
        field.getRoot().getChildren().addAll(keyTextb, keyTextc, keyTextd);
    }

    /**
     * level 5
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayLevel5(Stage primaryStage) throws Exception { // make sure to initialize fruit, ice, and enemies in each level and add new field
        field5 = new Field();
        sceneWidth = 860;
        sceneHeight = 620;
        //level = 5;

        // ice blocks
        //left side top
        field5.addObstacle(new Ice(10, 10));
        field5.addObstacle(new Ice(10, 50));
        field5.addObstacle(new Ice(50, 50));
        field5.addObstacle(new Ice(90, 10));
        field5.addObstacle(new Ice(90, 50));
        field5.addObstacle(new Ice(130, 10));
        field5.addObstacle(new Ice(130, 50));
        field5.addObstacle(new Ice(130, 90));
        field5.addObstacle(new Ice(170, 10));
        field5.addObstacle(new Ice(170, 90));
        field5.addObstacle(new Ice(210, 10));
        field5.addObstacle(new Ice(210, 50));
        field5.addObstacle(new Ice(210, 90));
        field5.addObstacle(new Ice(250, 10));
        field5.addObstacle(new Ice(250, 50));
        field5.addObstacle(new Ice(250, 90));
        field5.addObstacle(new Ice(250, 130));
        field5.addObstacle(new Ice(290, 10));
        field5.addObstacle(new Ice(290, 50));
        field5.addObstacle(new Ice(290, 130));
        field5.addObstacle(new Ice(330, 10));
        field5.addObstacle(new Ice(330, 50));
        field5.addObstacle(new Ice(330, 90));
        field5.addObstacle(new Ice(330, 130));
        field5.addObstacle(new Ice(370, 10));
        field5.addObstacle(new Ice(370, 50));
        field5.addObstacle(new Ice(370, 90));
        field5.addObstacle(new Ice(370, 130));
        field5.addObstacle(new Ice(370, 170));
        field5.addObstacle(new Ice(410, 50));
        field5.addObstacle(new Ice(410, 90));
        field5.addObstacle(new Ice(410, 170));

        //right side top
        field5.addObstacle(new Ice(450, 10));
        field5.addObstacle(new Ice(450, 50));
        field5.addObstacle(new Ice(450, 90));
        field5.addObstacle(new Ice(450, 130));
        field5.addObstacle(new Ice(450, 170));
        field5.addObstacle(new Ice(490, 10));
        field5.addObstacle(new Ice(490, 50));
        field5.addObstacle(new Ice(490, 90));
        field5.addObstacle(new Ice(490, 130));
        field5.addObstacle(new Ice(530, 10));
        field5.addObstacle(new Ice(530, 50));
        field5.addObstacle(new Ice(530, 130));
        field5.addObstacle(new Ice(570, 10));
        field5.addObstacle(new Ice(570, 50));
        field5.addObstacle(new Ice(570, 90));
        field5.addObstacle(new Ice(570, 130));
        field5.addObstacle(new Ice(610, 10));
        field5.addObstacle(new Ice(610, 50));
        field5.addObstacle(new Ice(610, 90));
        field5.addObstacle(new Ice(650, 10));
        field5.addObstacle(new Ice(650, 90));
        field5.addObstacle(new Ice(690, 10));
        field5.addObstacle(new Ice(690, 50));
        field5.addObstacle(new Ice(690, 90));
        field5.addObstacle(new Ice(730, 10));
        field5.addObstacle(new Ice(730, 50));
        field5.addObstacle(new Ice(770, 50));
        field5.addObstacle(new Ice(810, 10));
        field5.addObstacle(new Ice(810, 50));

        //middle
        field5.addObstacle(new Ice(410, 210));
        field5.addObstacle(new Ice(410, 250));
        field5.addObstacle(new Ice(370, 250));
        field5.addObstacle(new Ice(450, 250));
        field5.addObstacle(new Ice(410, 290));
        field5.addObstacle(new Ice(330, 290));
        field5.addObstacle(new Ice(490, 290));
        field5.addObstacle(new Ice(410, 330));
        field5.addObstacle(new Ice(370, 330));
        field5.addObstacle(new Ice(450, 330));
        field5.addObstacle(new Ice(410, 370));

        //left side bottom
        field5.addObstacle(new Ice(10, 570));
        field5.addObstacle(new Ice(10, 530));
        field5.addObstacle(new Ice(50, 530));
        field5.addObstacle(new Ice(90, 570));
        field5.addObstacle(new Ice(90, 530));
        field5.addObstacle(new Ice(130, 570));
        field5.addObstacle(new Ice(130, 530));
        field5.addObstacle(new Ice(130, 490));
        field5.addObstacle(new Ice(170, 570));
        field5.addObstacle(new Ice(170, 490));
        field5.addObstacle(new Ice(210, 570));
        field5.addObstacle(new Ice(210, 530));
        field5.addObstacle(new Ice(210, 490));
        field5.addObstacle(new Ice(250, 570));
        field5.addObstacle(new Ice(250, 530));
        field5.addObstacle(new Ice(250, 490));
        field5.addObstacle(new Ice(250, 450));
        field5.addObstacle(new Ice(290, 570));
        field5.addObstacle(new Ice(290, 530));
        field5.addObstacle(new Ice(290, 450));
        field5.addObstacle(new Ice(330, 570));
        field5.addObstacle(new Ice(330, 530));
        field5.addObstacle(new Ice(330, 490));
        field5.addObstacle(new Ice(330, 450));
        field5.addObstacle(new Ice(370, 570));
        field5.addObstacle(new Ice(370, 530));
        field5.addObstacle(new Ice(370, 490));
        field5.addObstacle(new Ice(370, 450));
        field5.addObstacle(new Ice(370, 410));
        field5.addObstacle(new Ice(410, 530));
        field5.addObstacle(new Ice(410, 490));
        field5.addObstacle(new Ice(410, 410));

        //right side bottom
        field5.addObstacle(new Ice(450, 570));
        field5.addObstacle(new Ice(450, 530));
        field5.addObstacle(new Ice(450, 490));
        field5.addObstacle(new Ice(450, 450));
        field5.addObstacle(new Ice(450, 410));
        field5.addObstacle(new Ice(490, 570));
        field5.addObstacle(new Ice(490, 530));
        field5.addObstacle(new Ice(490, 490));
        field5.addObstacle(new Ice(490, 450));
        field5.addObstacle(new Ice(530, 570));
        field5.addObstacle(new Ice(530, 530));
        field5.addObstacle(new Ice(530, 450));
        field5.addObstacle(new Ice(570, 570));
        field5.addObstacle(new Ice(570, 530));
        field5.addObstacle(new Ice(570, 490));
        field5.addObstacle(new Ice(570, 450));
        field5.addObstacle(new Ice(610, 570));
        field5.addObstacle(new Ice(610, 530));
        field5.addObstacle(new Ice(610, 490));
        field5.addObstacle(new Ice(650, 570));
        field5.addObstacle(new Ice(650, 490));
        field5.addObstacle(new Ice(690, 570));
        field5.addObstacle(new Ice(690, 530));
        field5.addObstacle(new Ice(690, 490));
        field5.addObstacle(new Ice(730, 570));
        field5.addObstacle(new Ice(730, 530));
        field5.addObstacle(new Ice(770, 530));
        field5.addObstacle(new Ice(810, 570));
        field5.addObstacle(new Ice(810, 530));
        //boxes
        field5.addObstacle(new Ice(650, 250));
        field5.addObstacle(new Ice(690, 250));
        field5.addObstacle(new Ice(730, 250));
        field5.addObstacle(new Ice(650, 290));
        field5.addObstacle(new Ice(650, 330));
        field5.addObstacle(new Ice(690, 330));
        field5.addObstacle(new Ice(730, 290));
        field5.addObstacle(new Ice(730, 330));

        field5.addObstacle(new Ice(90, 250));
        field5.addObstacle(new Ice(130, 250));
        field5.addObstacle(new Ice(170, 250));
        field5.addObstacle(new Ice(90, 290));
        field5.addObstacle(new Ice(90, 330));
        field5.addObstacle(new Ice(130, 330));
        field5.addObstacle(new Ice(170, 290));
        field5.addObstacle(new Ice(170, 330));

// enemies
        Enemy3 a = new Enemy3(1, Color.TRANSPARENT, 1.5, 830, 510, 15, sceneWidth, sceneHeight);

        field5.addEnemies(a);
        field5.addObstacle(a);

        for (int i = 0; i < field5.getList().size(); i++) {
            field5.getRoot().getChildren().add(field5.getList().get(i).getShape());
        }
        for (int i = 0; i < field5.getEnemies().size(); i++) {
            switch (field5.getEnemies().get(i).getEnemyType()) {
                case 1:
                    field5 = addSpoon(field5, (Enemy1) field5.getEnemies().get(i));
                    break;
                case 2:
                    field5 = addBowl(field5, (Enemy2) field5.getEnemies().get(i));
                    break;
                case 3:
                    field5 = addGhost(field5, (Enemy3) field5.getEnemies().get(i));
                    break;
            }
        }

        // fruit
        //top fruit
        field5.addFruits(new Fruit(70, 30, "strawberry"));
        field5.addFruits(new Fruit(190, 70, "cherry"));
        field5.addFruits(new Fruit(310, 110, "strawberry"));
        field5.addFruits(new Fruit(430, 150, "cherry"));
        field5.addFruits(new Fruit(430, 30, "cherry"));
        field5.addFruits(new Fruit(550, 110, "strawberry"));
        field5.addFruits(new Fruit(670, 70, "cherry"));
        field5.addFruits(new Fruit(790, 30, "strawberry"));

        //bottom fruit
        field5.addFruits(new Fruit(70, 590, "strawberry"));
        field5.addFruits(new Fruit(190, 550, "cherry"));
        field5.addFruits(new Fruit(310, 510, "strawberry"));
        field5.addFruits(new Fruit(430, 470, "cherry"));
        field5.addFruits(new Fruit(430, 590, "cherry"));
        field5.addFruits(new Fruit(550, 510, "strawberry"));
        field5.addFruits(new Fruit(670, 550, "cherry"));
        field5.addFruits(new Fruit(790, 590, "strawberry"));

        field5.addFruits(new Fruit(470, 310, "banana"));
        field5.addFruits(new Fruit(390, 310, "banana"));

        field5.addFruits(new Fruit(710, 310, "watermelon"));
        field5.addFruits(new Fruit(150, 310, "watermelon"));

        for (int i = 0; i < field5.getFruits().size(); i++) {
            for (int j = 0; j < field5.getFruits().get(i).getGraphic().getImage().size(); j++) {
                field5.getRoot().getChildren().add(field5.getFruits().get(i).getGraphic().getImage().get(j));
                numFruit = field5.getFruits().size();
            }
        }
        numFruit = field5.getFruits().size();
        //field colour
        field5.getRoot().setStyle("-fx-background-color: gainsboro");

        displaySideBar(field5);
        primaryStage.setTitle("LEVEL 5");
        moveEnemies(field5, primaryStage);
        coordinateKeyInput(primaryStage, field5);
    }

    /**
     * level 4
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayLevel4(Stage primaryStage) throws Exception { // make sure to initialize fruit, ice, and enemies in each level and add new field
         field4 = new Field();
        sceneWidth = 660;
        sceneHeight = 620;
        //level = 4;

        // ice blocks
        field4.addObstacle(new Ice(90, 50));
        field4.addObstacle(new Ice(130, 50));
        field4.addObstacle(new Ice(170, 50));
        field4.addObstacle(new Ice(90, 90));
        field4.addObstacle(new Ice(170, 90));
        field4.addObstacle(new Ice(90, 130));
        field4.addObstacle(new Ice(130, 130));
        field4.addObstacle(new Ice(170, 130));

        field4.addObstacle(new Ice(50, 130));
        field4.addObstacle(new Ice(10, 130));
        field4.addObstacle(new Ice(10, 170));
        field4.addObstacle(new Ice(10, 210));
        field4.addObstacle(new Ice(50, 210));
        field4.addObstacle(new Ice(90, 210));
        field4.addObstacle(new Ice(90, 170));
        field4.addObstacle(new Ice(90, 210));

        field4.addObstacle(new Ice(170, 10));
        field4.addObstacle(new Ice(210, 10));
        field4.addObstacle(new Ice(250, 10));
        field4.addObstacle(new Ice(250, 50));
        field4.addObstacle(new Ice(250, 90));
        field4.addObstacle(new Ice(210, 90));

        field4.addObstacle(new Ice(570, 50));
        field4.addObstacle(new Ice(530, 50));
        field4.addObstacle(new Ice(490, 50));
        field4.addObstacle(new Ice(450, 50));
        field4.addObstacle(new Ice(410, 50));
        field4.addObstacle(new Ice(370, 50));
        field4.addObstacle(new Ice(330, 50));
        field4.addObstacle(new Ice(330, 90));

        field4.addObstacle(new Ice(330, 130));
        field4.addObstacle(new Ice(330, 170));
        field4.addObstacle(new Ice(330, 210));
        field4.addObstacle(new Ice(330, 250));
        field4.addObstacle(new Ice(330, 290));
        field4.addObstacle(new Ice(570, 290));
        field4.addObstacle(new Ice(530, 290));
        field4.addObstacle(new Ice(490, 290));
        field4.addObstacle(new Ice(450, 290));
        field4.addObstacle(new Ice(410, 290));
        field4.addObstacle(new Ice(370, 290));
        field4.addObstacle(new Ice(570, 90));
        field4.addObstacle(new Ice(570, 130));
        field4.addObstacle(new Ice(570, 170));
        field4.addObstacle(new Ice(570, 210));
        field4.addObstacle(new Ice(570, 250));
        field4.addObstacle(new Ice(490, 130));
        field4.addObstacle(new Ice(450, 130));
        field4.addObstacle(new Ice(410, 130));
        field4.addObstacle(new Ice(490, 170));
        field4.addObstacle(new Ice(410, 170));
        field4.addObstacle(new Ice(490, 210));
        field4.addObstacle(new Ice(450, 210));
        field4.addObstacle(new Ice(410, 210));

        field4.addObstacle(new Ice(130, 250));
        field4.addObstacle(new Ice(170, 250));
        field4.addObstacle(new Ice(210, 250));
        field4.addObstacle(new Ice(130, 290));
        field4.addObstacle(new Ice(130, 330));
        field4.addObstacle(new Ice(130, 370));
        field4.addObstacle(new Ice(210, 290));
        field4.addObstacle(new Ice(210, 330));
        field4.addObstacle(new Ice(210, 370));
        field4.addObstacle(new Ice(170, 370));
        field4.addObstacle(new Ice(250, 330));
        field4.addObstacle(new Ice(290, 330));
        field4.addObstacle(new Ice(290, 370));
        field4.addObstacle(new Ice(290, 410));
        field4.addObstacle(new Ice(250, 410));
        field4.addObstacle(new Ice(210, 410));

        field4.addObstacle(new Ice(170, 490));
        field4.addObstacle(new Ice(170, 530));
        field4.addObstacle(new Ice(170, 570));
        field4.addObstacle(new Ice(210, 490));
        field4.addObstacle(new Ice(250, 490));
        field4.addObstacle(new Ice(290, 490));
        field4.addObstacle(new Ice(330, 490));
        field4.addObstacle(new Ice(370, 490));
        field4.addObstacle(new Ice(210, 570));
        field4.addObstacle(new Ice(250, 570));
        field4.addObstacle(new Ice(290, 570));
        field4.addObstacle(new Ice(330, 570));
        field4.addObstacle(new Ice(370, 570));
        field4.addObstacle(new Ice(370, 530));

        field4.addObstacle(new Ice(370, 450));
        field4.addObstacle(new Ice(370, 410));
        field4.addObstacle(new Ice(370, 370));
        field4.addObstacle(new Ice(410, 370));
        field4.addObstacle(new Ice(450, 370));
        field4.addObstacle(new Ice(450, 410));
        field4.addObstacle(new Ice(450, 450));
        field4.addObstacle(new Ice(450, 490));
        field4.addObstacle(new Ice(410, 490));

        field4.addObstacle(new Ice(490, 450));
        field4.addObstacle(new Ice(530, 450));
        field4.addObstacle(new Ice(570, 450));
        field4.addObstacle(new Ice(570, 490));
        field4.addObstacle(new Ice(570, 530));
        field4.addObstacle(new Ice(530, 530));
        field4.addObstacle(new Ice(490, 530));
        field4.addObstacle(new Ice(450, 530));

        field4.addObstacle(new Ice(530, 330));
        field4.addObstacle(new Ice(530, 370));
        field4.addObstacle(new Ice(530, 410));
        field4.addObstacle(new Ice(570, 410));
        field4.addObstacle(new Ice(610, 410));
        field4.addObstacle(new Ice(610, 370));
        field4.addObstacle(new Ice(610, 330));
        field4.addObstacle(new Ice(610, 290));

        field4.addObstacle(new Ice(90, 370));
        field4.addObstacle(new Ice(90, 410));
        field4.addObstacle(new Ice(90, 450));
        field4.addObstacle(new Ice(90, 490));
        field4.addObstacle(new Ice(90, 530));
        field4.addObstacle(new Ice(50, 530));
        field4.addObstacle(new Ice(10, 530));
        field4.addObstacle(new Ice(10, 370));
        field4.addObstacle(new Ice(10, 410));
        field4.addObstacle(new Ice(50, 370));
        field4.addObstacle(new Ice(10, 450));
        field4.addObstacle(new Ice(10, 490));

        // enemies
        Enemy1 a = new Enemy1(Color.TRANSPARENT, 2, 70, 430, 15, sceneWidth, sceneHeight, false);
        Enemy1 b = new Enemy1(Color.TRANSPARENT, 2, 550, 510, 15, sceneWidth, sceneHeight, true);
        Enemy1 c = new Enemy1(Color.TRANSPARENT, 2, 230, 550, 15, sceneWidth, sceneHeight, true);
        Enemy1 d = new Enemy1(Color.TRANSPARENT, 2, 230, 470, 15, sceneWidth, sceneHeight, true);
        Enemy1 e = new Enemy1(Color.TRANSPARENT, 2, 630, 30, 15, sceneWidth, sceneHeight, false);
        Enemy1 f = new Enemy1(Color.TRANSPARENT, 2, 350, 30, 15, sceneWidth, sceneHeight, true);
        Enemy2 g = new Enemy2(3, Color.TRANSPARENT, 2, 550, 110, 15, sceneWidth, sceneHeight);
        Enemy2 h = new Enemy2(4, Color.TRANSPARENT, 2, 390, 270, 15, sceneWidth, sceneHeight);
        Enemy2 k = new Enemy2(1, Color.TRANSPARENT, 2, 30, 30, 15, sceneWidth, sceneHeight);
        Enemy2 l = new Enemy2(3, Color.TRANSPARENT, 2, 190, 350, 15, sceneWidth, sceneHeight);
        Enemy2 m = new Enemy2(3, Color.TRANSPARENT, 2, 430, 470, 15, sceneWidth, sceneHeight);
        field4.addEnemies(a);
        field4.addObstacle(a);
        field4.addEnemies(b);
        field4.addObstacle(b);
        field4.addEnemies(c);
        field4.addObstacle(c);
        field4.addEnemies(d);
        field4.addObstacle(d);
        field4.addEnemies(e);
        field4.addObstacle(e);
        field4.addEnemies(f);
        field4.addObstacle(f);
        field4.addEnemies(g);
        field4.addObstacle(g);
        field4.addEnemies(h);
        field4.addObstacle(h);
        field4.addEnemies(k);
        field4.addObstacle(k);
        field4.addEnemies(l);
        field4.addObstacle(l);
        field4.addEnemies(m);
        field4.addObstacle(m);

        for (int i = 0; i < field4.getList().size(); i++) {
            field4.getRoot().getChildren().add(field4.getList().get(i).getShape());
        }

        for (int i = 0; i < field4.getEnemies().size(); i++) {
            switch (field4.getEnemies().get(i).getEnemyType()) {
                case 1:
                    field4 = addSpoon(field4, (Enemy1) field4.getEnemies().get(i));
                    break;
                case 2:
                    field4 = addBowl(field4, (Enemy2) field4.getEnemies().get(i));
                    break;
                case 3:
                    field4 = addGhost(field4, (Enemy3) field4.getEnemies().get(i));
                    break;
            }

        }

        // fruit
        field4.addFruits(new Fruit(30, 30, "banana"));
        field4.addFruits(new Fruit(150, 110, "cherry"));
        field4.addFruits(new Fruit(70, 190, "watermelon"));
        field4.addFruits(new Fruit(230, 70, "strawberry"));
        field4.addFruits(new Fruit(390, 110, "banana"));
        field4.addFruits(new Fruit(550, 110, "banana"));
        field4.addFruits(new Fruit(390, 270, "banana"));
        field4.addFruits(new Fruit(550, 270, "banana"));
        field4.addFruits(new Fruit(470, 190, "watermelon"));
        field4.addFruits(new Fruit(430, 430, "cherry"));
        field4.addFruits(new Fruit(590, 390, "strawberry"));
        field4.addFruits(new Fruit(510, 510, "watermelon"));
        field4.addFruits(new Fruit(350, 550, "banana"));
        field4.addFruits(new Fruit(230, 550, "watermelon"));
        field4.addFruits(new Fruit(270, 390, "strawberry"));
        field4.addFruits(new Fruit(190, 310, "cherry"));
        field4.addFruits(new Fruit(70, 430, "banana"));
        field4.addFruits(new Fruit(70, 510, "cherry"));

        for (int i = 0; i < field4.getFruits().size(); i++) {
            for (int j = 0; j < field4.getFruits().get(i).getGraphic().getImage().size(); j++) {
                field4.getRoot().getChildren().add(field4.getFruits().get(i).getGraphic().getImage().get(j));
            }
        }
        numFruit = field4.getFruits().size();
        field4.getRoot().setStyle("-fx-background-color: gainsboro");
        //  Color.
        displaySideBar(field4);
        primaryStage.setTitle("LEVEL 4");
        moveEnemies(field4, primaryStage);
        coordinateKeyInput(primaryStage, field4);
    }

    /**
     * level 3
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayLevel3(Stage primaryStage) throws Exception { // make sure to initialize fruit, ice, and enemies in each level and add new field
        field3 = new Field();
        sceneWidth = 620;
        sceneHeight = 540;
        //level = 3;

        // ice blocks
        field3.addObstacle(new Ice(10, 50));
        field3.addObstacle(new Ice(50, 50));
        field3.addObstacle(new Ice(50, 90));
        field3.addObstacle(new Ice(50, 130));
        field3.addObstacle(new Ice(50, 170));
        field3.addObstacle(new Ice(50, 210));
        field3.addObstacle(new Ice(90, 210));
        field3.addObstacle(new Ice(130, 210));
        field3.addObstacle(new Ice(170, 210));
        field3.addObstacle(new Ice(210, 250));
        field3.addObstacle(new Ice(290, 210));
        field3.addObstacle(new Ice(50, 250));
        field3.addObstacle(new Ice(90, 250));
        field3.addObstacle(new Ice(130, 250));
        field3.addObstacle(new Ice(170, 250));
        field3.addObstacle(new Ice(250, 250));
        field3.addObstacle(new Ice(290, 250));
        field3.addObstacle(new Ice(290, 210));
        field3.addObstacle(new Ice(290, 170));
        field3.addObstacle(new Ice(290, 130));
        field3.addObstacle(new Ice(290, 90));
        field3.addObstacle(new Ice(290, 50));
        field3.addObstacle(new Ice(290, 250));
        field3.addObstacle(new Ice(330, 10));
        field3.addObstacle(new Ice(330, 50));
        field3.addObstacle(new Ice(370, 50));
        field3.addObstacle(new Ice(330, 90));
        field3.addObstacle(new Ice(370, 90));
        field3.addObstacle(new Ice(410, 90));
        field3.addObstacle(new Ice(330, 130));
        field3.addObstacle(new Ice(410, 130));
        field3.addObstacle(new Ice(450, 130));
        field3.addObstacle(new Ice(330, 170));
        field3.addObstacle(new Ice(370, 170));
        field3.addObstacle(new Ice(410, 170));
        field3.addObstacle(new Ice(450, 170));
        field3.addObstacle(new Ice(490, 170));
        field3.addObstacle(new Ice(90, 290));
        field3.addObstacle(new Ice(90, 330));
        field3.addObstacle(new Ice(90, 370));
        field3.addObstacle(new Ice(90, 410));
        field3.addObstacle(new Ice(130, 330));
        field3.addObstacle(new Ice(130, 370));
        field3.addObstacle(new Ice(130, 410));
        field3.addObstacle(new Ice(170, 290));
        field3.addObstacle(new Ice(170, 330));
        field3.addObstacle(new Ice(170, 370));
        field3.addObstacle(new Ice(170, 410));
        field3.addObstacle(new Ice(210, 290));
        field3.addObstacle(new Ice(210, 330));
        field3.addObstacle(new Ice(210, 410));
        field3.addObstacle(new Ice(250, 290));
        field3.addObstacle(new Ice(250, 330));
        field3.addObstacle(new Ice(250, 370));
        field3.addObstacle(new Ice(250, 410));
        field3.addObstacle(new Ice(530, 10));
        field3.addObstacle(new Ice(530, 50));
        field3.addObstacle(new Ice(570, 50));
        field3.addObstacle(new Ice(290, 410));
        field3.addObstacle(new Ice(330, 410));
        field3.addObstacle(new Ice(370, 410));
        field3.addObstacle(new Ice(410, 410));
        field3.addObstacle(new Ice(450, 410));
        field3.addObstacle(new Ice(490, 410));
        field3.addObstacle(new Ice(530, 410));
        field3.addObstacle(new Ice(10, 450));
        field3.addObstacle(new Ice(50, 450));
        field3.addObstacle(new Ice(50, 490));

        // enemies
        Enemy3 a = new Enemy3(1, Color.TRANSPARENT, 1.5, 550, 510, 15, sceneWidth, sceneHeight);
        field3.addEnemies(a);
        field3.addObstacle(a);
        for (int i = 0; i < field3.getList().size(); i++) {
            field3.getRoot().getChildren().add(field3.getList().get(i).getShape());
        }

        for (int i = 0; i < field3.getEnemies().size(); i++) {
            switch (field3.getEnemies().get(i).getEnemyType()) {
                case 1:
                    field3 = addSpoon(field3, (Enemy1) field3.getEnemies().get(i));
                    break;
                case 2:
                    field3 = addBowl(field3, (Enemy2) field3.getEnemies().get(i));
                    break;
                case 3:
                    field3 = addGhost(field3, (Enemy3) field3.getEnemies().get(i));
                    break;
            }

        }

        // fruit
        field3.addFruits(new Fruit(30, 30, "banana"));
        field3.addFruits(new Fruit(110, 110, "cherry"));
        field3.addFruits(new Fruit(590, 30, "cherry"));
        field3.addFruits(new Fruit(390, 150, "banana"));
        field3.addFruits(new Fruit(150, 310, "strawberry"));
        field3.addFruits(new Fruit(230, 390, "cherry"));
        field3.addFruits(new Fruit(30, 510, "strawberry"));
        field3.addFruits(new Fruit(310, 30, "cherry"));
        field3.addFruits(new Fruit(430, 350, "strawberry"));
        field3.addFruits(new Fruit(270, 510, "strawberry"));
        field3.addFruits(new Fruit(470, 510, "banana"));

        for (int i = 0; i < field3.getFruits().size(); i++) {
            //  field3.getRoot().getChildren().add(field2.getFruits().get(i).getFruit());
            for (int j = 0; j < field3.getFruits().get(i).getGraphic().getImage().size(); j++) {
                field3.getRoot().getChildren().add(field3.getFruits().get(i).getGraphic().getImage().get(j));
            }
        }
        numFruit = field3.getFruits().size();
        field3.getRoot().setStyle("-fx-background-color: gainsboro");
        //  Color.
        displaySideBar(field3);
        primaryStage.setTitle("LEVEL 3");
        moveEnemies(field3, primaryStage);
        coordinateKeyInput(primaryStage, field3);
    }

    /**
     * level 2
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayLevel2(Stage primaryStage) throws Exception { // make sure to initialize fruit, ice, and enemies in each level and add new field
        field2 = new Field();
        sceneWidth = 540;
        sceneHeight = 450;
        //level = 2;

        // ice blocks
        field2.addObstacle(new Ice(10, 10));
        field2.addObstacle(new Ice(10, 50));
        field2.addObstacle(new Ice(90, 10));
        field2.addObstacle(new Ice(130, 10));
        field2.addObstacle(new Ice(170, 10));
        field2.addObstacle(new Ice(330, 10));
        field2.addObstacle(new Ice(370, 10));
        field2.addObstacle(new Ice(410, 10));
        field2.addObstacle(new Ice(330, 50));
        field2.addObstacle(new Ice(410, 50));
        field2.addObstacle(new Ice(450, 50));
        field2.addObstacle(new Ice(490, 50));
        field2.addObstacle(new Ice(10, 90));
        field2.addObstacle(new Ice(50, 130));
        field2.addObstacle(new Ice(90, 130));
        field2.addObstacle(new Ice(130, 130));
        field2.addObstacle(new Ice(170, 130));
        field2.addObstacle(new Ice(90, 170));
        field2.addObstacle(new Ice(90, 210));
        field2.addObstacle(new Ice(130, 210));
        field2.addObstacle(new Ice(170, 170));
        field2.addObstacle(new Ice(170, 210));
        field2.addObstacle(new Ice(170, 250));
        field2.addObstacle(new Ice(210, 250));
        field2.addObstacle(new Ice(250, 250));
        field2.addObstacle(new Ice(290, 250));
        field2.addObstacle(new Ice(330, 250));
        field2.addObstacle(new Ice(330, 290));
        field2.addObstacle(new Ice(330, 330));
        field2.addObstacle(new Ice(330, 370));
        field2.addObstacle(new Ice(170, 250));
        field2.addObstacle(new Ice(170, 290));
        field2.addObstacle(new Ice(170, 330));
        field2.addObstacle(new Ice(170, 370));
        field2.addObstacle(new Ice(10, 370));
        field2.addObstacle(new Ice(50, 370));
        field2.addObstacle(new Ice(50, 410));
        field2.addObstacle(new Ice(410, 250));
        field2.addObstacle(new Ice(410, 290));
        field2.addObstacle(new Ice(410, 330));
        field2.addObstacle(new Ice(450, 330));
        field2.addObstacle(new Ice(450, 250));
        field2.addObstacle(new Ice(450, 250));
        field2.addObstacle(new Ice(490, 250));
        field2.addObstacle(new Ice(170, 410));
        field2.addObstacle(new Ice(330, 410));

        // enemies
        Enemy2 a = new Enemy2(4, Color.TRANSPARENT, 2, 230, 430, 15, sceneWidth, sceneHeight);
        Enemy2 b = new Enemy2(3, Color.TRANSPARENT, 2, 470, 180, 15, sceneWidth, sceneHeight);
        Enemy2 c = new Enemy2(1, Color.TRANSPARENT, 2, 70, 270, 15, sceneWidth, sceneHeight);
        Enemy2 d = new Enemy2(2, Color.TRANSPARENT, 2, 470, 30, 15, sceneWidth, sceneHeight);
        field2.addObstacle(a);
        field2.addEnemies(a);
        field2.addObstacle(b);
        field2.addEnemies(b);
        field2.addObstacle(c);
        field2.addEnemies(c);
        field2.addObstacle(d);
        field2.addEnemies(d);

        for (int i = 0; i < field2.getList().size(); i++) {
            field2.getRoot().getChildren().add(field2.getList().get(i).getShape());
        }

        for (int i = 0; i < field2.getEnemies().size(); i++) {
            switch (field2.getEnemies().get(i).getEnemyType()) {
                case 1:
                    field2 = addSpoon(field2, (Enemy1) field2.getEnemies().get(i));
                    break;
                case 2:
                    field2 = addBowl(field2, (Enemy2) field2.getEnemies().get(i));
                    break;
                case 3:
                    field2 = addGhost(field2, (Enemy3) field2.getEnemies().get(i));
                    break;
            }

        }

        // fruit
        field2.addFruits(new Fruit(70, 30, "watermelon"));
        field2.addFruits(new Fruit(30, 430, "watermelon"));
        field2.addFruits(new Fruit(470, 30, "watermelon"));
        field2.addFruits(new Fruit(30, 150, "watermelon"));
        field2.addFruits(new Fruit(150, 190, "watermelon"));
        field2.addFruits(new Fruit(310, 310, "watermelon"));
        field2.addFruits(new Fruit(470, 310, "watermelon"));
        field2.addFruits(new Fruit(350, 110, "watermelon"));

        for (int i = 0; i < field2.getFruits().size(); i++) {
            //  field2.getRoot().getChildren().add(field2.getFruits().get(i).getFruit());
            for (int j = 0; j < field2.getFruits().get(i).getGraphic().getImage().size(); j++) {
                field2.getRoot().getChildren().add(field2.getFruits().get(i).getGraphic().getImage().get(j));
            }
        }
        numFruit = field2.getFruits().size();
        field2.getRoot().setStyle("-fx-background-color: gainsboro");
        displaySideBar(field2);
        primaryStage.setTitle("LEVEL 2");
        moveEnemies(field2, primaryStage);
        coordinateKeyInput(primaryStage, field2);
    }

    /**
     * level 1
     *
     * @param primaryStage
     * @throws Exception
     */
    public void displayLevel1(Stage primaryStage) throws Exception { // make sure to initialize fruit, ice, and enemies in each level and add new field
        field1 = new Field();
        sceneWidth = 500;
        sceneHeight = 410;
        // level = 1;

        // ice blocks
        field1.addObstacle(new Ice(50, 10));//(x,y)
        field1.addObstacle(new Ice(90, 10));
        field1.addObstacle(new Ice(130, 10));
        field1.addObstacle(new Ice(10, 50));
        field1.addObstacle(new Ice(10, 90));
        field1.addObstacle(new Ice(10, 130));
        field1.addObstacle(new Ice(170, 130));
        field1.addObstacle(new Ice(210, 130));
        field1.addObstacle(new Ice(250, 130));
        field1.addObstacle(new Ice(290, 130));
        field1.addObstacle(new Ice(330, 10));
        field1.addObstacle(new Ice(370, 10));
        field1.addObstacle(new Ice(410, 10));
        field1.addObstacle(new Ice(450, 50));
        field1.addObstacle(new Ice(450, 90));
        field1.addObstacle(new Ice(450, 130));
        field1.addObstacle(new Ice(170, 250));
        field1.addObstacle(new Ice(210, 250));
        field1.addObstacle(new Ice(250, 250));
        field1.addObstacle(new Ice(290, 250));
        field1.addObstacle(new Ice(10, 250));
        field1.addObstacle(new Ice(10, 290));
        field1.addObstacle(new Ice(10, 330));
        field1.addObstacle(new Ice(50, 370));
        field1.addObstacle(new Ice(90, 370));
        field1.addObstacle(new Ice(130, 370));
        field1.addObstacle(new Ice(330, 370));
        field1.addObstacle(new Ice(370, 370));
        field1.addObstacle(new Ice(410, 370));
        field1.addObstacle(new Ice(450, 330));
        field1.addObstacle(new Ice(450, 290));
        field1.addObstacle(new Ice(450, 250));

        // enemies
        Enemy1 a = new Enemy1(Color.BLUE, 1.5, 70, 70, 15, sceneWidth, sceneHeight, false);
        field1.addObstacle(a);
        field1.addEnemies(a);
        Enemy1 b = new Enemy1(Color.BLUE, -1.5, 430, 350, 15, sceneWidth, sceneHeight, false);
        field1.addObstacle(b);
        field1.addEnemies(b);
        Enemy1 c = new Enemy1(Color.BLUE, 1.5, 70, 310, 15, sceneWidth, sceneHeight, true);
        field1.addObstacle(c);
        field1.addEnemies(c);

        for (int i = 0; i < field1.getList().size(); i++) {
            field1.getRoot().getChildren().add(field1.getList().get(i).getShape());
        }

        for (int i = 0; i < field1.getEnemies().size(); i++) {
            switch (field1.getEnemies().get(i).getEnemyType()) {
                case 1:
                    field1 = addSpoon(field1, (Enemy1) field1.getEnemies().get(i));
                    break;
                case 2:
                    field1 = addBowl(field1, (Enemy2) field1.getEnemies().get(i));
                    break;
                case 3:
                    field1 = addGhost(field1, (Enemy3) field1.getEnemies().get(i));
                    break;
            }

        }

        // fruit
        field1.addFruits(new Fruit(30, 30, "cherry"));
        field1.addFruits(new Fruit(470, 30, "cherry"));
        field1.addFruits(new Fruit(30, 390, "cherry"));
        field1.addFruits(new Fruit(470, 390, "cherry"));
        field1.addFruits(new Fruit(300, 310, "cherry"));

        for (int i = 0; i < field1.getFruits().size(); i++) {
            //field1.getFruits().get(i).get
            for (int j = 0; j < field1.getFruits().get(i).getGraphic().getImage().size(); j++) {
                field1.getRoot().getChildren().add(field1.getFruits().get(i).getGraphic().getImage().get(j));
            }
        }
        numFruit = field1.getFruits().size();
        field1.getRoot().setStyle("-fx-background-color: gainsboro");
        //  Color.
        displaySideBar(field1);
        primaryStage.setTitle("LEVEL 1");
        moveEnemies(field1, primaryStage);
        coordinateKeyInput(primaryStage, field1);
    }

    /**
     * checks if enemy has collided with anything
     *
     * @param y
     * @param field
     * @param primaryStage
     * @return
     */
    public boolean checkEnemyCollisions(Enemy y, Field field, Stage primaryStage) {
        for (Obstacle x : field.getList()) { // if enemy collides with other obstacles
            if (x != y && y.getShape().intersects(x.getShape().getBoundsInLocal())) {
                if (y.getEnemyType() == 3 && x.getObstacleType().equalsIgnoreCase("ice")) {
                    useGhostBreak((Enemy3) y, (Ice) x, field);
                } else if (y.getEnemyType() == 2) {
                    moveEnemy2((Enemy2) y, field, x);
                }

                return true;
            }
        }

        if (y.getShape().intersects(iceCream.getGraphic().getCir().getBoundsInParent())) { // if enemy collides with ice cream
            iceCream.getGraphic().getCir().setCenterX(247);
            iceCream.getGraphic().getCir().setCenterY(205);
            iceCream.getGraphic().moveGraphic();

            Rectangle rect = new Rectangle(217, 195, 80, 20);
            for (int j = 0; j < field.getList().size(); j++) {
                for (int i = 0; i < field.getList().size(); i++) {
                    if (field.getList().get(i).getShape().intersects(rect.getBoundsInLocal())) {
                        field.getRoot().getChildren().remove(field.getList().get(i).getShape());
                        field.getList().remove(i);
                    }
                }
            }

            iceCream.setLife(iceCream.getLife() - 1);
            displaySideBar(field);
            if (iceCream.getLife() == 0) {
                stopGame(primaryStage);
            } else {

                // resets positions
                for (int i = 0; i < field.getEnemies().size(); i++) {
                    field.getEnemies().get(i).getGraphic().resetPositions();
                }

                // pause to reset level after losing life
                animator.stop();
                timeline.stop();
                PauseTransition resetLevel = new PauseTransition(Duration.seconds(1));
                resetLevel.setOnFinished(e -> {
                    animator.start();
                    timeline.play();
                });
                resetLevel.play();
            }

            return true;
        }

        return false; // no collision
    }

    /**
     * moves enemies
     *
     * @param field
     * @param primaryStage
     */
    public void moveEnemies(Field field, Stage primaryStage) {
        animator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                for (int i = 0; i < field.getEnemies().size(); i++) {

                    if (field.getEnemies().get(i).getEnemyType() == 3) {
                        followPlayer((Enemy3) field.getEnemies().get(i), field);
                    }

                    if (checkEnemyCollisions(field.getEnemies().get(i), field, primaryStage)) {
                        if (field.getEnemies().get(i).getEnemyType() == 1) {
                            field.getEnemies().get(i).setSpeed(field.getEnemies().get(i).getSpeed() * -1);
                        }
                    }
                    field.getEnemies().get(i).move();
                }

            }
        };

        animator.start();
    }

    /**
     * enemy2/bowler's behavior upon hitting obstacles (called in
     * checkEnemyCollisions)
     *
     * @param a
     * @param field
     * @param b
     */
    public void moveEnemy2(Enemy2 a, Field field, Obstacle b) {
        if (b.getObstacleType().equalsIgnoreCase("ice")) { // if ice is hit, reposition, then change direction
            if (b.getX() > a.getGraphic().getBase().getCenterX() && a.getDirection() == 1) {
                a.getGraphic().getBase().setCenterX(b.getX() - 16);
                a.changeDirection();
            } else if (b.getX() < a.getGraphic().getBase().getCenterX() && a.getDirection() == 2) {
                a.getGraphic().getBase().setCenterX(b.getX() + 55);
                a.changeDirection();
            } else if (b.getY() > a.getGraphic().getBase().getCenterY() && a.getDirection() == 3) {
                a.getGraphic().getBase().setCenterY(b.getY() - 16);
                a.changeDirection();
            } else if (b.getY() < a.getGraphic().getBase().getCenterY() && a.getDirection() == 4) {
                a.getGraphic().getBase().setCenterY(b.getY() + 55);
                a.changeDirection();
            }
        } else if (b.getObstacleType().equalsIgnoreCase("enemy")) { // if another enemy is hit, go in the opposite direction
            switch (a.getDirection()) {
                case 1:
                    a.setDirection(2);
                    break;
                case 2:
                    a.setDirection(1);
                    break;
                case 3:
                    a.setDirection(4);
                    break;
                case 4:
                    a.setDirection(3);
                    break;
            }
        }
        a.move();
    }

    /**
     * enemy3/ghost's behavior
     *
     * @param a
     * @param field
     */
    public void followPlayer(Enemy3 a, Field field) {
        // the range of values in which enemy3 can intersect the ice cream man
        int minX = (int) iceCream.getGraphic().getCir().getCenterX() - 3; // hitting from the left
        int maxX = (int) iceCream.getGraphic().getCir().getCenterX() + 3; // hitting from the right
        int minY = (int) iceCream.getGraphic().getCir().getCenterY() - 3; // hitting from above
        int maxY = (int) iceCream.getGraphic().getCir().getCenterY() + 3; // hitting from below

        if (a.getShape().getCenterY() > maxY) {
            a.setDirection(4); // move up
        } else if (a.getShape().getCenterY() < minY) {
            a.setDirection(3); // move down
        } else if (a.getShape().getCenterX() > maxX) {
            a.setDirection(2); // move left
        } else if (a.getShape().getCenterX() < minX) {
            a.setDirection(1); // move right
        }

        a.move();
    }

    /**
     * enemy3/ghost icebreaking ability
     *
     * @param a
     * @param block
     * @param field
     */
    public void useGhostBreak(Enemy3 a, Ice block, Field field) {
        field.getRoot().getChildren().remove(field.getList().get(field.getList().indexOf(block)).getShape());
        field.getList().remove(field.getList().indexOf(block));

        // delay after each ice break
        animator.stop();
        PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
        if (level == 5) {
            pause.setDuration(Duration.seconds(0.5));
        }
        pause.setOnFinished(e -> animator.start());
        pause.play();

    }

    /**
     * adds spoon graphic to field
     *
     * @param field
     * @param e
     * @return field (with spoon graphic))
     */
    public Field addSpoon(Field field, Enemy1 e) {
        for (int i = 0; i < e.getGraphic().getSpoon().size(); i++) {
            field.getRoot().getChildren().add(e.getGraphic().getSpoon().get(i));
        }
        field.getRoot().getChildren().add(e.getGraphic().getLeftBrow());
        field.getRoot().getChildren().add(e.getGraphic().getRightBrow());
        field.getRoot().getChildren().add(e.getGraphic().getMouth());
        return field;
    }

    /**
     * adds bowl graphic to field
     *
     * @param field
     * @param e
     * @return field (with bowl graphic)
     */
    public Field addBowl(Field field, Enemy2 e) {
        field.getRoot().getChildren().add(e.getGraphic().getInside());
        field.getRoot().getChildren().add(e.getGraphic().getOutside());
        field.getRoot().getChildren().add(e.getGraphic().getLeftEye());
        field.getRoot().getChildren().add(e.getGraphic().getRightEye());
        field.getRoot().getChildren().add(e.getGraphic().getLeftBrow());
        field.getRoot().getChildren().add(e.getGraphic().getRightBrow());
        return field;
    }

    /**
     * adds ghost graphic to field
     *
     * @param field
     * @param e
     * @return field (with ghost graphic)
     */
    public Field addGhost(Field field, Enemy3 e) {
        field.getRoot().getChildren().add(e.getGraphic().getCone());

        for (int i = 0; i < e.getGraphic().getMeltedOutline().size(); i++) {
            field.getRoot().getChildren().add(e.getGraphic().getMeltedOutline().get(i));
        }

        field.getRoot().getChildren().add(e.getGraphic().getIceCream());

        for (int i = 0; i < e.getGraphic().getMelted().size(); i++) {
            field.getRoot().getChildren().add(e.getGraphic().getMelted().get(i));
        }

        field.getRoot().getChildren().add(e.getGraphic().getLeftEye());
        field.getRoot().getChildren().add(e.getGraphic().getRightEye());
        field.getRoot().getChildren().add(e.getGraphic().getMouth());

        return field;
    }

    /**
     * checks if the player has completed the level; transitions to next level
     *
     * @param field
     * @param primaryStage
     * @throws Exception
     */
    public void checkWin(Field field, Stage primaryStage) throws Exception {
        level = level + 1;
        timeline.stop();
        animator.stop();
        loadingBarAnimator.stop();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        switch (level) {
            case 2:
                running1 = false;
                // running2 = true;
                displayLoading(primaryStage);
                pause.setOnFinished(e -> {
                    try {
                        displayLevel2(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pause.play();
                break;
            case 3:
                running2 = false;
                //running3 = true;
                displayLoading(primaryStage);
                pause.setOnFinished(e -> {
                    try {
                        displayLevel3(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pause.play();
                break;
            case 4:
                running3 = false;
                //   running4 = true;
                displayLoading(primaryStage);
                pause.setOnFinished(e -> {
                    try {
                        displayLevel4(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pause.play();
                break;
            case 5:
                running4 = false;
                // running5= true;
                displayLoading(primaryStage);
                pause.setOnFinished(e -> {
                    try {
                        displayLevel5(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pause.play();
                break;
            case 6:
                running5 = false;
                displayWin(primaryStage);
            default:
                break;
        }
    }

    /**
     * checks if the ice cream man has collected any fruit
     *
     * @param field
     */
    public void checkFruit(Field field) {
        for (int i = 0; i < field.getFruits().size(); i++) {
            if (field.getFruits().get(i).getFruit().intersects(iceCream.getGraphic().getCir().getBoundsInParent()) || iceCream.getGraphic().getCir().intersects(field.getFruits().get(i).getFruit().getBoundsInParent())) {
                for (int j = 0; j < field.getFruits().get(i).getGraphic().getImage().size(); j++) {
                    field.getRoot().getChildren().remove(field.getFruits().get(i).getGraphic().getImage().get(j));
                }
                numFruit = numFruit - 1;
                field.getFruits().remove(i);

                if (level == 5) {
                    field.getEnemies().get(0).setSpeed(field.getEnemies().get(0).getSpeed() + 0.075);
                }
            }
        }
    }

    /**
     * description: checks collisions with ice cream man
     *
     * @param field
     * @return
     */
    public boolean checkCollisions(Field field) {
        for (int i = 0; i < field.getList().size(); i++) {
            if (field.getList().get(i).getShape().intersects(iceCream.getGraphic().getCir().getBoundsInParent())) {
                // put if statement checking if the thing the ice cream is colliding with is an enemy, then subtract life
                return true;
            }
        }
        return false;
    }

    /**
     * move ice cream man left
     *
     * @param field
     * @param primaryStage
     */
    public void moveLeft(Field field, Stage primaryStage) {
        if (iceCream.getGraphic().getCir().getCenterX() - 5 >= 10) {//call method which detects collision with ice or wall
            iceCream.getGraphic().getCir().setCenterX(iceCream.getGraphic().getCir().getCenterX() - 5);
            iceCream.getGraphic().getCir().setFill(Color.LIGHTCORAL);
            if (checkCollisions(field)) {
                iceCream.getGraphic().getCir().setCenterX(iceCream.getGraphic().getCir().getCenterX() + 5);
            }
            checkFruit(field);
            if (level != 0) {
                try {

                    if (numFruit < 1) {
                        checkWin(field, primaryStage);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * move ice cream man right
     *
     * @param field
     * @param primaryStage
     */
    public void moveRight(Field field, Stage primaryStage) {
        if ((iceCream.getGraphic().getCir().getCenterX() + 5 <= (sceneWidth - 15))) {//call method that detects collision with ice or wall
            iceCream.getGraphic().getCir().setCenterX(iceCream.getGraphic().getCir().getCenterX() + 5);
            iceCream.getGraphic().getCir().setFill(Color.LIGHTCORAL);
            if (checkCollisions(field)) {
                iceCream.getGraphic().getCir().setCenterX(iceCream.getGraphic().getCir().getCenterX() - 5);
            }
            checkFruit(field);
            if (level != 0) {
                try {

                    if (numFruit < 1) {
                        checkWin(field, primaryStage);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * move ice cream man up
     *
     * @param field
     * @param primaryStage
     */
    public void moveUp(Field field, Stage primaryStage) {
        if ((iceCream.getGraphic().getCir().getCenterY() - 5 >= 0)) {
            iceCream.getGraphic().getCir().setCenterY(iceCream.getGraphic().getCir().getCenterY() - 5);
            iceCream.getGraphic().getCir().setFill(Color.LIGHTCORAL);
            if (checkCollisions(field)) {
                iceCream.getGraphic().getCir().setCenterY(iceCream.getGraphic().getCir().getCenterY() + 5);
            }
            checkFruit(field);
            if (level != 0) {
                try {

                    if (numFruit < 1) {
                        checkWin(field, primaryStage);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * move ice cream man down
     *
     * @param field
     * @param primaryStage
     */
    public void moveDown(Field field, Stage primaryStage) {
        if ((iceCream.getGraphic().getCir().getCenterY() + 5 <= (sceneHeight - 20))) {
            iceCream.getGraphic().getCir().setCenterY(iceCream.getGraphic().getCir().getCenterY() + 5);
            iceCream.getGraphic().getCir().setFill(Color.LIGHTCORAL);
            if (checkCollisions(field)) {
                iceCream.getGraphic().getCir().setCenterY(iceCream.getGraphic().getCir().getCenterY() - 5);
            }
            checkFruit(field);
            if (level != 0) {
                try {

                    if (numFruit < 1) {
                        checkWin(field, primaryStage);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(BadIceCream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * break ice above
     *
     * @param field
     * @param primaryStage
     */
    public void breakUp(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int x1 = x;
        int x2 = x;

        while ((x1 - 10) % 40 != 0 && (x2 - 10) % 40 != 0) {
            x1 = x1 + 1;
            x2 = x2 - 1;
        }
        if ((x1 - 10) % 40 == 0) {
            x = x1;
        } else {
            x = x2;
        }
        for (int i = 0; i < field.getList().size(); i++) {
            //System.out.println(field.getList().get(i).getX());

            if (field.getList().get(i).getX() == x && field.getList().get(i).getY() < y) {
                field.getRoot().getChildren().remove(field.getList().get(i).getShape());
                field.getList().remove(i);
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * break ice below
     *
     * @param field
     * @param primaryStage
     */
    public void breakDown(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int x1 = x;
        int x2 = x;

        while ((x1 - 10) % 40 != 0 && (x2 - 10) % 40 != 0) {
            x1 = x1 + 1;
            x2 = x2 - 1;
        }
        if ((x1 - 10) % 40 == 0) {
            x = x1;
        } else {
            x = x2;
        }
        for (int i = 0; i < field.getList().size(); i++) {
            //System.out.println(field.getList().get(i).getX());

            if (field.getList().get(i).getX() == x && field.getList().get(i).getY() > y) {
                field.getRoot().getChildren().remove(field.getList().get(i).getShape());
                field.getList().remove(i);
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * break ice to the left
     *
     * @param field
     * @param primaryStage
     */
    public void breakLeft(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int y1 = y;
        int y2 = y;
        while ((y1 - 10) % 40 != 0 && (y2 - 10) % 40 != 0) {
            y1 = y1 + 1;
            y2 = y2 - 1;
        }

        if ((y1 - 10) % 40 == 0) {
            y = y1;
        } else {
            y = y2;
        }
        while ((x - 10) % 40 != 0) {
            x = x - 1;
        }
        for (int i = 0; i < field.getList().size(); i++) {
            //System.out.println(field.getList().get(i).getX());

            if (field.getList().get(i).getY() == y && field.getList().get(i).getX() < x) {
                field.getRoot().getChildren().remove(field.getList().get(i).getShape());
                field.getList().remove(i);
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * break ice to the right
     *
     * @param field
     * @param primaryStage
     */
    public void breakRight(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int y1 = y;
        int y2 = y;
        while ((y1 - 10) % 40 != 0 && (y2 - 10) % 40 != 0) {
            y1 = y1 + 1;
            y2 = y2 - 1;
        }

        if ((y1 - 10) % 40 == 0) {
            y = y1;
        } else {
            y = y2;
        }
        while ((x - 10) % 40 != 0) {
            x = x - 1;
        }
        for (int i = 0; i < field.getList().size(); i++) {
            //System.out.println(field.getList().get(i).getX());

            if (field.getList().get(i).getY() == y && field.getList().get(i).getX() > x) {
                field.getRoot().getChildren().remove(field.getList().get(i).getShape());
                field.getList().remove(i);
            }
        }
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * make ice above
     *
     * @param field
     * @param primaryStage
     */
    public void makeUp(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int x1 = x;
        int x2 = x;
        while ((x1 - 10) % 40 != 0 && (x2 - 10) % 40 != 0) {
            x1 = x1 + 1;
            x2 = x2 - 1;
        }

        if ((x1 - 10) % 40 == 0) {
            x = x1;
        } else {
            x = x2;
        }
        while ((y - 10) % 40 != 0) {
            y = y - 1;
        }
        do {
            y = y - 40;

            field.addObstacle(new Ice(x, y));
            boolean possible = true;
            for (int i = 0; i < field.getList().size() - 1; i++) { // if new ice intersects with any obstacle in the field 
                if (field.getList().get(field.getList().size() - 1).getShape().intersects(field.getList().get(i).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                } else if (field.getList().get(i).getShape().intersects(field.getList().get(field.getList().size() - 1).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                }
            }
            if (possible) {
                field.getRoot().getChildren().add(field.getList().get(field.getList().size() - 1).getShape());
            } else { // stop making ice
                break;
            }
        } while (y - 40 > 0);
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * make ice below
     *
     * @param field
     * @param primaryStage
     */
    public void makeDown(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int x1 = x;
        int x2 = x;
        while ((x1 - 10) % 40 != 0 && (x2 - 10) % 40 != 0) {
            x1 = x1 + 1;
            x2 = x2 - 1;
        }
        if ((x1 - 10) % 40 == 0) {
            x = x1;
        } else {
            x = x2;
        }
        while ((y - 10) % 40 != 0) {
            y = y + 1;
        }
        do {
            y = y + 40;
            field.addObstacle(new Ice(x, y));
            boolean possible = true;
            for (int i = 0; i < field.getList().size() - 1; i++) { // if new ice intersects with any obstacle in the field 
                if (field.getList().get(field.getList().size() - 1).getShape().intersects(field.getList().get(i).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                } else if (field.getList().get(i).getShape().intersects(field.getList().get(field.getList().size() - 1).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                }
            }
            if (possible) {
                field.getRoot().getChildren().add(field.getList().get(field.getList().size() - 1).getShape());
            } else { // stop making ice
                break;
            }
        } while (y + 40 < sceneHeight);
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * make ice to the left
     *
     * @param field
     * @param primaryStage
     */
    public void makeLeft(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int y1 = y;
        int y2 = y;
        while ((y1 - 10) % 40 != 0 && (y2 - 10) % 40 != 0) {
            y1 = y1 + 1;
            y2 = y2 - 1;
        }

        if ((y1 - 10) % 40 == 0) {
            y = y1;
        } else {
            y = y2;
        }
        while ((x - 10) % 40 != 0) {
            x = x - 1;
        }
        do {
            x = x - 40;
            field.addObstacle(new Ice(x, y));
            boolean possible = true;
            for (int i = 0; i < field.getList().size() - 1; i++) { // if new ice intersects with any obstacle in the field 
                if (field.getList().get(field.getList().size() - 1).getShape().intersects(field.getList().get(i).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                } else if (field.getList().get(i).getShape().intersects(field.getList().get(field.getList().size() - 1).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                }
            }
            if (possible) {
                field.getRoot().getChildren().add(field.getList().get(field.getList().size() - 1).getShape());
            } else { // stop making ice
                break;
            }
        } while (x - 40 > 0);
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * make ice to the right
     *
     * @param field
     * @param primaryStage
     */
    public void makeRight(Field field, Stage primaryStage) {
        int x = (int) (iceCream.getGraphic().getCir().getCenterX() - iceCream.getGraphic().getCir().getRadius());
        int y = (int) (iceCream.getGraphic().getCir().getCenterY() - iceCream.getGraphic().getCir().getRadius());
        int y1 = y;
        int y2 = y;
        while ((y1 - 10) % 40 != 0 && (y2 - 10) % 40 != 0) {
            y1 = y1 + 1;
            y2 = y2 - 1;
        }

        if ((y1 - 10) % 40 == 0) {
            y = y1;
        } else {
            y = y2;
        }
        while ((x - 10) % 40 != 0) {
            x = x + 1;
        }
        do {
            x = x + 40;
            field.addObstacle(new Ice(x, y));
            boolean possible = true;
            for (int i = 0; i < field.getList().size() - 1; i++) { // if new ice intersects with any obstacle in the field 
                if (field.getList().get(field.getList().size() - 1).getShape().intersects(field.getList().get(i).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                } else if (field.getList().get(i).getShape().intersects(field.getList().get(field.getList().size() - 1).getShape().getBoundsInParent())) {
                    field.getList().remove(field.getList().size() - 1);
                    possible = false;
                    break;
                }

            }
            if (possible) {
                field.getRoot().getChildren().add(field.getList().get(field.getList().size() - 1).getShape());
            } else { // stop making ice
                break;
            }
        } while (x + 40 < sceneWidth);
        iceCream.getGraphic().moveGraphic();
    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCreamPractice(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!runningPractice) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCream1(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running1) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCream2(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running2) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCream3(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running3) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCream4(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running4) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * listens to keyboard and moves ice cream man accordingly
     *
     * @param field
     * @param primaryStage
     */
    public void moveIceCream5(Field field, Stage primaryStage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running5) {
                return;
            }
            // System.out.println("keySW: "+field.getFruits().size());
            switch (action) {
                case LEFT:
                    moveLeft(field, primaryStage);
                    break;
                case RIGHT:
                    moveRight(field, primaryStage);
                    break;
                case UP:
                    moveUp(field, primaryStage);
                    break;
                case DOWN:
                    moveDown(field, primaryStage);
                    break;
                case BREAKUP:
                    breakUp(field, primaryStage);
                    break;
                case BREAKDOWN: // mood
                    breakDown(field, primaryStage);
                    break;
                case BREAKLEFT:
                    breakLeft(field, primaryStage);
                    break;
                case BREAKRIGHT:
                    breakRight(field, primaryStage);
                    break;
                case MAKEUP:
                    makeUp(field, primaryStage);
                    break;
                case MAKEDOWN:
                    makeDown(field, primaryStage);
                    break;
                case MAKELEFT:
                    makeLeft(field, primaryStage);
                    break;
                case MAKERIGHT:
                    makeRight(field, primaryStage);
                    break;
                case NONE:
                    break;
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        field.getRoot().getChildren().add(iceCream.getGraphic().getCir());//applies layout to text object
        field.getRoot().getChildren().add(iceCream.getGraphic().getCone());

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopOutline());

        for (int i = 0; i < iceCream.getGraphic().getSwirl().size(); i++) {
            if (i < iceCream.getGraphic().getSwirlOutline().size()) {
                field.getRoot().getChildren().add(iceCream.getGraphic().getSwirlOutline().get(i));
            }
            field.getRoot().getChildren().add(iceCream.getGraphic().getSwirl().get(i));
        }

        field.getRoot().getChildren().add(iceCream.getGraphic().getTopRight());
        field.getRoot().getChildren().add(iceCream.getGraphic().getTopMouth());
        field.getRoot().getChildren().add(iceCream.getGraphic().getBottomMouth());

    }

    /**
     * connects keyboard input to the appropriate movement
     */
    public enum UserAction {
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN,
        BREAKUP,
        BREAKLEFT,
        BREAKRIGHT,
        BREAKDOWN,
        MAKEUP,
        MAKEDOWN,
        MAKELEFT,
        MAKERIGHT
    }

    private void stopGame(Stage primaryStage) {

        running1 = false;
        running2 = false;
        running3 = false;
        running4 = false;
        running5 = false;
        timeline.stop();
        animator.stop();

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> displayLose(primaryStage));
        pause.play();
    }

    private void startGame() {
        iceCream.getGraphic().getCir().setCenterX(247);
        iceCream.getGraphic().getCir().setCenterY(205);
        iceCream.getGraphic().moveGraphic();
        timeline.play();
        if (level == 1) {
            running1 = true;
        } else if (level == 2) {
            running2 = true;
        } else if (level == 3) {
            running3 = true;
        } else if (level == 4) {
            running4 = true;
        } else if (level == 5) {
            running5 = true;
        }
    }

    /**
     * coordinates key input with desired directions
     *
     * @param primaryStage
     * @param field
     * @throws Exception
     */
    public void coordinateKeyInput(Stage primaryStage, Field field) throws Exception {
        //System.out.println(field.getFruits().size());
        KeyCombination makeUp = new KeyCodeCombination(KeyCode.W, KeyCodeCombination.SHIFT_DOWN);
        KeyCombination makeDown = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.SHIFT_DOWN);
        KeyCombination makeLeft = new KeyCodeCombination(KeyCode.A, KeyCodeCombination.SHIFT_DOWN);
        KeyCombination makeRight = new KeyCodeCombination(KeyCode.D, KeyCodeCombination.SHIFT_DOWN);

        switch (level) {
            case 1:
                moveIceCream1(field, primaryStage);
                break;
            case 2:
                moveIceCream2(field, primaryStage);
                break;
            case 3:
                moveIceCream3(field, primaryStage);
                break;
            case 4:
                moveIceCream4(field, primaryStage);
                break;
            case 5:
                moveIceCream5(field, primaryStage);
                break;
            default:
                moveIceCreamPractice(field, primaryStage);
                break;
        }

        Scene scene;
        if (level == 0) {
            scene = new Scene(field.getRoot(), sceneWidth, sceneHeight + 150);
        } else {
            scene = new Scene(field.getRoot(), sceneWidth + 200, sceneHeight);
        }
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    action = UserAction.LEFT;
                    break;
                case RIGHT:
                    action = UserAction.RIGHT;
                    break;
                case UP:
                    action = UserAction.UP;
                    break;
                case DOWN:
                    action = UserAction.DOWN;
                    break;
                case W:
                    action = UserAction.BREAKUP;
                    break;
                case S:
                    action = UserAction.BREAKDOWN;
                    break;
                case A:
                    action = UserAction.BREAKLEFT;
                    break;
                case D:
                    action = UserAction.BREAKRIGHT;
                    break;
            }

            if (makeUp.match(event)) {
                action = UserAction.MAKEUP;
            } else if (makeDown.match(event)) {
                action = UserAction.MAKEDOWN;
            } else if (makeLeft.match(event)) {
                action = UserAction.MAKELEFT;
            } else if (makeRight.match(event)) {
                action = UserAction.MAKERIGHT;
            }

        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    action = UserAction.NONE;
                    break;
                case RIGHT:
                    action = UserAction.NONE;
                    break;
                case UP:
                    action = UserAction.NONE;
                    break;
                case DOWN:
                    action = UserAction.NONE;
                    break;
                case A:
                    action = UserAction.NONE;
                    break;
                case D:
                    action = UserAction.NONE;
                    break;
                case W:
                    action = UserAction.NONE;
                    break;
                case S:
                    action = UserAction.NONE;
                    break;
            }
        });
        iceCream.getGraphic().getSwirl().get(1).setFill(Color.GAINSBORO);

        primaryStage.setScene(scene); //assigns scene to stage
        primaryStage.show(); //show stage
        startGame();

    }
}
