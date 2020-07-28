package com.allen.dataprovider;

import com.allen.utils.FileUtil;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 1、数据驱动文件路径 projectName/src/test/testdata/className/methodName.csv或projectName/testdata/methodName.csv
 * 2、不读取文件中的第一行数据，默认为表头
 * 3、默认分隔符是逗号（，），可以在beforeTest等方法中使用setCsvSeprator方法自定义分隔符
 * 4、@Test使用例子   @Test(dataProvider = CSVDataProvider.DEFAULT_PROVIDER, dataProviderClass = CSVDataProvider.class)
 **/
@Slf4j
public class CsvDataProvider {

    public final static String DEFAULT_PROVIDER = "csvData";
    private final static String DEFAULT_FILE_PREFIX = "/src/test/testdata/";
    private final static String DEFAULT_FILE_SUFFIX = ".csv";
    private final static String DEFAULT_ENCODING = "UTF-8";


    public static void setCsvSeprator(char csvSeprator) {
        CsvDataProvider.csvSeprator = csvSeprator;
    }


    private static char csvSeprator = ',';


    @DataProvider(name = DEFAULT_PROVIDER)
    public static Object[][] prepareData(Method method) {
        CSVReader reader = null;
        String directoryName = String.format("%s%s", System.getProperty("user.dir"), DEFAULT_FILE_PREFIX);
        FileUtil.ifDirectoryNotExistThenCreated(directoryName);
        Class<?> declaringClass = method.getDeclaringClass();
        String[] split = declaringClass.getName().split("\\.");
        String className =split[split.length-1];
        String filePath = String.format("%s%s/%s%s", directoryName, className, method.getName(), DEFAULT_FILE_SUFFIX);
        File f = new File(filePath);
        if (!f.exists()){
            filePath = String.format("%s%s%s", directoryName, method.getName(), DEFAULT_FILE_SUFFIX);
        }
        String systemEncoding = System.getProperty("file.encoding");
        try {
            File file = new File(filePath);
            //从第二行开始读取数据，排除第一行的header
            reader = new CSVReader(new InputStreamReader(new FileInputStream(file), systemEncoding), csvSeprator, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
        } catch (FileNotFoundException e) {
            log.error("未找到数据驱动文件："+ filePath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] csvRow;
        List<Object[]> csvList = new ArrayList<>();
        try {
            while ((csvRow = reader.readNext()) != null) {
                csvList.add(charsetConvert(csvRow, systemEncoding, DEFAULT_ENCODING));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] results = new Object[csvList.size()][];


        for (int i = 0; i < csvList.size(); i++) {
            results[i] = csvList.get(i);
        }
        return results;
    }


    /**
     * @param source
     * @param sourceCharset
     * @param targetCharset
     * @return
     */
    private static String[] charsetConvert(String[] source, String sourceCharset, String targetCharset) {
        String[] target = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            try {
                target[i] = new String(source[i].getBytes(sourceCharset), targetCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return target;
    }
}
