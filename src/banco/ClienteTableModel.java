
package banco;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ClienteTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"Nome", "Sobrenome", "RG", "CPF", "Endereço"};
    
    private List<Cliente> lista = new ArrayList();
    
    public ClienteTableModel(List<Cliente> lista) {
        this.lista=lista;
        
        
    }
    
    public ClienteTableModel(){
      
    }
    
    public void setListaContatos(HashMap<String, Cliente> hashClientes) {
        lista = new ArrayList<>(hashClientes.values());
        Collections.sort(lista);
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount(){
        return this.lista.size();
    }
    
    @Override
    public int getColumnCount(){
        return this.colunas.length;
    }
    
    @Override
    public String getColumnName (int index) {
        return this.colunas[index];
    }
    
    @Override
    public boolean isCellEditable (int row, int column) {
        return false;
    }
    
    @Override
    public Object getValueAt (int rowIndex, int columnIndex){
        Cliente customer = lista.get(rowIndex);
        switch (columnIndex){
            case 0: return customer.getNome();
            case 1: return customer.getSobrenome();
            case 2: return customer.getRg();
            case 3: return customer.getCpf();
            case 4: return customer.getEndereco();
            default : return null;
        }
    }
    
 public boolean removeCliente(Cliente customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaCliente(Cliente customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaContatos(List<Cliente> contatos) {
        this.lista = contatos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }
    
    /* public void setListaContatos(HashMap<String,Cliente> contatos) {
        
        this.lista = new ArrayList<Cliente>();
        for(Cliente c:contatos.values()){
            this.lista.add(c);
        }
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    } */

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Cliente getCliente(int linha){
        return lista.get(linha);
    }

    public void removeClientes(List<Cliente> listaParaExcluir) {
        for(Cliente c:listaParaExcluir)
            removeCliente(c);
    }
    
    public void atualizarCliente(int linha){
        this.fireTableRowsUpdated(linha, linha);
    }
       
     public void ordenarPorNome() {
        Collections.sort(lista, Comparator.comparing(Cliente::getNome));
        fireTableDataChanged();
    }

    public void ordenarPorSobrenome() {
        Collections.sort(lista, Comparator.comparing(Cliente::getSobrenome));
        fireTableDataChanged();
    }

   /* public void ordenarPorMaiorSalario() {
        Collections.sort(lista, Comparator.comparing(Cliente::getSalario));
        fireTableDataChanged();
    }
    */
}
