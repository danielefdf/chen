package model.utilities;

public abstract class FenStrings {

    /**
     * standard initial position
     */
    public static final String
    		STD = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    /**
     * perft test - Chessprogramming.com
     */
    public static final String
			PFT_1  = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
    		PFT_2  = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
    	    PFT_3  = "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1",
    	    PFT_4  = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1",
    	    PFT_4m = "r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1",
    	    PFT_5  = "rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8",
    	    PFT_6  = "r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10";

    /**
     * openings
     */
    public static final String
            // Open games
            O_OG_O_G       = "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2",          // open game                1.e4 e5
            O_OG_RUY_LOPEZ = "r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3",      // Ruy Lopez                1.e4 e5 2.Nf3 Nc6 3.Bb5
            O_OG_ITAL_G    = "r1bqkbnr/pppp1ppp/2n5/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3",      // Italian Game             1.e4 e5 2.Nf3 Nc6 3.Bc4
            O_OG_G_PIAN    = "r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4",    // Giuoco Piano             1.e4 e5 2.Nf3 Nc6 3.Bc4 Bc5
            TWO_N_D        = "r1bqkb1r/pppp1ppp/2n2n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4",    // Two Knights Defense      1.e4 e5 2.Nf3 Nc6 3.Bc4 Nf6
            SCOT_G         = "r1bqkbnr/pppp1ppp/2n5/4p3/3PP3/5N2/PPP2PPP/RNBQKB1R b KQkq d3 0 3",      // Scotch Game              1.e4 e5 2.Nf3 Nc6 3.d4
            PETR_D         = "rnbqkb1r/pppp1ppp/5n2/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3",       // Petrov's Defense         1.e4 e5 2.Nf3 Nf6
            KING_G         = "rnbqkbnr/pppp1ppp/8/4p3/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3 0 2",          // King's Gambit            1.e4 e5 2.f4
            VIEN_G         = "rnbqkbnr/pppp1ppp/8/4p3/4P3/2N5/PPPP1PPP/R1BQKBNR b KQkq - 1 2",         // Vienna Game              1.e4 e5 2.Nc3
            BISH_O         = "rnbqkbnr/pppp1ppp/8/4p3/2B1P3/8/PPPP1PPP/RNBQK1NR b KQkq - 1 2",         // Bishop's Opening         1.e4 e5 2.Bc4
            // Semi-open games
            SICI_D         = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2",          // Sicilian Defense         1.e4 c5
            FREN_D         = "rnbqkbnr/pppp1ppp/4p3/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // French Defense           1.e4 e6
            CARO_D         = "rnbqkbnr/pp1ppppp/2p5/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Caro–Kann Defense        1.e4 c6
            ALEK_D         = "rnbqkb1r/pppppppp/5n2/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2",           // Alekhine's Defense       1.e4 Nf6
            PIRC_D         = "rnbqkbnr/ppp1pppp/3p4/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Pirc Defense             1.e4 d6 2.d4 Nf6 3.Nc3 g6
            MODE_D         = "rnbqkbnr/pppppp1p/6p1/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Modern Defense           1.e4 g6
            SCAN_D         = "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2",          // Scandinavian Defense     1.e4 d5
            NIMZ_D         = "r1bqkbnr/pppppppp/2n5/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2",           // Nimzowitsch Defense      1.e4 Nc6
            // Closed Games
            CLOS_G         = "rnbqkbnr/ppp1pppp/8/3p4/3P4/8/PPP1PPPP/RNBQKBNR w KQkq d6 0 2",          // Closed Game              1.d4 d5
            Q_G            = "rnbqkbnr/ppp1pppp/8/3p4/2PP4/8/PP2PPPP/RNBQKBNR b KQkq c3 0 2",          // Queen's Gambit           1.d4 d5 2.c4
            Q_G_D          = "rnbqkbnr/ppp2ppp/4p3/3p4/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",          // Queen's Gambit Declined  1.d4 d5 2.c4 e6
            SLAV_D         = "rnbqkbnr/pp2pppp/2p5/3p4/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",          // Slav Defense             1.d4 d5 2.c4 c6
            Q_G_A          = "rnbqkbnr/ppp1pppp/8/8/2pP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",             // Queen's Gambit Accepted  1.d4 d5 2.c4 dxc4
            COLL_S         = "rnbqkb1r/ppp1pppp/5n2/3p4/3P4/4PN2/PPP2PPP/RNBQKB1R b KQkq - 0 3",       // Colle System             1.d4 d5 2.Nf3 Nf6 3.e3
            LOND_S         = "rnbqkb1r/ppp1pppp/5n2/3p4/3P1B2/5N2/PPP1PPPP/RN1QKB1R b KQkq - 3 3",     // London System            1.d4 d5 2.Nf3 Nf6 3.Bf4
            TORR_A         = "rnbqkb1r/ppp1pppp/5n2/3p2B1/3P4/5N2/PPP1PPPP/RN1QKB1R b KQkq - 3 3",     // Torre Attack             1.d4 d5 2.Nf3 Nf6 3.Bg5
            // Indian defenses
            INDI_D         = "rnbqkb1r/pppppppp/5n2/8/3P4/8/PPP1PPPP/RNBQKBNR w KQkq - 1 2",           // Indian Defense           1.d4 Nf6
            NI_IN_D        = "rnbqk2r/pppp1ppp/4pn2/8/1bPP4/2N5/PP2PPPP/R1BQKBNR w KQkq - 2 4",        // Nimzo-Indian Defense     1.d4 Nf6 2.c4 e6 3.Nc3 Bb4
            K_IN_D         = "rnbqk2r/ppppppbp/5np1/8/2PP4/2N5/PP2PPPP/R1BQKBNR w KQkq - 2 4",         // King's Indian Defense    1.d4 Nf6 2.c4 g6 3.Nc3 Bg7
            GRUN_D         = "rnbqkb1r/ppp1pp1p/5np1/3p4/2PP4/2N5/PP2PPPP/R1BQKBNR w KQkq d6 0 4",     // Grünfeld Defense         1.d4 Nf6 2.c4 g6 3.Nc3 d5
            Q_IN_D         = "rnbqkb1r/p1pp1ppp/1p2pn2/8/2PP4/5N2/PP2PPPP/RNBQKB1R w KQkq - 0 4",      // Queen's Indian Defense   1.d4 Nf6 2.c4 e6 3.Nf3 b6
            MODE_BENO      = "rnbqkb1r/pp1p1ppp/4pn2/2pP4/2P5/8/PP2PPPP/RNBQKBNR w KQkq c6 0 4",       // Modern Benoni            1.d4 Nf6 2.c4 c5 3.d5 e6
            BUDA_G         = "rnbqkb1r/pppp1ppp/5n2/4p3/2PP4/8/PP2PPPP/RNBQKBNR w KQkq e6 0 3",        // Budapest Gambit          1.d4 Nf6 2.c4 e5
            OL_IN_D        = "rnbqkb1r/ppp1pppp/3p1n2/8/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",         // Old Indian Defense       1.d4 Nf6 2.c4 d6
            // Semi-Closed Game
            DUTC_D         = "rnbqkbnr/ppppp1pp/8/5p2/3P4/8/PPP1PPPP/RNBQKBNR w KQkq f6 0 2",          // Dutch Defense            1.d4 f5
            BENO_D         = "rnbqkbnr/pp1ppppp/8/2p5/3P4/8/PPP1PPPP/RNBQKBNR w KQkq c6 0 2",          // Benoni Defense           1.d4 c5
            // Flank openings
            RETI_O         = "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1",             // Réti Opening             1.f3
            ENGL_O         = "rnbqkbnr/pppppppp/8/8/2P5/8/PP1PPPPP/RNBQKBNR b KQkq c3 0 1",            // English Opening          1.c4
            BIRD_O         = "rnbqkbnr/pppppppp/8/8/5P2/8/PPPPP1PP/RNBQKBNR b KQkq f3 0 1";            // Bird's Opening           1.f4

    /**
     * openings
     */
    public static String[] OPENINGS_LIST = {
            // Open games
            O_OG_O_G       , // "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2",          // open game                1.e4 e5
            O_OG_RUY_LOPEZ , // "r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3",      // Ruy Lopez                1.e4 e5 2.Nf3 Nc6 3.Bb5
            O_OG_ITAL_G    , // "r1bqkbnr/pppp1ppp/2n5/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3",      // Italian Game             1.e4 e5 2.Nf3 Nc6 3.Bc4
            O_OG_G_PIAN    , // "r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4",    // Giuoco Piano             1.e4 e5 2.Nf3 Nc6 3.Bc4 Bc5
            TWO_N_D        , // "r1bqkb1r/pppp1ppp/2n2n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4",    // Two Knights Defense      1.e4 e5 2.Nf3 Nc6 3.Bc4 Nf6
//            SCOT_G         , // "r1bqkbnr/pppp1ppp/2n5/4p3/3PP3/5N2/PPP2PPP/RNBQKB1R b KQkq d3 0 3",      // Scotch Game              1.e4 e5 2.Nf3 Nc6 3.d4
            PETR_D         , // "rnbqkb1r/pppp1ppp/5n2/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3",       // Petrov's Defense         1.e4 e5 2.Nf3 Nf6
//            KING_G         , // "rnbqkbnr/pppp1ppp/8/4p3/4PP2/8/PPPP2PP/RNBQKBNR b KQkq f3 0 2",          // King's Gambit            1.e4 e5 2.f4
//            VIEN_G         , // "rnbqkbnr/pppp1ppp/8/4p3/4P3/2N5/PPPP1PPP/R1BQKBNR b KQkq - 1 2",         // Vienna Game              1.e4 e5 2.Nc3
//            BISH_O         , // "rnbqkbnr/pppp1ppp/8/4p3/2B1P3/8/PPPP1PPP/RNBQK1NR b KQkq - 1 2",         // Bishop's Opening         1.e4 e5 2.Bc4
            // Semi-open games
            SICI_D         , // "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2",          // Sicilian Defense         1.e4 c5
            FREN_D         , // "rnbqkbnr/pppp1ppp/4p3/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // French Defense           1.e4 e6
            CARO_D         , // "rnbqkbnr/pp1ppppp/2p5/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Caro–Kann Defense        1.e4 c6
            ALEK_D         , // "rnbqkb1r/pppppppp/5n2/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2",           // Alekhine's Defense       1.e4 Nf6
            PIRC_D         , // "rnbqkbnr/ppp1pppp/3p4/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Pirc Defense             1.e4 d6 2.d4 Nf6 3.Nc3 g6
            MODE_D         , // "rnbqkbnr/pppppp1p/6p1/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2",           // Modern Defense           1.e4 g6
            SCAN_D         , // "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2",          // Scandinavian Defense     1.e4 d5
            NIMZ_D         , // "r1bqkbnr/pppppppp/2n5/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2",           // Nimzowitsch Defense      1.e4 Nc6
            // Closed Games
            CLOS_G         , // "rnbqkbnr/ppp1pppp/8/3p4/3P4/8/PPP1PPPP/RNBQKBNR w KQkq d6 0 2",          // Closed Game              1.d4 d5
//            Q_G            , // "rnbqkbnr/ppp1pppp/8/3p4/2PP4/8/PP2PPPP/RNBQKBNR b KQkq c3 0 2",          // Queen's Gambit           1.d4 d5 2.c4
            Q_G_D          , // "rnbqkbnr/ppp2ppp/4p3/3p4/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",          // Queen's Gambit Declined  1.d4 d5 2.c4 e6
            SLAV_D         , // "rnbqkbnr/pp2pppp/2p5/3p4/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",          // Slav Defense             1.d4 d5 2.c4 c6
            Q_G_A          , // "rnbqkbnr/ppp1pppp/8/8/2pP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",             // Queen's Gambit Accepted  1.d4 d5 2.c4 dxc4
//            COLL_S         , // "rnbqkb1r/ppp1pppp/5n2/3p4/3P4/4PN2/PPP2PPP/RNBQKB1R b KQkq - 0 3",       // Colle System             1.d4 d5 2.Nf3 Nf6 3.e3
//            LOND_S         , // "rnbqkb1r/ppp1pppp/5n2/3p4/3P1B2/5N2/PPP1PPPP/RN1QKB1R b KQkq - 3 3",     // London System            1.d4 d5 2.Nf3 Nf6 3.Bf4
//            TORR_A         , // "rnbqkb1r/ppp1pppp/5n2/3p2B1/3P4/5N2/PPP1PPPP/RN1QKB1R b KQkq - 3 3",     // Torre Attack             1.d4 d5 2.Nf3 Nf6 3.Bg5
            // Indian defenses
            INDI_D         , // "rnbqkb1r/pppppppp/5n2/8/3P4/8/PPP1PPPP/RNBQKBNR w KQkq - 1 2",           // Indian Defense           1.d4 Nf6
            NI_IN_D        , // "rnbqk2r/pppp1ppp/4pn2/8/1bPP4/2N5/PP2PPPP/R1BQKBNR w KQkq - 2 4",        // Nimzo-Indian Defense     1.d4 Nf6 2.c4 e6 3.Nc3 Bb4
            K_IN_D         , // "rnbqk2r/ppppppbp/5np1/8/2PP4/2N5/PP2PPPP/R1BQKBNR w KQkq - 2 4",         // King's Indian Defense    1.d4 Nf6 2.c4 g6 3.Nc3 Bg7
            GRUN_D         , // "rnbqkb1r/ppp1pp1p/5np1/3p4/2PP4/2N5/PP2PPPP/R1BQKBNR w KQkq d6 0 4",     // Grünfeld Defense         1.d4 Nf6 2.c4 g6 3.Nc3 d5
            Q_IN_D         , // "rnbqkb1r/p1pp1ppp/1p2pn2/8/2PP4/5N2/PP2PPPP/RNBQKB1R w KQkq - 0 4",      // Queen's Indian Defense   1.d4 Nf6 2.c4 e6 3.Nf3 b6
            MODE_BENO      , // "rnbqkb1r/pp1p1ppp/4pn2/2pP4/2P5/8/PP2PPPP/RNBQKBNR w KQkq c6 0 4",       // Modern Benoni            1.d4 Nf6 2.c4 c5 3.d5 e6
            BUDA_G         , // "rnbqkb1r/pppp1ppp/5n2/4p3/2PP4/8/PP2PPPP/RNBQKBNR w KQkq e6 0 3",        // Budapest Gambit          1.d4 Nf6 2.c4 e5
            OL_IN_D        , // "rnbqkb1r/ppp1pppp/3p1n2/8/2PP4/8/PP2PPPP/RNBQKBNR w KQkq - 0 3",         // Old Indian Defense       1.d4 Nf6 2.c4 d6
            // Semi-Closed games
            DUTC_D         , // "rnbqkbnr/ppppp1pp/8/5p2/3P4/8/PPP1PPPP/RNBQKBNR w KQkq f6 0 2",          // Dutch Defense            1.d4 f5
            BENO_D         , // "rnbqkbnr/pp1ppppp/8/2p5/3P4/8/PPP1PPPP/RNBQKBNR w KQkq c6 0 2",          // Benoni Defense           1.d4 c5
            // Flank openings
//            RETI_O         , // "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1",             // Réti Opening             1.f3
//            ENGL_O         , // "rnbqkbnr/pppppppp/8/8/2P5/8/PP1PPPPP/RNBQKBNR b KQkq c3 0 1",            // English Opening          1.c4
//            BIRD_O           // "rnbqkbnr/pppppppp/8/8/5P2/8/PPPPP1PP/RNBQKBNR b KQkq f3 0 1";            // Bird's Opening           1.f4

    };

