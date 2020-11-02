package model.elements;

import model.notations.San;
import model.figures.Alpha2;
import model.notations.Fen;
import model.figures.Utf8;

public abstract class Squares {

    // lato della scacchiera
    public static final byte EDGE = 8;

    // numero case
    public static final byte SQUARES_NUMBER = 64;

    public static final byte
            SQUARE_A1 = 56, SQUARE_B1 = 57, SQUARE_C1 = 58, SQUARE_D1 = 59, SQUARE_E1 = 60, SQUARE_F1 = 61, SQUARE_G1 = 62, SQUARE_H1 = 63,
            SQUARE_A2 = 48, SQUARE_B2 = 49, SQUARE_C2 = 50, SQUARE_D2 = 51, SQUARE_E2 = 52, SQUARE_F2 = 53, SQUARE_G2 = 54, SQUARE_H2 = 55,
            SQUARE_A3 = 40, SQUARE_B3 = 41, SQUARE_C3 = 42, SQUARE_D3 = 43, SQUARE_E3 = 44, SQUARE_F3 = 45, SQUARE_G3 = 46, SQUARE_H3 = 47,
            SQUARE_A4 = 32, SQUARE_B4 = 33, SQUARE_C4 = 34, SQUARE_D4 = 35, SQUARE_E4 = 36, SQUARE_F4 = 37, SQUARE_G4 = 38, SQUARE_H4 = 39,
            SQUARE_A5 = 24, SQUARE_B5 = 25, SQUARE_C5 = 26, SQUARE_D5 = 27, SQUARE_E5 = 28, SQUARE_F5 = 29, SQUARE_G5 = 30, SQUARE_H5 = 31,
            SQUARE_A6 = 16, SQUARE_B6 = 17, SQUARE_C6 = 18, SQUARE_D6 = 19, SQUARE_E6 = 20, SQUARE_F6 = 21, SQUARE_G6 = 22, SQUARE_H6 = 23,
            SQUARE_A7 =  8, SQUARE_B7 =  9, SQUARE_C7 = 10, SQUARE_D7 = 11, SQUARE_E7 = 12, SQUARE_F7 = 13, SQUARE_G7 = 14, SQUARE_H7 = 15,
            SQUARE_A8 =  0, SQUARE_B8 =  1, SQUARE_C8 =  2, SQUARE_D8 =  3, SQUARE_E8 =  4, SQUARE_F8 =  5, SQUARE_G8 =  6, SQUARE_H8 =  7;

    public static final byte FILES_NUMBER = 8, RANKS_NUMBER = 8;

    public static final byte FILE_A = 0, FILE_B = 1, FILE_C = 2, FILE_D = 3, FILE_E = 4, FILE_F = 5, FILE_G = 6, FILE_H = 7;
    public static final byte RANK_1 = 7, RANK_2 = 6, RANK_3 = 5, RANK_4 = 4, RANK_5 = 3, RANK_6 = 2, RANK_7 = 1, RANK_8 = 0;

    public static final byte
            NORTH_EAST_EAST_STEP  = -6,
            NORTH_EAST_STEP       = -7,
            NORTH_NORTH_EAST_STEP = -15,
            NORTH_STEP            = -8, NORTH_DOUBLE_STEP = -16,
            NORTH_NORTH_WEST_STEP = -17,
            NORTH_WEST_STEP       = -9,
            NORTH_WEST_WEST_STEP  = -10,
            WEST_STEP             = -1,
            SOUTH_WEST_WEST_STEP  = +6,
            SOUTH_WEST_STEP       = +7,
            SOUTH_SOUTH_WEST_STEP = +15,
            SOUTH_STEP            = +8, SOUTH_DOUBLE_STEP = +16,
            SOUTH_SOUTH_EAST_STEP = +17,
            SOUTH_EAST_STEP       = +9,
            SOUTH_EAST_EAST_STEP  = +10,
            EAST_STEP             = +1;

