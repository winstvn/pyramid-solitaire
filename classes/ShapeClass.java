// The "ShapeClass" class.
import java.awt.*;

public abstract class ShapeClass
{
    // encapsulated data
    protected int iWidth = 100;
    protected int iHeight = 140;
    protected int iCentreX = 100;
    protected int iCentreY = 100;

    // communicator methods
    public void setCentre (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }
    
    public void setCentreX (int iNewCentreX)
    {
	iCentreX = iNewCentreX;
    }
    
    
    public void setCentreY (int iNewCentreY)
    {
	iCentreY = iNewCentreY;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCentreX ()
    {
	return iCentreX;
    }


    public int getCentreY ()
    {
	return iCentreY;
    }


    // draw methods
    public abstract void draw (Graphics g, int iValue, int iSuit, int iCentreX, int iCentreY);
} // ShapeClass class
