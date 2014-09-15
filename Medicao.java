import java.io.Serializable;
public class Medicao implements Serializable{
	private long data;
	private double fis,emo,inte,pfis,pemo,pinte=0;
	public Medicao(long d,double f,double e,double i,double pf,double pe,double pi){
		data=d;
		fis=f;
		emo=e;
		inte=i;
		pfis =pf;
		pemo =pe;
		pinte =pi;
	}
	double getData(){
		return data;
	}
	double getFis(){
		return fis;
	}
	double getEmo(){
		return emo;
	}
	double getInte(){
		return inte;
	}
	double getPfis(){
		return pfis;
	}
	double getPemo(){
		return pemo;
	}
	double getPinte(){
		return pinte;
	}
	void setPfis(double p){
		pfis =p;
	}
	void setPemo(double p){
		pemo =p;
	}
	void setPinte(double p){
		pinte =p;
	}
	@Override
	public String toString(){
		return("\n**Medicao**\nData: "+data+"\nFisico:"+fis+"\nEmocional: "+emo+ "\nIntelectual: "+inte+ "\nPeriodoF: "+pfis+ "\nPeriodoE: "+pemo+"\nPeriodoI: "+pinte);
	
	}
}