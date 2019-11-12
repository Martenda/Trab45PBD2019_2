/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos_sql;

import principal.ExecutarSQL;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import principal.FramePrincipal;

/**
 *
 * @author Lucas Martendal
 */
public abstract class SelectSQL {
    
    public static boolean Select(String nomeBase, String nomeTabela, JTable tblSQLResults) throws IOException {
        //Select - Verificando se o Banco de Dados existe, caso tenha sido digitado o nome do banco
        if (!nomeBase.isEmpty()) {
            File diretorio = new File(nomeBase);
            if (!diretorio.exists()) {
                System.out.println("ERRO - Esse Banco de Dados não existe");
                //Exibir mensagem de erro ("Esse Banco de Dados não existe")
                return false;
            }
        }
        
        //Select - Verificando se a Tabela existe
        File diretorio = new File(nomeBase + nomeTabela + ".dat");
        if (!diretorio.exists()) {
            System.out.println("ERRO - Essa Tabela não existe");
            //Mensagem de erro ("Essa Tabela não existe")
            return false;
        }
        
        //Select - Criando o raf para ler o arquivo da Tabela
        RandomAccessFile raf = new RandomAccessFile(nomeBase + nomeTabela + ".dat", "rw");
        
        //Select - Lendo os MetaDados do arquivo
//        System.out.println(view.list().get(0));
        Path caminhoArquivo = Paths.get(nomeBase + nomeTabela + ".dat");
        UserDefinedFileAttributeView view = Files.getFileAttributeView(caminhoArquivo,
                UserDefinedFileAttributeView.class);
        ByteBuffer buf;
        buf = ByteBuffer.allocateDirect(view.size("TamanhoRegistro"));
        view.read("TamanhoRegistro", buf);
        buf.flip();
        String tamanhoReg = Charset.defaultCharset().decode(buf).toString();
        buf = ByteBuffer.allocateDirect(view.size("NomesColunas"));
        view.read("NomesColunas", buf);
        buf.flip();
        String nomesColunasConcat = Charset.defaultCharset().decode(buf).toString();
        buf = ByteBuffer.allocateDirect(view.size("TiposColunas"));
        view.read("TiposColunas", buf);
        buf.flip();
        String tiposColunasConcat = Charset.defaultCharset().decode(buf).toString();
        
//        System.out.println(tamanhoReg);
//        System.out.println(nomesColunasConcat);
//        System.out.println(tiposColunasConcat);
        
        //Select - Pegando os nomes e tipos das colunas (desconcatenando)
        String[] nomesColunasTabela = nomesColunasConcat.split(";");
        String[] tiposColunasTabela = tiposColunasConcat.split(";");
        
        //Select - Pegando o Tamanho do Registro (TAMREG) dos MetaDados do arquivo
        int TAMREG = Integer.parseInt(tamanhoReg);
        
        //Select - Calculando a quantidade de registros no arquivo da Tabela
        int qtdRegistros = (int) (raf.length() / TAMREG);
//        System.out.println(qtdRegistros);
        
        //Select - Criando a matriz de dados da Tabela
        Object[][] dadosTabelaVisual = new Object[qtdRegistros][nomesColunasTabela.length];
        
        //Select - Colocando o ponteiro no início do arquivo da Tabela
        raf.seek(0);
        
        //Select - Percorrendo todos os registros do arquivo da Tabela
        
        for (int i = 0; i < qtdRegistros; i++) {
            for (int j = 0; j < nomesColunasTabela.length; j++) {
                if (tiposColunasTabela[j].equalsIgnoreCase("int")) {
                    dadosTabelaVisual[i][j] = raf.readInt();
                } else {
                    if (tiposColunasTabela[j].equalsIgnoreCase("float")) {
                        dadosTabelaVisual[i][j] = raf.readDouble();
                    } else {
                        //Select - Verificando tamanho de registro a ser lido
                        int tamanhoRegistroASerLido = CreateTableSQL.CalculaTamanhoColuna(tiposColunasTabela[j]);
                        byte[] registro = new byte[tamanhoRegistroASerLido];
                        
                        //Select - Lendo registro do arquivo
                        raf.read(registro);
                        
                        //Select - Atribuindo valor (com Trim())
                        dadosTabelaVisual[i][j] = new String(registro, 0, tamanhoRegistroASerLido).trim();
                    }
                }
            }
        }
        
        tblSQLResults.setModel(new javax.swing.table.DefaultTableModel(dadosTabelaVisual, nomesColunasTabela));
        
        //Fechando o raf
        raf.close();
        
        return true;
    }
    
}