    /**
     * mate in 3
     */
    public static final String
			MA3_1  = "r6r/p6p/bb1R2pB/3R2Nn/1p4k1/8/1PP2PPP/6K1 w - - 0 1",              // h3+ Kf4 Rf6+ Nxf6 g3#
			MA3_2  = "8/R4Q2/1p2rn1p/1Pp2k2/2q1p3/2B1P2P/5PP1/6K1 w - - 0 1",            // g4+ Kg5 Bxf6+ Rxf6 Qh5#
			MA3_3  = "r5k1/1R2Q1b1/8/5p2/6P1/p1q4P/P3BPK1/8 w - - 0 1",                  // Bc4+ Kh7 Qh4+ Kg6 Bf7#
			MA3_4  = "2n5/1p3pkp/2q2Np1/p4PP1/3Q4/8/PPP1r2P/2KR4 w - - 0 1",             // Nh5+ Kf8 Qh8+ Ke7 Qd8#
			MA3_5  = "3r3r/pp1Q1p2/5k2/3PpN1P/1P6/3P2P1/5b1N/7K w - - 0 1",              // Qe7+ Kxf5 Qxf7+ Kg5 Qg6#
			MA3_6  = "r4bkr/5R1p/ppb1q3/5N1Q/2P1P3/4P1P1/PP5P/5RK1 w - - 0 1",           // Qg5+ Qg6 Rg7+ Bxg7 Ne7#
			MA3_7  = "q2r2k1/4n1pp/2p1b3/1pP5/1P6/4PN2/1Q3PPP/1K3B1R b - - 0 1",         // Rd1+ Kc2 Qa4+ Kc3 Nd5#
			MA3_8  = "5r2/pN1k2b1/4p3/1pp5/2p4N/6K1/PPQ2BP1/4q1n1 b - - 0 1",            // Qe5+ Kg4 Rf4+ Kg3 Re4#
			MA3_9  = "2rq1k2/pp5Q/5Np1/3P4/3B4/8/PP3PPP/n5K1 w - - 0 1",                 // Qg8+ Ke7 Qe6+ Kf8 Nh7#
			MA3_10 = "Q7/5pk1/4q1p1/3p1p2/2nP1Q1P/5P1K/8/8 w - - 0 1",                   // Qh6+ if Kxh6 Qh8# or if Kf6 Qhh8+
			MA3_11 = "3q4/5R2/1r4p1/5rk1/2Qp4/1P3P2/P5K1/4R3 w - - 0 1",                 // Qc1+
			MA3_12 = "r1q2rk1/pp2R1p1/n1p4p/7Q/3P4/B1PB2P1/P6P/6K1 w - - 0 1",           // Rxg7+ Kxg7 Qg6+
			MA3_13 = "5b1k/2r2p1p/3p4/3q1NQ1/8/6P1/5P1P/4R1K1 w - - 0 1",                // Qg7+ Bxg7 Re8+
			MA3_14 = "7k/p5r1/q1pb1QPp/3p4/2nPP3/1RN5/P5R1/6K1 w - - 0 1",               // Rb8+ Bxb8 Qf8+
			MA3_15 = "r4bk1/1b3pPp/p2p4/q4P1Q/1p1r1P2/1P1R4/1PP4P/2K3R1 w - - 0 1",      // Qxh7+
			MA3_16 = "1b5r/p4ppp/1p2k1b1/3R4/2B5/Q3B2P/PP3PPq/R4K2 w - - 0 1",           // Re5+ if Kxe5 Qe7+ or if Kd7 Qe7+
			MA3_17 = "6k1/1b1q2b1/1n1p1pQp/2pP4/5P2/1P4R1/P4P1P/1R4K1 w - - 0 1",        // Re1
			MA3_18 = "r7/pR4pp/5pk1/2P1pN2/n7/5PP1/r3P2P/3R1K2 w - - 0 1",               // Rxg7+ Kxf5 e4+
			MA3_19 = "4r3/p2q2p1/2R4p/5pk1/2Q1p3/PP2P1P1/5P1P/6K1 w - - 0 1",            // f4+ if Kg4 Qe2+
			MA3_20 = "nr3qrk/5p2/p4Pp1/P1p4p/2B1p2Q/2P4R/6PP/4R2K w - - 0 1",            // Qxh5+
			MA3_21 = "3r1rk1/p5p1/2Q1N3/4Rb2/2Pp4/8/P4qPP/4R2K b - - 0 1",               // ...Be4 if R5xe4 Qf1+ or if Rg1 Qxg2+
			MA3_22 = "6k1/1p3p1p/p5p1/P1BP4/4qPb1/2b2nP1/5RKP/2Q5 b - - 0 1",            // ...Ng5+
			MA3_23 = "1r4BQ/6p1/2q2pkp/8/3p3P/P5PK/1r3P2/3RnR2 b - - 0 1",               // ...Qg2+ Kg4 f5+
			MA3_24 = "6k1/4Qp1p/4p2P/3pP1p1/3nq1P1/pP4K1/P4P1N/8 b - - 0 1",             // ...Ne2+ Kh3 Nf4+
			MA3_25 = "3r2k1/5pPp/8/1Q6/2p1qR2/R3P3/6rP/4K3 b - - 0 1",                   // ...Rd1+ Kxd1 Qc2+
			MA3_26 = "3r3k/pp1r2p1/7p/2p1Q1bP/2P3P1/2B2R2/PP3P1q/4RK2 b - - 0 1",        // ...Qh1+ Ke2 Rd2+
			MA3_27 = "1r4k1/7p/p1Q1p1p1/6B1/2Pb4/8/q4PPP/5RK1 b - - 0 1",                // ...Qxf2+
			MA3_28 = "r4rk1/p6p/2p1pqpn/2p5/2p1Nn2/3P2Q1/PPP3PP/4RRK1 b - - 0 1",        // ...Ne2+
			MA3_29 = "2q1r1k1/QR4b1/4npp1/2B1pnN1/2PpN2p/3P2P1/4PP1P/6K1 w - - 0 1",     // Rxg7+ if Nexg7 Nxf6+ or if Kh8 Rh7+
			MA3_30 = "2Q5/p5p1/1b6/4R1pp/1k6/7P/qPP5/7K w - - 0 1",                      // Qc3+ Ka4 Qc6+
			MA3_31 = "r3qnk1/p2R1p2/7R/1pp1pQp1/8/7P/PP3PP1/6K1 w - - 0 1",              // Qf6 if Ng6 Rxg6+ or if Nh7 Rg6+
			MA3_32 = "3rk2r/pp2ppBp/6p1/5b2/7Q/1PP5/1q2NPPP/2R1K2R b Kk - 0 1",          // 1...Qd2+ 2.Kf1 Qd1+
			MA3_33 = "5Q2/p2r3k/3rp3/1p1q3B/8/P1P3PP/1P6/5RK1 w - - 0 1",                // 1.Bg6+ Kxg6 2.Rf6+
			MA3_34 = "r4rk1/pp4P1/3np1Qn/q2pb3/2pP4/2P1PP2/1PB5/2KR4 w - - 0 1",         // 1.Qh7+ Kf7 2.g8/N+
			MA3_35 = "4k1r1/p3n3/4pQ1p/1Pr2p2/5q1P/P4B2/P1P5/1K1R4 w - - 0 1",           // 1.Bh5+
			MA3_36 = "8/6R1/5pn1/1r3Nk1/2R3Pr/8/p4PK1/8 w - - 0 1",                      // 1.f4+ Kxg4 2.Ne3+
			MA3_37 = "5rk1/4p3/6p1/1Ppp2q1/4Nn1p/2P2P2/Q1P2RPP/6K1 b - - 0 1",           // 1...Nh3+ if 2.Kf1 Qc1+ 3.Ke2 Nf4#
			MA3_38 = "4n2k/7p/2p1Pp2/2qb1P2/6R1/3P1pQP/3K4/n7 b - - 0 1",                // 1...Nb3+ 2.Ke1 Qe3+
			MA3_39 = "7R/2q1kpp1/p2p1b2/8/N2pb3/PB6/KP2Q3/2r5 b - - 0 1",                // 1...Ra1+ 2.Kxa1 Qc1+
			MA3_40 = "5r1k/6pp/p7/P7/2Q1N2P/5qPK/1PP1R3/6r1 b - - 0 1",                  // 1...Qf1+ if 2.Rg2 Rh1+
			MA3_41 = "5Q2/q3B2p/2p2pp1/1p2p1k1/p1P1P1P1/P7/1P3P1P/2n2K2 w - - 0 1",      // 1.Qxf6+ Kxg4 2.h3+
			MA3_42 = "2r1Rbk1/1pq2p2/p2r1Q2/3p1P2/8/2N2P2/1PP4P/1K2R3 w - - 0 1",        // 1.Rxf8+
			MA3_43 = "5n1r/3r2b1/1pR5/2p1pp1p/6Pk/4BP1P/PP2P1K1/R7 w - - 0 1",           // 1.Bf2+ Kg5 2.h4+
			MA3_44 = "r3r3/1p1bN2k/p2pp1Nb/q3P2Q/6pP/8/PnP2PP1/R4K1R w - - 0 1",         // 1.Nf8+ Rxf8 2.Qg6+
			MA3_45 = "4knrr/3R4/5ppP/8/1q5P/4BPn1/1PP1Q3/1K1R4 w - - 0 1",               // 1.Bc5+ Qe4 2.Re7+
			MA3_46 = "r5k1/5p1p/n7/6B1/1PQ3P1/3p2PK/N3rq1P/R6R b - - 0 1",               // 1...Qg2+ 2.Kh4 Qxh2+
			MA3_47 = "8/5p1k/p3r2p/P1Q2N2/7K/6PP/4q3/8 b - - 0 1",                       // 1...Re4+ 2.g4 Rxg4+ if 3.Kh5 Ra4# or if 3.hxg4 Qh2#
			MA3_48 = "8/1kp5/p2n3p/8/1KPQ4/1P2RP2/r7/2q5 b - - 0 1",                     // 1...Qa3+ 2.Kc3 Qa5+
			MA3_49 = "4rn1k/r3p2n/2pqP1pQ/p1N1p3/1pBP2P1/8/PP5R/1K1R4 w - - 0 1",        // 1.Rf1
			MA3_50 = "r1b4Q/1p1pbkp1/8/1pqpR1p1/3p4/3P4/PPP2PPP/4R1K1 w - - 0 1",        // 1.Rf5+ if 1...Kg6 2.g4 or if 1...Bf6 2.Qe8#
			MA3_51 = "r3nr1k/ppp4p/2np2p1/3Q2P1/2B2P2/qP2P3/P4N2/R3K2R w KQ - 1 0",      // 1.Rxh7+ Kxh7 2.Qh1+
			MA3_52 = "3R2nk/p4rpp/1p3p2/4qP2/1P6/P5P1/Q6P/3R2K1 w - - 0 1",              // 1.Rxg8+
			MA3_53 = "7k/1pqr2p1/p1p1Q2p/P3Rp1P/1P3P2/2P2K2/6P1/8 w - - 0 1",            // 1.Qe8+
			MA3_54 = "8/p3rpk1/1n5p/1q2N1p1/3PQ3/5RP1/5PPK/8 w - - 0 1",                 // 1.Rxf7+ if 1...Rxf7 2.Qg6+
			MA3_55 = "2kr4/ppp4p/4K3/1P3p2/2Pq4/P7/2Q2P2/R3BR2 b - - 0 1",               // 1...Rf8 if 2.Bb4 Rf6+
			MA3_56 = "3Q4/5Rpk/4p1n1/4P2K/2N1P2p/5P2/6q1/1r6 b - - 0 1",                 // 1...Nf4+ 2.Rxf4 Qg6+
			MA3_57 = "r1b2bk1/ppq2p2/4pn1Q/3r2N1/8/2P5/PPB2PPP/R4RK1 w - - 0 1",         // 1.Bh7+
			MA3_58 = "r1r3k1/6q1/pnp2pP1/2bPp3/8/2N4Q/PPPB4/2K4R w - - 0 1",             // 1.Qe6+ Kf8 2.Rh8+
			MA3_59 = "r4rk1/3qbp2/2p3p1/3nB1Rp/3P4/3Q4/p3B1PP/R5K1 w - - 0 1",           // 1.Rxg6+ if 1...Kh7 2.Rg7+
			MA3_60 = "r1b2rk1/6p1/1qn4p/p1ppp2Q/1p1P3b/2PBP1R1/PP1N2PP/3R2K1 w - - 0 1", // 1.Rxg7+
			MA3_61 = "r1b4r/1p1p3p/p4Q2/3kp3/2q1N3/2P5/PP1K1PPP/n6R w - - 0 1",          // 1.Qf7+ if 1...Kxe4 2.f3# or if 1...Kc6 2.Qxc4+
			MA3_62 = "r4rk1/3bppb1/1p4Bp/2pn4/q7/N5BP/1PP1QPP1/2KRR3 b - - 0 1",         // 1...Bxb2+ if 2.Kd2 Qb4+ or if 2.Kb1 Nc3+ or if 2.Kxb2 Qxa3+
			MA3_63 = "3rr1k1/2p2pPp/b1p5/p7/Pb6/3qP3/1P1P1PP1/R1BQK2R b KQ - 0 1";       // 1...Rxe3+ 2.fxe3 Qxe3+

	/**
	 * mate in 10
	 */
	public static final String
			MA10_1  = "8/8/4k3/2K1N1B1/5Pp1/8/2B2P1p/6n1 w - - 0 1",
			MA10_2  = "8/8/4k3/2K1N1B1/5Pp1/8/2B2P1p/6n1 w - - 0 1",
			MA10_3  = "8/8/4k3/2K1N1B1/B4Pp1/8/5P1p/6n1 b - - 1 1",
			MA10_4  = "8/8/8/2K1NkB1/B4Pp1/8/5P1p/6n1 w - - 2 2",
			MA10_5  = "8/3B4/8/2K1NkB1/5Pp1/8/5P1p/6n1 b - - 3 2",
			MA10_6  = "8/3B4/8/2K1N1B1/4kPp1/8/5P1p/6n1 w - - 4 3",
			MA10_7  = "4B3/8/8/2K1N1B1/4kPp1/8/5P1p/6n1 b - - 5 3",
			MA10_8  = "4B3/8/8/2K1NkB1/5Pp1/8/5P1p/6n1 w - - 6 4",
			MA10_9  = "8/8/6B1/2K1NkB1/5Pp1/8/5P1p/6n1 b - - 7 4",
			MA10_10 = "8/8/4k1B1/2K1N1B1/5Pp1/8/5P1p/6n1 w - - 8 5",
			MA10_11 = "8/8/4k3/2K1N1BB/5Pp1/8/5P1p/6n1 b - - 9 5",
			MA10_12 = "8/8/8/2K1NkBB/5Pp1/8/5P1p/6n1 w - - 10 6",
			MA10_13 = "8/8/8/2K1NkB1/5PB1/8/5P1p/6n1 b - - 0 6",
			MA10_14 = "8/8/8/2K1N1B1/4kPB1/8/5P1p/6n1 w - - 1 7",
			MA10_15 = "8/8/8/2K1N1B1/4kP2/8/5P1p/3B2n1 b - - 2 7",
			MA10_16 = "8/8/8/2K1NkB1/5P2/8/5P1p/3B2n1 w - - 3 8",
			MA10_17 = "8/8/8/2K1NkB1/5P2/8/2B2P1p/6n1 b - - 4 8",
			MA10_18 = "8/8/4k3/2K1N1B1/5P2/8/2B2P1p/6n1 w - - 5 9",
			MA10_19 = "8/8/4k3/2K1NPB1/8/8/2B2P1p/6n1 b - - 0 9",
			MA10_20 = "8/8/8/2K1kPB1/8/8/2B2P1p/6n1 w - - 0 10",
			MA10_21 = "8/8/8/2K1kPB1/5P2/8/2B4p/6n1 b - f3 0 10";

	/**
	 * stalemate
	 */
	public static final String
			SM_1 = "8/5P1k/5K2/5N1p/7r/8/2R5/8 b - - 0 1",
		// forzati
			SM_2 = "1q6/2b2ppb/4p1k1/7p/2Np1p1P/3P1Q2/6PK/8 w - - 0 1",
			SM_3 = "3r4/7R/8/8/2K2P2/1P2Bp2/Pp1P4/k2r4 b - - 0 1",
			SM_4 = "8/6p1/5p2/5P1K/4k2P/8/8/8 b - - 0 1",
			SM_5 = "8/kp6/p7/P4Q3/6pp/4q3/8/7K w - - 0 1";

    /**
     * Bratko-Kopec Test
     */
    public static final String
            BKT_1  = "1k1r4/pp1b1R2/3q2pp/4p3/2B5/4Q3/PPP2B2/2K5 b - - 0 1",                   // Qd1+
            BKT_2  = "3r1k2/4npp1/1ppr3p/p6P/P2PPPP1/1NR5/5K2/2R5 w - - 0 1",                  // d5
            BKT_3  = "2q1rr1k/3bbnnp/p2p1pp1/2pPp3/PpP1P1P1/1P2BNNP/2BQ1PRK/7R b - - 0 1",     // f5
            BKT_4  = "rnbqkb1r/p3pppp/1p6/2ppP3/3N4/2P5/PPP1QPPP/R1B1KB1R w KQkq - 0 1",       // e6
            BKT_5  = "r1b2rk1/2q1b1pp/p2ppn2/1p6/3QP3/1BN1B3/PPP3PP/R4RK1 w - - 0 1",          // Nd5/a4
            BKT_6  = "2r3k1/pppR1pp1/4p3/4P1P1/5P2/1P4K1/P1P5/8 w - - 0 1",                    // g6
            BKT_7  = "1nk1r1r1/pp2n1pp/4p3/q2pPp1N/b1pP1P2/B1P2R2/2P1B1PP/R2Q2K1 w - - 0 1",   // Nf6
            BKT_8  = "4b3/p3kp2/6p1/3pP2p/2pP1P2/4K1P1/P3N2P/8 w - - 0 1",                     // f5
            BKT_9  = "2kr1bnr/pbpq4/2n1pp2/3p3p/3P1P1B/2N2N1Q/PPP3PP/2KR1B1R w - - 0 1",       // f5
            BKT_10 = "3rr1k1/pp3pp1/1qn2np1/8/3p4/PP1R1P2/2P1NQPP/R1B3K1 b - - 0 1",           // Ne5
            BKT_11 = "2r1nrk1/p2q1ppp/bp1p4/n1pPp3/P1P1P3/2PBB1N1/4QPPP/R4RK1 w - - 0 1",      // f4
            BKT_12 = "r3r1k1/ppqb1ppp/8/4p1NQ/8/2P5/PP3PPP/R3R1K1 b - - 0 1",                  // Bf5
            BKT_13 = "r2q1rk1/4bppp/p2p4/2pP4/3pP3/3Q4/PP1B1PPP/R3R1K1 w - - 0 1",             // b4
            BKT_14 = "rnb2r1k/pp2p2p/2pp2p1/q2P1p2/8/1Pb2NP1/PB2PPBP/R2Q1RK1 w - - 0 1",       // Qd2/Qe1
            BKT_15 = "2r3k1/1p2q1pp/2b1pr2/p1pp4/6Q1/1P1PP1R1/P1PN2PP/5RK1 w - - 0 1",         // Qxg7+
            BKT_16 = "r1bqkb1r/4npp1/p1p4p/1p1pP1B1/8/1B6/PPPN1PPP/R2Q1RK1 w kq - 0 1",        // Ne4
            BKT_17 = "r2q1rk1/1ppnbppp/p2p1nb1/3Pp3/2P1P1P1/2N2N1P/PPB1QP2/R1B2RK1 b - - 0 1", // h5
            BKT_18 = "r1bq1rk1/pp2ppbp/2np2p1/2n5/P3PP2/N1P2N2/1PB3PP/R1B1QRK1 b - - 0 1",     // Nb3
            BKT_19 = "3rr3/2pq2pk/p2p1pnp/8/2QBPP2/1P6/P5PP/4RRK1 b - - 0 1",                  // Rxe4
            BKT_20 = "r4k2/pb2bp1r/1p1qp2p/3pNp2/3P1P2/2N3P1/PPP1Q2P/2KRR3 w - - 0 1",         // g4
            BKT_21 = "3rn2k/ppb2rpp/2ppqp2/5N2/2P1P3/1P5Q/PB3PPP/3RR1K1 w - - 0 1",            // Nh6
            BKT_22 = "2r2rk1/1bqnbpp1/1p1ppn1p/pP6/N1P1P3/P2B1N1P/1B2QPP1/R2R2K1 b - - 0 1",   // Bxe4
            BKT_23 = "r1bqk2r/pp2bppp/2p5/3pP3/P2Q1P2/2N1B3/1PP3PP/R4RK1 b kq - 0 1",          // f6
            BKT_24 = "r2qnrnk/p2b2b1/1p1p2pp/2pPpp2/1PP1P3/PRNBB3/3QNPPP/5RK1 w - - 0 1";      // f4

