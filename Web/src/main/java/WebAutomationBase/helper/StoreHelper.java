package WebAutomationBase.helper;

import WebAutomationBase.model.ElementInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

public enum StoreHelper
{
    INSTANCE;
    Logger logger = LoggerFactory.getLogger(getClass());
    static File[] fileList = null;
    ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();

    StoreHelper() {
        try {
            String currentWorkingDir = System.getProperty("user.dir");
            initMap(getFileList(currentWorkingDir + "/src"));
        }
        catch (Exception ex) {
            System.out.println(ex + " \n" +
                    "Error while searching json files.");
        }
        // initMap(readJsonFile());
    }


    public void initMap(List<File> fileList) {
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList = null;
        for (File file : fileList) {
            try {
                elementInfoList = gson
                        .fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
            } catch (FileNotFoundException e) {

            }
        }
        System.out.println(elementMapList.size() + " Adet element listeye eklendi");
    }

    public List<File> getFileList(String directoryName) throws IOException {
        List<File> dirList = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(Paths.get(directoryName))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                if (f.toString().endsWith(".json")) {
                    logger.info(f.toFile().getName() + " adlı json dosyası bulundu.");
                    dirList.add(f.toFile());
                }
            });
        }
        return dirList;
    }
    public void printAllValues() {
        elementMapList.forEach((key, value) -> logger.info("Key = {} value = {}", key, value));
    }


    public ElementInfo findElementInfoByKey(String key) {
        return (ElementInfo) elementMapList.get(key);
    }

    public String saveValue(String key, String value) {
        elementMapList.put(key, value);
        return key;
    }

    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }

}
