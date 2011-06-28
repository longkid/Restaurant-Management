package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.Serializable;

import javax.swing.JEditorPane;
import javax.swing.RepaintManager;

public class CPrintAbleEditorPanel extends JEditorPane implements Printable, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D)graphics;
		g2.setColor (Color.black);

		RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
		Dimension d = this.getSize();
		double panelWidth = d.width;
		double panelHeight = d.height;
		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scale = pageWidth / panelWidth;
		int totalNumPages = (int)Math.ceil(scale * panelHeight /
		pageHeight);

		// Check for empty pages
		if (pageIndex >= totalNumPages) return Printable.NO_SUCH_PAGE;

		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		g2.translate(0f, -pageIndex * pageHeight);
		//scale=1;
		g2.scale(scale, scale);
		this.paint(g2);
		
		return Printable.PAGE_EXISTS;
	}

}
