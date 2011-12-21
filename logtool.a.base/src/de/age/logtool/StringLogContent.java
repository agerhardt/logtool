package de.age.logtool;

public class StringLogContent implements LogContent {

	private final String content;

	public StringLogContent(String content) {
		if (content == null) {
			throw new NullPointerException();
		}
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StringLogContent) {
			StringLogContent otherObj = (StringLogContent) obj;
			return content.equals(otherObj.content);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return content.hashCode();
	}

	public String getString() {
		return content;
	}

}
