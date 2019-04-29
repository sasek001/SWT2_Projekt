package ESA;
import java.io.Serializable;

public class Artikel implements Serializable
{
	private static final long serialVersionUID = 1L;


	private String name;
	

	private double preis;
	private int menge;
	
	public Artikel() 
	{
		
	}
	public Artikel(String name, double preis, int menge) 
	{
		this.name = name;
		this.preis = preis;
		this.menge = menge;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getPreis() 
	{
		return preis;
	}

	public void setPreis(double preis) 
	{
		this.preis = preis;
	}
	
	public int getMenge()
	{
		return menge;
	}
	
	public void setMenge(int menge) 
	{
		this.menge = menge;
	}
}
