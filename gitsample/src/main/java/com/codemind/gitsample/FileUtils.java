package com.codemind.gitsample;

import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Optional;
import java.util.Set;

public class FileUtils {
    public static boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.contains("win");
    }

    public static String getPlatformPath(String path) {
        String gitConfigPath = path;
        if(FileUtils.isWindows()) {
            gitConfigPath = "//wsl$/Ubuntu" + path;
        }
        return gitConfigPath;
    }

    public static String toLinuxPath(Path path) {
        return path.toString().replace("\\", "/");
    }

    public static void makeDirectory(String path) throws IOException {
        Path directory = Paths.get(path);
        if(!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    public static void deleteDirectory(String path) throws IOException {
        FileSystemUtils.deleteRecursively(Paths.get(path));
    }

    public static Optional<Path> extractFindFirstFileByExtension(String directory, String extension) throws IOException {
        Path path = Paths.get(directory);
        return Files.list(path)
                .filter(file -> file.getFileName().toString().endsWith("." + extension))
                .findFirst();
    }

    public static boolean isSameFileContents(Path sourcePath, Path targetPath) throws IOException {
        byte[] sourceBytes = Files.readAllBytes(sourcePath);
        byte[] targetBytes = Files.readAllBytes(targetPath);
        return java.util.Arrays.equals(sourceBytes, targetBytes);
    }

}
