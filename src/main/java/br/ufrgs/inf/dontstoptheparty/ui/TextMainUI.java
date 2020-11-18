/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.ui;

import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxImpl;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxListener;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.MediaProcessorInterface;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.utils.FileUtils;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
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

public class TextMainUI {
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

    public TextMainUI() throws MidiUnavailableException {
        this(new JukeBoxImpl(new ArrayList<>()), new TextProcessor());
    }

    public TextMainUI(String filePath) throws MidiUnavailableException {
        this(new JukeBoxImpl(new ArrayList<>()), new TextProcessor());
        openFile(filePath);
    }

    public TextMainUI(JukeBox newJukeBox, MediaProcessorInterface<String> newMediaProcessor) {
        this.jukeBox = newJukeBox;
        this.textProcessor = newMediaProcessor;
        this.isPlaying = false;
        this.isRunning = false;
        this.updateComponents();

        this.jukeBox.setFinishListener(new JukeBoxListener() {
            @Override
            public void onStarted() {
                jukeBoxStartListener();
            }

            @Override
            public void onTokenPlayed(int position) {
                jukeBoxTokenPlayedListener(position);
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
        new DropTarget(this.musicTextArea, new DropTargetListener() {
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
        this.updateComponents();
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

    private void jukeBoxTokenPlayedListener(int position) {
        if (isRunning) {
            highlightCharTextArea(position);
        }
    }

    private void jukeBoxFinishListener() {
        this.isPlaying = false;
        this.isRunning = false;
        this.updateComponents();
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

    private void updateComponents() {
        this.updatePlayPauseButton();
        this.updateStartStopButton();
        this.updateResetButton();
        this.updateRecordButton();
        this.updateOpenFileButton();
        this.updateMusicTextArea();
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

    private void updateRecordButton() {
        this.buttonRecord.setEnabled(!isRunning);
    }

    private void updateOpenFileButton() {
        this.openFileButton.setEnabled(!this.isRunning);
    }

    private void updateMusicTextArea() {
        if (!isRunning) {
            this.unhighlightTextArea();
            this.unblockTextArea();
        }
    }

    private void loadJukeboxTokens() {
        final String musicText = this.musicTextArea.getText();
        final List<Token> tokens = this.textProcessor.convert(musicText);
        this.jukeBox.reload(tokens);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-12236470));
        Font mainPanelFont = this.$$$getFont$$$("Georgia", Font.PLAIN, -1, mainPanel.getFont());
        if (mainPanelFont != null) mainPanel.setFont(mainPanelFont);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(10, 0, 10, 10), -1, -1));
        panel1.setBackground(new Color(-12236470));
        mainPanel.add(panel1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playPauseButton = new JButton();
        playPauseButton.setBackground(new Color(-12895429));
        playPauseButton.setForeground(new Color(-3223858));
        playPauseButton.setText("Play/Pause");
        playPauseButton.setToolTipText("Play or Pause the music");
        panel1.add(playPauseButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, -1), null, new Dimension(80, -1), 0, false));
        resetButton = new JButton();
        resetButton.setBackground(new Color(-12895429));
        resetButton.setForeground(new Color(-3223858));
        resetButton.setText("Reset");
        panel1.add(resetButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, -1), null, new Dimension(80, -1), 0, false));
        startStopButton = new JButton();
        startStopButton.setActionCommand("Stop");
        startStopButton.setBackground(new Color(-12895429));
        startStopButton.setForeground(new Color(-3223858));
        startStopButton.setText("Start/Stop");
        panel1.add(startStopButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, -1), null, new Dimension(80, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 10), -1, -1));
        panel2.setBackground(new Color(-12236470));
        panel2.setForeground(new Color(-12236470));
        mainPanel.add(panel2, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-12236470));
        mainPanel.add(scrollPane1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, 280), null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-3223858)), "Write your song", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, scrollPane1.getFont()), new Color(-4473925)));
        musicTextArea = new JTextArea();
        musicTextArea.setAutoscrolls(true);
        musicTextArea.setBackground(new Color(-12236470));
        musicTextArea.setForeground(new Color(-3223858));
        musicTextArea.setLineWrap(true);
        musicTextArea.setMargin(new Insets(10, 10, 10, 10));
        scrollPane1.setViewportView(musicTextArea);
        openFileButton = new JButton();
        openFileButton.setBackground(new Color(-12895429));
        openFileButton.setForeground(new Color(-3223858));
        openFileButton.setText("Open file");
        openFileButton.setToolTipText("Open a text file containing a music text");
        mainPanel.add(openFileButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), null, new Dimension(100, -1), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 10, 0, 0), -1, -1));
        panel3.setBackground(new Color(-12236470));
        mainPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonRecord = new JButton();
        buttonRecord.setBackground(new Color(-12895429));
        buttonRecord.setForeground(new Color(-3223858));
        buttonRecord.setText("Record");
        buttonRecord.setToolTipText("Records a MIDI file with the written text");
        mainPanel.add(buttonRecord, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), null, new Dimension(100, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
