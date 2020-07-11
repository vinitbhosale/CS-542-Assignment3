package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.mytree.Tree;
import studentskills.mytree.TreeHelper;
import studentskills.processor.InputDataProcessor;
import studentskills.userException.EmptyInputFileException;
import studentskills.userException.EmptyModifyFileException;
import studentskills.userException.InvalidInputFormatException;
import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.ResultsI;
import studentskills.util.StdoutDisplayInterface;

/**
 * Driver class start point.
 * 
 * @author Vint S Bhosale
 */

public class Driver {

    public static void main(String[] args) throws InvalidPathException, FileNotFoundException, IOException,
            EmptyInputFileException, EmptyModifyFileException, InvalidInputFormatException {
        try {
            /*
             * As the build.xml specifies the arguments as input,output1, output2, output3,
             * error, and debug text file in case the argument value is not given java takes
             * the default value specified in build.xml. To avoid that, below condition is
             * used
             */
            if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}"))
                    || (args[2].equals("${output1}")) || (args[3].equals("${output2}"))
                    || (args[4].equals("${output3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
                System.err.println("Error: Incorrect number of arguments. Program accepts 7 arguments.");
                System.exit(0);
            }
            // Condition for missing Input file.
            if (args[0].isEmpty()) {
                throw new FileNotFoundException("Missing Input file parameter!");

            }
            // Condition for missing Modify file.
            if (args[1].isEmpty()) {
                throw new FileNotFoundException("Missing Modify file parameter!");

            }
            MyLogger.writeMessage("Setting debug level to " + args[6], MyLogger.DebugLevel.DRIVER);
            MyLogger.setDebugValue(Integer.parseInt(args[6]));

            /**
             * Object creation of three trees.
             */
            Tree replica_0 = new Tree();
            Tree replica_1 = new Tree();
            Tree replica_2 = new Tree();

            /**
             * Results object of three trees with output file as parameter.
             */
            ResultsI rs0 = new Results(args[2]);
            ResultsI rs1 = new Results(args[3]);
            ResultsI rs2 = new Results(args[4]);

            ResultsI errRs = new Results(args[5]);

            /**
             * TreeHelperobject creation
             * 
             * @params ResultsI, and three tree objects.
             */
            TreeHelper tH = new TreeHelper(rs0, rs1, rs2, errRs,replica_0, replica_1, replica_2);

            /**
             * FileProcessor objects for Input and Modify file
             * 
             * @params Respective text file.
             */
            FileProcessor inputFp = new FileProcessor(args[0]);
            FileProcessor modifyFp = new FileProcessor(args[1]);

            /**
             * InputDataProcessor object with two FileProcessor object and TreeHelper object
             * as parameter.
             */
            InputDataProcessor iDp = new InputDataProcessor(inputFp, modifyFp, tH, errRs);

            MyLogger.writeMessage("Calling InputFileProcess to process Input file.", MyLogger.DebugLevel.DRIVER);
            // call of the InputFileProcess method in the InputDataprocessor.
            iDp.InputFileProcess();

            MyLogger.writeMessage("Calling ModifyFileProcess to process Modify file.", MyLogger.DebugLevel.DRIVER);
            // call of the ModifyFileProcess method in the InputDataprocessor.
            iDp.ModifyFileProcess();

            MyLogger.writeMessage("Calling storeTreeResult to store tree in result.", MyLogger.DebugLevel.DRIVER);
            // Call of the storeTreeResult method in the InputDataprocessor.
            tH.storeTreeResult();

            MyLogger.writeMessage("Calling printResults to print all trees in Stdout.", MyLogger.DebugLevel.DRIVER);
            MyLogger.writeMessage("Calling printResultsToFile to print all trees in respective output file.\n", MyLogger.DebugLevel.DRIVER);
            /**
             * printResults method for printing output on terminal
             * 
             * @params Three StdoutDisplayInterface results object.
             */
            printResults((StdoutDisplayInterface) rs0, (StdoutDisplayInterface) rs1, (StdoutDisplayInterface) rs2);
            /**
             * printResultsToFile method for printing output in output file.
             * 
             * @params Three FileDisplayInterface results object.
             */
            printResultsToFile((FileDisplayInterface) rs0, (FileDisplayInterface) rs1, (FileDisplayInterface) rs2,
                    (FileDisplayInterface) errRs);

        } catch (InvalidPathException | IOException | EmptyInputFileException | EmptyModifyFileException
                | InvalidInputFormatException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * printResults method to display output.
     * 
     * @param arr
     */
    private static void printResults(StdoutDisplayInterface... arr) {
        int i=0;
        for (StdoutDisplayInterface result : arr) {
            System.out.println("---------------------------------replica_"+i+" Tree--------------------------------------\n");
            result.writeToStdout();
            i += 1;
        }
    }

    /**
     * printResultsToFile method to write output in respective files.
     * 
     * @param arr
     * @throws IOException
     */
    private static void printResultsToFile(FileDisplayInterface... arr) throws IOException {
        for (FileDisplayInterface result : arr) {
            result.writeToFile();
        }
    }
}