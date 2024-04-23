package com.mocito;

public interface IHinnoittelija {
	public abstract float getAlennusProsentti(Asiakas asiakas, Tuote tuote);

	public abstract void setAlennusProsentti(Asiakas asiakas, float f);

    public abstract void lopeta();

    public abstract void aloita();
}
