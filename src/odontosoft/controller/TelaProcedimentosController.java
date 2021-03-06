package odontosoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import odontosoft.model.dao.ProcedimentoDAO;
import odontosoft.model.domain.Procedimento;

/**
 * FXML Controller class
 *
 */
public class TelaProcedimentosController implements Initializable {
    
    
    
    @FXML
    private TableView<Procedimento> tableViewProcedimentos;
    @FXML
    private TableColumn tableColumnDescricao,tableColumnPreco;
    
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private Procedimento procedimentoSelecionado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewProcedimentos();
        
        tableViewProcedimentos.getSelectionModel().selectedItemProperty().addListener(
        (observable,odlValue,newValue) -> selecionarItemTableViewProcedimentos(newValue));
    }    
    
    public void selecionarItemTableViewProcedimentos(Procedimento p){
        this.procedimentoSelecionado = p;
    }
    
    public void carregarTableViewProcedimentos(){
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        List<Procedimento> listPacientes = procedimentoDAO.listar();
        ObservableList<Procedimento> observableListPacientes = FXCollections.observableArrayList(listPacientes);
        tableViewProcedimentos.setItems(observableListPacientes);
    }
    
    public void btnAdicionarProcedimentoClicked(){
        Stage modal = new Stage();
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/odontosoft/view/FXMLTelaAdicionarProcedimento.fxml"));
            Scene scene = new Scene(parent);
            modal.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/odontosoft/view/css/bootstrap3.css").toExternalForm());
        } catch (IOException ex) {
            Logger.getLogger(PacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        modal.setTitle("Cadastrar Procedimento");
        modal.centerOnScreen();
        modal.initOwner(tableViewProcedimentos.getScene().getWindow());
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.showAndWait();
        
        carregarTableViewProcedimentos();
    }
    
    public void btnRemoverProcedimentoClicked(){
        int id = this.procedimentoSelecionado.getId();
        procedimentoDAO.delete(id);
        
        carregarTableViewProcedimentos();
    }
    
    public void btnAlterarProcedimentoClicked(){
        if(procedimentoSelecionado == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Procedimento não selecionado");
            alert.setHeaderText("Selecione um procedimento na tabela!");
            alert.initOwner(tableViewProcedimentos.getScene().getWindow());
            alert.showAndWait();
            
            return;
        }
        
        
        Stage modal = new Stage();
        
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/odontosoft/view/FXMLTelaAlterarProcedimento.fxml"));
            Parent parent = fxmlloader.load();
            TelaAlterarProcedimentoController controller = fxmlloader.getController();
            controller.setProcedimento(procedimentoSelecionado);
            
            Scene scene = new Scene(parent);
            modal.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/odontosoft/view/css/bootstrap3.css").toExternalForm());
        } catch (IOException ex) {
            Logger.getLogger(PacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        modal.setTitle("Alterar Procedimento");
        modal.centerOnScreen();
        modal.initOwner(tableViewProcedimentos.getScene().getWindow());
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.showAndWait();
        
        carregarTableViewProcedimentos();
    }
    
}
