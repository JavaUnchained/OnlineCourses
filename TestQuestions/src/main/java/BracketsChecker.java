import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketsChecker {

    public boolean isCorrectBrackets(String exp){
        String e = clearing(exp);// чистим от всего кроме скобок
        return even(e) ? deleteNeighbors(e) : false;
    }

    // очищаем строку от всего кроме скобок
    private String clearing(String exp){
        Pattern pattern = Pattern.compile("[^(\\[{}\\])]");
        Matcher matcher = pattern.matcher(exp);
        return matcher.replaceAll("").trim();
    }

    private boolean even(String exp){return exp.length()%2 == 0;}

    private boolean deleteNeighbors(String exp){
        Pattern pattern = Pattern.compile("[(\\{\\})|(\\[\\])|(\\(\\))]?");
        Matcher matcher = pattern.matcher(exp);
        while (exp.contains("{}") || exp.contains("()") || exp.contains("[]")){
            exp = matcher.replaceAll("");
        }
        return exp.length() == 0;
    }
}