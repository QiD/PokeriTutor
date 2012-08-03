package pokeri;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class KorttiPaneeli extends JPanel
{
    private JButton uusipeli;
    private JPanel  kortitPane;
    private JPanel  toiminnotPane;
    private KorttipaneelinKuuntelija kuuntelija;
    private Pakka    pakka;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli()
    {
        kortitPane = new JPanel();
        toiminnotPane = new JPanel();

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        pakka = Pakka.getPakka();
        alustaKortit();

        // Toiminnot
        uusipeli = new JButton("Uusi peli");
        kuuntelija = new KorttipaneelinKuuntelija();
        uusipeli.addActionListener(kuuntelija);
        toiminnotPane.add(uusipeli);

        add(kortitPane);
        add(toiminnotPane);
        kortitPane.setSize(getMaximumSize());
        toiminnotPane.setSize(getMaximumSize());

        setVisible(true);
    }

    public void alustaKortit()
    {
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            Kortti k = pakka.otaKortti();

            k.setLocation(0, 25);
            poytakortit[i] = k;
            kortitPane.add(poytakortit[i]);
        }
        /*
        Random rand = new Random();
        Point sijainti = new Point();

        sijainti.setLocation(0, 25); // XXX kun toimii, poista tämä
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            int randArvo = rand.nextInt(Extern.KORTTEJA_MAASSA) + 2 % Extern.KORTTEJA_MAASSA;
            int randMaa = rand.nextInt(Extern.MAIDEN_LKM);
            poytakortit[i] = new Kortti(Extern.MAAT[randMaa], randArvo, sijainti);
            sijainti.setLocation(0, 25);
            kortitPane.add(poytakortit[i]);
        }
        */
        revalidate();
    }

    public void merkkaaValituksi(Kortti k)
    {
        if (k.getValinta() == false) {
            k.toggleValinta();
            k.repaint();
        }
    }

    public class KorttipaneelinKuuntelija implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("Uusi peli")) {
                // TODO: Nollaa pakka, nollaa tilastot, nolla elämä
                kortitPane.removeAll();
                poytakortit = null;
                poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
                pakka = Pakka.uusiPakka();
                pakka.sekoita();
                alustaKortit();
            }
        }
    }

}
