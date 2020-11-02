package view.analysis.moves;

import engine.model.Move;

public interface MovesTableViewListener {

    void onMoveSelected(Move move)
            throws Exception;

    void onMoveToPlay(Move move)
            throws Exception;

    void onMoveToEvaluate(Move move)
            throws Exception;

    void onControlPageDownPressed()
            throws Exception;

    void onControlPageUpPressed()
            throws Exception;

}
