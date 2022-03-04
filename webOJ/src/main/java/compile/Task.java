package compile;
import common.FileUtil;

import java.io.File;
import java.io.IOException;

//借助这个类来描述一次编译运行的过程
public class Task {
    //存放的目录
    private static final String WORK_DIR = "./tmp/";
    //要编译的代码的类名
    private static final String CLASS = "Solution";
    //要编译的代码对应的文件名，需要和类名一致
    private static final String CODE = "Solution.java";
    //标准输入对应的文件(实际上也没有用到)
    private static final String STDIN = WORK_DIR + "stdin.txt";
    //标准输出对应的文件(编译执行的代码的结果保存到这个文件中)
    private static final String STDOUT = WORK_DIR + "stdout.txt";
    //标准错误对应的文件(编译执行的代码的结果保存到这个文件中)
    private static final String STDERR = WORK_DIR + "stderr.txt";
    //编译错误对应的文件(编译出错时的具体原因)
    private static final String COMPILE_ERROR = WORK_DIR + "compile_error.txt";

    public Answer compileAndRun(Question question) throws IOException, InterruptedException {
        Answer answer = new Answer();
        //1. 先创建好存放临时文件的目录
        File worDir = new File(WORK_DIR);
        if(!worDir.exists()){
            worDir.mkdirs();
        }
        //2. 根据Question对象，构造需要的一些临时文件
        FileUtil.writeFile(WORK_DIR+CODE,question.getCode());
        FileUtil.writeFile(STDIN,question.getStdin());
        //3. 构造编译命令，并执行
        //   编译命令例如：javac -encoding utf8 ./tmp/Solution.java -d ./tmp/
        //   直接通过字符串拼接，有的时候如果太复杂，容易拼错，尤其是命令选项多的时候，很容易少空格之类的东西。
        //   String cmd = "javac -encoding utf8 " + CODE + " -d " + WORK_DIR;
        String cmd = String.format(
                "javac -encoding utf8 %s -d %s",WORK_DIR+CODE,WORK_DIR
        );
        System.out.println("编译命令："+cmd);
        CommandUtil.run(cmd,null,COMPILE_ERROR);
        //   判断编译是否出错，如果编译出错，则不需要继续向下运行了
        //   如果COMPILE_ERROR 文件为空，就表示编译顺利，如果非空就表示编译出错。
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if(!"".equals(compileError)){
            //编译出错
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        //4. 构造运行命令，并执行
        //   运行命令例如：java -classpath ./tmp/ Solution
        //   为了能让java 命令正确找到类对应的 .class 文件，需要指定加载路径，-classpath 选项来指定
        cmd = String.format(
                "java -classpath %s %s",WORK_DIR,CLASS
        );
        System.out.println("运行命令：" + cmd);
        CommandUtil.run(cmd,STDOUT,STDERR);
        //判定运行是否出错(是否存在异常),查看 STDERR 是否为空
        String stdError = FileUtil.readFile(STDERR);
        if(!"".equals(stdError)){
            System.out.println("运行出错");
            answer.setError(2);
            answer.setReason(stdError);
            answer.setStderr(stdError);
            return answer;
        }
        //5. 将最终的运行结果包装到Answer中。
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //验证 Task 是否能正确运行
        Question question = new Question();
        question.setCode(
                "public class Solution {\n" +
                "   public static void main(String[] args) {\n" +
                "       System.out.println(\"hello\");\n" +
                "   }\n" +
                "}\n"
        );
        question.setStdin("");
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}
