package avocate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class connector {
	connector(){
	}
	Connection con=null;
	public Connection connector1() {
		
		try{
       // create a database connection
       con = DriverManager.getConnection("jdbc:sqlite:avocate.db");
       Statement statement = con.createStatement();
       statement.setQueryTimeout(30);
       }
       catch(SQLException e){  System.err.println(e.getMessage());
                                JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donnée");
        }
    return con;
	}
	
	void close() {         
            try {
                  if(con != null)
                     con.close();
                  }
            catch(SQLException e) {  // Use SQLException class instead.          
                System.err.println(e); 
              }
	}
}