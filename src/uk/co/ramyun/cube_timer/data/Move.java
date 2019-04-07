package uk.co.ramyun.cube_timer.data;

import java.util.ArrayList;
import java.util.List;

public enum Move {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Move.java
	 */

	RIGHT("R", Slice.R, true), RIGHT_PRIME("R'", Slice.R, true), RIGHT_TWO("R2", Slice.R, true),

	LEFT("L", Slice.L, true), LEFT_PRIME("L'", Slice.L, true), LEFT_TWO("L2", Slice.L, true),

	UP("U", Slice.U, true), UP_PRIME("U'", Slice.U, true), UP_TWO("U2", Slice.U, true),

	DOWN("D", Slice.D, true), DOWN_PRIME("D'", Slice.D, true), DOWN_TWO("D2", Slice.D, true),

	FRONT("F", Slice.F, true), FRONT_PRIME("F'", Slice.F, true), FRONT_TWO("F2", Slice.F, true),

	BACK("B", Slice.B, true), BACK_PRIME("B'", Slice.B, true), BACK_TWO("B2", Slice.B, true),

	MIDDLE("M", Slice.M, true), MIDDLE_PRIME("M'", Slice.M, true), MIDDLE_TWO("M2", Slice.M, true),

	WIDE_RIGHT("r", Slice.R, false), WIDE_RIGHT_PRIME("r'", Slice.R, false), WIDE_RIGHT_TWO("r2", Slice.R, false),

	WIDE_LEFT("l", Slice.L, false), WIDE_LEFT_PRIME("l'", Slice.L, false), WIDE_LEFT_TWO("l2", Slice.L, false),

	WIDE_UP("u", Slice.U, false), WIDE_UP_PRIME("u'", Slice.U, false), WIDE_UP_TWO("u2", Slice.U, false),

	WIDE_DOWN("d", Slice.D, false), WIDE_DOWN_PRIME("d'", Slice.D, false), WIDE_DOWN_TWO("d2", Slice.D, false),

	WIDE_FRONT("f", Slice.F, false), WIDE_FRONT_PRIME("f'", Slice.F, false), WIDE_FRONT_TWO("f2", Slice.F, false),

	WIDE_BACK("b", Slice.B, false), WIDE_BACK_PRIME("b'", Slice.B, false), WIDE_BACK_TWO("b2", Slice.B, false);

	private String name, notation;
	private Slice slice;
	private boolean basic;

	Move(String notation, Slice slice, boolean basic) {
		String temp = super.toString().toLowerCase().replace("_", " ");
		this.name = Character.toUpperCase(temp.charAt(0)) + temp.substring(1, temp.length());
		this.notation = notation;
		this.slice = slice;
		this.basic = basic;
	}

	public boolean isBasic() {
		return basic;
	}

	public static boolean isBasic(Move m) {
		return m.isBasic();
	}

	public Slice getSlice() {
		return slice;
	}

	public String getNotation() {
		return notation;
	}

	public String getName() {
		return name;
	}

	public static Move[] getBasics() {
		List<Move> basics = new ArrayList<>();
		for (Move m : Move.values())
			if (isBasic(m))
				basics.add(m);
		return basics.toArray(new Move[basics.size()]);
	}

	@Override
	public String toString() {
		return getName();
	}
}
