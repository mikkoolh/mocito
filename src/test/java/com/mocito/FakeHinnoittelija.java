package com.mocito;

public class FakeHinnoittelija implements IHinnoittelija {
    private float alennus;

    public FakeHinnoittelija(float alennus) {
        this.alennus = alennus;
    }

    public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
        return alennus;
    }

    @Override
    public void setAlennusProsentti(Asiakas asiakas, float f) {
        throw new UnsupportedOperationException("Unimplemented method 'setAlennusProsentti'");
    }

    @Override
    public void lopeta() {
        throw new UnsupportedOperationException("Unimplemented method 'lopeta'");
    }

    @Override
    public void aloita() {
        throw new UnsupportedOperationException("Unimplemented method 'aloita'");
    }
}