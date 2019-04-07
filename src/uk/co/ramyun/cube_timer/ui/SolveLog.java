package uk.co.ramyun.cube_timer.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.ramyun.cube_timer.util.Time;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SolveLog extends JFrame {

	private final JPanel contentPane = new JPanel();

	/**
	 * @author © Michael
	 * @since 9 Mar 2017
	 * @file Solves.java
	 */

	private List<Time> solves = new ArrayList<>();
	private JList list = new JList(solves.toArray());

	private static final long serialVersionUID = 1L;

	public SolveLog(Gui parent) {
		setTitle("Solve log");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 240, 515);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solves.clear();
				list.setListData(solves.toArray());
				parent.updateStats();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Remove Selected");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solves.remove(list.getSelectedValue());
				list.setListData(solves.toArray());
				parent.updateStats();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}

	public Time averageTime() {
		long sum = 0L;
		for (Time t : solves)
			sum += t.getMillis();
		return solves.size() > 0 ? new Time(sum / solves.size()) : new Time(0L);
	}

	public Time bestTime() {
		return solves.size() > 0 ? Collections.min(solves) : new Time(0L);
	}

	public void addTime(Time time) {
		solves.add(time);
		list.setListData(solves.toArray());
	}

	public void removeTime(Time time) {
		solves.remove(time);
		list.setListData(solves.toArray());
	}

	public List<Time> getSolves() {
		return solves;
	}
}