    public static final byte[] NORTH_EAST_EAST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_G7, SQUARE_H7,
            SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7, SQUARE_H7, SQUARE_G6, SQUARE_H6,
            SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6, SQUARE_H6, SQUARE_G5, SQUARE_H5,
            SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5, SQUARE_H5, SQUARE_G4, SQUARE_H4,
            SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4, SQUARE_H4, SQUARE_G3, SQUARE_H3,
            SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3, SQUARE_H3, SQUARE_G2, SQUARE_H2,
            SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2, SQUARE_H2, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] NORTH_EAST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H7,
            SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H7, SQUARE_H6,
            SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H7, SQUARE_H6, SQUARE_H5,
            SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H7, SQUARE_H6, SQUARE_H5, SQUARE_H4,
            SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H7, SQUARE_H6, SQUARE_H5, SQUARE_H4, SQUARE_H3,
            SQUARE_G8, SQUARE_H8, SQUARE_H7, SQUARE_H6, SQUARE_H5, SQUARE_H4, SQUARE_H3, SQUARE_H2,
            SQUARE_H8, SQUARE_H7, SQUARE_H6, SQUARE_H5, SQUARE_H4, SQUARE_H3, SQUARE_H2, SQUARE_H1
    };
    public static final byte[] NORTH_NORTH_EAST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A7, SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7, SQUARE_H7,
            SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8, SQUARE_H6,
            SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7, SQUARE_H7, SQUARE_H5,
            SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6, SQUARE_H6, SQUARE_H4,
            SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5, SQUARE_H5, SQUARE_H3,
            SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4, SQUARE_H4, SQUARE_H2,
            SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3, SQUARE_H3, SQUARE_H1
    };
    public static final byte[] NORTH_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8
    };
    public static final byte[] NORTH_NORTH_WEST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A7, SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7, SQUARE_H7,
            SQUARE_A6, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8,
            SQUARE_A5, SQUARE_A7, SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7,
            SQUARE_A4, SQUARE_A6, SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6,
            SQUARE_A3, SQUARE_A5, SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5,
            SQUARE_A2, SQUARE_A4, SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4,
            SQUARE_A1, SQUARE_A3, SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3
    };
    public static final byte[] NORTH_WEST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A7, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8,
            SQUARE_A6, SQUARE_A7, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8,
            SQUARE_A5, SQUARE_A6, SQUARE_A7, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8,
            SQUARE_A4, SQUARE_A5, SQUARE_A6, SQUARE_A7, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8,
            SQUARE_A3, SQUARE_A4, SQUARE_A5, SQUARE_A6, SQUARE_A7, SQUARE_A8, SQUARE_B8, SQUARE_C8,
            SQUARE_A2, SQUARE_A3, SQUARE_A4, SQUARE_A5, SQUARE_A6, SQUARE_A7, SQUARE_A8, SQUARE_B8,
            SQUARE_A1, SQUARE_A2, SQUARE_A3, SQUARE_A4, SQUARE_A5, SQUARE_A6, SQUARE_A7, SQUARE_A8
    };
    public static final byte[] NORTH_WEST_WEST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8, SQUARE_G8, SQUARE_H8,
            SQUARE_A7, SQUARE_B7, SQUARE_A8, SQUARE_B8, SQUARE_C8, SQUARE_D8, SQUARE_E8, SQUARE_F8,
            SQUARE_A6, SQUARE_B6, SQUARE_A7, SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7,
            SQUARE_A5, SQUARE_B5, SQUARE_A6, SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6,
            SQUARE_A4, SQUARE_B4, SQUARE_A5, SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5,
            SQUARE_A3, SQUARE_B3, SQUARE_A4, SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4,
            SQUARE_A2, SQUARE_B2, SQUARE_A3, SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3,
            SQUARE_A1, SQUARE_B1, SQUARE_A2, SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2
    };
    public static final byte[] WEST_END = {
            SQUARE_A8, SQUARE_A8, SQUARE_A8, SQUARE_A8, SQUARE_A8, SQUARE_A8, SQUARE_A8, SQUARE_A8,
            SQUARE_A7, SQUARE_A7, SQUARE_A7, SQUARE_A7, SQUARE_A7, SQUARE_A7, SQUARE_A7, SQUARE_A7,
            SQUARE_A6, SQUARE_A6, SQUARE_A6, SQUARE_A6, SQUARE_A6, SQUARE_A6, SQUARE_A6, SQUARE_A6,
            SQUARE_A5, SQUARE_A5, SQUARE_A5, SQUARE_A5, SQUARE_A5, SQUARE_A5, SQUARE_A5, SQUARE_A5,
            SQUARE_A4, SQUARE_A4, SQUARE_A4, SQUARE_A4, SQUARE_A4, SQUARE_A4, SQUARE_A4, SQUARE_A4,
            SQUARE_A3, SQUARE_A3, SQUARE_A3, SQUARE_A3, SQUARE_A3, SQUARE_A3, SQUARE_A3, SQUARE_A3,
            SQUARE_A2, SQUARE_A2, SQUARE_A2, SQUARE_A2, SQUARE_A2, SQUARE_A2, SQUARE_A2, SQUARE_A2,
            SQUARE_A1, SQUARE_A1, SQUARE_A1, SQUARE_A1, SQUARE_A1, SQUARE_A1, SQUARE_A1, SQUARE_A1
    };
    public static final byte[] SOUTH_WEST_WEST_END = {
            SQUARE_A8, SQUARE_B8, SQUARE_A7, SQUARE_B7, SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7,
            SQUARE_A7, SQUARE_B7, SQUARE_A6, SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6,
            SQUARE_A6, SQUARE_B6, SQUARE_A5, SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5,
            SQUARE_A5, SQUARE_B5, SQUARE_A4, SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4,
            SQUARE_A4, SQUARE_B4, SQUARE_A3, SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3,
            SQUARE_A3, SQUARE_B3, SQUARE_A2, SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2,
            SQUARE_A2, SQUARE_B2, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_WEST_END = {
            SQUARE_A8, SQUARE_A7, SQUARE_A6, SQUARE_A5, SQUARE_A4, SQUARE_A3, SQUARE_A2, SQUARE_A1,
            SQUARE_A7, SQUARE_A6, SQUARE_A5, SQUARE_A4, SQUARE_A3, SQUARE_A2, SQUARE_A1, SQUARE_B1,
            SQUARE_A6, SQUARE_A5, SQUARE_A4, SQUARE_A3, SQUARE_A2, SQUARE_A1, SQUARE_B1, SQUARE_C1,
            SQUARE_A5, SQUARE_A4, SQUARE_A3, SQUARE_A2, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1,
            SQUARE_A4, SQUARE_A3, SQUARE_A2, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1,
            SQUARE_A3, SQUARE_A2, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1,
            SQUARE_A2, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_SOUTH_WEST_END = {
            SQUARE_A8, SQUARE_A6, SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6,
            SQUARE_A7, SQUARE_A5, SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5,
            SQUARE_A6, SQUARE_A4, SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4,
            SQUARE_A5, SQUARE_A3, SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3,
            SQUARE_A4, SQUARE_A2, SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2,
            SQUARE_A3, SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1,
            SQUARE_A2, SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2, SQUARE_H2,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_END = {
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_SOUTH_EAST_END = {
            SQUARE_B6, SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6, SQUARE_H6, SQUARE_H8,
            SQUARE_B5, SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5, SQUARE_H5, SQUARE_H7,
            SQUARE_B4, SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4, SQUARE_H4, SQUARE_H6,
            SQUARE_B3, SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3, SQUARE_H3, SQUARE_H5,
            SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2, SQUARE_H2, SQUARE_H4,
            SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H3,
            SQUARE_A2, SQUARE_B2, SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2, SQUARE_H2,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_EAST_END = {
            SQUARE_H1, SQUARE_H2, SQUARE_H3, SQUARE_H4, SQUARE_H5, SQUARE_H6, SQUARE_H7, SQUARE_H8,
            SQUARE_G1, SQUARE_H1, SQUARE_H2, SQUARE_H3, SQUARE_H4, SQUARE_H5, SQUARE_H6, SQUARE_H7,
            SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H2, SQUARE_H3, SQUARE_H4, SQUARE_H5, SQUARE_H6,
            SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H2, SQUARE_H3, SQUARE_H4, SQUARE_H5,
            SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H2, SQUARE_H3, SQUARE_H4,
            SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H2, SQUARE_H3,
            SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_H2,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] SOUTH_EAST_EAST_END = {
            SQUARE_C7, SQUARE_D7, SQUARE_E7, SQUARE_F7, SQUARE_G7, SQUARE_H7, SQUARE_G8, SQUARE_H8,
            SQUARE_C6, SQUARE_D6, SQUARE_E6, SQUARE_F6, SQUARE_G6, SQUARE_H6, SQUARE_G7, SQUARE_H7,
            SQUARE_C5, SQUARE_D5, SQUARE_E5, SQUARE_F5, SQUARE_G5, SQUARE_H5, SQUARE_G6, SQUARE_H6,
            SQUARE_C4, SQUARE_D4, SQUARE_E4, SQUARE_F4, SQUARE_G4, SQUARE_H4, SQUARE_G5, SQUARE_H5,
            SQUARE_C3, SQUARE_D3, SQUARE_E3, SQUARE_F3, SQUARE_G3, SQUARE_H3, SQUARE_G4, SQUARE_H4,
            SQUARE_C2, SQUARE_D2, SQUARE_E2, SQUARE_F2, SQUARE_G2, SQUARE_H2, SQUARE_G3, SQUARE_H3,
            SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1, SQUARE_G2, SQUARE_H2,
            SQUARE_A1, SQUARE_B1, SQUARE_C1, SQUARE_D1, SQUARE_E1, SQUARE_F1, SQUARE_G1, SQUARE_H1
    };
    public static final byte[] EAST_END = {
            SQUARE_H8, SQUARE_H8, SQUARE_H8, SQUARE_H8, SQUARE_H8, SQUARE_H8, SQUARE_H8, SQUARE_H8,
            SQUARE_H7, SQUARE_H7, SQUARE_H7, SQUARE_H7, SQUARE_H7, SQUARE_H7, SQUARE_H7, SQUARE_H7,
            SQUARE_H6, SQUARE_H6, SQUARE_H6, SQUARE_H6, SQUARE_H6, SQUARE_H6, SQUARE_H6, SQUARE_H6,
            SQUARE_H5, SQUARE_H5, SQUARE_H5, SQUARE_H5, SQUARE_H5, SQUARE_H5, SQUARE_H5, SQUARE_H5,
            SQUARE_H4, SQUARE_H4, SQUARE_H4, SQUARE_H4, SQUARE_H4, SQUARE_H4, SQUARE_H4, SQUARE_H4,
            SQUARE_H3, SQUARE_H3, SQUARE_H3, SQUARE_H3, SQUARE_H3, SQUARE_H3, SQUARE_H3, SQUARE_H3,
            SQUARE_H2, SQUARE_H2, SQUARE_H2, SQUARE_H2, SQUARE_H2, SQUARE_H2, SQUARE_H2, SQUARE_H2,
            SQUARE_H1, SQUARE_H1, SQUARE_H1, SQUARE_H1, SQUARE_H1, SQUARE_H1, SQUARE_H1, SQUARE_H1
    };

    public static String toString(final byte square)
            throws Exception {

        return toSan(square);
    }

    public static byte color(final byte square) {

        // vedi squares.xlsx
        if (((square % EDGE) + (square / EDGE)) % 2 == 0) {
            return Colors.WHITE;
        } else {
            return Colors.BLACK;
        }

    }

    // view/export only
    public static byte file(final byte square) {

        return (byte) (square % Squares.EDGE);
    }

    // view/export only
    public static byte rank(final byte square) {

        return (byte) (square / Squares.EDGE);
    }

    // view/export only
    public static byte newSquare(byte file, byte rank) {

        return (byte) (Squares.EDGE * rank + file);
    }

    public static String toFen(final Byte enPassantSquare)
            throws Exception {

        if (enPassantSquare == null) {
            return Fen.NO_EN_PASSANT;
        } else {
            return Squares.toSan(enPassantSquare);
        }

    }

    public static String toSan(final byte square)
            throws Exception {

        return toSanFile(file(square))
                + toSanRank(rank(square));
    }

    private static String toSanFile(final byte file)
            throws Exception {

        switch (file) {
        case FILE_A: return San.FILE_A;
        case FILE_B: return San.FILE_B;
        case FILE_C: return San.FILE_C;
        case FILE_D: return San.FILE_D;
        case FILE_E: return San.FILE_E;
        case FILE_F: return San.FILE_F;
        case FILE_G: return San.FILE_G;
        case FILE_H: return San.FILE_H;
        default:
            throw new Exception("file=" + file);
        }

    }

    private static String toSanRank(final byte rank)
            throws Exception {

        switch (rank) {
        case RANK_1: return San.RANK_1;
        case RANK_2: return San.RANK_2;
        case RANK_3: return San.RANK_3;
        case RANK_4: return San.RANK_4;
        case RANK_5: return San.RANK_5;
        case RANK_6: return San.RANK_6;
        case RANK_7: return San.RANK_7;
        case RANK_8: return San.RANK_8;
        default:
            throw new Exception("rank=" + rank);
        }

    }

    public static byte fromSan(final String sanSquare)
            throws Exception {

        char[] ca = sanSquare.toCharArray();

        byte file = fromSanFile(String.valueOf(ca[0]));
        byte rank = fromSanRank(String.valueOf(ca[1]));

        return (byte) (file + EDGE * rank);
    }

    private static byte fromSanFile(final String sanFile)
            throws Exception {

        switch (sanFile) {
        case San.FILE_A: return FILE_A;
        case San.FILE_B: return FILE_B;
        case San.FILE_C: return FILE_C;
        case San.FILE_D: return FILE_D;
        case San.FILE_E: return FILE_E;
        case San.FILE_F: return FILE_F;
        case San.FILE_G: return FILE_G;
        case San.FILE_H: return FILE_H;
        default:
            throw new Exception("sanFile=" + sanFile);
        }

    }

    private static byte fromSanRank(final String sanRank)
            throws Exception {

        switch (sanRank) {
        case San.RANK_1: return RANK_1;
        case San.RANK_2: return RANK_2;
        case San.RANK_3: return RANK_3;
        case San.RANK_4: return RANK_4;
        case San.RANK_5: return RANK_5;
        case San.RANK_6: return RANK_6;
        case San.RANK_7: return RANK_7;
        case San.RANK_8: return RANK_8;
        default:
            throw new Exception("sanRank=" + sanRank);
        }

    }

    public static String toAlpha2WithField(final byte square)
            throws Exception {

        switch (color(square)) {
        case Colors.WHITE: return Alpha2.WHITE_FIELD_EMPTY_SQUARE;
        case Colors.BLACK: return Alpha2.BLACK_FIELD_EMPTY_SQUARE;
        default:
            throw new Exception("color(square)=" + color(square));
        }

    }

    public static String toUTF8(final byte square) {

        return Utf8.VOID_SQUARE;
    }

}
