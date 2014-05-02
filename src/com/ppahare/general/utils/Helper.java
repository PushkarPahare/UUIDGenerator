package com.ppahare.general.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class Helper {

    public static Object generateUUID() throws Exception {
        byte[] scheduleUUID;
        ByteArrayOutputStream uuidBOS = null;
        ObjectOutputStream uuidOOS = null;
        try {
            uuidBOS = new ByteArrayOutputStream();
            uuidOOS = new ObjectOutputStream(uuidBOS);
            UUID uuID = UUID.randomUUID();
            //System.out.println("UUID is " + uuID.toString());
            uuidOOS.writeLong(uuID.getLeastSignificantBits());
            uuidOOS.flush();
            byte[] leastUUIDBits = uuidBOS.toByteArray();
            uuidBOS.reset();
            uuidOOS.reset();
            uuidOOS.writeLong(uuID.getMostSignificantBits());
            uuidOOS.flush();
            byte[] sigUUIDBits = uuidBOS.toByteArray();

            scheduleUUID = new byte[16];

            for (int i = 0; i < 8; i++) {
                scheduleUUID[i] = leastUUIDBits[i];
            }
            for (int i = 8; i < 16; i++) {
                scheduleUUID[i] = sigUUIDBits[i - 8];
            }
        } catch (IOException iOE) {
            throw new Exception("Failed in generating UUID");
        } finally {
            uuidOOS.close();
            uuidBOS.close();
        }
        return (scheduleUUID);
    }
}
