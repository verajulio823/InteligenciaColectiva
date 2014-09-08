package ci.tagcloud.impl;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ci.tagcloud.TagCloud;
import ci.tagcloud.TagCloudElement;
import ci.tagcloud.VisualizeTagCloudDecorator;


public class HTMLTagCloudDecorator implements VisualizeTagCloudDecorator {
    private static final String HEADER_HTML =
        "<html><br><head><br><title>TagCloud <br></title><br></head>";
    private static final int NUM_TAGS_IN_LINE = 5;
    private Map<String, String> fontMap = null;
    private static final String HEADER = "<html><br><head><br><title>TagCloud <br></title><br></head>";
    private static final int NUM_TAGS_PER_LINE = 10;
    public HTMLTagCloudDecorator() {
        getFontMap();
    }
    
    private void getFontMap() {
        //For your application, read mapping from xml file
        this.fontMap = new HashMap<String,String>();
        fontMap.put("font-size: 0", "font-size: 13px");
        fontMap.put("font-size: 1", "font-size: 20px");
        fontMap.put("font-size: 2", "font-size: 24px");
    }
    
    public String decorateTagCloud(TagCloud tagCloud,String href,String head) {
        StringWriter sw = new StringWriter();
        List<TagCloudElement> elements = tagCloud.getTagCloudElements();
        //sw.append(HEADER_HTML);
        //sw.append("<br><body><h3>TagCloud (" +  elements.size() +")</h3>");
        sw.append(head);
        int count = 0;
        for (TagCloudElement tce :  elements) {
            sw.append("&nbsp;<a href=\""+href+tce.getTagText()+"\" style=\""+ this.fontMap.get(tce.getFontSize())+";\">" );
            sw.append(tce.getTagText() +"</a>&nbsp;");
            if (count++ == NUM_TAGS_IN_LINE) {
                count = 0;
                sw.append("<br>" );
            }
        }
        //sw.append("<br></body><br></html>");
        System.out.println(sw.toString());
        return sw.toString();           
    }
    public String decorateTagCloud(TagCloud tagCloud) {
    	StringBuilder sb = new StringBuilder();
    	List<TagCloudElement> tagElements = tagCloud.getTagCloudElements();
    	//sb.append(HEADER);
    	sb.append("<br><body><h3>TagCloud (" + tagElements.size() +")</h3>");
    	int count = 0;
    	for (TagCloudElement e : tagElements) {
    	sb.append("&nbsp;<a style=\""+ this.fontMap.get(e.getFontSize())+";\">" );
    	sb.append(e.getTagText() +"</a>&nbsp;");
    	if (count++ == NUM_TAGS_PER_LINE) {
    	count = 0;
    	sb.append("<br>" );
    	}
    	}
    	//sb.append("<br></body><br></html>");
    	return sb.toString();
    	}

}
