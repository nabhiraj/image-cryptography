package guipractice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
class filechecher{
	public static boolean checkif(String s,String check){
		int i=0;
		while(s.charAt(i)!='.'||i<s.length())
			i++;
		if(i==s.length()){
			return false;
		}else{
			String t=s.substring(i);
			if(t.equals(check))
				return true;
		}
		return false;
	}
}
class Inter_face extends JFrame{
	JButton jpgtopng,pngtojpg,encrpt,dcrpt;
	Inter_face(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Image cryptography");
		setLayout(new GridLayout(4,1));
		jpgtopng=new JButton();
		jpgtopng.setText("Convert from jpg to png");
		pngtojpg=new JButton();
		pngtojpg.setText("Convert from png to jpg");
		encrpt=new JButton();
		encrpt.setText("Encrypt the Image");
		dcrpt=new JButton();
		dcrpt.setText("Dcrypt the Image");
		add(encrpt);
		add(dcrpt);
		add(jpgtopng);
		add(pngtojpg);
		pack();
		setResizable(false);
		dcrpt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Imagemerger();
			}
		});
		encrpt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ImageSplitter();
			}
		});
		
		pngtojpg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new converter(false);
			}
		});
		jpgtopng.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new converter(true);
			}
		});
	}
}
class converter extends JFrame{
	JButton uploadimage,saveimage;
	JLabel status;
	String source,destination;
	File onlyfile=null;
	BufferedImage only=null;
	public converter(boolean t){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setTitle("Converter");
		setLayout(new GridLayout(4,1));
		uploadimage=new JButton();
		uploadimage.setText("Select the Image");
		saveimage=new JButton();
		saveimage.setText("Save the Image");
		saveimage.setVisible(false);
		status=new JLabel();
		status.setText("file : ");
		add(uploadimage);
		add(status);
		add(saveimage);
		pack();
		setResizable(false);
		saveimage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(t){
					//jpg to png
					onlyfile=new File(source);
					
					try {
						only=ImageIO.read(onlyfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("unable to read the file");
						e1.printStackTrace();
					}
					JButton b=new JButton();
					JFileChooser jc=new JFileChooser();
					jc.setCurrentDirectory(new java.io.File("."));
					jc.setDialogTitle("Select the File");
					jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
						
					}
					destination=jc.getSelectedFile().getAbsolutePath();
					try {
						ImageIO.write(only,"png",new File(destination+"\\temppngconverted.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					//png to jpg
onlyfile=new File(source);
					
					try {
						only=ImageIO.read(onlyfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("unable to read the file");
						e1.printStackTrace();
					}
					JButton b=new JButton();
					JFileChooser jc=new JFileChooser();
					jc.setCurrentDirectory(new java.io.File("."));
					jc.setDialogTitle("Select the File");
					jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
						
					}
					destination=jc.getSelectedFile().getAbsolutePath();
					try {
						ImageIO.write(only,"jpg",new File(destination+"\\tempjpgconverted.jpg"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		uploadimage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				//jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				source=jc.getSelectedFile().getAbsolutePath();
				status.setText("file :"+source);
				saveimage.setVisible(true);
			}
		});
	}
	
	
}
class Imagemerger extends JFrame{
	JButton uploadnoise=null,uploaddata=null,uploadresult=null,generate=null;
	JLabel status1=null;
	JLabel status2=null;
	BufferedImage noise=null;
	BufferedImage data=null;
	BufferedImage togenrate=null;
	int w=0,h=0;
	String Snoisestring="";
	String Sdatastring="";
	String destination="";
	File datafile,noisefile;
	public Imagemerger(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setTitle("Merger");
		setLayout(new GridLayout(6,1));
		generate=new JButton();
		generate.setText("Genrate");
		uploadnoise=new JButton();
		uploadnoise.setText("Select the Noise image");
		uploaddata=new JButton();
		uploaddata.setText("Select the Encrpted Image");
		uploadresult=new JButton();
		uploadresult.setText("Save the Dcrypted Image");
		uploadresult.setVisible(false);
		 status1=new JLabel();
		status1.setText("file 1 : ");
		status2=new JLabel();
		status2.setText("file 2 :");
		add(uploaddata);
		add(uploadnoise);
		add(generate);
		add(status1);
		add(status2);
		add(uploadresult);
		pack();
		setResizable(false);
		uploadresult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				destination=jc.getSelectedFile().getAbsolutePath();
				System.out.println(""+destination);
				try {
					ImageIO.write(data,"png",new File(destination+"\\tempactual.png"));
					System.out.println(destination+"\tempNoise.png");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("is this a error");
					//e.printStackTrace();
				}	
				
			}
		});
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			      generateimg(w,h,data,noise);
				uploadresult.setVisible(true);
			}
		});
		uploadnoise.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				//jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				Snoisestring=jc.getSelectedFile().getAbsolutePath();
				//now we will launch the algorithm.
				//after thatsavenoise.setVisible(true);
				status2.setText("file 2 :"+Snoisestring);
						noisefile=new File(Snoisestring);
						try {
							noise=ImageIO.read(noisefile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("unable to read the file");
							e1.printStackTrace();
						}
						w=data.getWidth();
						h=data.getHeight();
			}
		});
		uploaddata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				//jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				Sdatastring=jc.getSelectedFile().getAbsolutePath();
				//now we will launch the algorithm.
				//after thatsavenoise.setVisible(true);
				status1.setText("file 1 :"+Sdatastring);
						datafile=new File(Sdatastring);
						try {
							data=ImageIO.read(datafile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("unable to read the file");
							e1.printStackTrace();
						}
						w=data.getWidth();
						h=data.getHeight();
				
			}
		});
		
	}
	void generateimg(int w,int h,BufferedImage a,BufferedImage r){
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int p=a.getRGB(x, y);
				int pr=r.getRGB(x, y);
				//actual work start here 
				int ap=p>>24 & 0xff;
				int rp=p>>16 & 0xff;
				int gp=p>>8 & 0xff;
				int bp=p & 0xff;
				int apr=pr>>24 & 0xff;
				int rpr=pr>>16 & 0xff;
				int gpr=pr>>8 & 0xff;
				int bpr=pr & 0xff;
				ap=ap^apr;
				rp=rp^rpr;
				gp=gp^gpr;
				bp=bp^bpr;
				p=ap<<24|rp<<16|gp<<8|bp;
				a.setRGB(x, y,p);
			}
			
			
		}
		
		data=a;
	}
}
class ImageSplitter extends JFrame{
	JButton upload=null,savenoise=null;
	JLabel status=null;
	File sourcefile=null;
	String source="";
	String destination="";
	BufferedImage selected=null;
	int w,h;
	BufferedImage noise=null;
	BufferedImage data=null;
	public ImageSplitter() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setTitle("Splitter");
		setLayout(new GridLayout(4,1));
		upload=new JButton();
		upload.setText("Select file");
		savenoise=new JButton();
		savenoise.setText("Give path for Noise file");
		savenoise.setVisible(false);
		status=new JLabel();
		status.setText("file: ");
		add(upload);
		add(status);
		add(savenoise);
		pack();
		setResizable(false);
		savenoise.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//we have to just save to file
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				destination=jc.getSelectedFile().getAbsolutePath();
				//now some string manupulation are required.
				System.out.println(""+destination);
				try {
					ImageIO.write(noise,"png",new File(destination+"\\tempNoise.png"));
					System.out.println(destination+"\tempNoise.png");
					ImageIO.write(data,"png",new File(destination+"\\tempdata.png"));
					System.out.println(destination+"\tempdata.png");
					savenoise.setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("is this a error");
					//e.printStackTrace();
				}
				
			}
		});
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b=new JButton();
				JFileChooser jc=new JFileChooser();
				jc.setCurrentDirectory(new java.io.File("."));
				jc.setDialogTitle("Select the File");
				//jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jc.showOpenDialog(b)==JFileChooser.APPROVE_OPTION){
					
				}
				source=jc.getSelectedFile().getAbsolutePath();
				//now we will launch the algorithm.
				//after thatsavenoise.setVisible(true);
				status.setText("file :"+source);
						sourcefile=new File(source);
						try {
							data=ImageIO.read(sourcefile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("unable to read the file");
							e1.printStackTrace();
						}
						w=data.getWidth();
						h=data.getHeight();
						generateRandomImage(w,h);
						generateimg(w,h,data,noise);
						savenoise.setVisible(true);
			}
		});
		
	}
	//this function generates the random image.
	void generateRandomImage(int w,int h){
		Random rr=new Random();
		noise=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int p=rr.nextInt(256)<<24 | rr.nextInt(256)<<16 | rr.nextInt(256)<<8 | rr.nextInt(256);
				noise.setRGB(x, y, p);
			}
		}
	}
	//this function generates the encrypted images
	void generateimg(int w,int h,BufferedImage a,BufferedImage r){
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int p=a.getRGB(x, y);
				int pr=r.getRGB(x, y);
				//actual work start here 
				int ap=p>>24 & 0xff;
				int rp=p>>16 & 0xff;
				int gp=p>>8 & 0xff;
				int bp=p & 0xff;
				int apr=pr>>24 & 0xff;
				int rpr=pr>>16 & 0xff;
				int gpr=pr>>8 & 0xff;
				int bpr=pr & 0xff;
				ap=ap^apr;
				rp=rp^rpr;
				gp=gp^gpr;
				bp=bp^bpr;
				p=ap<<24|rp<<16|gp<<8|bp;
				a.setRGB(x, y,p);
			}
		}
		data=a;
	}
}
class Main extends JFrame{
	public static void main(String args[]){
		Inter_face i=new Inter_face();
	}
}
