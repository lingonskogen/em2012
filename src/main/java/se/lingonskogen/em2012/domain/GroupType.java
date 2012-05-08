package se.lingonskogen.em2012.domain;

public enum GroupType {
	ATELES,
	SIGMA,
	VÄNNER;
	
	@Override public String toString() {
	   String s = super.toString();
	   return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}