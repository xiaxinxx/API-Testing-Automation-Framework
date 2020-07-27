package com.allen.dataprovider;

import com.allen.utils.ExcelUtil;
import com.allen.utils.FileUtil;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @Desc
 * 1、数据驱动文件路径 projectName/testdata/className.xls或projectName/testdata/className.xlsx
 * 2、不读取文件中的第一行数据，默认为表头
 * 3、methodName 作为Excel中的sheetName读取数据
 * 4、Excel的格式只支持 Excel97-2004（xls） 和  Excel工作簿（xlsx）
 **/
public class ExcelDataProvider {

    public final static String DEFAULT_PROVIDER = "excelData";
    private final static String DEFAULT_FILE_PREFIX = "/src/test/testdata/";
    private final static String XLSX_SUFFIX = ".xlsx";
    private final static String XLS_SUFFIX = ".xls";
    private final static String DEFAULT_ENCODING = "UTF-8";


    @DataProvider(name = DEFAULT_PROVIDER)
    public static Object[][] prepareData(Method method) throws Exception {
        String directoryName = String.format("%s%s", System.getProperty("user.dir"), DEFAULT_FILE_PREFIX);
        FileUtil.ifDirectoryNotExistThenCreated(directoryName);
        Class<?> declaringClass = method.getDeclaringClass();
        String[] split = declaringClass.getName().split("\\.");
        String className =split[split.length-1];
        String filePath = String.format("%s%s%s", directoryName, className, XLSX_SUFFIX);
        File f = new File(filePath);
        if(!f.exists()){
            filePath = String.format("%s%s%s", directoryName, className, XLS_SUFFIX);
        }
        return ExcelUtil.getTable(filePath, method.getName(),1);
    }
}
