package view.nodepane;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.LinkedList;
import engine.model.Engine;
import engine.model.Move;
import engine.model.Node;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.elements.BitBoards;
import model.elements.Colors;
import model.elements.Functions;
import model.elements.Pieces;
import model.elements.Squares;
import model.elements.States;
import model.utilities.FenStrings;
import view.game.GameStage;
import view.options.UserOptions;
import view.utilities.ViewUtils;

@SuppressWarnings({"RedundantThrows",
        "FieldCanBeLocal",
        "Convert2Lambda",
        "UnnecessaryLocalVariable",
        "Convert2MethodRef",
        "FieldMayBeFinal"})
public class NodePane extends StackPane {

    private static final double INSETS_COEFF = 1/4.;
    private static final double CIRCLE_COEFF = 1/10.;
    private static final double CAPTURED_COEFF = 3/5.;

    private static final Engine MOVES_ENGINE = Engine.newMiniMaxEngine();

    private Stage ownerStage;
    private Node node;
    private Move move;
    private boolean showAvailables, showNodeDetails, showNodeSetVBox;
    private int boardTileEdge;

    public Node getNode() {
        return node;
    }

    public Move getMove() {
        return move;
    }

    private int boardEdge;

    private double capturedEdge;
    private LinkedList<Byte> whiteMatDiffList, blackMatDiffList;

    private Node movesNode;

    private byte fromSquare;

    private Byte pieceToSet;
    private boolean enPassantToSet;

    private GridPane controlsGridPane = new GridPane();
        private GridPane fenGridPane = new GridPane();
            private TextField fenTextField = new TextField();
            private ChangeListener<Boolean> fenTFFocusChangeListener;
        private VBox nodeSetVBox = new VBox();
            private Button stdButton           = new Button("std");
            private Button clearButton         = new Button("clear");
            private GridPane piecesSetGridPane = new GridPane();
                private SetPieceButton setWhitePawnSTB;
                private SetPieceButton setWhiteKnightSTB;
                private SetPieceButton setWhiteBishopSTB;
                private SetPieceButton setWhiteRookSTB;
                private SetPieceButton setWhiteQueenSTB;
                private SetPieceButton setBlackPawnSTB;
                private SetPieceButton setBlackKnightSTB;
                private SetPieceButton setBlackBishopSTB;
                private SetPieceButton setBlackRookSTB;
                private SetPieceButton setBlackQueenSTB;
                private SetEnPassantButton setEnPassantSETB;
        private Pane playerPane;
            private Circle northCircle;
            private Circle southCircle;
            private EventHandler<MouseEvent> northCEventHandler;
            private EventHandler<MouseEvent> southCEventHandler;
        private GridPane northCastlingsGridPane = new GridPane();
            private CastlingToggleButton northWestCTB;
            private CastlingToggleButton northEastCTB;
        private GridPane southCastlingsGridPane = new GridPane();
            private CastlingToggleButton southWestCTB;
            private CastlingToggleButton southEastCTB;
        private StackPane boardStackPane;
            private ProgressIndicator boardProgressIndicator;
            private Pane boardPane;
                private Group tilesGroup;
                private Group figuresGroup;
                private Group availsGroup;
        private Label moveLabel;
        private ScrollPane matDiffScrollPane = new ScrollPane();
            private GridPane matDiffGridPane = new GridPane();
        private GridPane nodeQuantitiesGridPane = new GridPane();
            private Label halfmovesLabel         = new Label("halfmoves clock");
            private Spinner<Integer> halfmovesSpinner = new Spinner<>(0, 1000, 0, 1);
            private ChangeListener<Integer> halfmovesChangeListener;
            private Label fullmovesLabel         = new Label("fullmoves number");
            private Spinner<Integer> fullmovesSpinner = new Spinner<>(1, 1000, 1, 1);
            private ChangeListener<Integer> fullmovesChangeListener;
            private Label gameStateLabel         = new Label("game state");
            private Label gameStateValueLabel    = new Label();
            private Label treeLevelLabel         = new Label("node tree level");
            private Label treeLevelValueLabel    = new Label();
            private Label nodeHashcodeLabel      = new Label("node hashcode");
            private Label nodeHashcodeValueLabel = new Label();
    private HBox disabledHBox = new HBox();

    private NodePaneListener nodePaneListener;

    private EventHandler<KeyEvent> keyEventHandler;

    @SuppressWarnings("UnusedAssignment")
    private DropShadow controlsDropShadow = new DropShadow();

    public TextField getFenTextField() {
        return fenTextField;
    }

    public void setNodePaneListener(NodePaneListener newNodePaneListener) {
        nodePaneListener = newNodePaneListener;
    }

    public EventHandler<KeyEvent> getKeyEventHandler() {
        return keyEventHandler;
    }

    public NodePane(Stage newOwnerStage, Node newNode, Move newMove, boolean newShowAvailables, boolean newShowNodeDetails,
            boolean newShowNodeSetVBox, int newBoardTileEdge)
                    throws Exception {

        ownerStage = newOwnerStage;
        node = new Node(newNode);
        move = newMove;

        showAvailables  = newShowAvailables;
        showNodeDetails = newShowNodeDetails;
        showNodeSetVBox = newShowNodeSetVBox;

        boardTileEdge = newBoardTileEdge;

        boardEdge = boardTileEdge * Squares.EDGE;

        capturedEdge = boardTileEdge * CAPTURED_COEFF;

        whiteMatDiffList = new LinkedList<>();
        blackMatDiffList = new LinkedList<>();

        pieceToSet = null;
        enPassantToSet = false;

        controlsDropShadow = new DropShadow();
        controlsDropShadow.setColor(Color.GRAY);

        movesNode = new Node(node);
        MOVES_ENGINE.computePlayerMovesBb(movesNode);

        newDisabledHBox();
        newControlsGridPane();

        setNodePane();

        setEventFilter();

    }

