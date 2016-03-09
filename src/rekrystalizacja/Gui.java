package rekrystalizacja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui {
    JFrame mainWindow;
    JPanel up_panel;
    JPanel center_panel; 
    JPanel sim_panel;
    JButton start;
    JButton stop;
    JButton reset;
    JButton random;
    JButton rowno;
    JButton bounds;
    JButton Rec;
    JCheckBox periodic;
    JTextField seeds;
    JTextField promien;
    JRadioButton Moore;
    JRadioButton vonNeuman;
    JRadioButton hexagonalLeft;
    JRadioButton hexagonalRight;
    JRadioButton pentagonalLeft;
    JRadioButton pentagonalRight;
    JRadioButton pentagonalUp;
    JRadioButton pentagonalDown;
    ButtonGroup neighbourhoods;
    Border colorline;
    
     Gui(){
        seeds = new JTextField();
        promien = new JTextField();
        Moore = new JRadioButton("Moore");
        vonNeuman = new JRadioButton("Neuman");
        hexagonalLeft = new JRadioButton("Hex Left");
        hexagonalRight = new JRadioButton("Hex Right");
        pentagonalLeft = new JRadioButton("Pent Left");
        pentagonalRight = new JRadioButton("Pent Right");
        pentagonalUp = new JRadioButton("Pent Up");
        pentagonalDown = new JRadioButton("Pent Down");
        periodic = new JCheckBox("Periodic");
        mainWindow = new JFrame();
        up_panel = new JPanel();
        center_panel = new JPanel();
        colorline = BorderFactory.createLineBorder(Color.black);

        sim_panel = new JPanel();
        center_panel.setLayout(null);
        
        start = new JButton();
        start.setText("Start");
        start.setPreferredSize(new Dimension(65, 25));
        
        stop = new JButton();
        stop.setText("Stop");
        stop.setPreferredSize(new Dimension(65, 25));
        
        reset = new JButton();
        reset.setText("Reset");
        reset.setPreferredSize(new Dimension(70,25));
        
        random = new JButton();
        random.setText("Random");
        seeds.setPreferredSize(new Dimension(70,25));
        promien.setPreferredSize(new Dimension(70,25));
        
        rowno = new JButton();
        rowno.setText("Równo");
        rowno.setPreferredSize(new Dimension(75,25));
        
        bounds = new JButton();
        bounds.setText("Bounds");
        bounds.setPreferredSize(new Dimension(80,25));
        Rec = new JButton();
        Rec.setText("Rec");
        Rec.setPreferredSize(new Dimension(65,25));
        
        
        neighbourhoods = new ButtonGroup();
        neighbourhoods.add(Moore);
        neighbourhoods.add(vonNeuman);
        neighbourhoods.add(hexagonalLeft);
        neighbourhoods.add(hexagonalRight);
        neighbourhoods.add(pentagonalLeft);
        neighbourhoods.add(pentagonalRight);
        neighbourhoods.add(pentagonalUp);
        neighbourhoods.add(pentagonalDown);
        
        
        sim_panel.add(start);
        sim_panel.add(stop);
        sim_panel.add(reset);
        sim_panel.add(random);
        sim_panel.add(bounds);
        sim_panel.add(rowno);
        sim_panel.add(Rec);
        sim_panel.add(seeds);
        sim_panel.add(promien);
        sim_panel.add(Moore);
        sim_panel.add(vonNeuman);
        sim_panel.add(hexagonalLeft);
        sim_panel.add(hexagonalRight);
        sim_panel.add(pentagonalLeft);
        sim_panel.add(pentagonalRight);
        sim_panel.add(pentagonalUp);
        sim_panel.add(pentagonalDown);
        sim_panel.add(periodic);
        
        mainWindow.setSize(new Dimension(1400,800));
        mainWindow.setVisible(true);
        
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainWindow.add(up_panel,BorderLayout.PAGE_END);
        mainWindow.add(center_panel, BorderLayout.CENTER);
        mainWindow.setResizable(true);
        up_panel.add(sim_panel);

    }
}
