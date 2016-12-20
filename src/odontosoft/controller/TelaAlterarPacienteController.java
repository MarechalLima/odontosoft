/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontosoft.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import odontosoft.model.dao.PacienteDAO;
import odontosoft.model.database.ConexaoBanco;
import odontosoft.model.domain.Paciente;
import odontosoft.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author eduardo
 */
public class TelaAlterarPacienteController implements Initializable {

    private Paciente paciente;    
    
    @FXML
    private Button btnSalvar,btnCancelar;
    @FXML
    private TextField txtFieldNomePaciente,txtFieldTelefonePaciente
            ,txtFieldCpfPaciente;
    @FXML
    private DatePicker datePickerDataNascPaciente;
    
    PacienteDAO pacienteDao = new PacienteDAO(new ConexaoBanco());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        txtFieldNomePaciente.setText(paciente.getNome());
        txtFieldTelefonePaciente.setText(paciente.getTelefone());
        txtFieldCpfPaciente.setText(paciente.getCpf());
        
    }
    
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    
    public void btnAlterar(){
        
        //Atributos da classe cliente
        String nome = txtFieldNomePaciente.getText();
        Date dataNasc = Date.valueOf(datePickerDataNascPaciente.getValue());
        String cpf = txtFieldCpfPaciente.getText();
        String telefone = txtFieldTelefonePaciente.getText();
        
        System.out.println("chegou aqui!");
        
        Paciente p = new Paciente(0, nome, dataNasc, cpf, telefone);
        
        pacienteDao.update(paciente.getId(), p);
        
        System.out.println("chegou aqui!");
        
        Stage stage = (Stage)btnSalvar.getScene().getWindow();
        stage.close();
    }
    
    public void btnCancelarClicked(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}
