import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Methods extends JPanel {
	
    ArrayList<String> instructions = new ArrayList<String>();

	public Methods() {
		String filename = JOptionPane.showInputDialog("Please enter file name");
        File f = new File(filename);

        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                instructions.add(s.nextLine());
            }
            s.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Could not find file: " + filename);
        }
	}


    /************************************************************
     * PARSING FILE
     ************************************************************/
    public void parseCommand(String command, Graphics g){
        String[] array = new String[600];

         for(int i = 0; i < command.length(); i++){
             array = command.split(" ");
         }

         for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                break;
            }
            //Gettting color numbers
            else if(array[i].equals("COLOR")){
                int[] colorNums = new int[3];
                int x = 1;

                for(int j = 0; j < colorNums.length; j++){
                    colorNums[j] = Integer.parseInt(array[i+x]);
                    x++;
                }

                changeColor(colorNums[0], colorNums[1], colorNums[2], g);
            }

            //Getting circle numbers
            else if(array[i].equals("CIRCLE")){
                int[] circleNums = new int[3];
                int x = 1;

                for(int j = 0; j < circleNums.length; j++){
                    circleNums[j] = Integer.parseInt(array[i+x]);
                    x++;
                }

                drawCircle(circleNums[0], circleNums[1], circleNums[2], g);

            }
            //Getting triangle numbers
            else if(array[i].equals("TRIANGLE")){
                int[] trigNums = new int[6];
                int x = 1;

                for(int j = 0; j < trigNums.length; j++){
                    trigNums[j] = Integer.parseInt(array[i+x]);
                    x++;
                }

                drawTriangle(trigNums[0], trigNums[1], trigNums[2], trigNums[3], trigNums[4], trigNums[5], g);

            }



       }

    }


    /************************************************************
     * PRINTING
     ************************************************************/

	@Override
	public void paintComponent(Graphics g) {
        
        for(int i = 0; i < instructions.size(); i++){
            parseCommand(instructions.get(i), g);
        }
	}

    public void changeColor(int red, int green, int blue, Graphics g){
        Color color = new Color(red, green, blue);
        g.setColor(color);
    }

    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Graphics g){
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);

    }

    public void drawCircle(int xc, int yc, int r, Graphics g){
        g.drawOval(xc-r, yc-r, r*2, r*2);

    }



	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400,400);
		window.setContentPane(new Methods());
		window.setVisible(true);
	}
}
