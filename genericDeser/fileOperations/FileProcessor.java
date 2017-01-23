package genericDeser.fileOperations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import genericDeser.util.Logger;

public class FileProcessor {
	private String inputFileName = null;
	private String outputFileName = null;
	private BufferedReader bufferReadFile = null;
	private PrintWriter fileWrite = null;
	
	/**
	 * Get Input FileName
	 * @return String
	 */
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * Set Input FileName
	 * @param inputFileName
	 */
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	/**
	 * Get Output FileName
	 * @return String
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * Set Output FileName
	 * @param outputFileName
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/**
	 * Get BufferedReader
	 * @return BufferedReader
	 */
	public BufferedReader getBufferReadFile() {
		return bufferReadFile;
	}

	/**
	 * Set BufferedReader
	 * @param bufferReadFile
	 */
	public void setBufferReadFile(BufferedReader bufferReadFile) {
		this.bufferReadFile = bufferReadFile;
	}

	/**
	 * Get PrintWriter
	 * @return PrintWriter
	 */
	public PrintWriter getFileWrite() {
		return fileWrite;
	}

	/**
	 * Set PrintWriter
	 * @param fileWrite
	 */
	public void setFileWrite(PrintWriter fileWrite) {
		this.fileWrite = fileWrite;
	}

	/**
	 * FileProcessor - Initialize file processor.
	 * @param inFName
	 * @param oFName
	 */
	public FileProcessor(String inFName, String oFName) {
		try {
			Logger.writeMessage("FileProcessor Constructor called", Logger.DebugLevel.CONSTRUCTOR);
			setInputFileName(inFName);
			setOutputFileName(oFName);
			setBufferReadFile(new BufferedReader(new InputStreamReader(new FileInputStream(inFName))));
//			setFileWrite(new PrintWriter( new BufferedWriter(new OutputStreamWriter(new FileOutputStream(oFName)))));
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			
		}
	}
	
	/**
	 * isInputFileReadable - check if input is readable.
	 * @param inFName
	 * @return
	 */
	public static Boolean isInputFileReadable(String inFName){
		BufferedReader bufferRead = null;
		try {
			bufferRead = new BufferedReader(new InputStreamReader(new FileInputStream(inFName)));
			if(bufferRead.readLine() != null){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bufferRead != null){
				try {
					bufferRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					
				}
			}
		}
		return false;
	}

	/**
	 * readFileContents() - reads file line by line
	 * @return string
	 */
	public synchronized String readFileContents(){
		try{
			if( getBufferReadFile() != null ){
				String line = null;
				if ( (line = getBufferReadFile().readLine() )!=null) {
					return line;
				}
			}
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Exception occured, Closing resources");
			closeFileReadResources();
			System.exit(0);
		}finally {
			
		}
		return null;
	}
	
	/**
	 * closeFileReadResources - closing buffer reader
	 */
	public void closeFileReadResources(){
		try {
			if(getBufferReadFile() != null){
				getBufferReadFile().close();
			}
		} catch (IOException ie) {
			ie.printStackTrace();
			System.exit(0);
		}finally {
			
		}
	}

	/**
	 * closeFileWriteResources - closing print writer
	 */
	public void closeFileWriteResources(){
		if(getFileWrite() != null){
			getFileWrite().close();
		}
	}
	/**
	 * writeToFile() - writes string content to file
	 * @param contentToBeWritten
	 */
	public void writeToFile(String contentToBeWritten){
		getFileWrite().print(contentToBeWritten);
		getFileWrite().flush();
		closeFileWriteResources();
	}
	
	@Override
	public String toString() {
		return "FileProcessor Class :: Input File Name: " + getInputFileName() + " , "
									+ "Output File Name: " + getOutputFileName() + "\n";
	}
	
	/**
	 * in finalize - releasing resources if not released
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		closeFileWriteResources();
		closeFileReadResources();
	}
}
