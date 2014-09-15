import java.util.Calendar;
import java.util.ArrayList;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
public class Teste{
	
	int periodo=28;
	public double calcula(int dias, int periodo){
	 	double a= Math.sin(2*Math.PI*dias/periodo);
		return a;
	}
	public static void main(String[] args){
		ArrayList<Medicao> listaMedicao=new ArrayList<Medicao>();
		ArrayList<Medicao> listaAjustes=new ArrayList<Medicao>();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(1987, Calendar.DECEMBER, 24);
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(1989, Calendar.DECEMBER, 24);
		Calendar calendar = Calendar.getInstance();
		//System.out.println(calendar.get(Calendar.DATE));
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		//System.out.println(calendar.getTime());
		//System.out.println(calendar1.getTime());
		//System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		 	
		 	Calendar calendar3=Calendar.getInstance();
			calendar3.setTimeInMillis(calendar1.getTimeInMillis());
			//System.out.println( calendar3.getTime()	);
			int dias= (int)((calendar.getTimeInMillis()-calendar1.getTimeInMillis())/ (1000 * 60 * 60 * 24) );
			//System.out.println(dias);
			
			Medicao a=new Medicao(calendar1.getTimeInMillis(),0.1,0.2,0.3,23,28,30);
			Medicao b=new Medicao(calendar2.getTimeInMillis(),0.31,0.12,0.13,25,28,29);
			listaMedicao.add(a);
			listaMedicao.add(b);
			Medicao c=new Medicao(calendar1.getTimeInMillis(),0.1,0.2,0.3,23,28,30);
			Medicao d=new Medicao(calendar2.getTimeInMillis(),0.01,0.02,0.03,26,27,30);
			listaAjustes.add(c);
			listaAjustes.add(d);
			System.out.println(listaMedicao.toString());

			
		//int diffInDays = (int)( (calendar.getTime() - calendar1.getTime())/ (1000 * 60 * 60 * 24) );
		//int days = Days.daysBetween(date1, date2).getDays();
		//System.out.println(days);
		ObjectOutputStream outputStream = null;
        
        try {
            
            outputStream = new ObjectOutputStream(new FileOutputStream("f.saved"));
            outputStream.writeObject(listaMedicao);
			outputStream.writeObject(listaAjustes);
			outputStream.writeObject(null);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		ArrayList<Medicao> listaMedicao1=new ArrayList<Medicao>();
		ArrayList<Medicao> listaMedicao3=new ArrayList<Medicao>();
		try{
		FileInputStream fis = new FileInputStream("f.saved");
		ObjectInputStream ois = new ObjectInputStream(fis);
		listaMedicao1 = (ArrayList<Medicao>) ois.readObject();
		listaMedicao3 = (ArrayList<Medicao>) ois.readObject();  
		ois.close();
		} catch (Exception ex) {
         ex.printStackTrace();
		 }
		 System.out.println(listaMedicao1.get(0).getEmo());
		 System.out.println(listaMedicao1.get(listaMedicao1.size() - 1));
		System.out.println("listaMedicao");
		

	}
} 	