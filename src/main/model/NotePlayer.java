package model;

import sound.MidiSynth;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NotePlayer {
    private final static Random random = new Random();
    private final static int[] octaves = {0, 12, 24};

    public final int INSTRUMENT = 10;
    public final int BASENOTE = 60;
    public final int TEMPO = 300;

    private MidiSynth midiSynth = new MidiSynth();
    private int instrument = INSTRUMENT;
    //velocity between 0-127, but very quiet if less than 60
    private int velocity = 100;
    //from 0-127, 60 is middle C
    private int baseNote = BASENOTE;
    private int tempo = TEMPO;

    private Boolean stop = false;
    private Boolean minor = false;

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
            if (stop) {
                break;
            }

            if (isPause(note)) {
                pause(tempo);
            } else if (isVowel(note) && !minor) {
                playSeventhChord();
            } else if (minor) {
                playMinorChord();
            } else {
                playBasicChord();
            }
        }
    }

    //EFFECTS: randomly plays the major chord notes based on the baseNote within 2 octaves
    private void playBasicChord() {
        int[] keyNotes = {baseNote, baseNote + 4, baseNote + 7};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];
        int[] beats = {tempo, tempo, tempo, tempo/2};

        midiSynth.play(instrument, note, velocity);
        pause(beats[random.nextInt(beats.length)]);
        midiSynth.stop(instrument, note);
    }

    private void playMinorChord() {
        int[] keyNotes = {baseNote, baseNote + 2, baseNote + 3, baseNote + 7};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];
        int[] beats = {tempo, tempo, tempo, tempo/2};

        midiSynth.play(instrument, note, velocity);
        pause(beats[random.nextInt(beats.length)]);
        midiSynth.stop(instrument, note);
    }

    private void playSeventhChord() {
        int[] keyNotes = {baseNote, baseNote + 4, baseNote + 7, baseNote - 1};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];
        int[] beats = {tempo, tempo, tempo, tempo/2, tempo/4};

        midiSynth.play(instrument, note, velocity);
        pause(beats[random.nextInt(beats.length)]);
        midiSynth.stop(instrument, note);
    }

    private void playSecondChord() {
        int[] keyNotes = {baseNote, baseNote + 5, baseNote + 1, baseNote + 9};
        int note = keyNotes[random.nextInt(keyNotes.length)] + octaves[random.nextInt(octaves.length)];
        int[] beats = {tempo, tempo, tempo, tempo/2, tempo/4};

        midiSynth.play(instrument, note, velocity);
        pause(beats[random.nextInt(beats.length)]);
        midiSynth.stop(instrument, note);
    }

    private void playAnyNote() {
        int keyNote = ThreadLocalRandom.current().nextInt(baseNote - 12, baseNote + 12);
        int note = keyNote + octaves[random.nextInt(octaves.length)];
        int[] beats = {tempo, tempo, tempo, tempo/2, tempo/4};

        midiSynth.play(instrument, note, velocity);
        pause(beats[random.nextInt(beats.length)]);
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

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public void setMinor (Boolean minor) {
        this.minor = minor;
    }

    public Boolean getMinor() {
        return minor;
    }

    public void reset() {
        instrument = 10;
        velocity = 100;
        baseNote = 60;
        minor = false;
    }
}
