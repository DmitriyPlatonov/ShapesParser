import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ShapesParser {

    private static Document getDocument(String filePath) throws Exception {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(new File(filePath));

        } catch (Exception exception) {
            String message = "XML file parsing error!";
            throw new Exception(message);
        }
    }

    private static ArrayList<Shape> ParseShapes(Document doc) {
        String[] shape = {"triangle", "rectangle", "circle", "square"};
        ArrayList<Shape> shapesList = new ArrayList<>();

        for (int i = 0; i < shape.length; i++)
            if (doc.getElementsByTagName(shape[i]).getLength() > 0) {
                NodeList nList = doc.getElementsByTagName(shape[i]);
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        String color = eElement.getElementsByTagName("color").item(0).getTextContent(); // shape color
                        int sideCount = eElement.getElementsByTagName("side").getLength(); // number of sides
                        float[] side = new float[sideCount]; // sides
                        for (int k = 0; k < sideCount; k++)
                            side[k] = Float.parseFloat(eElement.getElementsByTagName("side").item(k).getTextContent());

                        switch (shape[i]) {
                            case "triangle":
                                shapesList.add(new Triangle(color, side[0], side[1], side[2]));
                                break;
                            case "rectangle":
                                shapesList.add(new Rectangle(color, side[0], side[1]));
                                break;
                            case "square":
                                shapesList.add(new Square(color, side[0]));
                                break;
                            case "circle":
                                float diameter = Float.parseFloat(eElement.getElementsByTagName("diameter").item(0).getTextContent());
                                shapesList.add(new Circle(color, diameter));
                                break;
                        }
                    }
                }
            }
        return shapesList;
    }

    public static void main(String[] args) throws Exception {

        String filePath = args[0];
        Document doc = getDocument(filePath);
        ArrayList<Shape> shapesList = ParseShapes(doc);

        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", s);

        for (int i=0; i<shapesList.size(); i++) {
            System.out.println(i + ": " + shapesList.get(i).getColor() + " - " + df.format(shapesList.get(i).getArea()));
        }
    }
}