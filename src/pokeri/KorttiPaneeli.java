package pokeri;

import java.awt.FlowLayout;
import java.awt.Point;
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
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(JPanel panel)
    {
        Point sijainti = panel.getLocation();

        kortitPane = new JPanel();
        toiminnotPane = new JPanel();

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        sijainti.setLocation(0, 25); // XXX kun toimii, poista tämä
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            poytakortit[i] = new Kortti(Extern.MAAT[i % 4], i + 1, sijainti);
            sijainti.setLocation(0, 25);
            kortitPane.add(poytakortit[i]);
        }

        // Toiminnot
        uusipeli = new JButton("Uusi peli");
        kuuntelija = new KorttipaneelinKuuntelija();
        uusipeli.addActionListener(kuuntelija);
        toiminnotPane.add(uusipeli);

        add(kortitPane);
        add(toiminnotPane);

        setPreferredSize(panel.getPreferredSize());
        setVisible(true);
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
            // TODO: Nollaa pakka, nollaa tilastot, nolla elämä
            System.out.println(String.format("Toteuta minut"));
        }
    }

}
