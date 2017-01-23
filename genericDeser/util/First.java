package genericDeser.util;

import java.util.Objects;

public class First {
	int intValue;
	float floatValue;
	short shortValue;
	String stringValue;
	
	public First() {}
	
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public float getFloatValue() {
		return floatValue;
	}
	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	public short getShortValue() {
		return shortValue;
	}
	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof First){
			First inObj = (First) obj;
			if( Objects.equals(this.getIntValue(),    inObj.getIntValue())    &&
				Objects.equals(this.getShortValue(),  inObj.getShortValue())  &&
				Objects.equals(this.getFloatValue(),  inObj.getFloatValue())  &&
				Objects.equals(this.getStringValue(), inObj.getStringValue()) ){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0, hashCont = 37;
		String variablesInString = getStringValue();
		for(int i=0; variablesInString!=null && i<variablesInString.length(); i++){
			hashCode = (hashCont * hashCode) + ((int) variablesInString.charAt(i));
		}
		
		hashCode = (hashCont * hashCode) + (Float.floatToIntBits(getFloatValue()));
		hashCode = (hashCont * hashCode) + (getIntValue());
		hashCode = (hashCont * hashCode) + (getShortValue());
		
		return hashCode;
	}
	
	@Override
	public String toString() {
		return 	  "Int: "		+ getIntValue()
				+ ", Float: "	+ getFloatValue()
				+ ", Short: "	+ getShortValue()
				+ ", String: "	+ getStringValue();
	}
}
