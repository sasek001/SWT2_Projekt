package ESA;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Kundenverwaltung 
{	
	List<Kunde> kundenListe;

	public Kundenverwaltung()
	{
		kundenListe = new LinkedList<>();
	}
	
	public Kundenverwaltung(List<Kunde> kundenListe) 
	{
		super();
		this.kundenListe = kundenListe;
	}

	public List<Kunde> getKundenListe() 
	{
		return kundenListe;
	}

	public void setKundenListe(List<Kunde> kundenListe) 
	{
		this.kundenListe = kundenListe;
	}
	
	public boolean hinzufuegen(Kunde kunde)
	{
		if(!kundenListe.contains(kunde))
		{
			kundenListe.add(kunde);
			return true;
		}	
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void laden(File Kundenliste)
	{
		try (FileInputStream fis = new FileInputStream(Kundenliste); ObjectInputStream ois = new ObjectInputStream(fis))
		{
			 kundenListe = (List<Kunde>) ois.readObject(); 
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void  speichern (File Kundenliste) 
	{
		try (FileOutputStream fos = new FileOutputStream(Kundenliste); ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(kundenListe);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
