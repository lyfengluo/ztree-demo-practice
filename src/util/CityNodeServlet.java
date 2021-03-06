package util;

import java.io.IOException;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CityNodeServlet
 */
@WebServlet("/CityNodeServlet")
public class CityNodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CityDAO c = new CityImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityNodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = request.getParameter("id")==null? 0 : Integer.parseInt(request.getParameter("id"));
		List<City> e = c.findTypeChildrenById(id);
		JSONArray jsonArray = new JSONArray();
		for(City temp : e){
			TreeNode treeNode = new TreeNode();
			JSONObject jO = new JSONObject();
			treeNode.setId(temp.getId());
			jO.put("id", treeNode.getId());
			treeNode.setpId(temp.getpId());
			jO.put("pId", treeNode.getpId());
			treeNode.setNodeName(temp.getCity());
			jO.put("nodeName", treeNode.getNodeName());
			jO.put("open", treeNode.getOpen());
			jO.put("checked", false);
			List<City> cities = c.findTypeChildrenById(temp.getId());
			if(cities.size() ==0)
				treeNode.setIsParent(false);
			jO.put("isParent", treeNode.getIsParent());
			jsonArray.add(jO);
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(jsonArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
