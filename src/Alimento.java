
import com.mysql.jdbc.PreparedStatement;
import javax.swing.ImageIcon;


public class Alimento {
    private String id,nombre, descripcion, categoria, estado;
    private ImageIcon carta;
    private Float precio;
    
    public Alimento (String id, String nombre, String descripcion, String Categoria, String Estado, String est, ImageIcon carta, Conexion cnx){
        this.id = id;
        this.nombre= nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.estado = estado; 
        this.carta= carta;
    }
    
     public int insertar(Conexion cnx){
        try {
            String sql = "INSERT INTO alimentos VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) cnx.con.prepareStatement(sql);
             ps.setString(1, id);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setFloat(4, precio);
            ps.setString(5, categoria);
            ps.setString(6, estado);
            ps.setBinaryStream(7, cnx.ImagenBytes(carta));
            return ps.executeUpdate();
           
        } catch (Exception e) {
            System.out.println("Error al insertar: "+e.getMessage());
            return 0;
        }
    }
    
}
