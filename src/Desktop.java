import java.io.Serializable;

public class Desktop implements Serializable {
    private int[][] desktop;

    public Desktop(){

    }

    public int[][] getDesktop() {
        return desktop;
    }

    public void setDesktop(int[][] desktop){
        this.desktop = desktop;
    }
}
