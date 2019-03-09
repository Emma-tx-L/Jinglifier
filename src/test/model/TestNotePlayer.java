package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotePlayerTest {
    private NotePlayer player;

    @BeforeEach
    void runBefore() {
        player = new NotePlayer();
    }

    @Test
    void testConstructor() {
        player = new NotePlayer();
        assertEquals(10, player.getInstrument());
        assertEquals(60, player.getBaseNote());
        assertEquals(500, player.getTempo());
    }

    @Test
    void testPlayAnyNote() {
        player.playString("assertEquals(10, player.getInstrument())");
    }

    @Test
    void testChangeBaseNote() {
        player.setBaseNote(61);
        player.playString("assertEquals(10, player.getInstrument())");
    }
}