    /**
     * Eigenmann Endgame Test
     */
    public static final String
            EET_1   = "8/8/p2p3p/3k2p1/PP6/3K1P1P/8/8 b - - 0 1",                 // bm Kc6         B vs B
            EET_2   = "8/p5pp/3k1p2/3p4/1P3P2/P1K5/5P1P/8 b - - 0 1",             // bm g5          B vs B
            EET_3   = "8/1p3p2/p7/8/2k5/4P1pP/2P1K1P1/8 w - - 0 1",               // bm h4          B vs B
            EET_4   = "8/pp5p/3k2p1/8/4Kp2/2P1P2P/P7/8 w - - 0 1",                // bm exf4        B vs B
            EET_5   = "8/7p/1p3pp1/p2K4/Pk3PPP/8/1P6/8 b - - 0 1",                // bm Kb3 f5      B vs B
            EET_6   = "2k5/3b4/PP3K2/7p/4P3/1P6/8/8 w - - 0 1",                   // bm Ke7         B vs L
            EET_7   = "8/3Pb1p1/8/3P2P1/5P2/7k/4K3/8 w - - 0 1",                  // bm Kd3         B vs L
            EET_8   = "8/1Pk2Kpp/8/8/4nPP1/7P/8/8 b - - 0 1",                     // bm Nf2         B vs S
            EET_9   = "2n5/4k1p1/P6p/3PK3/7P/8/6P1/8 b - - 0 1",                  // bm g6          B vs S
            EET_10  = "4k3/8/3PP1p1/8/3K3p/8/3n2PP/8 b - - 0 1",                  // am Nf1         B vs S
            EET_11  = "6k1/5p2/P3p1p1/2Qp4/5q2/2K5/8/8 b - - 0 1",                // am Qc1+ Qe5+   D vs D
            EET_12  = "8/6pk/8/2p2P1p/6qP/5QP1/8/6K1 w - - 0 1",                  // bm Qd3 Qf2     D vs D
            EET_13  = "5q1k/5P1p/Q7/5n1p/6P1/7K/8/8 w - - 0 1",                   // bm Qa1+        D vs D&S
            EET_14  = "4qr2/4p2k/1p2P1pP/5P2/1P3P2/6Q1/8/3B1K2 w - - 0 1",        // bm Ba4         D&L vs D&T
            EET_15  = "8/kn4b1/P2B4/8/1Q6/6pP/1q4pP/5BK1 w - - 0 1",              // bm Bc5+        D&L&L vs D&L&S
            EET_17  = "6k1/1p2p1bp/p5p1/4pb2/1q6/4Q3/1P2BPPP/2R3K1 w - - 0 1",    // bm Qa3         D&T&L vs D&L&L
            EET_18  = "1rr2k2/p1q5/3p2Q1/3Pp2p/8/1P3P2/1KPRN3/8 w - e6 0 1",      // bm Rd1         D&T&S vs D&T&T
            EET_19  = "r5k1/3R2p1/p1r1q2p/P4p2/5P2/2p1P3/5P1P/1R1Q2K1 w - - 0 1", // am Rbb7        D&T&T vs D&T&T
            EET_20  = "8/1p4k1/pK5p/2B5/P4pp1/8/7P/8 b - - 0 1",                  // am g3          L vs B
            EET_21  = "8/6p1/6P1/6Pp/B1p1p2K/6PP/3k2P1/8 w - - 0 1",              // bm Bd1         L vs B
            EET_22  = "8/4k3/8/2Kp3p/B3bp1P/P7/1P6/8 b - - 0 1",                  // bm Bg2         L vs L
            EET_23  = "8/8/2p1K1p1/2k5/p7/P4BpP/1Pb3P1/8 w - - 0 1",              // am Kd7         L vs L
            EET_24  = "8/3p3B/5p2/5P2/p7/PP5b/k7/6K1 w - - 0 1",                  // bm b4          L vs L
            EET_25  = "8/p4p2/1p2k2p/6p1/P4b1P/1P6/3B1PP1/6K1 w - - 0 1",         // am Bxf4        L vs L
            EET_27  = "3b3k/1p4p1/p5p1/4B3/8/7P/1P3PP1/5K2 b - - 0 1",            // am Bf6         L vs L
            EET_28  = "4b1k1/1p3p2/4pPp1/p2pP1P1/P2P4/1P1B4/8/2K5 w - - 0 1",     // bm b4          L vs L
            EET_29  = "8/3k1p2/n3pP2/1p2P2p/5K2/1PB5/7P/8 b - - 0 1",             // am Kc6 b4      L vs S
            EET_30  = "8/8/4p1p1/1P1kP3/4n1PK/2B4P/8/8 b - - 0 1",                // bm Kc5         L vs S
            EET_31  = "8/5k2/4p3/B2p2P1/3K2n1/1P6/8/8 b - - 0 1",                 // bm Kg6         L vs S
            EET_32  = "5b2/p4B2/5B2/1bN5/8/P3r3/4k1K1/8 w - - 0 1",               // bm Bh5+        L&L&S vs T&L&L
            EET_33  = "8/p5pq/8/p2N3p/k2P3P/8/KP3PB1/8 w - - 0 1",                // bm Be4         L&S vs D
            EET_34  = "1b6/1P6/8/2KB4/6pk/3N3p/8/8 b - - 0 1",                    // bm Kg3         L&S vs L&B
            EET_35  = "8/p7/7k/1P1K3P/8/1n6/4Bp2/5Nb1 b - - 0 1",                 // bm Na5         L&S vs L&S
            EET_36  = "8/8/8/3K4/2N5/p2B4/p7/k4r2 w - - 0 1",                     // bm Kc5         L&S vs T&B
            EET_37  = "8/8/2kp4/5Bp1/8/5K2/3N4/2rN4 w - - 0 1",                   // bm Nb3         L&S&S vs T&B
            EET_38  = "k2K4/1p4pN/P7/1p3P2/pP6/8/8/8 w - - 0 1",                  // bm f6 Kc7      S vs B
            EET_39  = "k2N4/1qpK1p2/1p6/1P4p1/1P4P1/8/8/8 w - - 0 1",             // bm Nc6         S vs D
            EET_40  = "6k1/4b3/4p1p1/8/6pP/4PN2/5K2/8 w - - 0 1",                 // bm Ne5 Nh2     S vs L
            EET_41  = "8/8/6Np/2p3kP/1pPbP3/1P3K2/8/8 w - - 0 1",                 // bm e5          S vs L
            EET_42  = "8/3P4/1p3b1p/p7/P7/1P3NPP/4p1K1/3k4 w - - 0 1",            // bm g4          S vs L
            EET_43  = "8/8/1p2p3/p3p2b/P1PpP2P/kP6/2K5/7N w - - 0 1",             // bm Nf2         S vs L
            EET_44  = "4N3/8/3P3p/1k2P3/7p/1n1K4/8/8 w - - 0 1",                  // bm d7          S vs S
            EET_45  = "N5n1/2p1kp2/2P3p1/p4PP1/K4P2/8/8/8 w - - 0 1",             // bm f6 Kb5      S vs S
            EET_46  = "8/8/2pn4/p4p1p/P4N2/1Pk2KPP/8/8 w - - 0 1",                // bm Ne2 Ne6     S vs S
            EET_47  = "8/7k/2P5/2p4p/P3N2K/8/8/5r2 w - - 0 1",                    // bm Ng5+        S vs T
            EET_48  = "2k1r3/p7/K7/1P6/P2N4/8/P7/8 w - - 0 1",                    // bm Nc6         S vs T
            EET_49  = "1k6/8/8/1K6/5pp1/8/4Pp1p/R7 w - - 0 1",                    // bm Kb6         T vs B
            EET_50  = "6k1/8/8/1K4p1/3p2P1/2pp4/8/1R6 w - - 0 1",                 // bm Kc6         T vs B
            EET_51  = "8/5p2/3pp2p/p5p1/4Pk2/2p2P1P/P1Kb2P1/1R6 w - - 0 1",       // bm a4 Rb5      T vs L
            EET_52  = "8/8/4pR2/3pP2p/6P1/2p4k/P1Kb4/8 b - - 0 1",                // bm hxg4        T vs L
            EET_53  = "3k3K/p5P1/P3r3/8/1N6/8/8/8 w - - 0 1",                     // bm Kh7         T vs S
            EET_54  = "8/8/5p2/5k2/p4r2/PpKR4/1P5P/8 w - - 0 1",                  // am Rd4         T vs T
            EET_55  = "5k2/7R/8/4Kp2/5Pp1/P5rp/1P6/8 w - - 0 1",                  // bm Kf6         T vs T
            EET_56  = "2K5/p7/7P/5pR1/8/5k2/r7/8 w - - 0 1",                      // bm Rxf5+       T vs T
            EET_57  = "8/2R4p/4k3/1p2P3/pP3PK1/r7/8/8 b - - 0 1",                 // bm h5 Ra1      T vs T
            EET_58  = "2k1r3/5R2/3KP3/8/1pP3p1/1P5p/8/8 w - - 0 1",               // bm Rc7+        T vs T
            EET_59  = "8/6p1/1p5p/1R2k3/4p3/1P2P3/1K4PP/3r4 b - - 0 1",           // am Rd5         T vs T
            EET_60  = "5K2/kp3P2/2p5/2Pp4/3P4/r7/p7/6R1 w - - 0 1",               // bm Ke7         T vs T
            EET_61  = "8/pp3K2/2P4k/5p2/8/6P1/R7/6r1 w - - 0 1",                  // bm Kf6         T vs T
            EET_62  = "2r3k1/6pp/3pp1P1/1pP5/1P6/P4R2/5K2/8 w - - 0 1",           // bm c6          T vs T
            EET_63  = "r2k4/8/8/1P4p1/8/p5P1/6P1/1R3K2 w - - 0 1",                // bm b6          T vs T
            EET_64  = "8/4k3/1p4p1/p7/P1r1P3/1R4Pp/5P1P/4K3 w - - 0 1",           // bm Ke2         T vs T
            EET_65  = "R7/4kp2/P3p1p1/3pP1P1/3P1P2/p6r/3K4/8 w - - 0 1",          // bm Kc2         T vs T
            EET_66  = "8/1pp1r3/p1p2k2/6p1/P5P1/1P3P2/2P1RK2/8 b - - 0 1",        // am Rxe2+ Re5   T vs T
            EET_67  = "8/1p2R3/8/p5P1/3k4/P2p2K1/1P6/5r2 w - - 0 1",              // bm Kg2         T vs T
            EET_68  = "R7/P5Kp/2P5/k3r2P/8/5p2/p4P2/8 w - - 0 1",                 // bm Rb8         T vs T
            EET_69  = "8/2p4K/4k1p1/p1p1P3/PpP2P2/5R1P/8/6r1 b - - 0 1",          // bm Kf7         T vs T
            EET_71  = "8/B7/1R6/q3k2p/8/6p1/5P2/5K2 w - - 0 1",                   // bm Rb3         T&L vs D
            EET_72  = "5k2/8/2Pb1B2/8/6RK/7p/5p1P/8 w - - 0 1",                   // bm Be5         T&L vs L&B
            EET_73  = "3kB3/R4P2/8/8/1p6/pp6/5r2/1K6 w - - 0 1",                  // bm f8=Q f8=R   T&L vs T&B
            EET_74  = "5k2/1p6/1P1p4/1K1p2p1/PB1P2P1/3pR2p/1P2p1pr/8 w - - 0 1",  // bm Ba5         T&L vs T&B
            EET_75  = "6k1/p6p/1p1p2p1/2bP4/P1P5/2B3P1/4r2P/1R5K w - - 0 1",      // bm a5          T&L vs T&L
            EET_76  = "3R3B/8/1r4b1/8/4pP2/7k/8/7K w - - 0 1",                    // bm Bd4         T&L vs T&L
            EET_77  = "rk1b4/p2p2p1/1P6/2R2P2/8/2K5/8/5B2 w - - 0 1",             // bm Rc8+        T&L vs T&L
            EET_78  = "3r1k2/8/7R/8/8/pp1B4/P7/n1K5 w - - 0 1",                   // bm Rf6+        T&L vs T&S
            EET_79  = "r5k1/5ppp/1p6/2b1R3/1p3P2/2nP2P1/1B3PKP/5B2 w - - 0 1",    // bm d4          T&L&L vs T&L&S
            EET_80  = "5k2/3p1b2/4pN2/3PPpp1/6R1/6PK/1B1q1P2/8 w - - 0 1",        // bm Ba3+        T&L&S vs D&L
            EET_81  = "8/1p5p/6p1/1p4Pp/1PpR4/2P1K1kB/6Np/7b w - - 0 1",          // bm Rd1         T&L&S vs L&B
            EET_82  = "7k/1p1Nr2P/3Rb3/8/3K4/6b1/8/5B2 w - - 0 1",                // bm Ne5         T&L&S vs T&L&L
            EET_83  = "8/1B4k1/5pn1/6N1/1P3rb1/P1K4p/3R4/8 w - - 0 1",            // bm Nxh3        T&L&S vs T&L&S
            EET_84  = "8/7p/6p1/3Np1bk/4Pp2/1R3PPK/5r1P/8 w - - 0 1",             // bm Nc7         T&S vs T&L
            EET_85  = "1r6/3b1p2/2k4P/1N3p1P/5P2/8/3R4/2K5 w - - 0 1",            // bm Na7+        T&S vs T&L
            EET_86  = "k6r/8/1R6/8/1pK2p2/8/7N/3b4 w - - 0 1",                    // bm Nf1         T&S vs T&L
            EET_87  = "8/8/8/p1p5/2P1k3/1Pn5/1N1R2K1/1r6 w - - 0 1",              // bm Kg3         T&S vs T&S
            EET_88  = "5n1k/1r3P1p/p2p3P/P7/8/1N6/5R2/4K3 b - - 0 1",             // bm Re7+        T&S vs T&S
            EET_89  = "6R1/P2k1N2/r7/7P/r7/p7/7K/8 w - - 0 1",                    // bm Nh6         T&S vs T&T
            EET_90  = "8/1rk1P3/7p/P7/1N2r3/5RKb/8/8 w - - 0 1",                  // bm Na6+        T&S&B vs T&T&L
            EET_91  = "2K5/k3q3/6pR/6p1/6Pp/7P/8/3R4 w - - 0 1",                  // bm Rh7         T&T vs D
            EET_92  = "R5bk/5r2/P7/1P1pR3/3P4/7p/5p1K/8 w - - 0 1",               // bm Rh5+        T&T vs T&L
            EET_93  = "4k3/7r/3nb3/2R5/8/6n1/1R3K2/8 w - - 0 1",                  // bm Re5         T&T vs T&L&S
            EET_94  = "1r6/1r6/1P1KP3/6k1/1R4p1/7p/7R/8 w - - 0 1",               // bm Kc6 Rb5     T&T vs T&T
            EET_95  = "1k1K4/1p6/P4P2/2R5/4p2R/r2p4/8/3r4 w - - 0 1",             // bm Rf4         T&T vs T&T
            EET_96  = "5k2/R1p5/p1R3Pb/2K5/2B5/2q2b2/8/8 w - - 0 1",              // bm g7+         T&T&L vs D&L&L
            EET_97  = "8/8/k7/n7/p1R5/p7/4r1p1/KB3R2 w - - 0 1",                  // bm Rc3         T&T&L vs T&S&B
            EET_98  = "3r2k1/p1R2ppp/1p6/P1b1PP2/3p4/3R2B1/5PKP/1r6 w - - 0 1",   // bm f6          T&T&L vs T&T&L
            EET_99  = "8/5p2/5rp1/p2k1r1p/P1pNp2P/RP1bP1P1/5P1R/4K3 b - - 0 1",   // bm c3          T&T&S vs T&T&L
            EET_100 = "1r4k1/6pp/3Np1b1/p1R1P3/6P1/P2pr2P/1P1R2K1/8 b - - 0 1",   // bm Rf8         T&T&S vs T&T&L
            EET_16B = "4k1r1/pp2p2p/3pN1n1/2pP4/2P3P1/PP5P/8/5RK1 b - - 0 1",     // am Nf8         T&S vs T&S
            EET_26B = "8/3k3p/1p2p3/p4p2/Pb1Pp3/2B3PP/1P3P2/5K2 w - - 0 1";       // am Bxb4        L vs L

