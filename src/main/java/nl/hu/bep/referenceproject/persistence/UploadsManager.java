package nl.hu.bep.referenceproject.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class UploadsManager {
    private final static String uploadsLocation = "/home/data";

    public static EncodedBase64 loadEncodedUploadFromAzure(String uploadId) {
        try {
            String base64str = Files.readString(Path.of(uploadsLocation, uploadId));
            return new EncodedBase64(base64str);
        } catch (IOException ioe) {
            throw new IllegalStateException("Could not load image!");
        }
    }

    public static DecodedBase64 loadDecodedUploadFromAzure(String uploadId) {
        String base64str = loadEncodedUploadFromAzure(uploadId).getBase64str();

        int prefixEndIndex = base64str.indexOf(";base64");
        String mime = base64str.substring(5, prefixEndIndex);
        byte[] bytez = Base64.getDecoder().decode(base64str.substring(prefixEndIndex + 8));

        return new DecodedBase64(bytez, mime);
    }

    public static String saveUploadToAzure(EncodedBase64 upload) {
        String base64str = upload.getBase64str();

        long[] idParts = { System.currentTimeMillis(), Math.abs(base64str.hashCode()) };
        String uniqueId = String.valueOf(idParts[0]).concat(String.valueOf(idParts[1]));

        try {
            Files.createDirectories(Path.of(uploadsLocation));
            Files.writeString(Path.of(uploadsLocation, uniqueId), base64str, StandardOpenOption.CREATE_NEW);
        } catch (IOException ioe) {
            uniqueId = null;
        }

        return uniqueId;
    }
}