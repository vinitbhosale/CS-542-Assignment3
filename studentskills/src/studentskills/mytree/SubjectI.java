package studentskills.mytree;

import studentskills.operation.Operation;

/**
 * SubjectI interface that intialize methods used by Node file.
 */
public interface SubjectI {
    void registerObserver(ObserverI observerI);

    void notifyAll(Operation operation);
}