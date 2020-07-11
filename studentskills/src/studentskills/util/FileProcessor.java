package studentskills.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

/**
 * FileProcessor is a utility to be used to read in the contents of the input
 * file.
 *
 *
 * @author Vinit S Bhosale
 */
public final class FileProcessor {
	private BufferedReader reader;

	/**
	 * Constructs a FileProcessor that can stream the contents of the provided input
	 * file line by line.
	 * 
	 * @exception InvalidPathException  On invalid path string.
	 * @exception SecurityException     On not having necessary read permissions to
	 *                                  the input file.
	 * @exception FileNotFoundException On input file not found.
	 * @exception IOException           On any I/O errors while reading lines from
	 *                                  input file.
	 */
	public FileProcessor(String inputFilePath)
			throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		MyLogger.writeMessage("FileProcessor constructor.", MyLogger.DebugLevel.CONSTRUCTOR);
		if (!Files.exists(Paths.get(inputFilePath))) {
			throw new FileNotFoundException("invalid input file or input file in incorrect location");
		}
		MyLogger.writeMessage("Bufferreader object reader intiatizing.", MyLogger.DebugLevel.CONSTRUCTOR);
		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
	}

	/**
	 * Retrieves and returns the next line in the input file.
	 *
	 * @return String The next line read from the input file.
	 * @exception IOException On error encountered when reading from input file.
	 */
	public String poll() throws IOException {
		MyLogger.writeMessage("Poll method reading one line from file\n", MyLogger.DebugLevel.FILEPROCESSOR);
		return reader.readLine();
	}

	/**
	 * Close the buffered reader instance.
	 *
	 * @exception IOException On error encountered when closing the buffered reader.
	 */
	public void close() throws IOException {
		MyLogger.writeMessage("Closing reader.", MyLogger.DebugLevel.CONSTRUCTOR);
		reader.close();
	}
}