package model.elements;

public abstract class FileSets {

    // base numerica utilizzata -- per parseUnsignedLong/toUnsignedString
    public static final byte BINARY_RADIX = 2;

    public static String toString(int fileSet) {

        StringBuilder fileSetSb;

        fileSetSb = new StringBuilder(Integer.toUnsignedString(fileSet, BINARY_RADIX));

        while (fileSetSb.length() < 8) {
            fileSetSb.insert(0, "0");
        }

        final StringBuilder outFileSetSb;

        outFileSetSb = new StringBuilder();
        outFileSetSb.append(" ");
        for (int f = 7; f >= 0; --f) {
            // +1 fisso per metodo
            outFileSetSb.append(fileSetSb.charAt(f));
            outFileSetSb.append(" ");
        }

        return outFileSetSb.toString()
        					      .replaceAll("0", "-")
        					          .replaceAll("1", "x");
    }

}
