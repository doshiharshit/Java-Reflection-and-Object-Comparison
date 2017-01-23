package genericDeser.reflectionDOM;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentObjectModel {
	@SuppressWarnings("rawtypes")
	private Class[] signature ;
	private Object[] params ;
	private String methdName;
	private Object obj;
	private String objType;
	private Class<?> cls;
	private String[] type = { "int", "float", "short", "String", "double", "boolean" };
	@SuppressWarnings("rawtypes")
	private Class[] typeClass = { Integer.TYPE, Float.TYPE, Short.TYPE, String.class, Double.TYPE, Boolean.TYPE };
	private String[] typeObj = { "java.lang.Integer", "java.lang.Float", "java.lang.Short", "java.lang.String", "java.lang.Double", "java.lang.Boolean" };
	
	public Class<?> getCls() {
		return cls;
	}
	public void setCls(Class<?> cls) {
		this.cls = cls;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	@SuppressWarnings("rawtypes")
	public Class[] getSignature() {
		return signature;
	}
	@SuppressWarnings("rawtypes")
	public void setSignature(Class[] signature) {
		this.signature = signature;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public String getMethdName() {
		return methdName;
	}
	public void setMethdName(String methdName) {
		this.methdName = methdName;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void setInvocationDetails(String line){
		String pattern1 = "=";
		String pattern2 = ",";
		
		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(line);
		String[] inputFindings = new String[3];
		int a=0;
		m.find();	inputFindings[a++] = m.group(1);
		m.find();	inputFindings[a++] = m.group(1);
		inputFindings[a] = line.substring( line.lastIndexOf(pattern1) + 1 );
		int index = -1;
		for(int i=0; i<type.length; i++){
			if(type[i].equals(inputFindings[0])){
				index = i;
				break;
			}
		}
		this.setSignature(new Class[]{typeClass[index]});
		this.setMethdName("set" + inputFindings[1]);
		
		try {
			Class<?> cls = Class.forName(typeObj[index]);
		    Constructor<?> c = cls.getConstructor(String.class);
		    Object[] params = new Object[1];
		    params[0] = inputFindings[2];
		    Object obj = c.newInstance(params);
		    Object[] prms = new Object[1];
			prms[0] = obj;
			this.setParams(prms);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
				InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Problem setting param: " + typeObj[index]);
			e.printStackTrace();
		}
	}
}
