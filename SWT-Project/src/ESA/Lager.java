package ESA;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class Lager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<Artikel> artListe;
	private Bestellung bestellung;
	
	public Lager()
	{
		artListe = new LinkedList<>();
		bestellung = new Bestellung();
		artListe.add(new Artikel("Kamera", 25, 5));
		artListe.add(new Artikel("Handy", 50, 10));
	}

	public Lager(LinkedList<Artikel> artListe) 
	{
		super();
		this.artListe = artListe;
	}

	public List<Artikel> getArtListe()
	{
		return artListe;
	}

	public void setArtListe(List<Artikel> artListe)
	{
		this.artListe = artListe;
	}
	
	public Bestellung getBestellung() 
	{
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung)
	{
		this.bestellung = bestellung;
	}

	public void hinzufuegen(Artikel art)
	{
		boolean vorhanden = false;
		int index = 0;
		for (int i = 0; i<artListe.size(); i++)
			if(artListe.get(i).getName()==art.getName())
			{
				index = i;
				vorhanden = true;
			}
		
		if (vorhanden == true)
		{
			artListe.set(index, new Artikel(artListe.get(index).getName(),artListe.get(index).getPreis(),artListe.get(index).getMenge()+1));
		}
		else
		{
			artListe.add(new Artikel(art.getName(), art.getPreis(), 1));
		}
	}
	
	public void entfernen(Artikel art)
	{
		int index = 0;
		
		for (int i = 0; i<artListe.size(); i++)
			if(art.getName()==artListe.get(i).getName())
				index = i;
		
		if (art.getMenge()>1)
		{
			artListe.set(index, new Artikel(art.getName(), art.getPreis(), art.getMenge()-1));
		}
		else
		{
			artListe.remove(index);
		}
	}
	
	public void lagerZuBestellung(Artikel art)
	{
		bestellung.hinzufuegen(art);
		
		entfernen(art);
	}
	
	public void bestellungZuLager (Artikel art)
	{
		hinzufuegen(art);
		bestellung.entfernen(art);
	}
	
	@SuppressWarnings("unchecked")
	public void laden(File file)
	{
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis))
		{
			 artListe = (List<Artikel>) ois.readObject(); 
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
	
	public void  speichern (File file) 
	{
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(artListe);
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
