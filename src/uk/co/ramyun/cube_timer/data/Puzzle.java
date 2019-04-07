package uk.co.ramyun.cube_timer.data;

import java.util.List;

import uk.co.ramyun.cube_timer.util.Random;

import java.util.ArrayList;
import java.util.Arrays;

public enum Puzzle {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Puzzle.java
	 */

	RUBIKAPOSTROPHES_CUBE(new ArrayList<Move>(Arrays.asList(Move.getBasics()))),

	RUBIKAPOSTROPHES_REVENGE(new ArrayList<Move>(Arrays.asList(Move.values()))),

	PROFESSORAPOSTROPHES_CUBE(new ArrayList<Move>(Arrays.asList(Move.values())));

	private String name;
	private List<Move> notationList;
	private Random random;

	Puzzle(List<Move> notationSet) {
		String temp = super.toString().toLowerCase().replace("_", " ").replace("apostrophe", "'");
		this.name = Character.toUpperCase(temp.charAt(0)) + temp.substring(1, temp.length());
		this.notationList = notationSet;
		this.random = new Random();
	}

	public String scramble(int moves) {
		StringBuilder sb = new StringBuilder();
		generateScramble(moves).stream().map(r -> r.getNotation()).forEach(notation -> sb.append(notation + " "));
		return sb.toString();
	}

	private List<Move> generateScramble(int moves) {
		Move randomMove = getRandomMove();
		List<Move> scramble = new ArrayList<>(Arrays.asList(randomMove));
		for (int i = 0; i < moves - 1; i++) {
			while (scramble.get(i).getSlice().equals(randomMove.getSlice())
					|| scramble.get(i).getSlice().isOppositeTo(randomMove.getSlice()))
				randomMove = getRandomMove();
			scramble.add(randomMove);
		}
		return scramble;
	}

	public Move getRandomMove() {
		return notationList.get(random.generate(0, notationList.size() - 1));
	}

	public List<Move> getNotationSet() {
		return notationList;
	}

	@Override
	public String toString() {
		return name;
	}
}
