package view.analysis.stage;

import java.util.LinkedList;
import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.analysis.evaluation.EvaluationBorderPane;
import view.analysis.moves.MovesHBox;
import view.analysis.moves.MovesTableViewListener;
import view.analysis.search.PvTableViewListener;
import view.analysis.search.SearchAim;
import view.analysis.search.SearchHBox;
import view.engine.EngineHBox;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class AnalysisStage extends Stage {

//    private Stage ownerStage;
    private Engine engine;
    private Node node;
    private Move move;

    private TabPane rootTabPane = new TabPane();
        private EngineHBox engineHBox;
        private EvaluationBorderPane evaluationBorderPane;
        private MovesHBox movesHBox;
        private SearchHBox searchHBox;

    public EngineHBox getEngineHBox() {
        return engineHBox;
    }

    public EvaluationBorderPane getEvaluationBorderPane() {
        return evaluationBorderPane;
    }

    public MovesHBox getMovesHBox() {
        return movesHBox;
    }

    public SearchHBox getSearchHBox() {
        return searchHBox;
    }

    public AnalysisStage(Stage newOwnerStage, Engine newEngine, Node newNode, Move newMove)
            throws Exception {

//        ownerStage = newOwnerStage;

        engine = newEngine;
        node = newNode;
        move = newMove;

        setTitle("analysis");
//        initModality(Modality.APPLICATION_MODAL);
//        initOwner(ownerStage);
//        setResizable(false);
//        initStyle(StageStyle.UNDECORATED);
        setOnCloseRequest(we ->  we.consume() );

        getIcons().add(ViewUtils.selectRandomIcon());

        newEngineHBox();
        newEvaluationBorderPane();
        newMovesHBox();
        newSearchHBox();

        setAnalysisStage();

    }

    private void setAnalysisStage() {

        rootTabPane.getTabs().clear();

        Tab playerTab = new Tab("engine", engineHBox);
        playerTab.setClosable(false);

        Tab evaluationTab = new Tab("evaluation", evaluationBorderPane);
        evaluationTab.setClosable(false);

        Tab movesTab = new Tab("moves", movesHBox);
        movesTab.setClosable(false);

        Tab searchTab = new Tab("search", searchHBox);
        searchTab.setClosable(false);

        rootTabPane.getTabs().add(0, playerTab);
        rootTabPane.getTabs().add(1, evaluationTab);
        rootTabPane.getTabs().add(2, movesTab);
        rootTabPane.getTabs().add(3, searchTab);

        Scene s = new Scene(rootTabPane);

        s.getStylesheets().add(getClass().getResource("analysis.css").toExternalForm());

        setScene(s);

        rootTabPane.requestFocus();

    }

    private void newEngineHBox()
            throws Exception {

        engineHBox = new EngineHBox(this, engine);

    }

    private void newEvaluationBorderPane()
            throws Exception {

        evaluationBorderPane = new EvaluationBorderPane(this, engine, node);

    }

    private void newMovesHBox()
            throws Exception {

        movesHBox = new MovesHBox(this, node, move, /*allowedToGame*/true);

        movesHBox.getMovesTableView().addMovesTableViewListener(new MovesTableViewListener() {

            @Override
            public void onMoveSelected(Move move) {} // not handled here

            @Override
            public void onMoveToPlay(Move move) {} // not handled here

            @Override
            public void onMoveToEvaluate(Move move) {} // not handled here

            @Override
            public void onControlPageDownPressed() {
                rootTabPane.getSelectionModel().select(3);
            }

            @Override
            public void onControlPageUpPressed() {
                rootTabPane.getSelectionModel().select(1);
            }

        });

    }

    private void newSearchHBox()
            throws Exception {

        searchHBox = new SearchHBox(this, node, move, /*allowedToGame*/true, SearchAim.ANALYSIS);

        searchHBox.getPvTableView().addPvTableViewListener(new PvTableViewListener() {

            @Override
            public void onVariationSelected(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onVariationToPlay(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onVariationToEvaluate(LinkedList<Move> movesList) {} // not handled here

            @Override
            public void onControlPageDownPressed() {
                rootTabPane.getSelectionModel().select(0);
            }

            @Override
            public void onControlPageUpPressed() {
                rootTabPane.getSelectionModel().select(2);
            }

        });

    }

}
