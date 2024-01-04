package br.com.treinaweb.springbootapi.atribuicoes

import spock.lang.Specification

import java.sql.Connection
import java.sql.PreparedStatement

class SqlUtilTeste extends Specification {
    PreparedStatement preparedStatement = Mock(PreparedStatement)
    Connection connection = Mock(Connection)
    def 'shouldExecuteInsert'(){
        given:

        String sql = "INSERT INTO tabela (coluna1, coluna2) VALUES (?, ?)";

        SqlUtilTest.TestObject testObject = new SqlUtilTest.TestObject();
        testObject.setColuna1("valor1");
        testObject.setColuna2("valor2");

        when:
        SqlUtil.executeInsert(sql, connection,testObject)

        then:
        1* connection.prepareStatement(_)>>preparedStatement
        1* preparedStatement.executeUpdate()
    }

    def 'shouldSetParameters'(){
        given:

        SqlUtilTest.TestObject testObject = new SqlUtilTest.TestObject();
        testObject.setColuna1("valor1");
        testObject.setColuna2("valor2");

        when:
        /*nome da classe. função ( objetos da função)*/
        SqlUtil.setParameters(preparedStatement, testObject)

        then:
        //vezes que sera executado
        1* preparedStatement.setObject(1,"valor1")
        1* preparedStatement.setObject(2, "valor2")

    }

}
