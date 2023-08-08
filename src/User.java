import java.io.*;
import java.util.*;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

 class Main {
    public static void main(String[] args) {
        String inputFileName = "src/file2.txt";
        String outputFileName = "user.json";

        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJson(users, outputFileName);

        Map<String, Integer> wordFrequency = getWordFrequency(inputFileName);
        wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(" ");
                if (tokens.length >= 2) {
                    String name = tokens[0];
                    int age = Integer.parseInt(tokens[1]);
                    users.add(new User(name, age));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static void writeUsersToJson(List<User> users, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write("    {\n");
                writer.write("        \"name\": \"" + user.name + "\",\n");
                writer.write("        \"age\": " + user.age + "\n");
                writer.write("    }");
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> getWordFrequency(String fileName) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(" ");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        wordFrequency.put(token, wordFrequency.getOrDefault(token, 0) + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }
}
