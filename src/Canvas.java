import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {

    private Model model;
    private Image imagePlayer;
    private Image imageWall;
    private Image imageBox;
    private Image imageTarget;

    public Canvas(Model model){
        this.model = model;
        setBackground(Color.DARK_GRAY);
        setOpaque(true);
        File fileNameImagePlayer = new File("src/images/player.png");
        File fileNameImageWall = new File("src/images/wall.png");
        File fileNameImageBox = new File("src/images/box.png");
        File fileNameImageGoal = new File("src/images/target.png");
        try {
            imagePlayer = ImageIO.read(fileNameImagePlayer);
            imageWall = ImageIO.read(fileNameImageWall);
            imageBox = ImageIO.read(fileNameImageBox);
            imageTarget = ImageIO.read(fileNameImageGoal);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void paint(Graphics graphics){
        super.paint(graphics);
        if (model.isLevelValid()){
            drawDesktop(graphics);
        }else{
            drawError(graphics);
        }
    }

    private void drawDesktop(Graphics graphics){
        int start = 50;
        int x = start;
        int y = start;
        int width = 50;
        int height = 50;
        int offset = 10;

        int[][] desktop = model.getDesktop();

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if(desktop[i][j] == 1){
                    graphics.drawImage(imagePlayer,x,y,null);
                }else if(desktop[i][j] == 2){
                    graphics.drawImage(imageWall,x,y,null);
                }else if(desktop[i][j] == 3){
                    graphics.drawImage(imageBox,x,y,null);
                }else if(desktop[i][j] == 4){
                    graphics.drawImage(imageTarget,x,y,null);
                }
                x = x + width + offset;
            }
            x = start;
            y = y + height + offset;
        }
    }

    private void drawError(Graphics graphics){
        graphics.setFont(new Font("Arial",Font.BOLD,30));
        graphics.setColor(Color.RED);
        graphics.drawRect(70,160,600,60);
        graphics.drawString("Level is broken , please check it again!",85,200);
    }
}
