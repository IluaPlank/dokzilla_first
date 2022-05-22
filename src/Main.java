import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //передача пути где искать все файлы
        searchForFilesInFolders("C:/Users/gerev/Desktop/Новая папка");
    }

    public static void searchForFilesInFolders(String path) {
        try {
            List<String> result = Files.walk(Paths.get(path))
                    .map(Path::toString)
                    .filter(f -> f.endsWith(".txt")).toList();
            writingToFile(sortFile(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writingToFile(List<String> path) {
        for (String pathFile : path) {
            try (
                    FileReader reader = new FileReader(pathFile);
                    FileWriter writer = new FileWriter("backet.txt", true);
                    Scanner scan = new Scanner(reader)
            ) {
                while (scan.hasNextLine()) {
                    writer.write(scan.nextLine() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<String> sortFile(List<String> path) {
        List<String> sortedPath = new ArrayList<>();
        List<String> nameFiles = new ArrayList<>();
        for (String pathFile : path) {
            String[] pathName = pathFile.split("\\\\");
            nameFiles.add(pathName[pathName.length - 1]);
        }
        Collections.sort(nameFiles);
        for (String name : nameFiles) {
            for (String pathFile : path) {
                if (pathFile.contains(name)) {
                    sortedPath.add(pathFile);
                    break;
                }
            }
        }
        sortedPath.forEach(System.out::println);//проверка что отсортировалось
        return sortedPath;

//      по второй части задания ,я не придумал как лучше переставить местами файлы когда нашел зависимость.
//        for (String pathFile : map.values()){
//            try (
//                    FileReader reader = new FileReader(pathFile);
//                    Scanner scan = new Scanner(reader)
//            ) {
//                while (scan.hasNextLine()){
//                    String line = scan.nextLine();
//                    if (line.equals("require")){
////
//                        String[] addiction = line.split("‘");
//                        String addictionFile = addiction[1];
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
