package de.age.logtool;

/**
 * The timestamp of a log. This can be the time denoted by the entry in the
 * logfile, or the time the line was read, if no timestamp is available in the
 * file.
 */
public class Timestamp implements Comparable<Timestamp> {

	private final long timeInMillis;

	public Timestamp(long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}

	public static Timestamp current() {
		return new Timestamp(System.currentTimeMillis());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Timestamp) {
			return ((Timestamp) obj).timeInMillis == timeInMillis;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int) timeInMillis;
	}

	@Override
	public int compareTo(Timestamp otherTimestamp) {
		long diff = timeInMillis - otherTimestamp.timeInMillis;
		if (diff < 0) {
			return -1;
		} else if (diff > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
