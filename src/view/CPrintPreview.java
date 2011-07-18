package view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CPrintPreview   extends JFrame
{
	private static final long serialVersionUID = 1L;
	private CPrintAbleEditorPanel editorPane;
	private JButton btnPrint,btnClose;
	public CPrintAbleEditorPanel getPrintAbleEditor()
	{
		return editorPane;
	}
	public JButton getButtonPrint()
	{
		return btnPrint;
	}
	public JButton getButtonClose()
	{
		return btnClose;
	}
	public CPrintPreview()
	{
		createEditorPane();
	}
	private void createEditorPane()
	{
		editorPane=new CPrintAbleEditorPanel();
		editorPane.setEditable(true);
		editorPane.setContentType("text/html");
		
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnTitle=new JPanel();
		pnTitle.setLayout(new BorderLayout());
		JPanel pnPrint=new JPanel();
		pnPrint.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnPrint=new JButton("Print Report");
		pnPrint.add(btnPrint);
		pnTitle.add(pnPrint,BorderLayout.WEST);
		
		btnClose=new JButton("Close Report");
		JPanel pnClose=new JPanel();
		pnClose.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnClose.add(btnClose);
		pnTitle.add(pnClose,BorderLayout.EAST );
		
		con.add(pnTitle,BorderLayout.NORTH);
		con.add(new JScrollPane(editorPane),BorderLayout.CENTER);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
