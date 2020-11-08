package view.analysis.evaluation;

import engine.model.Node;

public interface EvaluationBorderPaneListener {

    Thread getNextMoveThread();

    void onSearchButtonPressed(Node node)
    		throws Exception;

    void onEvaluationNodeChanged(Node node)
            throws Exception;

}
