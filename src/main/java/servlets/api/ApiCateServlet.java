package servlets.api;

import com.google.gson.Gson;
import common.APIResult;
import entity.Categogy;
import entity.Product;
import helper.HttpHelper;
import helper.ServletUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CateModel;
import model.ProductModel;
import org.json.JSONObject;

public class ApiCateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        List<Categogy> listCategogies = CateModel.getListCategogies();
        ServletUtil.printJson(request, response, gson.toJson(listCategogies));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                String name = jData.optString("cateName");

                int addCate = CateModel.addCate(name);
                if (addCate >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thêm danh mục thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thêm danh mục thất bại!");
                }
                break;
            }

            case "edit": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("idCate");
                String name = jData.optString("name");

                int editCate = CateModel.editCate(id, name);
                if (editCate >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Sửa danh mục thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Sửa danh mục thất bại!");
                }
                break;
            }
            case "delete": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("id");
                int removeCate = CateModel.removeCate(id);
                if (removeCate >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Xóa danh mục thành công!");
                } else {
                    result.setErrorCode(-2);
                    result.setMessage("Xóa danh mục thất bại!");
                }
                break;
            }

            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));
    }
}
