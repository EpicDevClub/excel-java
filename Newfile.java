import java.io.File;
import java.io.IOException;

  public class FileCreateExample {

    public static void main(String[] args) {
      //initialize File constructor
      File file = new File("/home/dfuser/Desktop/dataflair/file.txt");
      try {
        boolean createFile = file.createNewFile();
        if (createFile) {
          System.out.println("New File is created.");
        }else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }
