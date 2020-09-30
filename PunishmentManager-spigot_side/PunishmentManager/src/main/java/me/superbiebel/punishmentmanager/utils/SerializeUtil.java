package me.superbiebel.punishmentmanager.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
}