    /*
     * Silent But Deadly
     */
    public static final String
		    SBD_1   = "1qr3k1/p2nbppp/bp2p3/3p4/3P4/1P2PNP1/P2Q1PBP/1N2R1K1 b - - 0 1",         // bm Qc7
		    SBD_2   = "1r2r1k1/3bnppp/p2q4/2RPp3/4P3/6P1/2Q1NPBP/2R3K1 w - - 0 1",              // bm Rc7
		    SBD_3   = "2b1k2r/2p2ppp/1qp4n/7B/1p2P3/5Q2/PPPr2PP/R2N1R1K b k - 0 1",             // bm O-O
		    SBD_4   = "2b5/1p4k1/p2R2P1/4Np2/1P3Pp1/1r6/5K2/8 w - - 0 1",                       // bm Rd8
		    SBD_5   = "2brr1k1/ppq2ppp/2pb1n2/8/3NP3/2P2P2/P1Q2BPP/1R1R1BK1 w - - 0 1",         // bm g3
		    SBD_6   = "2kr2nr/1pp3pp/p1pb4/4p2b/4P1P1/5N1P/PPPN1P2/R1B1R1K1 b - - 0 1",         // bm Bf7
		    SBD_7   = "2r1k2r/1p1qbppp/p3pn2/3pBb2/3P4/1QN1P3/PP2BPPP/2R2RK1 b k - 0 1",        // bm O-O
		    SBD_8   = "2r1r1k1/pbpp1npp/1p1b3q/3P4/4RN1P/1P4P1/PB1Q1PB1/2R3K1 w - - 0 1",       // bm Rce1
		    SBD_9   = "2r2k2/r4p2/2b1p1p1/1p1p2Pp/3R1P1P/P1P5/1PB5/2K1R3 w - - 0 1",            // bm Kd2
		    SBD_10  = "2r3k1/5pp1/1p2p1np/p1q5/P1P4P/1P1Q1NP1/5PK1/R7 w - - 0 1",               // bm Rd1
		    SBD_11  = "2r3qk/p5p1/1n3p1p/4PQ2/8/3B4/5P1P/3R2K1 w - - 0 1",                      // bm e6
		    SBD_12  = "3b4/3k1pp1/p1pP2q1/1p2B2p/1P2P1P1/P2Q3P/4K3/8 w - - 0 1",                // bm Qf3
		    SBD_13  = "3n1r1k/2p1p1bp/Rn4p1/6N1/3P3P/2N1B3/2r2PP1/5RK1 w - - 0 1",              // bm Na4 Nce4
		    SBD_14  = "3q1rk1/3rbppp/ppbppn2/1N6/2P1P3/BP6/P1B1QPPP/R3R1K1 w - - 0 1",          // bm Nd4
		    SBD_15  = "3r1rk1/p1q4p/1pP1ppp1/2n1b1B1/2P5/6P1/P1Q2PBP/1R3RK1 w - - 0 1",         // bm Bh6
		    SBD_16  = "3r2k1/2q2p1p/5bp1/p1pP4/PpQ5/1P3NP1/5PKP/3R4 b - - 0 1",                 // bm Qd6
		    SBD_17  = "3r2k1/p1q1npp1/3r1n1p/2p1p3/4P2B/P1P2Q1P/B4PP1/1R2R1K1 w - - 0 1",       // bm Bc4
		    SBD_18  = "3r4/2k5/p3N3/4p3/1p1p4/4r3/PPP3P1/1K1R4 b - - 0 1",                      // bm Kd7
		    SBD_19  = "3r4/2R1np1p/1p1rpk2/p2b1p2/8/PP2P3/4BPPP/2R1NK2 w - - 0 1",              // bm b4
		    SBD_20  = "3rk2r/1b2bppp/p1qppn2/1p6/4P3/PBN2PQ1/1PP3PP/R1B1R1K1 b k - 0 1",        // bm O-O
		    SBD_21  = "3rk2r/1bq2pp1/2pb1n1p/p3pP2/P1B1P3/8/1P2QBPP/2RN1R1K b k - 0 1",         // bm Be7 O-O
		    SBD_22  = "3rkb1r/pppb1pp1/4n2p/2p5/3NN3/1P5P/PBP2PP1/3RR1K1 w - - 0 1",            // bm Nf5
		    SBD_23  = "3rr1k1/1pq2ppp/p1n5/3p4/6b1/2P2N2/PP1QBPPP/3R1RK1 w - - 0 1",            // bm Rfe1
		    SBD_24  = "4r1k1/1q1n1ppp/3pp3/rp6/p2PP3/N5P1/PPQ2P1P/3RR1K1 w - - 0 1",            // bm Rc1
		    SBD_25  = "4rb1k/1bqn1pp1/p3rn1p/1p2pN2/1PP1p1N1/P1P2Q1P/1BB2PP1/3RR1K1 w - - 0 1", // bm Qe2
		    SBD_26  = "4rr2/2p5/1p1p1kp1/p6p/P1P4P/6P1/1P3PK1/3R1R2 w - - 0 1",                 // bm Rfe1
		    SBD_27  = "5r2/pp1b1kpp/8/2pPp3/2P1p2P/4P1r1/PPRKB1P1/6R1 b - - 0 1",               // bm Ke7
		    SBD_28  = "6k1/1R5p/r2p2p1/2pN2B1/2bb4/P7/1P1K2PP/8 w - - 0 1",                     // bm Nf6+
		    SBD_29  = "6k1/pp1q1pp1/2nBp1bp/P2pP3/3P4/8/1P2BPPP/2Q3K1 w - - 0 1",               // bm Qc5
		    SBD_30  = "6k1/pp2rp1p/2p2bp1/1n1n4/1PN3P1/P2rP2P/R3NPK1/2B2R2 w - - 0 1",          // bm Rd2
		    SBD_31  = "8/2p2kpp/p6r/4Pp2/1P2pPb1/2P3P1/P2B1K1P/4R3 w - - 0 1",                  // bm h4
		    SBD_32  = "Q5k1/5pp1/5n1p/2b2P2/8/5N1P/5PP1/2q1B1K1 b - - 0 1",                     // bm Kh7
		    SBD_33  = "r1b1k1nr/1p3ppp/p1np4/4p1q1/2P1P3/N1NB4/PP3PPP/2RQK2R w Kkq - 0 1",      // bm O-O
		    SBD_34  = "r1b1k2r/p1pp1ppp/1np1q3/4P3/1bP5/1P6/PB1NQPPP/R3KB1R b KQkq - 0 1",      // bm O-O
		    SBD_35  = "r1b1k2r/ppppqppp/8/2bP4/3p4/6P1/PPQPPPBP/R1B2RK1 b kq - 0 1",            // bm O-O
		    SBD_36  = "r1b1k2r/ppq1bppp/2n5/2N1p3/8/2P1B1P1/P3PPBP/R2Q1RK1 b kq - 0 1",         // bm O-O
		    SBD_37  = "r1b1kb1r/pp2qppp/2pp4/8/4nP2/2N2N2/PPPP2PP/R1BQK2R w KQkq - 0 1",        // bm O-O
		    SBD_38  = "r1b1qrk1/pp4b1/2pRn1pp/5p2/2n2B2/2N2NPP/PPQ1PPB1/5RK1 w - - 0 1",        // bm Rd3
		    SBD_39  = "r1b2rk1/1pqn1pp1/p2bpn1p/8/3P4/2NB1N2/PPQB1PPP/3R1RK1 w - - 0 1",        // bm Rc1
		    SBD_40  = "r1b2rk1/2qnbp1p/p1npp1p1/1p4PQ/4PP2/1NNBB3/PPP4P/R4RK1 w - - 0 1",       // bm Qh6
		    SBD_41  = "r1b2rk1/pp2ppbp/2n2np1/2q5/5B2/1BN1PN2/PP3PPP/2RQK2R w K - 0 1",         // bm O-O
		    SBD_42  = "r1b2rk1/pp4pp/1q1Nppn1/2n4B/1P3P2/2B2RP1/P6P/R2Q3K b - - 0 1",           // bm Na6
		    SBD_43  = "r1b2rk1/ppp1qppp/1b1n4/8/B2n4/3NN3/PPPP1PPP/R1BQK2R w KQ - 0 1",         // bm O-O
		    SBD_44  = "r1b2rk1/ppq1bppp/2p1pn2/8/2NP4/2N1P3/PP2BPPP/2RQK2R w K - 0 1",          // bm O-O
		    SBD_45  = "r1bq1rk1/1p1n1pp1/p4n1p/2bp4/8/2NBPN2/PPQB1PPP/R3K2R w KQ - 0 1",        // bm O-O
		    SBD_46  = "r1bq1rk1/1p2ppbp/p2p1np1/6B1/2P1P3/2N5/PP1QBPPP/R3K2R w KQ - 0 1",       // bm O-O
		    SBD_47  = "r1bq1rk1/1p3ppp/p1np4/3Np1b1/2B1P3/P7/1PP2PPP/RN1QK2R w KQ - 0 1",       // bm O-O
		    SBD_48  = "r1bq1rk1/4bppp/ppnppn2/8/2P1P3/2N5/PPN1BPPP/R1BQK2R w KQ - 0 1",         // bm O-O
		    SBD_49  = "r1bq1rk1/pp1n1pbp/2n1p1p1/2ppP3/8/2PP1NP1/PP1N1PBP/R1BQ1RK1 w - - 0 1",  // bm d4
		    SBD_50  = "r1bq1rk1/pp1pppbp/2n2np1/8/4P3/1NN5/PPP1BPPP/R1BQK2R w KQ - 0 1",        // bm O-O
		    SBD_51  = "r1bq1rk1/pp2ppbp/2n2np1/2p3B1/4P3/2P2N2/PP1NBPPP/R2QK2R w KQ - 0 1",     // bm O-O
		    SBD_52  = "r1bq1rk1/pp2ppbp/2n3p1/2p5/2BPP3/2P1B3/P3NPPP/R2QK2R w KQ - 0 1",        // bm O-O
		    SBD_53  = "r1bq1rk1/pp3ppp/2n1pn2/2p5/1bBP4/2N1PN2/PP3PPP/R1BQ1RK1 w - - 0 1",      // bm a3
		    SBD_54  = "r1bq1rk1/pp3ppp/2n2n2/3p4/8/P1NB4/1PP2PPP/R1BQK2R w KQ - 0 1",           // bm O-O
		    SBD_55  = "r1bq1rk1/ppp1npb1/3p2pp/3Pp2n/1PP1P3/2N5/P2NBPPP/R1BQR1K1 b - - 0 1",    // bm Nf4
		    SBD_56  = "r1bq1rk1/ppp2ppp/2n1pn2/3p4/1bPP4/2NBPN2/PP3PPP/R1BQK2R w KQ - 0 1",     // bm O-O
		    SBD_57  = "r1bq1rk1/pppp1pbp/2n2np1/4p3/2P5/P1N2NP1/1P1PPPBP/R1BQK2R w KQ - 0 1",   // bm O-O
		    SBD_58  = "r1bqk2r/2ppbppp/p1n2n2/1p2p3/4P3/1B3N2/PPPPQPPP/RNB2RK1 b kq - 0 1",     // bm O-O
		    SBD_59  = "r1bqk2r/5ppp/p1np4/1p1Np1b1/4P3/2P5/PPN2PPP/R2QKB1R b KQkq - 0 1",       // bm O-O
		    SBD_60  = "r1bqk2r/bp3ppp/p1n1pn2/3p4/1PP5/P1N1PN2/1B3PPP/R2QKB1R b KQkq - 0 1",    // bm O-O
		    SBD_61  = "r1bqk2r/p2pppbp/2p3pn/2p5/4P3/2P2N2/PP1P1PPP/RNBQR1K1 b kq - 0 1",       // bm O-O
		    SBD_62  = "r1bqk2r/pp2bppp/2n1p3/1B1n4/3P4/2N2N2/PP3PPP/R1BQ1RK1 b kq - 0 1",       // bm O-O
		    SBD_63  = "r1bqk2r/pp2bppp/2n1p3/3n4/3P4/2NB1N2/PP3PPP/R1BQ1RK1 b kq - 0 1",        // bm O-O
		    SBD_64  = "r1bqk2r/pp2ppbp/2np1np1/2p5/4P3/1B1P1N1P/PPP2PP1/RNBQK2R w KQkq - 0 1",  // bm O-O
		    SBD_65  = "r1bqk2r/ppn1bppp/2n5/2p1p3/8/2NP1NP1/PP1BPPBP/R2Q1RK1 b kq - 0 1",       // bm O-O
		    SBD_66  = "r1bqk2r/ppp1bppp/2n5/3p4/3P4/2PB1N2/P1P2PPP/R1BQ1RK1 b kq - 0 1",        // bm O-O
		    SBD_67  = "r1bqk2r/ppp2ppp/2nb4/3np3/8/PP2P3/1BQP1PPP/RN2KBNR b KQkq - 0 1",        // bm O-O
		    SBD_68  = "r1bqk2r/ppp2ppp/3b4/4p3/8/1PPP1N2/2PB1PPP/R2Q1RK1 b kq - 0 1",           // bm O-O
		    SBD_69  = "r1bqk2r/pppp1ppp/5n2/4p3/Bb2P3/5Q2/PPPPNPPP/R1B1K2R b KQkq - 0 1",       // bm O-O
		    SBD_70  = "r1bqkb1r/pp3ppp/2n5/2pp4/3Pn3/2N2N2/PPP1BPPP/R1BQK2R w KQkq - 0 1",      // bm O-O
		    SBD_71  = "r1bqkb1r/pp3ppp/2npp3/3nP3/2BP4/5N2/PP3PPP/RNBQK2R w KQkq - 0 1",        // bm O-O
		    SBD_72  = "r1bqkbnr/3p1ppp/p1p1p3/8/4P3/3B4/PPP2PPP/RNBQK2R w KQkq - 0 1",          // bm O-O
		    SBD_73  = "r1bqkbnr/ppp2ppp/2n5/8/2BpP3/5N2/PP3PPP/RNBQK2R w KQkq - 0 1",           // bm O-O
		    SBD_74  = "r1bqrbk1/1pp3pp/2n2p2/p2np3/8/PP1PPN2/1BQNBPPP/R3K2R w KQ - 0 1",        // bm O-O
		    SBD_75  = "r1br2k1/1p2qppp/pN2pn2/P7/2pn4/4N1P1/1P2PPBP/R3QRK1 b - - 0 1",          // bm Rb8
		    SBD_76  = "r1q1k2r/1b1nbppp/pp1ppn2/8/2PQP3/1PN2NP1/PB3PBP/R2R2K1 b kq - 0 1",      // bm O-O
		    SBD_77  = "r1q1k2r/pb1nbppp/1p2pn2/8/P1PNP3/2B3P1/2QN1PBP/R4RK1 b kq - 0 1",        // bm O-O
		    SBD_78  = "r1r3k1/1bq2pbp/pp1pp1p1/2n5/P3PP2/R2B4/1PPBQ1PP/3N1R1K w - - 0 1",       // bm Bc3
		    SBD_79  = "r1rn2k1/pp1qppbp/6p1/3pP3/3P4/1P3N1P/PB1Q1PP1/R3R1K1 w - - 0 1",         // bm Rac1
		    SBD_80  = "r2q1rk1/1b1nbpp1/pp2pn1p/8/2BN3B/2N1P3/PP2QPPP/2R2RK1 w - - 0 1",        // bm Rfd1
		    SBD_81  = "r2q1rk1/1b3ppp/4pn2/1pP5/1b6/2NBPN2/1PQ2PPP/R3K2R w KQ - 0 1",           // bm O-O
		    SBD_82  = "r2q1rk1/pb1nppbp/6p1/1p6/3PP3/3QBN1P/P3BPP1/R3K2R w KQ - 0 1",           // bm O-O
		    SBD_83  = "r2q1rk1/pb2bppp/npp1pn2/3pN3/2PP4/1PB3P1/P2NPPBP/R2Q1RK1 w - - 0 1",     // bm e4
		    SBD_84  = "r2q1rk1/pppb1pbp/2np1np1/4p3/2P5/P1NPPNP1/1P3PBP/R1BQK2R w KQ - 0 1",    // bm O-O
		    SBD_85  = "r2qk2r/1b1n1ppp/4pn2/p7/1pPP4/3BPN2/1B3PPP/R2QK2R w KQkq - 0 1",         // bm O-O
		    SBD_86  = "r2qk2r/1b2bppp/p1n1pn2/1p6/1P6/P2BPN2/1B2QPPP/RN3RK1 b kq - 0 1",        // bm O-O
		    SBD_87  = "r2qk2r/2p2ppp/p1n1b3/1pbpP3/4n3/1BP2N2/PP1N1PPP/R1BQ1RK1 b kq - 0 1",    // bm O-O
		    SBD_88  = "r2qk2r/3n1ppp/p3p3/3nP3/3R4/5N2/1P1N1PPP/3QR1K1 b kq - 0 1",             // bm O-O
		    SBD_89  = "r2qk2r/p1pn1ppp/b3pn2/3p4/Nb1P4/1P3NP1/P3PPBP/1RBQ1RK1 b kq - 0 1",      // bm O-O Qe7
		    SBD_90  = "r2qk2r/ppp1bppp/2n2n2/8/2BP2b1/2N2N2/PP3PPP/R1BQR1K1 b kq - 0 1",        // bm O-O
		    SBD_91  = "r2qkb1r/pb1n1p2/2p1p2p/4P1pn/PppP4/2N2NB1/1P2BPPP/R2Q1RK1 w kq - 0 1",   // bm Ne4
		    SBD_92  = "r2qkb1r/pp2nppp/1np1p3/4Pb2/3P4/PB3N2/1P3PPP/RNBQ1RK1 b kq - 0 1",       // bm Ned5
		    SBD_93  = "r2qkb1r/pp3ppp/2bppn2/8/2PQP3/2N2N2/PP3PPP/R1B1K2R w KQkq - 0 1",        // bm O-O
		    SBD_94  = "r2qr1k1/p3bppp/1p2n3/3Q1N2/5P2/4B1P1/PP3R1P/R5K1 w - - 0 1",             // bm Rd1
		    SBD_95  = "r2r2k1/p1pnqpp1/1p2p2p/3b4/3P4/3BPN2/PP3PPP/2RQR1K1 b - - 0 1",          // bm c5
		    SBD_96  = "r2r2k1/pp1b1ppp/8/3p2P1/3N4/P3P3/1P3P1P/3RK2R b K - 0 1",                // bm Rac8
		    SBD_97  = "r3k2r/1b1nb1p1/p1q1pn1p/1pp3N1/4PP2/2N5/PPB3PP/R1BQ1RK1 w kq - 0 1",     // bm Nf3
		    SBD_98  = "r3k2r/1pqnnppp/p5b1/1PPp1p2/3P4/2N5/P2NB1PP/2RQ1RK1 b kq - 0 1",         // bm O-O
		    SBD_99  = "r3k2r/p1q1nppp/1pn5/2P1p3/4P1Q1/P1P2P2/4N1PP/R1B2K1R b kq - 0 1",        // bm O-O
		    SBD_100 = "r3k2r/pp2pp1p/6p1/2nP4/1R2PB2/4PK2/P5PP/5bNR w kq - 0 1",                // bm Ne2
		    SBD_101 = "r3k2r/ppp1bppp/2n5/3n4/3PB3/8/PP3PPP/RNB1R1K1 b kq - 0 1",               // bm O-O-O
		    SBD_102 = "r3kb1r/pp3ppp/4bn2/3p4/P7/4N1P1/1P2PPBP/R1B1K2R w KQkq - 0 1",           // bm O-O
		    SBD_103 = "r3kbnr/1pp3pp/p1p2p2/8/3qP3/5Q1P/PP3PP1/RNB2RK1 w kq - 0 1",             // bm Rd1
		    SBD_104 = "r3kr2/pppb1p2/2n3p1/3Bp2p/4P2N/2P5/PP3PPP/2KR3R b q - 0 1",              // bm O-O-O
		    SBD_105 = "r3nrk1/pp2qpb1/3p1npp/2pPp3/2P1P2N/2N3Pb/PP1BBP1P/R2Q1RK1 w - - 0 1",    // bm Re1
		    SBD_106 = "r3r1k1/1pqn1pbp/p2p2p1/2nP2B1/P1P1P3/2NB3P/5PP1/R2QR1K1 w - - 0 1",      // bm Rc1
		    SBD_107 = "r3r1k1/pp1q1ppp/2p5/P2n1p2/1b1P4/1B2PP2/1PQ3PP/R1B2RK1 w - - 0 1",       // bm e4
		    SBD_108 = "r3r1k1/pp3ppp/2ppqn2/5R2/2P5/2PQP1P1/P2P2BP/5RK1 w - - 0 1",             // bm Qd4
		    SBD_109 = "r3rbk1/p2b1p2/5p1p/1q1p4/N7/6P1/PP1BPPBP/3Q1RK1 w - - 0 1",              // bm Nc3
		    SBD_110 = "r4r1k/pp1bq1b1/n2p2p1/2pPp1Np/2P4P/P1N1BP2/1P1Q2P1/2KR3R w - - 0 1",     // bm Ne6
		    SBD_111 = "r4rk1/1bqp1ppp/pp2pn2/4b3/P1P1P3/2N2BP1/1PQB1P1P/2R2RK1 w - - 0 1",      // bm b3
		    SBD_112 = "r4rk1/1q2bppp/p1bppn2/8/3BPP2/3B2Q1/1PP1N1PP/4RR1K w - - 0 1",           // bm e5
		    SBD_113 = "r4rk1/pp2qpp1/2pRb2p/4P3/2p5/2Q1PN2/PP3PPP/4K2R w K - 0 1",              // bm O-O
		    SBD_114 = "r7/3rq1kp/2p1bpp1/p1Pnp3/2B4P/PP4P1/1B1RQP2/2R3K1 b - - 0 1",            // bm Rad8
		    SBD_115 = "r7/pp1bpp2/1n1p2pk/1B3P2/4P1P1/2N5/PPP5/1K5R b - - 0 1",                 // bm Kg5
		    SBD_116 = "rn1q1rk1/p4pbp/bp1p1np1/2pP4/8/P1N2NP1/1PQ1PPBP/R1B1K2R w KQ - 0 1",     // bm O-O
		    SBD_117 = "rn1q1rk1/pb3p2/1p5p/3n2P1/3p4/P4P2/1P1Q1BP1/R3KBNR b KQ - 0 1",          // bm Re8+
		    SBD_118 = "rn1q1rk1/pp2bppp/1n2p1b1/8/2pPP3/1BN1BP2/PP2N1PP/R2Q1RK1 w - - 0 1",     // bm Bc2
		    SBD_119 = "rn1q1rk1/pp3ppp/4bn2/2bp4/5B2/2NBP1N1/PP3PPP/R2QK2R w KQ - 0 1",         // bm O-O
		    SBD_120 = "rn1qkbnr/pp1b1ppp/8/1Bpp4/3P4/8/PPPNQPPP/R1B1K1NR b KQkq - 0 1",         // bm Qe7
		    SBD_121 = "rn1qr1k1/pb3p2/1p5p/3n2P1/3p4/P4P2/1P1QNBP1/R3KB1R b KQ - 0 1",          // bm d3
		    SBD_122 = "rn2kb1r/pp2nppp/1q2p3/3pP3/3P4/5N2/PP2NPPP/R1BQK2R w KQkq - 0 1",        // bm O-O
		    SBD_123 = "rn3rk1/1bqp1ppp/p3pn2/8/Nb1NP3/4B3/PP2BPPP/R2Q1RK1 w - - 0 1",           // bm Rc1
		    SBD_124 = "rn3rk1/pbp1qppp/1p1ppn2/8/2PP4/P1Q2NP1/1P2PPBP/R1B1K2R w KQ - 0 1",      // bm O-O
		    SBD_125 = "rnb1k2r/1pq2ppp/p2ppn2/2b5/3NPP2/2P2B2/PP4PP/RNBQ1R1K b kq - 0 1",       // bm O-O
		    SBD_126 = "rnb2rk1/ppq1ppbp/6p1/2p5/3PP3/2P2N2/P3BPPP/1RBQK2R w K - 0 1",           // bm O-O
		    SBD_127 = "rnbq1rk1/5ppp/p3pn2/1p6/2BP4/P1P2N2/5PPP/R1BQ1RK1 w - - 0 1",            // bm Bd3
		    SBD_128 = "rnbq1rk1/pp2ppbp/2pp1np1/8/P2PP3/2N2N2/1PP1BPPP/R1BQK2R w KQ - 0 1",     // bm O-O
		    SBD_129 = "rnbq1rk1/ppp1ppbp/6p1/8/8/2P2NP1/P2PPPBP/R1BQK2R w KQ - 0 1",            // bm O-O
		    SBD_130 = "rnbqk1nr/pp3pbp/2ppp1p1/8/2BPP3/2N2Q2/PPP2PPP/R1B1K1NR w KQkq - 0 1",    // bm Nge2
		    SBD_131 = "rnbqk2r/ppp2ppp/1b1p1n2/4p3/2B1P3/2PP1N2/PP1N1PPP/R1BQK2R b KQkq - 0 1", // bm O-O
		    SBD_132 = "rnbqk2r/pppp2pp/4pn2/5p2/1b1P4/2P2NP1/PP2PPBP/RNBQK2R b KQkq - 0 1",     // bm Be7
		    SBD_133 = "rnbqr1k1/pp1p1ppp/5n2/3Pb3/1P6/P1N3P1/4NPBP/R1BQK2R w KQ - 0 1",         // bm O-O
		    SBD_134 = "rnq1nrk1/pp3pbp/6p1/3p4/3P4/5N2/PP2BPPP/R1BQK2R w KQ - 0 1";             // bm O-O

