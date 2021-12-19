import javax.swing.*;

public class Viewer {
    private Canvas canvas;
    private JFrame frame;
    private Controller controller;
    public Viewer(){
        controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);
        initGame();
    }

    private void initGame(){
        JMenuBar jMenuBar = initLevels();

        frame = new JFrame("Sokoban Game MVC Pattern");
        frame.setSize(800,800);
        frame.add("Center",canvas);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(jMenuBar);
        frame.addKeyListener(controller);
    }

    private JMenuBar initLevels(){
        JMenuBar menuBar = new JMenuBar();
        JMenu levels = new JMenu("Choose Level");
        JMenuItem firstLevel = new JMenuItem("First Level");
        firstLevel.addActionListener(controller);
        firstLevel.setActionCommand("First");

        JMenuItem secondLevel = new JMenuItem("Second Level");
        secondLevel.addActionListener(controller);
        secondLevel.setActionCommand("Second");

        JMenuItem thirdLevel = new JMenuItem("Third Level");
        thirdLevel.addActionListener(controller);
        thirdLevel.setActionCommand("Third");

        JMenuItem fourthLevel = new JMenuItem("Fourth Level");
        fourthLevel.addActionListener(controller);
        fourthLevel.setActionCommand("Fourth");

        JMenuItem fifthLevel = new JMenuItem("Fifth Level");
        fifthLevel.addActionListener(controller);
        fifthLevel.setActionCommand("Fifth");

        JMenuItem sixthLevel = new JMenuItem("Sixth Level");
        sixthLevel.addActionListener(controller);
        sixthLevel.setActionCommand("Sixth");

        JMenuItem seventhLevel = new JMenuItem("Seventh Level");
        seventhLevel.addActionListener(controller);
        seventhLevel.setActionCommand("Seventh");

        JMenuItem eighthLevel = new JMenuItem("Eighth Level");
        eighthLevel.addActionListener(controller);
        eighthLevel.setActionCommand("Eighth");

        JMenuItem ninthLevel = new JMenuItem("Ninth Level");
        ninthLevel.addActionListener(controller);
        ninthLevel.setActionCommand("Ninth");

        levels.add(firstLevel);
        levels.add(secondLevel);
        levels.add(thirdLevel);

        levels.add(fourthLevel);
        levels.add(fifthLevel);
        levels.add(sixthLevel);

        levels.add(seventhLevel);
        levels.add(eighthLevel);
        levels.add(ninthLevel);

        menuBar.add(levels);
        return menuBar;
    }

    public void update(){
        canvas.repaint();
    }

    public void showWonDialog(){
        JOptionPane.showMessageDialog(frame,"You won , congratulations");
    }

    public void showLevelIsNotValidDialog(){
        canvas.repaint();
    }

    public void totalWon(){
        canvas.repaint();
    }
}
