package studentskills.operation;

public enum Operation {

    INSERT("insert"),
    MODIFY("modify");

    private final String operationStrval;

    Operation(String operationStrval){
        this.operationStrval = operationStrval; 
    }

    public String getOperationVal(){
        return this.operationStrval;
    }
}