    public static String[] SBD_LIST = {
            SBD_31 , //  "8/2p2kpp/p6r/4Pp2/1P2pPb1/2P3P1/P2B1K1P/4R3 w - - 0 1",                  // bm h4
            SBD_16 , //  "3r2k1/2q2p1p/5bp1/p1pP4/PpQ5/1P3NP1/5PKP/3R4 b - - 0 1",                 // bm Qd6
            SBD_12 , //  "3b4/3k1pp1/p1pP2q1/1p2B2p/1P2P1P1/P2Q3P/4K3/8 w - - 0 1",                // bm Qf3
            SBD_10 , //  "2r3k1/5pp1/1p2p1np/p1q5/P1P4P/1P1Q1NP1/5PK1/R7 w - - 0 1",               // bm Rd1
            SBD_2  , //  "1r2r1k1/3bnppp/p2q4/2RPp3/4P3/6P1/2Q1NPBP/2R3K1 w - - 0 1",              // bm Rc7
            SBD_3  , //  "2b1k2r/2p2ppp/1qp4n/7B/1p2P3/5Q2/PPPr2PP/R2N1R1K b k - 0 1",             // bm O-O
            SBD_9  , //  "2r2k2/r4p2/2b1p1p1/1p1p2Pp/3R1P1P/P1P5/1PB5/2K1R3 w - - 0 1",            // bm Kd2
            SBD_42 , //  "r1b2rk1/pp4pp/1q1Nppn1/2n4B/1P3P2/2B2RP1/P6P/R2Q3K b - - 0 1",           // bm Na6
            SBD_14 , //  "3q1rk1/3rbppp/ppbppn2/1N6/2P1P3/BP6/P1B1QPPP/R3R1K1 w - - 0 1",          // bm Nd4
            SBD_1  , //  "1qr3k1/p2nbppp/bp2p3/3p4/3P4/1P2PNP1/P2Q1PBP/1N2R1K1 b - - 0 1",         // bm Qc7
            SBD_7  , //  "2r1k2r/1p1qbppp/p3pn2/3pBb2/3P4/1QN1P3/PP2BPPP/2R2RK1 b k - 0 1",        // bm O-O
            SBD_8  , //  "2r1r1k1/pbpp1npp/1p1b3q/3P4/4RN1P/1P4P1/PB1Q1PB1/2R3K1 w - - 0 1",       // bm Rce1
            SBD_33 , //  "r1b1k1nr/1p3ppp/p1np4/4p1q1/2P1P3/N1NB4/PP3PPP/2RQK2R w Kkq - 0 1",      // bm O-O
            SBD_51 , //  "r1bq1rk1/pp2ppbp/2n2np1/2p3B1/4P3/2P2N2/PP1NBPPP/R2QK2R w KQ - 0 1",     // bm O-O
            SBD_60 , //  "r1bqk2r/bp3ppp/p1n1pn2/3p4/1PP5/P1N1PN2/1B3PPP/R2QKB1R b KQkq - 0 1",    // bm O-O
            SBD_57 , //  "r1bq1rk1/pppp1pbp/2n2np1/4p3/2P5/P1N2NP1/1P1PPPBP/R1BQK2R w KQ - 0 1",   // bm O-O
            SBD_49 , //  "r1bq1rk1/pp1n1pbp/2n1p1p1/2ppP3/8/2PP1NP1/PP1N1PBP/R1BQ1RK1 w - - 0 1",  // bm d4
            SBD_25   //  "4rb1k/1bqn1pp1/p3rn1p/1p2pN2/1PP1p1N1/P1P2Q1P/1BB2PP1/3RR1K1 w - - 0 1", // bm Qe2
    };

    /**
     * null move test - zugzwang
     */
    public static final String
		    ZUG_1 = "8/8/p1p5/1p5p/1P5p/8/PPP2K1p/4R1rk w - - 0 1",    // bm Rf1
		    ZUG_2 = "1q1k4/2Rr4/8/2Q3K1/8/8/8/8 w - - 0 1",            // bm Kh6
		    ZUG_3 = "7k/5K2/5P1p/3p4/6P1/3p4/8/8 w - - 0 1",           // bm g5
		    ZUG_4 = "8/6B1/p5p1/Pp4kp/1P5r/5P1Q/4q1PK/8 w - - 0 32",   // bm Qxh4
		    ZUG_5 = "8/8/1p1r1k2/p1pPN1p1/P3KnP1/1P6/8/3R4 b - - 0 1"; // bm Nxd5

