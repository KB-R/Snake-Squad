package gameModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * @author Maxime Ndutiye
 * GOCManagerFileStream handles the saving and loading of a 
 * GOCManager to and from a file to that the game state can be saved
 * and continued later
 */
public class GOCManagerFileStream {

	/**
	 * Write a given object to file given the filepath
	 * @param Object serObj the object to write to file
	 * @param filepath the path of the file to write to
	 */
	public static void WriteToFile(Object serObj, String filepath) {
		try {

			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(serObj);
			objectOut.close();
			System.out.println("Save Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read a GOCManager from a file
	 * @param String file the path to the file to open from
	 * @return
	 */
	public static GOCManager readFromFile(String file) {
		GOCManager manager = null;
		ObjectInputStream inputStream = null;
		try {
		   	inputStream = new ObjectInputStream(new FileInputStream(file));
			manager = (GOCManager) inputStream.readObject();
		} catch (EOFException eofException) {
		   return manager;
		} catch (ClassNotFoundException classNotFoundException) {
		   System.err.println("Object creation failed.");
		} catch (IOException ioException) {
		   System.err.println("Error opening file.");
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException ioException) {
				System.err.println("Error closing file.");
			}
		}
		return manager;
	 }
}