package model.game;

import engine.model.Engine;
import model.elements.Kinds;

public final class Player {

    public byte kind;
    public Engine engine;

    public Player(final byte newKind, final Engine newEngine) {
        kind = newKind;
        engine = newEngine;
    }

    /**
     * @return La descrizione del giocatore.
     * Ad es.:
     *  * motore di default: E@default
     *  * motore personalizzato: E@778ae0b8
     *  * umano con motore di default come tutor: H@default
     *  * umano con motore personalizzato come tutor: H@778ae0b8
     */
    @Override
    public String toString() {

        try {
            return Kinds.toString(kind).charAt(0) + engine.name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
