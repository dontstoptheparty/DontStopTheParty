package br.ufrgs.inf.dontstoptheparty.ui;

import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxImpl;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxListener;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.MediaProcessorInterface;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.utils.DirectoryUtils;
import br.ufrgs.inf.dontstoptheparty.utils.FileUtils;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JPanel mainPanel;
    private JButton playPauseButton;
    private JButton resetButton;
    private JButton startStopButton;
    private JButton openFileButton;
    private JTextArea musicTextArea;
    private JButton buttonRecord;

    private static final String DEFAULT_ERROR_TITLE_DIALOG = "Error";
    private static final String RECORD_SAVE_SUCCESSFUL = "File was successfully recorded.";
    private boolean isPlaying;
    private boolean isRunning;

    private static final String STOP_TEXT = "Stop";
    private static final String START_TEXT = "Start";
    private static final String PLAY_TEXT = "Play";
    private static final String PAUSE_TEXT = "Pause";
    private static final String MIDI_UNAVAILABLE_EXCEPTION = "We are unable to play songs to you.";
    private static final String FILE_READ_ERROR = "Error reading selected text file.";
    private static final String INVALID_TEXT_FILE = "Invalid text file.";
    private static final String RECORD_SAVE_ERROR = "Error saving your music.";
    private final MediaProcessorInterface<String> textProcessor;
    private static final String INVALID_DIRECTORY = "Invalid directory.";
    private final JukeBox jukeBox;

    public Main() throws MidiUnavailableException {
        this(new JukeBoxImpl(new ArrayList<>()), new TextProcessor());
    }

    public Main(JukeBox newJukeBox, MediaProcessorInterface<String> newMediaProcessor) {
        this.jukeBox = newJukeBox;
        this.textProcessor = newMediaProcessor;
        this.isPlaying = false;
        this.isRunning = false;
        this.updateButtons();

        this.jukeBox.setFinishListener(new JukeBoxListener() {
            @Override
            public void onStarted() {
                jukeBoxStartListener();
            }

            @Override
            public void onTokenPlayed(List<Token> tokens, int position) {
                jukeBoxTokenPlayedListener(tokens, position);
            }

            @Override
            public void onFinish() {
                jukeBoxFinishListener();
            }
        });
        this.playPauseButton.addActionListener(actionEvent -> this.handlePlayPauseButtonClick());
        this.startStopButton.addActionListener(actionEvent -> this.handleStartStopButtonClick());
        this.resetButton.addActionListener(actionEvent -> this.handleResetButtonClick());
        this.openFileButton.addActionListener(actionEvent -> this.handleOpenFileButtonClick());
        this.buttonRecord.addActionListener(actionEvent -> this.handleRecordButtonClick());
    }

    public void display() {
        JFrame frame = new JFrame("DontStopTheParty");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void handlePlayPauseButtonClick() {
        if (isRunning) {
            if (isPlaying) {
                jukeBox.pause();
            } else {
                jukeBox.play();
            }
            isPlaying = !isPlaying;
            updatePlayPauseButton();
        }
    }

    private void handleStartStopButtonClick() {
        if (this.isRunning) {
            this.jukeBox.stop();
            this.isPlaying = false;
        } else {
            this.loadJukeboxTokens();
            this.jukeBox.start();
            this.isPlaying = true;
        }
        this.isRunning = !this.isRunning;
        this.updateButtons();
    }

    private void unblockTextArea() {
        this.musicTextArea.setEditable(true);
    }

    private void blockTextArea() {
        this.musicTextArea.setEditable(false);
    }

    private void handleResetButtonClick() {
        this.jukeBox.reset();
    }

    private void handleOpenFileButtonClick() {
        final String filePath = FileUtils.chooseFile();
        if (filePath != null) {
            try {
                final String fileText = FileUtils.readTextFromTextFile(filePath);
                this.musicTextArea.setText(fileText);
            } catch (IOException e) {
                this.showErrorDialog(FILE_READ_ERROR);
            }
        } else {
            this.showErrorDialog(INVALID_TEXT_FILE);
        }
    }

    private void handleRecordButtonClick() {
        final String directory = DirectoryUtils.chooseDirectory();
        if (directory != null) {
            this.loadJukeboxTokens();
            try {
                this.jukeBox.record(directory);
                this.showMessageDialog(RECORD_SAVE_SUCCESSFUL);
            } catch (IOException e) {
                this.showErrorDialog(RECORD_SAVE_ERROR);
            }
        } else {
            this.showErrorDialog(INVALID_DIRECTORY);
        }
    }

    private void jukeBoxStartListener() {
        this.blockTextArea();
        highlightCharTextArea(0);
    }

    private void jukeBoxTokenPlayedListener(List<Token> tokens, int position) {
        final String musicText = this.musicTextArea.getText();
        int charAt = textProcessor.getOriginPositionFromListPosition(musicText, tokens, position);

        highlightCharTextArea(charAt);
    }

    private void jukeBoxFinishListener() {
        this.isPlaying = false;
        this.isRunning = false;
        unhighlightTextArea();
        unblockTextArea();
        this.updateButtons();
    }

    private void highlightCharTextArea(int startHighlight) {
        int endHighlight = startHighlight + 1;
        if (endHighlight <= musicTextArea.getText().length()) {
            try {
                Highlighter highlighter = musicTextArea.getHighlighter();
                Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                highlighter.removeAllHighlights();
                highlighter.addHighlight(startHighlight, endHighlight, highlightPainter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private void unhighlightTextArea() {
        Highlighter highlighter = musicTextArea.getHighlighter();
        highlighter.removeAllHighlights();
    }

    private void updateButtons() {
        this.updatePlayPauseButton();
        this.updateStartStopButton();
        this.updateResetButton();
    }

    private void updatePlayPauseButton() {
        if (this.isPlaying) {
            this.playPauseButton.setText(PAUSE_TEXT);
        } else {
            this.playPauseButton.setText(PLAY_TEXT);
        }

        this.playPauseButton.setEnabled(this.isRunning);
    }

    private void updateStartStopButton() {
        if (this.isRunning) {
            this.startStopButton.setText(STOP_TEXT);
        } else {
            this.startStopButton.setText(START_TEXT);
        }
    }

    private void updateResetButton() {
        this.resetButton.setEnabled(this.isRunning);
    }

    private void loadJukeboxTokens() {
        final String musicText = this.musicTextArea.getText();
        final List<Token> tokens = this.textProcessor.convert(musicText);
        this.jukeBox.reload(tokens);
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, DEFAULT_ERROR_TITLE_DIALOG, JOptionPane.ERROR_MESSAGE);
    }
}
