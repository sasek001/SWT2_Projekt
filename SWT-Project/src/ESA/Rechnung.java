package ESA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rechnung implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Kunde k;
	Bestellung b;
	String summe;
	Rechnungsverwaltung rv;
	
	public Rechnung(Kunde k, Bestellung b, String summe, Rechnungsverwaltung rv) 
	{
		this.k = k;
		this.b = b;
		this.summe = summe;
		this.rv = rv;
	}

	public void ausgeben()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		Date now = new Date();
		
		String rechnung = format.format(now) + "\n\n";
		
		rechnung += k.getVorname() + " " + k.getNachname() + "\n" + k.getBday() + "\n"
				+ k.getStrasse() + " " + k.getHausnr() + "\n" + k.getPlz() + " " + k.getOrt() + "\n\n\n";
		
		rechnung += "Bestellung: \n\n";
		
		for(int i = 0; i<b.getBestellListe().size(); i++)
			rechnung += b.getBestellListe().get(i) + "\n";
		
		rechnung += "\nSumme:    " + summe;
		
		File file = new File("Rechnung" + rv.getRechnungsliste().size() + ".txt");
		file.delete();
		
		try(RandomAccessFile raf = new RandomAccessFile(file, "rw"))
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
