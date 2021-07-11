package servlets.api;

import com.google.gson.Gson;
import common.APIResult;
import common.Config;
import entity.Product;
import helper.HttpHelper;
import helper.ServletUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import org.json.JSONObject;

public class ApiProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        List<Product> listProducts = ProductModel.getListProducts();
        ServletUtil.printJson(request, response, gson.toJson(listProducts));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                String name = jData.optString("name");
                String image = jData.optString("image");
                int price = jData.optInt("price");
                String status = jData.optString("status");
                int idCate = jData.optInt("idCate");

                int addProduct = ProductModel.addProduct(name, image, price, status, idCate);
                if (addProduct >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thêm sản phẩm thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thêm sản phẩm thất bại!");
                }
                break;
            }

            case "edit": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("id");
                int idCate = jData.optInt("idCate");
                String name = jData.optString("name");
                String image = jData.optString("image");
                int price = jData.optInt("price");
                String status = jData.optString("status");

                int editProduct = ProductModel.editProduct(id, idCate, name, image, price, status);
                if (editProduct >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Sửa sản phẩm thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Sửa sản phẩm thất bại!");
                }
                break;
            }
            case "delete": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                int id = jData.optInt("id");
                int removeProduct = ProductModel.removeProduct(id);
                if (removeProduct >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Xóa sản phẩm thành công!");
                } else {
                    result.setErrorCode(-2);
                    result.setMessage("Xóa sản phẩm thất bại!");
                }
                break;
            }
            case "search": {
                String bodyData = HttpHelper.getBodyData(request);
                JSONObject jData = new JSONObject(bodyData);
                String query = jData.optString("query");
                int idCate = jData.optInt("idCate");
                String status = jData.optString("status");
                List<Product> searchProduct = ProductModel.searchProduct(query, idCate, status);
                result.setData(searchProduct);
                break;
            }
           


            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));
    }
}
