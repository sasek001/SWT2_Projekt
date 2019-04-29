package ESA;
import java.io.Serializable;

public class Kunde implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String nachname;
	private String bday;
	private String adresse;
	
	public Kunde() 
	{

	}

	public Kunde(String vorname, String nachname, String bday, String adresse) 
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.bday = bday;
		this.adresse = adresse;
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

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) 
	{
		this.bday = bday;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}
	
	public String toString()
	{
		return vorname + " " + nachname + "; " + bday + "; " + adresse;
	}
}
