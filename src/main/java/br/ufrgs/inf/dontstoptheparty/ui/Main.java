package br.ufrgs.inf.dontstoptheparty.ui;

import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxImpl;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxListener;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.MediaProcessorInterface;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.utils.FileUtils;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.ufrgs.inf.dontstoptheparty.ui.UITextConstants.*;

public class Main {
    private JPanel mainPanel;
    private JButton playPauseButton;
    private JButton resetButton;
    private JButton startStopButton;
    private JButton openFileButton;
    private JTextArea musicTextArea;
    private JButton buttonRecord;

    private boolean isPlaying;
    private boolean isRunning;

    private final MediaProcessorInterface<String> textProcessor;
    private final JukeBox jukeBox;

    public Main() throws MidiUnavailableException {
        this(new JukeBoxImpl(new ArrayList<>()), new TextProcessor());
    }

    public Main(String filePath) throws MidiUnavailableException {
        this(new JukeBoxImpl(new ArrayList<>()), new TextProcessor());
        openFile(filePath);
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

        this.playPauseButton.setMnemonic('P');
        this.startStopButton.setMnemonic('S');
        this.buttonRecord.setMnemonic('R');
        this.openFileButton.setMnemonic('O');
        this.resetButton.setMnemonic('e');

        this.playPauseButton.addActionListener(actionEvent -> this.handlePlayPauseButtonClick());
        this.startStopButton.addActionListener(actionEvent -> this.handleStartStopButtonClick());
        this.resetButton.addActionListener(actionEvent -> this.handleResetButtonClick());
        this.openFileButton.addActionListener(actionEvent -> this.handleOpenFileButtonClick());
        this.buttonRecord.addActionListener(actionEvent -> this.handleRecordButtonClick());

        enableDragAndDrop();
    }

    private void enableDragAndDrop() {
        DropTarget target = new DropTarget(this.musicTextArea, new DropTargetListener() {
            public void dragEnter(DropTargetDragEvent e) {
            }

            public void dragExit(DropTargetEvent e) {
            }

            public void dragOver(DropTargetDragEvent e) {
            }

            public void dropActionChanged(DropTargetDragEvent e) {
            }

            @SuppressWarnings(value = "unchecked")
            public void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                List<File> list;
                try {
                    list = (List<File>) event.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    File file = list.get(0);
                    openFile(file.getPath());
                } catch (UnsupportedFlavorException | IOException e) {
                    UIUtils.showErrorDialog(INVALID_TEXT_FILE);
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateMusicTextAreaText(String readTextFromTextFile) {
        this.musicTextArea.setText(readTextFromTextFile);
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
        final String filePath = UIUtils.chooseFile();
        openFile(filePath);
    }

    private void openFile(String filePath) {
        if (filePath != null) {
            try {
                final String fileText = FileUtils.readTextFromTextFile(filePath);
                if (fileText != null) {
                    updateMusicTextAreaText(fileText);
                } else {
                    UIUtils.showErrorDialog(FILE_READ_ERROR_NOT_TXT_FILE);
                }
            } catch (IOException e) {
                UIUtils.showErrorDialog(FILE_READ_ERROR);
            }
        } else {
            UIUtils.showErrorDialog(INVALID_TEXT_FILE);
        }
    }

    private void handleRecordButtonClick() {
        final String directory = UIUtils.chooseDirectory();
        if (directory != null) {
            this.loadJukeboxTokens();
            try {
                this.jukeBox.record(directory);
                UIUtils.showMessageDialog(RECORD_SAVE_SUCCESSFUL);
            } catch (IOException e) {
                UIUtils.showErrorDialog(RECORD_SAVE_ERROR);
            }
        } else {
            UIUtils.showErrorDialog(INVALID_DIRECTORY);
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

    private void unblockTextArea() {
        this.musicTextArea.setEditable(true);
    }

    private void blockTextArea() {
        this.musicTextArea.setEditable(false);
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
        this.updateOpenFileButton();
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

    private void updateOpenFileButton() {
        this.openFileButton.setEnabled(!this.isRunning);
    }

    private void loadJukeboxTokens() {
        final String musicText = this.musicTextArea.getText();
        final List<Token> tokens = this.textProcessor.convert(musicText);
        this.jukeBox.reload(tokens);
    }


}
