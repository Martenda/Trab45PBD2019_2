/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos_sql;

import java.io.File;

/**
 *
 * @author Lucas Martendal
 */
public abstract class CreateDataBaseSQL {
    
    public static boolean CreateDataBase(String nomeBase) {
        //Create Data Base - Verificando se o nome do Banco de Dados foi informado
        if (nomeBase.isEmpty()) {
            System.out.println("ERRO - O nome do Banco de Dados não foi informado");
            //Exibir mensagem de erro ("O nome do Banco de Dados não foi informado")
            return false;
        }
        
        //Create Data Base - Verificando se o nome do Banco de Dados é válido, de acordo com os RF's
        if (false) {
            System.out.println("ERRO - O nome do Banco de Dados é inválido");
            //Exibir mensagem de erro ("O nome do Banco de Dados é inválido")
            return false;
        }
        
        //Create Data Base - Criando a pasta do Banco, caso não exista
        File diretorio = new File(nomeBase);
        if (!diretorio.mkdir()) {
            System.out.println("ERRO - Não foi possível criar o Banco de Dados");
            //Exibir mensagem de erro ("Não foi possível criar o Banco de Dados")
            return false;
        }
        
        return true;
    }
    
}
