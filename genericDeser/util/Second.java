package genericDeser.util;

import java.util.Objects;

public class Second {
	int intValue;
	double doubleValue;
	boolean booleanValue;
	
	public Second() {}
	
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public boolean getBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Second){
			Second inObj = (Second) obj;
			if( Objects.equals(this.getIntValue(), inObj.getIntValue()) 		&&
				Objects.equals(this.getDoubleValue(), inObj.getDoubleValue())	&&
				Objects.equals(this.getBooleanValue(), inObj.getBooleanValue())	){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0, hashCont = 37;
		
		hashCode = (hashCont * hashCode) + Float.floatToIntBits(Double.doubleToLongBits(getDoubleValue()));
		hashCode = (hashCont * hashCode) + (getIntValue());
		hashCode = (hashCont * hashCode) + (getBooleanValue() ? 0 : 1);
		
		return hashCode;
	}
	
	@Override
	public String toString() {
		return 	  "Double: "	+ getDoubleValue()
				+ ", Int: "		+ getIntValue()
				+ ", Boolean: "	+ getBooleanValue();
	}
}
