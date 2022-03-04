import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;

public class TestJson {
    public static void main(String[] args) {
        Problem problem = new Problem();
        problem.setId(1);
        problem.setLevel("简单");
        problem.setDescription("描述");
        problem.setTemplateCode("模板");
        problem.setTestCode("null");
        Gson gson = new GsonBuilder().create();
        String s = gson.toJson(problem);
        System.out.println(s);
        Problem problem1 = gson.fromJson(s,Problem.class);
        System.out.println(problem1);
    }
}
