package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class Test {
    private Test() {
    }

    public static void main(final String[] args) {

        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get("file.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.CREATE)) {

            ByteBuffer buffer = ByteBuffer.allocate(26);

            for (int i = 0; i < buffer.capacity(); i++) {
                buffer.put((byte) ('A' + 1));
            }

            buffer.rewind();
            channel.write(buffer);
        } catch (IOException exception) {
            System.out.println("Input / Output Error!");
        }

    }
}
