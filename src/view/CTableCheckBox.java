package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class MultiRenderer extends DefaultTableCellRenderer {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JCheckBox checkBox = new JCheckBox();
	

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
		  if (value instanceof Boolean) {
	      checkBox.setSelected(((Boolean) value).booleanValue());
	      checkBox.setHorizontalAlignment(JLabel.CENTER);
	      return checkBox;
	    }
	    String str = (value == null) ? "" : value.toString();
	    return super.getTableCellRendererComponent(table, str, isSelected,
	        hasFocus, row, column);
	  }
	}
class MultiEditor implements TableCellEditor {
	  
	  private final static int BOOLEAN = 0;

	  private final static int NUM_EDITOR = 1;

	  DefaultCellEditor[] cellEditors;

	  JComboBox comboBox;

	  int flg;

	  public MultiEditor() {
	    cellEditors = new DefaultCellEditor[NUM_EDITOR];
	    JCheckBox checkBox = new JCheckBox();
	    //checkBox.setOpaque( true );
	    checkBox.setHorizontalAlignment(JLabel.CENTER);
	    cellEditors[BOOLEAN] = new DefaultCellEditor(checkBox);
	    flg = BOOLEAN; // nobody
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (value instanceof Boolean) { // Boolean
	      flg = BOOLEAN;
	      return cellEditors[BOOLEAN].getTableCellEditorComponent(table,
	          value, isSelected, row, column);
	    }return null;
	  }

	  public Object getCellEditorValue() {
		  
		  switch (flg) {
	    case BOOLEAN:
	      return cellEditors[flg].getCellEditorValue();
	    default:
	      return null;
	    }
	  }

	  public Component getComponent() {
		  
	    return cellEditors[flg].getComponent();
	  }

	  public boolean stopCellEditing() {
	    return cellEditors[flg].stopCellEditing();
	  }

	  public void cancelCellEditing() {
	    cellEditors[flg].cancelCellEditing();
	  }

	  public boolean isCellEditable(EventObject anEvent) {
		  
	    return cellEditors[flg].isCellEditable(anEvent);
	  }

	  public boolean shouldSelectCell(EventObject anEvent) {
	    return cellEditors[flg].shouldSelectCell(anEvent);
	  }

	  public void addCellEditorListener(CellEditorListener l) {
	    cellEditors[flg].addCellEditorListener(l);
	  }

	  public void removeCellEditorListener(CellEditorListener l) {
	    cellEditors[flg].removeCellEditorListener(l);
	  }

	  public void setClickCountToStart(int n) {
	    cellEditors[flg].setClickCountToStart(n);
	  }

	  public int getClickCountToStart() {
	    return cellEditors[flg].getClickCountToStart();
	  }
	}
public class CTableCheckBox  extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnShow;
	DefaultTableModel dm =null;
	public CTableCheckBox  ()
	{
		dm = new DefaultTableModel() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
		    	  if (column == 0) {
		          return true;
		        }
		        return false;
		      }
		    };
		    dm.setDataVector(
		        new Object[][] {
		            { new Boolean(true), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            { new Boolean(false), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            { new Boolean(false), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            { new Boolean(false), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            { new Boolean(false), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            { new Boolean(false), "Boolean", "JCheckBox",
		                "JCheckBox" 
		            },
		            },
		            
		        new Object[] { "Component", "Data", "Renderer", "Editor" });

		    JTable table = new JTable(dm);
		    table.getColumn("Component").setCellRenderer(new MultiRenderer());
		    table.getColumn("Component").setCellEditor(new MultiEditor());

		    JScrollPane scroll = new JScrollPane(table);
		    
		    Container con=getContentPane();
		    con.setLayout(new BorderLayout());
		    con.add(scroll,BorderLayout.CENTER);
		    
		    JPanel pnButton=new JPanel();
		    btnShow=new JButton("Show");
		    btnShow.addActionListener(new CMyProcessButtonEvent());
		    pnButton.add(btnShow);
		    con.add(pnButton,BorderLayout.SOUTH);
		    setSize(400, 160);
		    setVisible(true);
	
	}
	private void doCountNumberChecked()
	{
		if(dm!=null)
		{
			int nRow=dm.getRowCount();
			for(int i=0;i<nRow;i++)
			{
			 @SuppressWarnings("unchecked")
			Boolean bcheck=(Boolean ) ((Vector<Object>)dm.getDataVector().elementAt(i)).elementAt(0);
			 if(bcheck)
				 JOptionPane.showMessageDialog(null, "a");
			}
		}
	}
	private class CMyProcessButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object o=arg0.getSource();
			if(o.equals(btnShow))
			{
				doCountNumberChecked();
			}
		}
		
	}

}
