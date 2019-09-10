import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @ClassName: FileUtils
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class FileUtils {
    public static void saveFile(InputStream inputStream, File outFile) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//创建一个Buffer字符串
        byte[] buffer = new byte[1024];
//每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
//使用一个输入流从buffer里把数据读取出来
        while ((len = inputStream.read(buffer)) != -1) {
//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
//关闭输入流
        inputStream.close();
//把outStream里的数据写入内存

//得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = outStream.toByteArray();
//创建输出流
        FileOutputStream fileOutStream = new FileOutputStream(outFile);
//写入数据
        fileOutStream.write(data);
    }
}
