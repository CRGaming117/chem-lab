package chem_lab;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
public class ChemLabPanel extends JPanel {
	int w,h,p,e,n;
	private JPanel cP,eP,bP;
	private JLabel eL,eN;
	private ArrayList<Component> controls;
	private Element element;
	public ChemLabPanel(int w,int h) throws FileNotFoundException {
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.darkGray);
		
		p=0;
		n=0;
		e=0;
		
		element=new Element(p,n,e);
		
		cP=new JPanel();
		cP.setPreferredSize(new Dimension(w-10,h-10));
		cP.setBackground(Color.lightGray);
		
		bP=new JPanel();
		bP.setPreferredSize(new Dimension(w-(h+5),h-20));
		bP.setBackground(Color.gray);
		
		Dimension d=new Dimension(80,40);
		Font f=new Font("Arial",Font.PLAIN,40);
		
		controls=new ArrayList<Component>();
		
		for(int i=0;i<3;i++) {
			String title="";
			switch(i) {
			case 0:title="Protons:    ";
			break;
			case 1:title="Neutrons:   ";
			break;
			case 2:title="Electrons:  ";
			break;
			}
			JLabel pT=new JLabel(title);
			pT.setHorizontalAlignment(SwingConstants.CENTER);
			pT.setFont(f);
			
			JButton plus=new JButton("+");
			plus.addActionListener(new ControlListener());
			plus.setPreferredSize(d);
			plus.setFont(f);
			
			JButton minus=new JButton("-");
			minus.addActionListener(new ControlListener());
			minus.setPreferredSize(d);
			minus.setFont(f);
			
			JTextField text=new JTextField("0");
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.addActionListener(new ControlListener());
			text.setFont(f);
			text.setPreferredSize(d);
			
			controls.add(pT);
			controls.add(minus);
			controls.add(text);
			controls.add(plus);
		}
		
		for(Component c:controls) {
			bP.add(c);
		}
		
		eP=new ElementPanel();
		eP.setPreferredSize(new Dimension(h-20,h-20));
		eP.setBackground(Color.gray);
		
		eL=new JLabel("Element:");
		eL.setHorizontalAlignment(SwingConstants.CENTER);
		eL.setFont(f);
		
		eN=new JLabel("");
		eN.setHorizontalAlignment(SwingConstants.CENTER);
		eN.setFont(f);
		
		
		bP.add(eL);
		bP.add(eN);
		
		cP.add(bP);
		cP.add(eP);
		this.add(cP);
		
		
		//DEBUG
		
	}
	private class ElementPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(!element.getName().equals("")) {
				int w=getWidth(),d=500;
				g.setColor(Color.red);
				g.fillOval(w/2-40,w/2-40,80,80);
				
				if(e<87)d-=60;
				if(e<55)d-=60;
				if(e<37)d-=60;
				if(e<19)d-=60;
				if(e<11)d-=60;
				if(e<3)d-=60;
				g.setColor(Color.black);
				for(int i=140;i<=d;i+=60) {
					g.drawOval(w/2-i/2,w/2-i/2,i,i);
				}
			}
		}
	}
	private class ControlListener implements ActionListener {
		public int newElement() throws FileNotFoundException {
			int index=-1;
			element=new Element(p,n,e);
			for(int i=1;i<=118;i++) {
				if(element.equals(new Element(i))) {
					element=new Element(i);
					index=i;
				}
			}
			return index;
		}
		public void actionPerformed(ActionEvent a) {
			
			if(a.getSource()==controls.get(1)) {//- protons
				p=(p==0)?0:p-1;
			}
			if(a.getSource()==controls.get(2)) {//set protons
				p=Integer.parseInt(((JTextField)controls.get(2)).getText());
			}
			if(a.getSource()==controls.get(3)) {//+ protons
				p++;
			}
			
			if(a.getSource()==controls.get(5)) {//- neutrons
				n=(n==0)?0:n-1;
			}
			if(a.getSource()==controls.get(6)) {//set neutrons
				n=Integer.parseInt(((JTextField)controls.get(6)).getText());
			}
			if(a.getSource()==controls.get(7)) {//+ neutrons
				n++;
			}
			
			if(a.getSource()==controls.get(9)) {//- electrons
				e=(e==0)?0:e-1;
			}
			if(a.getSource()==controls.get(10)) {//set electrons
				e=Integer.parseInt(((JTextField)controls.get(10)).getText());
			}
			if(a.getSource()==controls.get(11)) {//+ electrons
				e++;
			}
			
			int index=-1;
			
			try {
				index=newElement();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			((JTextField) controls.get(2)).setText(""+p);
			((JTextField) controls.get(6)).setText(""+n);
			((JTextField) controls.get(10)).setText(""+e);
			eN.setText(element.getName());
			try {
				element=(index>=0)?new Element(index):new Element(p,n,e);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			repaint();
		}
	}
}