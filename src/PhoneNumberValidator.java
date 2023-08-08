import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    public static void main(String[] args) {
        String fileName = "src/file.txt";
        List<String> validPhoneNumbers = processPhoneNumbers(fileName);
        Collections.sort(validPhoneNumbers, new PhoneNumberComparator());
        for (String phoneNumber : validPhoneNumbers) {
            System.out.println(phoneNumber);
        }
    }

    public static List<String> processPhoneNumbers(String fileName) {
        List<String> validPhoneNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    validPhoneNumbers.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validPhoneNumbers;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        Pattern phonePattern = Pattern.compile(pattern);
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }

    static class PhoneNumberComparator implements Comparator<String> {
        Map<String, Integer> frequencyMap = new HashMap<>();

        public int compare(String phoneNumber1, String phoneNumber2) {
            int frequency1 = frequencyMap.getOrDefault(phoneNumber1, 0);
            int frequency2 = frequencyMap.getOrDefault(phoneNumber2, 0);
            return Integer.compare(frequency2, frequency1); // Зворотній порядок для зростання частоти
        }
    }
}
