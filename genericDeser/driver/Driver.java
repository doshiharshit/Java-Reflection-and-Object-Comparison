/**
 * 
 */
package genericDeser.driver;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.util.Logger;
import genericDeser.util.Logger.DebugLevel;
import genericDeser.util.PopulateObjects;

/**
 * @author Harshit
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			if(args!=null && args.length==2){}else{
				System.out.println("Invalid Arguments");
				System.exit(0);
			}
			Integer debugValue = Integer.parseInt(args[1]);
			if(debugValue < 0 || debugValue > 4){
				System.out.println("Invalid Debug Value");
				System.exit(0);
			}else{
				Logger.setDebugValue(debugValue);
			}
			
			FileProcessor fp = new FileProcessor(args[0], "");
			PopulateObjects populateObjects = new PopulateObjects();
			populateObjects.processFile(fp);
			Logger.writeMessage(populateObjects.toString(), DebugLevel.TOSTRING);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
		}
	}

}
