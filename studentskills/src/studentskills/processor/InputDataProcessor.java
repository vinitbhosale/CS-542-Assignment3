package studentskills.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.userException.EmptyInputFileException;
import studentskills.userException.EmptyModifyFileException;
import studentskills.userException.ErroFileException;
import studentskills.userException.InvalidInputFormatException;
import studentskills.util.FileProcessor;
import studentskills.util.ResultsI;

/**
 * InputDataProcessor class implements processing of each line and calls for
 * InputFileProcess and ModifyFileProcess methods, also implements
 * generateOutput method to generate tree output.
 */

public class InputDataProcessor {

    /**
     * Intializing object of FileProcessor for Input and Modify file.
     */
    private FileProcessor inputFp;
    private FileProcessor modifyFp;

    private String strData;
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    // Initializing Set foer skills.
    private Set<String> skills;
    // Intializing object of TreeHelper
    private TreeHelper tH;
    private String replicaNum;
    private int ModBNumber;
    private String origValue;
    private String newValue;
    private ResultsI errRs;

    /**
     * InputDataProcessor construst that initialize the instance variables.
     * 
     * @param inInputFp  - FileProcessor obj for Input file.
     * @param inModifyFp - FileProcessor obj for Modify file.
     * @param inTH       - TreeHelper obj.
     */
    public InputDataProcessor(FileProcessor inInputFp, FileProcessor inModifyFp, TreeHelper inTH, ResultsI inErrRs) {
        inputFp = inInputFp;
        modifyFp = inModifyFp;
        tH = inTH;
        errRs = inErrRs;

    }

    /**
     * InputFileProcess method implements processing of line in Input.txt file.
     * 
     * @throws IOException
     * @throws InvalidInputFormatException
     */
    public void InputFileProcess() throws IOException, EmptyInputFileException, InvalidInputFormatException {

        // Calling the poll method to fetch each line in input file.
        strData = inputFp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            throw new EmptyInputFileException("Empty Input File!");
        }
        // looping through input file.
        while (strData != null) {

            try {
                // Empty line condition.
                if (strData.length() == 0) {
                    throw new ErroFileException("Empty line in input file!");
                }
                // Condition to check the required format of line.
                if (strData.split(":").length == 1) {
                    throw new InvalidInputFormatException(
                            "Invalid Input! Line in the input file does not follow the specified format.");
                }
                String[] keyAndValue = strData.split(":");
                bNumber = Integer.parseInt(keyAndValue[0]);

                /**
                 * To check bnumber is negative and length is than 4.
                 */
                int bNumberLen = Integer.toString(bNumber).length();
                if (bNumber < 0 && bNumberLen > 4) {
                    throw new ErroFileException(bNumber+" Negative Bnumber/Not a 4 digit Bnumber read!");
                }

                // List creation for adding value after(:).
                ArrayList<String> innerValue = new ArrayList<String>();
                for (String value : keyAndValue[1].split(",")) {
                    innerValue.add(value);
                }
                // Assigning values of the list.
                firstName = innerValue.get(0);
                lastName = innerValue.get(1);
                gpa = Double.parseDouble(innerValue.get(2));
                major = innerValue.get(3);

                /**
                 * Process to check number of skills are not more tha 10.
                 */
                List<String> sub_List = innerValue.subList(4, innerValue.size());
                skills = new HashSet<String>(sub_List);
                if (skills.size() > 10) {
                    throw new ErroFileException(bNumber+" Skills cannot be more than 10!");
                }

                // Creating the student record.
                StudentRecord st = new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
                // Sending student record to built tree.
                tH.builTree(st);

                // Fetching the next line in the input file.
                strData = inputFp.poll();

            } catch (ErroFileException e) {
                errRs.storeResult(e.toString());
                // Fetching the next line in the input file.
                strData = inputFp.poll();
            }

        }

    }

    /**
     * ModifyFileProcess method implements processing of line in Modify.txt file.
     * 
     * @throws IOException
     * @throws EmptyModifyFileException
     * @throws InvalidInputFormatException
     */
    public void ModifyFileProcess() throws IOException, EmptyModifyFileException, InvalidInputFormatException {
        // Calling the poll method to fetch each line in modify file.
        strData = modifyFp.poll();
        // Condition to check Empty modify file.
        if (null == strData) {
            throw new EmptyModifyFileException("Empty Modify file!");
        }
        try{
                // looping through input file.
            while (strData != null) {
                // Empty line condition and continue reading next line.
                if (strData.length() == 0) {
                    strData = modifyFp.poll();
                }
                // Condition to check the required format of line.
                if (strData.split(":").length == 1) {
                    throw new InvalidInputFormatException("Invalid Input! Line in the input file does not follow the specified format.");
                }

                String[] keyAndValue = strData.split(":");
                newValue = keyAndValue[1];

                ArrayList<String> ModInnerValue = new ArrayList<String>();
                for (String value : keyAndValue[0].split(",")) {
                    ModInnerValue.add(value);
                }
                replicaNum = ModInnerValue.get(0);
                ModBNumber = Integer.parseInt(ModInnerValue.get(1));

                // Empty value after (:)
                if (newValue.isEmpty()) {
                    throw new ErroFileException(ModBNumber+" Modified value is empty!");
                }
                /**
                 * To check bnumber is negative and length is than 4.
                 */
                int ModBNumberLen = Integer.toString(ModBNumber).length();
                if (bNumber < 0 && ModBNumberLen > 4) {
                    throw new ErroFileException(bNumber+" Negative Bnumber/Bnumber more than 4 digit!");
                }
                origValue = ModInnerValue.get(2);

                tH.updateNode(replicaNum, ModBNumber, origValue, newValue);
                strData = modifyFp.poll();
            }
        }catch(ErroFileException e){
            errRs.storeResult(e.toString());
                // Fetching the next line in the input file.
                strData = modifyFp.poll();
        }
        
        
        

    }

    public void generateOutput() {
        tH.storeTreeResult();
    }

    @Override
    public String toString() {
        return "Class: InputDataProcessor, Data Members: [inputFp=" + inputFp.toString() + ", modifyFp ="
                + modifyFp.toString() + ", strData=" + strData.toString() + ", bNumber=" + bNumber + ", firstName="
                + firstName.toString() + ", lastName=" + lastName.toString() + ", gpa=" + gpa + ", major="
                + major.toString() + ", skills=" + skills.toString() + ", tH=" + tH.toString() + ", replicaNum="
                + replicaNum.toString() + ", ModBNumber=" + ModBNumber + ", origValue=" + origValue.toString()
                + ", newValue=" + newValue.toString() + "]";

    }
}