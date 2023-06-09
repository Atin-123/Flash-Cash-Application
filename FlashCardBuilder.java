import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class MyApp extends JFrame{
    private JPanel panel;
    private JLabel question, answer;
    private JTextArea quesTa, ansTa;
    private JScrollPane quesSp, ansSp;
    private JMenuBar mb;
    private JButton nextCardBtn;
    private ArrayList<FlashCard> al;

    public MyApp(){
        //object of question and answer label
        question = new JLabel("Question");
        answer = new JLabel("Answer");


        //object of nextCard button
        nextCardBtn = new JButton("Next Card");


        //object of quesTf and ansTf
        quesTa = new JTextArea(6, 20);
        ansTa = new JTextArea(6, 20);

        quesTa.setLineWrap(true);
        quesTa.setWrapStyleWord(true);
        quesTa.setFont(new Font("Times New Roman", Font.BOLD, 19));

        ansTa.setLineWrap(true);
        ansTa.setWrapStyleWord(true);
        ansTa.setFont(new Font("Times New Roman", Font.BOLD, 19));


        //object of Menubar
        mb = new JMenuBar();
        //object of Menu and menuitem;
        JMenu m = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        //add menu items to menubar
        m.add(newMenuItem);
        m.add(saveMenuItem);
        mb.add(m);

        //add menubar to the layout
        setJMenuBar(mb);


        //object of panel
        panel = new JPanel();

        //object of quesSp and ansSp
        quesSp = new JScrollPane(quesTa);
        ansSp = new JScrollPane(ansTa);

        //add components to panel
        panel.add(question);
        panel.add(quesSp);
        panel.add(answer);
        panel.add(ansSp);
        panel.add(nextCardBtn);


        al = new ArrayList<FlashCard>();

        getContentPane().add(BorderLayout.CENTER, panel);

        //add listeners to components
        nextCardBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                //create a FlashCard
                FlashCard fc = new FlashCard(quesTa.getText(), ansTa.getText());
                al.add(fc);
                System.out.println(al.size());
                
                quesTa.setText("");
                ansTa.setText("");

                quesTa.requestFocus(); // we want to focus the cursor on the quesTa once we press next card button.

                
            }
        });

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("New File Created");
            }
        });

        saveMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(new MyApp());
                saveFile(fc.getSelectedFile());
            }
        });

    }

    public void saveFile(File f) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));

            Iterator<FlashCard> i = al.iterator();

            while(i.hasNext()){
                System.out.println("Writing");
                //get particular flashcard 
                FlashCard fc = (FlashCard) i.next();
                bw.write(fc.getQuestion()+"/");
                bw.write(fc.getAnswer()+"\n");
            }
            bw.close();
        }catch(IOException e){

        }
    }

    
}

public class FlashCardBuilder {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                MyApp app = new MyApp();
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setSize(411, 591);
                app.setVisible(true);
            }
        });
    }
}
