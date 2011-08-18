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
	private static final long serialVersionUID = 1L;

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D graph2D = (Graphics2D)graphics;
		graph2D.setColor (Color.black);

		RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
		Dimension dimension = getSize();
		double scaleToPrint = pageFormat.getImageableWidth() / dimension.width;
		int totalNumPages = (int)Math.ceil(scaleToPrint * dimension.height /
				pageFormat.getImageableHeight());
		if (pageIndex >= totalNumPages)
			return Printable.NO_SUCH_PAGE;

		graph2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		graph2D.translate(0f, -pageIndex * pageFormat.getImageableHeight());
		graph2D.scale(scaleToPrint, scaleToPrint);
		this.paint(graph2D);
		return Printable.PAGE_EXISTS;
	}

}
