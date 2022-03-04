package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProblemServlet extends HttpServlet {
    Gson gson = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || "".equals(id)){
            //没有id这个参数，执行"查找全部"逻辑
            selectAll(resp);
        }else {
            //存在id，查找指定题目
            selectOne(Integer.parseInt(id),resp);
        }
    }

    private void selectOne(int id, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(id);
        problem.setTestCode("");
        //转换为json格式
        String s = gson.toJson(problem);
        resp.getWriter().write(s);
    }

    private void selectAll(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        //把结果组织成json格式，
        //只需要id，标题，难度三个属性
        String jsonString = gson.toJson(problems);
        resp.getWriter().write(jsonString);
    }
}
