package studentskills.mytree;

import studentskills.operation.Operation;

public interface ObserverI {
    void update(StudentRecord st, Operation operations);
}