package com.kobylynskyi.graphql.codegen;

import com.kobylynskyi.graphql.codegen.model.DateTimeGenerator;
import com.kobylynskyi.graphql.codegen.model.GeneratedInformation;
import com.kobylynskyi.graphql.codegen.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static final ZonedDateTime GENERATED_DATE_TIME =
            ZonedDateTime.parse("2020-12-31T23:59:59-0500", GeneratedInformation.DATE_TIME_FORMAT);

    public static File getFileByName(File[] files, String fileName) throws FileNotFoundException {
        return Arrays.stream(files)
                .filter(f -> f.getName().equalsIgnoreCase(fileName))
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }

    public static void assertSameTrimmedContent(File expected, File file) throws IOException {
        String expectedContent = Utils.getFileContent(expected.getPath()).trim();
        String actualContent = Utils.getFileContent(file.getPath()).trim();
        assertEquals(expectedContent, actualContent);
    }

    public static GeneratedInformation getStaticGeneratedInfo() {
        GeneratedInformation generatedInformation = new GeneratedInformation();
        generatedInformation.setDateTimeGenerator(new DateTimeGenerator() {
            @Override
            public ZonedDateTime newDateTime() {
                return GENERATED_DATE_TIME;
            }
        });
        return generatedInformation;
    }

}
