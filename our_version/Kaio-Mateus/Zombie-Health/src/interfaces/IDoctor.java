package interfaces;

import java.util.ArrayList;

public interface IDoctor extends IEnquirer, IResponderReceptacle, ITableProducerReceptacle {
    public ArrayList<String> getDiagnostic();
}