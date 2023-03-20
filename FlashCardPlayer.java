import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class AppPlayer extends JFrame{
private JTextArea display;
private JTextArea answer;
private ArrayList<FlashCard> cardList;
private Iterator cardlterator;
private FlashCard currentCard;
private int currentCardIndex;
private JButton showAnswer;
private JFrame frame;
private boolean isShowAnswer;
private JMenuBar mb;

    public AppPlayer(){
        //create main panel
        JPanel mainPanel = new JPanel();

        display = new JTextArea(6, 20);
        display.setFont(new Font("Times New Roman", Font.BOLD, 19));
        display.setLineWrap(true);
        display.setWrapStyleWord(true);

        //create scroll panel for display and answer
        JScrollPane disSp = new JScrollPane(display);
        // JScrollPane ansSp = new JScrollPane(answer);

        showAnswer = new JButton("Show Answer");

        //create menubar object
        mb = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem loadFile = new JMenuItem("Load File");

        file.add(loadFile);
        mb.add(file);

        mainPanel.add(disSp);
        // mainPanel.add(ansSp);
        mainPanel.add(showAnswer);

        setJMenuBar(mb);


        getContentPane().add(BorderLayout.CENTER, mainPanel);



        showAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if (isShowAnswer){
                    display.setText(currentCard.getAnswer());
                    showAnswer.setText("Next Card");
                    isShowAnswer = false;
                }else if (cardlterator.hasNext()){
                    showNextCard();
                }else{
                    display.setText("that was the last card");
                    showAnswer.setEnabled(false);
                }
            }
        });

        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                JFileChooser load = new JFileChooser();
                load.showOpenDialog(new AppPlayer());
                showFile(load.getSelectedFile());
            }
        });


    }

    //to show the question and answer on the display
    public void showFile(File f){
        cardList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;

            while((line = br.readLine()) != null){
                makeParse(line);
            }
        }catch(IOException e){
        
        }

        //show the first card
        cardlterator = cardList.iterator();
        showNextCard();
    }

    //to create answer and question strings
    public void makeParse(String line){
        String[] strArr = line.split("/");
        FlashCard fc = new FlashCard(strArr[0], strArr[1]);
        cardList.add(fc);
        System.out.println("made card");
    }

    //to show the question on display 
    public void showNextCard(){
        currentCard = (FlashCard) cardlterator.next();
        display.setText(currentCard.getQuestion());
        showAnswer.setText("Show Answer");
        isShowAnswer = true;
    }
}

public class FlashCardPlayer{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                AppPlayer ap = new AppPlayer();
                ap.setSize(411, 591);
                ap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ap.setVisible(true);
            }
        });
    }
}