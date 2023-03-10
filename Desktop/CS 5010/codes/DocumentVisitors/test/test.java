import document.Document;
import document.MarkdownStringVisitor;
import document.element.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class test {
    Document doc;
    BasicText single;
    Heading topHeading;
    Heading khoury;
    HyperText link;
    Heading summary;
    BoldText bold;
    Paragraph para;



    @Before
    public void setup() {
        topHeading = new Heading("Top Level Heading", 1);
        single = new BasicText("Just text is an important part of every document.");
        khoury = new Heading("Khoury College Website", 3);
        link = new HyperText("Link to Khoury College", "https://www.khoury.northeastern.edu/");
        summary = new Heading("Summary", 2);
        bold = new BoldText("Bold text so it's more important!");

        para = new Paragraph();
        BasicText para1 = new BoldText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        BasicText para2 = new BasicText("Ut enim ad minim veniam, quis nostrud exercitation "
                + "ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        BasicText para3 = new ItalicText("Duis aute irure dolor in reprehenderit in voluptate velit "
                + "esse cillum dolore eu fugiat nulla pariatur.");
        para.add(para1);
        para.add(para2);
        para.add(para3);

        doc = new Document();
    }

    @Test
    public void testMarkdownStringVisitor() {
        String expected = "";
        assertEquals("Failed with empty document", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "# Top Level Heading";
        doc.add(topHeading);
        assertEquals("Failed after adding level 1 heading ...", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "\nJust text is an important part of every document.";
        doc.add(single);
        assertEquals("Failed after adding basic text ...", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "\n### Khoury College Website";
        doc.add(khoury);
        assertEquals("Failed after adding level 3 heading ...", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "\n[Link to Khoury College](https://www.khoury.northeastern.edu/)";
        doc.add(link);
        assertEquals("Failed after adding hypertext ...", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "\n## Summary";
        doc.add(summary);
        assertEquals("Failed after adding level 2 heading ...", expected, doc.toText(new MarkdownStringVisitor()));

        expected += "\n**Bold text so it's more important!**";
        doc.add(bold);
        assertEquals("Failed after adding bold text ...", expected, doc.toText(new MarkdownStringVisitor()));

        String expectedParagraph = "\n\n**Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.**\n"
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi "
                + "ut aliquip ex ea commodo consequat.\n*Duis aute irure dolor in reprehenderit "
                + "in voluptate velit esse cillum dolore eu fugiat nulla pariatur.*";

        expected += expectedParagraph;
        doc.add(para);
        assertEquals("Failed after adding paragraph...", expected, doc.toText(new MarkdownStringVisitor()));
    }

    @Test
    public void test() {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new BoldText("bbbbbb"));
        paragraph.add(new ItalicText("cccccc"));
        paragraph.add(new Heading("dddddd", 1));
        paragraph.add(new Heading("eeeeeee", 2));
        paragraph.add(new HyperText("mmmmm", "www.google.com"));
        Document document = new Document();
        document.add(paragraph);
        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(new BoldText("ooooo"));
        paragraph2.add(new ItalicText("iiiii"));
        document.add(paragraph2);
        System.out.println("------\n" + document.toText(new MarkdownStringVisitor()) + "\n--------");
    }


}