    /**
     * Graham 2014 - 1/2-1/2
     */
    public static final String
		    GRA_1   = "r1b2rk1/ppq1ppbp/2n2np1/2ppN3/5P2/1P2P3/PBPPB1PP/RN1Q1RK1 w - - 4 9",     // 1. f4 d5 2. Nf3 Nf6 3. e3 g6 4. b3 Bg7 5. Bb2 O-O 6. Be2 c5 7. O-O Nc6 8. Ne5 Qc7 1/2-1/2
		    GRA_2   = "rn1q1rk1/pp3ppp/3bpn2/2pp4/5Pb1/1P2PN2/PBPPB1PP/RN1Q1RK1 w - c6 0 8",     // 1. f4 d5 2. Nf3 Nf6 3. e3 Bg4 4. Be2 e6 5. O-O Bd6 6. b3 O-O 7. Bb2 c5 1/2-1/2
		    GRA_3   = "r3kb1r/pp2pppp/1qb2n2/2pp4/5P2/1P2PN2/PBPP2PP/RN1Q1RK1 b kq - 1 8",       // 1. f4 d5 2. Nf3 c5 3. e3 Nc6 4. Bb5 Bd7 5. b3 Nf6 6. Bb2 Qb6 7. Bxc6 Bxc6 8. O-O 1/2-1/2
		    GRA_4   = "r2qk2r/pp2bppp/2b1pn2/2pp4/5P2/1P2PN2/PBPP2PP/RN1Q1RK1 w kq - 2 9",       // 1. f4 d5 2. Nf3 c5 3. e3 Nc6 4. Bb5 Bd7 5. b3 Nf6 6. Bxc6 Bxc6 7. Bb2 e6 8. O-O Be7 1/2-1/2
		    GRA_5   = "r2qkb1r/pp1npppp/2p2n2/3p4/5P2/5BP1/PPPPP2P/RNBQ1RK1 w kq - 2 7",         // 1. f4 d5 2. Nf3 Nf6 3. g3 Bg4 4. Bg2 Bxf3 5. Bxf3 c6 6. O-O Nbd7 1/2-1/2
		    GRA_6   = "r2q1rk1/pp1bppbp/2n2np1/2ppN3/5P2/1P2P3/PBPPB1PP/RN1Q1RK1 w - - 4 9",     // 1. f4 d5 2. Nf3 Nf6 3. e3 g6 4. b3 Bg7 5. Bb2 O-O 6. Be2 c5 7. O-O Nc6 8. Ne5 Bd7 1/2-1/2
		    GRA_7   = "r2qk2r/pb2bppp/2p1pn2/2ppN3/5P2/1P2P3/PBPP2PP/RN1QK2R w KQkq - 2 9",      // 1. f4 d5 2. Nf3 Nf6 3. e3 e6 4. b3 c5 5. Bb2 Nc6 6. Bb5 Be7 7. Bxc6+ bxc6 8. Ne5 Bb7 1/2-1/2
		    GRA_8   = "rnbqkb1r/ppp1pppp/5n2/8/5P2/1P1BP3/P1P3PP/RNBQK1NR b KQkq - 0 5",         // 1. f4 d5 2. b3 Nf6 3. e3 d4 4. Bd3 dxe3 5. dxe3 1/2-1/2
		    GRA_9   = "r2qk2r/pp1bbppp/2n1pn2/1Bpp4/5P2/1P1PPN2/PBP3PP/RN1Q1RK1 b kq - 0 8",     // 1. f4 d5 2. b3 Nf6 3. e3 c5 4. Nf3 Nc6 5. Bb5 Bd7 6. Bb2 e6 7. O-O Be7 8. d3 1/2-1/2
		    GRA_10  = "r1bq1rk1/pp2ppbp/2n2np1/2p5/3p1P2/N2P1NP1/PPP1P1BP/R1B1QRK1 w - - 2 9",   // 1. f4 g6 2. Nf3 Bg7 3. g3 Nf6 4. Bg2 O-O 5. O-O c5 6. d3 d5 7. Qe1 d4 8. Na3 Nc6 1/2-1/2
		    GRA_11  = "rnbq1rk1/1p2bppp/p2p1n2/4p3/4P3/1NN1B3/PPP1BPPP/R2QK2R w KQ - 4 9",       // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nb3 Be7 8. Be3 O-O 1/2-1/2
		    GRA_12  = "r1bqk2r/1p2bppp/p1nppn2/8/3NP3/2N1B3/PPP1BPPP/R2Q1RK1 w kq - 4 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. Be2 a6 7. Be3 Be7 8. O-O Nc6 1/2-1/2
		    GRA_13  = "rn1qk2r/1p2bppp/p2pbn2/4p1B1/4P3/1NN5/PPP1BPPP/R2QK2R w KQkq - 4 9",      // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nb3 Be7 8. Bg5 Be6 1/2-1/2
		    GRA_14  = "r3kb1r/pp1q1ppp/2nppn2/8/3pP3/2P2N2/PP3PPP/RNBQR1K1 w kq - 0 9",          // 1. e4 c5 2. Nf3 d6 3. Bb5 Bd7 4. Bxd7 Qxd7 5. O-O Nc6 6. c3 Nf6 7. Re1 e6 8. d4 cxd4 1/2-1/2
		    GRA_15  = "r3kb1r/pp1qpppp/3p4/2pPn3/4n3/2P2N2/PP3PPP/RNBQ1RK1 w kq - 1 9",          // 1. e4 c5 2. Nf3 d6 3. Bb5 Bd7 4. Bxd7 Qxd7 5. O-O Nc6 6. c3 Nf6 7. d4 Nxe4 8. d5 Ne5 1/2-1/2
		    GRA_16  = "r3kb1r/pp1qpp1p/2np1np1/8/2PNP3/2N5/PP3PPP/R1BQK2R w KQkq - 0 9",         // 1. e4 c5 2. Nf3 d6 3. Bb5 Bd7 4. Bxd7 Qxd7 5. c4 Nc6 6. Nc3 Nf6 7. d4 cxd4 8. Nxd4 g6 1/2-1/2
		    GRA_17  = "r1b1kb1r/pp2pppp/3p1n2/1N2P3/3n4/5N2/PPP2PPP/R1B1K2R w KQkq - 0 9",       // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Qxd4 Nf6 5. e5 Nc6 6. Bb5 Qa5 7. Nc3 Qxb5 8. Nxb5 Nxd4 1/2-1/2
		    GRA_18  = "rnb1k2r/1pq1bppp/p2ppn2/6B1/3NPP2/2N2Q2/PPP3PP/R3KB1R w KQkq - 3 9",      // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Bg5 e6 7. f4 Be7 8. Qf3 Qc7 1/2-1/2
		    GRA_19  = "rnbq1rk1/1p2bppp/p2p1n2/4p3/4P3/1NN5/PPP1BPPP/R1BQ1RK1 w - - 4 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nb3 Be7 8. O-O O-O 1/2-1/2
		    GRA_20  = "rn1qk2r/1p2bppp/p2pbn2/4p3/4P3/1NN1BP2/PPP3PP/R2QKB1R w KQkq - 1 9",      // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be3 e5 7. Nb3 Be6 8. f3 Be7 1/2-1/2
		    GRA_21  = "r1bq1rk1/pp2bppp/2nppn2/8/3NPP2/2N5/PPP1B1PP/R1BQ1RK1 w - - 1 9",         // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. Be2 Be7 7. O-O O-O 8. f4 Nc6 1/2-1/2
		    GRA_22  = "r1bqkb1r/pp3pp1/2nppn2/6B1/3NP3/2N5/PPP2P1P/R2QKB1R w KQkq - 1 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. g4 h6 7. g5 hxg5 8. Bxg5 Nc6 1/2-1/2
		    GRA_23  = "rnbqk2r/pp2bpp1/4pn1p/3p4/3NP1PP/2N5/PPP2P2/R1BQKBR1 w Qkq - 0 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. g4 h6 7. h4 Be7 8. Rg1 d5 1/2-1/2
		    GRA_24  = "r1bqk2r/1p2bppp/p1nppn2/8/3NP3/2N1BP2/PPPQ2PP/R3KB1R w KQkq - 2 9",       // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. Be3 Be7 7. f3 a6 8. Qd2 Nc6 1/2-1/2
		    GRA_25  = "rnb1k2r/1pq1bppp/p2ppn2/8/3NPP2/2N5/PPP1B1PP/R1BQ1RK1 w kq - 1 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e6 7. O-O Be7 8. f4 Qc7 1/2-1/2
		    GRA_26  = "rnbq1rk1/1p2bppp/p2ppn2/8/3NPP2/2N5/PPP1B1PP/R1BQ1RK1 w - - 1 9",         // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e6 7. O-O Be7 8. f4 O-O 1/2-1/2
		    GRA_27  = "r1bq1rk1/pp2ppbp/2np1np1/8/3NP3/2N1B3/PPP1BPPP/R2Q1RK1 w - - 6 9",        // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 g6 6. Be3 Bg7 7. Be2 Nc6 8. O-O O-O 1/2-1/2
		    GRA_28  = "r2qkb1r/pp1b1pp1/2nppn1p/8/3NP1P1/2N4P/PPP2PB1/R1BQK2R w KQkq - 1 9",     // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 e6 6. g4 h6 7. Bg2 Nc6 8. h3 Bd7 1/2-1/2
		    GRA_29  = "r2qkb1r/1p1b1ppp/p1nppn2/6B1/3NP3/2N5/PPPQ1PPP/2KR1B1R w kq - 2 9",       // 1. e4 c5 2. Nf3 Nc6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Bg5 e6 7. Qd2 a6 8. O-O-O Bd7 1/2-1/2
		    GRA_30  = "r1bqkb1r/1p3pp1/p1nppn1p/6B1/3NP3/2N5/PPPQ1PPP/2KR1B1R w kq - 0 9",       // 1. e4 c5 2. Nf3 Nc6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Bg5 e6 7. Qd2 a6 8. O-O-O h6 1/2-1/2
		    GRA_31  = "r1bqk2r/1p2bppp/p1nppn2/8/2BNP3/2N1B3/PPP1QPPP/R3K2R w KQkq - 0 9",       // 1. e4 c5 2. Nf3 Nc6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Bc4 e6 7. Be3 Be7 8. Qe2 a6 1/2-1/2
		    GRA_32  = "r1b1k2r/pp2bppp/1qnppn2/8/2B1P3/1NN5/PPP2PPP/R1BQ1RK1 w kq - 2 9",        // 1. e4 c5 2. Nf3 Nc6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Bc4 Qb6 7. Nb3 e6 8. O-O Be7 1/2-1/2
		    GRA_33  = "r1b1kb1r/1p3ppp/p1n1p3/2pq4/8/2P2N2/PP1P1PPP/RNBQR1K1 w kq - 0 9",        // 1. e4 c5 2. Nf3 Nc6 3. Bb5 e6 4. O-O Nge7 5. c3 d5 6. exd5 Qxd5 7. Re1 a6 8. Bxc6 Nxc6 1/2-1/2
		    GRA_34  = "r1bq1rk1/pp2bppp/2nppn2/8/3NP3/2N1B3/PPP1BPPP/R2Q1RK1 w - - 6 9",         // 1. e4 c5 2. Nf3 e6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Be2 Be7 7. O-O O-O 8. Be3 Nc6 1/2-1/2
		    GRA_35  = "r1bqkb1r/3n1ppp/p2ppn2/1p6/3NP3/2N1BP2/PPPQ2PP/R3KB1R w KQkq - 2 9",      // 1. e4 c5 2. Nf3 e6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Be3 a6 7. f3 b5 8. Qd2 Nbd7 1/2-1/2
		    GRA_36  = "rnb1k1nr/1pqp1ppp/p3p3/8/1b1NP3/2N3P1/PPP2P1P/R1BQKB1R w KQkq - 1 7",     // 1. e4 c5 2. Nf3 e6 3. d4 cxd4 4. Nxd4 a6 5. Nc3 Qc7 6. g3 Bb4 1/2-1/2
		    GRA_37  = "r1b1k2r/1pqp1ppp/p1n1pn2/8/1b1NP3/2N1B3/PPPQ1PPP/2KR1B1R w kq - 4 9",     // 1. e4 c5 2. Nf3 e6 3. d4 cxd4 4. Nxd4 Nc6 5. Nc3 Qc7 6. Be3 a6 7. Qd2 Nf6 8. O-O-O Bb4 1/2-1/2
		    GRA_38  = "1rbqk1nr/pp3pbp/3pp1p1/2p5/3nP3/2NPB1P1/PPPQNPBP/R3K2R w KQk - 4 9",      // 1. e4 c5 2. Nc3 Nc6 3. g3 g6 4. Bg2 Bg7 5. d3 d6 6. Be3 e6 7. Qd2 Rb8 8. Nge2 Nd4 1/2-1/2
		    GRA_39  = "r1bq1rk1/pp2npbp/2npp1p1/2p5/4PP2/2NP1NP1/PPP3BP/R1BQ1RK1 w - - 4 9",     // 1. e4 c5 2. Nc3 Nc6 3. g3 g6 4. Bg2 Bg7 5. d3 d6 6. f4 e6 7. Nf3 Nge7 8. O-O O-O 1/2-1/2
		    GRA_40  = "r1bqk2r/pp2npbp/2n1p1p1/2p5/2B1P3/2N2N2/PPPP2PP/R1BQK2R w KQkq - 0 8",    // 1. e4 c5 2. Nc3 Nc6 3. f4 g6 4. Nf3 Bg7 5. Bc4 e6 6. f5 Nge7 7. fxe6 dxe6 1/2-1/2
		    GRA_41  = "r1bqk1nr/1p2ppbp/p2p2p1/8/3pPP2/2NB4/PPPP2PP/R1BQ1RK1 w kq - 0 9",        // 1. e4 c5 2. Nc3 Nc6 3. f4 g6 4. Nf3 Bg7 5. Bb5 Nd4 6. O-O a6 7. Bd3 d6 8. Nxd4 cxd4 1/2-1/2
		    GRA_42  = "r1bq1rk1/pp2ppbp/2np1np1/8/3NP3/1BN1B3/PPP2PPP/R2QK2R w KQ - 0 9",        // 1. e4 c5 2. Nc3 Nc6 3. Nf3 g6 4. d4 cxd4 5. Nxd4 Bg7 6. Be3 Nf6 7. Bc4 O-O 8. Bb3 d6 1/2-1/2
		    GRA_43  = "r3kb1r/pp3ppp/2n1pn2/2pq3b/3P4/2P2N1P/PP2BPP1/RNBQ1RK1 w kq - 3 9",       // 1. e4 c5 2. c3 d5 3. exd5 Qxd5 4. d4 Nf6 5. Nf3 Bg4 6. Be2 e6 7. h3 Bh5 8. O-O Nc6 1/2-1/2
		    GRA_44  = "r3kb1r/pp3ppp/2n1pn2/3q4/3p2b1/2P1BN2/PP2BPPP/RN1Q1RK1 w kq - 0 9",       // 1. e4 c5 2. c3 d5 3. exd5 Qxd5 4. d4 Nf6 5. Nf3 Bg4 6. Be2 e6 7. O-O Nc6 8. Be3 cxd4 1/2-1/2
		    GRA_45  = "r1bqkb1r/1p1pn1pp/p1n1pp2/6B1/2B1P3/2N2N2/PP3PPP/R2Q1RK1 w kq - 0 9",     // 1. e4 c5 2. d4 cxd4 3. c3 dxc3 4. Nxc3 Nc6 5. Nf3 e6 6. Bc4 a6 7. O-O Nge7 8. Bg5 f6 1/2-1/2
		    GRA_46  = "r1bqkb1r/1p3pp1/p1n1p3/2ppPn1p/5P2/2N2NP1/PPPP2BP/R1BQ1RK1 w kq h6 0 9",  // 1. e4 c5 2. f4 e6 3. Nf3 Nc6 4. Nc3 a6 5. g3 d5 6. e5 Nge7 7. Bg2 Nf5 8. O-O h5 1/2-1/2
		    GRA_47  = "r1bqkbnr/1p3ppp/p1n1p3/2p5/3pPP2/2N2NP1/PPPP2BP/R1BQK2R w KQkq - 0 7",    // 1. e4 c5 2. f4 e6 3. Nf3 Nc6 4. Nc3 a6 5. g3 d5 6. Bg2 d4 1/2-1/2
		    GRA_48  = "r1bqkb1r/1p3ppp/p1n1pn2/2p5/3pPP2/3P1NP1/PPP1N2P/R1BQKB1R w KQkq - 2 8",  // 1. e4 c5 2. f4 e6 3. Nf3 Nc6 4. Nc3 a6 5. g3 d5 6. d3 d4 7. Ne2 Nf6 1/2-1/2
		    GRA_49  = "r2q1rk1/pp1nnppp/3bp3/2pp4/2P1PP2/3P1N2/PP4PP/RNBQ1RK1 w - - 1 9",        // 1. e4 c5 2. f4 e6 3. Nf3 d5 4. Bb5 Bd7 5. Bxd7 Nxd7 6. d3 Bd6 7. O-O Ne7 8. c4 O-O 1/2-1/2
		    GRA_50  = "r1bq1rk1/p3ppbp/2pp1np1/2p5/4PP2/2NP1N2/PPP3PP/R1BQ1RK1 w - - 4 9",       // 1. e4 c5 2. f4 Nc6 3. Nf3 g6 4. Bb5 Bg7 5. Bxc6 bxc6 6. d3 d6 7. O-O Nf6 8. Nc3 O-O 1/2-1/2
		    GRA_51  = "r1bqkb1r/ppp2ppp/2np1n2/3Pp3/4P3/2P2N2/PP3PPP/RNBQKB1R b KQkq - 0 5",     // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d6 5. d5 1/2-1/2
		    GRA_52  = "r1bq1rk1/ppp1bppp/5n2/4p3/4P3/2P1B3/PP1N1PPP/R2QKB1R w KQ - 0 9",         // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d6 5. Be3 Be7 6. Nbd2 O-O 7. dxe5 Nxe5 8. Nxe5 dxe5 1/2-1/2
		    GRA_53  = "r3kb1r/pppbqppp/8/1B1Qn3/3Pn3/2P5/PP3PPP/RNB1K2R w KQkq - 1 9",           // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d5 5. Bb5 Nxe4 6. Nxe5 Bd7 7. Qb3 Nxe5 8. Qxd5 Qe7 1/2-1/2
		    GRA_54  = "rnbqkb1r/ppp2ppp/3p4/3P4/8/2PN4/PP3PPP/RNBQK2R w KQkq - 0 9",             // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 Nxe4 5. d5 Nb8 6. Bd3 Nc5 7. Nxe5 Nxd3 8. Nxd3 d6 1/2-1/2
		    GRA_55  = "r1bqkb1r/pppp1pp1/5np1/3P4/8/2PB4/PP3PPP/RNBQK2R w KQkq - 2 9",           // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 Nxe4 5. d5 Ne7 6. Nxe5 Ng6 7. Nxg6 hxg6 8. Bd3 Nf6 1/2-1/2
		    GRA_56  = "rnbqk2r/ppppbppp/8/3P4/8/2PN4/PP3PPP/RNBQK2R w KQkq - 1 9",               // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 Nxe4 5. d5 Nb8 6. Bd3 Nc5 7. Nxe5 Nxd3 8. Nxd3 Be7 1/2-1/2
		    GRA_57  = "r1bqkbnr/ppp3pp/2n2p2/1B1pp3/Q3P3/2P2N2/PP1P1PPP/RNB1K2R b KQkq - 1 5",   // 1. e4 e5 2. Nf3 Nc6 3. c3 d5 4. Qa4 f6 5. Bb5 1/2-1/2
		    GRA_58  = "r2qkb1r/pppb1ppp/5n2/3P4/2B1p3/2P2Q2/PP1P1PPP/RNB1K2R w KQkq - 0 9",      // 1. e4 e5 2 .Nf3 Nc6 3. c3 d5 4. Qa4 Bd7 5. exd5 Nd4 6. Qd1 Nxf3+ 7. Qxf3 Nf6 8. Bc4 e4 1/2-1/2
		    GRA_59  = "2kr1bnr/pppb1ppp/2nq4/4p3/Q1B5/2P2N2/PP1P1PPP/RNB2RK1 w - - 4 9",         // 1. e4 e5 2. Nf3 Nc6 3. c3 d5 4. Qa4 Qd6 5. Bb5 Bd7 6. exd5 Qxd5 7. O-O O-O-O 8. Bc4 Qd6 1/2-1/2
		    GRA_60  = "r1b1kbnr/ppp2ppp/2n5/1B1qp3/Q7/2P2N2/PP1P1PPP/RNB1K2R b KQkq - 1 6",      // 1. e4 e5 2. Nf3 Nc6 3. c3 d5 4. Qa4 Qd6 5. exd5 Qxd5 6. Bb5 1/2-1/2
		    GRA_61  = "r1bq1rk1/pppp1ppp/2n5/3nP3/3P4/5N2/PP1Q1PPP/RN2KB1R w KQ - 1 9",          // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 exd4 5. e5 Nd5 6. cxd4 Bb4 7. Bd2 Bxd2 8. Qxd2 O-O 1/2-1/2
		    GRA_62  = "r2qk2r/ppp1bppp/2npb3/1B1nP3/3P4/2N2N2/PP3PPP/R1BQK2R w KQkq - 4 9",      // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 exd4 5. e5 Nd5 6. cxd4 d6 7. Bb5 Be7 8. Nc3 Be6 1/2-1/2
		    GRA_63  = "r1b1kb1r/ppp3pp/2n2q2/3p4/3pn3/2P2N2/PP1NQPPP/R1B1KB1R w KQkq - 0 9",     // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 exd4 5. e5 Ne4 6. Qe2 f5 7. exf6 d5 8. Nbd2 Qxf6 1/2-1/2
		    GRA_64  = "r2qkb1r/p1pb1ppp/2p5/3pP3/3Nn3/2P5/PP3PPP/RNBQK2R w KQkq - 0 9",          // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d5 5. Bb5 exd4 6. e5 Ne4 7. Nxd4 Bd7 8. Bxc6 bxc6 1/2-1/2
		    GRA_65  = "r1bq1rk1/ppp2ppp/2n5/1B1pP3/1b1Pn3/5N2/PP1N1PPP/R1BQK2R w KQ - 3 9",      // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d5 5. Bb5 exd4 6. e5 Ne4 7. cxd4 Bb4 8. Nbd2 O-O 1/2-1/2
		    GRA_66  = "r3kb1r/ppp2ppp/2b2q2/3pN3/3Pn3/2P2Q2/PP3PPP/RNB1K2R w KQkq - 2 9",        // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d5 5. Bb5 Nxe4 6. Nxe5 Bd7 7. Bxc6 Bxc6 8. Qf3 Qf6 1/2-1/2
		    GRA_67  = "r3kbnr/pppq2pp/2n1bp2/3pp3/Q3P3/2PPBN2/PP1N1PPP/R3KB1R b KQkq - 4 7",     // 1. e4 e5 2. Nf3 Nc6 3. c3 d5 4. Qa4 f6 5. d3 Be6 6. Be3 Qd7 7. Nbd2 1/2-1/2
		    GRA_68  = "rnb1k2r/ppp1bppp/3p1n2/6B1/8/3P1N2/PPP1BPPP/RN2K2R w KQkq - 1 9",         // 1. e4 e5 2. Nf3 Nf6 3. Nxe5 d6 4. Nf3 Nxe4 5. Qe2 Qe7 6. d3 Nf6 7. Bg5 Qxe2 8. Bxe2 Be7 1/2-1/2
		    GRA_69  = "rn2kb1r/ppp2ppp/1q2pn2/5b2/1P6/2N2N1P/P1PP1PP1/1RBQKB1R w Kkq - 0 8",     // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. Nf3 Nf6 5. h3 Bf5 6. b4 Qb6 7. Rb1 e6 1/2-1/2
		    GRA_70  = "rn2k2r/pp3ppp/2p1pn2/q4b2/1bBP4/2N2N2/PPPBQPPP/R3K2R w KQkq - 2 9",       // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. d4 Nf6 5. Nf3 c6 6. Bc4 Bf5 7. Bd2 e6 8. Qe2 Bb4 1/2-1/2
		    GRA_71  = "rn2kb1r/pp3ppp/2p1pn2/q7/3P4/2NQ1N1P/PPP2PP1/R1B1K2R w KQkq - 0 9",       // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. d4 Nf6 5. Nf3 c6 6. h3 Bf5 7. Bd3 Bxd3 8. Qxd3 e6 1/2-1/2
		    GRA_72  = "r3kb1r/pp1n1ppp/2p1pn2/q4b2/2B5/P1NP1N1P/1PP2PP1/R1BQK2R w KQkq - 1 9",   // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. Nf3 Nf6 5. h3 Bf5 6. Bc4 e6 7. d3 c6 8. a3 Nbd7 1/2-1/2
		    GRA_73  = "r3kb1r/pp1n1ppp/2p1pn2/q3Nb2/2BP4/2N5/PPP2PPP/R1BQ1RK1 w kq - 2 9",       // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. d4 Nf6 5. Nf3 Bf5 6. Bc4 e6 7. O-O c6 8. Ne5 Nbd7 1/2-1/2
		    GRA_74  = "r3kb1r/ppp2ppp/1nn1b3/4P3/2P2P2/8/PP4PP/R1BNKBNR w KQkq - 1 9",           // 1. e4 d5 2. exd5 Nf6 3. d4 Nxd5 4. c4 Nb6 5. Nc3 e5 6. dxe5 Qxd1 7. Nxd1 Nc6 8. f4 Be6 1/2-1/2
		    GRA_75  = "rn1qkb1r/pppn1ppp/4p3/2P5/2BP4/5b1P/PP3PP1/RNBQK2R w KQkq - 0 9",         // 1. e4 d5 2. exd5 Nf6 3. d4 Nxd5 4. c4 Nb6 5.Nf3 Bg4 6. c5 N6d7 7. Bc4 e6 8.h3 Bxf3 1/2-1/2
		    GRA_76  = "rnbqk2r/ppp2pbp/1n2p1p1/4p1N1/3P1P2/1B6/PPP3PP/RNBQK2R w KQkq - 0 9",     // 1. e4 Nf6 2. e5 Nd5 3. d4 d6 4. Nf3 g6 5. Bc4 Nb6 6. Bb3 Bg7 7. Ng5 e6 8. f4 dxe5 1/2-1/2
		    GRA_77  = "r1bq1rk1/ppp1ppbp/1nnp2p1/4P3/3P4/1B3N2/PPP1QPPP/RNB2RK1 w - - 8 9",      // 1. e4 Nf6 2. e5 Nd5 3. d4 d6 4. Nf3 g6 5. Bc4 Nb6 6. Bb3 Bg7 7. Qe2 Nc6 8. O-O O-O 1/2-1/2
		    GRA_78  = "rnbq1rk1/pp2n1pp/4p3/2ppPp2/3P2Q1/P1PB4/2P2PPP/R1B1K1NR w KQ f6 0 9",     // 1. e4 e6 2. d4 d5 3. Nc3 Bb4 4. e5 c5 5. a3 Bxc3 6. bxc3 Ne7 7. Qg4 O-O 8. Bd3 f5 1/2-1/2
		    GRA_79  = "rnb1k2r/1p1nqppp/p3p3/2ppP3/3P1P2/2N2N2/PPP3PP/R2QKB1R w KQkq c6 0 9",    // 1. e4 e6 2. d4 d5 3. Nc3 Nf6 4. Bg5 Be7 5. e5 Nfd7 6. Bxe7 Qxe7 7. f4 a6 8. Nf3 c5 1/2-1/2
		    GRA_80  = "rnb2rk1/pp1nqppp/4p3/2ppP3/3P1P2/2N2N2/PPP3PP/R2QKB1R w KQ c6 0 9",       // 1. e4 e6 2. d4 d5 3. Nc3 Nf6 4. Bg5 Be7 5. e5 Nfd7 6. Bxe7 Qxe7 7. f4 O-O 8. Nf3 c5 1/2-1/2
		    GRA_81  = "r1bq1rk1/pppn1ppp/4pb2/8/3PN3/5N2/PPPQ1PPP/R3KB1R w KQ - 4 9",            // 1. e4 e6 2. d4 d5 3. Nc3 Nf6 4. Bg5 dxe4 5. Nxe4 Be7 6. Bxf6 Bxf6 7. Nf3 Nd7 8. Qd2 O-O 1/2-1/2
		    GRA_82  = "r1bqk2r/pp1n1ppp/2n1p3/2bpP3/3N1P2/2N1B3/PPP3PP/R2QKB1R w KQkq - 1 9",    // 1. e4 e6 2. d4 d5 3. Nc3 Nf6 4. e5 Nfd7 5. f4 c5 6. Nf3 Nc6 7. Be3 cxd4 8. Nxd4 Bc5 1/2-1/2
		    GRA_83  = "r1bq1rk1/pp2nppp/2n1p3/2ppP3/3P2Q1/P1P2N2/2P2PPP/R1B1KB1R w KQ - 4 9",    // 1. e4 e6 2. d4 d5 3. Nc3 Bb4 4. e5 Ne7 5. a3 Bxc3 6. bxc3 c5 7. Qg4 Nbc6 8. Nf3 O-O 1/2-1/2
		    GRA_84  = "rnbq1rk1/pp2nppp/4p3/3pP3/2pP2Q1/P1P2N2/2P2PPP/R1B1KB1R w KQ - 0 9",      // 1. e4 e6 2. d4 d5 3. Nc3 Bb4 4. e5 Ne7 5. a3 Bxc3 6. bxc3 c5 7. Qg4 O-O 8. Nf3 c4 1/2-1/2
		    GRA_85  = "r1bq1rk1/ppp1ppbp/2n3p1/4P2n/3P4/2NB1N2/PPP3PP/R1BQK2R w KQ - 1 9",       // 1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. f4 Bg7 5. Nf3 O-O 6. Bd3 Nc6 7. e5 dxe5 8. fxe5 Nh5 1/2-1/2
		    GRA_86  = "rnb2rk1/pp2ppbp/3p1np1/2q5/4PP2/2NB1N2/PPP1Q1PP/R1B1K2R w KQ - 2 9",      // 1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. f4 Bg7 5. Nf3 c5 6. dxc5 Qa5 7. Bd3 Qxc5 8. Qe2 O-O 1/2-1/2
		    GRA_87  = "rn1qk2r/pp1Bppbp/3p1np1/2p5/3PPP2/2N2N2/PPP3PP/R1BQK2R b KQkq - 0 7",     // 1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. f4 Bg7 5. Nf3 c5 6. Bb5 Bd7 7. Bxd7 1/2-1/2
		    GRA_88  = "r1b2rk1/pp1nppbp/2pp1np1/q7/3PPB2/2N2N2/PPP1BPPP/R2QR1K1 w - - 4 9",      // 1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. Nf3 Bg7 5. Be2 O-O 6. O-O c6 7. Re1 Nbd7 8. Bf4 Qa5 1/2-1/2
		    GRA_89  = "r2qk1nr/1b1nppbp/p2p2p1/1pp5/3PP1P1/2N1BP2/PPPQN2P/R3KB1R w KQkq c6 0 9", // 1. e4 d6 2. d4 g6 3. Nc3 Bg7 4. Be3 a6 5. Qd2 Nd7 6. f3 b5 7. g4 Bb7 8. Nge2 c5 1/2-1/2
		    GRA_90  = "r2qkbnr/pp1npppb/2p4p/7P/3P4/5NN1/PPP2PP1/R1BQKB1R w KQkq - 1 9",         // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Bf5 5. Ng3 Bg6 6. h4 h6 7. Nf3 Nd7 8. h5 Bh7 1/2-1/2
		    GRA_91  = "r2qkb1r/pp1npppp/2p2n2/8/3P4/3Q1NN1/PPP2PPP/R1B1K2R w KQkq - 1 9",        // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Bf5 5. Ng3 Bg6 6. Nf3 Nd7 7. Bd3 Bxd3 8. Qxd3 Ngf6 1/2-1/2
		    GRA_92  = "r1bqk2r/pp1n1pp1/2pbpn1p/6N1/3P4/3B1N2/PPP1QPPP/R1B1K2R w KQkq - 0 9",    // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Nd7 5. Ng5 Ngf6 6. Bd3 e6 7. N1f3 Bd6 8. Qe2 h6 1/2-1/2
		    GRA_93  = "r2qkb1r/pp3ppp/2p1pn2/5b2/2BP4/5N2/PPP1QPPP/R1B1K2R w KQkq - 0 9",        // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Nd7 5. Nf3 Ngf6 6. Nxf6 Nxf6 7. Bc4 Bf5 8. Qe2 e6 1/2-1/2
		    GRA_94  = "r1bq1rk1/pp1n1ppp/2pb1p2/7Q/2BP4/8/PPP1NPPP/R1B1K2R w KQ - 6 9",          // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Nf6 5. Nxf6 exf6 6. Bc4 Bd6 7. Qh5 O-O 8. Ne2 Nd7 1/2-1/2
		    GRA_95  = "r1bqkb1r/pp3pp1/1np1pn1p/6N1/3P4/3B4/PPP1QPPP/R1B1K1NR w KQkq - 0 9",     // 1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Nd7 5. Bc4 Ngf6 6. Ng5 e6 7. Qe2 Nb6 8. Bd3 h6 1/2-1/2
		    GRA_96  = "r1bqkb1r/pppp1p1p/2n2np1/8/4P3/P3Q3/1PP2PPP/RNB1KBNR w KQkq - 0 6",       // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qe3 Nf6 5. a3 g6 1/2-1/2
		    GRA_97  = "r1bqr1k1/pppp1ppp/2n2n2/8/4P3/P3Q3/1PPN1PPP/2KR1BNR w - - 3 9",           // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qe3 Nf6 5. Bd2 Bb4 6. a3 Bxd2 7. Nxd2 O-O 8. O-O-O Re8 1/2-1/2
		    GRA_98  = "r2q1rk1/pppbbppp/2np1n2/6B1/Q3P3/2N2N2/PPP2PPP/2KR1B1R w - - 6 9",        // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qa4 Nf6 5. Nf3 d6 6. Bg5 Be7 7. Nc3 O-O 8. O-O-O Bd7 1/2-1/2
		    GRA_99  = "r1bqr1k1/ppppbppp/2n2n2/6B1/Q3P3/2N5/PPP2PPP/2KR1BNR w - - 9 8",          // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qa4 Nf6 5. Bg5 Be7 6. Nc3 O-O 7. O-O-O Re8 1/2-1/2
		    GRA_100 = "r1bq1rk1/ppppbppp/2n2n2/6B1/Q3P3/2N2P2/PPP3PP/R3KBNR w KQ - 3 8",         // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qa4 Nf6 5. Nc3 Bc5 6. f3 O-O 7. Bg5 Be7 1/2-1/2
		    GRA_101 = "r1bqk2r/ppppnppp/2n5/2b5/Q3P3/5N2/PPP2PPP/RNB1KB1R w KQkq - 5 6",         // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qa4 Bc5 5. Nf3 Nge7 1/2-1/2
		    GRA_102 = "rnbqk1nr/ppppppbp/6p1/8/3PPP2/8/PPP3PP/RNBQKBNR b KQkq f3 0 3",           // 1. e4 g6 2. d4 Bg7 3. f4 1/2-1/2
		    GRA_103 = "rnbq1rk1/pp3pp1/2p1pb1p/3p4/2PP4/2N1PN2/PP3PPP/2RQKB1R w K - 0 9",        // 1. d4 d5 2. c4 e6 3. Nc3 Nf6 4. Bg5 Be7 5. e3 O-O 6. Nf3 h6 7. Bxf6 Bxf6 8. Rc1 c6 1/2-1/2
		    GRA_104 = "r2qkb1r/pb1n1ppp/2p1pn2/1p6/3P4/2NBPN2/PP3PPP/R1BQK2R w KQkq - 2 9",      // 1. d4 d5 2. c4 e6 3. Nc3 c6 4. Nf3 Nf6 5. e3 Nbd7 6. Bd3 dxc4 7. Bxc4 b5 8. Bd3 Bb7 1/2-1/2
		    GRA_105 = "r1bq1rk1/pp3ppp/2n1pn2/2bp4/2P2B2/2N1PN2/PPQ2PPP/R3KB1R w KQ - 2 9",      // 1. d4 d5 2. c4 e6 3. Nc3 Nf6 4. Nf3 Be7 5. Bf4 O-O 6. e3 c5 7. dxc5 Bxc5 8. Qc2 Nc6 1/2-1/2
		    GRA_106 = "rnbq1rk1/2p1bppp/p3pn2/1p6/2QP4/5NP1/PP2PPBP/RNB2RK1 w - b6 0 9",         // 1. d4 d5 2. c4 e6 3. Nf3 Nf6 4. g3 Be7 5. Bg2 O-O 6. O-O dxc4 7. Qc2 a6 8. Qxc4 b5 1/2-1/2
		    GRA_107 = "rnb1kbnr/pp2pppp/1qp5/3p2B1/3P4/4P3/PPP2PPP/RN1QKBNR w KQkq - 1 4",       // 1. d4 d5 2. Bg5 c6 3. e3 Qb6 1/2-1/2
		    GRA_108 = "r1b1kbnr/pp1nppp1/1qp4p/3p4/3P3B/3BP3/PPP2PPP/RN1QK1NR w KQkq - 3 6",     // 1. d4 d5 2. Bg5 h6 3. Bh4 c6 4. e3 Qb6 5. Bd3 Nd7 1/2-1/2
		    GRA_109 = "r1bq1rk1/pppn1ppp/3b1p2/8/2BP4/2N1P3/PP3PPP/R2QK1NR w KQ - 3 8",          // 1. d4 d5 2. Bg5 Nf6 3. Bxf6 exf6 4. e3 Bd6 5. c4 dxc4 6. Bxc4 O-O 7. Nc3 Nd7 1/2-1/2
		    GRA_110 = "r1b1k2r/p1qnbppp/1p2pn2/2ppN3/3P1P2/2PBP3/PP1N2PP/R1BQK2R w KQkq - 2 9",  // 1. d4 d5 2. e3 Nf6 3. Nd2 c5 4. c3 e6 5. Bd3 Nbd7 6. f4 Qc7 7. Ngf3 b6 8. Ne5 Be7 1/2-1/2
		    GRA_111 = "r1bq1rk1/pp1n1ppp/2pb1n2/3p4/3P4/2NBPN2/PP3PPP/R1BQ1RK1 w - - 6 9",       // 1. d4 d5 2. e3 Nf6 3. c4 c6 4. Nc3 e6 5. cxd5 exd5 6. Bd3 Bd6 7. Nf3 Nbd7 8. O-O O-O 1/2-1/2
		    GRA_112 = "r1bq1rk1/ppp1bppp/1nn5/4p3/8/2NP1NP1/PP2PPBP/R1BQ1RK1 w - - 1 9",         // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. g3 d5 5. cxd5 Nxd5 6. Bg2 Nb6 7. O-O Be7 8. d3 O-O 1/2-1/2
		    GRA_113 = "r1bq1rk1/ppp1bppp/1nn5/4p3/8/P1N2NP1/1P1PPPBP/R1BQ1RK1 w - - 1 9",        // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. g3 d5 5. cxd5 Nxd5 6. Bg2 Nb6 7. O-O Be7 8. a3 O-O 1/2-1/2
		    GRA_114 = "rnbqr1k1/ppp2ppp/3p1n2/8/2PNp3/2P3P1/P2PPPBP/R1BQ1RK1 w - - 0 9",         // 1. c4 e5 2. Nc3 Nf6 3. g3 Bb4 4. Bg2 O-O 5. Nf3 Re8 6. O-O e4 7. Nd4 Bxc3 8. bxc3 d6 1/2-1/2
		    GRA_115 = "r1b1k2r/ppp1qppp/2n2n2/3p4/2Pp4/P1Q1PN2/1P3PPP/R1B1KB1R w KQkq - 0 9",    // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. e3 Bb4 5. Qc2 Bxc3 6. Qxc3 Qe7 7. a3 d5 8. d4 exd4 1/2-1/2
		    GRA_116 = "r1bqk2r/pppp1pp1/5n1p/4n3/2PN3B/2P5/P3PPPP/R2QKB1R w KQkq - 1 9",         // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. d4 exd4 5. Nxd4 Bb4 6. Bg5 h6 7. Bh4 Bxc3 8. bxc3 Ne5 1/2-1/2
		    GRA_117 = "r2qk2r/ppp1bppp/1nn1b3/4p3/8/P1N2NP1/1P1PPPBP/R1BQ1RK1 w kq - 1 9",       // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. g3 d5 5. cxd5 Nxd5 6. Bg2 Nb6 7. O-O Be7 8. a3 Be6 1/2-1/2
		    GRA_118 = "r1bq1rk1/ppp1n1bp/2np2p1/4pp2/2P1P3/2NP2P1/PP2NPBP/R1BQ1RK1 w - f6 0 9",  // 1. c4 e5 2. Nc3 Nc6 3. g3 g6 4. Bg2 Bg7 5. e4 Nge7 6. Nge2 O-O 7. d3 d6 8. O-O f5 1/2-1/2
		    GRA_119 = "r1bq1rk1/1pp2pbp/2np1np1/p3p3/2P5/2NP1NP1/PP2PPBP/1RBQ1RK1 w - a6 0 9",   // 1. c4 Nf6 2. Nc3 g6 3. g3 Bg7 4. Bg2 O-O 5. Nf3 d6 6. O-O e5 7. d3 Nc6 8. Rb1 a5 1/2-1/2
		    GRA_120 = "rn1q1rk1/pb1pbppp/1p2pn2/2p5/2P5/1PN2NP1/PB1PPPBP/R2QK2R w KQ - 3 8",     // 1. c4 Nf6 2. Nc3 c5 3. Nf3 e6 4. g3 b6 5. Bg2 Bb7 6. b3 Be7 7. Bb2 O-O 1/2-1/2
		    GRA_121 = "rn1q1rk1/pbp2ppp/1p2pn2/3p4/2PP4/P1Q2NP1/1P2PP1P/R1B1KB1R w KQ d6 0 9",   // 1. c4 Nf6 2. Nc3 e6 3. d4 Bb4 4. Qc2 O-O 5. a3 Bxc3 6. Qxc3 b6 7. g3 Bb7 8. Nf3 d5 1/2-1/2
		    GRA_122 = "rnbqk2r/pp2bppp/2p2n2/3p4/2PQ4/5NP1/PP2PPBP/RNB1K2R w KQkq - 2 7",        // 1. c4 Nf6 2. g3 c6 3. Bg2 e5 4. d4 exd4 5. Qxd4 d5 6. Nf3 Be7 1/2-1/2
		    GRA_123 = "r1bqkbnr/pp1n1ppp/4p3/2p5/2Q5/3P2P1/PP2PPBP/RNB1K1NR b KQkq - 0 6",       // 1. c4 e6 2. g3 d5 3. Bg2 dxc4 4. Qa4 Nd7 5. Qxc4 c5 6. d3 1/2-1/2
		    GRA_124 = "r1bqk2r/pp2ppbp/2np2pn/2p5/2PP4/2N2NP1/PP2PPBP/R1BQ1RK1 b kq d3 0 7",     // 1. c4 c5 2. g3 g6 3. Bg2 Bg7 4. Nf3 Nc6 5. Nc3 d6 6. O-O Nh6 7. d4 1/2-1/2
		    GRA_125 = "rn1qk2r/pb2bppp/1p1ppn2/8/2PQ4/2N2NP1/PP2PPBP/R1B2RK1 w kq - 0 9",        // 1. c4 c5 2. Nf3 Nf6 3. Nc3 e6 4. g3 b6 5. Bg2 Bb7 6. O-O Be7 7. d4 cxd4 8. Qxd4 d6 1/2-1/2
		    GRA_126 = "r1bq1rk1/pp1nppb1/2pp1npp/6B1/2PP4/2N1PN2/PP2BPPP/R2Q1RK1 w - - 0 9",     // 1. Nf3 Nf6 2. c4 g6 3. Nc3 Bg7 4. d4 O-O 5. Bg5 d6 6. e3 Nbd7 7. Be2 c6 8. O-O h6 1/2-1/2
		    GRA_127 = "rn1qk2r/pppb1pbp/1n4p1/4p3/8/1QN2NP1/PP1PPPBP/R1B1K2R w KQkq - 4 9",      // 1. Nf3 Nf6 2. c4 g6 3. Nc3 d5 4. cxd5 Nxd5 5. g3 Bg7 6. Bg2 e5 7. Qa4+ Bd7 8. Qb3 Nb6 1/2-1/2
		    GRA_128 = "rnbq1rk1/pp2ppbp/5np1/2p5/3P4/N1P2NP1/P3PPBP/R1BQ1RK1 w - c6 0 9",        // 1. Nf3 Nf6 2. g3 g6 3. Bg2 Bg7 4. O-O O-O 5. d4 d5 6. c4 dxc4 7. Na3 c3 8. bxc3 c5 1/2-1/2
		    GRA_129 = "r2qk2r/pp1n1ppp/2p2n2/2b1p3/4P1b1/5NP1/PPPN1PBP/R1BQ1RK1 w kq - 1 9",     // 1. Nf3 Nf6 2. g3 d5 3. Bg2 c6 4. O-O Bg4 5. d3 Nbd7 6. Nbd2 e5 7. e4 dxe4 8. dxe4 Bc5 1/2-1/2
		    GRA_130 = "r2qk2r/pbp1bppp/1p2pn2/3pn1B1/3P4/3BP3/PPPN1PPP/R2Q1RK1 w kq - 0 9",      // 1. Nf3 Nf6 2. d4 d5 3. Bg5 e6 4. Nbd2 Be7 5. e3 Nbd7 6. Bd3 b6 7. O-O Bb7 8. Ne5 Nxe5 1/2-1/2
		    GRA_131 = "rnbqk2r/pp3pbp/5pp1/3p4/3P4/2N2N2/PPP2PPP/R2QKB1R w KQkq - 0 8",          // 1. Nf3 Nf6 2. d4 d5 3. Bg5 g6 4. Nc3 Bg7 5. Bxf6 exf6 6. e4 c6 7. exd5 cxd5 1/2-1/2
		    GRA_132 = "r3kb1r/1pp1qppp/p1bp1n2/4p3/3P4/1P2P3/PBP1NPPP/RN1Q1RK1 w kq - 1 9",      // 1. b3 e5 2. Bb2 Nc6 3. e3 Nf6 4. Bb5 d6 5. Ne2 Bd7 6. O-O a6 7. Bxc6 Bxc6 8. d4 Qe7 1/2-1/2
		    GRA_133 = "r1bqkb1r/2p2ppp/p1pp1n2/8/4p3/1P2PN2/PBPP1PPP/RN1Q1RK1 w kq - 0 8",       // 1. b3 e5 2. Bb2 Nc6 3. e3 Nf6 4. Bb5 d6 5. Nf3 a6 6. Bxc6 bxc6 7. O-O e4 1/2-1/2
		    GRA_134 = "r1b1k1nr/p1p1qppp/2pb4/3pp3/8/1P2PN2/PBPP1PPP/RN1QK2R w KQkq - 2 7",      // 1. b3 e5 2. Bb2 Nc6 3. e3 d5 4. Bb5 Bd6 5. Bxc6 bxc6 6. Nf3 Qe7 1/2-1/2
		    GRA_135 = "r1bqkbnr/ppp3pp/2n2p2/1B1pp3/8/1P2P3/PBPPNPPP/RN1QK2R b KQkq - 1 5",      // 1. b3 e5 2. Bb2 Nc6 3. e3 d5 4. Bb5 f6 5. Ne2 1/2-1/2
		    GRA_136 = "r1bqk1nr/pppp1pbp/2n3p1/4p3/5P2/1P2PN2/PBPP2PP/RN1QKB1R b KQkq - 2 5",    // 1. b3 e5 2. Bb2 Nc6 3. e3 g6 4. f4 Bg7 5. Nf3 1/2-1/2
		    GRA_137 = "rnbq1rk1/pp4bp/2pp1np1/3Ppp2/2P5/2N2NP1/PP2PPBP/R1BQ1RK1 w - e6 0 9",     // 1. d4 f5 2. g3 Nf6 3. Bg2 g6 4. Nf3 Bg7 5. O-O O-O 6. c4 d6 7. Nc3 c6 8. d5 e5 1/2-1/2
		    GRA_138 = "rnbq1rk1/pp2b1pp/2p1pn2/3p1p2/2PP4/2N2NP1/PP2PPBP/R1BQ1RK1 w - - 0 8",    // 1. d4 f5 2. c4 Nf6 3. g3 e6 4. Bg2 Be7 5. Nf3 O-O 6. O-O d5 7. Nc3 c6 1/2-1/2
		    GRA_139 = "2kr1b1r/pppq2pp/2n1bp2/3p1p2/3P4/P1NBP3/1PP1NPPP/R2QK2R w KQ - 1 9",      // 1. d4 f5 2. Nc3 Nf6 3. Bg5 d5 4. Bxf6 exf6 5. e3 Be6 6. Bd3 Nc6 7. Nge2 Qd7 8. a3 O-O-O 1/2-1/2
		    GRA_140 = "r1bq1rk1/pp2p1bp/n1pp1np1/5p2/2PP4/1PN2NP1/P3PPBP/R1BQ1RK1 w - - 2 9",    // 1. d4 f5 2. g3 Nf6 3. Bg2 c6 4. Nf3 g6 5. O-O Bg7 6. b3 O-O 7. c4 d6 8. Nc3 Na6 1/2-1/2
		    GRA_141 = "rnbqkb1r/ppp1p1pp/3p1n2/8/3PP1p1/2N4P/PPP2P2/R1BQKBNR b KQkq - 2 5",      // 1. d4 f5 2. g4 fxg4 3. e4 d6 4. h3 Nf6 5. Nc3 1/2-1/2
		    GRA_142 = "rnbqkb1r/ppppp1pp/5n2/8/3P1Bp1/2N5/PPP1PP1P/R2QKBNR b KQkq - 3 4",        // 1. d4 f5 2. g4 fxg4 3. Bf4 Nf6 4. Nc3 1/2-1/2
		    GRA_143 = "r2qr1k1/ppp2ppp/2n2n2/3p4/1b4b1/4PN2/PBPPBPPP/RN1Q1RK1 w - - 4 9",        // 1. b4 e5 2. Bb2 Bxb4 3. Bxe5 Nf6 4. e3 Nc6 5. Bb2 O-O 6. Nf3 d5 7. Be2 Re8 8. O-O Bg4 1/2-1/2
		    GRA_144 = "rnbqk1nr/pppp2pp/5p2/4B3/1b6/8/P1PPPPPP/RN1QKBNR w KQkq - 0 4",           // 1. b4 e5 2. Bb2 Bxb4 3. Bxe5 f6 1/2-1/2
		    GRA_145 = "rnb1k1nr/ppp1qppp/3b4/3pp3/1P6/P4N2/1BPPPPPP/RN1QKB1R w KQkq - 4 5",      // 1. b4 e5 2. a3 d5 3. Bb2 Bd6 4. Nf3 Qe7 1/2-1/2
		    GRA_146 = "rnbqk2r/pp2ppbp/2p2np1/3p4/5P2/3P1NP1/PPP1P1BP/RNBQK2R w KQkq - 2 6",     // 1. g3 d5 2. Bg2 Nf6 3. d3 c6 4. f4 g6 5. Nf3 Bg7 1/2-1/2
		    GRA_147 = "r2qkb1r/pp1npppp/2p2n2/3p4/6b1/3P1NP1/PPP1PPBP/RNBQ1RK1 w kq - 1 6";      // 1. g3 d5 2. Bg2 Nf6 3. Nf3 c6 4. O-O Bg4 5. d3 Nbd7 1/2-1/2

