/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos_sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Arrays;

/**
 *
 * @author Lucas Martendal
 */
public abstract class InsertSQL {
    
    public static boolean Insert(String nomeBase, String nomeTabela, String[] nomesColunasInserir, String[] valoresColunasInserir) throws FileNotFoundException, IOException {
        //Insert - Verificando se o Banco de Dados existe, caso tenha sido digitado o nome do banco
        if (!nomeBase.isEmpty()) {
            File diretorio = new File(nomeBase);
            if (!diretorio.exists()) {
                System.out.println("ERRO - Esse Banco de Dados não existe");
                //Exibir mensagem de erro ("Esse Banco de Dados não existe")
                return false;
            }
        }
        
        //Insert - Verificando se a Tabela existe
        File diretorio = new File(nomeBase + nomeTabela + ".dat");
        if (!diretorio.exists()) {
            System.out.println("ERRO - Essa Tabela não existe");
            //Mensagem de erro ("Essa Tabela não existe")
            return false;
        }
        
        //Insert - Criando o raf do arquivo da Tabela
        RandomAccessFile raf = new RandomAccessFile(nomeBase + nomeTabela + ".dat", "rw");
        
        //Insert - Lendo os MetaDados do arquivo
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
        
        System.out.println(tamanhoReg);
        System.out.println(nomesColunasConcat);
        System.out.println(tiposColunasConcat);
        
        //Insert - Pegando o Tamanho do Registro (TAMREG) nos MetaDados do arquivo
        int TAMREG = Integer.parseInt(tamanhoReg);
        
        //Insert - Pegando as colunas e seus tipos nos Metadados do arquivo
        String[] nomesColunas = nomesColunasConcat.split(";");
        String[] tiposColunas = tiposColunasConcat.split(";");
        
        //Insert - Verificando se existem essas colunas do Insert SQL
        for (int i = 0; i < nomesColunasInserir.length; i++) {
//            System.out.println(nomesColunasInserir[i]);
            
            boolean colunaExiste = false;

            for (int j = 0; j < nomesColunas.length; j++) {
//                System.out.println(nomesColunas[j]);
                
                if (nomesColunasInserir[i].equalsIgnoreCase(nomesColunas[j])) {
                    colunaExiste = true;
                }
            }
            
            if (!colunaExiste) {
                System.out.println("ERRO - Colunas do comando Insert inexistentes");
                //Exibir mensagem de erro ("Colunas do comando Insert inexistentes")
                return false;
            }
        }
        
        //Insert - Pulando com o ponteiro para o final do arquivopara inserir
        raf.seek(raf.length());

//        raf.setLength(0);
//        raf.seek(3 * TAMREG); //3° registro - vai gravar como último - length/size

        //Insert - Escrevendo os dados no arquivo da Tabela
        for (int i = 0; i < nomesColunas.length; i++) {
            System.out.println(nomesColunas[i]);
            
            boolean gravarColuna = false;
            
            for (int j = 0; j < nomesColunasInserir.length; j++) {
                System.out.println(nomesColunasInserir[j]);
                
                if (nomesColunas[i].equalsIgnoreCase(nomesColunasInserir[j])) {
                    gravarColuna = true;
                    
//                    System.out.println("Gravar a coluna " + nomesColunas[i]);

                    GravarValorColuna(raf, valoresColunasInserir[j], nomesColunas[i], tiposColunas[i]);
                }
            }
            
            if (!gravarColuna) {
//                System.out.println("Gravar vazio na coluna " + nomesColunas[i]);

                GravarColunaVazia(raf, nomesColunas[i], tiposColunas[i]);
            }
        }
        
        //Fechando o raf
        raf.close();
        
        return true;
    }
    
    public static void GravarValorColuna(RandomAccessFile raf, String valorColunaInserir, String nomeColuna, String tipoColuna) throws IOException {
        if (tipoColuna.equalsIgnoreCase("int")) {
            raf.writeInt(Integer.parseInt(valorColunaInserir));
        } else {
            if (tipoColuna.equalsIgnoreCase("float")) {
                raf.writeDouble(Float.parseFloat(valorColunaInserir));
            } else {
                //Insert - Removendo as aspas simples do valor a ser inserido
                valorColunaInserir = valorColunaInserir.replaceAll("^\'|\'$", "");
                
                //Insert - Preenchendo o restante do campo com espaços em branco à direita
                int tamanhoColuna = CreateTableSQL.CalculaTamanhoColuna(tipoColuna);
                int espacosFaltando = tamanhoColuna - valorColunaInserir.length();
                
                for (int i = 0; i < espacosFaltando; i++) {
                    valorColunaInserir += " ";
                }
                
                //Insert - Escrevendo o valor no campo
                raf.write(valorColunaInserir.getBytes(), 0, tamanhoColuna);
            }
        }
    }
    
    public static void GravarColunaVazia(RandomAccessFile raf, String nomeColuna, String tipoColuna) throws IOException {
        if (tipoColuna.equalsIgnoreCase("int")) {
            raf.writeInt(0);
        } else {
            if (tipoColuna.equalsIgnoreCase("float")) {
                raf.writeDouble(0);
            } else {
                //Preenchendo com espaços em branco
                byte[] emBranco = new byte[CreateTableSQL.CalculaTamanhoColuna(tipoColuna)];
                Arrays.fill(emBranco, " ".getBytes()[0]);
                
                raf.write(emBranco);
            }
        }
    }
    
}
