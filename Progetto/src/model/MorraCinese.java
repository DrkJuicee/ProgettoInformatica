package model;
import java.util.Random;
public class MorraCinese {
	
    final private String[] MOSSE = {"lizard", "spock", "forbice","carta","sasso"};
    private int mossa;
    private int rand;

    private int perse;
    private int vinte;
    private int pareggi;

    public MorraCinese() {
        mossa = -1;
        rand = -1;

        perse = 0;
        vinte = 0;
        pareggi = 0;
    }

    public void setMossa(int mossa) {
        this.mossa = mossa;
    }

    public void setRand() {
        Random gen = new Random();
        rand = gen.nextInt(5);
    }

    public String esitoMorra() {
        String pareggioText = "pareggio";
        String vittoriaText = "vittoria";
        String sconfittaText = "sconfitta";
        if (mossa >= 0 && mossa < 3 && rand >= 0 && rand < 3) {
           if(rand == (mossa + 1)%5 || rand == (mossa + 3)%5) {
        	   	vinte++;
        	   	return vittoriaText;
           }else if(rand == mossa) {
        	   pareggi++;
        	   return pareggioText;
           }else {
        	   perse++;
        	   return sconfittaText;
           }
        }
        return "Benvenuto";
    }

    public void azzera() {
        mossa = -1;
        rand = -1;

        perse = 0;
        vinte = 0;
        pareggi = 0;
    }

    public String getMossa() {
        return MOSSE[mossa];
    }

    public String getRand() {
        return MOSSE[rand];
    }


    public void setVinte(int vinte) {
        this.vinte = vinte;
    }

    public int getVinte() {
        return vinte;
    }

    public int getPerVinte() {
        double tot = vinte + perse + pareggi;
        return (int) ((vinte / tot) * 100);
    }

    public void setPerse(int perse) {
        this.perse = perse;
    }

    public int getPerse() {
        return perse;
    }

    public int getPerPerse() {
        double tot = vinte + perse + pareggi;
        return (int) ((perse / tot) * 100);
    }

    public void setPareggi(int pareggi) {
        this.pareggi = pareggi;
    }

    public int getPareggi() {
        return pareggi;
    }

    public int getPerPareggi() {
        double tot = vinte + perse + pareggi;
        return (int) ((pareggi / tot) * 100);
    }
    
    public int getTotali() {
        return vinte + perse + pareggi;
    }
}
