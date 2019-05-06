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

public class Rechnungsverwaltung 
{
	private List<Rechnung> rechnungsliste = new LinkedList<>();

	public Rechnungsverwaltung() 
	{
	}

	public Rechnungsverwaltung(List<Rechnung> rechnungsliste) 
	{
		this.rechnungsliste = rechnungsliste;
	}

	public List<Rechnung> getRechnungsliste() 
	{
		return rechnungsliste;
	}

	public void setRechnungsliste(List<Rechnung> rechnungsliste) 
	{
		this.rechnungsliste = rechnungsliste;
	}
	
	public void speichern(File file)
	{
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(rechnungsliste);
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
	
	public void laden(File file)
	{
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis))
		{
			rechnungsliste = (List<Rechnung>) ois.readObject();
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
}
