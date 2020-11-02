package view.analysis.search;

import java.util.LinkedList;
import engine.model.Move;

public interface PvTableViewListener {

    void onVariationSelected(LinkedList<Move> movesList)
            throws Exception;

    void onVariationToPlay(LinkedList<Move> movesList)
            throws Exception;

    void onVariationToEvaluate(LinkedList<Move> movesList)
            throws Exception;

    void onControlPageDownPressed()
            throws Exception;

    void onControlPageUpPressed()
            throws Exception;

}
