package studentskills.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Results method that implemments FileDisplayInterface, StdoutDisplayInterface
 * and ResultsI interfaces. It stores tree results and implement writeToFile and
 * writeToStd method and prints in the output file.
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface, ResultsI {
    // Initializing result string to store result
    private String result = "";
    // Intializing filePath sting to store output file.
    private String filePath;

    // Intializing file to create output file.
    private File outputFile;
    // Initializing BufferedWriter to write in output file.
    private BufferedWriter outputBufferedWriter;

    /**
     * Results constructor tha stores corresponding output file.
     * 
     * @param inOpFile - output file
     */
    public Results(String inOpFile) {
        filePath = inOpFile;
    }

    /**
     * storeResult method to store results
     * 
     * @param inResString - result string
     */
    @Override
    public void storeResult(String inResString) {
        result = result.concat(inResString + "\n");
    }

    /**
     * writeToStdout method to print result on stdout.
     */
    @Override
    public void writeToStdout() {
        // TODO Auto-generated method stub
        System.out.println(result);

    }

    /**
     * writeToFile method that write results in the output file.
     * 
     * @throws IOException
     */
    @Override
    public void writeToFile() throws IOException {
        // TODO Auto-generated method stub
        outputFile = new File(filePath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        outputBufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        outputBufferedWriter.write(result.trim());

        outputBufferedWriter.close();

    }

    @Override
    public String toString() {
        return "Class: Results, Data Members: [result=" + result.toString() + ", filePath=" + filePath.toString()
                + ", outputFile=" + outputFile.toString() + ", outputBufferedWriter=" + outputBufferedWriter.toString()
                + "]";

    }

}