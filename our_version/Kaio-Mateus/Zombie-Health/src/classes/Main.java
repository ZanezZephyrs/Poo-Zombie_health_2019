package classes;
import interfaces.*;

public class Main {

	public static void main(String[] args) {
		IDataSet dataset = new DataSetComponent();
		dataset.setDataSource("C:\\Users\\Mateus\\eclipse-workspace\\Zombie-Health\\src\\db\\zombie-health-cases500.csv");
		
		IPatient aPatient = new Patient();
		aPatient.connect(dataset);
	
		IDoctor cDoctor = new Doctor();
		cDoctor.connect(dataset);
		cDoctor.connect(aPatient);
		
		IConsulta umaConsulta = new Consulta();

		umaConsulta.connect(dataset);
		umaConsulta.connect(cDoctor);
		umaConsulta.connect(aPatient);

		umaConsulta.Diagnostico();
		
	}

}
