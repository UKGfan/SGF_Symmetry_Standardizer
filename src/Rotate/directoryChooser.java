package Rotate;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;

import jgogears.SGFRotator;



public class directoryChooser extends JPanel
   implements ActionListener {
   JButton folder1;
   JButton folder2;
   
   File inputString = null;
   File outputString = null;
   

   
   JFileChooser chooser;
   String choosertitle;
   
  public directoryChooser(String buttontext, String buttontext2) {
 
	  
	  
	folder1 = new JButton(buttontext);
    folder1.addActionListener(this);
    folder2 = new JButton(buttontext2);
    folder2.addActionListener(this);
    
    JButton runButton = new JButton( new AbstractAction("run"){
    	
    	public void actionPerformed(ActionEvent e){
    		
    		if((inputString != null) && (outputString != null)){
    			
    			
    			SGFRotator rotator = new SGFRotator();
    			
    			rotator.convertSGFs(inputString.getPath(), outputString.getPath() + "\\");
    
    			System.out.println(inputString.getPath());
    			
    			System.out.println(outputString.getPath() + "\\");
    			
    		}
    		
    	}
    	
    });
    
    
    add(folder1);
    add(folder2);
    add(runButton);
   }

  public void actionPerformed(ActionEvent e) {
    int result;
   
    boolean input;
    
    if(e.getActionCommand() == "Select SGF input directory"){
    	input = true;
    }
    else input = false;
        
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
      
      	if(input){inputString = chooser.getSelectedFile();}
      	else{outputString = chooser.getSelectedFile();}
    	}
    else {
      System.out.println("No Selection ");
      }
     }
   
  public Dimension getPreferredSize(){
    return new Dimension(400, 120);
    }
    
  public static void main(String s[]) {
    JFrame frame = new JFrame("SGF Rotation Standarizer");
    frame.setLayout(new FlowLayout());
    directoryChooser panel = new directoryChooser("Select SGF input directory", "Select SGF output directory");
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
   
   JButton runbutton = new JButton("Run");
    
    frame.add(panel);
 
    
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
    }
  

}