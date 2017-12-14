package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapContents 
{
	static ArrayList<String> h4 = new ArrayList<>();
	static ArrayList<TreeMap<String,String>> al = new ArrayList<TreeMap<String, String>>();
	static String content_html;
	public static void getContents()
	{
		try
		{
			Document doc = Jsoup.connect("https://www.tutorialspoint.com/").get();
			Elements col_md = doc.select(".col-md-3");
			
			for(Element e:col_md.select("h4"))
			{
				h4.add(e.text());
			}
//			int counter = 0;
			
			for(Element subject_head : col_md)
			{
				for(Element col:subject_head.select("ul"))
				{
					TreeMap<String, String> temp_hs = new TreeMap<>();
					for(Element chapters:col.select("li"))
					{
//						System.out.println(chapters.text()+"---->"+chapters.select("a").attr("href"));
						temp_hs.put(chapters.select("a").attr("href"), chapters.text());
					}
					al.add(temp_hs);
					temp_hs = null;	
				}
			}
//			counter = 0;
//			for(TreeMap h:al)
//			{
//				Iterator<Map.Entry<Integer, Integer>> it = h.entrySet().iterator();
//				while (it.hasNext()) 
//				{
//				    Map.Entry<Integer, Integer> pair = it.next();
//				    System.out.println(counter+1+"--->"+h4.get(counter)+"--->"+"https://www.tutorialspoint.com"+pair.getKey()+"---->"+pair.getValue());
//				}
//				if(counter++ == 24)
//					break;
//			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid Website");
		}
	}
	public static HashMap<String, String> getEndContent(String url) throws IOException 
	{
//		System.out.println(url);
		Document doc = Jsoup.connect(url).get();
		Elements content = doc.select(".col-md-7.middle-col");
		Elements img = doc.select(".cover");
		Elements pre_btn = doc.select(".pre-btn");
		Elements post_btn = doc.select(".nxt-btn");
		ScrapContents.content_html = content.toString().replace(img.toString(), "").replace(pre_btn.toString(), "").replace(post_btn.toString(), "");
//		System.out.println(content_html);
		HashMap<String, String> menu = new HashMap<>();
		for(Element e:doc.select("ul.nav.nav-list.primary.left-menu"))
		{
			for(Element li:e.select("li")) 
			{
				menu.put(li.select("a").attr("href"), li.text());
//				menu_urls.add(li.select("a").attr("href"));
//				menu_url_text.add(li.text());
				//System.out.println(li.text()+"----->"+li.select("a").attr("href"));
			}
		}
		return menu;
	}
}
