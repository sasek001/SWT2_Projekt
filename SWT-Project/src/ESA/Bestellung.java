package ESA;
import java.util.LinkedList;
import java.util.List;

public class Bestellung 
{
	private List<Artikel> bestellListe;

	public Bestellung()
	{
		bestellListe = new LinkedList<>();
	}
	
	public Bestellung(List<Artikel> bestellListe) 
	{
		super();
		this.bestellListe = bestellListe;
	}

	public List<Artikel> getBestellListe() 
	{
		return bestellListe;
	}

	public void setBestellListe(List<Artikel> bestellListe)
	{
		this.bestellListe = bestellListe;
	}
	
	public void hinzufuegen (Artikel art)
	{
		boolean vorhanden = false;
		int index = 0;
		
		for (int i = 0; i<bestellListe.size(); i++)
			if (art.getName() == bestellListe.get(i).getName())
			{
				vorhanden = true;
				index = i;
			}

		if (vorhanden == true)
		{
			bestellListe.set(index, new Artikel (bestellListe.get(index).getName(), bestellListe.get(index).getPreis(), bestellListe.get(index).getMenge()+1));
		}
		else
		{
			bestellListe.add(new Artikel(art.getName(), art.getPreis(), 1));
		}
	}
	
	public void entfernen(Artikel art)
	{
		int index = 0;
		
		for (int i = 0; i<bestellListe.size(); i++)
			if(bestellListe.get(i).getName()==art.getName())
			{
				index = i;
			}
		
		if (bestellListe.get(index).getMenge() > 1)
		{
			bestellListe.set(index, new Artikel(bestellListe.get(index).getName(), bestellListe.get(index).getPreis(), bestellListe.get(index).getMenge()-1));
		}
		else
		{
			bestellListe.remove(index);
		}
	}
}
