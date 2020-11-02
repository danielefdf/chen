package view.analysis.evaluation;

import engine.model.Node;

public interface EvaluationBorderPaneListener {

    void onEvaluationNodeChanged(Node node)
    		throws Exception;

}
