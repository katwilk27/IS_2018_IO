package sample;
import java.io.*;
import java.util.*;

public class Serialization implements Serializable {
    public void serialize(String date, String pin) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("Log_bin.ser"))) {
            out.writeUTF(date);
            out.writeUTF(pin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("A problem occurred during serialization");
            System.out.println(e.getMessage());
        }
    }

    public String deserializeDate() {
        String data = null;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("Log_bin.ser"))) {
            data = in.readUTF();
        } catch (IOException e) {
            System.out.println("A problem occurred during deserialization");
            System.out.println(e.getMessage());
        }
        return data;
    }


}