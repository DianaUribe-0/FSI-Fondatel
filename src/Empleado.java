import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Empleado {

    private final String id, nombre, usuario, rol;

    public Empleado(String id, String nombre, String usuario, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.rol = rol;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getRol() { return rol; }

    /**
     * Control de acceso: el usuario es el código del empleado; el rol sale de su puesto.
     * La clave se compara como hash SHA-256 y la consulta es parametrizada.
     * @return el empleado, o null si los datos son incorrectos o está inactivo
     */
    public static Empleado autenticar(Conexion cnx, String usuario, String clave) {
        String sql = "SELECT codigo, nombree, codigo, LOWER(puesto) FROM empleados "
                   + "WHERE codigo = ? AND clave = SHA2(?, 256) AND LOWER(estadoe) = 'activo'";
        try (PreparedStatement ps = cnx.con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al autenticar: " + e.getMessage());
        }
        return null;
    }
}
