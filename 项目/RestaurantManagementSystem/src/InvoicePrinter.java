import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InvoicePrinter {

    public void printInvoice(String content) {
        try {
            // 创建临时文件
            File tempFile = File.createTempFile("invoice", ".txt");
            tempFile.deleteOnExit();
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(content);
            }

            // 打开默认的打印对话框进行打印
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().print(tempFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
