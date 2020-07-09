package studentskills.mytree;

import studentskills.operation.Operation;

public interface SubjectI {
    void registerObserver(ObserverI observerI);
    void notifyAll(Operation operation);
}