package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GiocoGUI2 extends JFrame {

	private final MorraCinese morra;
	private final JLabel esito;
	private final JLabel[] dati;
	private final ImageIcon[] icone;

	final Font fontTitolo = new Font(Font.DIALOG, Font.BOLD, 35);
	final Font fontMossa = new Font(Font.DIALOG, Font.BOLD, 22);
	final Font fontCapo = new Font(Font.DIALOG, Font.BOLD, 18);
	final Font fontDefault = new Font(Font.DIALOG, Font.BOLD, 15);
	final Font fontProduttore = new Font(Font.DIALOG, Font.ITALIC, 15);

	class Calcola implements ActionListener {
		private JButton[] mosse;
		

		mosse[0].addActionListener(new ActionListener() {
		@Override 
		public void actionPerformed(ActionEvent e) {
			morra.setMossa(0);
		}
		});
		
		
		

/*		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(mosse[0]))
				morra.setMossa(0);
			else if (event.getSource().equals(mosse[1]))
				morra.setMossa(1);
			else if (event.getSource().equals(mosse[2]))
				morra.setMossa(2);
			else if (event.getSource().equals(mosse[3]))
				morra.setMossa(3);
			else if (event.getSource().equals(mosse[4]))
				morra.setMossa(4);
*/
			morra.setRand();
			esito.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			String esitoMorra = morra.esitoMorra();
			esito.setText(esitoMorra);

			JLabel testo = new JLabel();
			testo.setFont(fontDefault);

			if (esitoMorra.equals("vittoria")) {
				testo = new JLabel("<html>Congratulazioni! Hai vinto. <br>" + "Il computer ha tirato " + morra.getRand()+ ".</html>");
				testo.setFont(fontDefault);
				JOptionPane.showMessageDialog(new JFrame(), testo, "Esito morra", JOptionPane.INFORMATION_MESSAGE,icone[5]);
			} else if (esitoMorra.equals("sconfitta")) {
				testo = new JLabel("<html>Che peccato! Hai perso. <br>" + " Il computer ha tirato " + morra.getRand()+ ".</html>");
				testo.setFont(fontDefault);
				JOptionPane.showMessageDialog(new JFrame(), testo, "Esito morra", JOptionPane.INFORMATION_MESSAGE,icone[7]);
			} else {
				testo = new JLabel("<html>Accidenti! Hai pareggiato. <br>" + " Il computer ha tirato " + morra.getRand()+ ".</html>");
				testo.setFont(fontDefault);
				JOptionPane.showMessageDialog(new JFrame(), testo, "Esito morra", JOptionPane.INFORMATION_MESSAGE,icone[6]);
			}

			aggiornaDati();
		}
	}

	public static ImageIcon[] setIcone() {
		ImageIcon[] icone = new ImageIcon[] { new ImageIcon("img/rock.png"), new ImageIcon("img/paper.png"),
				new ImageIcon("img/scissor.png"), new ImageIcon("img/lizard.png"), new ImageIcon("img/spock.png"),
				new ImageIcon("img/rock.png"), new ImageIcon("img/rock.png"), new ImageIcon("img/rock.png") };
		for (ImageIcon img : icone) {
			Image immagine = img.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			img = new ImageIcon(immagine);
		}
		return icone;
	}

	public GiocoGUI2() {
		super("Morra Cinese");
		setSize(835, 482);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rock_1faa8.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		JPanel intestazione = new JPanel();
		intestazione.setBorder(new EmptyBorder(20, 0, 10, 0));

		JLabel titolo = new JLabel("Morra Cinese");
		titolo.setFont(fontTitolo);

		intestazione.add(titolo);

		pane.add(intestazione, BorderLayout.NORTH);

		JPanel centro = new JPanel();

		centro.setLayout(new GridLayout(0, 1));

		morra = new MorraCinese();

		icone = GiocoGUI2.setIcone();

		JLabel scegli = new JLabel("Scegli la tua mossa");
		scegli.setFont(fontMossa);
		scegli.setHorizontalAlignment(JLabel.CENTER);
		centro.add(scegli);

		@SuppressWarnings("unused")
		ActionListener listener = new Calcola();
		final String[] imgPath = new String[] { "lizard", "spock", "scissor", "paper", "rock" };

		ImageIcon[] icone = new ImageIcon[imgPath.length];
		JButton[] mosse = new JButton[imgPath.length];
		JPanel bottoni = new JPanel();

		for (int i = 0; i < imgPath.length; i++) {
			icone[i] = new ImageIcon("img/%s.png".formatted(imgPath[i]));
			icone[i].getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			mosse[i] = new JButton(icone[i]);
			mosse[i].addActionListener(e -> new Calcola());
			mosse[i].setBackground(new Color(0xffffff));
			mosse[i].setOpaque(false);
			mosse[i].setOpaque(false);
			mosse[i].setBorderPainted(false);
			mosse[i].setBorder(null);
			bottoni.add(mosse[i]);
		}

		bottoni.setBorder(new EmptyBorder(0, 50, 100, 50));
		centro.add(bottoni);

		pane.add(centro, BorderLayout.CENTER);

		dati = new JLabel[4];

		JPanel stats = new JPanel();

		stats.setPreferredSize(new Dimension(180, 0));
		stats.setLayout(new GridLayout(0, 1));
		stats.setBorder(new EmptyBorder(5, 30, 5, 20));

		JLabel titoloStats = new JLabel("Statistiche");
		titoloStats.setFont(fontCapo);
		stats.add(titoloStats);
		for (int i = 0; i < dati.length; i++) {
			dati[i] = new JLabel();
			dati[i].setFont(fontDefault);
			stats.add(dati[i]);
		}
		aggiornaDati();

		JButton Azzera = new JButton("Azzera");
		Azzera.addActionListener(e -> morra.azzera());
		Azzera.setBackground(Color.WHITE);
		Azzera.setFont(fontDefault);
		stats.add(Azzera);

		pane.add(stats, BorderLayout.WEST);

		esito = new JLabel();

		JPanel fine = new JPanel();
		fine.setBorder(new EmptyBorder(10, 30, 10, 30));
		fine.setLayout(new BorderLayout());

		JLabel produttore = new JLabel("La Trinità");
		produttore.setFont(fontProduttore);
		fine.add(produttore, BorderLayout.WEST);

		class Uscita implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}
		JButton esci = new JButton("Esci");
		esci.setBackground(Color.WHITE);
		esci.addActionListener(new Uscita());
		esci.setFont(fontDefault);
		fine.add(esci, BorderLayout.EAST);

		pane.add(fine, BorderLayout.SOUTH);

		setVisible(true);
	}

	public void aggiornaDati() {
		dati[0].setText("Vittorie: " + morra.getVinte() + ", " + morra.getPerVinte() + "%");
		dati[1].setText("Sconfitte: " + morra.getPerse() + ", " + morra.getPerPerse() + "%");
		dati[2].setText("Pareggi: " + morra.getPareggi() + ", " + morra.getPerPareggi() + "%");
		dati[3].setText("Totali: " + morra.getTotali());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiocoGUI2 frame = new GiocoGUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
