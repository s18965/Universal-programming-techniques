package zad1;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableCellRenderer;
import javax.xml.crypto.Data;

public class CountryTable {
    String countriesFilename;
    ArrayList<Country> list=new ArrayList<>();
    String[] kolumny;
    String linia;
    int numerLinii;
    private DefaultTableModel model;
    Object[][] tablica;

    public CountryTable(String countriesFileName) {
       this.countriesFilename=countriesFileName;
       numerLinii=0;
    }
    {
        try {
            BufferedReader br
                    = new BufferedReader(new FileReader("data/countries.txt"));

                while((linia=br.readLine()) != null){
                    if(numerLinii>0){
                      String taLinia[]=linia.split("\t");
                      list.add(new Country(taLinia[0], taLinia[1], Integer.parseInt(taLinia[2])));
                    }
                    else
                    { kolumny=linia.split("\t");
                    }
                    numerLinii++;
            }
                tablica=new Object[numerLinii-1][5];
                for(int i=0; i<list.size();i++){
                    tablica[i][0]=list.get(i).Name;
                    tablica[i][1]=list.get(i).Capital;
                    tablica[i][2]=list.get(i).Population;
                }

        br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JTable create(){

        JTable table = new JTable(model=new DefaultTableModel(tablica, kolumny)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 2;
            }
            @Override
            public Class getColumnClass(int column)
            {
                switch(column) {
                    case 0: return String.class;
                    case 1: return String.class;
                    case 2: return Integer.class;
                    case 3: return ImageIcon.class;
                    case 4: return Data.class;
                    default: return Object.class;
            }}
        }
        );
        table.setRowHeight(100);

        table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                        c.setForeground(((Integer) value)>20000 ? Color.RED : Color.black);
                return c;
            }
        }); //koncepcja skontruowana dzięki pomocy stackoverflow

            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent tme) {
                    if (tme.getType() == TableModelEvent.UPDATE) {
                        if(LocalDate.now()!=table.getValueAt(0,4)){
                            if(model.getValueAt(tme.getFirstRow(),2)!=(Integer)tablica[tme.getFirstRow()][2]
                        ){
                            table.setValueAt(LocalDateTime.now(),tme.getFirstRow(),4);
                        }
                    }
                }
            }});//koncepcja skontruowana dzięki pomocy stackoverflow

        ImageIcon icon;
        for(int i=0; i<tablica.length; i++){
            String nazwaKraju=list.get(i).Name;
            icon = new ImageIcon("data/"+nazwaKraju+".png");
            table.setValueAt(icon,i,3);
            table.setValueAt(LocalDateTime.now(),i,4);
        }
        return table;
    }


}