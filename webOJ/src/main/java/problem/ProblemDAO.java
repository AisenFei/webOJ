package problem;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//数据访问层
public class ProblemDAO {
    //获取所有题目信息
    public List<Problem> selectAll(){
        List<Problem> result = new ArrayList<>();
        //1. 获取数据库连接
        Connection connection = DBUtil.getConection();
        //2. 拼装SQL语句
        String sql = "select * from oj_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            //3. 执行SQL语句
            resultSet = statement.executeQuery();
            //4. 遍历结果集
            while(resultSet.next()){
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
//                problem.setDescription(resultSet.getString("description"));
//                problem.setTemplateCode(resultSet.getString("templateCode"));
//                problem.setTestCode(resultSet.getString("testCode"));
                result.add(problem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5. 关闭释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return result;
    }

    //获取指定id的题目信息
    public Problem selectOne(int id){
        Problem problem = new Problem();
        //1. 获取数据库连接
        Connection connection = DBUtil.getConection();
        //2. 拼接SQL语句
        String sql = "select * from oj_table where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            //3. 执行SQL语句
            resultSet = statement.executeQuery();
            //4. 封装结果集
            if (resultSet.next()){
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
            }
            return problem;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //新增一个题目到数据库中
    public void insert(Problem problem){
        //1. 获取数据库连接
        Connection connection = DBUtil.getConection();
        PreparedStatement statement = null;
        //2. 拼装SQL语句
        String sql = "insert into oj_table values (null,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            System.out.println("insert: "+statement);
            //3. 执行SQL语句
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //4. 关闭释放相关资源
            DBUtil.close(connection,statement,null);
        }
    }

    //删除指定题目信息
    public void delete(int id){
        //1. 获取链接
        Connection connection = DBUtil.getConection();
        //2. 拼接SQL
        String sql = "delete from oj_table where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            //3. 执行sql语句
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //4. 关闭释放资源
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) {
        ProblemDAO problemDAO = new ProblemDAO();
        //1. 验证insert 操作
        Problem problem = new Problem();
        problem.setTitle("两数之和");
        problem.setDescription("给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "\n" +
                "你可以按任意顺序返回答案。\n" +
                "\n" +
                "来源：力扣（LeetCode）\n" +
                "链接：https://leetcode-cn.com/problems/two-sum\n" +
                "著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。");
        problem.setLevel("简单");
        problem.setTemplateCode("public class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        int len = 2;\n" +
                "        int[] result = null;\n" +
                "        boolean flag = true;\n" +
                "        if(flag && ((result = solution.twoSum(new int[]{2,7,11,15},9)) == null || result.length != len || result[0] != 0 || result[1] != 1)){\n" +
                "            System.out.println(\"{2,7,11,15},9 用例不通过\");\n" +
                "            flag = false;\n" +
                "        }\n" +
                "        if(flag && ((result = solution.twoSum(new int[]{3,2,4},6)) == null || result.length != len || result[0] != 1 || result[1] != 2)){\n" +
                "            System.out.println(\"{3,2,4},6 用例不通过\");\n" +
                "            flag = false;\n" +
                "        }\n" +
                "        if(flag && ((result = solution.twoSum(new int[]{3,3},6)) == null || result.length != len || result[0] != 0 || result[1] != 1)){\n" +
                "            System.out.println(\"{3,3},6 用例不通过\");\n" +
                "            flag = false;\n" +
                "        }\n" +
                "        if(flag){\n" +
                "            System.out.println(\"恭喜用例全部通过\");\n" +
                "        }\n" +
                "    }");
        problemDAO.insert(problem);
        System.out.println(problem);
    }
}
