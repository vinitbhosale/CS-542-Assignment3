package studentskills.processor;

import java.io.IOException;

import studentskills.util.FileProcessor;

public class InputDataProcessor {

    private FileProcessor fp;
    private String strData;

    public InputDataProcessor(FileProcessor inFp) {
        fp = inFp;
    }

    public void process() throws IOException {
        
        strData = fp.poll();

        if(null == strData){
            System.err.println("Empty Input file");
        }
        
        while(strData != null){
            System.out.println(strData);
            strData = fp.poll();
        }
    }
    
}