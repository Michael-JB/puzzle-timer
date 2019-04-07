package uk.co.ramyun.cube_timer.data;

public enum Slice {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Slice.java
	 */

	R(), L(), F(), U(), B(), D(), M();

	private String name;

	Slice() {
		name = super.toString();
	}

	public String getName() {
		return name;
	}

	public Slice getOpposite(Slice slice) {
		switch (slice) {
		case B:
			return Slice.F;
		case D:
			return Slice.U;
		case F:
			return Slice.B;
		case L:
			return Slice.R;
		case M:
			return null;
		case R:
			return Slice.L;
		case U:
			return Slice.D;
		}
		return null;
	}

	public boolean isOppositeTo(Slice slice) {
		Slice opposite = getOpposite(this);
		return opposite != null && opposite.equals(slice);
	}

	@Override
	public String toString() {
		return getName();
	}
}
