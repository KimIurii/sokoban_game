import java.util.ArrayList;
import java.util.List;

public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private List<Integer> listOfTargets;
    private int indexX;
    private int indexY;
    private Level levels;
    private boolean isLevelValid;

    public Model(Viewer viewer){
        this.viewer = viewer;
        levels = new Level();
        desktop = levels.getNextLevel();
        isLevelValid = verifyLevel(desktop);
        findPlayerInCurrentLevel(desktop);
        listOfTargets = new ArrayList<Integer>();
        initialization();
    }

    //Whether level is valid or not
    public boolean verifyLevel(int[][] desktop){
        int player = 0;
        boolean walls = true;
        int boxes = 0;
        int targets = 0;

        //Verify how many players , boxes and targets exist on this level
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if(desktop[i][j] == 1){
                    player++;
                }else if(desktop[i][j] == 3){
                    boxes++;
                }else if(desktop[i][j] == 4){
                    targets++;
                }
            }
        }

        //Verify are every wall consist of only two's
        for (int i = 0; i < desktop.length; i++) {
            if(i == 0 || i == 9){
                for (int j = 0; j < desktop[i].length; j++) {
                    if (desktop[i][j]!=2){
                        walls = false;
                    }
                }
            }else {
                for (int j = 0; j < desktop[i].length; j++) {
                    if(desktop[i][0] != 2 || desktop[i][9] != 2){
                        walls = false;
                    }
                }
            }
        }

        boolean isThereOnlyOnPlayer = player == 1;
        boolean boxesAndTargetsAreEnoughAndEqual = ((boxes>0) && (targets>0) && (boxes == targets));

        return isThereOnlyOnPlayer && boxesAndTargetsAreEnoughAndEqual && walls;
    }

    //If level is valid , find and set targets
    private void checkTarget(){
        if(listOfTargets.isEmpty()){
            for (int i = 0; i < desktop.length; i++) {
                for (int j = 0; j < desktop[i].length; j++) {
                    if(desktop[i][j] == 4){
                        listOfTargets.add(i);
                        listOfTargets.add(j);
                    }
                }
            }
        }
        for (int i = 0; i < listOfTargets.size(); i++) {
            int x = listOfTargets.get(i);
            int y = listOfTargets.get(++i);
            if((desktop[x][y] != 1 && desktop[x][y] != 3) && desktop[x][y] == 0){
                desktop[x][y] = 4;
                i+=2;
            }
        }

    }
    //Find x and y position in current level
    private void findPlayerInCurrentLevel(int[][] array){
        if(isLevelValid) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == 1) {
                        indexX = i;
                        indexY = j;
                    }
                }
            }
        }
    }

    private void initialization() {
        if(isLevelValid){
            desktop[indexX][indexY] = 1;
        }else{
            viewer.showLevelIsNotValidDialog();
        }
    }

    public void move(String direction){
        if (direction.equals("Up")){
            moveUp();
            checkTarget();
        }else if(direction.equals("Right")){
            moveRight();
            checkTarget();
        }else if(direction.equals("Down")){
            moveDown();
            checkTarget();
        }else if(direction.equals("Left")){
            moveLeft();
            checkTarget();
        }else{
            return;
        }
        viewer.update();
        isWon();
    }

    private void isWon(){
        int counterForResult = 0;
        int counterForLoop = 0;
        for (int i = 1; i <= listOfTargets.size(); i++) {
            if(desktop[listOfTargets.get(counterForLoop)][listOfTargets.get(counterForLoop+1)] == 3){
                counterForLoop+=2;
                i++;
                counterForResult++;
            }
        }
        boolean isWon = counterForResult==(listOfTargets.size()/2);
        if(isWon){
            viewer.showWonDialog();
            desktop = levels.getNextLevel();
            listOfTargets = new ArrayList<>();
            isLevelValid = verifyLevel(desktop);
            findPlayerInCurrentLevel(desktop);
            initialization();
            viewer.update();
        }

    }

    private void moveUp() {
        if(desktop[indexX-1][indexY] == 3){
            if(desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4){
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }

        if(desktop[indexX-1][indexY] == 0 || desktop[indexX-1][indexY] == 4){
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }

    }

    private void moveRight() {
        if(desktop[indexX][indexY + 1] == 3){
            if(desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4){
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if (desktop[indexX][indexY + 1]== 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveDown() {
        if(desktop[indexX + 1][indexY] == 3){
            if(desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4){
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }

        if(desktop[indexX+1][indexY] == 0 || desktop[indexX+1][indexY] == 4){
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveLeft() {
        if(desktop[indexX][indexY - 1] == 3){
            if(desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4){
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }

        if (desktop[indexX][indexY - 1]== 0 || desktop[indexX][indexY-1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    public int[][] getDesktop(){
        return desktop;
    }

    public void goToChosenLevel(int levelNumber){
        switch (levelNumber){
            case 1:
                desktop = levels.getFirstLevel();
                break;
            case 2:
                desktop = levels.getSecondLevel();
                break;
            case 3:
                desktop = levels.getThirdLevel();
                break;
            case 4:
                desktop = levels.getFourthLevel();
                break;
            case 5:
                desktop = levels.getFifthLevel();
                break;
            case 6:
                desktop = levels.getSixthLevel();
                break;
            case 7:
                desktop = levels.getSeventhLevel();
                break;
            case 8:
                desktop = levels.getEighthLevel();
                break;
            case 9:
                desktop = levels.getNinthLevel();
                break;
        }
        listOfTargets = new ArrayList<>();
        isLevelValid = verifyLevel(desktop);
        findPlayerInCurrentLevel(desktop);
        initialization();
        viewer.update();
    }

    public boolean isLevelValid() {
        return isLevelValid;
    }

}
