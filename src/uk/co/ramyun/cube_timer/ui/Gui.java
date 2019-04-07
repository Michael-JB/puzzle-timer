package uk.co.ramyun.cube_timer.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import uk.co.ramyun.cube_timer.data.Puzzle;
import uk.co.ramyun.cube_timer.util.Time;
import uk.co.ramyun.cube_timer.util.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.EtchedBorder;

public class Gui extends JFrame {

	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Gui.java
	 */

	private boolean active;
	private Timer timer;

	private final JPanel content_pane = new JPanel();
	private final JLabel best_time_label = new JLabel("Best time:");
	private final JLabel average_time_label = new JLabel("Avg time:");
	private final JLabel timer_label = new JLabel("Timer:");
	private final JLabel scramble_label = new JLabel("");
	private final JButton start_button = new JButton("Start");
	private final JComboBox<Puzzle> puzzle_combo_box = new JComboBox<Puzzle>();
	private final JButton solve_log_button = new JButton("Close Solve log");
	private SolveLog solveLog;
	private int scrambleMoves = 0;
	private final JPanel timer_panel = new JPanel();

	private static final long serialVersionUID = 1L;

	public Gui(int scrambleMoves) {
		this.scrambleMoves = scrambleMoves;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				solveLog.dispose();
			}
		});
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				start_button.requestFocusInWindow();
			}
		});
		solveLog = new SolveLog(this);
		solveLog.setVisible(true);
		setTitle("Puzzle Timer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 210);
		content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content_pane);
		GridBagLayout gbl_content_pane = new GridBagLayout();
		gbl_content_pane.columnWidths = new int[] { 0, 0, 0 };
		gbl_content_pane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_content_pane.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_content_pane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
		content_pane.setLayout(gbl_content_pane);
		puzzle_combo_box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateScramble(scrambleMoves);
			}
		});

		puzzle_combo_box.setModel(new DefaultComboBoxModel<Puzzle>(Puzzle.values()));
		GridBagConstraints gbc_puzzle_combo_box = new GridBagConstraints();
		gbc_puzzle_combo_box.gridwidth = 3;
		gbc_puzzle_combo_box.insets = new Insets(0, 0, 5, 0);
		gbc_puzzle_combo_box.fill = GridBagConstraints.HORIZONTAL;
		gbc_puzzle_combo_box.gridx = 0;
		gbc_puzzle_combo_box.gridy = 0;
		content_pane.add(puzzle_combo_box, gbc_puzzle_combo_box);

		GridBagConstraints gbc_scramble_label = new GridBagConstraints();
		gbc_scramble_label.fill = GridBagConstraints.BOTH;
		gbc_scramble_label.gridwidth = 3;
		gbc_scramble_label.insets = new Insets(0, 0, 5, 5);
		gbc_scramble_label.gridx = 0;
		gbc_scramble_label.gridy = 1;
		scramble_label.setHorizontalAlignment(SwingConstants.CENTER);
		content_pane.add(scramble_label, gbc_scramble_label);

		GridBagConstraints gbc_timer_panel = new GridBagConstraints();
		gbc_timer_panel.gridwidth = 3;
		gbc_timer_panel.insets = new Insets(0, 0, 5, 0);
		gbc_timer_panel.fill = GridBagConstraints.BOTH;
		gbc_timer_panel.gridx = 0;
		gbc_timer_panel.gridy = 2;
		timer_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		content_pane.add(timer_panel, gbc_timer_panel);
		GridBagLayout gbl_timer_panel = new GridBagLayout();
		gbl_timer_panel.columnWidths = new int[] { 162 };
		gbl_timer_panel.rowHeights = new int[] { 25 };
		gbl_timer_panel.columnWeights = new double[] { 1.0 };
		gbl_timer_panel.rowWeights = new double[] { 1.0 };
		timer_panel.setLayout(gbl_timer_panel);
		GridBagConstraints gbc_timer_label = new GridBagConstraints();
		gbc_timer_label.insets = new Insets(0, 5, 0, 0);
		gbc_timer_label.fill = GridBagConstraints.BOTH;
		gbc_timer_label.gridx = 0;
		gbc_timer_label.gridy = 0;
		timer_panel.add(timer_label, gbc_timer_label);

		timer_label.setFont(new Font("Tahoma", Font.BOLD, 25));

		GridBagConstraints gbc_best_time_label = new GridBagConstraints();
		gbc_best_time_label.gridwidth = 3;
		gbc_best_time_label.fill = GridBagConstraints.BOTH;
		gbc_best_time_label.insets = new Insets(0, 0, 5, 5);
		gbc_best_time_label.gridx = 0;
		gbc_best_time_label.gridy = 3;
		best_time_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		content_pane.add(best_time_label, gbc_best_time_label);

		GridBagConstraints gbc_average_time_label = new GridBagConstraints();
		gbc_average_time_label.gridwidth = 3;
		gbc_average_time_label.fill = GridBagConstraints.BOTH;
		gbc_average_time_label.insets = new Insets(0, 0, 5, 5);
		gbc_average_time_label.gridx = 0;
		gbc_average_time_label.gridy = 4;
		average_time_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		content_pane.add(average_time_label, gbc_average_time_label);
		start_button.setFont(new Font("Tahoma", Font.BOLD, 11));

		start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageStart();
			}
		});
		GridBagConstraints gbc_start_button = new GridBagConstraints();
		gbc_start_button.fill = GridBagConstraints.BOTH;
		gbc_start_button.insets = new Insets(0, 0, 0, 5);
		gbc_start_button.gridx = 0;
		gbc_start_button.gridy = 5;
		content_pane.add(start_button, gbc_start_button);

		JButton new_scramble_button = new JButton("New Scramble");
		new_scramble_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateScramble(scrambleMoves);
			}
		});
		GridBagConstraints gbc_new_scramble_button = new GridBagConstraints();
		gbc_new_scramble_button.insets = new Insets(0, 0, 0, 5);
		gbc_new_scramble_button.fill = GridBagConstraints.BOTH;
		gbc_new_scramble_button.gridx = 1;
		gbc_new_scramble_button.gridy = 5;
		content_pane.add(new_scramble_button, gbc_new_scramble_button);

		GridBagConstraints gbc_solve_log_button = new GridBagConstraints();
		gbc_solve_log_button.fill = GridBagConstraints.BOTH;
		gbc_solve_log_button.gridx = 2;
		gbc_solve_log_button.gridy = 5;
		solve_log_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solveLog.setVisible(!solveLog.isVisible());
				solve_log_button.setText((solveLog.isVisible() ? "Close" : " Open") + " Solve log");
			}
		});
		content_pane.add(solve_log_button, gbc_solve_log_button);

		generateScramble(scrambleMoves);
		setVisible(true);
	}

	public void generateScramble(int moves) {
		scramble_label.setText(((Puzzle) puzzle_combo_box.getSelectedItem()).scramble(moves));
	}

	private void manageStart() {
		if (active) {
			if (timer != null && solveLog != null) {
				long time = timer.getElapsed();
				solveLog.addTime(new Time(time));
				setTimer(new Time(time));
				updateStats();
				generateScramble(scrambleMoves);
				start_button.setText("Start");
				timer = null;
			}
		} else {
			start_button.setText("Stop");
			timer = new Timer(0L);
		}
		active = !active;
	}

	public void setTimer(Time time) {
		if (timer != null)
			timer_label.setText("Timer: " + time);
	}

	public void updateTimer() {
		if (timer != null)
			timer_label.setText("Timer: " + new Time(timer.getElapsed()).format());
	}

	public void updateStats() {
		if (solveLog != null) {
			setAvgTime(solveLog.averageTime());
			setBestTime(solveLog.bestTime());
		}
	}

	private void setBestTime(Time time) {
		best_time_label.setText("Best time: " + (time.getMillis() != 0 ? time.toString() : ""));
	}

	private void setAvgTime(Time time) {
		average_time_label.setText("Avg time: " + (time.getMillis() != 0 ? time.toString() : ""));
	}
}
