package tags;

import utils.WordsService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Definition extends SimpleTagSupport {

    private String word;
    public void setWord(String word)
    {
        this.word=word;
    }
    @Override
    public void doTag() throws JspException, IOException {
        String definition=null;
        if(word!=null)
        {
            definition= WordsService.getDefinition(word);
        }
        else
        {
            definition="No word provided";
        }
        JspWriter out=getJspContext().getOut();
        out.println(definition);
    }
}
