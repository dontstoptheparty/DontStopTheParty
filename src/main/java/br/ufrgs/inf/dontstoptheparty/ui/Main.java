package br.ufrgs.inf.dontstoptheparty.ui;

import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.utils.DirectoryUtils;
import br.ufrgs.inf.dontstoptheparty.utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.List;

public class Main {
    private JPanel mainPanel;
    private JButton playPauseButton;
    private JButton resetButton;
    private JButton startStopButton;
    private JButton openFileButton;
    private JTextArea musicTextArea;
    private JButton buttonRecord;
    private final TextProcessor textProcessor;
    private final JukeBox jukeBox;
    private boolean isPlaying;
    private boolean isRunning;

    private static final String STOP_TEXT = "Stop";
    private static final String START_TEXT = "Start";
    private static final String PLAY_TEXT = "Play";
    private static final String PAUSE_TEXT = "Pause";
    private static final String FILE_READ_ERROR = "Error reading selected text file.";
    private static final String INVALID_TEXT_FILE = "Invalid text file.";
    private static final String RECORD_SAVE_ERROR = "Error saving your music.";
    private static final String INVALID_DIRECTORY = "Invalid directory.";

    public Main(JukeBox newJukeBox, TextProcessor newTextProcessor) {
        this.jukeBox = newJukeBox;
        this.textProcessor = newTextProcessor;
        this.isPlaying = false;
        this.isRunning = false;
        this.updateButtons();

        this.jukeBox.setFinishCallback(() -> this.finishCallback());
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
                this.showMessageDialog(FILE_READ_ERROR);
            }
        } else {
            this.showMessageDialog(INVALID_TEXT_FILE);
        }
    }

    private void handleRecordButtonClick() {
        final String directory = DirectoryUtils.chooseDirectory();
        if (directory != null) {
            this.loadJukeboxTokens();
            try {
                this.jukeBox.record(directory);
            } catch (IOException e) {
                this.showMessageDialog(RECORD_SAVE_ERROR);
            }
        } else {
            this.showMessageDialog(INVALID_DIRECTORY);
        }
    }

    private void finishCallback() {
        this.isPlaying = false;
        this.isRunning = false;
        this.updateButtons();
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

        if (this.isRunning) {
            this.playPauseButton.setEnabled(true);
        } else {
            this.playPauseButton.setEnabled(false);
        }
    }

    private void updateStartStopButton() {
        if (this.isRunning) {
            this.startStopButton.setText(STOP_TEXT);
        } else {
            this.startStopButton.setText(START_TEXT);
        }
    }

    private void updateResetButton() {
        if (this.isRunning) {
            this.resetButton.setEnabled(true);
        } else {
            this.resetButton.setEnabled(false);
        }
    }

    private void loadJukeboxTokens() {
        final String musicText = this.musicTextArea.getText();
        final List<Token> tokens = this.textProcessor.convert(musicText);
        this.jukeBox.reload(tokens);
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
