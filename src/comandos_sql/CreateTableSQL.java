/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos_sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 *
 * @author Lucas Martendal
 */
public abstract class CreateTableSQL {
    
    public static boolean CreateTable(String nomeBase, String nomeTabela, String[] nomesColunas, String[] tiposColunas) throws FileNotFoundException, IOException {
        //Create Table - Verificando se o Banco de Dados existe, caso tenha sido digitado o nome do banco
        if (!nomeBase.isEmpty()) {
            File diretorio = new File(nomeBase);
            if (!diretorio.exists()) {
                //Mensagem de erro ("Esse Banco de Dados não existe")
                return false;
            }
        }
        
        //Se nome do Banco estiver vazio, pega o último utilizado
        
        //Create Table - Verificando se o nome da Tabela é válido, de acordo com os RF's
        if (false) {
            System.out.println("ERRO - O nome da Tabela é inválido");
            //Exibir mensagem de erro ("O nome da Tabela é inválido")
            return false;
        }
        
        //Create Table - Verificando se essa Tabela (arquivo) já não existe
        File arquivo = new File(nomeBase + nomeTabela + ".dat");
        if(arquivo.exists()) {
            System.out.println("ERRO - Já existe uma Tabela com este nome");
            //Exibir mensagem de erro ("Já existe uma Tabela com este nome")
            return false;
        }

        //Create Table - Criando o arquivo da Tabela
        Path caminhoArquivo = Files.createFile(Paths.get(nomeBase + nomeTabela + ".dat"));
        
        //Create Table - Formatando os nomes e tipos das Colunas para gravar nos metadados
        String nomesColunasConcat = null;
        String tiposColunasConcat = null;
        
        for (int i = 0; i < nomesColunas.length; i++) {
            nomesColunasConcat += nomesColunasConcat + ";" + nomesColunas[i];
            tiposColunasConcat += tiposColunasConcat + ";" + tiposColunas[i];
        }
        
        //Create Table - Calculando o Tamanho do Registro (TAMREG), conforme o total do tamanho das Colunas
        int TAMREG = 7;
        
        //Create Table - Escrevendo os atributos (metadatas) no arquivo da Tabela (nome das Colunas, TamReg, etc...)
        UserDefinedFileAttributeView view = Files.getFileAttributeView(caminhoArquivo, UserDefinedFileAttributeView.class);
        view.write("TamanhoRegistro", Charset.defaultCharset().encode(String.valueOf(TAMREG)));
        view.write("NomesColunas", Charset.defaultCharset().encode(nomesColunasConcat));
        view.write("TiposColunas", Charset.defaultCharset().encode(tiposColunasConcat));
        view.write("TamanhosColunas", Charset.defaultCharset().encode(""));
        
        return true;
    }
        
}
