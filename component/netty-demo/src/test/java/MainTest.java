import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class MainTest {

    @Test
    public void test() throws IOException {
        final AtomicInteger dirCount = new AtomicInteger();
        final AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("C:\\all\\env\\jdk\\jdk1.8.0_301"), new SimpleFileVisitor<Path>() {
            // 访问文件夹之前的回调
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            // 访问文件之前的回调
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("dirCount: " + dirCount);
        System.out.println("fileCount: " + fileCount);
    }


}
