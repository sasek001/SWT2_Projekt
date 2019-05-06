package ESA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class Rechnung 
{
	Kunde k;
	Bestellung b;
	String summe;
	
	public Rechnung(Kunde k, Bestellung b, String summe) 
	{
		this.k = k;
		this.b = b;
		this.summe = summe;
	}

	public void ausgeben()
	{
		Date now = new Date();
		
		String rechnung = now + "\n\n";
		
		rechnung += k.getVorname() + " " + k.getNachname() + "\n" + k.getBday() + "\n"
				+ k.getStrasse() + " " + k.getHausnr() + "\n" + k.getPlz() + " " + k.getOrt() + "\n\n\n";
		
		rechnung += "Bestellung: \n\n";
		
		for(int i = 0; i<b.getBestellListe().size(); i++)
			rechnung += b.getBestellListe().get(i) + "\n";
		
		rechnung += "\nSumme:    " + summe;
		
		try(RandomAccessFile raf = new RandomAccessFile(new File("Rechnung" + rv.getRechnungsliste().size() + ".txt"), "rw"))
		{
			raf.write(rechnung.getBytes());
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		rv.getRechnungsliste().add(this);
	}
}
