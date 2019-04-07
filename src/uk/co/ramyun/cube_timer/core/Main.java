package uk.co.ramyun.cube_timer.core;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import uk.co.ramyun.cube_timer.ui.Gui;

public class Main {
	/**
	 * @author © Michael
	 * @since 9 Mar 2017
	 * @file Main.java
	 */

	public static void main(String[] args) {
		int scrambleMoves = 25;
		setLookAndFeel();
		Gui ui = new Gui(scrambleMoves);

		while (ui != null) {
			ui.updateTimer();
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
	}

}
