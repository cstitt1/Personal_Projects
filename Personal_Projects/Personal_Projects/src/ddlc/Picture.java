package ddlc;
import java.awt.*;
import java.awt.image.BufferedImage;// resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /**
   * Method to set red and green to 0
   */
  public void keepOnlyBlue() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  pixelObj.setGreen(0);
			  pixelObj.setRed(0);
		  }
	  }
  }
  
  /**
   * Method to negate each color
   */
  public void negate() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  pixelObj.setGreen(255-pixelObj.getGreen());
			  pixelObj.setRed(255-pixelObj.getRed());
			  pixelObj.setBlue(255-pixelObj.getBlue());
		  }
	  }
  }
  
  /**
   * Method to turn the picture to grayscale
   */
  public void grayscale() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  int avg = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen())/3;
			  
			  pixelObj.setGreen(avg);
			  pixelObj.setRed(avg);
			  pixelObj.setBlue(avg);
		  }
	  }
  }

  /**
   * Method to create more contrast for the fish
   */
  public void fixUnderwater() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  pixelObj.setGreen(pixelObj.getGreen()-65);
			  pixelObj.setRed(pixelObj.getRed()+55);
			  pixelObj.setBlue(pixelObj.getBlue()-35);
		  }
	  }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }

  /** Method that mirrors the picture around a
   *  vertical mirror in the center of the picture
   *  from right to left */
  public void mirrorVerticalRightToLeft() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int width = pixels[0].length;
	  for (int row = 0; row < pixels.length; row++)
	  {
	    for (int col = width-1; col > width / 2; col--)
	    {
	      rightPixel = pixels[row][col];
	      leftPixel = pixels[row][width - 1 - col];
	      leftPixel.setColor(rightPixel.getColor());
	    }
	  }
  }  
  
  /** Method that mirrors the picture around a
   *  horizontal mirror in the center of the picture
   *  from top to bottom */
  public void mirrorHorizontal()
  {
	Pixel[][] pixels = this.getPixels2D();
	Pixel topPixel = null;
	Pixel bottomPixel = null;
	int height = pixels.length;
	for (int row = 0; row < height/2; row++)
	{
	  for (int col = 0; col < pixels[0].length; col++)
	  {
	    topPixel = pixels[row][col];
	    bottomPixel = pixels[height - 1 - row][col];
	    bottomPixel.setColor(topPixel.getColor());
	  }
	}  
  }
  
  /** Method that mirrors the picture around a
   *  horizontal mirror in the center of the picture
   *  from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
	Pixel[][] pixels = this.getPixels2D();
	Pixel topPixel = null;
	Pixel bottomPixel = null;
	int height = pixels.length;
	for (int row = height-1; row > height/2; row--)
	{
	  for (int col = 0; col < pixels[0].length; col++)
	  {
	    bottomPixel = pixels[row][col];
	    topPixel = pixels[height - 1 - row][col];
	    topPixel.setColor(bottomPixel.getColor());
	  }
	}  
  }
  
  /**
   * Method that mirrors a picture about a diagonal line
   */
  public void mirrorDiagonal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel blPixel = null;
	  Pixel trPixel = null;
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < row; col++)
		  {
			  if (row != col)
			  {
				  blPixel = pixels[row][col];
				  trPixel = pixels[col][row];
				  trPixel.setColor(blPixel.getColor());
			  }
		  }
	  }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }
    System.out.println(count);
  }
  
  /** Mirror part of a snowman to create one with 4 arms */
  public void mirrorArms()
  {
	  Pixel topPixel = null;
	  Pixel botPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  
	  // Left Side -- Bottom Right = (191,163) & Top Left = (159,105)
	  for (int r = 159; r <= 191; r++) {
		  for (int c=105; c<=163; c++) {
			  topPixel = pixels[r][c];
			  botPixel = pixels[r+80][c];
			  botPixel.setColor(topPixel.getColor());
		  }
	  }
	  
	  //Right Side -- Bottom Right = (196,294) & Top Left = (172,245)
	  for (int r = 172; r <= 196; r++) {
		  for (int c=245; c<=294; c++) {
			  topPixel = pixels[r][c];
			  botPixel = pixels[r+80][c];
			  botPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  /** Mirror a seagull to the right of its current position */
  public void mirrorGull()
  {
	  Pixel leftPixel = null; //(235-320,238-348)
	  Pixel rightPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int r = 235; r <= 320; r++) {
		  for (int c=238; c<=348; c++) {
			  leftPixel = pixels[r][c];
			  rightPixel = pixels[r][c+150];
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
  }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