	/**
	 * Graham 2014 - List - 1/2-1/2
	 */
    public static String[] GRA_LIST = {
            GRA_1   , // "r1b2rk1/ppq1ppbp/2n2np1/2ppN3/5P2/1P2P3/PBPPB1PP/RN1Q1RK1 w - - 4 9",     // 1. f4 d5 2. Nf3 Nf6 3. e3 g6 4. b3 Bg7 5. Bb2 O-O 6. Be2 c5 7. O-O Nc6 8. Ne5 Qc7 1/2-1/2
            GRA_3   , // "r3kb1r/pp2pppp/1qb2n2/2pp4/5P2/1P2PN2/PBPP2PP/RN1Q1RK1 b kq - 1 8",       // 1. f4 d5 2. Nf3 c5 3. e3 Nc6 4. Bb5 Bd7 5. b3 Nf6 6. Bb2 Qb6 7. Bxc6 Bxc6 8. O-O 1/2-1/2
            GRA_5   , // "r2qkb1r/pp1npppp/2p2n2/3p4/5P2/5BP1/PPPPP2P/RNBQ1RK1 w kq - 2 7",         // 1. f4 d5 2. Nf3 Nf6 3. g3 Bg4 4. Bg2 Bxf3 5. Bxf3 c6 6. O-O Nbd7 1/2-1/2
            GRA_8   , // "rnbqkb1r/ppp1pppp/5n2/8/5P2/1P1BP3/P1P3PP/RNBQK1NR b KQkq - 0 5",         // 1. f4 d5 2. b3 Nf6 3. e3 d4 4. Bd3 dxe3 5. dxe3 1/2-1/2
            GRA_10  , // "r1bq1rk1/pp2ppbp/2n2np1/2p5/3p1P2/N2P1NP1/PPP1P1BP/R1B1QRK1 w - - 2 9",   // 1. f4 g6 2. Nf3 Bg7 3. g3 Nf6 4. Bg2 O-O 5. O-O c5 6. d3 d5 7. Qe1 d4 8. Na3 Nc6 1/2-1/2
            GRA_11  , // "rnbq1rk1/1p2bppp/p2p1n2/4p3/4P3/1NN1B3/PPP1BPPP/R2QK2R w KQ - 4 9",       // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nb3 Be7 8. Be3 O-O 1/2-1/2
            GRA_14  , // "r3kb1r/pp1q1ppp/2nppn2/8/3pP3/2P2N2/PP3PPP/RNBQR1K1 w kq - 0 9",          // 1. e4 c5 2. Nf3 d6 3. Bb5 Bd7 4. Bxd7 Qxd7 5. O-O Nc6 6. c3 Nf6 7. Re1 e6 8. d4 cxd4 1/2-1/2
            GRA_17  , // "r1b1kb1r/pp2pppp/3p1n2/1N2P3/3n4/5N2/PPP2PPP/R1B1K2R w KQkq - 0 9",       // 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Qxd4 Nf6 5. e5 Nc6 6. Bb5 Qa5 7. Nc3 Qxb5 8. Nxb5 Nxd4 1/2-1/2
            GRA_29  , // "r2qkb1r/1p1b1ppp/p1nppn2/6B1/3NP3/2N5/PPPQ1PPP/2KR1B1R w kq - 2 9",       // 1. e4 c5 2. Nf3 Nc6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Bg5 e6 7. Qd2 a6 8. O-O-O Bd7 1/2-1/2
            GRA_33  , // "r1b1kb1r/1p3ppp/p1n1p3/2pq4/8/2P2N2/PP1P1PPP/RNBQR1K1 w kq - 0 9",        // 1. e4 c5 2. Nf3 Nc6 3. Bb5 e6 4. O-O Nge7 5. c3 d5 6. exd5 Qxd5 7. Re1 a6 8. Bxc6 Nxc6 1/2-1/2
            GRA_34  , // "r1bq1rk1/pp2bppp/2nppn2/8/3NP3/2N1B3/PPP1BPPP/R2Q1RK1 w - - 6 9",         // 1. e4 c5 2. Nf3 e6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 d6 6. Be2 Be7 7. O-O O-O 8. Be3 Nc6 1/2-1/2
            GRA_38  , // "1rbqk1nr/pp3pbp/3pp1p1/2p5/3nP3/2NPB1P1/PPPQNPBP/R3K2R w KQk - 4 9",      // 1. e4 c5 2. Nc3 Nc6 3. g3 g6 4. Bg2 Bg7 5. d3 d6 6. Be3 e6 7. Qd2 Rb8 8. Nge2 Nd4 1/2-1/2
            GRA_40  , // "r1bqk2r/pp2npbp/2n1p1p1/2p5/2B1P3/2N2N2/PPPP2PP/R1BQK2R w KQkq - 0 8",    // 1. e4 c5 2. Nc3 Nc6 3. f4 g6 4. Nf3 Bg7 5. Bc4 e6 6. f5 Nge7 7. fxe6 dxe6 1/2-1/2
            GRA_42  , // "r1bq1rk1/pp2ppbp/2np1np1/8/3NP3/1BN1B3/PPP2PPP/R2QK2R w KQ - 0 9",        // 1. e4 c5 2. Nc3 Nc6 3. Nf3 g6 4. d4 cxd4 5. Nxd4 Bg7 6. Be3 Nf6 7. Bc4 O-O 8. Bb3 d6 1/2-1/2
            GRA_43  , // "r3kb1r/pp3ppp/2n1pn2/2pq3b/3P4/2P2N1P/PP2BPP1/RNBQ1RK1 w kq - 3 9",       // 1. e4 c5 2. c3 d5 3. exd5 Qxd5 4. d4 Nf6 5. Nf3 Bg4 6. Be2 e6 7. h3 Bh5 8. O-O Nc6 1/2-1/2
            GRA_45  , // "r1bqkb1r/1p1pn1pp/p1n1pp2/6B1/2B1P3/2N2N2/PP3PPP/R2Q1RK1 w kq - 0 9",     // 1. e4 c5 2. d4 cxd4 3. c3 dxc3 4. Nxc3 Nc6 5. Nf3 e6 6. Bc4 a6 7. O-O Nge7 8. Bg5 f6 1/2-1/2
            GRA_46  , // "r1bqkb1r/1p3pp1/p1n1p3/2ppPn1p/5P2/2N2NP1/PPPP2BP/R1BQ1RK1 w kq h6 0 9",  // 1. e4 c5 2. f4 e6 3. Nf3 Nc6 4. Nc3 a6 5. g3 d5 6. e5 Nge7 7. Bg2 Nf5 8. O-O h5 1/2-1/2
            GRA_50  , // "r1bq1rk1/p3ppbp/2pp1np1/2p5/4PP2/2NP1N2/PPP3PP/R1BQ1RK1 w - - 4 9",       // 1. e4 c5 2. f4 Nc6 3. Nf3 g6 4. Bb5 Bg7 5. Bxc6 bxc6 6. d3 d6 7. O-O Nf6 8. Nc3 O-O 1/2-1/2
            GRA_51  , // "r1bqkb1r/ppp2ppp/2np1n2/3Pp3/4P3/2P2N2/PP3PPP/RNBQKB1R b KQkq - 0 5",     // 1. e4 e5 2. Nf3 Nc6 3. c3 Nf6 4. d4 d6 5. d5 1/2-1/2
            GRA_68  , // "rnb1k2r/ppp1bppp/3p1n2/6B1/8/3P1N2/PPP1BPPP/RN2K2R w KQkq - 1 9",         // 1. e4 e5 2. Nf3 Nf6 3. Nxe5 d6 4. Nf3 Nxe4 5. Qe2 Qe7 6. d3 Nf6 7. Bg5 Qxe2 8. Bxe2 Be7 1/2-1/2
            GRA_69  , // "rn2kb1r/ppp2ppp/1q2pn2/5b2/1P6/2N2N1P/P1PP1PP1/1RBQKB1R w Kkq - 0 8",     // 1. e4 d5 2. exd5 Qxd5 3. Nc3 Qa5 4. Nf3 Nf6 5. h3 Bf5 6. b4 Qb6 7. Rb1 e6 1/2-1/2
            GRA_74  , // "r3kb1r/ppp2ppp/1nn1b3/4P3/2P2P2/8/PP4PP/R1BNKBNR w KQkq - 1 9",           // 1. e4 d5 2. exd5 Nf6 3. d4 Nxd5 4. c4 Nb6 5. Nc3 e5 6. dxe5 Qxd1 7. Nxd1 Nc6 8. f4 Be6 1/2-1/2
            GRA_76  , // "rnbqk2r/ppp2pbp/1n2p1p1/4p1N1/3P1P2/1B6/PPP3PP/RNBQK2R w KQkq - 0 9",     // 1. e4 Nf6 2. e5 Nd5 3. d4 d6 4. Nf3 g6 5. Bc4 Nb6 6. Bb3 Bg7 7. Ng5 e6 8. f4 dxe5 1/2-1/2
            GRA_78  , // "rnbq1rk1/pp2n1pp/4p3/2ppPp2/3P2Q1/P1PB4/2P2PPP/R1B1K1NR w KQ f6 0 9",     // 1. e4 e6 2. d4 d5 3. Nc3 Bb4 4. e5 c5 5. a3 Bxc3 6. bxc3 Ne7 7. Qg4 O-O 8. Bd3 f5 1/2-1/2
            GRA_85  , // "r1bq1rk1/ppp1ppbp/2n3p1/4P2n/3P4/2NB1N2/PPP3PP/R1BQK2R w KQ - 1 9",       // 1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. f4 Bg7 5. Nf3 O-O 6. Bd3 Nc6 7. e5 dxe5 8. fxe5 Nh5 1/2-1/2
            GRA_89  , // "r2qk1nr/1b1nppbp/p2p2p1/1pp5/3PP1P1/2N1BP2/PPPQN2P/R3KB1R w KQkq c6 0 9", // 1. e4 d6 2. d4 g6 3. Nc3 Bg7 4. Be3 a6 5. Qd2 Nd7 6. f3 b5 7. g4 Bb7 8. Nge2 c5 1/2-1/2
            GRA_96  , // "r1bqkb1r/pppp1p1p/2n2np1/8/4P3/P3Q3/1PP2PPP/RNB1KBNR w KQkq - 0 6",       // 1. e4 e5 2. d4 exd4 3. Qxd4 Nc6 4. Qe3 Nf6 5. a3 g6 1/2-1/2
            GRA_102 , // "rnbqk1nr/ppppppbp/6p1/8/3PPP2/8/PPP3PP/RNBQKBNR b KQkq f3 0 3",           // 1. e4 g6 2. d4 Bg7 3. f4 1/2-1/2
            GRA_103 , // "rnbq1rk1/pp3pp1/2p1pb1p/3p4/2PP4/2N1PN2/PP3PPP/2RQKB1R w K - 0 9",        // 1. d4 d5 2. c4 e6 3. Nc3 Nf6 4. Bg5 Be7 5. e3 O-O 6. Nf3 h6 7. Bxf6 Bxf6 8. Rc1 c6 1/2-1/2
            GRA_106 , // "rnbq1rk1/2p1bppp/p3pn2/1p6/2QP4/5NP1/PP2PPBP/RNB2RK1 w - b6 0 9",         // 1. d4 d5 2. c4 e6 3. Nf3 Nf6 4. g3 Be7 5. Bg2 O-O 6. O-O dxc4 7. Qc2 a6 8. Qxc4 b5 1/2-1/2
            GRA_107 , // "rnb1kbnr/pp2pppp/1qp5/3p2B1/3P4/4P3/PPP2PPP/RN1QKBNR w KQkq - 1 4",       // 1. d4 d5 2. Bg5 c6 3. e3 Qb6 1/2-1/2
            GRA_108 , // "r1b1kbnr/pp1nppp1/1qp4p/3p4/3P3B/3BP3/PPP2PPP/RN1QK1NR w KQkq - 3 6",     // 1. d4 d5 2. Bg5 h6 3. Bh4 c6 4. e3 Qb6 5. Bd3 Nd7 1/2-1/2
            GRA_109 , // "r1bq1rk1/pppn1ppp/3b1p2/8/2BP4/2N1P3/PP3PPP/R2QK1NR w KQ - 3 8",          // 1. d4 d5 2. Bg5 Nf6 3. Bxf6 exf6 4. e3 Bd6 5. c4 dxc4 6. Bxc4 O-O 7. Nc3 Nd7 1/2-1/2
            GRA_110 , // "r1b1k2r/p1qnbppp/1p2pn2/2ppN3/3P1P2/2PBP3/PP1N2PP/R1BQK2R w KQkq - 2 9",  // 1. d4 d5 2. e3 Nf6 3. Nd2 c5 4. c3 e6 5. Bd3 Nbd7 6. f4 Qc7 7. Ngf3 b6 8. Ne5 Be7 1/2-1/2
            GRA_111 , // "r1bq1rk1/pp1n1ppp/2pb1n2/3p4/3P4/2NBPN2/PP3PPP/R1BQ1RK1 w - - 6 9",       // 1. d4 d5 2. e3 Nf6 3. c4 c6 4. Nc3 e6 5. cxd5 exd5 6. Bd3 Bd6 7. Nf3 Nbd7 8. O-O O-O 1/2-1/2
            GRA_112 , // "r1bq1rk1/ppp1bppp/1nn5/4p3/8/2NP1NP1/PP2PPBP/R1BQ1RK1 w - - 1 9",         // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. g3 d5 5. cxd5 Nxd5 6. Bg2 Nb6 7. O-O Be7 8. d3 O-O 1/2-1/2
            GRA_114 , // "rnbqr1k1/ppp2ppp/3p1n2/8/2PNp3/2P3P1/P2PPPBP/R1BQ1RK1 w - - 0 9",         // 1. c4 e5 2. Nc3 Nf6 3. g3 Bb4 4. Bg2 O-O 5. Nf3 Re8 6. O-O e4 7. Nd4 Bxc3 8. bxc3 d6 1/2-1/2
            GRA_115 , // "r1b1k2r/ppp1qppp/2n2n2/3p4/2Pp4/P1Q1PN2/1P3PPP/R1B1KB1R w KQkq - 0 9",    // 1. c4 e5 2. Nc3 Nf6 3. Nf3 Nc6 4. e3 Bb4 5. Qc2 Bxc3 6. Qxc3 Qe7 7. a3 d5 8. d4 exd4 1/2-1/2
            GRA_118 , // "r1bq1rk1/ppp1n1bp/2np2p1/4pp2/2P1P3/2NP2P1/PP2NPBP/R1BQ1RK1 w - f6 0 9",  // 1. c4 e5 2. Nc3 Nc6 3. g3 g6 4. Bg2 Bg7 5. e4 Nge7 6. Nge2 O-O 7. d3 d6 8. O-O f5 1/2-1/2
            GRA_119 , // "r1bq1rk1/1pp2pbp/2np1np1/p3p3/2P5/2NP1NP1/PP2PPBP/1RBQ1RK1 w - a6 0 9",   // 1. c4 Nf6 2. Nc3 g6 3. g3 Bg7 4. Bg2 O-O 5. Nf3 d6 6. O-O e5 7. d3 Nc6 8. Rb1 a5 1/2-1/2
            GRA_120 , // "rn1q1rk1/pb1pbppp/1p2pn2/2p5/2P5/1PN2NP1/PB1PPPBP/R2QK2R w KQ - 3 8",     // 1. c4 Nf6 2. Nc3 c5 3. Nf3 e6 4. g3 b6 5. Bg2 Bb7 6. b3 Be7 7. Bb2 O-O 1/2-1/2
            GRA_121 , // "rn1q1rk1/pbp2ppp/1p2pn2/3p4/2PP4/P1Q2NP1/1P2PP1P/R1B1KB1R w KQ d6 0 9",   // 1. c4 Nf6 2. Nc3 e6 3. d4 Bb4 4. Qc2 O-O 5. a3 Bxc3 6. Qxc3 b6 7. g3 Bb7 8. Nf3 d5 1/2-1/2
            GRA_122 , // "rnbqk2r/pp2bppp/2p2n2/3p4/2PQ4/5NP1/PP2PPBP/RNB1K2R w KQkq - 2 7",        // 1. c4 Nf6 2. g3 c6 3. Bg2 e5 4. d4 exd4 5. Qxd4 d5 6. Nf3 Be7 1/2-1/2
            GRA_123 , // "r1bqkbnr/pp1n1ppp/4p3/2p5/2Q5/3P2P1/PP2PPBP/RNB1K1NR b KQkq - 0 6",       // 1. c4 e6 2. g3 d5 3. Bg2 dxc4 4. Qa4 Nd7 5. Qxc4 c5 6. d3 1/2-1/2
            GRA_125 , // "rn1qk2r/pb2bppp/1p1ppn2/8/2PQ4/2N2NP1/PP2PPBP/R1B2RK1 w kq - 0 9",        // 1. c4 c5 2. Nf3 Nf6 3. Nc3 e6 4. g3 b6 5. Bg2 Bb7 6. O-O Be7 7. d4 cxd4 8. Qxd4 d6 1/2-1/2
            GRA_126 , // "r1bq1rk1/pp1nppb1/2pp1npp/6B1/2PP4/2N1PN2/PP2BPPP/R2Q1RK1 w - - 0 9",     // 1. Nf3 Nf6 2. c4 g6 3. Nc3 Bg7 4. d4 O-O 5. Bg5 d6 6. e3 Nbd7 7. Be2 c6 8. O-O h6 1/2-1/2
            GRA_128 , // "rnbq1rk1/pp2ppbp/5np1/2p5/3P4/N1P2NP1/P3PPBP/R1BQ1RK1 w - c6 0 9",        // 1. Nf3 Nf6 2. g3 g6 3. Bg2 Bg7 4. O-O O-O 5. d4 d5 6. c4 dxc4 7. Na3 c3 8. bxc3 c5 1/2-1/2
            GRA_130 , // "r2qk2r/pbp1bppp/1p2pn2/3pn1B1/3P4/3BP3/PPPN1PPP/R2Q1RK1 w kq - 0 9",      // 1. Nf3 Nf6 2. d4 d5 3. Bg5 e6 4. Nbd2 Be7 5. e3 Nbd7 6. Bd3 b6 7. O-O Bb7 8. Ne5 Nxe5 1/2-1/2
            GRA_132 , // "r3kb1r/1pp1qppp/p1bp1n2/4p3/3P4/1P2P3/PBP1NPPP/RN1Q1RK1 w kq - 1 9",      // 1. b3 e5 2. Bb2 Nc6 3. e3 Nf6 4. Bb5 d6 5. Ne2 Bd7 6. O-O a6 7. Bxc6 Bxc6 8. d4 Qe7 1/2-1/2
            GRA_137 , // "rnbq1rk1/pp4bp/2pp1np1/3Ppp2/2P5/2N2NP1/PP2PPBP/R1BQ1RK1 w - e6 0 9",     // 1. d4 f5 2. g3 Nf6 3. Bg2 g6 4. Nf3 Bg7 5. O-O O-O 6. c4 d6 7. Nc3 c6 8. d5 e5 1/2-1/2
            GRA_138 , // "rnbq1rk1/pp2b1pp/2p1pn2/3p1p2/2PP4/2N2NP1/PP2PPBP/R1BQ1RK1 w - - 0 8",    // 1. d4 f5 2. c4 Nf6 3. g3 e6 4. Bg2 Be7 5. Nf3 O-O 6. O-O d5 7. Nc3 c6 1/2-1/2
            GRA_139 , // "2kr1b1r/pppq2pp/2n1bp2/3p1p2/3P4/P1NBP3/1PP1NPPP/R2QK2R w KQ - 1 9",      // 1. d4 f5 2. Nc3 Nf6 3. Bg5 d5 4. Bxf6 exf6 5. e3 Be6 6. Bd3 Nc6 7. Nge2 Qd7 8. a3 O-O-O 1/2-1/2
            GRA_140 , // "r1bq1rk1/pp2p1bp/n1pp1np1/5p2/2PP4/1PN2NP1/P3PPBP/R1BQ1RK1 w - - 2 9",    // 1. d4 f5 2. g3 Nf6 3. Bg2 c6 4. Nf3 g6 5. O-O Bg7 6. b3 O-O 7. c4 d6 8. Nc3 Na6 1/2-1/2
            GRA_141 , // "rnbqkb1r/ppp1p1pp/3p1n2/8/3PP1p1/2N4P/PPP2P2/R1BQKBNR b KQkq - 2 5",      // 1. d4 f5 2. g4 fxg4 3. e4 d6 4. h3 Nf6 5. Nc3 1/2-1/2
            GRA_142 , // "rnbqkb1r/ppppp1pp/5n2/8/3P1Bp1/2N5/PPP1PP1P/R2QKBNR b KQkq - 3 4",        // 1. d4 f5 2. g4 fxg4 3. Bf4 Nf6 4. Nc3 1/2-1/2
            GRA_143 , // "r2qr1k1/ppp2ppp/2n2n2/3p4/1b4b1/4PN2/PBPPBPPP/RN1Q1RK1 w - - 4 9",        // 1. b4 e5 2. Bb2 Bxb4 3. Bxe5 Nf6 4. e3 Nc6 5. Bb2 O-O 6. Nf3 d5 7. Be2 Re8 8. O-O Bg4 1/2-1/2
            GRA_145 , // "rnb1k1nr/ppp1qppp/3b4/3pp3/1P6/P4N2/1BPPPPPP/RN1QKB1R w KQkq - 4 5",      // 1. b4 e5 2. a3 d5 3. Bb2 Bd6 4. Nf3 Qe7 1/2-1/2
            GRA_146 , // "rnbqk2r/pp2ppbp/2p2np1/3p4/5P2/3P1NP1/PPP1P1BP/RNBQK2R w KQkq - 2 6",     // 1. g3 d5 2. Bg2 Nf6 3. d3 c6 4. f4 g6 5. Nf3 Bg7 1/2-1/2
            GRA_147   // "r2qkb1r/pp1npppp/2p2n2/3p4/6b1/3P1NP1/PPP1PPBP/RNBQ1RK1 w kq - 1 6";      // 1. g3 d5 2. Bg2 Nf6 3. Nf3 c6 4. O-O Bg4 5. d3 Nbd7 1/2-1/2
    };

    /**
     * clear button on NewGameDialog
     */
    public static final String
            NEW_GAME_CLEAR = "4k3/8/8/8/8/8/8/4K3 w - - 0 1";

}
