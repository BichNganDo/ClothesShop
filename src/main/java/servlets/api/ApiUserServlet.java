package servlets.api;

import com.google.gson.Gson;
import common.APIResult;
import entity.Product;
import entity.User;
import helper.HttpHelper;
import helper.SecurityHelper;
import helper.ServletUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import model.UserModel;
import org.eclipse.jetty.util.security.Password;
import org.json.JSONObject;

public class ApiUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        List<User> listUsers = UserModel.getListUsers();
        ServletUtil.printJson(request, response, gson.toJson(listUsers));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                String name = jData.optString("username");
                String password = SecurityHelper.getMD5Hash(jData.optString("password"));
                String phone = jData.optString("phone");
                String address = jData.optString("address");

                int addUser = UserModel.addUser(name, password, phone, address);
                if (addUser >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thêm người dùng thành công!");
                } else if(addUser == -6){
                    result.setErrorCode(-6);
                    result.setMessage("Số điện thoại dã tồn tại!");
                } else {
                    result.setErrorCode(-4);
                    result.setMessage("Thêm người dùng thất bại!");
                }
                break;
            }

            case "edit": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("id");
                String username = jData.optString("username");

                String password = jData.optString("password");
                String address = jData.optString("address");

                int editUser = UserModel.editUser(id, username, password, address);
                if (editUser >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Sửa người dùng thành công!");
                } else {
                    result.setErrorCode(-5);
                    result.setMessage("Sửa người dùng thất bại!");
                }
                break;
            }
            case "delete": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("id");
                int removeUser = UserModel.removeUser(id);
                if (removeUser >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Xóa người dùng thành công!");
                } else {
                    result.setErrorCode(-6);
                    result.setMessage("Xóa người dùng thất bại!");
                }
                break;
            }
            case "search": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                String query = jData.optString("query");
                List<Product> searchProduct = ProductModel.searchProduct(query);
                result.setData(searchProduct);
                break;
            }

            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));
    }
}
