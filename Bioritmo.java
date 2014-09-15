import java.util.Calendar;
import java.util.ArrayList;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
public class Bioritmo{
	private Calendar nascimento,actual;
	private ArrayList<Medicao> listaMedicao=new ArrayList<Medicao>();
	private ArrayList<Medicao> listaAjustados=new ArrayList<Medicao>();
	private Medicao ultima,ultimoAjuste;
	private double pFis =23;
	private double pEmo =28;
	private double pInte =33;
	private int diaspassados;
	
	
	
	public Bioritmo(long i){
		Calendar inicio = Calendar.getInstance();
		inicio.setTimeInMillis(i);
		Calendar actual = Calendar.getInstance();		
		nascimento=inicio;
		diaspassados=getDias(nascimento,actual);
		ultima=calcula(actual);
		ultimoAjuste=ultima;
		listaMedicao.add(ultima);
		//System.out.println("listaMedicao");
		//System.out.println("listaAjustados"); 
		
	}
	public Bioritmo(String fich){
	
		try{
			FileInputStream filestream = new FileInputStream(fich);
			ObjectInputStream objstream = new ObjectInputStream(filestream);
			listaMedicao = (ArrayList<Medicao>) objstream.readObject();
			
			listaAjustados = (ArrayList<Medicao>) objstream.readObject();
		
			objstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(listaMedicao.toString());
		Calendar actual = Calendar.getInstance();
		nascimento=actual;
		//se data do ultimo elemento na lista nao for igual data actual -> d nascimento = primeiro da lista
		if(((long)listaMedicao.get(listaMedicao.size() - 1).getData())!=(actual.getTimeInMillis())){
			nascimento.setTimeInMillis((long)listaMedicao.get(0).getData());
		}
		
		//se lista de ajustes nao vazia actualiza periodos com o ultimo da lista
		if(!listaAjustados.isEmpty()){
			ultimoAjuste=listaAjustados.get(listaAjustados.size() - 1);
			pFis =ultimoAjuste.getPfis();
			pEmo =ultimoAjuste.getPemo();
			pInte =ultimoAjuste.getPinte();	
		}
		Calendar hoje = Calendar.getInstance();
		diaspassados=getDias(nascimento,hoje);
		//System.out.println(hoje.getTime());
		ultima=calcula(hoje);
		ultimoAjuste=ultima;
	}
	

	private double formula(int dias, double periodo){
		return(Math.sin((2*Math.PI*dias)/periodo));
	}
	private int getDias(Calendar inicio,Calendar fim){
		return ((int)((fim.getTimeInMillis()-inicio.getTimeInMillis())/(1000*60*60*24)));
	}
	public Medicao calcula(Calendar data){
		
		Medicao m1=new Medicao(data.getTimeInMillis(),
				formula(getDias(nascimento,data),pFis),
				formula(getDias(nascimento,data),pEmo),
				formula(getDias(nascimento,data),pInte),
				pFis,pEmo,pInte);
		

		return(m1);
		
	}
	
	private Boolean ascendente(int diaspassados, double periodo){
		Boolean ascendente=new Boolean(true);
		double resto=diaspassados%periodo;
		double intervalo=periodo/4;
		if (resto>intervalo&&resto<=3*intervalo){
			ascendente=new Boolean(false);
		}else
		if (resto<=intervalo||resto>3*intervalo){
			ascendente=new Boolean(true);
		}
		return ascendente;
	}
	
	private Boolean aumenta(String s, Boolean ascendente){
		Boolean aumenta=new Boolean(true);
		//é ascendente
		if (ascendente){	
			//se melhor -> reduz periodo
			if (s.equals("+")){
				aumenta=new Boolean(false);
			}
			//se pior -> aumenta periodo
			else if(s.equals("-")){
				aumenta=new Boolean(true);
			}
		}
		//é descendente
		else {
			//se melhor -> aumenta periodo
			if (s.equals("+")){
				aumenta=new Boolean(true);
			}
			//se pior -> reduz periodo
			else if(s.equals("-")){
				aumenta=new Boolean(false);
			}
		}
		return aumenta;
	}
	
	public void ajusta(String input,String idperiodo){
		
		if(input.equals("=")){
		}else if (idperiodo.equals("fis")){
			
				if(aumenta(input,ascendente(diaspassados,pFis))){
					pFis++;
					ultimoAjuste.setPfis(pFis);
				}else {
					pFis--;
					ultimoAjuste.setPfis(pFis);
				}
				
			}else if(idperiodo.equals("emo")){
			
				if(aumenta(input,ascendente(diaspassados,pEmo))){
					pEmo++;
					ultimoAjuste.setPemo(pEmo);
				}else {
					pEmo--;
					ultimoAjuste.setPemo(pEmo);
				}
				
			}else if(idperiodo.equals("inte")){
			
				if(aumenta(input,ascendente(diaspassados,pInte))){
					pInte++;
					ultimoAjuste.setPinte(pInte);
				}else {
					pInte--;
					ultimoAjuste.setPinte(pInte);
				}
				
			}

	}
	
	public String getFisico(){
		return getResposta(ultima.getFis());
	}
	public String getEmocional(){
		return getResposta(ultima.getEmo());
	}
	public String getIntelectual(){
		return getResposta(ultima.getInte());
	}
	
	private String getResposta(double val){
		System.out.println("Valor especifico: "+val);
		String ret="";
		if (val>=0.80){
			 ret+="Excelente";
		}else if (val<0.80&&val>0.10){
			 ret+="Bem";
		}else if (val<=0.10&&val>=-0.10){
			 ret+="Normal";
		}else if (val<-0.10&&val>-0.80){
			 ret+="Mal";
		}else if (val<=-0.80){
			 ret+="Pessimo";
		}
		return ret;
	}
	public void save(){

		// se listaajustes vazia-> adiciona ultimo ajuste à lista
		if(listaAjustados.isEmpty()){
			listaAjustados.add(ultimoAjuste);
			//System.out.println("Ajustes vazia."+listaMedicao.toString());
		}else{
			Calendar aj=Calendar.getInstance();
			aj.setTimeInMillis((long)listaAjustados.get(listaAjustados.size() - 1).getData());
			// se dias passados igual aos dias passados do ultimo ajuste substitui ajuste
			if(getDias(nascimento,aj)==diaspassados){
				listaAjustados.set((listaAjustados.size() - 1),ultimoAjuste);
				//System.out.println("Ajustes no mesmo dia."+listaMedicao.toString());
			}
			else{
			//caso contrario adiciona novo ajuste
				listaAjustados.add(ultimoAjuste);
				//System.out.println("Ajustes novos."+listaMedicao.toString());
			}
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("f.saved"));
			outputStream.writeObject(listaMedicao);
			outputStream.writeObject(listaAjustados);
			outputStream.writeObject(null);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		System.out.println("\nlistaMedicao");
		System.out.println(listaMedicao.toString());
		System.out.println("\nlistaAjustados"); 
		System.out.println(listaAjustados.toString());
		*/
	}
	
}