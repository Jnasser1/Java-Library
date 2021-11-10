package jb5;

import java.time.temporal.TemporalQuery;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;



public class Friday13query implements TemporalQuery<Boolean> {

	@Override
	public Boolean queryFrom(TemporalAccessor date) {

		return ((date.get(ChronoField.DAY_OF_MONTH) == 13) && (date.get(ChronoField.DAY_OF_WEEK) == 5));
	}
}

