package model;

import sound.MidiSynth;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NotePlayer {
    private final static Random random = new Random();

    private MidiSynth midiSynth = new MidiSynth();
    private int instrument = 10;
    //velocity between 0-127, but very quiet if less than 60
    private int velocity = 100;
    //from 0-127, 60 is middle C
    private int baseNote = 60;
    private int tempo = 300;

    //EFFECTS:  constructs NotePlayer with given instrument, base note, tempo at given int/note, note length in ms
    public void NotePlayer(int instrument, int baseNote, int tempo) {
        this.instrument = instrument;
        this.baseNote = baseNote;
        this.tempo = tempo;
        velocity = 100;
        this.midiSynth = new MidiSynth();
        midiSynth.open();
    }

    public void playString(String input) {
        midiSynth.open();
        String[] notes = input.split("");

        for (String note : notes) {
            if (isPause(note)) {
                pause(tempo);
            } else if (isVowel(note)) {
                playKeyNote();
            } else {
                playKeyNote();
                /*playAnyNote();*/
            }
        }
    }

    //EFFECTS: randomly plays the 1, 3, 4, 5 chord notes based on the baseNote within 1 octave
    private void playKeyNote() {
        System.out.println("playing key notes");
        int[] octaves = {0, 12};
        int[] keyNotes = {baseNote, baseNote + 4, baseNote + 7,  baseNote + 5};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];

        midiSynth.play(instrument, note, velocity);
        pause(tempo);
        midiSynth.stop(instrument, note);
    }

    private void playAnyNote() {
        int[] octaves = {0, -7, 7, 14, -14};
        int keyNote = ThreadLocalRandom.current().nextInt(baseNote - 7, baseNote + 7);
        int note = keyNote + octaves[random.nextInt(octaves.length)];

        midiSynth.play(instrument, note, velocity);
        pause(tempo);
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

    //getters
    public int getInstrument() {
        return instrument;
    }

    public int getBaseNote() {
        return baseNote;
    }

    public int getTempo() {
        return tempo;
    }
}
