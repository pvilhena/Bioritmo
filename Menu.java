import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;
public class Menu{
	public static void main(String[] args) {	
		 String datain="";
		 String nomeficheiro="";
		 Bioritmo bio;
		if(args.length==0){

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Insira a data de nascimento (dd MM yyyy)\n");
				datain = reader.readLine();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1); 
			}
			
			SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
			Date d1= new Date();
			try {
			 d1 = df.parse(datain);
			} catch (ParseException e) {
				System.err.println("nao esta no fromato (dd MM yyyy)");
				System.exit(1); 
			}
			Calendar datanasc = Calendar.getInstance();
			datanasc.setTime(d1);
			long escolha=datanasc.getTimeInMillis();
			bio=new Bioritmo(escolha);
		}else{
			nomeficheiro=args[0];
			bio=new Bioritmo(nomeficheiro);
		}
		Boolean flag=new Boolean(true);
		String input="";

		System.out.println("\n\n*            *");
		System.out.println("** Bioritmo **");
		System.out.println("*            *\n\n\n");
		System.out.println("Os seus niveis sao: ");	
		System.out.println("\nFisico: "+bio.getFisico());
		while(flag){
			input=leResposta();
			if (input.equals("=")||input.equals("-")||input.equals("+")){
				bio.ajusta(input,"fis");
				flag=new Boolean(false);
			}else{
				System.out.println("Por favor insira ou = ou + ou -");
			}	
		}
		flag=new Boolean(true);
		
		
		System.out.println("\nEmocional: "+bio.getEmocional());
		while(flag){
			input=leResposta();
			if (input.equals("=")||input.equals("-")||input.equals("+")){
				bio.ajusta(input,"emo");
				flag=new Boolean(false);
			}else{
				System.out.println("Por favor insira ou = ou + ou -");
			}	
		}
		flag=new Boolean(true);
		
		System.out.println("\nIntelectual: "+bio.getIntelectual());
		while(flag){
			input=leResposta();
			if (input.equals("=")||input.equals("-")||input.equals("+")){
				bio.ajusta(input,"inte");
				flag=new Boolean(false);
			}else{
				System.out.println("Por favor insira ou = ou + ou -");
			}	
		}
		bio.save();
		
		System.out.println("\n*                        *");
		System.out.println("**Obrigado e ate amanha!**");
		System.out.println("*                        *\n\n\n");
		
	
	}
	private static String leResposta(){
		String input="";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Sente-se melhor(+), pior(-) ou igual(=)?\n");
			input = reader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}	
}