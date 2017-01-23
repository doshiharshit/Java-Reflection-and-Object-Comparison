package genericDeser.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.reflectionDOM.DocumentObjectModel;

public class PopulateObjects {
	private Set<First> dsFirst = new HashSet<First>();
	private Set<Second> dsSecond = new HashSet<Second>();
	private Integer firstDupCount = 0;
	private Integer secondDupCount = 0;
	private DocumentObjectModel objDetails;
	
	public Set<First> getDsFirst() {
		return dsFirst;
	}
	public void setDsFirst(Set<First> dsFirst) {
		this.dsFirst = dsFirst;
	}
	public Set<Second> getDsSecond() {
		return dsSecond;
	}
	public void setDsSecond(Set<Second> dsSecond) {
		this.dsSecond = dsSecond;
	}
	
	public void processFile(FileProcessor fp){
		String readFileLine = null;
		while( (readFileLine = fp.readFileContents()) != null){
			if(readFileLine.startsWith("fqn")){
				if(objDetails != null){
					if("First".equals(objDetails.getObjType())){
						First temp = (First) objDetails.getObj();
						if(dsFirst.contains(temp)){
							firstDupCount++;
						}else{
							dsFirst.add(temp);
						}
					}else if("Second".equals(objDetails.getObjType())){
						Second temp = (Second) objDetails.getObj();
						if(dsSecond.contains(temp)){
							secondDupCount++;
						}else{
							dsSecond.add(temp);
						}
					}
				}
				objDetails = new DocumentObjectModel();
				try {
					if(readFileLine.endsWith("First")){
						objDetails.setObjType("First");
					}else if(readFileLine.endsWith("Second")){
						objDetails.setObjType("Second");
					}
					objDetails.setCls( Class.forName(readFileLine.substring(4)) );
					objDetails.setObj( objDetails.getCls().newInstance() );
				} catch (ClassNotFoundException e) {
					System.out.println("Invalid Class Name: " + readFileLine.substring(4));
					e.printStackTrace();
				} catch (InstantiationException | IllegalAccessException e) {
					System.out.println("Unable to instantiate: " + readFileLine.substring(4));
					e.printStackTrace();
				} finally {
					
				}
			}else{
				try {
					objDetails.setInvocationDetails(readFileLine);
					Method meth = objDetails.getCls().getMethod(objDetails.getMethdName(), objDetails.getSignature());
				    @SuppressWarnings("unused")
					Object result = meth.invoke(objDetails.getObj(), objDetails.getParams()); 
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
						IllegalArgumentException | InvocationTargetException e) {
					System.out.println("Exception while invoking method");
					e.printStackTrace();
				} finally {
					
				}
			}
		}
		if(objDetails != null){
			if("First".equals(objDetails.getObjType())){
				First temp = (First) objDetails.getObj();
				if(dsFirst.contains(temp)){
					firstDupCount++;
				}else{
					dsFirst.add(temp);
				}
			}else if("Second".equals(objDetails.getObjType())){
				Second temp = (Second) objDetails.getObj();
				if(dsSecond.contains(temp)){
					secondDupCount++;
				}else{
					dsSecond.add(temp);
				}
			}
		}
	}
	
	public String getCountOutputString(){
		String out = "";
		if(getDsFirst() != null && !getDsFirst().isEmpty() && getDsFirst().size()>0){
			out = out 	+ "Number of unique First objects: " + getDsFirst().size()  + "\n"
						+ "Total Number of First objects: "  + (getDsFirst().size() + firstDupCount) + "\n";
		}
		if(getDsSecond() != null && !getDsSecond().isEmpty() && getDsSecond().size()>0){
			out = out	+ "Number of unique Second objects: "+ getDsSecond().size() + "\n"
						+ "Total Number of Second objects: " + (getDsSecond().size()+ secondDupCount);
		}
		return out;
	}
	
	@Override
	public String toString() {
		return getCountOutputString();
	}
}
