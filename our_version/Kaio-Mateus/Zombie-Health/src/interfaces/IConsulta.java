package interfaces;

public interface IConsulta extends ITableProducerReceptacle{
	public void connect(IDoctor Doctor);
	/* Conecta com o Doutor */
	/* StartInterview */
	public void connect(IPatient Patient);
	/* Conecta com o Paciente */
	public void Diagnostico();
	/* Escolhe o melhor Diagnostico(Opcao que há mais casos registrados) */
	/* Executa o finalAnswer */
}


