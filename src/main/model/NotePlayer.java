package model;

import sound.MidiSynth;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NotePlayer {
    private final static Random random = new Random();

    private MidiSynth midiSynth;
    private int instrument;
    //velocity between 0-127, but very quiet if less than 60
    private int velocity = 100;
    //from 0-127, 60 is middle C
    private int baseNote;
    private int tempo;
    private int noteLength;


    //EFFECTS:  constructs NotePlayer with default instrument, middle C as base note, tempo of 0.5s/note,
    //          note length of 10s
    public void NotePlayer() {
        instrument = 1;
        baseNote = 60;
        tempo = 500;
        noteLength = 1000;
        this.midiSynth = new MidiSynth();
    }

    //EFFECTS:  constructs NotePlayer with given instrument, base note, tempo at given int/note, note length in ms
    public void NotePlayer(int instrument, int baseNote, int tempo, int noteLength) {
        this.instrument = instrument;
        this.baseNote = baseNote;
        this.tempo = tempo;
        this.noteLength = noteLength;
        this.midiSynth = new MidiSynth();
    }

    public void playString(String input) {
        String[] notes = input.split("");

        for (String note : notes) {
            if (isPause(note)) {
                pause(tempo);
            } else if (isVowel(note)) {
                playKeyNote();
                pause(tempo);
            } else {
                playAnyNote();
                pause(tempo);
            }
        }
    }

    //EFFECTS: randomly plays the 1, 3, 5 chord notes based on the baseNote within 4 octaves
    private void playKeyNote() {
        int[] octaves = {0, -7, 7, 14, -14};
        int[] keyNotes = {baseNote, baseNote + 2, baseNote + 4};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];

        midiSynth.play(instrument, note, velocity);
        pause(noteLength);
        midiSynth.stop(instrument, note);
    }

    private void playAnyNote() {
        int[] octaves = {0, -7, 7, 14, -14};
        int keyNote = ThreadLocalRandom.current().nextInt(baseNote - 7, baseNote + 7);
        int note = keyNote + octaves[random.nextInt(octaves.length)];

        midiSynth.play(instrument, note, velocity);
        pause(noteLength);
        midiSynth.stop(instrument, note);
    }


    //EFFECTS: returns true if input signals a pause
    private Boolean isPause(String input) {
        return (input.equals(" ") || input.equals(",") || input.equals(".") || input.equals(":") || input.equals(";"));
    }

    //EFFECTS: returns true if input is a vowel, y included
    private Boolean isVowel(String input) {
        return (input.equals("a") || input.equals("e") || input.equals("i") || input.equals("o") || input.equals("u")
                || input.equals("y"));
    }

    //EFFECTS: pauses for tempo milliseconds
    private void pause(int time) {
        try {
            Thread.sleep(time); //milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //setters
    public void setBaseNote(int base) {
        baseNote = base;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
