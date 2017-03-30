package web.spider;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;

import com.sun.jersey.server.linking.LinkFilter;

public class HtmlParserTool {
	//获取一个网站上的链接,filter用来过滤连接
	public static Set<String> extracLinks(String url,LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			//过滤<frame>标签的filter,用来提取frame标签里的src属性
			NodeFilter frameFilter = new NodeFilter() {
				
				@Override
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src="))
					return true;
					return false;
				}
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
