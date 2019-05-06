package ESA;
import java.io.Serializable;

public class Kunde implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String nachname;
	private String bday;
	private String strasse;
	private int hausnr;
	private int plz;
	private String ort;
	
	public Kunde() 
	{

	}

	public Kunde(String vorname, String nachname, String bday, String strasse, int hausnr, int plz, String ort) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.bday = bday;
		this.strasse = strasse;
		this.hausnr = hausnr;
		this.plz = plz;
		this.ort = ort;
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

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getHausnr() {
		return hausnr;
	}

	public void setHausnr(int hausnr) {
		this.hausnr = hausnr;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String toString()
	{
		return vorname + " " + nachname + "; " + bday + "; " + strasse + " " + hausnr + ", " + plz + " " + ort;
	}
}
