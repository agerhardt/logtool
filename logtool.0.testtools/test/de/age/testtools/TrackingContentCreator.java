package de.age.testtools;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public abstract class TrackingContentCreator<T> {

	private final List<T> trackedContent = new ArrayList<T>();
	
	protected abstract T createContent();

	public T create()  {
		T newContent = createContent();
		trackedContent.add(newContent);
		return newContent;
	}
	
	public Matcher<List<T>> matcher() {
		return new ContentMatcher(0);
	}
	
	public Matcher<List<T>> matcher(int startingOffset) {
		return new ContentMatcher(startingOffset);
	}

	private final class ContentMatcher extends BaseMatcher<List<T>> {

		private final int startingOffset;
		
		public ContentMatcher(int startingOffset) {
			super();
			this.startingOffset = startingOffset;
		}

		@Override
		public boolean matches(Object item) {
			if (item instanceof List<?>) {
				List<?> list = (List<?>) item;
				if (list.size() != trackedContent.size() - startingOffset) {
					return false;
				}
				for (int i = 0; i < list.size(); i++) {
					if (!trackedContent.get(i + startingOffset).equals(list.get(i))) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void describeTo(Description description) {
			description.appendValue(trackedContent);
		}
	}
	
}
