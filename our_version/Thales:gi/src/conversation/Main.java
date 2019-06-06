package conversation;
import datasource.*;
import interfaces.*;

public class Main {

	public static void main(String[] args) {
		
		DataSetComponent reader=new DataSetComponent();
		reader.setDataSource("zombie-health-cases500.csv");
		String[] instancias=reader.requestAttributes();
		Patient paciente=new Patient();
		paciente.connect(reader);
		Medico medico=new Medico();
		medico.connect(reader);
		medico.connect(paciente);
		medico.startInterview();


	}

}
