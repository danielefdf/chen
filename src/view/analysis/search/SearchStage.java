package view.analysis.search;

import engine.model.Move;
import engine.model.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class SearchStage extends Stage {

    private Stage ownerStage;
    private Node node;
    private Move move;
    private SearchAim searchAim;

    private SearchHBox searchHBox;

    public SearchHBox getSearchHBox() {
        return searchHBox;
    }

    public SearchStage(Stage newOwnerStage, Node newNode, Move newMove, SearchAim newSearchAim)
            throws Exception {

        ownerStage = newOwnerStage;

        node = newNode;
        move = newMove;
        searchAim = newSearchAim;

        newSearchHBox();

        setTitle("search");
//        initModality(Modality.APPLICATION_MODAL);
        initOwner(ownerStage);
//        initStyle(StageStyle.UNDECORATED);
//        setResizable(false);
//        setOnCloseRequest();

        getIcons().add(ViewUtils.selectRandomIcon());

        setSearchStage();

    }

    private void setSearchStage() {

        Scene s = new Scene(searchHBox);

        s.getStylesheets().add(getClass().getResource("search.css").toExternalForm());

        setScene(s);

    }

    private void newSearchHBox()
            throws Exception {

        searchHBox = new SearchHBox(this, node, move, /*allowedToGame*/true, searchAim);

    }

}
