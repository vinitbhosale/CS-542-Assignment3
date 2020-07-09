package studentskills.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface, ResultsI {
    private String result = "";
    private String filePath;

    private File outputFile;
    private BufferedWriter outputBufferedWriter;

    public Results(String inOpFile) {
        filePath = inOpFile;
    }

    @Override
    public void storeResult(String inResString) {
        result = result.concat(inResString + "\n");
    }

    @Override
    public void writeToStdout() {
        // TODO Auto-generated method stub
        System.out.println(result);

    }

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

}