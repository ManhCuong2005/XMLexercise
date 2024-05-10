import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập đường dẫn của thư mục: ");
        String directoryPath = scanner.nextLine();

        File directory = new File(directoryPath);

        // Kiểm tra xem đường dẫn nhập vào có tồn tại không
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Thư mục không tồn tại hoặc không hợp lệ.");
            return;
        }

        // Gọi phương thức để tạo file XML
        try {
            createXMLFile(directory);
            System.out.println("Đã tạo file XML thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi tạo file XML: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Phương thức để tạo file XML từ cây thư mục
    private static void createXMLFile(File directory) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("D:\\test\\test.xml");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<directory>\n");

            createXMLForDirectory(directory, writer);

            writer.write("</directory>");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Phương thức để tạo XML cho thư mục và các tệp
    private static void createXMLForDirectory(File directory, FileWriter writer) throws IOException {
        File[] files = directory.listFiles();

        // Duyệt qua từng tệp và thư mục
        for (File file : files) {
            if (file.isDirectory()) {
                writer.write("<" + file.getName() + ">\n");
                createXMLForDirectory(file, writer);
                writer.write("</" + file.getName() + ">\n");
            } else {
                // Nếu là tệp, ghi thẻ <file> với tên tệp
                writer.write("<file>");
                writer.write(file.getName());
                writer.write("</file>\n");
            }
        }
    }
}
