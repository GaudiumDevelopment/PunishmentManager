package me.superbiebel.punishmentmanager.utils;


import java.io.*;
import java.util.Base64;

public class SerializeUtil {
    public String Serialize(Object object) {
        ByteArrayOutputStream io = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(io);
            os.writeObject(object);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] serializedObject = io.toByteArray();

        return Base64.getEncoder().encodeToString(serializedObject);
    }

    public Object deserialize(String deserialize) throws IOException, ClassNotFoundException {
        byte[] deserializedObject = Base64.getDecoder().decode(deserialize);
        ByteArrayInputStream in = new ByteArrayInputStream(deserializedObject);
        ObjectInputStream is = null;
            is = new ObjectInputStream(in);
            Object result = is.readObject();
            return result;
    }
}