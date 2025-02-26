package config;

import java.io.*;

final class Serialize {

    static void serialize(File file, Object object) throws IOException {

        try (FileOutputStream out = new FileOutputStream(file);
             BufferedOutputStream buf = new BufferedOutputStream(out);
             ObjectOutputStream obj = new ObjectOutputStream(buf)) {

            obj.writeObject(object);
        }
    }
}
