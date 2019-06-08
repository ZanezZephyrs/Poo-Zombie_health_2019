package factory;

import date_analysis.DateAnalysis;
import generaldiagnosis.GeneralDiagnosisComponent;
import matriz_analysis.*;
import matriz_modifier.*;



public class Zhealth_Factory{
	
	public static GeneralDiagnosisComponent createGeneralDiagnosis(){
		return new GeneralDiagnosisComponent();		
	}
	public static MatrizAnalysis createMatrizAnalysis(){
		return new MatrizAnalysis();	
	}
	public static MatrizModifier createMatrizModifier(){
		return new MatrizModifier();	
	}
	public static DateAnalysis createDate_Analysis(){
		return new DateAnalysis();	
	}	
}
