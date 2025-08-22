// HelloOpenCV.java
import org.opencv.core.Core;

public class HelloOpenCV {
  static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
  public static void main(String[] args) {
    System.out.println("Loaded: " + Core.getVersionString());
  }
}
