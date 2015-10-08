/**
 * Created by dmitriy on 07.10.15.
 */
public abstract class Shape{
    public abstract float getArea();
    public abstract String getColor();
}

class Circle extends Shape{
    String color;
    float diameter;
    public Circle(String color, float diameter){
        this.color = color;
        this.diameter = diameter;
    }
    public String getColor() {return color;}
    public float getArea(){
        return (float)Math.PI * (diameter/2) * (diameter/2);
    }
}

class Triangle extends Shape{
    String color;
    float width, height, length, p;

    public Triangle(String color, float width, float height, float length){
        this.color = color;
        this.height = height;
        this.width = width;
        this.length = length;

    }
    public String getColor() {return color;}
    public float getArea(){
        p = (width + height + length)/2;
        return (float)(Math.sqrt(p*(p-width)*(p-height)*(p-length)));
    }
}

class Square extends Shape{
    String color;
    float height;

    public Square(String color, float height){
        this.color = color;
        this.height = height;
    }
    public String getColor() {return color;}
    public float getArea(){
        return height * height;
    }
}

class Rectangle extends Shape{
    String color;
    float height, width;

    public Rectangle(String color, float height, float width){
        this.color = color;
        this.height = height;
        this.width = width;
    }

    public String getColor() {return color;}
    public float getArea(){
        return height * width;
    }
}
