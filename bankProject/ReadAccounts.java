import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAccounts {

    private String filename;

    public ReadAccounts(String filename) {
        this.filename = filename;
    }

    public List<String> getFirstNames() throws IOException {
        List<String> firstNames = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            firstNames.add(fields[0]);
        }
        reader.close();
        return firstNames;
    }

    public List<String> getLastNames() throws IOException {
        List<String> lastNames = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            lastNames.add(fields[1]);
        }
        reader.close();
        return lastNames;
    }

    public List<Integer> getAccountNumbers() throws IOException {
        List<Integer> accountNumbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            accountNumbers.add(Integer.parseInt(fields[2]));
        }
        reader.close();
        return accountNumbers;
    }

    public List<Integer> getBalances() throws IOException {
        List<Integer> balances = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            balances.add(Integer.parseInt(fields[3]));
        }
        reader.close();
        return balances;
    }
}