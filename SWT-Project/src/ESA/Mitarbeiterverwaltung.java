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

public class Mitarbeiterverwaltung
{
	List<Mitarbeiter> mitarbeiterListe;
	
	public List<Mitarbeiter> getMitarbeiterListe() 
	{
		return mitarbeiterListe = new LinkedList<>();
	}

	public void setMitarbeiterListe(List<Mitarbeiter> mitarbeiterListe) 
	{
		this.mitarbeiterListe = mitarbeiterListe;
	}

	@SuppressWarnings("unchecked")
	public void laden(File MitarbeiterListe)
	{
		try (FileInputStream fis = new FileInputStream(MitarbeiterListe); ObjectInputStream ois = new ObjectInputStream(fis))
		{
			 mitarbeiterListe = (List<Mitarbeiter>) ois.readObject(); 
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
	
	public void  speichern (File MitarbeiterListe) 
	{
		try (FileOutputStream fos = new FileOutputStream(MitarbeiterListe); ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(mitarbeiterListe);
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
	
	public boolean ueberpruefen(String id, Long pass)
	{
		if(!mitarbeiterListe.isEmpty())
			for (int i = 0; i<mitarbeiterListe.size(); i++)
				if (mitarbeiterListe.get(i).getAnmeldeDaten().containsKey(id))
					if (mitarbeiterListe.get(i).getAnmeldeDaten().get(id).equals(pass))
						return true;
		return false;
	}
}
