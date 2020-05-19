import javax.swing.JTable;

public class ExtendedTable extends JTable {
	public ExtendedTable(Object[][] currentData, String[] teamsAndAthletes) {
		// TODO Auto-generated constructor stub
		super(currentData, teamsAndAthletes);
	}

	@Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}
