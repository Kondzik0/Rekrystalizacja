package rekrystalizacja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


public class Rekrystalizacja implements Runnable
{
    Gui gui = new Gui();
    JButton[][] cells;
    float[][] black;
    int size = 15;
    int rows = 45;
    int cols = 80;
    int random_num = 0;
    int edgeCount = 0;
    Color color;
    
    float packets = 10;
    
    boolean[][] mainArray;
    boolean[][] tempArray;
    boolean run = false;
    boolean hit;

   void copy(boolean array[][], boolean arrayB[][])
   {
        for(int i = 1; i < rows-1; i++)
            System.arraycopy(arrayB[i], 1, array[i], 1, cols-1 - 1);
    }
    
   Color colorArray()
   {
       Random rand = new Random();
       float r = rand.nextFloat();
       float g = rand.nextFloat();
       float b = rand.nextFloat();
        color = new Color(r, g, b);  
        return color;
       
   }
   
    void Init()
    {
        mainArray = new boolean[rows][cols];
        tempArray =  new boolean[rows][cols];
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                mainArray[i][j] = false;
                tempArray[i][j] = false;
            }
        }
        
        cells = new JButton[rows][cols];
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                cells[i][j] = new JButton();
                cells[i][j].setSize(new Dimension(size, size));
                gui.center_panel.add(cells[i][j]);
                cells[i][j].setBounds(j*size, i*size, size, size);
                cells[i][j].setBorderPainted(false);
                
            
                if(i==0 || i==rows-1 || j==0 || j==cols-1)
                {
                    cells[i][j].setBorderPainted(false);
                    cells[i][j].setEnabled(false);
                }
                else
                {
                  cells[i][j].setBackground(Color.white);
                }
            }
        }
        
    }
     void cellsPaint()
     {
        for(int i = 1; i < rows-1; i++)
        {
            for(int j = 1; j < cols-1; j++)
            {
                if(mainArray[i][j] == true)
                    cells[i][j].setBackground(color);
                else
                    cells[i][j].setBackground(Color.white);
            }
        }
    }
    void rekrystalizacja()
    {
        black = new float[rows][cols];
        float A = 86710969050178.5f;
        float B = 9.41268203527779f;
        Random rand = new Random();   
        float ro_main;  
        int recr_num = 100;
        int recr_num_count = 0;
        
        float rho_crit = (float) (4.21584*Math.exp(12)/(rows*cols));
        
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                mainArray[i][j] = false;
                tempArray[i][j] = false;
                black[i][j] = 0;
            }
        }
        
        
        for(float i = 0; i < 0.2; i+=0.001){
                    ro_main = (float) (A/B+(1-A/B)*Math.exp(-B*i));
                    float pack = ro_main/(packets*0.8f);
                    for(int g = 0; g < packets; g++){
                        hit = false;
                        
                        while(!hit){
                            int x = rand.nextInt(rows-1); 
                            int y = rand.nextInt(cols-1);
                           
                            for(int k = 1; k < rows - 1; k++)
                            {
                                for(int j=1;j<cols-1;j++)
                                {    
                                    
                                    if(k==x && j==y && cells[k][j].getBackground() == Color.black){
                                        hit = true;
                                        black[k][j] += pack;
                                    }
                                }
                            }
                        }
    
                    }
                    
                    pack = ro_main/(packets*0.2f);
                    for(int g = 0; g < packets; g++){
                        int x = rand.nextInt(rows-1); 
                        int y = rand.nextInt(cols-1);
                       
                        for(int k = 1; k < rows-1; k++)
                        {
                            for(int j = 1; j < cols-1 ; j++)
                            {
                                if(k!=x && j!=y && cells[k][j].getBackground() != Color.black)
                                {
                                    black[x][y] += pack;
                                }
                            }
                        }
                       
                    }
                    for(int j = 1; j < rows-1; j++)
                        for(int k = 1; k < cols-1 ; k++)
                            if(black[j][k] > rho_crit)
                                
                                 if(recr_num_count < recr_num){
                                        colorArray();
                                        mainArray[j][k] = true;
                                        cells[j][k].setBackground(color);
                                        recr_num_count++;
                                }
                    
                    
                    
                }
        
    }
     
     
     void periodic()
     {
        for(int j = 1; j < cols-1; j++)
        {
            mainArray[0][j] = mainArray[rows-2][j];
            cells[0][j].setBackground(cells[rows-2][j].getBackground());
        }
        for(int j = 1; j < cols-1; j++)
        {
            mainArray[rows-1][j] = mainArray[1][j];
            cells[rows-1][j].setBackground(cells[1][j].getBackground());
        }
        for(int i = 1; i < rows-1; i++)
        {
            mainArray[i][0] = mainArray[i][cols-2];
            cells[i][0].setBackground(cells[i][cols-2].getBackground());
        }
        for(int i = 1; i < rows-1; i++)
        {
            mainArray[i][cols-1] = mainArray[i][1];
            cells[i][cols-1].setBackground(cells[i][1].getBackground());
        }
        mainArray[0][0] = mainArray[rows-2][cols-2];
        mainArray[rows-1][cols-1] = mainArray[1][1];
        mainArray[rows-1][0] = mainArray[1][cols-2];
        mainArray[0][cols-1] = mainArray[rows-2][1];
        cells[0][0].setBackground(cells[rows-2][cols-2].getBackground());
        cells[rows-1][cols-1].setBackground(cells[1][1].getBackground());
        cells[rows-1][0].setBackground(cells[1][cols-2].getBackground());
        cells[0][cols-1].setBackground(cells[rows-2][1].getBackground());
    }
     
    void hexagonalLeft()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                        if(mainArray[i+1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j+1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                        if(mainArray[i-1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j-1].getBackground());
                        }
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                    }
                }
    }
    
    void hexagonalRight()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                            
                        }
                        if(mainArray[i-1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j+1].getBackground());
                        }
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i+1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j-1].getBackground());
                        }
                    }
                }
        
    }
    void pentagonalDown()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i-1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j-1].getBackground());
                        }
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i-1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j+1].getBackground());
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                    }
                }
    }
    void pentagonalUp()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                        if(mainArray[i+1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j-1].getBackground());
                        }
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                        if(mainArray[i+1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j+1].getBackground());
                        }
                    }
                }
    }
    
    void pentagonalRight()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i-1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j-1].getBackground());
                        }
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i+1][j-1] == true)
                        {
                           tempArray[i][j] = true;
                           cells[i][j].setBackground(cells[i+1][j-1].getBackground());
                        }
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                    }
                }
        
    }
    
    void pentagonalLeft()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i-1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j+1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                        if(mainArray[i+1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j+1].getBackground());
                        }
                    }
                    
                }
        
        
    }
    void moore()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                        if(mainArray[i+1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j+1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                        if(mainArray[i-1][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j+1].getBackground());
                        }
                        if(mainArray[i-1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j-1].getBackground());
                        }
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i+1][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j-1].getBackground());
                        }
                    }

                }
    }
    void vonNeuman()
    {
        for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    if(mainArray[i][j] == false)
                    {
                        if(mainArray[i-1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i-1][j].getBackground());
                            
                        }
                        if(mainArray[i][j-1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j-1].getBackground());
                        }
                        if(mainArray[i][j+1] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i][j+1].getBackground());
                        }
                        if(mainArray[i+1][j] == true)
                        {
                            tempArray[i][j] = true;
                            cells[i][j].setBackground(cells[i+1][j].getBackground());
                        }
                    }
                }
        
    }
     
    void game() throws InterruptedException
    {
        while(run)
        {
            if(gui.periodic.isSelected())
            {
                periodic();
            }
            if(gui.Moore.isSelected())
            {
                moore();
            }
            if(gui.vonNeuman.isSelected())
            {
                vonNeuman();
            }
            if(gui.hexagonalLeft.isSelected())
            {
                hexagonalLeft();
            }
            if(gui.hexagonalRight.isSelected())
            {
                hexagonalRight();
            }
            if(gui.pentagonalLeft.isSelected())
            {
                pentagonalLeft();
            }
            if(gui.pentagonalRight.isSelected())
            {
                pentagonalRight();
            }
            if(gui.pentagonalUp.isSelected())
            {
                pentagonalUp();
            }
            if(gui.pentagonalDown.isSelected())
            {
                pentagonalDown();
            }
            
            copy(mainArray, tempArray);
            Thread.sleep(300);
        }
    }
    
   void GridInit()
   {
     for(int i = 0; i < rows; i++)
       for(int j = 0; j < cols; j++)
       {
            int k = i;
            int l = j;   
            cells[i][j].addActionListener(new ActionListener() 
            {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                colorArray();
                if(cells[k][l].getBackground() != Color.white)
                {
                    cells[k][l].setBackground(Color.white);
                    mainArray[k][l] = false;
                }
                else
                {
                    cells[k][l].setBackground(color);
                    mainArray[k][l] = true;
                }                
                
            }
        });
    }          
   }
   
   
   void promien()
    {
        boolean add;
        Random generator=new Random();  
        int R = Integer.parseInt(gui.promien.getText());
        int random = Integer.parseInt(gui.seeds.getText());
        for(int i = 0; i < random; i++)
        {
            int x = generator.nextInt(rows-2)+R;
            int y = generator.nextInt(cols-2)+R;
            add=true;

            for(int j=x-R; j<x+R; j++)
            {
                for(int k=y-R; k<y+R; k++)
                {
                    if(mainArray[j][k]==true)
                    {
                        add=false;
                        break;
                    }
                    if(!add)
                        break;
                }
            }
            if(!add)
                i--;
            else
               colorArray();
               mainArray[x][y] = true;
               cells[x][y].setBackground(color);
        }
    }
   
   void rownomiernie()
   {
       random_num = Integer.parseInt(gui.seeds.getText());
       int num = 100/random_num;
       
       for(int i = 5;i<rows;i+=num)
       {
            for(int j = 5;j<cols;j+=num)
            {
                colorArray();
                mainArray[i][j]=true;
                cells[i][j].setBackground(color);
            }
        }  
   }
   
   void bounds()
   {
       for (int i =1;i<rows-1;i++)
       {
           for(int j=1;j<cols-1;j++)
           {
               if(cells[i][j+1].getBackground() != cells[i][j].getBackground())
               {
                   cells[i][j].setBackground(Color.black);
               }
               else
               {
                    if(cells[i+1][j].getBackground() != cells[i][j].getBackground())
                    {
                         cells[i][j].setBackground(Color.black);
                    }
               }
           }
       }
       
   }
   
   
   
   void randomizeSeeds()
   {
       Random ran = new Random();
       random_num = Integer.parseInt(gui.seeds.getText());
       for(int i=0;i<random_num;i++)
       {
           int ran_rows = ran.nextInt(rows-1);
           int ran_cols = ran.nextInt(cols-1);
           if (ran_rows == 0 || ran_cols == 0)
           {
               ran_rows++;
               ran_cols++;
           }
           if(ran_rows==rows || ran_cols == cols)
           {
               ran_rows--;
               ran_cols--;
           }
           colorArray();
           mainArray[ran_rows][ran_cols]=true;
           cells[ran_rows][ran_cols].setBackground(color);
       }
       
   }   
   
   void startStopReset()
   {
      gui.start.addActionListener(new ActionListener() 
      {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                 
                run = true;
                gameThread();
            }
        });
         gui.stop.addActionListener(new ActionListener() 
         {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                if(run)
                {
                    run = false;
                }
                else
                {
                    run = true;                    
                }
            }
        });
         gui.reset.addActionListener(new ActionListener() 
       {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                run = false;
                for(int i = 0; i < rows; i++)
                {
                        for(int j = 0; j < cols; j++)
                        {
                            if(cells[i][j].getBackground() != Color.white)
                            {
                                cells[i][j].setBackground(Color.white);
                                mainArray[i][j] = false;
                                tempArray[i][j] = false;

                            }
                            
                        }
                }
            }
       });
         gui.random.addActionListener(new ActionListener() 
         {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                randomizeSeeds();
            }
        });
         gui.rowno.addActionListener(new ActionListener() 
         {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                rownomiernie();
                
            }
        });
         gui.bounds.addActionListener(new ActionListener() 
         {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                bounds();
            }
        });
         gui.Rec.addActionListener(new ActionListener() 
      {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                run = false;
                rekrystalizacja();
            }
        });
   }
   
   void gameThread()
   {
        Thread thread = new Thread(this);
        thread.start();
   }
   
    @Override
    public void run() 
    {
        try 
        {
            
            game();
        } catch (InterruptedException ex) 
        {
            Logger.getLogger(Rekrystalizacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public static void main(String[] args) 
    {
        
        Rekrystalizacja temp = new Rekrystalizacja();
        temp.Init();
        temp.GridInit();
        temp.startStopReset();
   
   }
}
