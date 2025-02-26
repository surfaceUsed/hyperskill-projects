package config;

import java.io.*;

final class Deserialize {

    static Object deserialize(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream(file);
             BufferedInputStream bin = new BufferedInputStream(fin);
             ObjectInputStream obj = new ObjectInputStream(bin)) {

            return obj.readObject();
        }
    }
}
