package application.uci;

/**
 * Contiene alcuni metodi chiamati da {@link UciEngine} e {@link UciGui}.
 */
public class UciUtils {

    /**
     * Restituisce la parte di testo, in un testo dato, che segue una parola data.
     *
     * @param string Una stringa che può contenere una o più parole.
     * @param word La parola di riferimento.
     * @return Il testo successivo alla parola data.
     */
    public static String substringAfter(final String string, final String word) {

        return string.substring(string.indexOf(word) + word.length() + 1);
    }

    /**
     * Restituisce la parola successiva, in un testo, di una parola data.
     *
     * @param string Una stringa che può contenere una o più parole.
     * @param word La parola di riferimento.
     * @return La parola successiva alla parola data.
     */
    public static String wordAfter(final String string, final String word) {

        String s = substringAfter(string, word);

        if (s.contains(" ")) {
            return s.substring(0, s.indexOf(" "));
        } else {
            return s;
        }

    }

}
