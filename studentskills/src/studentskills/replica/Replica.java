package studentskills.replica;

public enum Replica {
    ZERO("0"),
    ONE("1"),
    TWO("2");

    private final String replicaNumString;

    Replica(String replicaNumString){
        this.replicaNumString = replicaNumString; 
    }

    public String getreplicaNumString(){
        return this.replicaNumString;
    }
}