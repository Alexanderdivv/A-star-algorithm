import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class GameFrame{
	String q1="", q2="", q3="", q4="", q5="", q6="";//variabel q, digunakan untuk memindahkan angka hasil random number kedalam objek
	private JFrame frame;
	int sumPemain=0, sumAI=0, q=2, g=0, n=6;//sum, digunakan untuk perhitungan penjumlahan masing-masing. q(pergantian waktu main),g(total koin dari awal-
	public int[] angka = new int[n];		//hingga state saat ini, n(banyak koin yang dimainkan).
	//array angka akan dijadikan tempat sebagai penampungan nilai semua koin
	//main program.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();	
	}
	//bagian deklarasi dan inisialisai semua bagian objek dan proses yang digunakan pada frame program	
	public void initialize() {
		frame = new JFrame();	//deklarasi frame
		frame.getContentPane().setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
		frame.setBounds(100, 100, 663, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHasilai = new JLabel("");	//label yang akan digunakan sebagai tampilan total koin yang didapatkan AI
		lblHasilai.setBounds(179, 247, 46, 14);//style
		frame.getContentPane().add(lblHasilai);
		
		JButton btnYou2 = new JButton("");	//button untuk menampilkan nilai kedua yang diambil oleh player pada barisan koin.
		btnYou2.setEnabled(false);	//diset disable karena button akan digunakan sebagai penampil dari hasil pilihan kumpulan button koin
		btnYou2.setBounds(444, 201, 48, 35);	//style
		frame.getContentPane().add(btnYou2);
		
		JButton btnYou3 = new JButton("");	//button untuk menampilkan nilai ketiga yang diambil oleh player pada barisan koin.
		btnYou3.setEnabled(false);	//set disable karena button tidak akan mendapatka action seperti klik dsb
		btnYou3.setBounds(492, 201, 48, 35);	//style
		frame.getContentPane().add(btnYou3);
		
		JLabel lblhasilPmain = new JLabel("");
		lblhasilPmain.setBounds(465, 247, 46, 14);
		frame.getContentPane().add(lblhasilPmain);
				
		JButton btnYou1 = new JButton("");	//button untuk menampilkan nilai pertama yang diambil oleh player dari barisan koin.
		btnYou1.setEnabled(false);	//set disable
		btnYou1.setBounds(396, 201, 48, 35);	//style(ukuran&jarak)
		frame.getContentPane().add(btnYou1);
		
		JLabel lblResult = new JLabel("");	//label Result akan digunakan untuk menampilkan nilai hasil akhir dari permainan.
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);//style
		lblResult.setBounds(218, 272, 217, 56);
		frame.getContentPane().add(lblResult);
		
		JButton btnAI2 = new JButton("");//button ini akan digunakan sebagai penampil hasil pilihan koin kedua oleh AI.
		btnAI2.setEnabled(false);	//sama seperti player, diset disable karena hanya digunakan sebagai penanda koin yang diambil.
		btnAI2.setBounds(157, 201, 48, 35);		//style
		frame.getContentPane().add(btnAI2);
		
		JButton btnAI3 = new JButton("");	//button ini akan digunakan sebagai penampil hasil pemilihan koin ketiga oleh AI.
		btnAI3.setEnabled(false);	//set disable
		btnAI3.setBounds(205, 201, 48, 35);	//style
		frame.getContentPane().add(btnAI3);
		
		JButton btnAI1 = new JButton("");	//button ini akan diguknakan sebagai penampil pilihan pertama koin oleh AI dari barisan koin diatas.
		btnAI1.setEnabled(false);	//set disable, karena hanya digunakan untuk tampilan
		btnAI1.setBounds(109, 201, 48, 35);	//style
		frame.getContentPane().add(btnAI1);
		//deklarasi button koin
		JButton btn1 = new JButton("");	//button urutan pertama yang digunakan dalam kumpulan barisan koin yang ikut dalam permainan.
		JButton btn2 = new JButton("");	//button urutan kedua yang digunakan dalam kumpulan barisan koin yang ikut dalam permainan.
		JButton btn3 = new JButton("");	//button urutan ketiga yang digunakan dalam kumpulan barisan koin yang masuk dalam permainan.
		JButton btn4 = new JButton("");	//button urutan keempat dalam permainan
		JButton btn5 = new JButton("");	//button urutan kelima
		JButton btn6 = new JButton("");	//button urutan keenam
		btn1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btn1.setEnabled(false);
			}
		});
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	//event untuk button ke-1 saat mouse diklik
				if(btnYou1.getText()==""){				//mengklik button berarti mengambil nilai koin pertaama dari barisan 
					btnYou1.setText(btn1.getText());	//periksa kolom untuk koin yang diambil.
				}else if(btnYou2.getText()==""){		//isikan pada kolom yang kosong
					btnYou2.setText(btn1.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn1.getText());
				}
				sumPemain+=Integer.parseInt(btn1.getText());		//tampung nilai yang diambil pada sumPemain untuk dijumlahkan,dan dibandingkan 
				lblhasilPmain.setText(String.valueOf(sumPemain));	//nantinya dengan hasil dari total pilihan oleh AI
				angka[0]=0;								//untuk urutan yang diambil dari barisan koin, set nilainya menjadi 0
				q++;									//q(urutan pergantaian main antara AI dengan player).
				if(q%2!=0&& q<6){						//urutan untuk AI bermain adalah ganjil.
					int[] balik=pilihanAI(angka,g);		//fungsi pilihanAI akan mengembalikan 2 nilai, untuk menampungnya gunakan array balik
					switch(balik[1]){								
					case 0: btn1.setEnabled(false);angka[0]=0;break;	//tombol yang dipilih akan deiset disable dan isi nya akan diset 0
					case 1: btn2.setEnabled(false);angka[1]=0;break;	//karna permainan melawan manusia, 
					case 2: btn3.setEnabled(false);angka[2]=0;break;	//gunakan looping karena akan dicek keseluruhan nilai yang dipilih AI
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;						
					sumAI=balik[0];					//hasil pilihan ai, akan disimpan ke sumAI.
					g+=sumAI;						//pilihan ai, akan dijumlahkan menjadi ongkos/g(n)
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));	//periksa masing-masing kolom yang tersedia untuk dimasukkan nilai dari pilihanAI
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));		//total ditampilkan 
					//untuk hasil akhir kemungkinan ada di button 1 atau button ke 6
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
									lblResult.setText("Totalnya sama loh. Semangat !");
					}
				btn1.setEnabled(false);		//setelah dipilih,  button akan didisable
			}
		});
		
		btn1.setFont(UIManager.getFont("Button.font"));
		btn1.setBounds(186, 105, 48, 35);
		frame.getContentPane().add(btn1);
		
		btn2.addMouseListener(new MouseAdapter() {
			@Override											//button 2 
			public void mouseClicked(MouseEvent e) {			//sama seperti button 1
				if(btnYou1.getText()==""){
					btnYou1.setText(btn2.getText());
				}else if(btnYou2.getText()==""){
					btnYou2.setText(btn2.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn2.getText());
				}
				btn2.setEnabled(false);
				sumPemain+=Integer.parseInt(btn2.getText());
				lblhasilPmain.setText(String.valueOf(sumPemain));
				angka[1]=0;
				q++;
				if(q%2!=0&& q<6){										//AI akan memilih langsung memilih setelah pemain memilih dengan meng-kliik
					int[] balik=pilihanAI(angka,g);						//salah satu button. 
					switch(balik[1]){
					case 0: btn1.setEnabled(false);angka[0]=0;break;
					case 1: btn2.setEnabled(false);angka[1]=0;break;
					case 2: btn3.setEnabled(false);angka[2]=0;break;
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;
					sumAI=balik[0];
					g+=sumAI;
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
						lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Totalnya sama loh. Semangat !");
				}
			}
		});
		btn2.setFont(UIManager.getFont("Button.font"));
		btn2.setBounds(234, 105, 48, 35);
		frame.getContentPane().add(btn2);

		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {						//button 3
				if(btnYou1.getText()==""){										
					btnYou1.setText(btn3.getText());
				}else if(btnYou2.getText()==""){
					btnYou2.setText(btn3.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn3.getText());
				}
				btn3.setEnabled(false);
				sumPemain+=Integer.parseInt(btn3.getText());		//nilai dalam sumPemain akan terus bertambah hingga q>5 
				lblhasilPmain.setText(String.valueOf(sumPemain));
				angka[2]=0;											//set nilai dalam array menjadi 0 setelah nilai koin diambil
				q++;
				if(q%2!=0&& q<6){									//jika ganjil, maka AI akan mengambil nilai secara otomatis dengan fungsi yang 
					int[] balik=pilihanAI(angka,g);					//disediakan
					switch(balik[1]){
					case 0: btn1.setEnabled(false);angka[0]=0;break;
					case 1: btn2.setEnabled(false);angka[1]=0;break;
					case 2: btn3.setEnabled(false);angka[2]=0;break;
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;										//sumAI akan berisi piliihan baru AI dari salah satu daru barisan kolom yang tersedia			
					sumAI=balik[0];									
					g+=sumAI;										//nilai koin yang diambil akan ditambahkan dengan koin yang masih disimpan sebelumnya
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
						lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Totalnya sama loh. Semangat !");
				}
			}
		});
		btn3.setBounds(282, 105, 48, 35);
		frame.getContentPane().add(btn3);

		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			//button 4
				if(btnYou1.getText()==""){
					btnYou1.setText(btn4.getText());
				}else if(btnYou2.getText()==""){
					btnYou2.setText(btn4.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn4.getText());
				}
				btn4.setEnabled(false);
				sumPemain+=Integer.parseInt(btn4.getText());
				lblhasilPmain.setText(String.valueOf(sumPemain));
				angka[3]=0;
				q++;
				if(q%2!=0&& q<6){
					int[] balik=pilihanAI(angka,g);				//array yangberisi nilai koin yang teredia akan dikirim dengan jumlah koin yang sedang disimpan
					switch(balik[1]){							//periksa pada nilai pada masing-masing button, untuk memilih button mana yang harus
					case 0: btn1.setEnabled(false);angka[0]=0;break;	//didisable karena sudah dipilih oleh AI
					case 1: btn2.setEnabled(false);angka[1]=0;break;
					case 2: btn3.setEnabled(false);angka[2]=0;break;
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;			
					sumAI=balik[0];
					g+=sumAI;
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
						lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Totalnya sama loh. Semangat !");
				}
			}
		});
		btn4.setBounds(330, 105, 48, 35);
		frame.getContentPane().add(btn4);
		
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		//button 5
				if(btnYou1.getText()==""){
					btnYou1.setText(btn5.getText());
				}else if(btnYou2.getText()==""){
					btnYou2.setText(btn5.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn5.getText());
				}
				btn5.setEnabled(false);
				sumPemain+=Integer.parseInt(btn5.getText());
				lblhasilPmain.setText(String.valueOf(sumPemain));
				angka[4]=0;
				q++;
				if(q%2!=0 && q<6){						//jika urutan sekarang ganjil, berarti saatnya AI bermain lagi
					int[] balik=pilihanAI(angka,g);		//jika genap, maka proses akan diabaikan
					switch(balik[1]){
					case 0: btn1.setEnabled(false);angka[0]=0;break;
					case 1: btn2.setEnabled(false);angka[1]=0;break;
					case 2: btn3.setEnabled(false);angka[2]=0;break;
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;
//					sumAI=pilihanAI(angka,g);
					sumAI=balik[0];
					g+=sumAI;
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
						lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Totalnya sama loh. Semangat !");
				}
			}
		});
		btn5.setBounds(378, 105, 48, 35);
		frame.getContentPane().add(btn5);

		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			//button 6
				if(btnYou1.getText()==""){
					btnYou1.setText(btn6.getText());
				}else if(btnYou2.getText()==""){
					btnYou2.setText(btn6.getText());
				}else if(btnYou3.getText()==""){
					btnYou3.setText(btn6.getText());
				}
				btn6.setEnabled(false);
				sumPemain+=Integer.parseInt(btn6.getText());
				lblhasilPmain.setText(String.valueOf(sumPemain));
				angka[5]=0;
				q++;
				if(q%2!=0&& q<6){
					int[] balik=pilihanAI(angka,g);
					switch(balik[1]){
					case 0: btn1.setEnabled(false);angka[0]=0;break;
					case 1: btn2.setEnabled(false);angka[1]=0;break;
					case 2: btn3.setEnabled(false);angka[2]=0;break;
					case 3: btn4.setEnabled(false);angka[3]=0;break;
					case 4: btn5.setEnabled(false);angka[4]=0;break;
					case 5: btn6.setEnabled(false);angka[5]=0;break;
					}
					sumAI=0;
//					sumAI=pilihanAI(angka,g);
					sumAI=balik[0];							
					g+=sumAI;
					if(btnAI2.getText()==""){
						btnAI2.setText(String.valueOf(sumAI));
					}else if(btnAI3.getText()==""){
						btnAI3.setText(String.valueOf(sumAI));}
					lblHasilai.setText(String.valueOf(g));	
					q++;}else if (q>6){ if(Integer.parseInt(lblHasilai.getText())>Integer.parseInt(lblhasilPmain.getText()))
						lblResult.setText("Yah, kalah sama komputer");else if(Integer.parseInt(lblHasilai.getText())<Integer.parseInt(lblhasilPmain.getText()))
							lblResult.setText("Wow, kamu menang! kok bisa?");else if(Integer.parseInt(lblHasilai.getText())==Integer.parseInt(lblhasilPmain.getText()))
								lblResult.setText("Totalnya sama loh. Semangat !");
				}
				}
		});
		btn6.setBounds(426, 105, 48, 35);
		frame.getContentPane().add(btn6);
		
		JLabel lblComputer = new JLabel("COMPUTER");	
		lblComputer.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		lblComputer.setBounds(140, 178, 85, 28);
		frame.getContentPane().add(lblComputer);
		
		JLabel lblNewLabel = new JLabel("YOU");
		lblNewLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(455, 185, 30, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCoinInA = new JLabel("COIN IN A LINE GAME");
		lblCoinInA.setFont(new Font("Arial", Font.BOLD, 16));
		lblCoinInA.setBounds(246, 33, 209, 23);
		frame.getContentPane().add(lblCoinInA);
		
		JLabel lblPilihAntara = new JLabel("PILIIH ANTARA HEAD ATAU TAIL");
		lblPilihAntara.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPilihAntara.setBounds(235, 80, 250, 14);
		frame.getContentPane().add(lblPilihAntara);
		
		JLabel lblTotal1 = new JLabel("Total : ");
		lblTotal1.setBounds(140, 247, 46, 14);
		frame.getContentPane().add(lblTotal1);
		
		JLabel lblTotal1_1 = new JLabel("Total : ");
		lblTotal1_1.setBounds(428, 247, 46, 14);
		frame.getContentPane().add(lblTotal1_1);
		
		JButton btnmain = new JButton("start");
		btnmain.addActionListener(new ActionListener() {	//ketika di klik, button start akan auto men generate 6 angka secar acak untuk menjadi koin
			public void actionPerformed(ActionEvent arg0) {	//yang akan digunakan dalam game
			for(int i=0;i<n;i++){				//pemilihan koin dilakukan dimulai dari angka 1 sampai 10, akan dipilih 6 angka, dan disimpan
					angka[i] = (1 + (int) (Math.random() * 10));//kedalam sebuah array penampung yang akan digunakan untuk permainan
			}
			q1 += angka[0];			//button pertma akan diisikan nilai awal, dari array angka
			btn1.setText(q1);		
			q2 += angka[1];			
			btn2.setText(q2);		//button kedua akan diisikan nilai kedua dari array penampung koin
			q3 += angka[2];
			btn3.setText(q3);		//button kedua akan diisikan nilai kedua dari array penampung koin
			q4 += angka[3];
			btn4.setText(q4);		//button kedua akan diisikan nilai kedua dari array penampung koin
			q5 += angka[4];
			btn5.setText(q5);		//button kedua akan diisikan nilai kedua dari array penampung koin
			q6 += angka[5];
			btn6.setText(q6);		//button kedua akan diisikan nilai kedua dari array penampung koin
			//proses pemilihan oleh AI
			int[] balik=pilihanAI(angka,g);	//pilihan pertma oleh AI akan dilakukan saat mengklik tombol start dan setelah mengenerate angka yang
			switch(balik[1]){				//akan menjadi nilai koin dalam permainan
			case 0: btn1.setEnabled(false);angka[0]=0;break;	//hanya ada 2 kemungkinan koin yang diambil. yaitu koin head dan tail.
			case 5: btn6.setEnabled(false);angka[5]=0;break;	//maka karena pilihan pertama, tidak akan diperlukan button lain selain 2 ini untuk diperisa
			}
			sumAI=balik[0];		//nilai yang direturn dari fungsi ada 2, yang pertama adalah nilai dari array, dan yang kedia adalah urutannya
			g+=sumAI;			//penjumlahan pertama untuk ongkos., saat ini g(n) masih bernilai kecil, karena awal pemilihan
			btnAI1.setText(String.valueOf(sumAI));
			lblHasilai.setText(String.valueOf(g));
		}
		});
		
		btnmain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {		//fungsi event mouse entered adalah ketikan mouse diarahkan maka semua button,
			btn1.setText(q1="");							//label, dan variabel penampung akan direset ke awal.
			btn1.setEnabled(true);
			btn2.setText(q2="");
			btn2.setEnabled(true);					//karena event ini akan dilaksanakan ketika seseorang ingin bermain lagi dengan AI
			btn3.setText(q3="");
			btn3.setEnabled(true);
			btn4.setText(q4="");
			btn4.setEnabled(true);					//selain diset kembali nilai-nilainya, butto juga akan kembali di set enamble,
			btn5.setText(q5="");					//agar koin dapat diisi kembali, dan dapat dimainnkan kembali nantinya
			btn5.setEnabled(true);
			btn6.setText(q6="");
			btn6.setEnabled(true);
			btnYou1.setText("");
			btnYou2.setText("");
			btnYou3.setText("");
			lblHasilai.setText("");
			lblhasilPmain.setText("");
			btnAI1.setText("");
			btnAI2.setText("");
			btnAI3.setText("");
			sumAI=0;sumPemain=0;q=2;g=0;
			lblResult.setText("Gas!");
			}
		});
		btnmain.setBounds(234, 347, 71, 23);
		frame.getContentPane().add(btnmain);
		
		JButton btnBerhenti = new JButton("stop");
		btnBerhenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
			lblResult.setText("							NYERAH?:V							");
			}
		});
		btnBerhenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnBerhenti.setBounds(336, 347, 71, 23);
		frame.getContentPane().add(btnBerhenti);
					
	}
	public int[] pilihanAI(int[] angka,int g){// algoritma A* (A-star) untuk permainan coin in a line game
		int a=0;			//akan digunakan untuk pengecekan nilai dari head ke arah tail dan sampai menemukan isi yang tidak kosong
		int h=0;			//h adaalah nilai heuristik. akan dihitung perkiraan nilai juka kita memilih suatu simpul sebagai tujuan selanjutnya
		int i=a;			//i akan menampung nilai a. dan menjadi kan a yang memenuhi syarat untuk state selanjutnya
		int j=5;			//j digunakan sebagai bahan pengecekan dari tail menuju head. nantinya j akan dipindahkan kedalam variabel b
		int[] balik=new int[2];	//nilai yang akan dikembalikan dalam fungsi ini adalah berupa array, karena akan mengembalikan lebih dari 1 nilai
		int[] f = new int[2];	//f(n) akan berguna untuk membangingkan nilai dengan kemungkinan selanjutnya. f(n) = g(n)+h(n)
		boolean pickAI=true;	//bool digunakan untuk perulangan
		while(pickAI){			//perulangan pertama akan digunakan utuk menemukan f(1. dengan mencari mulai dari head.
			if(angka[a]!=0){	//jika urutannya ganjil, maka itu adalah giliran ui bermain
				i=a;			//nilai yang tidak kosong akan dipindahka urutannya kedalam i;
				if(a<=3)h=(angka[a+2]+angka[j-1]+angka[a]);//jika head lebih kecil dari pada 3, maka gunakan rumus tersendiri sepperti itu
				else if(a>3)h=(angka[j-1]+angka[a]);//jika angka >3. maka rumus tersebut akan digunakan
				f[0]=g+h;	//algoritma A*, dimana g adalah ongkos/bobot yang didapatkan sampai saat ini
				pickAI=false;	//h merupakan nlai heurustik. adalah nilai/bobot yang akan didapatkan dari state sekarang menuju state selanjutnya
			}a++;
		}
		int b=j;	//pemeriksaan tahap ini akan dilakukan dari ut\rutan belkang
		h=0;
		pickAI=true;
		while(pickAI){		//perulangan akan berguna untuk mencari nilai f(1) untu membandingkan nilai f(10 degan nilai(2)
			if(angka[b]!=0){	//sama seperti sebelumnya, karena sebelumya array akan diset jika sudah dipilih.
				j=b;			//duplikat nilaidari 
				if(b>=2) h=(angka[b-2]+angka[i+1]+angka[b]);//jika b>=2, maka gunakan rumus seperti disamping
				else if (b<2) h=(angka[i+1]+angka[b]);	//jika lebih kecil, maka gunakan rumus disamping
				f[1]=g+h;	//perhitungan untuk f yang kedua
				pickAI=false;
			}b--;
		}
		if(f[0]>f[1]){			//akan diperiksa yang mana yang terbesar akan dipilih dan dimasukkan kedalam koin yang dimiliki
			balik[0]=angka[i];	//jika benar, maka nili dalam array yang terpilih akan dimasukkan kedalam variabel yangakan dikembalikan 
			angka[i]=0;			//set 0 lagi jika sudah dipilih
			balik[1]=i;			//mengembalikan urutan karena akan digunakan untuk memilih button mana 
		}						//yang akan didisable
		else {
			balik[0]=angka[j];	//jika kondisi pertama salah, maka berarti kita memilik f(2) atau tail
			angka[j]=0;			//mengemblaikan nilai f(n) jika sudah lebih besatr f(1) dari pada f(0)
			balik[1]=j;			//mengembalikan nilai urutan utnuk mendisable button yang dipilih
		}
		return balik;			//return nilai berbentuk array
	}
}
