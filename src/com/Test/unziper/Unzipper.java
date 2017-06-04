package com.Test.unziper;

/**
 * Created by olmer on 14.04.17.
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.zip.ZipFile;

public class Unzipper {

    @Autowired
    BatchWriter batchWriter;

    String folderPath = "X:\\zakupki";
    String outFile = "C:\\Prj\\out\\out.txt";

    public void unzip() {
        try (final BufferedWriter out = new BufferedWriter(new FileWriter(outFile))) {
            FileUtils.listFiles(new File(folderPath)
                    , new SuffixFileFilter("ZIP", IOCase.INSENSITIVE)
                    , TrueFileFilter.INSTANCE)
                    .stream()
                    .map(f -> {
                        try {
                            return new ZipFile(f);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .flatMap(z -> z.stream()
                            .map(e -> {
                                try {
                                    //TODO заменить метод toString на метод с выбором кодировки
                                    return new String[]{e.getName(), IOUtils.toString(z.getInputStream(e))};
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                return null;
                            }))
                    .filter(Objects::nonNull)
                    .forEach(batchWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
