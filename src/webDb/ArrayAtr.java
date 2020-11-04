package webDb;

public class ArrayAtr {
	private int barcode; 
    private String name; 
    private String color;
    private	String description;
    // Parameterized Constructor to set ArrayAtr 
    // name, barcode,color,description, enrolled in. 
    public ArrayAtr(int a, String n, String c,String d ) 
    { 
        this.name = n; 
        this.barcode = a; 
        this.color = c;
        this.description = d;
    } 
    // Setter Methods to set table data to be 
    // displayed 
    public String getName() { return name; } 
    public int getBarcode() { return barcode; } 
    public String getColor() { return color; } 
    public String getDescription() { return description; }

}
