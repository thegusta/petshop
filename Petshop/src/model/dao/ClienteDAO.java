package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {

    public void create(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_cliente (nomecliente,cpfcliente,celcliente,ruacliente,bairrocliente,cidadecliente) VALUES(?,?,?,?,?,?) ");
            
            stmt.setString(1, c.getNomecliente());
            stmt.setString(2, c.getCpfcliente());
            stmt.setString(3, c.getCelcliente());
            stmt.setString(4, c.getRuacliente());
            stmt.setString(5, c.getBairrocliente());
            stmt.setString(6, c.getCidadecliente());
            

            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Cliente> read() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNomecliente(rs.getString("nomecliente"));
                cliente.setCpfcliente(rs.getString("cpfcliente"));
                cliente.setCelcliente(rs.getString("celcliente"));
                cliente.setRuacliente(rs.getString("ruacliente"));
                cliente.setBairrocliente(rs.getString("bairrocliente"));
                cliente.setCidadecliente(rs.getString("cidadecliente"));
 
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

    public void update(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbl_cliente SET nomecliente = ?, cpfcliente = ?, celcliente = ?, ruacliente = ?, bairrocliente = ?, cidadecliente = ? WHERE id = ?");
            
            stmt.setString(1, c.getNomecliente());
            stmt.setString(2, c.getCpfcliente());
            stmt.setString(3, c.getCelcliente());
            stmt.setString(4, c.getRuacliente());
            stmt.setString(5, c.getBairrocliente());
            stmt.setString(6, c.getCidadecliente());
            stmt.setInt(7, c.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //parei aqui
    public void delete(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_cliente WHERE id = ?");
            stmt.setInt(1, c.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Cliente> readForDesc(String desc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_cliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNomecliente(rs.getString("nomecliente"));
                cliente.setCpfcliente(rs.getString("cpfcliente"));
                cliente.setCelcliente(rs.getString("celcliente"));
                cliente.setRuacliente(rs.getString("ruacliente"));
                cliente.setBairrocliente(rs.getString("bairrocliente"));
                cliente.setCidadecliente(rs.getString("cidadecliente"));
 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
}

