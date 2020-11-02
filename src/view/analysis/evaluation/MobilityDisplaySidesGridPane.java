package view.analysis.evaluation;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class MobilityDisplaySidesGridPane extends GridPane {

    private static final Pos ALIGNMENT = Pos.CENTER_LEFT;

    private String parmString;

    private Integer whiteSteps,        blackSteps,
                    whiteCaptures,     blackCaptures,
                    whiteDoubleSteps,  blackDoubleSteps,
                    whiteShortCg,      blackShortCg,
                    whiteLongCg,       blackLongCg;

    private EvalParmLabel parmEPL;
        private EvalParmLabel stepsEPL          = new EvalParmLabel("steps"           );
        private EvalParmLabel doubleStepsEPL    = new EvalParmLabel("double steps"    );
        private EvalParmLabel capturesEPL       = new EvalParmLabel("captures"        );
        private EvalParmLabel shortCgEPL        = new EvalParmLabel("short castling"  );
        private EvalParmLabel longCgEPL         = new EvalParmLabel("long castling"   );
        private EvalScoreLabel whiteStepsESL;
        private EvalScoreLabel whiteDoubleStepsESL;
        private EvalScoreLabel whiteCapturesESL;
        private EvalScoreLabel whiteShortCgESL;
        private EvalScoreLabel whiteLongCgESL;
        private EvalScoreLabel blackStepsESL;
        private EvalScoreLabel blackDoubleStepsESL;
        private EvalScoreLabel blackCapturesESL;
        private EvalScoreLabel blackShortCgESL;
        private EvalScoreLabel blackLongCgESL;

    public MobilityDisplaySidesGridPane(String newParmString,
            Integer newWhiteSteps, Integer newWhiteDoubleSteps,
            Integer newWhiteCaptures, Integer newWhiteShortCg, Integer newWhiteLongCg,
            Integer newBlackSteps, Integer newBlackDoubleSteps,
            Integer newBlackCaptures, Integer newBlackShortCg, Integer newBlackLongCg) {

        parmString = newParmString;

        whiteSteps        = newWhiteSteps;
        whiteCaptures     = newWhiteCaptures;
        whiteDoubleSteps  = newWhiteDoubleSteps;
        whiteShortCg      = newWhiteShortCg;
        whiteLongCg       = newWhiteLongCg;

        blackSteps        = newBlackSteps;
        blackCaptures     = newBlackCaptures;
        blackDoubleSteps  = newBlackDoubleSteps;
        blackShortCg      = newBlackShortCg;
        blackLongCg       = newBlackLongCg;

        parmEPL = new EvalParmLabel(parmString);

        newControls();

        setAlignment(ALIGNMENT);

        setParmPaneSpinner();

    }

    private void newControls() {

        whiteStepsESL          = new EvalScoreLabel(whiteSteps);
        whiteDoubleStepsESL    = new EvalScoreLabel(whiteDoubleSteps);
        whiteCapturesESL       = new EvalScoreLabel(whiteCaptures);
        whiteShortCgESL        = new EvalScoreLabel(whiteShortCg);
        whiteLongCgESL         = new EvalScoreLabel(whiteLongCg);

        blackStepsESL          = new EvalScoreLabel(blackSteps);
        blackDoubleStepsESL    = new EvalScoreLabel(blackDoubleSteps);
        blackCapturesESL       = new EvalScoreLabel(blackCaptures);
        blackShortCgESL        = new EvalScoreLabel(blackShortCg);
        blackLongCgESL         = new EvalScoreLabel(blackLongCg);

    }

    private void setParmPaneSpinner() {

        getChildren().clear();

        add(parmEPL, 0, 0, 1, 7);

        if (whiteSteps        != null) { add(stepsEPL,        1, 0); }
        if (whiteDoubleSteps  != null) { add(doubleStepsEPL,  1, 1); }
        if (whiteCaptures     != null) { add(capturesEPL,     1, 3); }
        if (whiteShortCg      != null) { add(shortCgEPL,      1, 5); }
        if (whiteLongCg       != null) { add(longCgEPL,       1, 6); }

        if (whiteSteps        != null) { add(whiteStepsESL,        2, 0); }
        if (whiteDoubleSteps  != null) { add(whiteDoubleStepsESL,  2, 1); }
        if (whiteCaptures     != null) { add(whiteCapturesESL,     2, 3); }
        if (whiteShortCg      != null) { add(whiteShortCgESL,      2, 5); }
        if (whiteLongCg       != null) { add(whiteLongCgESL,       2, 6); }

        if (blackSteps        != null) { add(blackStepsESL,        3, 0); }
        if (blackDoubleSteps  != null) { add(blackDoubleStepsESL,  3, 1); }
        if (blackCaptures     != null) { add(blackCapturesESL,     3, 3); }
        if (blackShortCg      != null) { add(blackShortCgESL,      3, 5); }
        if (blackLongCg       != null) { add(blackLongCgESL,       3, 6); }

    }

    public void reset(
            Integer newWhiteSteps, Integer newWhiteDoubleSteps,
            Integer newWhiteCaptures, Integer newWhiteShortCg, Integer newWhiteLongCg,
            Integer newBlackSteps, Integer newBlackDoubleSteps,
            Integer newBlackCaptures, Integer newBlackShortCg, Integer newBlackLongCg) {

        whiteSteps        = newWhiteSteps;
        whiteCaptures     = newWhiteCaptures;
        whiteDoubleSteps  = newWhiteDoubleSteps;
        whiteShortCg      = newWhiteShortCg;
        whiteLongCg       = newWhiteLongCg;

        blackSteps        = newBlackSteps;
        blackCaptures     = newBlackCaptures;
        blackDoubleSteps  = newBlackDoubleSteps;
        blackShortCg      = newBlackShortCg;
        blackLongCg       = newBlackLongCg;

        parmEPL.setText(parmString);

        resetControls();

    }

    private void resetControls() {

        whiteStepsESL.reset(       whiteSteps);
        whiteDoubleStepsESL.reset( whiteDoubleSteps);
        whiteCapturesESL.reset(    whiteCaptures);
        whiteShortCgESL.reset(     whiteShortCg);
        whiteLongCgESL.reset(      whiteLongCg);

        blackStepsESL.reset(       blackSteps);
        blackDoubleStepsESL.reset( blackDoubleSteps);
        blackCapturesESL.reset(    blackCaptures);
        blackShortCgESL.reset(     blackShortCg);
        blackLongCgESL.reset(      blackLongCg);

    }

}
