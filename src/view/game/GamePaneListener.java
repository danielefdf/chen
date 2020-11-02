package view.game;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface GamePaneListener {

    void onMousePressed(MouseEvent me);

    void onKeyPressed(KeyEvent ke);

}
