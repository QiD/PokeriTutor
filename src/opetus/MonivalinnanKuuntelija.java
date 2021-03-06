package opetus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import pokeri.Extern;
import raahauspeli.PokeriHanska;

public class MonivalinnanKuuntelija implements ActionListener
{
    private MonivalintaTaulu taulu;
    private Skenaario skenu;

    public MonivalinnanKuuntelija(MonivalintaTaulu mvt)
    {
        taulu = mvt;
    }

   @Override
    public void actionPerformed(ActionEvent e)
    {
        taulu.setOhjeTeksti("");

        if (e.getActionCommand().equals(Extern.UUSIMONIVALINTATEHTAVA)) {
            int uusiSkenu = 0;
            Random rnd = new Random();
            KasiTaulu kasiTaulu = new KasiTaulu(new PokeriHanska(6));

            taulu.removeAll();
            uusiSkenu = rnd.nextInt(Extern.SKENAARIOIDEN_LKM);
            while (uusiSkenu == taulu.getEdellinenSkenaarionNro()) {
                uusiSkenu = rnd.nextInt(Extern.SKENAARIOIDEN_LKM);
            }
            skenu = new Skenaario(uusiSkenu);
            taulu.setEdellinenSkenaarionNro(uusiSkenu);
            taulu.setAktiivinenSkenaario(skenu);
            for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
                kasiTaulu.setKorttiTaulussa(skenu.getKortti(i), i);
            }
            kasiTaulu.setValintaPoisPaalta();
            kasiTaulu.setBackground(Extern.TAIDOTTAUSTA);
            taulu.add(kasiTaulu);
            ValintaPaneeli vp = new ValintaPaneeli(taulu, skenu.getEkaHanska(),
                skenu.getTokaHanska(), skenu.getKolmasHanska());
            taulu.setValintaPaneeli(vp);
            taulu.add(vp);
            taulu.validate();
        } else if (e.getActionCommand().equals(Extern.MONIVALINTAVASTAUS)) {
            ValintaPaneeli vp = taulu.getValintaPaneeli();
            skenu = taulu.getAktiivinenSkenaario();

            System.out.println(vp.getVastaus());
            if (vp.getVastaus().equals(""))
                taulu.setOhjeTeksti("Et ole valinnut yhtään vaihtoehtoa.");
            else {
                PokeriHanska vastaus = new PokeriHanska(vp.getVastaus());

                if (vastaus.equals(skenu.getOikeaVastaus())) {
                    taulu.setOhjeTeksti(skenu.getOhjeTeksti());
                } else {
                    vp.setVastaaDisabled();
                    taulu.setOhjeTeksti("Väärä vastaus, kokeile uudelleen.");
                }
            }
        }
    }

}
