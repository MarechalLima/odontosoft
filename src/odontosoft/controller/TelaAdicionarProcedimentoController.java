package odontosoft.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import odontosoft.model.dao.ProcedimentoDAO;
import odontosoft.model.domain.Procedimento;

/**
 * FXML Controller class
 *
 */
public class TelaAdicionarProcedimentoController implements Initializable {

    @FXML
    private TextArea txtAreaDescricao;
    @FXML
    private TextField txtFieldPreco;
    
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    @FXML
    public void btnCancelarClicked(){
        Stage stage = (Stage) txtFieldPreco.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void btnSalvarClicked(){
        String descricao = txtAreaDescricao.getText();
        try{ 
            Double preco = Double.parseDouble(txtFieldPreco.getText());
             Procedimento p = new Procedimento(0, descricao, preco);        
            procedimentoDAO.inserir(p);        
            Stage stage = (Stage) txtFieldPreco.getScene().getWindow();
            stage.close();
        } catch(Exception excecao) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação de erro: "+excecao);
            alert.setHeaderText("Dados errados");
            alert.setContentText("Não use '.' ou '-' ou ','");
            alert.initOwner(txtFieldPreco.getScene().getWindow());
            
            alert.showAndWait();              
        }
    }
    
    
}
