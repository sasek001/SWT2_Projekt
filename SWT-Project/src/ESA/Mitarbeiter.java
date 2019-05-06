package ESA;
import java.io.Serializable;
import java.util.HashMap;

public class Mitarbeiter implements Serializable
{
	private static int nr;
	private static final long serialVersionUID = 1L;
	
	String vorname;
	String nachname;
	String id;
	HashMap<String, Long> anmeldeDaten;
	
	public Mitarbeiter() 
	{
		this.anmeldeDaten = new HashMap<>();
	}
	
	public Mitarbeiter(String vorname, String nachname, Long pass) 
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.id = "user" + nr++;
		this.anmeldeDaten = new HashMap<>();
		anmeldeDaten.put(id, pass);
	}

	
	public String getVorname() 
	{
		return vorname;
	}

	public void setVorname(String vorname) 
	{
		this.vorname = vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public void setNachname(String nachname) 
	{
		this.nachname = nachname;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public HashMap<String, Long> getAnmeldeDaten()  
	{
		return anmeldeDaten;
	}

	public void setAnmeldeDaten(HashMap<String, Long> anmeldeDaten)
	{
		this.anmeldeDaten = anmeldeDaten;
	}
}