    private void setEventFilter() {

        keyEventHandler = ke -> {

            if (ke.isControlDown()
                    && ke.getCode() == KeyCode.C) {
                try {
                    Toolkit.getDefaultToolkit().getSystemClipboard()
                            .setContents(new StringSelection(node.toFen()), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ke.consume();
            } else if (ke.isControlDown()
                        && ke.getCode() == KeyCode.V) {
                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                        .getContents(this);
                if (t != null) {
                    try {

                        String fenString = (String) t.getTransferData(DataFlavor.stringFlavor);

                        Node oldNode = new Node(node);
                        Move oldMove = move;

                        node = new Node(fenString);
                        move = null;

                        checkResetNodePane(oldNode, oldMove);

                        if (nodePaneListener != null) {
                            nodePaneListener.onBoardReset(node);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ke.consume();
            }

        };

    }

    private void checkResetNodePane(Node oldNode, Move oldMove)
            throws Exception {

        checkCastlingFlags();

        node = new Node(node.toFen());

        if (node.gameState != States.NOT_LEGAL) {
            resetNodePane(node, move);
        } else {
            if (ownerStage.getClass() != GameStage.class) {
                node = oldNode;
                move = oldMove;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("");
                alert.setContentText("position not allowed");
                alert.showAndWait();
                try {
                    resetNodePane(node, move);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private void checkCastlingFlags() {

        if (node.squarePieceMap[Squares.SQUARE_E1] == null
                || node.squarePieceMap[Squares.SQUARE_E1] != Pieces.WHITE_KING
                || node.squarePieceMap[Squares.SQUARE_H1] == null
                || node.squarePieceMap[Squares.SQUARE_H1] != Pieces.WHITE_ROOK) {
            node.whiteShortCg = false;
        }

        if (node.squarePieceMap[Squares.SQUARE_E1] == null
                || node.squarePieceMap[Squares.SQUARE_E1] != Pieces.WHITE_KING
                || node.squarePieceMap[Squares.SQUARE_A1] == null
                || node.squarePieceMap[Squares.SQUARE_A1] != Pieces.WHITE_ROOK) {
            node.whiteLongCg = false;
        }

        if (node.squarePieceMap[Squares.SQUARE_E8] == null
                || node.squarePieceMap[Squares.SQUARE_E8] != Pieces.BLACK_KING
                || node.squarePieceMap[Squares.SQUARE_H8] == null
                || node.squarePieceMap[Squares.SQUARE_H8] != Pieces.BLACK_ROOK) {
            node.blackShortCg = false;
        }

        if (node.squarePieceMap[Squares.SQUARE_E8] == null
                || node.squarePieceMap[Squares.SQUARE_E8] != Pieces.BLACK_KING
                || node.squarePieceMap[Squares.SQUARE_A8] == null
                || node.squarePieceMap[Squares.SQUARE_A8] != Pieces.BLACK_ROOK) {
            node.blackLongCg = false;
        }

    }

    public void resetNodePane(Node newNode, Move newMove)
                    throws Exception {

        node = new Node(newNode);

        /* refresh Zobrist key */
        Node nodeZobrist = new Node(node.toFen());
        node.nodeHashCode = nodeZobrist.nodeHashCode;

        movesNode = new Node(node);
        MOVES_ENGINE.computePlayerMovesBb(movesNode);

        move = newMove;

        resetControlsGridPane();

    }

    private void setNodePane() {

        getChildren().clear();

        getChildren().add(controlsGridPane);
        getChildren().add(disabledHBox);

        // altrimenti ci sono problemi con i click
        northCastlingsGridPane.toFront();
        southCastlingsGridPane.toFront();

        this.requestFocus();

    }

    private void newDisabledHBox() {

        disabledHBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");

        disabledHBox.setDisable(true);
        disabledHBox.setVisible(false);

    }

    private void newControlsGridPane()
            throws Exception {

        if (showNodeDetails) { newFenGridPane(); }
        if (showNodeSetVBox) { newNodeSetVBox(); }
                               newPlayerPane();
        if (showNodeDetails) { newNorthCastlingsGridPane(); }
                               newBoardStackPane();
        if (showNodeDetails) { newSouthCastlingsGridPane(); }
                               newMoveLabel();
                               newMatDiffScrollPane();
        if (showNodeDetails) { newNodeQuantitiesGridPane(); }

        controlsGridPane.setPadding(new Insets(boardTileEdge * INSETS_COEFF, boardTileEdge * INSETS_COEFF,
                boardTileEdge * INSETS_COEFF, boardTileEdge * INSETS_COEFF));

        controlsGridPane.setHgap(boardTileEdge * INSETS_COEFF);
        controlsGridPane.setVgap(boardTileEdge * INSETS_COEFF);

        controlsGridPane.setAlignment(Pos.TOP_CENTER);

        setControlsGridPane();

    }

    private void resetControlsGridPane()
            throws Exception {

        if (showNodeDetails) { resetFenGridPane(); }
        if (showNodeSetVBox) { resetNodeSetVBox(); }
                               resetPlayerPane();
        if (showNodeDetails) { resetNorthCastlingsGridPane(); }
                               resetBoardStackPane();
        if (showNodeDetails) { resetSouthCastlingsGridPane(); }
                               resetMoveLabel();
                               resetMatDiffScrollPane();
        if (showNodeDetails) { resetNodeQuantitiesGridPane(); }

    }

    private void setControlsGridPane() {

        controlsGridPane.getChildren().clear();

        controlsGridPane.add(fenGridPane,            0, 0, 4, 1);
        controlsGridPane.add(nodeSetVBox,            0, 1, 1, 5);
        controlsGridPane.add(playerPane,             1, 1, 1, 3);
        controlsGridPane.add(northCastlingsGridPane, 2, 1);
        controlsGridPane.add(boardStackPane,         2, 2);
        controlsGridPane.add(southCastlingsGridPane, 2, 3);
        controlsGridPane.add(moveLabel,              2, 4);
        controlsGridPane.add(matDiffScrollPane,      3, 1, 1, 3);
        controlsGridPane.add(nodeQuantitiesGridPane, 0, 5, 4, 1);

//        controlsGridPane.setGridLinesVisible(true);

    }

    private void newFenGridPane()
            throws Exception {

        newFenTextField();

        setfenGridPane();

    }

    private void resetFenGridPane()
            throws Exception {

        resetFenTextField();

    }

    private void setfenGridPane() {

        fenGridPane.getChildren().clear();

        fenGridPane.add(fenTextField, 0, 0);

    }

    private void newFenTextField()
            throws Exception {

        if (fenTFFocusChangeListener != null) {
            fenTextField.focusedProperty().removeListener(fenTFFocusChangeListener);
        }

        fenTextField.setText(node.toFen());

        fenTFFocusChangeListener = (ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {

            Node oldNode = null;
            Move oldMove;

            if (!newPropertyValue) {

                try {

                    oldNode = new Node(node);
                    oldMove = move;

                    node = new Node(fenTextField.getText());
                    move = null;

                    checkResetNodePane(oldNode, oldMove);

                    if (nodePaneListener != null) {
                        nodePaneListener.onBoardReset(node);
                    }

                } catch (Exception e) {

                    node = oldNode;

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText("");
                    alert.setContentText("FEN string not allowed");
                    alert.showAndWait();

                    try {
                        resetNodePane(node, move); // no checkReset
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            }

        };

        fenTextField.focusedProperty().addListener(fenTFFocusChangeListener);

        GridPane.setHgrow(fenTextField, Priority.ALWAYS);

    }

    private void resetFenTextField()
            throws Exception {

        fenTextField.setText(node.toFen());

    }

    private void newNodeSetVBox()
            throws Exception {

        nodeSetVBox.setSpacing(5);
        nodeSetVBox.setAlignment(Pos.TOP_CENTER);

        newStdButton();
        newClearButton();
        newPiecesSetGridPane();

        setNodeSetVBox();

    }

    private void resetNodeSetVBox()
            throws Exception {

    	resetPiecesSetGridPane();

    }

    private void setNodeSetVBox() {

        nodeSetVBox.getChildren().clear();

        nodeSetVBox.getChildren().add(stdButton);
        nodeSetVBox.getChildren().add(clearButton);
        nodeSetVBox.getChildren().add(piecesSetGridPane);

    }

    private void newStdButton() {

        double stdButtonTileEdge = boardTileEdge;

        stdButton.setPrefSize(2 * stdButtonTileEdge, stdButtonTileEdge);

        stdButton.setOnAction(ae -> {
            try {

                node = new Node(FenStrings.STD);

                resetNodePane(node, move); // no checkReset

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void newClearButton() {

        double clearButtonTileEdge = boardTileEdge;

        clearButton.setPrefSize(2 * clearButtonTileEdge, clearButtonTileEdge);

        clearButton.setOnAction(ae -> {
            try {

                node = new Node(FenStrings.NEW_GAME_CLEAR);

                resetNodePane(node, move); // no checkReset

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void newPiecesSetGridPane()
            throws Exception {

        newPiecesButtons();

        setPiecesSetGridPane();

    }

    private void resetPiecesSetGridPane()
            throws Exception {

        resetPiecesButtons();

    }

    private void setPiecesSetGridPane() {

        GridPane.setHalignment(setEnPassantSETB, HPos.CENTER);

        piecesSetGridPane.getChildren().clear();

        piecesSetGridPane.add(setWhitePawnSTB,   0, 0);
        piecesSetGridPane.add(setWhiteKnightSTB, 0, 1);
        piecesSetGridPane.add(setWhiteBishopSTB, 0, 2);
        piecesSetGridPane.add(setWhiteRookSTB,   0, 3);
        piecesSetGridPane.add(setWhiteQueenSTB,  0, 4);

        piecesSetGridPane.add(setBlackPawnSTB,   1, 0);
        piecesSetGridPane.add(setBlackKnightSTB, 1, 1);
        piecesSetGridPane.add(setBlackBishopSTB, 1, 2);
        piecesSetGridPane.add(setBlackRookSTB,   1, 3);
        piecesSetGridPane.add(setBlackQueenSTB,  1, 4);

        piecesSetGridPane.add(setEnPassantSETB,  0, 5, 2, 1);

//        piecesSetGridPane.setGridLinesVisible(true);

    }

    private void newPiecesButtons()
            throws Exception {

        double setButtonTileEdge = boardTileEdge * 3/4.;

        ToggleGroup tg = new ToggleGroup();

        setWhitePawnSTB = new SetPieceButton(Pieces.WHITE_PAWN, setButtonTileEdge);
        setWhitePawnSTB.setToggleGroup(tg);
        setWhitePawnSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.WHITE_PAWN) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.WHITE_PAWN;
            }
        });

        setWhiteKnightSTB = new SetPieceButton(Pieces.WHITE_KNIGHT, setButtonTileEdge);
        setWhiteKnightSTB.setToggleGroup(tg);
        setWhiteKnightSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.WHITE_KNIGHT) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.WHITE_KNIGHT;
            }
        });

        setWhiteBishopSTB = new SetPieceButton(Pieces.WHITE_BISHOP, setButtonTileEdge);
        setWhiteBishopSTB.setToggleGroup(tg);
        setWhiteBishopSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.WHITE_BISHOP) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.WHITE_BISHOP;
            }
        });

        setWhiteRookSTB = new SetPieceButton(Pieces.WHITE_ROOK, setButtonTileEdge);
        setWhiteRookSTB.setToggleGroup(tg);
        setWhiteRookSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.WHITE_ROOK) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.WHITE_ROOK;
            }
        });

        setWhiteQueenSTB = new SetPieceButton(Pieces.WHITE_QUEEN, setButtonTileEdge);
        setWhiteQueenSTB.setToggleGroup(tg);
        setWhiteQueenSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.WHITE_QUEEN) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.WHITE_QUEEN;
            }
        });

        setBlackPawnSTB = new SetPieceButton(Pieces.BLACK_PAWN, setButtonTileEdge);
        setBlackPawnSTB.setToggleGroup(tg);
        setBlackPawnSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.BLACK_PAWN) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.BLACK_PAWN;
            }
        });

        setBlackKnightSTB = new SetPieceButton(Pieces.BLACK_KNIGHT, setButtonTileEdge);
        setBlackKnightSTB.setToggleGroup(tg);
        setBlackKnightSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.BLACK_KNIGHT) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.BLACK_KNIGHT;
            }
        });

        setBlackBishopSTB = new SetPieceButton(Pieces.BLACK_BISHOP, setButtonTileEdge);
        setBlackBishopSTB.setToggleGroup(tg);
        setBlackBishopSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.BLACK_BISHOP) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.BLACK_BISHOP;
            }
        });

        setBlackRookSTB = new SetPieceButton(Pieces.BLACK_ROOK, setButtonTileEdge);
        setBlackRookSTB.setToggleGroup(tg);
        setBlackRookSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.BLACK_ROOK) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.BLACK_ROOK;
            }
        });

        setBlackQueenSTB = new SetPieceButton(Pieces.BLACK_QUEEN, setButtonTileEdge);
        setBlackQueenSTB.setToggleGroup(tg);
        setBlackQueenSTB.setOnAction(e -> {
            if (enPassantToSet) {
                enPassantToSet = false;
            }
            if (pieceToSet != null && pieceToSet == Pieces.BLACK_QUEEN) {
                pieceToSet = null;
            } else {
                pieceToSet = Pieces.BLACK_QUEEN;
            }
        });

        setEnPassantSETB = new SetEnPassantButton(setButtonTileEdge);
        setEnPassantSETB.setDisable(node.enPassantSquare != null);
        setEnPassantSETB.setToggleGroup(tg);
        setEnPassantSETB.setOnAction(e -> {
            if (pieceToSet != null) {
                pieceToSet = null;
            }
            enPassantToSet = !enPassantToSet;
        });

    }

    private void resetPiecesButtons()
            throws Exception {

    	setEnPassantSETB.setDisable(node.enPassantSquare != null);

    }

    private void newPlayerPane()
            throws Exception {

        newWhiteCircle();
        newBlackCircle();

        playerPane = new Pane();

        playerPane.setMinSize(boardTileEdge, boardEdge);
        playerPane.setEffect(controlsDropShadow);

        setPlayerPane();

    }

    private void resetPlayerPane()
            throws Exception {

        setPlayerPane();

    }

    private void setPlayerPane()
            throws Exception {

        double whiteBoxNumber;
        double blackBoxNumber;

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE:
                whiteBoxNumber = Squares.EDGE - 1.;
                blackBoxNumber = 0.;
                break;
            case Colors.BLACK:
                whiteBoxNumber = 0.;
                blackBoxNumber = Squares.EDGE - 1.;
                break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            whiteBoxNumber = Squares.EDGE - 1.;
            blackBoxNumber = 0.;
        }

        switch (node.playerColor) {
        case Colors.WHITE:

            northCircle.setStroke(Color.GRAY);
            northCircle.setStrokeWidth(boardTileEdge * CIRCLE_COEFF);
            northCircle.setFill(Color.WHITE);
            northCircle.setEffect(ViewUtils.newDropShadow(node.playerColor, boardTileEdge));

            southCircle.setStroke(Color.LIGHTGRAY);
            southCircle.setFill(Color.LIGHTGRAY);
            southCircle.setStrokeWidth(boardTileEdge * CIRCLE_COEFF / 2.);
            southCircle.setEffect(null);

            break;
        case Colors.BLACK:

            southCircle.setStroke(Color.GRAY);
            southCircle.setStrokeWidth(boardTileEdge * CIRCLE_COEFF);
            southCircle.setFill(Color.BLACK);
            southCircle.setEffect(ViewUtils.newDropShadow(node.playerColor, boardTileEdge));

            northCircle.setStroke(Color.LIGHTGRAY);
            northCircle.setFill(Color.LIGHTGRAY);
            northCircle.setStrokeWidth(boardTileEdge * CIRCLE_COEFF / 2.);
            northCircle.setEffect(null);

            break;
        default:
            throw new Exception("node.playerColor=" + node.playerColor);
        }

        if (ownerStage.getClass() != GameStage.class) {

            northCircle.setOnMouseEntered( me -> setCursor(Cursor.HAND));
            northCircle.setOnMouseExited(  me -> setCursor(Cursor.DEFAULT));

            southCircle.setOnMouseEntered( me -> setCursor(Cursor.HAND));
            southCircle.setOnMouseExited(  me -> setCursor(Cursor.DEFAULT));

        }

        double boxAdjustmt = 10;
        if (showNodeSetVBox) {
            boxAdjustmt = 20;
        }

        double x, y;

        x = (boardTileEdge - 2 * northCircle.getRadius()) / 2;
        y = (boardTileEdge - 2 * northCircle.getRadius()) / 2
                + whiteBoxNumber * boardTileEdge
                + boxAdjustmt;
        northCircle.relocate(x, y);

        x = (boardTileEdge - 2 * southCircle.getRadius()) / 2;
        y = (boardTileEdge - 2 * southCircle.getRadius()) / 2
                + blackBoxNumber * boardTileEdge
                + boxAdjustmt;
        southCircle.relocate(x, y);

        playerPane.getChildren().clear();

        playerPane.getChildren().add(northCircle);
        playerPane.getChildren().add(southCircle);

    }

    private void newWhiteCircle()
            throws Exception {

        double radius = boardTileEdge * 0.22;

        northCircle = new Circle(radius);

        if (northCEventHandler != null) {
            northCircle.removeEventHandler(MouseEvent.MOUSE_PRESSED, northCEventHandler);
        }

        northCEventHandler = ae -> {
            try {

                if (ownerStage.getClass() != GameStage.class) {

                    Node oldNode = new Node(node);
                    Move oldMove = move;

                    node.playerColor = Colors.WHITE;
                    node.enPassantSquare = null;

                    move = null;

                    checkResetNodePane(oldNode, oldMove);

                    if (nodePaneListener != null) {
                        nodePaneListener.onBoardReset(node);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        northCircle.addEventHandler(MouseEvent.MOUSE_PRESSED, northCEventHandler);

    }

    private void newBlackCircle()
            throws Exception {

        double radius = boardTileEdge * 0.22;

        southCircle = new Circle(radius);

        if (southCEventHandler != null) {
            southCircle.removeEventHandler(MouseEvent.MOUSE_PRESSED, southCEventHandler);
        }

        southCEventHandler = ae -> {
            try {

                if (ownerStage.getClass() != GameStage.class) {

                    Node oldNode = new Node(node);
                    Move oldMove = move;

                    node.playerColor = Colors.BLACK;
                    node.enPassantSquare = null;

                    move = null;

                    checkResetNodePane(oldNode, oldMove);

                    if (nodePaneListener != null) {
                        nodePaneListener.onBoardReset(node);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        southCircle.addEventHandler(MouseEvent.MOUSE_PRESSED, southCEventHandler);

    }

    private void newNorthCastlingsGridPane()
            throws Exception {

        newNorthEastCgToggleButton();
        newNorthWestCgToggleButton();

        northCastlingsGridPane.setAlignment(Pos.CENTER);
//        northCastlingsGridPane.setPrefHeight(boardTileEdge);

        setNorthCastlingsGridPane();

    }

    private void resetNorthCastlingsGridPane()
            throws Exception {

        resetNorthEastCgToggleButton();
        resetNorthWestCgToggleButton();

    }

    private void setNorthCastlingsGridPane() {

        northCastlingsGridPane.getChildren().clear();

        northCastlingsGridPane.add(northWestCTB, 0, 0);
        northCastlingsGridPane.add(northEastCTB, 1, 0);

    }

    private void newNorthEastCgToggleButton()
            throws Exception {

        northEastCTB = new CastlingToggleButton(boardTileEdge);

        setNorthEastCgToggleButton();

    }

    private void resetNorthEastCgToggleButton()
            throws Exception {

        setNorthEastCgToggleButton();

    }

    private void setNorthEastCgToggleButton()
            throws Exception {

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setNorthEastCgToggleButtonWhitePov(); break;
            case Colors.BLACK: setNorthEastCgToggleButtonBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setNorthEastCgToggleButtonWhitePov();
        }

        northEastCTB.reset();

    }

    private void setNorthEastCgToggleButtonWhitePov() {

        northEastCTB.setPrefWidth(3.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E8] != null
                && node.squarePieceMap[Squares.SQUARE_E8] == Pieces.BLACK_KING
                && node.squarePieceMap[Squares.SQUARE_H8] != null
                && node.squarePieceMap[Squares.SQUARE_H8] == Pieces.BLACK_ROOK) {
            northEastCTB.setDisable(false);
            northEastCTB.setSelected(node.blackShortCg);
        } else {
            node.blackShortCg = false;
            northEastCTB.setDisable(true);
        }

        northEastCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.blackShortCg = northEastCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void setNorthEastCgToggleButtonBlackPov() {

        northEastCTB.setPrefWidth(4.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E1] != null
                && node.squarePieceMap[Squares.SQUARE_E1] == Pieces.WHITE_KING
                && node.squarePieceMap[Squares.SQUARE_A1] != null
                && node.squarePieceMap[Squares.SQUARE_A1] == Pieces.WHITE_ROOK) {
            northEastCTB.setDisable(false);
            northEastCTB.setSelected(node.whiteLongCg);
        } else {
            node.whiteLongCg = false;
            northEastCTB.setDisable(true);
        }

        northEastCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.whiteLongCg = northEastCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void newNorthWestCgToggleButton()
            throws Exception {

        northWestCTB = new CastlingToggleButton(boardTileEdge);

        setNorthWestCgToggleButton();

    }

    private void resetNorthWestCgToggleButton()
            throws Exception {

        setNorthWestCgToggleButton();

    }

    private void setNorthWestCgToggleButton()
            throws Exception {

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setNorthWestCgToggleButtonWhitePov(); break;
            case Colors.BLACK: setNorthWestCgToggleButtonBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setNorthWestCgToggleButtonWhitePov();
        }

        northWestCTB.reset();

    }

    private void setNorthWestCgToggleButtonWhitePov()
            throws Exception {

        northWestCTB.setPrefWidth(4.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E8] != null
                && node.squarePieceMap[Squares.SQUARE_E8] == Pieces.BLACK_KING
                && node.squarePieceMap[Squares.SQUARE_A8] != null
                && node.squarePieceMap[Squares.SQUARE_A8] == Pieces.BLACK_ROOK) {
            northWestCTB.setDisable(false);
            northWestCTB.setSelected(node.blackLongCg);
        } else {
            node.blackLongCg = false;
            northWestCTB.setDisable(true);
        }

        northWestCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.blackLongCg = northWestCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void setNorthWestCgToggleButtonBlackPov()
            throws Exception {

        northWestCTB.setPrefWidth(3.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E1] != null
                && node.squarePieceMap[Squares.SQUARE_E1] == Pieces.WHITE_KING
                && node.squarePieceMap[Squares.SQUARE_H1] != null
                && node.squarePieceMap[Squares.SQUARE_H1] == Pieces.WHITE_ROOK) {
            northWestCTB.setDisable(false);
            northWestCTB.setSelected(node.whiteShortCg);
        } else {
            node.whiteShortCg = false;
            northWestCTB.setDisable(true);
        }

        northWestCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.whiteShortCg = northWestCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void newSouthCastlingsGridPane()
            throws Exception {

        newSouthEastCgToggleButton();
        newSouthWestCgToggleButton();

        southCastlingsGridPane.setAlignment(Pos.CENTER);

        setSouthCastlingsGridPane();

    }

    private void resetSouthCastlingsGridPane()
            throws Exception {

        resetSouthEastCgToggleButton();
        resetSouthWestCgToggleButton();

    }

    private void setSouthCastlingsGridPane() {

        southCastlingsGridPane.getChildren().clear();

        southCastlingsGridPane.add(southWestCTB, 0, 0);
        southCastlingsGridPane.add(southEastCTB, 1, 0);

    }

    private void newSouthEastCgToggleButton()
            throws Exception {

        southEastCTB = new CastlingToggleButton(boardTileEdge);

        setSouthEastCgToggleButton();

    }

    private void resetSouthEastCgToggleButton()
            throws Exception {

        setSouthEastCgToggleButton();

    }

    private void setSouthEastCgToggleButton()
            throws Exception {

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setSouthEastCgToggleButtonWhitePov(); break;
            case Colors.BLACK: setSouthEastCgToggleButtonBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setSouthEastCgToggleButtonWhitePov();
        }

        southEastCTB.reset();

    }

    private void setSouthEastCgToggleButtonWhitePov() {

        southEastCTB.setPrefWidth(3.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E1] != null
                && node.squarePieceMap[Squares.SQUARE_E1] == Pieces.WHITE_KING
                && node.squarePieceMap[Squares.SQUARE_H1] != null
                && node.squarePieceMap[Squares.SQUARE_H1] == Pieces.WHITE_ROOK) {
            southEastCTB.setDisable(false);
            southEastCTB.setSelected(node.whiteShortCg);
        } else {
            node.whiteShortCg = false;
            southEastCTB.setDisable(true);
        }

        southEastCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.whiteShortCg = southEastCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void setSouthEastCgToggleButtonBlackPov() {

        southEastCTB.setPrefWidth(4.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E8] != null
                && node.squarePieceMap[Squares.SQUARE_E8] == Pieces.BLACK_KING
                && node.squarePieceMap[Squares.SQUARE_A8] != null
                && node.squarePieceMap[Squares.SQUARE_A8] == Pieces.BLACK_ROOK) {
            southEastCTB.setDisable(false);
            southEastCTB.setSelected(node.blackLongCg);
        } else {
            node.blackLongCg = false;
            southEastCTB.setDisable(true);
        }

        southEastCTB.setOnAction(ae -> Platform.runLater(() -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.blackLongCg = southEastCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }

    private void newSouthWestCgToggleButton()
            throws Exception {

        southWestCTB = new CastlingToggleButton(boardTileEdge);

        setSouthWestCgToggleButton();

    }

    private void resetSouthWestCgToggleButton()
            throws Exception {

        setSouthWestCgToggleButton();

    }

    private void setSouthWestCgToggleButton()
            throws Exception {

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setSouthWestCgToggleButtonWhitePov(); break;
            case Colors.BLACK: setSouthWestCgToggleButtonBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setSouthWestCgToggleButtonWhitePov();
        }

        southWestCTB.reset();

    }

    private void setSouthWestCgToggleButtonWhitePov() {

        southWestCTB.setPrefWidth(4.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E1] != null
                && node.squarePieceMap[Squares.SQUARE_E1] == Pieces.WHITE_KING
                && node.squarePieceMap[Squares.SQUARE_A1] != null
                && node.squarePieceMap[Squares.SQUARE_A1] == Pieces.WHITE_ROOK) {
            southWestCTB.setDisable(false);
            southWestCTB.setSelected(node.whiteLongCg);
        } else {
            node.whiteLongCg = false;
            southWestCTB.setDisable(true);
        }

        southWestCTB.setOnAction(ae -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.whiteLongCg = southWestCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void setSouthWestCgToggleButtonBlackPov() {

        southWestCTB.setPrefWidth(3.5 * boardTileEdge);

        if (node.squarePieceMap[Squares.SQUARE_E8] != null
                && node.squarePieceMap[Squares.SQUARE_E8] == Pieces.BLACK_KING
                && node.squarePieceMap[Squares.SQUARE_H8] != null
                && node.squarePieceMap[Squares.SQUARE_H8] == Pieces.BLACK_ROOK) {
            southWestCTB.setDisable(false);
            southWestCTB.setSelected(node.blackShortCg);
        } else {
            node.blackShortCg = false;
            southWestCTB.setDisable(true);
        }

        southWestCTB.setOnAction(ae -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.blackShortCg = southWestCTB.isSelected();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void newBoardStackPane()
            throws Exception {

        newBoardProgressIndicator();
        newBoardPane();

        boardStackPane = new StackPane();
        boardStackPane.setEffect(controlsDropShadow);

        setBoardStackPane();

    }

    private void newBoardProgressIndicator() {

        boardProgressIndicator = new ProgressIndicator();

        setBoardProgressIndicator();

    }

    private void setBoardProgressIndicator() {

        boardProgressIndicator.setVisible(false);

    }

    public void startBoardProgressIndicator() {

        boardProgressIndicator.setVisible(true);

    }

    public void stopBoardProgressIndicator() {

        boardProgressIndicator.setVisible(false);

    }

    private void newBoardPane()
            throws Exception {

        newTilesGroup();
        newFiguresGroup();
        newAvailsGroup();

        boardPane = new Pane();

        boardPane.setPrefSize(boardEdge, boardEdge);

        setBoardPane();

    }

    private void resetBoardStackPane()
            throws Exception {

        resetBoardProgressPane();
        resetBoardPane();

        setBoardStackPane();

    }

    private void setBoardStackPane() {

        boardStackPane.getChildren().clear();

        boardStackPane.getChildren().add(boardPane);
        boardStackPane.getChildren().add(boardProgressIndicator);

    }

    private void resetBoardProgressPane()
            throws Exception {

        setBoardProgressIndicator();

    }

    private void resetBoardPane()
            throws Exception {

        setTilesGroup();
        setFiguresGroup();

        setBoardPane();

    }

    private void setBoardPane() {

        boardPane.getChildren().clear();

        boardPane.getChildren().addAll(tilesGroup, figuresGroup);

    }

    private void newTilesGroup()
            throws Exception {

        tilesGroup = new Group();

        setTilesGroup();

    }

    private void setTilesGroup()
            throws Exception {

        tilesGroup.getChildren().clear();

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setTilesGroupWhitePov(); break;
            case Colors.BLACK: setTilesGroupBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setTilesGroupWhitePov();
        }

    }

    private void setTilesGroupWhitePov() {

        for (byte r = 0; r <= Squares.EDGE - 1; ++r) {
            for (byte f = 0; f <= Squares.EDGE - 1; ++f) {
                BoardTile tile = new BoardTile(boardTileEdge, f, r);
                tile.setBoardTileListener(new BoardTileListener() {

                    @Override
                    public void onMousePressedLeft(byte fromFile, byte fromRank)
                            throws Exception {

                        fromSquare = Squares.newSquare(fromFile, fromRank);

                        if (pieceToSet != null
                                || enPassantToSet) {
                            try {
                                if (isSetAllowed(fromSquare)) {
                                    addPiece(fromSquare);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                });
                tilesGroup.getChildren().add(tile);
            }
        }

    }

    private void setTilesGroupBlackPov() {

        for (int r = 0; r <= Squares.EDGE - 1; ++r) {
            for (int f = 0; f <= Squares.EDGE - 1; ++f) {
                byte fileBlackPov = (byte) (Squares.EDGE - 1 - f);
                byte rankBlackPov = (byte) (Squares.EDGE - 1 - r);
                BoardTile tile = new BoardTile(boardTileEdge, fileBlackPov, rankBlackPov);
                tile.setBoardTileListener(new BoardTileListener() {

                    @Override
                    public void onMousePressedLeft(byte fromFile, byte fromRank)
                            throws Exception {

                        byte fileBlackPov = (byte) (Squares.EDGE - 1 - fromFile);
                        byte rankBlackPov = (byte) (Squares.EDGE - 1 - fromRank);

                        fromSquare = Squares.newSquare(fileBlackPov, rankBlackPov);

                        if (pieceToSet != null
                                || enPassantToSet) {
                            try {
                                if (isSetAllowed(fromSquare)) {
                                    addPiece(fromSquare);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                });
                tilesGroup.getChildren().add(tile);
            }
        }

    }

    private void newFiguresGroup()
            throws Exception {

        figuresGroup = new Group();

        setFiguresGroup();

    }

    private void setFiguresGroup()
            throws Exception {

        figuresGroup.getChildren().clear();

        if (UserOptions.rotateBoardOption) {
            switch (node.playerColor) {
            case Colors.WHITE: setFiguresGroupWhitePov(); break;
            case Colors.BLACK: setFiguresGroupBlackPov(); break;
            default:
                throw new Exception("node.playerColor=" + node.playerColor);
            }
        } else {
            setFiguresGroupWhitePov();
        }

    }

    private void setFiguresGroupWhitePov()
            throws Exception {

        for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
            if (node.squarePieceMap[s] != null) {
                byte piece = node.squarePieceMap[s];
                byte file = Squares.file(s);
                byte rank = Squares.rank(s);
                figuresGroup.getChildren().add(newFigureWhitePov(piece, file, rank));
            }
        }

        if (node.enPassantSquare != null) {
            byte file = Squares.file(node.enPassantSquare);
            byte rank = Squares.rank(node.enPassantSquare);
            figuresGroup.getChildren().add(newEPFigureWhitePov(file, rank));
        }

    }

    private void setFiguresGroupBlackPov()
            throws Exception {

        for (byte s = 0; s <= Squares.SQUARES_NUMBER - 1; ++s) {
            if (node.squarePieceMap[s] != null) {
                byte piece = node.squarePieceMap[s];
                byte file = (byte) (Squares.EDGE - 1 - Squares.file(s));
                byte rank = (byte) (Squares.EDGE - 1 - Squares.rank(s));
                figuresGroup.getChildren().add(newFigureBlackPov(piece, file, rank));
            }
        }

        if (node.enPassantSquare != null) {
            byte file = (byte) (Squares.EDGE - 1 - Squares.file(node.enPassantSquare));
            byte rank = (byte) (Squares.EDGE - 1 - Squares.rank(node.enPassantSquare));
            figuresGroup.getChildren().add(newEPFigureBlackPov(file, rank));
        }

    }

    private BoardFigure newFigureWhitePov(byte piece, byte file, byte rank)
            throws Exception {

        BoardFigure figure = new BoardFigure(piece, file, rank, boardTileEdge);

        figure.setBoardFigureListener(new BoardFigureListener() {

            @Override
            public void onMousePressedLeft(byte piece, byte fromFile, byte fromRank)
                    throws Exception {

                fromSquare = Squares.newSquare(fromFile, fromRank);

                if (pieceToSet == null
                        && !enPassantToSet) {
                    resetAvailsGroupWhitePov(piece);
                } else {
                    try {
                        if (isSetAllowed(fromSquare)) {
                            addPiece(fromSquare);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onMouseReleasedLeft(byte toFile, byte toRank)
                    throws Exception {

                boardPane.getChildren().remove(availsGroup);

                byte toSquare = Squares.newSquare(toFile, toRank);

                if (toSquare != fromSquare) {

                    try {
                        movePiece(fromSquare, toSquare);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onMouseReleasedRight(byte file, byte rank)
                    throws Exception {
                try {

                    if (ownerStage.getClass() != GameStage.class) {
                        byte square = Squares.newSquare(file, rank);
                        removePiece(square);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        return figure;
    }

    protected void resetAvailsGroupWhitePov(byte piece) {

        availsGroup.getChildren().clear();

        Byte toSquare;
        byte toFile, toRank;

        for (int m = 0; m <= MOVES_ENGINE.movesListMaxIndex; ++m) {
            Move move = MOVES_ENGINE.movesList[m];
            if (move != null) {
                toSquare = null;
                switch (move.function) {
                case Functions.SHORT_CG:
                    if (piece == Pieces.WHITE_KING && node.playerColor == Colors.WHITE) {
                        toSquare = Squares.SQUARE_G1;
                    } else if (piece == Pieces.BLACK_KING && node.playerColor == Colors.BLACK) {
                        toSquare = Squares.SQUARE_G8;
                    }
                    break;
                case Functions.LONG_CG:
                    if (piece == Pieces.WHITE_KING && node.playerColor == Colors.WHITE) {
                        toSquare = Squares.SQUARE_C1;
                    } else if (piece == Pieces.BLACK_KING && node.playerColor == Colors.BLACK) {
                        toSquare = Squares.SQUARE_C8;
                    }
                    break;
                default:
                    if (move.piece == piece
                            && move.fromSquare == fromSquare) {
                        toSquare = move.toSquare;
                    }
                }
                if (toSquare != null) {
                    toFile = Squares.file(toSquare);
                    toRank = Squares.rank(toSquare);
                    try {
                        if (showAvailables) {
                            BoardAvail a = new BoardAvail(node.playerColor, toFile, toRank, boardTileEdge);
                            availsGroup.getChildren().add(a);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        boardPane.getChildren().remove(availsGroup);
        boardPane.getChildren().add(availsGroup);

        figuresGroup.toFront();

    }

    private BoardFigure newFigureBlackPov(byte piece, byte file, byte rank)
            throws Exception {

        BoardFigure figure = new BoardFigure(piece, file, rank, boardTileEdge);

        figure.setBoardFigureListener(new BoardFigureListener() {

            @Override
            public void onMousePressedLeft(byte piece, byte fromFile, byte fromRank)
                    throws Exception {

                byte fromFileBlackPov = (byte) (Squares.EDGE - 1 - fromFile);
                byte fromRankBlackPov = (byte) (Squares.EDGE - 1 - fromRank);
                fromSquare = Squares.newSquare(fromFileBlackPov, fromRankBlackPov);

                if (pieceToSet == null
                        && !enPassantToSet) {
                    resetAvailsGroupBlackPov(piece);
                } else {
                    try {
                        if (isSetAllowed(fromSquare)) {
                            addPiece(fromSquare);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onMouseReleasedLeft(byte toFile, byte toRank)
                    throws Exception {

                boardPane.getChildren().remove(availsGroup);

                byte toFileBlackPov = (byte) (Squares.EDGE - 1 - toFile);
                byte toRankBlackPov = (byte) (Squares.EDGE - 1 - toRank);
                byte toSquare = Squares.newSquare(toFileBlackPov, toRankBlackPov);

                if (toSquare != fromSquare) {

                    try {
                        movePiece(fromSquare, toSquare);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onMouseReleasedRight(byte file, byte rank)
                    throws Exception {

                byte fileBlackPov = (byte) (Squares.EDGE - 1 - file);
                byte rankBlackPov = (byte) (Squares.EDGE - 1 - rank);
                byte square = Squares.newSquare(fileBlackPov, rankBlackPov);

                try {

                    if (ownerStage.getClass() != GameStage.class) {
                        removePiece(square);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        return figure;
    }

    protected void resetAvailsGroupBlackPov(int piece) {

        availsGroup.getChildren().clear();

        Byte toSquare;
        byte toFile, toRank;

        for (int m = 0; m <= MOVES_ENGINE.movesListMaxIndex; ++m) {
            Move move = MOVES_ENGINE.movesList[m];
            if (move != null) {
                toSquare = null;
                switch (move.function) {
                case Functions.SHORT_CG:
                    if (piece == Pieces.WHITE_KING && node.playerColor == Colors.WHITE) {
                        toSquare = Squares.SQUARE_G1;
                    } else if (piece == Pieces.BLACK_KING && node.playerColor == Colors.BLACK) {
                        toSquare = Squares.SQUARE_G8;
                    }
                    break;
                case Functions.LONG_CG:
                    if (piece == Pieces.WHITE_KING && node.playerColor == Colors.WHITE) {
                        toSquare = Squares.SQUARE_C1;
                    } else if (piece == Pieces.BLACK_KING && node.playerColor == Colors.BLACK) {
                        toSquare = Squares.SQUARE_C8;
                    }
                    break;
                default:
                    if (move.piece == piece
                            && move.fromSquare == fromSquare) {
                        toSquare = move.toSquare;
                    }
                }
                if (toSquare != null) {
                    toFile = (byte) (Squares.EDGE - 1 - Squares.file(toSquare));
                    toRank = (byte) (Squares.EDGE - 1 - Squares.rank(toSquare));
                    try {
                        if (showAvailables) {
                            BoardAvail a = new BoardAvail(node.playerColor, toFile, toRank, boardTileEdge);
                            availsGroup.getChildren().add(a);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        boardPane.getChildren().remove(availsGroup);
        boardPane.getChildren().add(availsGroup);

        figuresGroup.toFront();

    }

    private BoardEPFigure newEPFigureWhitePov(byte file, byte rank)
            throws Exception {

        BoardEPFigure figure = new BoardEPFigure(node.playerColor, file, rank, boardTileEdge);

        figure.setBoardEPFigureListener(new BoardEPFigureListener() {

            @Override
            public void onMousePressedLeft(byte fromFile, byte fromRank) {

                fromSquare = Squares.newSquare(fromFile, fromRank);

            }

            @Override
            public void onMouseReleasedLeft(byte toFile, byte toRank)
                    throws Exception {

            	boardPane.getChildren().remove(availsGroup);

                byte toSquare = Squares.newSquare(toFile, toRank);

                if (toSquare != fromSquare) {

                    try {
                        movePiece(fromSquare, toSquare);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onMouseReleasedRight(byte file, byte rank)
                    throws Exception {

                byte square = Squares.newSquare(file, rank);

                try {

                    if (ownerStage.getClass() != GameStage.class) {
                        removePiece(square);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        return figure;
    }

    private BoardEPFigure newEPFigureBlackPov(byte file, byte rank)
            throws Exception {

        BoardEPFigure figure = new BoardEPFigure(node.playerColor, file, rank, boardTileEdge);

        figure.setBoardEPFigureListener(new BoardEPFigureListener() {

            @Override
            public void onMousePressedLeft(byte fromFile, byte fromRank) {

                byte fromFileBlackPov = (byte) (Squares.EDGE - 1 - fromFile);
                byte fromRankBlackPov = (byte) (Squares.EDGE - 1 - fromRank);
                fromSquare = Squares.newSquare(fromFileBlackPov, fromRankBlackPov);

            }

            @Override
            public void onMouseReleasedLeft(byte toFile, byte toRank)
                    throws Exception {

            	boardPane.getChildren().remove(availsGroup);

            	byte toFileBlackPov = (byte) (Squares.EDGE - 1 - toFile);
            	byte toRankBlackPov = (byte) (Squares.EDGE - 1 - toRank);
            	byte toSquare = Squares.newSquare(toFileBlackPov, toRankBlackPov);

                if (toSquare != fromSquare) {

                    try {
                        movePiece(fromSquare, toSquare);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onMouseReleasedRight(byte file, byte rank)
                    throws Exception {

                byte fileBlackPov = (byte) (Squares.EDGE - 1 - file);
                byte rankBlackPov = (byte) (Squares.EDGE - 1 - rank);
                byte square = Squares.newSquare(fileBlackPov, rankBlackPov);

                try {

                    if (ownerStage.getClass() != GameStage.class) {
                        removePiece(square);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        return figure;
    }

    private void movePiece(byte fromSquare, byte toSquare)
            throws Exception {

        if (toSquare != fromSquare) {

            Node oldNode = new Node(node);
            Move oldMove = move;

            if (node.enPassantSquare != null
                    && node.enPassantSquare == fromSquare) {
                if (isSetEnPassantAllowed(toSquare)) {
                    setEnPassantSquare(toSquare);
                }
            } else {
                if (node.squarePieceMap[fromSquare] != null) {
                    if (isMoveAllowed(fromSquare, toSquare)) {
                        movePieceBb(fromSquare, toSquare);
                        movePieceMap(fromSquare, toSquare);
                    }
                }
            }

            move = null;

            checkResetNodePane(oldNode, oldMove);

            if (nodePaneListener != null) {
                nodePaneListener.onPieceMoved(fromSquare, toSquare);
            }

        }

    }

    private boolean isMoveAllowed(byte fromSquare, byte toSquare) {

        long toSquareBb = (BitBoards.ONE << toSquare);

        switch (node.squarePieceMap[fromSquare]) {
        case Pieces.WHITE_PAWN:
        case Pieces.BLACK_PAWN:
            if (Squares.rank(toSquare) == Squares.RANK_1
                    || Squares.rank(toSquare) == Squares.RANK_8) {
                return false;
            }
            break;
        case Pieces.WHITE_KING:
            int blackKingSquare = Long.numberOfTrailingZeros(node.blackKingBb);
            if ((toSquareBb & BitBoards.KING_MOVES_LT[blackKingSquare]) != BitBoards.EMPTY) {
                return false;
            }
            break;
        case Pieces.BLACK_KING:
            int whiteKingSquare = Long.numberOfTrailingZeros(node.whiteKingBb);
            if ((toSquareBb & BitBoards.KING_MOVES_LT[whiteKingSquare]) != BitBoards.EMPTY) {
                return false;
            }
            break;
        }

        //noinspection RedundantIfStatement
        if (node.squarePieceMap[toSquare] != null
                && (node.squarePieceMap[toSquare] == Pieces.WHITE_KING
                    || node.squarePieceMap[toSquare] == Pieces.BLACK_KING)) {
            return false;
        }

        return true;
    }

    private void movePieceBb(int fromSquare, int toSquare)
            throws Exception {

        long fromSquareBb = (BitBoards.ONE << fromSquare);
        long toSquareBb   = (BitBoards.ONE << toSquare);

        if (node.enPassantSquare != null
                && node.enPassantSquare == toSquare) {
            node.enPassantSquare = null;
        } else {
            if (node.squarePieceMap[toSquare] != null) {
                switch (node.squarePieceMap[toSquare]) {
                case Pieces.WHITE_PAWN:   node.whitePawnsBb   &= ~toSquareBb; break;
                case Pieces.WHITE_KNIGHT: node.whiteKnightsBb &= ~toSquareBb; break;
                case Pieces.WHITE_BISHOP: node.whiteBishopsBb &= ~toSquareBb; break;
                case Pieces.WHITE_ROOK:   node.whiteRooksBb   &= ~toSquareBb; break;
                case Pieces.WHITE_QUEEN:  node.whiteQueensBb  &= ~toSquareBb; break;
                case Pieces.BLACK_PAWN:   node.blackPawnsBb   &= ~toSquareBb; break;
                case Pieces.BLACK_KNIGHT: node.blackKnightsBb &= ~toSquareBb; break;
                case Pieces.BLACK_BISHOP: node.blackBishopsBb &= ~toSquareBb; break;
                case Pieces.BLACK_ROOK:   node.blackRooksBb   &= ~toSquareBb; break;
                case Pieces.BLACK_QUEEN:  node.blackQueensBb  &= ~toSquareBb; break;
                }
            }
        }

        switch (node.squarePieceMap[fromSquare]) {
        case Pieces.WHITE_PAWN:   node.whitePawnsBb   &= ~fromSquareBb; node.whitePawnsBb   |= toSquareBb; break;
        case Pieces.WHITE_KNIGHT: node.whiteKnightsBb &= ~fromSquareBb; node.whiteKnightsBb |= toSquareBb; break;
        case Pieces.WHITE_BISHOP: node.whiteBishopsBb &= ~fromSquareBb; node.whiteBishopsBb |= toSquareBb; break;
        case Pieces.WHITE_ROOK:   node.whiteRooksBb   &= ~fromSquareBb; node.whiteRooksBb   |= toSquareBb; break;
        case Pieces.WHITE_QUEEN:  node.whiteQueensBb  &= ~fromSquareBb; node.whiteQueensBb  |= toSquareBb; break;
        case Pieces.WHITE_KING:   node.whiteKingBb    &= ~fromSquareBb; node.whiteKingBb    |= toSquareBb; break;
        case Pieces.BLACK_PAWN:   node.blackPawnsBb   &= ~fromSquareBb; node.blackPawnsBb   |= toSquareBb; break;
        case Pieces.BLACK_KNIGHT: node.blackKnightsBb &= ~fromSquareBb; node.blackKnightsBb |= toSquareBb; break;
        case Pieces.BLACK_BISHOP: node.blackBishopsBb &= ~fromSquareBb; node.blackBishopsBb |= toSquareBb; break;
        case Pieces.BLACK_ROOK:   node.blackRooksBb   &= ~fromSquareBb; node.blackRooksBb   |= toSquareBb; break;
        case Pieces.BLACK_QUEEN:  node.blackQueensBb  &= ~fromSquareBb; node.blackQueensBb  |= toSquareBb; break;
        case Pieces.BLACK_KING:   node.blackKingBb    &= ~fromSquareBb; node.blackKingBb    |= toSquareBb; break;
        }

    }

    private void movePieceMap(int fromSquare, int toSquare)
            throws Exception {

        node.squarePieceMap[toSquare] = node.squarePieceMap[fromSquare];
        node.squarePieceMap[fromSquare] = null;

    }

    private void addPiece(Byte square)
            throws Exception {

        Node oldNode;
        Move oldMove;

        if (enPassantToSet) {

            oldNode = new Node(node);
            oldMove = move;

            removePiece(square);
            setEnPassantSquare(square);

            enPassantToSet = false;

            setEnPassantSETB.setSelected(false);

            move = null;

            checkResetNodePane(oldNode, oldMove);

            if (nodePaneListener != null) {
                nodePaneListener.onPieceAdded(square);
            }

        }

        if (pieceToSet != null) {

            oldNode = new Node(node);
            oldMove = move;

            addPieceBb(square);
            addPieceMap(square);

            move = null;

            checkResetNodePane(oldNode, oldMove);

            if (nodePaneListener != null) {
                nodePaneListener.onPieceAdded(square);
            }

        }

    }

    private void addPieceBb(int toSquare)
            throws Exception {

        long toSquareBb = (BitBoards.ONE << toSquare);

        if (node.squarePieceMap[toSquare] != null) {
            switch (node.squarePieceMap[toSquare]) {
            case Pieces.WHITE_PAWN:   node.whitePawnsBb   &= ~toSquareBb; break;
            case Pieces.WHITE_KNIGHT: node.whiteKnightsBb &= ~toSquareBb; break;
            case Pieces.WHITE_BISHOP: node.whiteBishopsBb &= ~toSquareBb; break;
            case Pieces.WHITE_ROOK:   node.whiteRooksBb   &= ~toSquareBb; break;
            case Pieces.WHITE_QUEEN:  node.whiteQueensBb  &= ~toSquareBb; break;
            case Pieces.BLACK_PAWN:   node.blackPawnsBb   &= ~toSquareBb; break;
            case Pieces.BLACK_KNIGHT: node.blackKnightsBb &= ~toSquareBb; break;
            case Pieces.BLACK_BISHOP: node.blackBishopsBb &= ~toSquareBb; break;
            case Pieces.BLACK_ROOK:   node.blackRooksBb   &= ~toSquareBb; break;
            case Pieces.BLACK_QUEEN:  node.blackQueensBb  &= ~toSquareBb; break;
            default:
                throw new Exception("node.squarePieceMap[toSquare]=" + node.squarePieceMap[toSquare]);
            }
        }
        switch (pieceToSet) {
        case Pieces.WHITE_PAWN:   node.whitePawnsBb   |= toSquareBb; break;
        case Pieces.WHITE_KNIGHT: node.whiteKnightsBb |= toSquareBb; break;
        case Pieces.WHITE_BISHOP: node.whiteBishopsBb |= toSquareBb; break;
        case Pieces.WHITE_ROOK:   node.whiteRooksBb   |= toSquareBb; break;
        case Pieces.WHITE_QUEEN:  node.whiteQueensBb  |= toSquareBb; break;
        case Pieces.BLACK_PAWN:   node.blackPawnsBb   |= toSquareBb; break;
        case Pieces.BLACK_KNIGHT: node.blackKnightsBb |= toSquareBb; break;
        case Pieces.BLACK_BISHOP: node.blackBishopsBb |= toSquareBb; break;
        case Pieces.BLACK_ROOK:   node.blackRooksBb   |= toSquareBb; break;
        case Pieces.BLACK_QUEEN:  node.blackQueensBb  |= toSquareBb; break;
        default:
            throw new Exception("pieceToSet=" + pieceToSet);
        }

    }

    private void addPieceMap(int square) {

        node.squarePieceMap[square] = pieceToSet;

    }

    private void removePiece(Byte square)
            throws Exception {

        Node oldNode = new Node(node);
        Move oldMove = move;

        if (node.enPassantSquare != null
        		&& node.enPassantSquare.equals(square)) {
        	node.enPassantSquare = null;
        } else {
            removePieceBb(square);
            removePieceMap(square);
        }

        move = null;

        checkResetNodePane(oldNode, oldMove);

        if (nodePaneListener != null) {
            nodePaneListener.onPieceRemoved(square);
        }

    }

    private void removePieceBb(int fromSquare)
            throws Exception {

        long toSquareBb = (BitBoards.ONE << fromSquare);

        if (node.squarePieceMap[fromSquare] != null
                && node.squarePieceMap[fromSquare] != Pieces.WHITE_KING
                && node.squarePieceMap[fromSquare] != Pieces.BLACK_KING) {
            switch (node.squarePieceMap[fromSquare]) {
            case Pieces.WHITE_PAWN:   node.whitePawnsBb   &= ~toSquareBb; break;
            case Pieces.WHITE_KNIGHT: node.whiteKnightsBb &= ~toSquareBb; break;
            case Pieces.WHITE_BISHOP: node.whiteBishopsBb &= ~toSquareBb; break;
            case Pieces.WHITE_ROOK:   node.whiteRooksBb   &= ~toSquareBb; break;
            case Pieces.WHITE_QUEEN:  node.whiteQueensBb  &= ~toSquareBb; break;
            case Pieces.BLACK_PAWN:   node.blackPawnsBb   &= ~toSquareBb; break;
            case Pieces.BLACK_KNIGHT: node.blackKnightsBb &= ~toSquareBb; break;
            case Pieces.BLACK_BISHOP: node.blackBishopsBb &= ~toSquareBb; break;
            case Pieces.BLACK_ROOK:   node.blackRooksBb   &= ~toSquareBb; break;
            case Pieces.BLACK_QUEEN:  node.blackQueensBb  &= ~toSquareBb; break;
            default:
                throw new Exception("fromSquare=" + fromSquare
                        + " node.squarePieceMap[fromSquare]=" + node.squarePieceMap[fromSquare]);
            }
        }

    }

    private void removePieceMap(int fromSquare)
            throws Exception {

        node.squarePieceMap[fromSquare] = null;

    }

    private void setEnPassantSquare(byte square)
            throws Exception {

        if (isSetEnPassantAllowed(square)) {
            node.enPassantSquare = square;
            switch (Squares.rank(square)) {
            case Squares.RANK_3:
                node.playerColor = Colors.BLACK;
                pieceToSet = Pieces.WHITE_PAWN;
                if (isSetAllowed(square)) {
                    addPieceBb(square - Squares.EDGE);
                    addPieceMap(square - Squares.EDGE);
                    pieceToSet = null;
                    removePieceBb(square + Squares.EDGE);
                    removePieceMap(square + Squares.EDGE);
                }
                break;
            case Squares.RANK_6:
                node.playerColor = Colors.WHITE;
                pieceToSet = Pieces.BLACK_PAWN;
                if (isSetAllowed(square)) {
                    addPieceBb(square + Squares.EDGE);
                    addPieceMap(square + Squares.EDGE);
                    pieceToSet = null;
                    removePieceBb(square - Squares.EDGE);
                    removePieceMap(square - Squares.EDGE);
                }
                break;
            }
        }

    }

    private boolean isSetEnPassantAllowed(byte toSquare) {

        //noinspection RedundantIfStatement
        if ((Squares.rank(toSquare) == Squares.RANK_3)
                || (Squares.rank(toSquare) == Squares.RANK_6)) {
            return true;
        }

        return false;
    }

    private boolean isSetAllowed(byte toSquare) {

        if (node.squarePieceMap[toSquare] != null
                && (node.squarePieceMap[toSquare] == Pieces.WHITE_KING
                    || node.squarePieceMap[toSquare] == Pieces.BLACK_KING)) {
            return false;
        }

        //noinspection RedundantIfStatement
        if (pieceToSet != null
                && (pieceToSet == Pieces.WHITE_PAWN
                    || pieceToSet == Pieces.BLACK_PAWN)
                        && (Squares.rank(toSquare) == Squares.RANK_1
                            || Squares.rank(toSquare) == Squares.RANK_8)) {
            return false;
        }

        return true;
    }

    private void newAvailsGroup() {

        availsGroup = new Group();

    }

    private void newMoveLabel() {

        moveLabel = new Label();

        moveLabel.setEffect(controlsDropShadow);

        setMoveLabel();

    }

    private void resetMoveLabel() {

        setMoveLabel();

    }

    private void setMoveLabel() {

        if (move == null) {
            moveLabel.setText("initial position");
        } else {
            moveLabel.setText(move.toString());
        }

        moveLabel.setFont(new Font("", boardTileEdge / 3.));

    }

    private void newMatDiffScrollPane()
            throws Exception {

        newMatDiffGridPane();

        matDiffScrollPane.setStyle("-fx-background-color:transparent;");
        matDiffScrollPane.setPrefWidth(capturedEdge * 3);
        matDiffScrollPane.setPrefHeight(capturedEdge * 3);

        setMatDiffScrollPane();

    }

    private void resetMatDiffScrollPane()
            throws Exception {

        resetMatDiffGridPane();

    }

    private void setMatDiffScrollPane()
            throws Exception {

        matDiffScrollPane.setContent(matDiffGridPane);

    }

    private void newMatDiffGridPane()
            throws Exception {

        setMatDiffGridPane();

    }

    private void resetMatDiffGridPane()
            throws Exception {

        setMatDiffGridPane();

    }

    private void setMatDiffGridPane()
            throws Exception {

        setMaterialDiffLists();

        matDiffGridPane.getChildren().clear();

        int matDiffCounter;

        matDiffCounter = 0;
        for (Byte piece : whiteMatDiffList) {
            ImageView pieceImageView = null;
            if (piece == null) {
                pieceImageView = new ImageView();
            } else {
                String s = "file:\\" + ViewUtils.NODEPANE_FIGURES_PATH;
                switch (piece) {
                case Pieces.WHITE_PAWN:   pieceImageView = new ImageView(s + "\\cwp.png"); break;
                case Pieces.WHITE_KNIGHT: pieceImageView = new ImageView(s + "\\cwn.png"); break;
                case Pieces.WHITE_BISHOP: pieceImageView = new ImageView(s + "\\cwb.png"); break;
                case Pieces.WHITE_ROOK:   pieceImageView = new ImageView(s + "\\cwr.png"); break;
                case Pieces.WHITE_QUEEN:  pieceImageView = new ImageView(s + "\\cwq.png"); break;
                }
            }
            assert pieceImageView != null;
            pieceImageView.setFitWidth(capturedEdge);
            pieceImageView.setFitHeight(capturedEdge);
            ++matDiffCounter;
            matDiffGridPane.add(pieceImageView, 0, matDiffCounter);
        }

        matDiffCounter = 0;
        for (Byte piece : blackMatDiffList) {
            ImageView pieceImageView = null;
            if (piece == null) {
                pieceImageView = new ImageView();
            } else {
                switch (piece) {
                case Pieces.BLACK_PAWN:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\cbp.png"); break;
                case Pieces.BLACK_KNIGHT: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\cbn.png"); break;
                case Pieces.BLACK_BISHOP: pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\cbb.png"); break;
                case Pieces.BLACK_ROOK:   pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\cbr.png"); break;
                case Pieces.BLACK_QUEEN:  pieceImageView = new ImageView("file:\\" + ViewUtils.NODEPANE_FIGURES_PATH + "\\cbq.png"); break;
                }
            }
            assert pieceImageView != null;
            pieceImageView.setFitWidth(capturedEdge);
            pieceImageView.setFitHeight(capturedEdge);
            ++matDiffCounter;
            matDiffGridPane.add(pieceImageView, 1, matDiffCounter);
        }

        matDiffGridPane.setEffect(controlsDropShadow);

//        matDiffGridPane.setGridLinesVisible(true);

    }

    private void setMaterialDiffLists() {

        whiteMatDiffList.clear();
        blackMatDiffList.clear();

        int pawnsDiff   = node.whitePawnsCounter   - node.blackPawnsCounter;
        int knightsDiff = node.whiteKnightsCounter - node.blackKnightsCounter;
        int bishopsDiff = node.whiteBishopsCounter - node.blackBishopsCounter;
        int rooksDiff   = node.whiteRooksCounter   - node.blackRooksCounter;
        int queensDiff  = node.whiteQueensCounter  - node.blackQueensCounter;

        for (int i = 1; i <= Math.abs(pawnsDiff); ++i) {
            if (Math.signum(pawnsDiff) > 0) {
                whiteMatDiffList.add(Pieces.WHITE_PAWN); blackMatDiffList.add(null);
            } else {
                blackMatDiffList.add(Pieces.BLACK_PAWN); whiteMatDiffList.add(null);
            }
        }

        for (int i = 1; i <= Math.abs(knightsDiff); ++i) {
            if (Math.signum(knightsDiff) > 0) {
                whiteMatDiffList.add(Pieces.WHITE_KNIGHT); blackMatDiffList.add(null);
            } else {
                blackMatDiffList.add(Pieces.BLACK_KNIGHT); whiteMatDiffList.add(null);
            }
        }

        for (int i = 1; i <= Math.abs(bishopsDiff); ++i) {
            if (Math.signum(bishopsDiff) > 0) {
                whiteMatDiffList.add(Pieces.WHITE_BISHOP); blackMatDiffList.add(null);
            } else {
                blackMatDiffList.add(Pieces.BLACK_BISHOP); whiteMatDiffList.add(null);
            }
        }

        for (int i = 1; i <= Math.abs(rooksDiff); ++i) {
            if (Math.signum(rooksDiff) > 0) {
                whiteMatDiffList.add(Pieces.WHITE_ROOK); blackMatDiffList.add(null);
            } else {
                blackMatDiffList.add(Pieces.BLACK_ROOK); whiteMatDiffList.add(null);
            }
        }

        for (int i = 1; i <= Math.abs(queensDiff); ++i) {
            if (Math.signum(queensDiff) > 0) {
                whiteMatDiffList.add(Pieces.WHITE_QUEEN); blackMatDiffList.add(null);
            } else {
                blackMatDiffList.add(Pieces.BLACK_QUEEN); whiteMatDiffList.add(null);
            }
        }

    }

    private void newNodeQuantitiesGridPane()
            throws Exception {

        double labelWidth;
        double labelHeight;

        labelWidth = 120;
        labelHeight = 30;

        halfmovesLabel.setPrefSize(labelWidth, labelHeight);
        fullmovesLabel.setPrefSize(labelWidth, labelHeight);
        gameStateLabel.setPrefSize(labelWidth, labelHeight);
        treeLevelLabel.setPrefSize(labelWidth, labelHeight);
        nodeHashcodeLabel.setPrefSize(labelWidth, labelHeight);

        newHalfmovesSpinner();
        newFullmovesSpinner();
        gameStateValueLabel.setText(States.toString(node.gameState));
        treeLevelValueLabel.setText(String.valueOf(node.treeLevel));
        nodeHashcodeValueLabel.setText(String.valueOf(node.nodeHashCode));

        labelWidth = 300;

        gameStateValueLabel.setPrefSize(labelWidth, labelHeight);
        treeLevelValueLabel.setPrefSize(labelWidth, labelHeight);
        nodeHashcodeValueLabel.setPrefSize(labelWidth, labelHeight);

        setNodeQuantitiesGridPane();

    }

    private void resetNodeQuantitiesGridPane()
            throws Exception {

        resetFullmovesSpinner();
        resetHalfmovesSpinner();

        gameStateValueLabel.setText(States.toString(node.gameState));
        treeLevelValueLabel.setText(String.valueOf(node.treeLevel));
        nodeHashcodeValueLabel.setText(String.valueOf(node.nodeHashCode));

        setNodeQuantitiesGridPane();

    }

    private void setNodeQuantitiesGridPane() {

        nodeQuantitiesGridPane.getChildren().clear();

        nodeQuantitiesGridPane.add(halfmovesLabel,         0, 0);
        nodeQuantitiesGridPane.add(halfmovesSpinner,       1, 0);
        nodeQuantitiesGridPane.add(fullmovesLabel,         0, 1);
        nodeQuantitiesGridPane.add(fullmovesSpinner,       1, 1);
        nodeQuantitiesGridPane.add(gameStateLabel,         0, 2);
        nodeQuantitiesGridPane.add(gameStateValueLabel,    1, 2);
        nodeQuantitiesGridPane.add(treeLevelLabel,         0, 3);
        nodeQuantitiesGridPane.add(treeLevelValueLabel,    1, 3);
        nodeQuantitiesGridPane.add(nodeHashcodeLabel,      0, 4);
        nodeQuantitiesGridPane.add(nodeHashcodeValueLabel, 1, 4);

//        nodeQuantitiesGridPane.setGridLinesVisible(true);

    }

    private void newHalfmovesSpinner() {

        if (halfmovesChangeListener != null) {
            halfmovesSpinner.valueProperty().removeListener(halfmovesChangeListener);
        }

        halfmovesSpinner.getValueFactory().setValue((int) node.halfmovesClock);
        halfmovesSpinner.setEditable(true);

        halfmovesChangeListener = (ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) -> {
            try {

                Node oldNode = new Node(node);
                Move oldMove = move;

                node.halfmovesClock = halfmovesSpinner.getValue().shortValue();

                checkResetNodePane(oldNode, oldMove);

                if (nodePaneListener != null) {
                    nodePaneListener.onBoardReset(node);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        halfmovesSpinner.valueProperty().addListener(halfmovesChangeListener);

    }

    private void resetHalfmovesSpinner() {

        halfmovesSpinner.getValueFactory().setValue((int) node.halfmovesClock);

    }

    private void newFullmovesSpinner() {

        if (fullmovesChangeListener != null) {
            fullmovesSpinner.valueProperty().removeListener(fullmovesChangeListener);
        }

        fullmovesSpinner.getValueFactory().setValue((int) node.fullmovesNumber);
        fullmovesSpinner.setEditable(true);

        fullmovesChangeListener = (ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) -> {
            try {

                node.fullmovesNumber = fullmovesSpinner.getValue().shortValue();

                resetNodePane(node, move); // no checkReset

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        fullmovesSpinner.valueProperty().addListener(fullmovesChangeListener);

    }

    private void resetFullmovesSpinner() {

        fullmovesSpinner.getValueFactory().setValue((int) node.fullmovesNumber);

    }

    public void disableControls() {

        disabledHBox.setDisable(false);
        disabledHBox.setVisible(true);

    }

}
