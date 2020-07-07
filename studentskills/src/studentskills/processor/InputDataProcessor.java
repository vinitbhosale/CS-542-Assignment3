package studentskills.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import studentskills.mytree.Node;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;

public class InputDataProcessor {

    private FileProcessor inputFp;
    private FileProcessor modifyFp;
    private String strData;
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private Set<String> skills ;
    private List<TreeHelper> treeHelperLst;  

    public InputDataProcessor(FileProcessor inInputFp, FileProcessor inModifyFp, TreeHelper ...treeArr) {
        inputFp = inInputFp;
        modifyFp = inModifyFp;
        treeHelperLst = Arrays.asList(treeArr);
    }

    public void InputFileProcess() throws IOException {

        strData = inputFp.poll();

        if (null == strData) {
            System.err.println("Empty Input file");
        }

        while (strData != null) {
            if (strData.split(":").length == 1) {
                System.err.println("Invalid Input! Line in the input file does not follow the specified format.");
            }
            String[] keyAndValue = strData.split(":");
            bNumber = Integer.parseInt(keyAndValue[0]); 
            ArrayList<String> innerValue = new ArrayList<String>();
            for (String value : keyAndValue[1].split(",")) {
                innerValue.add(value);
            }
            firstName=innerValue.get(0);
            lastName=innerValue.get(1);
            gpa=Double.parseDouble(innerValue.get(2));
            major = innerValue.get(3);
            List<String> sub_List = innerValue.subList(4, innerValue.size());
            skills = new HashSet<String>(sub_List);
            if(skills.size()>10){
                System.err.println("Skills not more than 10");
            }
            Node replica_Node_0 = new Node(bNumber, firstName, lastName, gpa, major, skills);
            Node replica_Node_1 = replica_Node_0.clone();
            Node replica_Node_2 = replica_Node_0.clone();
            replica_Node_0.registerObserver(replica_Node_1);
            replica_Node_0.registerObserver(replica_Node_2);
            treeHelperLst.get(0).insertStudent(replica_Node_0);


            

            strData = inputFp.poll();
        }
    }   
    public void ModifyFileProcess() throws IOException {
        strData = modifyFp.poll();

        if (null == strData) {
            System.err.println("Empty Modify file");
        }

        while(strData != null){

        }
    }

}