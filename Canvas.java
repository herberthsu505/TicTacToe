import javax.swing.JFrame;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Font;
public class Canvas extends JFrame implements MouseListener
{
	private BufferedImage buffered;
	private Image xs, os, image4, image5;
	private Random r;
	private Toolkit toolkit;
	private Cursor c;
	private Image[][] tic = new Image[3][3];
	private int[][] ticCheck = new int[3][3];
	private int mouseCount;
	private int x, y;
	private boolean canPlay, xWin, oWin, canClick;
	public Canvas()
	{
		super("Tic Tac Toe");

		mouseCount = 0;
		canPlay = true;
		xWin = false;
		oWin = false;
		canClick = false;
		r =new Random();
		toolkit = Toolkit.getDefaultToolkit();
  		image4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/cur.png"));
  		image5 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/icon.png"));
  		c = toolkit.createCustomCursor(image4 , new Point(getX(), getY()), "img");
		xs = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/x.png"));
		os = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/o.png"));
		int ra = 3;
		for(int r = 0; r<ticCheck.length; r++)
		{
			for(int c = 0; c<ticCheck[r].length; c++)
			{
				ticCheck[r][c]=ra;
				ra++;
			}
		}
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setCursor(c);
  		setIconImage(image5);
	
		addMouseListener(this);
	}
	public void paint(Graphics window)
	{
		if(buffered==null)
 			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
    	
    		Graphics windowTemp = buffered.createGraphics();

    		windowTemp.setColor(Color.WHITE);
    		windowTemp.fillRect(0, 0, 600, 600);
    		windowTemp.setColor(Color.BLACK);
    		windowTemp.fillRect(190, 0, 15, 600);
    		windowTemp.fillRect(395, 0, 15, 600);
    		windowTemp.fillRect(0, 190, 600, 15);
    		windowTemp.fillRect(0, 395, 600, 15);
    		windowTemp.drawImage(tic[0][0], 5, 10,this);
			windowTemp.drawImage(tic[0][1], 210, 10,this);
			windowTemp.drawImage(tic[0][2], 415, 10,this);
			windowTemp.drawImage(tic[1][0], 5, 210,this);
			windowTemp.drawImage(tic[1][1], 210, 210,this);
			windowTemp.drawImage(tic[1][2], 415, 210,this);
			windowTemp.drawImage(tic[2][0], 5, 415,this);
			windowTemp.drawImage(tic[2][1], 210, 415,this);
			windowTemp.drawImage(tic[2][2], 415, 415,this);
			windowTemp.drawImage(tic[0][0], 5, 10,this);
			windowTemp.drawImage(tic[0][1], 210, 10,this);
			windowTemp.drawImage(tic[0][2], 415, 10,this);
			windowTemp.drawImage(tic[1][0], 5, 210,this);
			windowTemp.drawImage(tic[1][1], 210, 210,this);
			windowTemp.drawImage(tic[1][2], 415, 210,this);
			windowTemp.drawImage(tic[2][0], 5, 415,this);
			windowTemp.drawImage(tic[2][1], 210, 415,this);
			windowTemp.drawImage(tic[2][2], 415, 415,this);

			if(canPlay==false)
			{
				canClick = true;
				Color tBlack = new Color (0.0f, 0.0f, 0.0f, 0.9f);
				windowTemp.setColor(tBlack);
    			windowTemp.fillRect(0, 0, 600, 600);
    			windowTemp.setColor(new Color(68, 218, 92));
    			windowTemp.fillRoundRect(205, 415, 195, 50, 10, 10);
    			windowTemp.setColor(new Color(255, 59, 37));
    			windowTemp.fillRoundRect(205, 485, 195, 50, 10, 10);
    			windowTemp.setColor(Color.BLACK);
    			windowTemp.setFont(new Font("Arial", Font.BOLD, 25)); 
    			windowTemp.drawString("Play Again", 235, 460-15);
    			windowTemp.drawString("Quit", 275, 530-15);
    			if(xWin==true)
    			{
    				windowTemp.setColor(Color.WHITE);
    				windowTemp.setFont(new Font("Arial", Font.BOLD, 40)); 
    				windowTemp.drawString("X WIN!!!", 222, 240);
    			}
    			else if(oWin==true)
    			{
    				windowTemp.setColor(Color.WHITE);
    				windowTemp.setFont(new Font("Arial", Font.BOLD, 40)); 
    				windowTemp.drawString("O WIN!!!", 222, 240);
    			}
    			else
    			{
    				windowTemp.setColor(Color.WHITE);
    				windowTemp.setFont(new Font("Arial", Font.BOLD, 40)); 
    				windowTemp.drawString("TIE", 262, 240);
    			}
			}

			checkWin();

			
    		

    		
    		window.drawImage(buffered, 0, 0, null);
    
	} 
	public void animate()
	{
		while(true)
		{	
			for(int i=0; i < 500; i++)
			{
				try 
				{
				    Thread.sleep(100);
				} 
				catch(InterruptedException ex) 
				{
				    Thread.currentThread().interrupt();
				}

				
				repaint();
			}
		}
	}
	public void mouseClicked(MouseEvent e) 
    {
    	System.out.println(e.getX()+", "+e.getY());
    	x = e.getX();
    	y = e.getY();
    	mouseCount++;
    	if(canPlay==true)
    	{	
    		if((mouseCount%2) == 1)
			{
				if(x>0&&x<190 && y>0&&y<190)
				{
					tic[0][0] = xs;
					ticCheck[0][0] = 0;
					aLogic();
					repaint();
				}
				if(x>205&&x<395 && y>0&&y<190)
				{
					tic[0][1] = xs;
					ticCheck[0][1] = 0;
					repaint();	
				}
				if(x>410&&x<600 && y>0&&y<190)
				{
					tic[0][2] = xs;
					ticCheck[0][2] = 0;
					repaint();		
				}

				if(x>0&&x<190 && y>205&&y<395)
				{
					tic[1][0] = xs;
					ticCheck[1][0] = 0;
					repaint();	
				}
				if(x>205&&x<395 && y>205&&y<395)
				{
					tic[1][1] = xs;
					ticCheck[1][1] = 0;
					repaint();			
				}
				if(x>410&&x<600 && y>205&&y<395)
				{
					tic[1][2] = xs;
					ticCheck[1][2] = 0;
					repaint();			
				}

				if(x>0&&x<190 && y>410&&y<600)
				{
					tic[2][0] = xs;
					ticCheck[2][0] = 0;
					repaint();	
				}
				if(x>205&&x<395 && y>410&&y<600)
				{
					tic[2][1] = xs;
					ticCheck[2][1] = 0;
					repaint();			
				}
				if(x>410&&x<600 && y>410&&y<600)
				{
					tic[2][2] = xs;
					ticCheck[2][2] = 0;
					repaint();			
				}
			}

			if((mouseCount%2) == 0)
			{
				if(x>0&&x<190 && y>0&&y<190)
				{
					tic[0][0] = os;
					ticCheck[0][0] = 1;
					repaint();
				}
				if(x>205&&x<395 && y>0&&y<190)
				{
					tic[0][1] = os;
					ticCheck[0][1] = 1;
					repaint();		
				}
				if(x>410&&x<600 && y>0&&y<190)
				{
					tic[0][2] = os;
					ticCheck[0][2] = 1;
					repaint();		
				}

				if(x>0&&x<190 && y>205&&y<395)
				{
					tic[1][0] = os;
					ticCheck[1][0] = 1;
					repaint();	
				}
				if(x>205&&x<395 && y>205&&y<395)
				{
					tic[1][1] = os;
					ticCheck[1][1] = 1;
					repaint();			
				}
				if(x>410&&x<600 && y>205&&y<395)
				{
					tic[1][2] = os;
					ticCheck[1][2] = 1;
					repaint();			
				}

				if(x>0&&x<190 && y>410&&y<600)
				{
					tic[2][0] = os;
					ticCheck[2][0] = 1;
					repaint();	
				}
				if(x>205&&x<395 && y>410&&y<600)
				{
					tic[2][1] = os;
					ticCheck[2][1] = 1;
					repaint();			
				}
				if(x>410&&x<600 && y>410&&y<600)
				{
					tic[2][2] = os;
					ticCheck[2][2] = 1;
					repaint();			
				}
			}
		}
		if(canClick==true)
		{
			if(x>205&&x<400 && y>415&&y<465)
			{
				replay();
			}
			if(x>205&&x<400 && y>485&&y<535)
			{
				System.exit(0);
			}
			
		}
    	repaint();

    	
	}
	public void mouseEntered(MouseEvent e) 
    {
		
	}
	public void mouseExited(MouseEvent e) 
	{
	}
	public void mousePressed(MouseEvent e) 
	{
		
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	public void replay()
	{
		mouseCount = 0;
		canPlay = true;
		xWin = false;
		oWin = false;
		canClick = false;
		r =new Random();
		int ra = 3;
		for(int r = 0; r<ticCheck.length; r++)
		{
			for(int c = 0; c<ticCheck[r].length; c++)
			{
				ticCheck[r][c]=ra;
				ra++;
			}
		}
		for(int r = 0; r<tic.length; r++)
		{
			for(int c = 0; c<tic[r].length; c++)
			{
				tic[r][c]=null;
				
			}
		}
	}
	public void checkWin()
	{
		if(ticCheck[0][0]==ticCheck[0][1]&&ticCheck[0][1]==ticCheck[0][2])
		{
			canPlay=false;
			if(ticCheck[0][0]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}
		if(ticCheck[1][0]==ticCheck[1][1]&&ticCheck[1][1]==ticCheck[1][2])
		{
			canPlay=false;
			if(ticCheck[1][0]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[2][0]==ticCheck[2][1]&&ticCheck[2][1]==ticCheck[2][2])
		{
			canPlay=false;
			if(ticCheck[2][0]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[0][0]==ticCheck[1][0]&&ticCheck[1][0]==ticCheck[2][0])
		{
			canPlay=false;
			if(ticCheck[0][0]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[0][1]==ticCheck[1][1]&&ticCheck[1][1]==ticCheck[2][1])
		{
			canPlay=false;
			if(ticCheck[0][1]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[0][2]==ticCheck[1][2]&&ticCheck[1][2]==ticCheck[2][2])
		{
			canPlay=false;
			if(ticCheck[0][2]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[0][0]==ticCheck[1][1]&&ticCheck[1][1]==ticCheck[2][2])
		{
			canPlay=false;
			if(ticCheck[0][0]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}

		if(ticCheck[0][2]==ticCheck[1][1]&&ticCheck[1][1]==ticCheck[2][0])
		{
			canPlay=false;
			if(ticCheck[0][2]==0)
			{
				xWin = true;
				repaint();
			}
			else
			{
				oWin = true;
				repaint();
			}
		}
		int count = 0;
		for(int r = 0; r<ticCheck.length; r++)
		{
			for(int c = 0; c<ticCheck[r].length; c++)
			{
				if(ticCheck[r][c]==0||ticCheck[r][c]==1)
				{
					count++;
				}
			}
		}
		if(count==8)
		{
			canPlay=false;
		}
	}
	public void aLogic()
	{
		ArrayList<Integer> xEmpty = new ArrayList<Integer>();
		ArrayList<Integer> yEmpty = new ArrayList<Integer>();
		for(int r = 0; r<ticCheck.length; r++)
		{
			for(int c = 0; c<ticCheck[r].length; c++)
			{
				if(ticCheck[r][c]==0||ticCheck[r][c]==1)
				{
					
				}
				else
				{
					xEmpty.add(r);
					yEmpty.add(c);
				}
			}
		}
		for(int i = 0; i<ticCheck.length; i++)
		{
			System.out.print(xEmpty.get(i)+", ");
			System.out.println(yEmpty.get(i));
		}



	}
	
}




