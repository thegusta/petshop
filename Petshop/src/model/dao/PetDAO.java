package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Pet;

public class PetDAO {
    
    
    public void create(Pet p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_pet (nome,tipo,raca,idade) VALUES(?,?,?,?) ");
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setString(3, p.getRaca());
            stmt.setString(4, p.getIdade());

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Pet> read() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pet> pets = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_pet");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pet pet = new Pet();
                
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setTipo(rs.getString("tipo"));
                pet.setRaca(rs.getString("raca"));
                pet.setIdade(rs.getString("idade"));
                
 
                pets.add(pet);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pets;
    }

    public void update(Pet p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbl_pet SET nome = ?, tipo = ?, raca = ?, idade = ? WHERE id = ?");
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setString(3, p.getRaca());
            stmt.setString(4, p.getIdade());
            
            stmt.setInt(5, p.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //parei aqui
    public void delete(Pet p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_pet WHERE id = ?");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Pet> readForDesc(String desc) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pet> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_pet WHERE nome LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pet pet = new Pet();
                
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setTipo(rs.getString("tipo"));
                pet.setRaca(rs.getString("raca"));
                pet.setIdade(rs.getString("idade"));
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
}
