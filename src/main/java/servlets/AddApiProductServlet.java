package servlets;

import com.google.gson.Gson;
import common.APIResult;
import common.Config;
import helper.HttpHelper;
import helper.ServletUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import org.json.JSONObject;

public class AddApiProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

                int addProduct = ProductModel.addProduct(name, image, price, status);
                if (addProduct >=0) {
                    result.setErrorCode(0);
                    result.setMessage("Thêm sản phẩm thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thêm sản phẩm thất bại!");
                }
                break;
            }

            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));
    }
}
