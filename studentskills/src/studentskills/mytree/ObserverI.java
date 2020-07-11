package studentskills.mytree;

import studentskills.operation.Operation;

/**
 * ObserverI interface that initialize methods used by Node file.
 */
public interface ObserverI {
    void update(StudentRecord st, Operation operations);
}