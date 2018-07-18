import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notepad extends WindowAdapter implements ActionListener 
{
    JFrame frame=new JFrame();
  JMenuBar mb;JMenu file,edit,help;JMenuItem cut,copy,paste,selectall,OPen,Save,NEW,Saveas;
  JScrollBar sb;
  JTextArea ta;
  int flag;
  String s,savecheck;
  File savefile;
  JScrollPane jp;
  
  Notepad() 
  {     
    Font font=new Font("Serif",Font.BOLD,12);
    flag=0;
    frame.setTitle("Kanav's Notepad");
    mb=new JMenuBar();
    mb.setSize(500,500);
    file=new JMenu("File");
    file.setSize(500,500);
    edit=new JMenu("Edit");
    help=new JMenu("Help");
    cut=new JMenuItem("Cut");
    copy=new JMenuItem("Copy");
    paste=new JMenuItem("Paste");
    NEW=new JMenuItem("New");
    OPen=new JMenuItem("Open");
    Save=new JMenuItem("Save");
    Saveas=new JMenuItem("Save As");
    file.add(NEW);file.add(OPen);file.add(Save);file.add(Saveas);
    selectall=new JMenuItem("SelectAll");
    ta=new JTextArea();
    ta.setBounds(0,0,1080,720);
    ta.setFont(font);
    ta.setForeground(Color.PINK);
    jp=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    cut.addActionListener(this);    
    copy.addActionListener(this);    
    paste.addActionListener(this);    
    selectall.addActionListener(this); 
    NEW.addActionListener(this);
    Saveas.addActionListener(this);
    Save.addActionListener(this);
    OPen.addActionListener(this);
    edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectall);
    mb.add(file);mb.add(edit);mb.add(help);
    //frame.add(ta);
    frame.add(mb);
    frame.add(jp);
        
    frame.setJMenuBar(mb);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.setSize(1080,720);
    frame.addWindowListener(this);
     
  }
  public void actionPerformed(ActionEvent ae)
  {
      if(ae.getSource()==cut)    
      ta.cut();    
      if(ae.getSource()==paste)    
      ta.paste();    
      if(ae.getSource()==copy)    
      ta.copy();    
      if(ae.getSource()==selectall)    
      ta.selectAll(); 
      if(ae.getSource()==OPen)
      {
          flag=0;
       JFileChooser fc=new JFileChooser(); 
       int i=fc.showOpenDialog(frame);
       if(i==JFileChooser.APPROVE_OPTION)
       {
              try {
                  FileReader filew=new FileReader(fc.getSelectedFile().getPath());
                  
                  BufferedReader bff=new BufferedReader(filew);
                  String sk="";
                  String line;
                  while((line=bff.readLine())!=null)
                          {
                              sk+=line+"\n";
                          }
                  ta.setText(sk);
              } catch (IOException ex) {
                  ta.setText("IO Exception");
              }
              
           
       }
      }
      if(ae.getSource()==Save)
      { 
          if(flag==0)
          {
              ae.setSource(Saveas);
          }
          
          else if(flag==1)
          { 
              try
              {
                  FileWriter fwe=new FileWriter(savefile);              
                  BufferedWriter fouter=new BufferedWriter(fwe);
                  s=ta.getText();
                  fouter.write(s);
                  fouter.close(); 
                  savecheck=s;
              }
              catch(Exception e)
              {
                  System.out.println("Shit");
              }
          }
          
      }
  
      if(ae.getSource()==Saveas)
      {
       
          JFileChooser scs=new JFileChooser();
          int i=scs.showSaveDialog(frame);
          if(i==JFileChooser.APPROVE_OPTION)
          {                   
              try {     
                  savefile=new File(scs.getSelectedFile().getPath());
                  FileWriter fw=new FileWriter(savefile);              
                  BufferedWriter fout=new BufferedWriter(fw);
                  s=ta.getText();
                  fout.write(s);
                  fout.close();
                  flag=1;
                  savecheck=s;
                  
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch (IOException ex) {
                  Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
          }
      } 
      }
  }
    public static void main(String[] args) {
        new Notepad()
               ;
    }
     public void windowClosing(WindowEvent e) {
    {
        if(savecheck==ta.getText())
        {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if(savecheck!=ta.getText())
        {
            
           int i=JOptionPane.showConfirmDialog(frame," You Want to Exit Without Saving?");
           if(i==JOptionPane.YES_OPTION)
           {
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           }
           else if(i==JOptionPane.NO_OPTION)
           {
             frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
           }
           else if(i==JOptionPane.CANCEL_OPTION)
           {
               frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
           }
           
        }
    }
    
}
}
