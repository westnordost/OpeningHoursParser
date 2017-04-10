package ch.poole.openinghoursparser;
/**
 * Container for objects from the opening_hours specification
 * @author Simon Poole
 *
 * Copyright (c) 2015 Simon Poole
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 " OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import java.util.ArrayList;
import java.util.List;

public class WeekDayRange extends Element {
	
	public enum WeekDay {
		MO("Mo"),
		TU("Tu"),
		WE("We"),
		TH("Th"),
		FR("Fr"),
		SA("Sa"),
		SU("Su");
		
		private final String name;
		
		WeekDay(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}

		public static WeekDay getValue(String day) {
			for (WeekDay w:WeekDay.values()) {
				if (w.toString().equals(day)) {
					return w;
				}
			}
			return null;
		}
		
		public static List<String> nameValues() {
			List<String> result = new ArrayList<String>();
			for (WeekDay w:values()) {
				result.add(w.toString());
			}
			return result;
		}
	}
	
	WeekDay startDay = null;
	WeekDay endDay = null;
	List<Nth> nths = new ArrayList<Nth>();

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(startDay);
		if (endDay != null) {
			b.append("-");
			b.append(endDay);
		} else if (nths != null && !nths.isEmpty()) {
			b.append("["); 
			for (Nth n:nths) {
				b.append(n.toString());
				if (!n.equals(nths.get(nths.size()-1))) {
					b.append(",");
				}
			}
			
			b.append("]");
		}
		return b.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other != null && other instanceof WeekDayRange) {
			WeekDayRange o = (WeekDayRange)other;
			if ((startDay == o.startDay  || (startDay != null && startDay.equals(o.startDay))) 
					&& (endDay == o.endDay  || (endDay != null && endDay.equals(o.endDay)))
					&& (nths == o.nths  || (nths != null && nths.equals(o.nths)))){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 37 * result + (startDay == null ? 0 : startDay.hashCode());
		result = 37 * result + (endDay == null ? 0 : endDay.hashCode());
		result = 37 * result + (nths == null ? 0 : nths.hashCode());
		return result;
	}

	/**
	 * @return the startDay
	 */
	public WeekDay getStartDay() {
		return startDay;
	}

	/**
	 * @return the endDay
	 */
	public WeekDay getEndDay() {
		return endDay;
	}

	/**
	 * @return the nths
	 */
	public List<Nth> getNths() {
		return nths;
	}

	/**
	 * Set the day the range starts on
	 * 
	 * @param day the day to set
	 */
	public void setStartDay(WeekDay day) {
		this.startDay = day;
	}
	
	/**
	 * Set the day the range starts on
	 * 
	 * @param day the day to set
	 */
	public void setStartDay(String day) {
		WeekDay w = WeekDay.getValue(day);
		if (w==null) {
			throw new IllegalArgumentException(day + " is not a valid WeekDay");
		}
		this.startDay = w;
	}

	/**
	 * Set the day the range ends on
	 * 
	 * @param day the day to set
	 */
	public void setEndDay(WeekDay day) {
		this.endDay = day;
	}
	
	/**
	 * Set the day the range ends on
	 * 
	 * @param day the day to set
	 */
	public void setEndDay(String day) {
		WeekDay w = WeekDay.getValue(day);
		if (w==null) {
			throw new IllegalArgumentException(day + " is not a valid WeekDay");
		}
		this.endDay = w;
	}

	/**
	 * @param nths the nths to set
	 */
	public void setNths(List<Nth> nths) {
		this.nths = nths;
	}
}
