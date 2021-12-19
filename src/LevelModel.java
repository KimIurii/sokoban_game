import java.io.Serializable;

public class LevelModel implements Serializable {
    private int[][] desktop;

    public LevelModel(){

    }

    public int[][] getDesktop() {
        return desktop;
    }

    public void setDesktop(int[][] desktop){
        this.desktop = desktop;
    }
}
