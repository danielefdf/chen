package view.analysis.evaluation;

import javafx.scene.control.ToolBar;
import view.utilities.ToolBarButton;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class TitledPanesToolBar extends ToolBar {

    private ToolBarButton collapseAllButton;
    private ToolBarButton expandAllButton;

    private TitledPanesToolBarListener titledPanesToolBarListener;

    public void setTitledPanesToolBarListener(TitledPanesToolBarListener newTitledPanesToolBarListener) {

        titledPanesToolBarListener = newTitledPanesToolBarListener;

    }

    public TitledPanesToolBar() {

        super();

        newCollapseAllButton();
        newExpandAllButton();

        setTitledPanesToolBar();

    }

    private void setTitledPanesToolBar() {

        getItems().clear();

        getItems().add(collapseAllButton);
        getItems().add(expandAllButton);

    }

    private void newCollapseAllButton() {

        collapseAllButton = new ToolBarButton("collapse all");

        collapseAllButton.setOnAction(ae -> {

            if (titledPanesToolBarListener != null) {
                try {
                    titledPanesToolBarListener.onCollapseAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void newExpandAllButton() {

        expandAllButton = new ToolBarButton("expand all");

        expandAllButton.setOnAction(ae -> {

            if (titledPanesToolBarListener != null) {
                try {
                    titledPanesToolBarListener.onExpandAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

}
