import java.io.*;
import java.net.Socket;

public class Level implements Serializable{
    private int level;
    private int[][] desktop;
    private static final String HOST = "194.152.37.7";
    private static final int PORT = 4446;
    public Level(){
        level = 1;
    }

    public int[][] getNextLevel(){
        switch (level){
            case 1:
                desktop = getFirstLevel();
                level++;
                break;
            case 2:
                desktop = getSecondLevel();
                level++;
                break;
            case 3:
                desktop = getThirdLevel();
                level++;
                break;
            case 4:
                desktop = getFourthLevel();
                level++;
                break;
            case 5:
                desktop = getFifthLevel();
                level++;
                break;
            case 6:
                desktop = getSixthLevel();
                level++;
                break;
            case 7:
                desktop = getSeventhLevel();
                level++;
                break;
            case 8:
                desktop = getEighthLevel();
                level++;
                break;
            case 9:
                desktop = getNinthLevel();
                level++;
                break;
            default:
                level = 1;
                desktop = getFirstLevel();
                break;
        }
        return desktop;
    }

    public int[][] getFirstLevel(){
        int[][] firstLevel = new int[][]{
                {2,2,2,2,2,2,2,2,2,2},
                {2,1,0,0,0,0,0,0,2,2},
                {2,0,2,0,2,2,0,3,0,2},
                {2,0,0,2,0,2,2,0,0,2},
                {2,0,2,0,0,4,2,0,0,2},
                {2,0,2,0,0,2,2,0,0,2},
                {2,0,0,2,0,2,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,2},
                {2,2,0,0,0,0,2,0,2,2},
                {2,2,2,2,2,2,2,2,2,2}

        };
        return firstLevel;
    }

    public int[][] getSecondLevel(){
        int[][] secondLevel = new int[][]{
                {2,2,2,2,2,2,2,2,2,2},
                {2,4,0,0,0,2,0,0,0,2},
                {2,0,2,2,2,2,0,3,0,2},
                {2,0,0,0,0,0,0,0,2,2},
                {2,0,0,0,2,0,0,3,0,2},
                {2,0,2,2,4,2,0,0,0,2},
                {2,0,0,2,0,2,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,2},
                {2,2,0,2,0,0,2,2,1,2},
                {2,2,2,2,2,2,2,2,2,2}
        };
        return secondLevel;
    }

    public int[][] getThirdLevel(){
        int[][] thirdLevel = new int[][]{
                {2,2,2,2,2,2,2,2,2,2},
                {2,4,0,0,0,0,0,0,4,2},
                {2,2,2,2,0,0,2,2,2,2},
                {2,0,2,0,0,0,0,2,0,2},
                {2,0,3,0,0,0,0,3,0,2},
                {2,0,0,0,0,2,0,0,0,2},
                {2,0,2,0,0,2,0,2,0,2},
                {2,0,2,0,3,2,0,2,0,2},
                {2,4,2,0,0,0,0,2,1,2},
                {2,2,2,2,2,2,2,2,2,2}
        };
        return thirdLevel;
    }

    public int[][] getFourthLevel(){
        String path = "src/levels/level4.sok";
        int[][] fourthLevel = null;
        File file = new File(path);
        String contentFromFile = null;
        try {
            contentFromFile = getContentFromFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        fourthLevel = convertStringTo2DArray(contentFromFile);
        return fourthLevel;
    }

    public int[][] getFifthLevel(){
        String path = "src/levels/level5.sok";
        int[][] fifthLevel = null;
        File file = new File(path);
        String contentFromFile = null;
        try {
            contentFromFile = getContentFromFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        fifthLevel = convertStringTo2DArray(contentFromFile);
        return fifthLevel;
    }

    public int[][] getSixthLevel(){
        String path = "src/levels/level6.sok";
        int[][] sixthLevel = null;
        File file = new File(path);
        String contentFromFile = null;
        try {
            contentFromFile = getContentFromFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        sixthLevel = convertStringTo2DArray(contentFromFile);
        return sixthLevel;
    }

    public int[][] getSeventhLevel(){
        int[][] seventhLevel = getLevelFromServer("7");
        return seventhLevel;
    }

    public int[][] getEighthLevel(){
        int[][] eighthLevel = getLevelFromServer("8");
        return eighthLevel;
    }

    public int[][] getNinthLevel(){
        int[][] ninthLevel = getLevelFromServer("9");
        return ninthLevel;
    }

    private int[][] convertStringTo2DArray(String line){
        int n = line.length();
        int row = 0;
        int column = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if(symbol == '\n') {
                row = row + 1;
            }
        }
        int[][] array = new int[row][];
        for (int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if(symbol != '\n') {
                column = column + 1;
            } else if(symbol == '\n'){
                array[index] = new int[column];
                index = index + 1;
                column = 0;
            }
        }
        row = 0;
        column = 0;
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if(symbol!='\n'){
                array[row][column] = Integer.parseInt(""+symbol);
                column = column + 1;
            }else if(symbol == '\n'){
                row = row + 1;
                column = 0;
            }
        }
        return array;
    }

    private String getContentFromFile(File file) throws Exception {
        try(FileInputStream in = new FileInputStream(file)){
            int size = (int) file.length();
            char[] array = new char[size];
            int unicode ;
            int index = 0;
            while ((unicode = in.read()) != -1){
                char symbol = (char) unicode;
                if(('0'<= symbol && symbol <= '4') || (symbol == '\n')){
                    array[index] = symbol;
                    index = index + 1;
                }
            }
            array[index++] = '\n';
            String content = new String(array,0,index);
            return content;
        } catch (FileNotFoundException fnfe) {
            throw new Exception("File not found exception "+fnfe);
        } catch (IOException ioe) {
            throw new Exception("Input output exception "+ioe);
        }
    }

    public int[][] getLevelFromServer(String getLevel) {
        try(Socket socket = new Socket(HOST,PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());)
        {
            out.println(getLevel);
            LevelModel serverLevel = (LevelModel)in.readObject();
            int[][] desktopFromServer = serverLevel.getDesktop();
            return desktopFromServer;
        }catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            return getFirstLevel();
        }catch (IOException ioe){
            ioe.printStackTrace();
            return getFirstLevel();
        }
    }

}
